package com.expocms.server.response.types.impl;

public class AlSelling {
	
	private int sellType;
	private String productId;
	private String productName;
	private Long allMoney;
	private int alreadySell;
	private int predictYearEarnings;
	private int investmentTimeLimit;
	private String whetherSellOut;
	
	public int getSellType() {
		return sellType;
	}
	public void setSellType(int sellType) {
		this.sellType = sellType;
	}
	
	public int getAlreadySell() {
		return alreadySell;
	}
	public void setAlreadySell(int alreadySell) {
		this.alreadySell = alreadySell;
	}
	
	public int getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(int predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
	}
	
	public int getInvestmentTimeLimit() {
		return investmentTimeLimit;
	}
	public void setInvestmentTimeLimit(int investmentTimeLimit) {
		this.investmentTimeLimit = investmentTimeLimit;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		//this.productName = ToolUtils.convertProductIdToString(productName);
		this.productName = productName;
	}
	
	public Long getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Long allMoney) {
		this.allMoney = allMoney;
	}
	
	public String getWhetherSellOut() {
		return whetherSellOut;
	}
	public void setWhetherSellOut(String whetherSellOut) {
		this.whetherSellOut = whetherSellOut;
	}
	
}
