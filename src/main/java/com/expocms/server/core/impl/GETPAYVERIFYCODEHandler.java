package com.expocms.server.core.impl;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdBankMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdBank;
import com.expocms.server.request.types.impl.GetPayVerifyCodeRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.GetPayVerifyCodeResponse;

import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.schedule.OrderManagementTask;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.util.BankInfo;
import com.expocms.server.util.BankInfoManager;
import com.payment.server.agent.command.params.KQ_BankInfo;
import com.payment.server.agent.command.params.KQ_GetVerifyCodeInfo;
import com.payment.server.agent.command.params.KQ_GetVerifyCodeResult;
import com.payment.server.agent.command.params.KQ_ResetBankInfo;
import com.payment.server.agent.command.params.KQ_ResetBankResult;

@Service("GETPAYVERIFYCODEHandler")
@Transactional
public class GETPAYVERIFYCODEHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(GETPAYVERIFYCODEHandler.class);

	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	//@Autowired
	//private RdBankMapper rdBankMapper;
	
	public GETPAYVERIFYCODEHandler() {
		super();
	}

	public BaseResponse handleRequest(IRequest request) {
		GetPayVerifyCodeResponse returnResp = new GetPayVerifyCodeResponse();
		GetPayVerifyCodeRequest gpvcRequest = (GetPayVerifyCodeRequest)request;
		
		String userId = gpvcRequest.getUserid();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		if(rdAppuser == null) {
			logger.error("user " + userId + " not exist!");
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		// bank info ...
		String bankName = null;
		String bankCardNo = null;
		String bankCardHolderName = null;
		String bankCardHolderId = null;
		String payPhone = null;
		
		// get flag of bank info modified by WEB or not ...
		boolean isBankModified = rdAppuser.isBankModified();
		if(isBankModified) {
			bankName = rdAppuser.getBankname();
			bankCardNo = rdAppuser.getBankcardno();
			bankCardHolderName = rdAppuser.getCardHolder();
			bankCardHolderId = rdAppuser.getIdcard();
			payPhone = rdAppuser.getPayPhone();
		} else {
			bankName = gpvcRequest.getBankName();
			bankCardNo = gpvcRequest.getCardNo();
			bankCardHolderName = gpvcRequest.getCardHolderName();
			bankCardHolderId = gpvcRequest.getCardHolderId();
			payPhone = gpvcRequest.getPayPhone();
			
			RdAppuser otherRdAppuser = rdAppuserMapper.selectByIdCard(bankCardHolderId);
			if(otherRdAppuser != null) {
				if(userId.equals(otherRdAppuser.getIds()) == false) {
					logger.error("user " + userId + " id card no already bind with other userId = " + otherRdAppuser.getIds());
					returnResp.setResultCode(1);
					returnResp.setResultMsg("证件号已经绑定过其它账号");
					return returnResp;
				}
			}
		}
		
		BankInfo bankInfo = null;
		
		//int numberOfOrder = rdOrderMapper.getOrderNumOfCurrentUser(userId);
		int numberOfOrder = rdAppuser.getPurchaseNum();
		if(numberOfOrder == 0) {
			if(bankName == null || bankName.equals("") ||
					bankCardNo == null || bankCardNo.equals("") ||
					bankCardHolderName == null || bankCardHolderName.equals("") ||
					bankCardHolderId == null || bankCardHolderId.equals("") ||
					payPhone == null || payPhone.equals("")) {
				returnResp.setResultCode(3);
				returnResp.setResultMsg("卡信息参数不全，无法申请支付验证码");
				return returnResp;
			}
			
			bankInfo = new BankInfo();
			bankInfo.setBankName(bankName);
			bankInfo.setBankCardNo(bankCardNo);
			bankInfo.setBankCardHolderName(bankCardHolderName);
			bankInfo.setBankCardHolderId(bankCardHolderId);
			bankInfo.setPayPhone(payPhone);
		} else {
			if(isBankModified) {
				bankInfo = new BankInfo();
				bankInfo.setBankName(bankName);
				bankInfo.setBankCardNo(bankCardNo);
				bankInfo.setBankCardHolderName(bankCardHolderName);
				bankInfo.setBankCardHolderId(bankCardHolderId);
				bankInfo.setPayPhone(payPhone);
				
				/**
				 * reset (previous) bank info ...
				 * this operation couldn't be done here, cause at this moment,
				 * the bank info already been freshed to newer bank,
				 * but the reset operation should be done at old bank,
				 * so normally should be done at WEB　side.
				 */
				//resetBankInfo(rdBankMapper, rdAppuser);
			} else if(bankName != null && bankName.equals("") == false &&
					bankCardNo != null && bankCardNo.equals("") == false &&
					bankCardHolderName != null && bankCardHolderName.equals("") == false &&
					bankCardHolderId != null && bankCardHolderId.equals("") == false &&
					payPhone != null && payPhone.equals("") == false) {
				
				if(bankName.equals(rdAppuser.getBankname()) == false ||
						bankCardNo.equals(rdAppuser.getBankcardno()) == false ||
						bankCardHolderName.equals(rdAppuser.getCardHolder()) == false ||
						bankCardHolderId.equals(rdAppuser.getIdcard()) == false ||
						payPhone.equals(rdAppuser.getPayPhone()) == false) {
					bankInfo = new BankInfo();
					bankInfo.setBankName(bankName);
					bankInfo.setBankCardNo(bankCardNo);
					bankInfo.setBankCardHolderName(bankCardHolderName);
					bankInfo.setBankCardHolderId(bankCardHolderId);
					bankInfo.setPayPhone(payPhone);
				}
				
			} else {
				bankName = "";
				bankCardNo = "";
				bankCardHolderName = "";
				bankCardHolderId = "";
				payPhone = "";
			}
		}
		
		String orderid = ToolUtils.getUuidByJdk(true);
		long originalAmount = gpvcRequest.getAmount() != null ? gpvcRequest.getAmount() : 0;
		
		/*
		GetDynTr2Entity gdte = new GetDynTr2Entity();
		gdte.setAmount(floatAmount);
		gdte.setBankName(bankName);
		gdte.setPan(bankCardNo);
		gdte.setCardHolderName(bankCardHolderName);
		gdte.setCardHolderId(bankCardHolderId);
		gdte.setPhoneNO(payPhone);
		gdte.setCustomerId(userId);
		gdte.setIdType("0");
		gdte.setUserId(userId);
		gdte.setExternalRefNumber(orderid);
		*/
		
		KQ_GetVerifyCodeInfo gci = new KQ_GetVerifyCodeInfo();
		gci.setAmount(originalAmount);
		gci.setCustomerId(userId);
		gci.setExternalRefNo(orderid);
		gci.setIdType("0");
		gci.setUserId(userId);
		gci.setBankInfo(new KQ_BankInfo());
		gci.getBankInfo().setBankName(bankName);
		gci.getBankInfo().setBankCardNo(bankCardNo);
		gci.getBankInfo().setBankCardHolderName(bankCardHolderName);
		gci.getBankInfo().setBankCardHolderId(bankCardHolderId);
		gci.getBankInfo().setPayPhone(payPhone);
		
		//Map<String, String> returnMap = null;
		KQ_GetVerifyCodeResult gcr = null;
		
		boolean isOK = false;
		Random r = new Random(System.currentTimeMillis());
		int n = Math.abs(r.nextInt()) % 100;
		if(n < Constants.RED_PACKAGE_IFX)
			isOK = true;
		if(isOK == false) {
			gcr = new KQ_GetVerifyCodeResult();
		} else {
			String testCode = ((GetPayVerifyCodeRequest)request).getTestCode();
			if(Constants.TEST_IF_ENABLED && testCode != null && testCode.equals(GetPayVerifyCodeRequest.TEST_CODE)) {
				/*
				returnMap = new HashMap<String, String>();
				returnMap.put("token", ToolUtils.getUuidByJdk(true));
				returnMap.put("responseCode", "00");
				*/
				
				gcr = new KQ_GetVerifyCodeResult();
				gcr.setCode(Constants.KQ_CODE_TICKET_SUCCESS);
				gcr.setToken(ToolUtils.getUuidByJdk(true));
			} else {
				/*
				rmiServiceInvoker = new RMIServiceInvoker();
				returnMap = rmiServiceInvoker.getDynTr2(gdte);
				if (returnMap == null || returnMap.size() == 0) {
					logger.error("no correct response back from KUAI_QIAN for user " + userId);
					returnResp.setResultCode(2);
					returnResp.setResultMsg("无法得到快钱返回结果信息");
					return returnResp;
				}
				*/
				
				String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_GetVerifyCode";
				String s = OrderManagementTask.sendRequest(url, gci);
				logger.info("original response from TPS:\n" + s);
				
				gcr = JSON.parseObject(s, KQ_GetVerifyCodeResult.class);
				if(gcr == null) {
					logger.error("no correct response back from KUAI_QIAN for user " + userId);
					returnResp.setResultCode(2);
					returnResp.setResultMsg("无法得到快钱返回结果信息");
					return returnResp;
				}
			}
		}
		
		//String responseCode = returnMap.get("responseCode");
		String responseCode = gcr.getCode();
		if(responseCode != null && "00".equals(responseCode.trim())) {
			//String token = returnMap.get("token");
			String token = gcr.getToken();
			
			if(token != null && token.equals("") == false) {
				logger.info("token from KUAI_QIAN for user " + userId + " is " + token);
				
				rdAppuser.setToken(token);
				final int result = rdAppuserMapper.updateTokenByIds(rdAppuser);
				if(result != 1) {
					logger.error("update token and possibly bank info for user " + userId + " failed!");
					returnResp.setResultCode(2);
					returnResp.setResultMsg("数据库更新失败");
					return returnResp;
				}

				if(bankInfo != null) {
					BankInfoManager.instance().setBankInfo(userId, bankInfo);
				}
				
				returnResp.setOrderId(orderid);
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
				return returnResp;
			}
		}
		
		if(responseCode == null || responseCode.trim().equals(""))
			responseCode = "未知代码";
			
		//String responseMessage = returnMap.get("responseTextMessage");
		String responseMessage = gcr.getDetailMessage();
		if(responseMessage == null || responseMessage.equals(""))
			responseMessage = "未知原因";
			
		logger.error("user " + userId + ", 申请失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
		throw new RuntimeException("申请失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
	}
	
	public static boolean resetBankInfo(final RdBankMapper rdBankMapper, final RdAppuser rdAppuser) {
		if(rdBankMapper == null)
			return false;
		if(rdAppuser == null)
			return false;
		
		RdBank rdBank = rdBankMapper.selectByBankName(rdAppuser.getBankname());
		if(rdBank == null)
			return false;
		
		KQ_ResetBankInfo KQ_rbi = new KQ_ResetBankInfo();
		KQ_rbi.setBankInfo(new KQ_BankInfo());
		KQ_rbi.setUserId(rdAppuser.getIds());
		//KQ_gvci.setExternalRefNo("15aa7e676e784a9fa0c9247790f8c890");
		KQ_rbi.setCustomerId(rdAppuser.getIds());
		//KQ_gvci.setAmount("1.00");
		KQ_rbi.setIdType("0");
		KQ_rbi.getBankInfo().setBankName(rdAppuser.getBankname());
		KQ_rbi.getBankInfo().setBankCardNo(rdAppuser.getBankcardno());
		KQ_rbi.getBankInfo().setBankCardHolderName(rdAppuser.getCardHolder());
		KQ_rbi.getBankInfo().setBankCardHolderId(rdAppuser.getIdcard());
		KQ_rbi.getBankInfo().setPayPhone(rdAppuser.getPayPhone());
		KQ_rbi.setBankId(rdBank.getBriefName());
		
		String url = "http://localhost:" + 13000 + "/payment/KQ_ResetBank";
		String resp = OrderManagementTask.sendRequest(url, KQ_rbi);
		System.out.println("resetBankInfo result : " + resp);
		logger.info("resetBankInfo result : " + resp);
		
		KQ_ResetBankResult KQ_rbr = null;
		try {
			KQ_rbr = (KQ_ResetBankResult) JSON.parseObject(resp, KQ_ResetBankResult.class);
		} catch (Exception e) {
			logger.info("resetBankInfo result : Exception occurred while parse result! " + e);
		}
		
		return KQ_rbr.getCode() != null && "00".equals(KQ_rbr.getCode());
	}
}
