package com.expocms.server.db.model;

import java.util.Date;

public class RdAdvicetemplate {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_advicetemplate.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_advicetemplate.title
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_advicetemplate.isDefault
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Boolean isdefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_advicetemplate.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_advicetemplate.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_advicetemplate.ids
     *
     * @return the value of rd_advicetemplate.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_advicetemplate.ids
     *
     * @param ids the value for rd_advicetemplate.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_advicetemplate.title
     *
     * @return the value of rd_advicetemplate.title
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_advicetemplate.title
     *
     * @param title the value for rd_advicetemplate.title
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_advicetemplate.isDefault
     *
     * @return the value of rd_advicetemplate.isDefault
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Boolean getIsdefault() {
        return isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_advicetemplate.isDefault
     *
     * @param isdefault the value for rd_advicetemplate.isDefault
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_advicetemplate.createTime
     *
     * @return the value of rd_advicetemplate.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_advicetemplate.createTime
     *
     * @param createtime the value for rd_advicetemplate.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_advicetemplate.content
     *
     * @return the value of rd_advicetemplate.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_advicetemplate.content
     *
     * @param content the value for rd_advicetemplate.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}