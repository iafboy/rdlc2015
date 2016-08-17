package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.CurrentPeriodBuyRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class CURRENTPERIODBUYHandlerTest  extends BaseTest{
	CURRENTPERIODBUYHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(CURRENTPERIODBUYHandler) ac.getBean("CURRENTPERIODBUYHandler");
		CurrentPeriodBuyRequest request=new CurrentPeriodBuyRequest();
		request.setProductId("1a535883460e44cd88b12a2f6ca894a8");
		request.setPage(0);
		request.setPageCount(5);
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
