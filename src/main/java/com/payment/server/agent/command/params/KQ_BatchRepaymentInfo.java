package com.payment.server.agent.command.params;

import java.util.List;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_BatchRepaymentInfo extends JSonObject {
	
	private String purpose_ = null;
	private List<KQ_BatchRepaymentDealInfo> dealList_ = null;

	public String getPurpose() {
		return purpose_;
	}
	public void setPurpose(String purpose) {
		this.purpose_ = purpose;
	}
	
	public List<KQ_BatchRepaymentDealInfo> getDealList() {
		return dealList_;
	}
	public void setDealList(List<KQ_BatchRepaymentDealInfo> dealList) {
		this.dealList_ = dealList;
	}

	@Override
	public boolean validate() {
		if(this.purpose_ == null || this.purpose_.equals(""))
			return false;
		if(this.dealList_ == null || this.dealList_.size() == 0)
			return false;
		for(int i = 0; i < this.dealList_.size(); i ++) {
			KQ_BatchRepaymentDealInfo deal = this.dealList_.get(i);
			if(deal.validate() == false)
				return false;
		}
		return true;
	}

}
