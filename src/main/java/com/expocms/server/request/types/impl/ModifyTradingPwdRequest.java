package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ModifyTradingPwdRequest implements IRequest {
	private String userId;
	private String transactionPwd;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionPwd() {
		return transactionPwd;
	}

	public void setTransactionPwd(String transactionPwd) {
		this.transactionPwd = transactionPwd;
	}

	public boolean validate() {
		if(userId!=null&&transactionPwd!=null&&!"".equals(userId)&&!"".equals(transactionPwd))
			return true;
		return false;
	}

}
