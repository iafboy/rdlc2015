package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class MyFeedbackRequest implements IRequest {

	private String userId;

	private int page = 0;

	private int pageCount;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean validate() {
		if (userId != null && !"".equals(userId.trim()))
			return true;
		return false;
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
