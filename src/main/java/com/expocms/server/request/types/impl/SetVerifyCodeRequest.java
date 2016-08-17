package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class SetVerifyCodeRequest implements IRequest {
	private String phoneNo;
	private String verifyCode;
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean validate() {
		if(phoneNo == null || phoneNo.equals(""))
			return false;
		
		if(verifyCode == null || verifyCode.equals(""))
			return false;
		
		return true;
	}

}
