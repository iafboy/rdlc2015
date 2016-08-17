package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class MyPropertyResponse extends BaseResponse {
	private Long allProperty;
	private Long yesterdayEarnings;
	private Long allEarnings;
	private List<RedeemItem> redeemItem;
	private List<AlreadyBuyItem> alreadybuyItem;
	
	public List<AlreadyBuyItem> getAlreadybuyItem() {
		return alreadybuyItem;
	}
	public void setAlreadybuyItem(List<AlreadyBuyItem> alreadybuyItem) {
		this.alreadybuyItem = alreadybuyItem;
	}
	public Long getAllProperty() {
		return allProperty;
	}
	public void setAllProperty(Long allProperty) {
		this.allProperty = allProperty;
	}
	public Long getYesterdayEarnings() {
		return yesterdayEarnings;
	}
	public void setYesterdayEarnings(Long yesterdayEarnings) {
		this.yesterdayEarnings = yesterdayEarnings;
	}
	public Long getAllEarnings() {
		return allEarnings;
	}
	public void setAllEarnings(Long allEarnings) {
		this.allEarnings = allEarnings;
	}
	public List<RedeemItem> getRedeemItem() {
		return redeemItem;
	}
	public void setRedeemItem(List<RedeemItem> redeemItem) {
		this.redeemItem = redeemItem;
	}
	
}
