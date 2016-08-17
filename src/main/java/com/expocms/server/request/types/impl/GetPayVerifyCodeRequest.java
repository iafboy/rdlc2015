package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class GetPayVerifyCodeRequest implements IRequest{
	
	public static final String TEST_CODE = "THIS is thE tEst code For GetPayVerifyCodeRequest";
	
	private String productId;
	private String userid;
	private Long amount;
	private String cardNo;
	private String bankName;
	private String cardHolderName;
	private String cardHolderId;
	private String payPhone;
	
	private String testCode;
	
	public boolean validate() {
		if(productId != null && !"".equals(productId.trim()) &&
				userid != null && !"".equals(userid.trim()))
			return true;
		return false;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCardHolderId() {
		return cardHolderId;
	}
	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}
	
	public String getPayPhone() {
		return payPhone;
	}
	public void setPayPhone(String payPhone) {
		this.payPhone = payPhone;
	}

	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

}
