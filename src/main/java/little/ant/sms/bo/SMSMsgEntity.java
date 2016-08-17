package little.ant.sms.bo;

import java.io.Serializable;

public class SMSMsgEntity implements Serializable{
	private static final long serialVersionUID = -997724126367917354L;
	private String msisdn;
	private String message;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
