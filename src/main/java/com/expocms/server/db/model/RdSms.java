package com.expocms.server.db.model;

import java.util.Date;

public class RdSms {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.to
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String to;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.date
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.state
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_sms.to_number
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Integer toNumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.ids
     *
     * @return the value of rd_sms.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.ids
     *
     * @param ids the value for rd_sms.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.content
     *
     * @return the value of rd_sms.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.content
     *
     * @param content the value for rd_sms.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.to
     *
     * @return the value of rd_sms.to
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getTo() {
        return to;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.to
     *
     * @param to the value for rd_sms.to
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.date
     *
     * @return the value of rd_sms.date
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.date
     *
     * @param date the value for rd_sms.date
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.state
     *
     * @return the value of rd_sms.state
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.state
     *
     * @param state the value for rd_sms.state
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_sms.to_number
     *
     * @return the value of rd_sms.to_number
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Integer getToNumber() {
        return toNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_sms.to_number
     *
     * @param toNumber the value for rd_sms.to_number
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setToNumber(Integer toNumber) {
        this.toNumber = toNumber;
    }
}