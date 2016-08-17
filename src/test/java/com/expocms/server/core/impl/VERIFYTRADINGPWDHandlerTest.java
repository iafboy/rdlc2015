package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import com.expocms.server.request.types.impl.VerifyTradingPwdRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class VERIFYTRADINGPWDHandlerTest extends BaseTest{
	VERIFYTRADINGPWDHandler handler;
	@Test
	public void testHandleRequest() {
		 handler=(VERIFYTRADINGPWDHandler) ac.getBean("VERIFYTRADINGPWDHandler");
		VerifyTradingPwdRequest request=new VerifyTradingPwdRequest();
		request.setPwd("1234567");
		request.setUserId("27184beee1c24de89bc45e45737ec6b3");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
