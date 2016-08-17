package com.expocms.server.core.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class RECOMMENDHandlerTest extends BaseTest{
	RECOMMENDHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(RECOMMENDHandler) ac.getBean("RECOMMENDHandler");
		RecommendRequest request=new RecommendRequest();
//		request.setUserId("ssss");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
