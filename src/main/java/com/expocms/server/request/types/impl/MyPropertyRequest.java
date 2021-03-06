package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class MyPropertyRequest implements IRequest {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean validate() {
		if (userId != null && !"".equals(userId.trim()))
			return true;
		return false;
	}

}
