package com.expocms.server.db.model;

public class ProductIntroductionEntity {
	
	private Long allMoney;
	private Double predictYearEarnings;
	private Double investmentTimeLimit;
	private String startDate;
	private String endDate;
	private Long lowMoney;
	private Long highMoney;
	
	public Long getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Long allMoney) {
		this.allMoney = allMoney;
	}
	
	public Double getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(Double predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
	}
	
	public Double getInvestmentTimeLimit() {
		return investmentTimeLimit;
	}
	public void setInvestmentTimeLimit(Double investmentTimeLimit) {
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
	
}
