package com.expocms.server.core.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.AuthCodeCacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.SendRegistVerifyCodeRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.util.RandomSecurityCode;

@Service("SENDREGISTVERIFYCODEHandler")
@Transactional
public class SENDREGISTVERIFYCODEHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(SENDVERIFYCODEHandler.class);

	@Override
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		String phone = ((SendRegistVerifyCodeRequest)request).getPhoneNo();
		if(phone == null || phone.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("手机号不能为空");
			return returnResp;
		}
		
		try {
			//String msg = String.copyValueOf(randomCode.getSecurityCode(6, SecurityCodeLevel.Hard,true));
			String msg = RandomSecurityCode.getSix();
			AuthCodeCacheManager.getInstance().putAuthCodeInCache(phone, msg);
			
			String smsContent = "" + msg + "（融道验证码），有效期5分钟，千万不要告诉别人哦！你正在使用融道登录。【融道理财】";
			QUICKPAYMENTHandler.sendSMS_via_PINGTAI(phone, smsContent);
			logger.debug("Successful send message to " + phone);
			returnResp.setResultCode(Constants.succCode);
			returnResp.setResultMsg(Constants.succMsg);
			
//			RMIServiceInvoker smsServiceInvoker = new RMIServiceInvoker();
//			SMSMsgEntity smsMsgEntity = new SMSMsgEntity();
//			smsMsgEntity.setMsisdn(phone);
//			smsMsgEntity.setMessage("" + msg + "（融道验证码），有效期5分钟，千万不要告诉别人哦！你正在使用融道登录。【融道理财】");
//			
//			if (smsServiceInvoker.SendSMS(1, smsMsgEntity)) {
//				logger.debug("Successful send message to " + phone);
//				returnResp.setResultCode(Constants.succCode);
//				returnResp.setResultMsg(Constants.succMsg);
//			} else {
//				logger.debug("Fail to send message to " + phone);
//				returnResp.setResultCode(1);
//				returnResp.setResultMsg("发送提示短信失败");
//			}
		} catch (Exception ex) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("发送验证码失败");
			logger.error(ex.getMessage(), ex);
		}
		
		return returnResp;
	}

}
