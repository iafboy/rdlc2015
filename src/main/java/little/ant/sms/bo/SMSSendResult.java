package little.ant.sms.bo;

import java.io.Serializable;

public class SMSSendResult implements Serializable{
	private static final long serialVersionUID = -2513304861790559336L;
	private long code;
	private String message;
	private String orgSentMsg;
	private Throwable exception;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	public String getOrgSentMsg() {
		return orgSentMsg;
	}
	public void setOrgSentMsg(String orgSentMsg) {
		this.orgSentMsg = orgSentMsg;
	}
	
}
