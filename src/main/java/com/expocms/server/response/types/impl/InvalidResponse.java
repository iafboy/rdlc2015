package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class InvalidResponse extends BaseResponse {

	public InvalidResponse(int userNotfind, String msg) {
		this.setResultCode(userNotfind);
		this.setResultMsg(msg);
	}

	public InvalidResponse() {
		
	}
	
}
