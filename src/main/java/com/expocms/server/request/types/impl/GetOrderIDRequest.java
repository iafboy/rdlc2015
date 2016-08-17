package com.expocms.server.request.types.impl;

import java.util.List;

import com.expocms.server.request.types.inf.IRequest;

public class GetOrderIDRequest implements IRequest {
	private String userId;
	private String productId;
	private Long investmentAmount;
	private String kuaiqianNumber;
	private List<String> redPackageMark;
	public boolean validate() {
		if(userId!=null&&!"".equals(userId.trim())
				&&productId!=null&&!"".equals(productId.trim())
				&&investmentAmount!=null&&investmentAmount.longValue()>-1
				&&kuaiqianNumber!=null&&!"".equals(kuaiqianNumber.trim()))
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
	public Long getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	public String getKuaiqianNumber() {
		return kuaiqianNumber;
	}
	public void setKuaiqianNumber(String kuaiqianNumber) {
		this.kuaiqianNumber = kuaiqianNumber;
	}
	public List<String> getRedPackageMark() {
		return redPackageMark;
	}
	public void setRedPackageMark(List<String> redPackageMark) {
		this.redPackageMark = redPackageMark;
	}
	
}
