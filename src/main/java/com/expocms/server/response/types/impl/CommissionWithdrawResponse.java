package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class CommissionWithdrawResponse extends BaseResponse {
	
	private String arrivalTime = null;
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
