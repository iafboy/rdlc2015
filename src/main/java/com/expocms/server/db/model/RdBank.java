package com.expocms.server.db.model;

import java.io.Serializable;

public class RdBank implements Serializable {

	private static final long serialVersionUID = 8036801290651435604L;
	
	private String name;
	private String briefName;
	private String supportPhone;
	private long limitPerPurchase;
	private long limitPerDay;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBriefName() {
		return briefName;
	}
	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}
	
	public String getSupportPhone() {
		return supportPhone;
	}
	public void setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}
	
	public long getLimitPerPurchase() {
		return limitPerPurchase;
	}
	public void setLimitPerPurchase(long limitPerPurchase) {
		this.limitPerPurchase = limitPerPurchase;
	}
	
	public long getLimitPerDay() {
		return limitPerDay;
	}
	public void setLimitPerDay(long limitPerDay) {
		this.limitPerDay = limitPerDay;
	}

}
