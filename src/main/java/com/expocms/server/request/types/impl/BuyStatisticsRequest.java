package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class BuyStatisticsRequest implements IRequest {
	private String userId;
	private String proId;
	private Long investmentAmount;
	
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

	public Long getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public boolean validate() {
		if(userId!=null&&!"".equals(userId)&&proId!=null&&!"".equals(proId)&&investmentAmount!=null&&investmentAmount.longValue()>-1)
			return true;
		return false;
	}

}
