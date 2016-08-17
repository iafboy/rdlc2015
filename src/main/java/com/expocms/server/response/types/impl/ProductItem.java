package com.expocms.server.response.types.impl;

public class ProductItem {
	
	private String productId;
	private String productName;
	private Long allEarnings;
	private Long predictYearEarnings;
	
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
		this.productName = productName;
	}
	
	public Long getAllEarnings() {
		return allEarnings;
	}
	public void setAllEarnings(Long allEarnings) {
		this.allEarnings = allEarnings;
	}
	
	public Long getPredictYearEarnings() {
		return predictYearEarnings;
	}
	public void setPredictYearEarnings(Long predictYearEarnings) {
		this.predictYearEarnings = predictYearEarnings;
	}
	
}
