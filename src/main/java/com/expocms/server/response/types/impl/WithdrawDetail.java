package com.expocms.server.response.types.impl;

public class WithdrawDetail {
	
	private String withdrawTime = null;
	private String arrivalTime = null;
	private Long withdrawAmount = null;
	private String withdrawStatus = null;
	private Long poundageAmount = null;
	
	public String getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Long getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Long withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	public String getWithdrawStatus() {
		return withdrawStatus;
	}
	public void setWithdrawStatus(String withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	
	public Long getPoundageAmount() {
		return poundageAmount;
	}
	public void setPoundageAmount(Long poundageAmount) {
		this.poundageAmount = poundageAmount;
	}

}
