package com.expocms.server.response.types.impl;

public class MyBankCard {
	
	private String issuer;
	private Long bankQuota;
	private String bankCardId;
	private String supportPhone;
	
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public Long getBankQuota() {
		return bankQuota;
	}
	public void setBankQuota(Long bankQuota) {
		this.bankQuota = bankQuota;
	}
	
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	
	public String getSupportPhone() {
		return supportPhone;
	}
	public void setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}
	
}
