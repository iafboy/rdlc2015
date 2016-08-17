package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ManageMoneyProductRequest implements IRequest {
	
	private String userId;
	
	public boolean validate() {
		return true;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
