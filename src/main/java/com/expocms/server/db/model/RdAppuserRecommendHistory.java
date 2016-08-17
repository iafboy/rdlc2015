package com.expocms.server.db.model;

import java.io.Serializable;
import java.util.Date;

public class RdAppuserRecommendHistory implements Serializable {
	
	private static final long serialVersionUID = 6217891207940078912L;
	
	private String ids;
	private String recommenderId;
	private String recommendeeId;
	private String recommendeePhone;
	private Date recommendTime;
	private String recommendeeName;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getRecommenderId() {
		return recommenderId;
	}
	public void setRecommenderId(String recommenderId) {
		this.recommenderId = recommenderId;
	}
	
	public String getRecommendeeId() {
		return recommendeeId;
	}
	public void setRecommendeeId(String recommendeeId) {
		this.recommendeeId = recommendeeId;
	}
	
	public String getRecommendeePhone() {
		return recommendeePhone;
	}
	public void setRecommendeePhone(String recommendeePhone) {
		this.recommendeePhone = recommendeePhone;
	}
	
	public Date getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}
	
	public String getRecommendeeName() {
		return recommendeeName;
	}
	public void setRecommendeeName(String recommendeeName) {
		this.recommendeeName = recommendeeName;
	}

}
