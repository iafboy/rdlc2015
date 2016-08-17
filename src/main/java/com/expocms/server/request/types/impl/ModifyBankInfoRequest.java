package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ModifyBankInfoRequest implements IRequest {
	private String userId;
	private String bankCard;
	private String bankName;
	private String bankAddr;
	private String bankCity;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddr() {
		return bankAddr;
	}

	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public boolean validate() {
		if(userId!=null&&!"".equals(userId)&&bankCard!=null&&!"".equals(bankCard))
			return true;
		return false;
	}

}
