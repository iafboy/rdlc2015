package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class WithdrawDetailResponse extends BaseResponse {
	
	private List<WithdrawDetail> withdrawDetailList = null;

	public List<WithdrawDetail> getWithdrawDetailList() {
		return withdrawDetailList;
	}
	public void setWithdrawDetailList(List<WithdrawDetail> withdrawDetailList) {
		this.withdrawDetailList = withdrawDetailList;
	}

}
