package com.expocms.server.db.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RdActivitygift implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1119398646955191178L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.ids
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.activityName
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private String activityname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.createDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.value
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Long value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.isActivated
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Boolean isactivated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.isUsed
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Boolean isused;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.expireDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Date expiredate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_activitygift.activateDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    private Date activatedate;

    // 1. activity gift  2. fixed gift
    private String type;

    // original gift Id
    private String giftId;


    private Long restMoney;

    private Timestamp activateTime;

    public Timestamp getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Timestamp activateTime) {
        this.activateTime = activateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.ids
     *
     * @return the value of rd_activitygift.ids
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public String getIds() {
        return ids;
    }

    public Long getRestMoney() {
        return restMoney;
    }

    public void setRestMoney(Long restMoney) {
        this.restMoney = restMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.ids
     *
     * @param ids the value for rd_activitygift.ids
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.activityName
     *
     * @return the value of rd_activitygift.activityName
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public String getActivityname() {
        return activityname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.activityName
     *
     * @param activityname the value for rd_activitygift.activityName
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.createDate
     *
     * @return the value of rd_activitygift.createDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.createDate
     *
     * @param createdate the value for rd_activitygift.createDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.value
     *
     * @return the value of rd_activitygift.value
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Long getValue() {
        return value;
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.value
     *
     * @param value the value for rd_activitygift.value
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.isActivated
     *
     * @return the value of rd_activitygift.isActivated
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Boolean getIsactivated() {
        return isactivated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.isActivated
     *
     * @param isactivated the value for rd_activitygift.isActivated
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setIsactivated(Boolean isactivated) {
        this.isactivated = isactivated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.isUsed
     *
     * @return the value of rd_activitygift.isUsed
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Boolean getIsused() {
        return isused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.isUsed
     *
     * @param isused the value for rd_activitygift.isUsed
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setIsused(Boolean isused) {
        this.isused = isused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.expireDate
     *
     * @return the value of rd_activitygift.expireDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Date getExpiredate() {
        return expiredate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.expireDate
     *
     * @param expiredate the value for rd_activitygift.expireDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_activitygift.activateDate
     *
     * @return the value of rd_activitygift.activateDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public Date getActivatedate() {
        return activatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_activitygift.activateDate
     *
     * @param activatedate the value for rd_activitygift.activateDate
     *
     * @mbggenerated Mon Sep 21 01:18:13 CST 2015
     */
    public void setActivatedate(Date activatedate) {
        this.activatedate = activatedate;
    }
}