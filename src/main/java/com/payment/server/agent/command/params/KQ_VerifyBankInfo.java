package com.payment.server.agent.command.params;

import com.diamon.product.server.agent.command.JSonObject;

public class KQ_VerifyBankInfo extends JSonObject {
	
	private KQ_BankInfo bankInfo_ = null;
	private String userId_ = null;
	private String externalRefNo_ = null;
	private String idType_ = null;
	
	public KQ_BankInfo getBankInfo() {
		return bankInfo_;
	}
	public void setBankInfo(KQ_BankInfo bankInfo) {
		this.bankInfo_ = bankInfo;
	}
	
	public String getUserId() {
		return userId_;
	}
	public void setUserId(String userId) {
		this.userId_ = userId;
	}
	
	public String getExternalRefNo() {
		return externalRefNo_;
	}
	public void setExternalRefNo(String externalRefNo) {
		this.externalRefNo_ = externalRefNo;
	}
	
	public String getIdType() {
		return idType_;
	}
	public void setIdType(String idType) {
		this.idType_ = idType;
	}

	@Override
	public boolean validate() {
		if(this.bankInfo_ == null)
			return false;
		return true;
	}

}
