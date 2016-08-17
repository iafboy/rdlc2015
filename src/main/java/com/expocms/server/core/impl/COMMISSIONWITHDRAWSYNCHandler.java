package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserWithdrawHistoryMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserWithdrawHistory;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.schedule.OrderManagementTask;
import com.expocms.server.tools.ToolMoney;
import com.expocms.server.util.BatchPaymentSync;
import com.payment.server.agent.command.params.KQ_BankInfo;
import com.payment.server.agent.command.params.KQ_BatchRepaymentDealInfo;
import com.payment.server.agent.command.params.KQ_BatchRepaymentInfo;
import com.payment.server.agent.command.params.KQ_BatchRepaymentResult;
import com.payment.server.agent.command.params.KQ_QueryRepaymentDealInfo;

@Service("COMMISSIONWITHDRAWSYNCHandler")
@Transactional
public class COMMISSIONWITHDRAWSYNCHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(COMMISSIONWITHDRAWSYNCHandler.class);
	
	@Autowired
	private RdAppuserWithdrawHistoryMapper rdAppuserWithdrawHistoryMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommonResponse();
		
		Date nowTime = new Date();
		List<RdAppuserWithdrawHistory> withdrawList = null;
		
		// 从快钱查询付款结果 ...
		withdrawList = rdAppuserWithdrawHistoryMapper.getWithdrawHistoryByStatus(RdAppuserWithdrawHistory.RAWD_IN_PROGRESS);
		if(withdrawList != null && withdrawList.size() != 0) {
			Map<String, KQ_QueryRepaymentDealInfo> dealMap = BatchPaymentSync.instance().getDealQueryMap();
			if(dealMap != null && dealMap.size() != 0) {
				for(int i = 0; i < withdrawList.size(); i ++) {
					RdAppuserWithdrawHistory withdraw = withdrawList.get(i);
					KQ_QueryRepaymentDealInfo deal = dealMap.get(withdraw.getIds());
					if(deal == null)
						continue;
					
					if("111".equals(deal.getDealStatus())) {
						// success
						withdraw.setStatus(RdAppuserWithdrawHistory.RAWD_SUCCEED);
						withdraw.setFinishTime(nowTime);
						withdraw.setThirdPartyAmount(deal.getAmount());
					} else if("101".equals(deal.getDealStatus())) {
						// process on going
						continue;
					} else {
						withdraw.setStatus(RdAppuserWithdrawHistory.RAWD_FAILED);
						withdraw.setFinishTime(nowTime);
						withdraw.setThirdPartyAmount(deal.getAmount());
						String failedReason =
								"status=" + deal.getDealStatus() +
								"\n" +
								"errorCode=" + deal.getErrorCode() +
								"\n" +
								"errorMessage=" + deal.getErrorMessage() +
								"\n" +
								"bankErrorCode=" + deal.getBankErrorCode() +
								"\n" +
								"bankErrorMessage=" + deal.getBankErrorMessage();
						withdraw.setFailedReason(failedReason);
					}
					
					if(rdAppuserWithdrawHistoryMapper.updateStatusByIds(withdraw) != 1) {
						logger.error("update withdraw " + withdraw.getIds() + " failed!");
						continue;
					}
				}
			}
		}
		
		// 提交付款请求给快钱 ...
		withdrawList = rdAppuserWithdrawHistoryMapper.getWithdrawHistoryByStatus(RdAppuserWithdrawHistory.RAWD_APPROVED);
		if(withdrawList != null && withdrawList.size() != 0) {
			List<KQ_BatchRepaymentDealInfo> dealList = new ArrayList<KQ_BatchRepaymentDealInfo>();
			for(int i = 0; i < withdrawList.size(); i ++) {
				RdAppuserWithdrawHistory withdraw = withdrawList.get(i);
				
				RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(withdraw.getUserId());
				if(rdAppuser == null) {
					logger.error("user " + withdraw.getUserId() + " not found!");
					continue;
				}

				withdraw.setStatus(RdAppuserWithdrawHistory.RAWD_IN_PROGRESS);
				if(rdAppuserWithdrawHistoryMapper.updateStatusByIds(withdraw) != 1) {
					logger.error("update withdraw " + withdraw.getIds() + " failed!");
					continue;
				}

				KQ_BatchRepaymentDealInfo deal = new KQ_BatchRepaymentDealInfo();
				deal.setDealId(withdraw.getIds());
				deal.setAmount(withdraw.getArrivalAmount());
				if(rdAppuser.getPayPhone() != null && rdAppuser.getPayPhone().equals("") == false)
					deal.setPhone(rdAppuser.getPayPhone());
				else
					deal.setPhone(rdAppuser.getPhone());
				deal.setNote("您的佣金提现申请" + ToolMoney.F2Y(withdraw.getArrivalAmount()) + "元已获批准，请注意查收！【融道理财】");

				KQ_BankInfo bankInfo = new KQ_BankInfo();
				bankInfo.setBankName(rdAppuser.getBankname());
				bankInfo.setBankCardNo(rdAppuser.getBankcardno());
				bankInfo.setBankCardHolderName(rdAppuser.getCardHolder());
				bankInfo.setBankCardHolderId(rdAppuser.getIdcard());
				bankInfo.setPayPhone(rdAppuser.getPayPhone());
				bankInfo.setBankProvince("全国");
				//bankInfo.setBankCity("北京");
				
				deal.setBankInfo(bankInfo);
				dealList.add(deal);
			}
			
			KQ_BatchRepaymentInfo bri = new KQ_BatchRepaymentInfo();
			bri.setPurpose("commission withdraw");
			bri.setDealList(dealList);
			
			String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_BatchRepayment";
			//String url = "http://123.56.109.118:" + 38888 + "/payment/KQ_BatchRepayment";
			String s = OrderManagementTask.sendRequest(url, bri);
			if(s == null || s.equals("")) {
				logger.error("send batch repayment request to TPS failed!");
			} else {
				logger.info("original response from TPS:\n" + s);
				try {
					KQ_BatchRepaymentResult resp = JSON.parseObject(s, KQ_BatchRepaymentResult.class);
					if("00".equals(resp.getCode()) == false) {
						logger.error("send batch repayment request to TPS failed!\n" + resp.getDetailMessage());
					}
					logger.info(resp.getDetailMessage());
				} catch (Exception e) {
					logger.error("Exception happened while parse string to KQ_QueryRepaymentResult object! " + e);
				}
			}
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
