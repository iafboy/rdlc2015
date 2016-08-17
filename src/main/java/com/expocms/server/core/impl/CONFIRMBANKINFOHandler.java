package com.expocms.server.core.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.ConfirmBankInfoRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.schedule.OrderManagementTask;
import com.expocms.server.util.BankInfo;
import com.expocms.server.util.BankInfoManager;
import com.payment.server.agent.command.params.KQ_BankInfo;
import com.payment.server.agent.command.params.KQ_ConfirmVerifyBankInfo;
import com.payment.server.agent.command.params.KQ_ConfirmVerifyBankResult;

public class CONFIRMBANKINFOHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(CONFIRMBANKINFOHandler.class);
	
	@Autowired
    private RdAppuserMapper rdAppuserMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		returnResp = new CommonResponse();
		
		ConfirmBankInfoRequest cbiRequest = (ConfirmBankInfoRequest)request;
		
		String userId = cbiRequest.getUserId();
        if(userId == null || userId.equals("")) {
        	returnResp.setResultCode(1);
        	returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
        RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		BankInfo bankInfo = BankInfoManager.instance().getBankInfo(userId);
		if(bankInfo == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("缺少银行信息");
			return returnResp;
		}
		
		try {
			String bankName = bankInfo.getBankName();
			String bankCardNo = bankInfo.getBankCardNo();
			String bankCardHolderName = bankInfo.getBankCardHolderName();
			String bankCardHolderId = bankInfo.getBankCardHolderId();
			String payPhone = bankInfo.getPayPhone();
			String validCode = cbiRequest.getValidCode();
			
			KQ_ConfirmVerifyBankInfo cvbi = new KQ_ConfirmVerifyBankInfo();
			cvbi.setBankInfo(new KQ_BankInfo());
			cvbi.getBankInfo().setBankName(bankName);
			cvbi.getBankInfo().setBankCardNo(bankCardNo);
			cvbi.getBankInfo().setBankCardHolderName(bankCardHolderName);
			cvbi.getBankInfo().setBankCardHolderId(bankCardHolderId);
			cvbi.getBankInfo().setPayPhone(payPhone);
			cvbi.setUserId(userId);
			cvbi.setExternalRefNo(userId);
			cvbi.setValidCode(validCode);
			cvbi.setToken(rdAppuser.getToken());
			
			String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_ConfirmVerifyBank";
			//String url = "http://localhost:13000" + "/payment/KQ_ConfirmVerifyBank";
			String s = OrderManagementTask.sendRequest(url, cvbi);
			logger.info("original response from TPS:\n" + s);
			
			KQ_ConfirmVerifyBankResult cvbr = JSON.parseObject(s, KQ_ConfirmVerifyBankResult.class);
			if(cvbr == null) {
				logger.error("confirm bank info from KUAI_QIAN failed, cause no response from KUAI_QIAN!");
				returnResp.setResultCode(1);
	        	returnResp.setResultMsg("绑定银行卡失败");
	        	return returnResp;
			}
			
			String responseCode = cvbr.getCode();
			if(responseCode == null || "00".equals(responseCode) == false) {
				if (responseCode == null || responseCode.trim().equals(""))
					responseCode = "未知代码";
	
				String responseMessage = cvbr.getDetailMessage();
				if (responseMessage == null || responseMessage.equals(""))
					responseMessage = "未知原因";
	
				logger.error("绑定失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
				returnResp.setResultCode(2);
	        	returnResp.setResultMsg("绑定失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
	        	return returnResp;
			}
			
			rdAppuser.setBankname(bankName);
			rdAppuser.setBankcardno(bankCardNo);
			rdAppuser.setCardHolder(bankCardHolderName);
			rdAppuser.setIdcard(bankCardHolderId);
			rdAppuser.setIssuer(bankName);
			rdAppuser.setUsername(bankCardHolderName);
			rdAppuser.setPayPhone(payPhone);
			rdAppuser.setIsBankModified(1);
	        if(rdAppuserMapper.updateBankInfoByIds(rdAppuser) != 1) {
	        	logger.error("update bank-info for user " + userId + " failed!");
	        	throw new RuntimeException("绑定银行卡失败");
	        }
	        
		} finally {
			BankInfoManager.instance().removeBankInfo(userId);
		}
		
		returnResp.setResultCode(Constants.succCode);
    	returnResp.setResultMsg(Constants.succMsg);
        return returnResp;
	}

}
