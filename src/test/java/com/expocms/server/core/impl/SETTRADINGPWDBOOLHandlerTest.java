package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.SetTradingPwdBoolRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class SETTRADINGPWDBOOLHandlerTest  extends BaseTest{
	SETTRADINGPWDBOOLHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(SETTRADINGPWDBOOLHandler) ac.getBean("SETTRADINGPWDBOOLHandler");
		SetTradingPwdBoolRequest request=new SetTradingPwdBoolRequest();
		request.setUserId("ecc4dcba81fa4c7a90ab090947329054");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
