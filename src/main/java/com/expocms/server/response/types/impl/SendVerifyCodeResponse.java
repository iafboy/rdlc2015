package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class SendVerifyCodeResponse extends BaseResponse {
	private String verifyCode;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
}