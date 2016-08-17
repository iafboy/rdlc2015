package com.expocms.server.response.types.impl;

public class CommissionRecord {
	
	private Long investAmount;
	private Integer investDuration;
	private String investorPhone;
	private String investTime;
	private Long commission;
	
	public Long getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(Long investAmount) {
		this.investAmount = investAmount;
	}
	
	public Integer getInvestDuration() {
		return investDuration;
	}
	public void setInvestDuration(Integer investDuration) {
		this.investDuration = investDuration;
	}
	
	public String getInvestorPhone() {
		return investorPhone;
	}
	public void setInvestorPhone(String investorPhone) {
		this.investorPhone = investorPhone;
	}
	
	public String getInvestTime() {
		return investTime;
	}
	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}
	
	public Long getCommission() {
		return commission;
	}
	public void setCommission(Long commission) {
		this.commission = commission;
	}

}
