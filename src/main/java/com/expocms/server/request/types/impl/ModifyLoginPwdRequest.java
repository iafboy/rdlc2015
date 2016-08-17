package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ModifyLoginPwdRequest implements IRequest {
	private String userId;
	private String oldPwd;
	private String newPwd;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public boolean validate() {
		if(userId!=null)
			return true;
		return false;
	}

}
