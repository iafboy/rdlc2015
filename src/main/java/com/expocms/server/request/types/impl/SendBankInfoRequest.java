package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class SendBankInfoRequest implements IRequest {
	
	private String userId;
	private String bankName;
	private String cardNo;
	private String cardHolderName;
	private String cardHolderId;
	private String payPhone;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCardHolderId() {
		return cardHolderId;
	}
	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}
	
	public String getPayPhone() {
		return payPhone;
	}
	public void setPayPhone(String payPhone) {
		this.payPhone = payPhone;
	}
	
	public boolean validate() {
		if(userId == null || userId.equals(""))
			return false;
		if(bankName == null || bankName.equals(""))
			return false;
		if(cardNo == null || cardNo.equals(""))
			return false;
		if(cardHolderName == null || cardHolderName.equals(""))
			return false;
		if(cardHolderId == null || cardHolderId.equals(""))
			return false;
		if(payPhone == null || payPhone.equals(""))
			return false;
		
		return true;
	}
	
}
