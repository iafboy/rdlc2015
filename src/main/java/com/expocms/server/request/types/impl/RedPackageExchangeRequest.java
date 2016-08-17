package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class RedPackageExchangeRequest implements IRequest {
	
	private String userId;
	private String redPackage;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRedPackage() {
		return redPackage;
	}
	public void setRedPackage(String redPackage) {
		this.redPackage = redPackage;
	}

	public boolean validate() {
		if(userId != null && !"".equals(userId) &&
				redPackage != null && !"".equals(redPackage))
			return true;
		return false;
	}

}
