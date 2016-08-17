package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.request.types.impl.ProductDetailsRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class PRODUCTDETAILSHandlerTest extends BaseTest{
	PRODUCTDETAILSHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(PRODUCTDETAILSHandler) ac.getBean("PRODUCTDETAISLHandler");
		ProductDetailsRequest request=new ProductDetailsRequest();
		request.setProductId("5294618b5b774bda8df7d3d79ed8bb7a");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
