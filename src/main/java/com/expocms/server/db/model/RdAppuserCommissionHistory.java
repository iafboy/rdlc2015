package com.expocms.server.db.model;

import java.io.Serializable;
import java.util.Date;

public class RdAppuserCommissionHistory implements Serializable {

	private static final long serialVersionUID = 5095450049775625715L;
	
	private String ids;
	private String recommenderId;
	private Long commission;
	private String orderId;
	private String investorId;
	private Long investAmount;
	private Integer investPeriod;
	private String investorPhone;
	private String investorName;
	private Date investTime;
	
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
	
	public Long getCommission() {
		return commission;
	}
	public void setCommission(Long commission) {
		this.commission = commission;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	
	public Long getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(Long investAmount) {
		this.investAmount = investAmount;
	}
	
	public Integer getInvestPeriod() {
		return investPeriod;
	}
	public void setInvestPeriod(Integer investPeriod) {
		this.investPeriod = investPeriod;
	}
	
	public String getInvestorPhone() {
		return investorPhone;
	}
	public void setInvestorPhone(String investorPhone) {
		this.investorPhone = investorPhone;
	}
	
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
}
