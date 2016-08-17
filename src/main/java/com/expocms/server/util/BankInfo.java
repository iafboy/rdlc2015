package com.expocms.server.util;

public class BankInfo {
	
	private String bankName_ = null;
	private String bankCardNo_ = null;
	private String bankCardHolderName_ = null;
	private String bankCardHolderId_ = null;
	private String payPhone_ = null;

	public String getBankName() {
		return bankName_;
	}
	public void setBankName(String bankName) {
		this.bankName_ = bankName;
	}
	
	public String getBankCardNo() {
		return bankCardNo_;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo_ = bankCardNo;
	}
	
	public String getBankCardHolderName() {
		return bankCardHolderName_;
	}
	public void setBankCardHolderName(String bankCardHolderName) {
		this.bankCardHolderName_ = bankCardHolderName;
	}
	
	public String getBankCardHolderId() {
		return bankCardHolderId_;
	}
	public void setBankCardHolderId(String bankCardHolderId) {
		this.bankCardHolderId_ = bankCardHolderId;
	}
	
	public String getPayPhone() {
		return payPhone_;
	}
	public void setPayPhone(String payPhone) {
		this.payPhone_ = payPhone;
	}

}
