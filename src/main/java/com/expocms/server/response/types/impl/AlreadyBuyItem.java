package com.expocms.server.response.types.impl;

import java.io.Serializable;
import java.util.Date;

public class AlreadyBuyItem implements Serializable{

	private static final long serialVersionUID = -8809306643332979752L;
	
	private String orderId;
	private String productName;
	private String productId;
	private Long currentPoperty;
	private Long allEarnings;
	private Long principal;
	private Long predictEarnings;
	private String valueDate;
	private String expireDate;
	private String predictGetTime;
	private String buyTime;
	private Date valueDateDB;
	private Date expireDateDB;
	private Date predictGetTimeDB;
	private Date buyTimeDB;
	private Integer predictYearEarnings;
	private String bank;
	private String bankName;
	private String bankCard;
	private String numccieURL;
	private String protocolUrl;
	private Integer orderStatus;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Long getCurrentPoperty() {
		return currentPoperty;
	}
	public void setCurrentPoperty(Long currentPoperty) {
		this.currentPoperty = currentPoperty;
	}
	
	public Long getAllEarnings() {
		return allEarnings;
	}
	public void setAllEarnings(Long allEarnings) {
		this.allEarnings = allEarnings;
	}
	
	public Long getPrincipal() {
		return principal;
	}
	public void setPrincipal(Long principal) {
		this.principal = principal;
	}
	
	public Long getPredictEarnings() {
		return predictEarnings;
	}
	public void setPredictEarnings(Long predictEarnings) {
		this.predictEarnings = predictEarnings;
	}
	
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getPredictGetTime() {
		return predictGetTime;
	}
	public void setPredictGetTime(String predictGetTime) {
		this.predictGetTime = predictGetTime;
	}
	
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	
	public Date getValueDateDB() {
		return valueDateDB;
	}
	public void setValueDateDB(Date valueDateDB) {
		this.valueDateDB = valueDateDB;
	}
	
	public Date getExpireDateDB() {
		return expireDateDB;
	}
	public void setExpireDateDB(Date expireDateDB) {
		this.expireDateDB = expireDateDB;
	}
	
	public Date getPredictGetTimeDB() {
		return predictGetTimeDB;
	}
	public void setPredictGetTimeDB(Date predictGetTimeDB) {
		this.predictGetTimeDB = predictGetTimeDB;
	}
	
	public Date getBuyTimeDB() {
		return buyTimeDB;
	}
	public void setBuyTimeDB(Date buyTimeDB) {
		this.buyTimeDB = buyTimeDB;
	}
	
	public Integer getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(Integer predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getProtocolUrl() {
		return protocolUrl;
	}
	public void setProtocolUrl(String protocolUrl) {
		this.protocolUrl = protocolUrl;
	}
	
	public String getNumccieURL() {
		return numccieURL;
	}
	public void setNumccieURL(String numccieURL) {
		this.numccieURL = numccieURL;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
