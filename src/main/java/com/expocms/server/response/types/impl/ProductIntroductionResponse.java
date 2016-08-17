package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class ProductIntroductionResponse extends BaseResponse {
	
	private Long allMoney;
	private Long predictYearEarnings;
	private Long investmentTimeLimit;
	private String startDate;
	private String endDate;
	private String guanyuDateStr;
	private Long lowMoney;
	private Long highMoney;
	private Long poundage;
	private String closeDatestr;
	private String persontaxStr;
	private String redemptionStr;
	
	public Long getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Long allMoney) {
		this.allMoney = allMoney;
	}
	
	public Long getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(Long predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
	}
	
	public Long getInvestmentTimeLimit() {
		return investmentTimeLimit;
	}
	public void setInvestmentTimeLimit(Long investmentTimeLimit) {
		this.investmentTimeLimit = investmentTimeLimit;
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
	
	public String getGuanyuDateStr() {
		return guanyuDateStr;
	}
	public void setGuanyuDateStr(String guanyuDateStr) {
		this.guanyuDateStr = guanyuDateStr;
	}
	
	public Long getLowMoney() {
		return lowMoney;
	}
	public void setLowMoney(Long lowMoney) {
		this.lowMoney = lowMoney;
	}
	
	public Long getHighMoney() {
		return highMoney;
	}
	public void setHighMoney(Long highMoney) {
		this.highMoney = highMoney;
	}
	
	public Long getPoundage() {
		return poundage;
	}
	public void setPoundage(Long poundage) {
		this.poundage = poundage;
	}
	
	public String getCloseDatestr() {
		return closeDatestr;
	}
	public void setCloseDatestr(String closeDatestr) {
		this.closeDatestr = closeDatestr;
	}
	
	public String getPersontaxStr() {
		return persontaxStr;
	}
	public void setPersontaxStr(String persontaxStr) {
		this.persontaxStr = persontaxStr;
	}
	
	public String getRedemptionStr() {
		return redemptionStr;
	}
	public void setRedemptionStr(String redemptionStr) {
		this.redemptionStr = redemptionStr;
	}
	
}
