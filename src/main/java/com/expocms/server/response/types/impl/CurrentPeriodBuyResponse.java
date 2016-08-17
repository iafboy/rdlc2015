package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class CurrentPeriodBuyResponse extends BaseResponse {
	private List<CurrentBuyUser> currentBuyUser;

	public List<CurrentBuyUser> getCurrentBuyUser() {
		return currentBuyUser;
	}

	public void setCurrentBuyUser(List<CurrentBuyUser> currentBuyUser) {
		this.currentBuyUser = currentBuyUser;
	}
	
}
