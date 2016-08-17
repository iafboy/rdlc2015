package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_BatchRepaymentDealInfo extends JSonObject {

	private String dealId_ = null;
	private Long amount_ = null;
	private String phone_ = null;
	private String note_ = null;
	private KQ_BankInfo bankInfo_ = null;

	public String getDealId() {
		return dealId_;
	}
	public void setDealId(String dealId) {
		this.dealId_ = dealId;
	}

	public Long getAmount() {
		return amount_;
	}
	public void setAmount(Long amount) {
		this.amount_ = amount;
	}
	
	public String getPhone() {
		return phone_;
	}
	public void setPhone(String phone) {
		this.phone_ = phone;
	}
	
	public String getNote() {
		return note_;
	}
	public void setNote(String note) {
		this.note_ = note;
	}
	
	public KQ_BankInfo getBankInfo() {
		return bankInfo_;
	}
	public void setBankInfo(KQ_BankInfo bankInfo) {
		this.bankInfo_ = bankInfo;
	}
	
	@Override
	public boolean validate() {
		if(this.amount_ == null)
			return false;
		if(this.dealId_ == null || this.dealId_.equals(""))
			return false;
		if(this.bankInfo_ == null)
			return false;
		return true;
	}

}
