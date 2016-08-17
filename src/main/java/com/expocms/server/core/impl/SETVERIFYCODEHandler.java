package com.expocms.server.core.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.AuthCodeCacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("SETVERIFYCODEHandler")
@Transactional
public class SETVERIFYCODEHandler extends AbsBaseHandler {
	
	@Override
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		if(Constants.TEST_IF_ENABLED) {
			String phone = ((SetVerifyCodeRequest)request).getPhoneNo();
			String verifyCode = ((SetVerifyCodeRequest)request).getVerifyCode();
			
			AuthCodeCacheManager.getInstance().putAuthCodeInCache(phone, verifyCode);
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
