package com.expocms.server.db.model;

import java.io.Serializable;
import java.util.Date;

public class RdAppuserWithdrawHistory implements Serializable {

	private static final long serialVersionUID = -3155224465443162692L;
	
	public static final int RAWD_APPLIED     = 0;       // 提现申请已提交
	public static final int RAWD_APPROVED    = 1;       // 提现申请已审核
	public static final int RAWD_IN_PROGRESS = 2;       // 提现处理中
	public static final int RAWD_SUCCEED     = 3;       // 提现成功
	public static final int RAWD_FAILED      = 4;       // 提现失败
	
	private String ids_;
	private String userId_;
	private Long amount_;
	private Date submitTime_;
	private Date arrivalTime_;
	private Date finishTime_;
	private Integer status_;
	private Long poundageAmount_;
	private Long arrivalAmount_;
	private String failedReason_;
	private String thirdPartyAmount_;

	public String getIds() {
		return ids_;
	}
	public void setIds(String ids) {
		this.ids_ = ids;
	}
	
	public String getUserId() {
		return userId_;
	}
	public void setUserId(String userId) {
		this.userId_ = userId;
	}
	
	public Long getAmount() {
		return amount_;
	}
	public void setAmount(Long amount) {
		this.amount_ = amount;
	}
	
	public Date getSubmitTime() {
		return submitTime_;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime_ = submitTime;
	}
	
	public Date getArrivalTime() {
		return arrivalTime_;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime_ = arrivalTime;
	}
	
	public Date getFinishTime() {
		return finishTime_;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime_ = finishTime;
	}
	
	public Integer getStatus() {
		return status_;
	}
	public void setStatus(Integer status) {
		this.status_ = status;
	}
	
	public Long getPoundageAmount() {
		return poundageAmount_;
	}
	public void setPoundageAmount(Long poundageAmount) {
		this.poundageAmount_ = poundageAmount;
	}
	
	public Long getArrivalAmount() {
		return arrivalAmount_;
	}
	public void setArrivalAmount(Long arrivalAmount) {
		this.arrivalAmount_ = arrivalAmount;
	}
	
	public String getFailedReason() {
		return failedReason_;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason_ = failedReason;
	}
	
	public String getThirdPartyAmount() {
		return thirdPartyAmount_;
	}
	public void setThirdPartyAmount(String thirdPartyAmount) {
		this.thirdPartyAmount_ = thirdPartyAmount;
	}
	
}
