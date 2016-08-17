package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class ProductListResponse extends BaseResponse {
	private List<ProductItem> list;
	private int page;
	private int pageCount;
	public List<ProductItem> getList() {
		return list;
	}

	public void setList(List<ProductItem> list) {
		this.list = list;
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
