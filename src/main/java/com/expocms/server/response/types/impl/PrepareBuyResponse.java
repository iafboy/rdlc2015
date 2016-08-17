package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class PrepareBuyResponse extends BaseResponse {

	private String bankInfo1;
	private String bankInfo2;
	private Long redPackageAll;
	private String protocolURL;
	private List<PrepareBuyActivityGift> redPackages;
	private Long commission;

	public String getBankInfo1() {
		return bankInfo1;
	}
	public void setBankInfo1(String bankInfo1) {
		this.bankInfo1 = bankInfo1;
	}
	public String getBankInfo2() {
		return bankInfo2;
	}
	public void setBankInfo2(String bankInfo2) {
		this.bankInfo2 = bankInfo2;
	}
	public Long getRedPackageAll() {
		return redPackageAll;
	}
	public void setRedPackageAll(Long redPackageAll) {
		this.redPackageAll = redPackageAll;
	}

	public List<PrepareBuyActivityGift> getRedPackages() {
		return redPackages;
	}

	public void setRedPackages(List<PrepareBuyActivityGift> redPackages) {
		this.redPackages = redPackages;
	}

	public String getProtocolURL() {
		return protocolURL;
	}
	public void setProtocolURL(String protocolURL) {
		this.protocolURL = protocolURL;
	}
	
	public Long getCommission() {
		return commission;
	}
	public void setCommission(Long commission) {
		this.commission = commission;
	}
	
}
