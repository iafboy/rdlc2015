package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ProductIntroductionRequest implements IRequest {
	//private String userId;
	private String productId;
	public boolean validate() {
//		if(userId!=null
//				&&!"".equals(userId.trim())
		if(productId!=null
				&&!"".equals(productId.trim()))
			return true;
		return false;
	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
