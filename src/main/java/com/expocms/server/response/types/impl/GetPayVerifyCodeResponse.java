package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class GetPayVerifyCodeResponse extends BaseResponse {
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
