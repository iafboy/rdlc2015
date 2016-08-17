package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class VerifyPhoneResponse extends BaseResponse {
	
	private String registProtocolURL;
	private Integer orderCount;
	
	public String getRegistProtocolURL() {
		return registProtocolURL;
	}
	public void setRegistProtocolURL(String registProtocolURL) {
		this.registProtocolURL = registProtocolURL;
	}
	
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

}
