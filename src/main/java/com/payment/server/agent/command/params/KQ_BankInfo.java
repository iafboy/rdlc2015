package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_BankInfo extends JSonObject {

	// basic info ...
	private String bankName_ = null;
	private String bankCardNo_ = null;
	private String bankCardHolderName_ = null;
	private String bankCardHolderId_ = null;
	private String payPhone_ = null;
	// extension info ...
	private String bankBranchName_ = null;
	private String bankProvince_ = null;
	private String bankCity_ = null;

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
	
	public String getBankBranchName() {
		return bankBranchName_;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName_ = bankBranchName;
	}
	
	public String getBankProvince() {
		return bankProvince_;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince_ = bankProvince;
	}
	
	public String getBankCity() {
		return bankCity_;
	}
	public void setBankCity(String bankCity) {
		this.bankCity_ = bankCity;
	}
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
