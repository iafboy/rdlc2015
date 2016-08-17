package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_QueryRepaymentInfo extends JSonObject {
	
	private String dealBeginDate_ = null;
	private String dealEndDate_ = null;
	private Long pageSize_ = null;

	public String getDealBeginDate() {
		return dealBeginDate_;
	}
	public void setDealBeginDate(String dealBeginDate) {
		this.dealBeginDate_ = dealBeginDate;
	}

	public String getDealEndDate() {
		return dealEndDate_;
	}
	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate_ = dealEndDate;
	}
	
	public Long getPageSize() {
		return pageSize_;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize_ = pageSize;
	}
	
	@Override
	public boolean validate() {
		if(this.dealBeginDate_ == null || this.dealBeginDate_.equals(""))
			return false;
		if(this.dealEndDate_ == null || this.dealEndDate_.equals(""))
			return false;
		if(this.pageSize_ == null)
			return false;
		return true;
	}

}
