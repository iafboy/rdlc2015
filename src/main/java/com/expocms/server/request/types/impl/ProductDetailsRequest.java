package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ProductDetailsRequest implements IRequest {
	private String productId;
	

	public boolean validate() {
		if(productId!=null&&!"".equalsIgnoreCase(productId))
			return true;
		return false;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}

}
