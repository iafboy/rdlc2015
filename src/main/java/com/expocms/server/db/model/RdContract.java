package com.expocms.server.db.model;

public class RdContract {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_contract.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_contract.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_contract.seq
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Integer seq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_contract.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_contract.ids
     *
     * @return the value of rd_contract.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_contract.ids
     *
     * @param ids the value for rd_contract.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_contract.name
     *
     * @return the value of rd_contract.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_contract.name
     *
     * @param name the value for rd_contract.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_contract.seq
     *
     * @return the value of rd_contract.seq
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_contract.seq
     *
     * @param seq the value for rd_contract.seq
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_contract.content
     *
     * @return the value of rd_contract.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_contract.content
     *
     * @param content the value for rd_contract.content
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}