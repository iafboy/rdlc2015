package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.ManageMoneyProductRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class MANAGEMONEYPRODUCTHandlerTest extends BaseTest{
	MANAGEMONEYPRODUCTHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(MANAGEMONEYPRODUCTHandler) ac.getBean("MANAGEMONEYPRODUCTHandler");
		ManageMoneyProductRequest request=new ManageMoneyProductRequest();
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
