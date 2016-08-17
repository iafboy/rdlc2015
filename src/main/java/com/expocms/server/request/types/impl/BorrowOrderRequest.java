package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class BorrowOrderRequest implements IRequest {
	private String userId;
	private String productId;
	private String orderId;
	
	public boolean validate() {
		if(userId != null && !"".equals(userId.trim()) &&
				productId != null && !"".equals(productId.trim()) &&
				orderId != null && !"".equals(orderId.trim()))
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
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
