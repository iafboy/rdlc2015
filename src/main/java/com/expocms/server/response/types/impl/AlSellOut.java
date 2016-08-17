package com.expocms.server.response.types.impl;

public class AlSellOut {
	
	private String productId;
	private String productName;
	private Long allEarnings;
	private int predictYearEarnings;

	public Long getAllEarnings() {
		return allEarnings;
	}
	public void setAllEarnings(Long allEarnings) {
		this.allEarnings = allEarnings;
	}
	
	public int getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(int predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
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
	
	
}
