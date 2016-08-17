package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class SetTradingPwdBoolRequest implements IRequest {
	private String userId;
	public boolean validate() {
		if(userId!=null&&!"".equals(userId))
			return true;
		return false;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
