package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

import java.util.List;

public class DataGenerateRequest implements IRequest {
	private String userId;
	private String productId;
	private String orderId;
	private Long investmentAmount;
	private List<String> redPackageMark;
	public boolean validate() {
		if(userId!=null
				&&!"".equals(userId)
				&&productId!=null
				&&!"".equals(productId.trim())
				&&orderId!=null
				&&!"".equals(orderId)
				&&investmentAmount.longValue()>-1)
				return true;
		return false;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	public List<String> getRedPackageMark() {
		return redPackageMark;
	}
	public void setRedPackageMark(List<String> redPackageMark) {
		this.redPackageMark = redPackageMark;
	}
	
}
