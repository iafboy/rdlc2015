package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class UndoQuickPaymentRequest implements IRequest {
	
	private String orderId;
	private Integer orderSuccess;
	private Integer orderState;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrderSuccess() {
		return orderSuccess;
	}
	public void setOrderSuccess(Integer orderSuccess) {
		this.orderSuccess = orderSuccess;
	}
	
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public boolean validate() {
		if(orderId == null || orderId.equals(""))
			return false;
		
		return true;
	}

}
