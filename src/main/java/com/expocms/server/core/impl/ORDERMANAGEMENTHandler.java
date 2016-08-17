package com.expocms.server.core.impl;

import java.util.Calendar;
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
import com.expocms.server.tools.ToolDays;
import com.expocms.server.tools.ToolMoney;

@Service("ORDERMANAGEMENTHandler")
@Transactional
public class ORDERMANAGEMENTHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(ORDERMANAGEMENTHandler.class);
	
	@Autowired
	private RdOrderMapper rdOrderMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommonResponse();
		
		Calendar today = ToolDays.getTodayCalendar();
		String dateStr = Constants.SDF_SHORT.format(today.getTime());
		
		List<RdOrder> orderList = null;
		try {
			orderList = rdOrderMapper.getOrdersToRepaid(dateStr);
		} catch (Exception e) {
			logger.error("exception happened while get order list! " + e.getMessage());
		}
		
		if(orderList != null && orderList.size() != 0) {
			for(int i = 0; i < orderList.size(); i ++) {
				RdOrder order = orderList.get(i);
				
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
									"今日到期自动赎回。本息收益共" + ToolMoney.F2Y(order.getRepaidamount()) +
									"元将在2-3个工作日内到达您" + bankInfo +
									"账户，请注意查收。详情请登录融道理财APP（我的资产）查看。【融道理财】";
				try {
					QUICKPAYMENTHandler.sendSMS_via_PINGTAI(phoneNo, smsContent);
				} catch(RuntimeException e) {
					logger.error("exception happened while send SMS to customer " + phoneNo + "! " + e.getMessage());
				}
			}
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
