package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class MoreInfoResponse extends BaseResponse {
	private String appidURL;
	private String guanwangURL;
	private List<HelpCenter> helpCentre;
	public String getAppidURL() {
		return appidURL;
	}
	public void setAppidURL(String appidURL) {
		this.appidURL = appidURL;
	}
	public String getGuanwangURL() {
		return guanwangURL;
	}
	public void setGuanwangURL(String guanwangURL) {
		this.guanwangURL = guanwangURL;
	}
	public List<HelpCenter> getHelpCentre() {
		return helpCentre;
	}
	public void setHelpCentre(List<HelpCenter> helpCentre) {
		this.helpCentre = helpCentre;
	}
	
}
