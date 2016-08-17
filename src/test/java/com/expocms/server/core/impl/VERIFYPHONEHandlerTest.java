package com.expocms.server.core.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class VERIFYPHONEHandlerTest extends BaseTest{
	VERIFYPHONEHandler handler;
	@Test
	public void testHandleRequest() {
		 handler=(VERIFYPHONEHandler) ac.getBean("VERIFYPHONEHandler");
		VerifyPhoneRequest request=new VerifyPhoneRequest();
		request.setPhoneNo("12345678");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
