package com.expocms.server.response.types.inf;

public abstract class BaseResponse {
	
	protected int resultCode;
	protected String resultMsg;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
}
