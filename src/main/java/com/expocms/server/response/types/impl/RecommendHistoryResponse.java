package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class RecommendHistoryResponse extends BaseResponse {
	
	private List<Recommendee> recommendeeList;

	public List<Recommendee> getRecommendeeList() {
		return recommendeeList;
	}
	public void setRecommendeeList(List<Recommendee> recommendeeList) {
		this.recommendeeList = recommendeeList;
	}

}
