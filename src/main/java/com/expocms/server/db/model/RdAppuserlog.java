package com.expocms.server.db.model;

import java.util.Date;

public class RdAppuserlog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_appuserlog.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_appuserlog.time
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Date time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_appuserlog.appUserId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String appuserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_appuserlog.appUserName
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String appusername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_appuserlog.operation
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String operation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_appuserlog.ids
     *
     * @return the value of rd_appuserlog.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_appuserlog.ids
     *
     * @param ids the value for rd_appuserlog.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_appuserlog.time
     *
     * @return the value of rd_appuserlog.time
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_appuserlog.time
     *
     * @param time the value for rd_appuserlog.time
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_appuserlog.appUserId
     *
     * @return the value of rd_appuserlog.appUserId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getAppuserid() {
        return appuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_appuserlog.appUserId
     *
     * @param appuserid the value for rd_appuserlog.appUserId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setAppuserid(String appuserid) {
        this.appuserid = appuserid == null ? null : appuserid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_appuserlog.appUserName
     *
     * @return the value of rd_appuserlog.appUserName
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getAppusername() {
        return appusername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_appuserlog.appUserName
     *
     * @param appusername the value for rd_appuserlog.appUserName
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setAppusername(String appusername) {
        this.appusername = appusername == null ? null : appusername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_appuserlog.operation
     *
     * @return the value of rd_appuserlog.operation
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_appuserlog.operation
     *
     * @param operation the value for rd_appuserlog.operation
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }
}