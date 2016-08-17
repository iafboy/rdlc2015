package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class BuyStatisticsResponse extends BaseResponse {
	private int buyCount;

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	
}
