package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class BuyProductRequest implements IRequest {
	private String userId;
	private String proId;
	private String orderNo;
	private Double investmentAmount;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public boolean validate() {
		if(userId!=null&&!"".equals(userId)&&proId!=null&&!"".equals(proId)&&investmentAmount!=null)
			return true;
		return false;
	}

}
