package com.payment.server.agent.command.params;

import java.util.List;

public class KQ_QueryRepaymentResult extends KQ_BasicResult {
	
	private Integer totalCount_ = null;
	private Integer totalPage_ = null;
	private List<KQ_QueryRepaymentDealInfo> dealList_ = null;
	
	public Integer getTotalCount() {
		return totalCount_;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount_ = totalCount;
	}
	
	public Integer getTotalPage() {
		return totalPage_;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage_ = totalPage;
	}
	
	public List<KQ_QueryRepaymentDealInfo> getDealList() {
		return dealList_;
	}
	public void setDealList(List<KQ_QueryRepaymentDealInfo> dealList) {
		this.dealList_ = dealList;
	}

}
