package com.payment.server.agent.command.params;

public class KQ_VerifyBankResult extends KQ_BasicResult {

	private String token_ = null;
	
	public String getToken() {
		return token_;
	}
	public void setToken(String token) {
		this.token_ = token;
	}
	
}
