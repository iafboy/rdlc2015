package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.RedPackageRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class REDPACKAGEHandlerTest extends BaseTest{
	REDPACKAGEHandler handler;
	@Test
	public void testHandleRequest() {
		 handler=(REDPACKAGEHandler) ac.getBean("REDPACKAGEHandler");
		RedPackageRequest request=new RedPackageRequest();
		request.setUserId("27184beee1c24de89bc45e45737ec6b3");
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
