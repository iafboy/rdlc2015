package com.payment.server.agent.command.params;

public class KQ_QueryRepaymentDealInfo {
	
	private String dealBeginDate_ = null;
	private String dealEndDate_ = null;
	private String amount_ = null;
	private String dealFee_ = null;
    private String dealId_ = null;
    private String dealStatus_ = null;
    
    private String errorCode_ = null;
    private String errorMessage_ = null;
    private String bankErrorCode_ = null;
    private String bankErrorMessage_ = null;
    
	public String getDealBeginDate() {
		return dealBeginDate_;
	}
	public void setDealBeginDate(String dealBeginDate) {
		this.dealBeginDate_ = dealBeginDate;
	}
	
	public String getDealEndDate() {
		return dealEndDate_;
	}
	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate_ = dealEndDate;
	}
	
	public String getAmount() {
		return amount_;
	}
	public void setAmount(String amount) {
		this.amount_ = amount;
	}
	
	public String getDealFee() {
		return dealFee_;
	}
	public void setDealFee(String dealFee) {
		this.dealFee_ = dealFee;
	}
	
	public String getDealId() {
		return dealId_;
	}
	public void setDealId(String dealId) {
		this.dealId_ = dealId;
	}
	
	public String getDealStatus() {
		return dealStatus_;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus_ = dealStatus;
	}
	
	public String getErrorCode() {
		return errorCode_;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode_ = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage_;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage_ = errorMessage;
	}
	
	public String getBankErrorCode() {
		return bankErrorCode_;
	}
	public void setBankErrorCode(String bankErrorCode) {
		this.bankErrorCode_ = bankErrorCode;
	}
	
	public String getBankErrorMessage() {
		return bankErrorMessage_;
	}
	public void setBankErrorMessage(String bankErrorMessage) {
		this.bankErrorMessage_ = bankErrorMessage;
	}
	
}
