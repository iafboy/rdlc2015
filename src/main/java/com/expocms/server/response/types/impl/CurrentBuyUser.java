package com.expocms.server.response.types.impl;

import java.util.Date;

public class CurrentBuyUser {
	
	//private String userId;
	private String phoneNo;
	private Date buyTimeDB;
	private String buyTime;
	private Long buyMoney;
	
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Date getBuyTimeDB() {
		return buyTimeDB;
	}
	public void setBuyTimeDB(Date buyTimeDB) {
		this.buyTimeDB = buyTimeDB;
	}
	
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	
	public Long getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Long buyMoney) {
		this.buyMoney = buyMoney;
	}
	
}
