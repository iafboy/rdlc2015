package com.expocms.server.db.model;

public class RdCoupon {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon.user_id
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String group;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_coupon.price
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    private String price;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon.ids
     *
     * @return the value of rd_coupon.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon.ids
     *
     * @param ids the value for rd_coupon.ids
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon.user_id
     *
     * @return the value of rd_coupon.user_id
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon.user_id
     *
     * @param userId the value for rd_coupon.user_id
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon.group
     *
     * @return the value of rd_coupon.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon.group
     *
     * @param group the value for rd_coupon.group
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_coupon.price
     *
     * @return the value of rd_coupon.price
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_coupon.price
     *
     * @param price the value for rd_coupon.price
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }
}