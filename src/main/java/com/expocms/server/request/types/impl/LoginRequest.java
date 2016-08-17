package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class LoginRequest implements IRequest {
	
	private String phoneNo;
	private String password;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validate() {
		if(phoneNo!=null&&!"".equals(phoneNo)&&password!=null&&!"".equals(password))
			return true;
		return false;
	}


}
