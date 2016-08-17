package com.expocms.server.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.model.RdOrder;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolMoney;
import com.expocms.server.util.BatchPaymentSync;
import com.payment.server.agent.command.params.KQ_QueryRepaymentDealInfo;

@Service("ORDERREPAYMENTHandler")
@Transactional
public class ORDERREPAYMENTHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(ORDERREPAYMENTHandler.class);
	
	private final String ORDER_DEAL_ID_TOKEN = "_DEAL_";
	
	@Autowired
	private RdOrderMapper rdOrderMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommonResponse();
		
		List<KQ_QueryRepaymentDealInfo> dealList = BatchPaymentSync.instance().getDealQueryList();
		if(dealList == null || dealList.size() == 0) {
			logger.error("no repayment order found!");
			returnResp.setResultCode(Constants.failCode);
			returnResp.setResultMsg(Constants.failMsg);
			return returnResp;
		}
		
		for(int i = 0; i < dealList.size(); i ++) {
			KQ_QueryRepaymentDealInfo deal = dealList.get(i);
			if(deal == null)
				continue;
			if("111".equals(deal.getDealStatus()) == false)
				continue;
			if(deal.getDealId() == null || deal.getDealId().equals(""))
				continue;
			
			String orderId = deal.getDealId();
			int index = deal.getDealId().indexOf(ORDER_DEAL_ID_TOKEN);
			if(index != -1)
				orderId = deal.getDealId().substring(0, index);
			
			RdOrder order = rdOrderMapper.getOrderToRepaid(orderId);
			if(order == null)
				continue;
			
			order.setStatus(RdOrder.ORDER_STATUS_REPAIED);
			if(rdOrderMapper.updateOrderStatus(order) != 1) {
				logger.error("update order status from repaying to repaid failed!");
				continue;
			}

			String phoneNo = order.getAppUserPhone();
			if(phoneNo == null || phoneNo.equals(""))
				continue;
			
			String bankInfo = "";
			if(order.getBankname() != null && order.getBankname().equals("") == false) {
				String bankCardNo = order.getBankCardNo();
		        String storableCardNo = "";
		        if(bankCardNo != null && bankCardNo.length() > 4)
		        	storableCardNo = bankCardNo.substring(bankCardNo.length() - 4);
		        bankInfo = order.getBankname() + "(尾号" + storableCardNo + ")";
			}
			
			String smsContent = "您在" + Constants.SDF_LONG_CHN.format(order.getOrdercreatetime()) +
								"购买的" + RdProduct.getFullProudctName(order.getProductname(), order.getProductseries()) +
								"：本息收益共" + ToolMoney.F2Y(order.getRepaidamount()) +
								"元已回款至您的" + bankInfo +
								"账户，融道活动进行中，登录融道了解详情。【融道理财】";
			try {
				QUICKPAYMENTHandler.sendSMS_via_PINGTAI(phoneNo, smsContent);
			} catch(RuntimeException e) {
				logger.error("exception happened while send SMS to customer " + phoneNo + "! " + e.getMessage());
			}
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
