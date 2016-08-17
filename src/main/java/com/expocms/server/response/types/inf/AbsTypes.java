package com.expocms.server.response.types.inf;

import java.util.List;

import com.google.common.collect.Lists;

public class AbsTypes {
	private long id=-1;
	private String name;
	private String bref;
	private long updateTS;
	private long pid=-1;
	private String booth;
	private String url;
	private List<String> imgurl=Lists.newArrayList();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBref() {
		return bref;
	}
	public void setBref(String bref) {
		this.bref = bref;
	}
	public long getUpdateTS() {
		return updateTS;
	}
	public void setUpdateTS(long updateTS) {
		this.updateTS = updateTS;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
	public List<String> getImgurl() {
		return imgurl;
	}
	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}
	
}
