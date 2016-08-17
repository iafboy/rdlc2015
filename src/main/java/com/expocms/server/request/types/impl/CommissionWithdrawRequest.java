package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class CommissionWithdrawRequest implements IRequest {
	
	private String userId;
	private Long amount;
	private Long poundageAmount;
	private Long arrivalAmount;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getPoundageAmount() {
		return poundageAmount;
	}
	public void setPoundageAmount(Long poundageAmount) {
		this.poundageAmount = poundageAmount;
	}
	
	public Long getArrivalAmount() {
		return arrivalAmount;
	}
	public void setArrivalAmount(Long arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}
	
	public boolean validate() {
		if(this.userId == null || this.userId.equals(""))
			return false;
		if(this.amount == null)
			return false;
		if(this.poundageAmount == null)
			return false;
		if(this.arrivalAmount == null)
			return false;
		return true;
	}

}
