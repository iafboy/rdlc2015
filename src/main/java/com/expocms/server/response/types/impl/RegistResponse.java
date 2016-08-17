package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class RegistResponse extends BaseResponse {
	
	private String userId;
	private String redPackageCode;
	private Long redPackageMoney;
	private Integer orderCount;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getRedPackageCode() {
		return redPackageCode;
	}
	public void setRedPackageCode(String redPackageCode) {
		this.redPackageCode = redPackageCode;
	}
	
	public Long getRedPackageMoney() {
		return redPackageMoney;
	}
	public void setRedPackageMoney(Long redPackageMoney) {
		this.redPackageMoney = redPackageMoney;
	}
	
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
}
