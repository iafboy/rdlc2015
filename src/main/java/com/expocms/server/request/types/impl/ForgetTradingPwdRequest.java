package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ForgetTradingPwdRequest implements IRequest {
	private String verifyCode;
    private String idCard;
    private String bankCard;
    private String userId;
    private String pwd;
    
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

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean validate() {
		if(verifyCode!=null&&idCard!=null&&bankCard!=null&&userId!=null&&!"".equals(verifyCode)&&!"".equals(idCard)&&!"".equals(bankCard)&&!"".equals(userId))
			return true;
		return false;
	}

}
