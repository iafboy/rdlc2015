package com.expocms.server.db.model;

public class RdSystemlogWithBLOBs extends RdSystemlog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_systemlog.operatorId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String operatorid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_systemlog.operationDesc
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String operationdesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_systemlog.operatorId
     *
     * @return the value of rd_systemlog.operatorId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getOperatorid() {
        return operatorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_systemlog.operatorId
     *
     * @param operatorid the value for rd_systemlog.operatorId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_systemlog.operationDesc
     *
     * @return the value of rd_systemlog.operationDesc
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getOperationdesc() {
        return operationdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_systemlog.operationDesc
     *
     * @param operationdesc the value for rd_systemlog.operationDesc
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setOperationdesc(String operationdesc) {
        this.operationdesc = operationdesc == null ? null : operationdesc.trim();
    }
}