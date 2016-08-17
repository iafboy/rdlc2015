package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

import little.ant.payment.pojo.RedPackageEntity;

public class RedPackageExchangeResponse extends BaseResponse {
	private Long redPackageMoney;
//	private List<RedPackageEntity redPackageList;
	private RedPackageEntity redPackageList;




	public Long getRedPackageMoney() {
		return redPackageMoney;
	}
	public void setRedPackageMoney(Long redPackageMoney) {
		this.redPackageMoney = redPackageMoney;
	}
//	public List<RedPackageEntity> getRedPackageList() {
//		return redPackageList;
//	}
//	public void setRedPackageList(List<RedPackageEntity> redPackageList) {
//		this.redPackageList = redPackageList;
//	}

	public RedPackageEntity getRedPackageList() {
		return redPackageList;
	}

	public void setRedPackageList(RedPackageEntity redPackageList) {
		this.redPackageList = redPackageList;
	}
}
