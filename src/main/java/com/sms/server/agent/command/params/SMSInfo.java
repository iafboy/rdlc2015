package com.sms.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class SMSInfo extends JSonObject {
	
	private String phoneNo_ = null;
	private String smsContent_ = null;
	
	public String getPhoneNo() {
		return phoneNo_;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo_ = phoneNo;
	}
	
	public String getSmsContent() {
		return smsContent_;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent_ = smsContent;
	}
	
	@Override
	public boolean validate() {
		if(this.phoneNo_ == null || this.phoneNo_.equals(""))
			return false;
		if(this.smsContent_ == null || this.smsContent_.equals(""))
			return false;
		return true;
	}

}
