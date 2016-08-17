package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class LoginResponse extends BaseResponse {
	
	private String name;
	private String userId;
	private Integer loginCount;
	private Integer orderCount;
	private String idCard;
	private MyBankCard myBankCard;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public MyBankCard getMyBankCard() {
		return myBankCard;
	}
	public void setMyBankCard(MyBankCard myBankCard) {
		this.myBankCard = myBankCard;
	}
	
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
}
