package com.sms.server.agent.command.params;

import java.util.List;

import com.diamon.product.server.agent.command.JSonObject;

public class SMSBCInfo extends JSonObject {
	
	private List<String> phoneNoList_ = null;
	private String smsContent_ = null;

	public List<String> getPhoneNoList() {
		return phoneNoList_;
	}
	public void setPhoneNoList(List<String> phoneNoList) {
		this.phoneNoList_ = phoneNoList;
	}
	
	public String getSmsContent() {
		return smsContent_;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent_ = smsContent;
	}
	
	@Override
	public boolean validate() {
		if(this.phoneNoList_ == null || this.phoneNoList_.size() == 0)
			return false;
		if(this.smsContent_ == null || this.smsContent_.equals(""))
			return false;
		return true;
	}

}
