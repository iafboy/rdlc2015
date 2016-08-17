package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class BorrowOrderResponse extends BaseResponse {
	
	private String loanNumber;
	private String startDate;
	private String investType;
	private Long applyMoney;
	private Long realityMoney;
	private Long surplusMoney;
	private Long predictEarnings;
	private String borrowingNumber;
	private String name;
	private String idCard;
	private String identity;
	private Long lendMoney;
	private String purpose;
	private String startRepayment;
	private String briefContent;
	
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	
	public Long getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(Long applyMoney) {
		this.applyMoney = applyMoney;
	}
	
	public Long getRealityMoney() {
		return realityMoney;
	}
	public void setRealityMoney(Long realityMoney) {
		this.realityMoney = realityMoney;
	}
	
	public Long getSurplusMoney() {
		return surplusMoney;
	}
	public void setSurplusMoney(Long surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
	
	public Long getPredictEarnings() {
		return predictEarnings;
	}
	public void setPredictEarnings(Long predictEarnings) {
		this.predictEarnings = predictEarnings;
	}
	
	public String getBorrowingNumber() {
		return borrowingNumber;
	}
	public void setBorrowingNumber(String borrowingNumber) {
		this.borrowingNumber = borrowingNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public Long getLendMoney() {
		return lendMoney;
	}
	public void setLendMoney(Long lendMoney) {
		this.lendMoney = lendMoney;
	}
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getStartRepayment() {
		return startRepayment;
	}
	public void setStartRepayment(String startRepayment) {
		this.startRepayment = startRepayment;
	}
	
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getBriefContent() {
		return briefContent;
	}
	public void setBriefContent(String briefContent) {
		this.briefContent = briefContent;
	}
	
}
