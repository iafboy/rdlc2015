package com.expocms.server.response.types.impl;

public final class OrderGift {
	
	private String redPackageCode;
	private Long usedMoney;
	private String type;
	private String originalRedPackageId;
	
	public String getRedPackageCode() {
		return redPackageCode;
	}
	public void setRedPackageCode(String redPackageCode) {
		this.redPackageCode = redPackageCode;
	}
	
	public Long getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(Long usedMoney) {
		this.usedMoney = usedMoney;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getOriginalRedPackageId() {
		return originalRedPackageId;
	}
	public void setOriginalRedPackageId(String originalRedPackageId) {
		this.originalRedPackageId = originalRedPackageId;
	}

}
