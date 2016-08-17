package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ForgetLoginPwdRequest implements IRequest {
	private String verifyCode;
	private String idCard;
	private String mobile;
	private String resetPwd;

	public boolean validate() {
		if (verifyCode!=null
				&&!"".equals(verifyCode.trim()))
			return true;
		return false;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(String resetPwd) {
		this.resetPwd = resetPwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
