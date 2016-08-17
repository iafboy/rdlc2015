package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserWithdrawHistoryMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserWithdrawHistory;
import com.expocms.server.request.types.impl.WithdrawDetailRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.WithdrawDetail;
import com.expocms.server.response.types.impl.WithdrawDetailResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("WITHDRAWDETAILHandler")
@Transactional
public class WITHDRAWDETAILHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(WITHDRAWDETAILHandler.class);
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdAppuserWithdrawHistoryMapper rdAppuserWithdrawHistoryMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new WithdrawDetailResponse();
		WithdrawDetailResponse wdResponse = (WithdrawDetailResponse)returnResp;
		
		WithdrawDetailRequest wdRequest = (WithdrawDetailRequest)request;
		String userId = wdRequest.getUserId();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
		if (rdAppuser == null) {
			logger.error("user " + userId + " does not exist!");
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		List<RdAppuserWithdrawHistory> withdrawList = rdAppuserWithdrawHistoryMapper.getWithdrawHistoryByIds(userId);
		if(withdrawList != null && withdrawList.size() != 0) {
			List<WithdrawDetail> withdrawDetailList = new ArrayList<WithdrawDetail>();
			
			for(int i = 0; i < withdrawList.size(); i ++) {
				RdAppuserWithdrawHistory withdraw = withdrawList.get(i);
				if(withdraw.getStatus() == null)
					continue;
				
				WithdrawDetail detail = new WithdrawDetail();
				
				detail.setWithdrawTime(Constants.SDF_LONG.format(withdraw.getSubmitTime()));
				detail.setPoundageAmount(withdraw.getPoundageAmount());
				
				if(withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_SUCCEED) {
					long thirdPartyAmount = 0;
					try {
						thirdPartyAmount = withdraw.getThirdPartyAmount() != null ? Long.parseLong(withdraw.getThirdPartyAmount()) : 0;
					} catch (NumberFormatException e) {
						logger.error("user " + userId + " third-party amount is invalid! " + withdraw.getThirdPartyAmount());
					}
					detail.setWithdrawAmount(thirdPartyAmount);
					detail.setArrivalTime(Constants.SDF_LONG.format(withdraw.getFinishTime()));
				} else {
					detail.setWithdrawAmount(withdraw.getArrivalAmount());
					detail.setArrivalTime(Constants.SDF_SHORT.format(withdraw.getArrivalTime()));
				}
				
				if(withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_APPLIED)
					detail.setWithdrawStatus("审核中");
				else if(withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_APPROVED ||
						withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_IN_PROGRESS)
					detail.setWithdrawStatus("处理中");
				else if(withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_SUCCEED)
					detail.setWithdrawStatus("成功");
				else if(withdraw.getStatus() == RdAppuserWithdrawHistory.RAWD_FAILED)
					detail.setWithdrawStatus("失败");
				
				withdrawDetailList.add(detail);
			}
			
			wdResponse.setWithdrawDetailList(withdrawDetailList);
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
