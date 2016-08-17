package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

import little.ant.payment.pojo.RedPackageEntity;

public class RedPackageResponse extends BaseResponse {
	
	private Long redPackageMoney;
	private List<RedPackageEntity> redPackageList;
	private String redPackageUseState;
	private String redPackageRewards;
	
	public Long getRedPackageMoney() {
		return redPackageMoney;
	}
	public void setRedPackageMoney(Long redPackageMoney) {
		this.redPackageMoney = redPackageMoney;
	}
	
	public List<RedPackageEntity> getRedPackageList() {
		return redPackageList;
	}
	public void setRedPackageList(List<RedPackageEntity> redPackageList) {
		this.redPackageList = redPackageList;
	}
	
	public String getRedPackageUseState() {
		return redPackageUseState;
	}
	public void setRedPackageUseState(String redPackageUseState) {
		this.redPackageUseState = redPackageUseState;
	}
	
	public String getRedPackageRewards() {
		return redPackageRewards;
	}
	public void setRedPackageRewards(String redPackageRewards) {
		this.redPackageRewards = redPackageRewards;
	}

}
