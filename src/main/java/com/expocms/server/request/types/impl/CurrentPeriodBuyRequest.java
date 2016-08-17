package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class CurrentPeriodBuyRequest implements IRequest {
	private String productId;
	private int page;
	private int pageCount;
	public boolean validate() {
		if(productId!=null
				&&!"".equals(productId.trim())
				&&page>-1
				&&pageCount>-1)
			return true;
		return false;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	
}
