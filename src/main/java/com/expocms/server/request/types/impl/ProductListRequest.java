package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class ProductListRequest implements IRequest {
	
	private String userId;
	private String productType;
	private int page;
	private int pageCount;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public boolean validate() {
		if(/*productType != null && !"".equals(productType) &&*/
				page > -1 && pageCount > -1)
			return true;
		return false;
	}
	
}
