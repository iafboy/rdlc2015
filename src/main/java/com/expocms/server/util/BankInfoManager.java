package com.expocms.server.util;

import java.util.HashMap;
import java.util.Map;

public final class BankInfoManager {
	
	private static BankInfoManager info_ = null;
	
	private Map<String, BankInfo> bankInfoMap_ = null;
	
	private BankInfoManager() {}
	
	public static BankInfoManager instance() {
		if(info_ == null)
			info_ = new BankInfoManager();
		return info_;
	}
	
	public synchronized BankInfo getBankInfo(final String ids) {
		if(ids == null || ids.equals(""))
			return null;
		
		if(this.bankInfoMap_ == null || this.bankInfoMap_.size() == 0)
			return null;
		
		return this.bankInfoMap_.get(ids);
	}
	
	public synchronized void setBankInfo(
			final String ids,
			final String bankName, final String bankCardNo,
			final String bankCardHolderName, final String bankCardHolderId) {
		
		if(ids == null || ids.equals(""))
			return;
		if(bankName == null || bankName.equals(""))
			return;
		if(bankCardNo == null || bankCardNo.equals(""))
			return;
		if(bankCardHolderName == null || bankCardHolderName.equals(""))
			return;
		if(bankCardHolderId == null || bankCardHolderId.equals(""))
			return;
		
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankName(bankName);
		bankInfo.setBankCardNo(bankCardNo);
		bankInfo.setBankCardHolderName(bankCardHolderName);
		bankInfo.setBankCardHolderId(bankCardHolderId);
		
		if(this.bankInfoMap_ == null)
			this.bankInfoMap_ = new HashMap<String, BankInfo>();
		
		this.bankInfoMap_.put(ids, bankInfo);
	}
	
	public synchronized void setBankInfo(final String ids, final BankInfo bankInfo) {
		if(ids == null || ids.equals(""))
			return;
		if(bankInfo == null)
			return;
		
		if(this.bankInfoMap_ == null)
			this.bankInfoMap_ = new HashMap<String, BankInfo>();
		
		this.bankInfoMap_.put(ids, bankInfo);
	}
	
	public synchronized void removeBankInfo(final String ids) {
		if(ids == null || ids.equals(""))
			return;
		
		if(this.bankInfoMap_ == null || this.bankInfoMap_.size() == 0)
			return;
		
		this.bankInfoMap_.remove(ids);
	}

}
