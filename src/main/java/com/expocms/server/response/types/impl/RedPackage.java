package com.expocms.server.response.types.impl;

public class RedPackage{
	
	private String redPackageCode;
	private String active;
	private Long money;
	private String validDate;
	private Integer purchaseNum;

	public String getRedPackageCode() {
		return redPackageCode;
	}
	public void setRedPackageCode(String redPackageCode) {
		this.redPackageCode = redPackageCode;
	}
	
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	
	public String getActive() {
		return active;
	}
	public String isActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	
	public Integer getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	
}
