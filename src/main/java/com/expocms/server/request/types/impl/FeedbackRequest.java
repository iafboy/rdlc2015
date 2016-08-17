package com.expocms.server.request.types.impl;

import com.expocms.server.request.types.inf.IRequest;

public class FeedbackRequest implements IRequest {
	private String userId;
	private String content;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean validate() {
		if(userId!=null&&content!=null&&!"".equals(userId)&&!"".equals(content))
			return true;
		return false;
	}

}
