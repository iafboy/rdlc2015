package com.expocms.server.response.types.impl;

import java.util.List;

public class RecommandProduct {
	
	private String proId;
	private String productName;
	private String whetherSellOut;
	private long	allMoney;
	private long alreadySell;
	private long maySellMonty;
	private int predictYearEarnings;
	private int investmentTimeLimit;
	private long lowMoney;
	private long highMoney;
	private int buyNumber;
	private String startDate;
	private String endDate;
	private int poundage;
	private int currentBuyUserNumber;
	//private List<CurrentBuyUser> currentBuyUser;
	private String safeguard;
	private String briefContent;
	
	private List<SupportBank> supportBank;
	
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getWhetherSellOut() {
		return whetherSellOut;
	}
	public void setWhetherSellOut(String whetherSellOut) {
		this.whetherSellOut = whetherSellOut;
	}
	
	public long getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(long allMoney) {
		this.allMoney = allMoney;
	}
	
	public long getAlreadySell() {
		return alreadySell;
	}
	public void setAlreadySell(long alreadySell) {
		this.alreadySell = alreadySell;
	}
	
	public long getMaySellMonty() {
		return maySellMonty;
	}
	public void setMaySellMonty(long maySellMonty) {
		this.maySellMonty = maySellMonty;
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
	
	public long getLowMoney() {
		return lowMoney;
	}
	public void setLowMoney(long lowMoney) {
		this.lowMoney = lowMoney;
	}
	
	public long getHighMoney() {
		return highMoney;
	}
	public void setHighMoney(long highMoney) {
		this.highMoney = highMoney;
	}
	
	public int getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(int buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getPoundage() {
		return poundage;
	}
	public void setPoundage(int poundage) {
		this.poundage = poundage;
	}
	
	public int getCurrentBuyUserNumber() {
		return currentBuyUserNumber;
	}
	public void setCurrentBuyUserNumber(int currentBuyUserNumber) {
		this.currentBuyUserNumber = currentBuyUserNumber;
	}
	
	/*
	public List<CurrentBuyUser> getCurrentBuyUser() {
		return currentBuyUser;
	}
	public void setCurrentBuyUser(List<CurrentBuyUser> currentBuyUser) {
		this.currentBuyUser = currentBuyUser;
	}
	*/
	
	public List<SupportBank> getSupportBank() {
		return supportBank;
	}
	public void setSupportBank(List<SupportBank> supportBank) {
		this.supportBank = supportBank;
	}
	
	public String getSafeguard() {
		return safeguard;
	}
	public void setSafeguard(String safeguard) {
		this.safeguard = safeguard;
	}
	
	public String getBriefContent() {
		return briefContent;
	}
	public void setBriefContent(String briefContent) {
		this.briefContent = briefContent;
	}
	
}
