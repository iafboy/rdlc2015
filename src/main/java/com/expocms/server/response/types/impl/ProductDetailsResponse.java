package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class ProductDetailsResponse extends BaseResponse {
	
	private ProductDetailInfo info;

	public ProductDetailInfo getInfo() {
		return info;
	}

	public void setInfo(ProductDetailInfo info) {
		this.info = info;
	}


}
