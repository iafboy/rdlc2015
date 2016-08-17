package com.expocms.server.response.types.impl;

import java.util.List;

public final class QuickPaymentGift {
	
	private List<OrderGift> orderGiftList = null;
	private RedPackage purchaseGift = null;

	public List<OrderGift> getOrderGiftList() {
		return orderGiftList;
	}
	public void setOrderGiftList(List<OrderGift> orderGiftList) {
		this.orderGiftList = orderGiftList;
	}
	
	public RedPackage getPurchaseGift() {
		return purchaseGift;
	}
	public void setPurchaseGift(RedPackage purchaseGift) {
		this.purchaseGift = purchaseGift;
	}

}
