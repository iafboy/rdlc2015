package com.expocms.server.core.impl;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.ProductListRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public class PRODUCTLISTHandlerTest extends BaseTest{
	PRODUCTLISTHandler handler;
	@Test
	public void testHandleRequest() {
		handler=(PRODUCTLISTHandler) ac.getBean("PRODUCTLISTHandler");
		ProductListRequest request=new ProductListRequest();
		request.setPage(0);
		request.setPageCount(5);
		request.setProductType(RdProduct.PRODUCT_STATUS_STR_REPAIED);;
		BaseResponse response = handler.handleRequest(request);
		System.out.println(JSON.toJSONString(response));
	}

}
