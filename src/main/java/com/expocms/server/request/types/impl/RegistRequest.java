package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class RegistRequest implements IRequest {
	private String phoneNo;
	private String password;
	private String VerifyCode;
	private String recommendCode;
	
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

	public String getVerifyCode() {
		return VerifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		VerifyCode = verifyCode;
	}
	
	public String getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}

	public boolean validate() {
		if(phoneNo!=null&&!"".equals(phoneNo)&&password!=null&&!"".equals(password))
			return true;
		return false;
	}

}
