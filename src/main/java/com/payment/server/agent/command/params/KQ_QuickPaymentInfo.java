package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_QuickPaymentInfo extends JSonObject {
	
	private KQ_BankInfo bankInfo_ = null;
	private String storableCardNo_ = null;
	private String expiredDate_ = null;
	private String cvv2_ = null;
	private Long amount_ = null;
	private String externalRefNo_ = null;
	private String userId_ = null;
	private String spFlag_ = null;
	private String idType_ = null;
	private String savePciFlag_ = null;
	private String payBatch_ = null;
	private String validCode_ = null;
	private String token_ = null;
	
	public KQ_BankInfo getBankInfo() {
		return bankInfo_;
	}
	public void setBankInfo(KQ_BankInfo bankInfo) {
		this.bankInfo_ = bankInfo;
	}
	
	public String getStorableCardNo() {
		return storableCardNo_;
	}
	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo_ = storableCardNo;
	}
	
	public String getExpiredDate() {
		return expiredDate_;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate_ = expiredDate;
	}
	
	public String getCvv2() {
		return cvv2_;
	}
	public void setCvv2(String cvv2) {
		this.cvv2_ = cvv2;
	}
	
	public Long getAmount() {
		return amount_;
	}
	public void setAmount(Long amount) {
		this.amount_ = amount;
	}
	
	public String getExternalRefNo() {
		return externalRefNo_;
	}
	public void setExternalRefNo(String externalRefNo) {
		this.externalRefNo_ = externalRefNo;
	}
	
	public String getUserId() {
		return userId_;
	}
	public void setUserId(String userId) {
		this.userId_ = userId;
	}
	
	public String getSpFlag() {
		return spFlag_;
	}
	public void setSpFlag(String spFlag) {
		this.spFlag_ = spFlag;
	}
	
	public String getIdType() {
		return idType_;
	}
	public void setIdType(String idType) {
		this.idType_ = idType;
	}
	
	public String getSavePciFlag() {
		return savePciFlag_;
	}
	public void setSavePciFlag(String savePciFlag) {
		this.savePciFlag_ = savePciFlag;
	}
	
	public String getPayBatch() {
		return payBatch_;
	}
	public void setPayBatch(String payBatch) {
		this.payBatch_ = payBatch;
	}
	
	public String getValidCode() {
		return validCode_;
	}
	public void setValidCode(String validCode) {
		this.validCode_ = validCode;
	}
	
	public String getToken() {
		return token_;
	}
	public void setToken(String token) {
		this.token_ = token;
	}
	
	@Override
	public boolean validate() {
		if(this.amount_ == null)
			return false;
		/*
		if(this.bankInfo_ == null)
			return false;
		if(this.bankInfo_.getBankCardNo() == null || this.bankInfo_.getBankCardNo().equals(""))
			return false;
		if(this.storableCardNo_ == null || this.storableCardNo_.equals(""))
			return false;
		if(this.expiredDate_ == null || this.expiredDate_.equals(""))
			return false;
		*/
		return true;
	}

}
