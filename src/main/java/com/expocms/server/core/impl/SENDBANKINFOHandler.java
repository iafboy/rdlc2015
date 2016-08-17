package com.expocms.server.core.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.SendBankInfoRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.schedule.OrderManagementTask;
import com.expocms.server.util.BankInfo;
import com.expocms.server.util.BankInfoManager;
import com.payment.server.agent.command.params.KQ_BankInfo;
import com.payment.server.agent.command.params.KQ_VerifyBankInfo;
import com.payment.server.agent.command.params.KQ_VerifyBankResult;

@Service ("SENDBANKINFOHandler")
@Transactional
public class SENDBANKINFOHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(SENDBANKINFOHandler.class);

    @Autowired
    private RdAppuserMapper rdAppuserMapper;

    public SENDBANKINFOHandler() {
        super ();
    }

    public BaseResponse handleRequest (final IRequest request) {
    	returnResp = new CommonResponse();
        
        SendBankInfoRequest sbiRequest = (SendBankInfoRequest)request;
        
        String userId = sbiRequest.getUserId();
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
		
		String bankName = sbiRequest.getBankName();
		String bankCardNo = sbiRequest.getCardNo();
		String bankCardHolderName = sbiRequest.getCardHolderName();
		String bankCardHolderId = sbiRequest.getCardHolderId();
		String payPhone = sbiRequest.getPayPhone();
		
		RdAppuser otherRdAppuser = rdAppuserMapper.selectByIdCard(bankCardHolderId);
		if(otherRdAppuser != null) {
			if(userId.equals(otherRdAppuser.getIds()) == false) {
				logger.error("user " + userId + " id card no already bind with other userId = " + otherRdAppuser.getIds());
				returnResp.setResultCode(1);
				returnResp.setResultMsg("证件号已经绑定过其它账号");
				return returnResp;
			}
		}
		
		KQ_VerifyBankInfo vbi = new KQ_VerifyBankInfo();
		vbi.setBankInfo(new KQ_BankInfo());
		vbi.getBankInfo().setBankName(bankName);
		vbi.getBankInfo().setBankCardNo(bankCardNo);
		vbi.getBankInfo().setBankCardHolderName(bankCardHolderName);
		vbi.getBankInfo().setBankCardHolderId(bankCardHolderId);
		vbi.getBankInfo().setPayPhone(payPhone);
		vbi.setUserId(userId);
		vbi.setIdType("0");
		vbi.setExternalRefNo(userId);
		
		String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_VerifyBank";
		//String url = "http://localhost:13000" + "/payment/KQ_VerifyBank";
		String s = OrderManagementTask.sendRequest(url, vbi);
		logger.info("original response from TPS:\n" + s);
		
		KQ_VerifyBankResult vbr = JSON.parseObject(s, KQ_VerifyBankResult.class);
		if(vbr == null) {
			logger.error("verify bank info from KUAI_QIAN failed, cause no response from KUAI_QIAN!");
			returnResp.setResultCode(1);
        	returnResp.setResultMsg("绑定银行卡失败");
        	return returnResp;
		}
		
		String responseCode = vbr.getCode();
		if(responseCode == null || "00".equals(responseCode) == false) {
			if (responseCode == null || responseCode.trim().equals(""))
				responseCode = "未知代码";

			String responseMessage = vbr.getDetailMessage();
			if (responseMessage == null || responseMessage.equals(""))
				responseMessage = "未知原因";

			logger.error("绑定失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
			returnResp.setResultCode(2);
        	returnResp.setResultMsg("绑定失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
        	return returnResp;
		}
		
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankName(bankName);
		bankInfo.setBankCardNo(bankCardNo);
		bankInfo.setBankCardHolderName(bankCardHolderName);
		bankInfo.setBankCardHolderId(bankCardHolderId);
		bankInfo.setPayPhone(payPhone);
		BankInfoManager.instance().setBankInfo(userId, bankInfo);
		
		/*
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
        */
        
        String token = vbr.getToken();
        if(token != null && token.equals("") == false) {
			logger.info("token from KUAI_QIAN for user " + userId + " is " + token);
			rdAppuser.setToken(token);
			if(rdAppuserMapper.updateTokenByIds(rdAppuser) != 1) {
				logger.error("update token for user " + userId + " failed!");
				throw new RuntimeException("绑定银行卡失败");
			}
        }
        
        returnResp.setResultCode(Constants.succCode);
    	returnResp.setResultMsg(Constants.succMsg);
        return returnResp;
    }

}
