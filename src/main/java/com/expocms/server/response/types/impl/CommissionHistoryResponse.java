package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class CommissionHistoryResponse extends BaseResponse {
	
	private Long commission;
	private List<CommissionRecord> commissionRecordList;
	
	public Long getCommission() {
		return commission;
	}
	public void setCommission(Long commission) {
		this.commission = commission;
	}
	
	public List<CommissionRecord> getCommissionRecordList() {
		return commissionRecordList;
	}
	public void setCommissionRecordList(List<CommissionRecord> commissionRecordList) {
		this.commissionRecordList = commissionRecordList;
	}

}
