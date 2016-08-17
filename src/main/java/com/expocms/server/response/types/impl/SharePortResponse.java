package com.expocms.server.response.types.impl;

import com.expocms.server.response.types.inf.BaseResponse;

public class SharePortResponse extends BaseResponse {
	private String shareTitle;
	private String shareContent;
	private String shareimageUrl;
	private String erweimaURL;
	private String shareURL;

	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public String getShareimageUrl() {
		return shareimageUrl;
	}
	public void setShareimageUrl(String shareimageUrl) {
		this.shareimageUrl = shareimageUrl;
	}

	public void setShareURL(String shareURL) {
		this.shareURL = shareURL;
	}

	public String getShareURL() {
		return shareURL;
	}

	public String getErweimaURL() {
		return erweimaURL;
	}

	public void setErweimaURL(String erweimaURL) {
		this.erweimaURL = erweimaURL;
	}
}
