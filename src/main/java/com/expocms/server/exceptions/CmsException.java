package com.expocms.server.exceptions;

public class CmsException extends Exception {
	private static final long serialVersionUID = -1401220163106670792L;
	private int exceptionCode;
	private String exceptionMessage;
	private Exception exception;
	public CmsException(int exceptionCode_,String exceptionMessage_,Exception exception_){
		this.exceptionCode=exceptionCode_;
		this.exceptionMessage=exceptionMessage_;
		this.exception=exception_;
	}
	public int getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
