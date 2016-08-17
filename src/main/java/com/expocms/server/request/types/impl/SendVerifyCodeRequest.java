package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class SendVerifyCodeRequest implements IRequest {
	
	private String phoneNo;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean validate() {
		if(phoneNo!=null&&!"".equals(phoneNo))
			return true;
		return false;
	}

}
