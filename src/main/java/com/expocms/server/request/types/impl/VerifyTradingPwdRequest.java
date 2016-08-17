package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class VerifyTradingPwdRequest implements IRequest {
	private String userId;
	private String pwd;

	public boolean validate() {
		if(userId!=null&&!"".equals(userId)&&pwd!=null&&!"".equals(pwd))
			return true;
		return false;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
