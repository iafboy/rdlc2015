package com.expocms.server.db.model;

import java.util.Date;

public class RdCouponGroup {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon_group.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon_group.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String group;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon_group.total_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private Integer totalNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon_group.current_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private Integer currentNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon_group.expire_date
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private Date expireDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon_group.ids
     *
     * @return the value of rd_coupon_group.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon_group.ids
     *
     * @param ids the value for rd_coupon_group.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon_group.group
     *
     * @return the value of rd_coupon_group.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon_group.group
     *
     * @param group the value for rd_coupon_group.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon_group.total_number
     *
     * @return the value of rd_coupon_group.total_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon_group.total_number
     *
     * @param totalNumber the value for rd_coupon_group.total_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon_group.current_number
     *
     * @return the value of rd_coupon_group.current_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public Integer getCurrentNumber() {
        return currentNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon_group.current_number
     *
     * @param currentNumber the value for rd_coupon_group.current_number
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon_group.expire_date
     *
     * @return the value of rd_coupon_group.expire_date
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon_group.expire_date
     *
     * @param expireDate the value for rd_coupon_group.expire_date
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}