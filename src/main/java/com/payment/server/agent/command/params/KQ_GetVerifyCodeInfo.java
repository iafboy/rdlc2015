package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_GetVerifyCodeInfo extends JSonObject {
	
	private KQ_BankInfo bankInfo_ = null;
	private Long amount_ = null;
	private String customerId_ = null;
	private String idType_ = null;
	private String userId_ = null;
	private String externalRefNo_ = null;

	public KQ_BankInfo getBankInfo() {
		return bankInfo_;
	}
	public void setBankInfo(KQ_BankInfo bankInfo) {
		this.bankInfo_ = bankInfo;
	}
	
	public Long getAmount() {
		return amount_;
	}
	public void setAmount(Long amount) {
		this.amount_ = amount;
	}
	
	public String getCustomerId() {
		return customerId_;
	}
	public void setCustomerId(String customerId) {
		this.customerId_ = customerId;
	}
	
	public String getIdType() {
		return idType_;
	}
	public void setIdType(String idType) {
		this.idType_ = idType;
	}
	
	public String getUserId() {
		return userId_;
	}
	public void setUserId(String userId) {
		this.userId_ = userId;
	}
	
	public String getExternalRefNo() {
		return externalRefNo_;
	}
	public void setExternalRefNo(String externalRefNo) {
		this.externalRefNo_ = externalRefNo;
	}
	
	@Override
	public boolean validate() {
		if(this.bankInfo_ == null)
			return false;
		if(this.customerId_ == null || this.customerId_.equals(""))
			return false;
		if(this.externalRefNo_ == null || this.externalRefNo_.equals(""))
			return false;
		if(this.amount_ == null)
			return false;
		/*
		if(this.bankInfo_.getBankCardHolderName() == null || this.bankInfo_.getBankCardHolderName().equals(""))
			return false;
		if(this.idType_ == null || this.idType_.equals(""))
			return false;
		if(this.bankInfo_.getBankCardHolderId() == null || this.bankInfo_.getBankCardHolderId().equals(""))
			return false;
		if(this.bankInfo_.getBankCardNo() == null || this.bankInfo_.getBankCardNo().equals(""))
			return false;
		if(this.bankInfo_.getPayPhone() == null || this.bankInfo_.getPayPhone().equals(""))
			return false;
		*/
		return true;
	}

}
