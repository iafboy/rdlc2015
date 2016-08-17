package com.expocms.server.response.types.inf;

public  class BasicRespObj {
	public long id;
	public int typeid;//0.for news,1 for exhibitors, 2 for exhibits, 3 for activity, 4 for adv
	private String name;
	private String brief;
	private String webpathpath;
	public long updateST;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUpdateST() {
		return updateST;
	}
	public void setUpdateST(long updateST) {
		this.updateST = updateST;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebpathpath() {
		return webpathpath;
	}
	public void setWebpathpath(String webpathpath) {
		this.webpathpath = webpathpath;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
}
