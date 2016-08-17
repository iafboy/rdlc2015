package com.expocms.server.db.model;

import java.util.Date;

public class RdPaymentRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.version
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.txnType
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String txntype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.interactiveStatus
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String interactivestatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.amount
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Long amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.merchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String merchantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.settleMerchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String settlemerchantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.terminalId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String terminalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.externalRefNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String externalrefnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.customerId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String customerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.refNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String refnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.responseCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String responsecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.transTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String transtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.entryTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String entrytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.cardOrg
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String cardorg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.issuer
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String issuer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.storableCardNo
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String storablecardno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.authorizationCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String authorizationcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.signature
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String signature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_payment_record.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.ids
     *
     * @return the value of rd_payment_record.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.ids
     *
     * @param ids the value for rd_payment_record.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.version
     *
     * @return the value of rd_payment_record.version
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.version
     *
     * @param version the value for rd_payment_record.version
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.txnType
     *
     * @return the value of rd_payment_record.txnType
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getTxntype() {
        return txntype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.txnType
     *
     * @param txntype the value for rd_payment_record.txnType
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTxntype(String txntype) {
        this.txntype = txntype == null ? null : txntype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.interactiveStatus
     *
     * @return the value of rd_payment_record.interactiveStatus
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getInteractivestatus() {
        return interactivestatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.interactiveStatus
     *
     * @param interactivestatus the value for rd_payment_record.interactiveStatus
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setInteractivestatus(String interactivestatus) {
        this.interactivestatus = interactivestatus == null ? null : interactivestatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.amount
     *
     * @return the value of rd_payment_record.amount
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.amount
     *
     * @param amount the value for rd_payment_record.amount
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.merchantId
     *
     * @return the value of rd_payment_record.merchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.merchantId
     *
     * @param merchantid the value for rd_payment_record.merchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.settleMerchantId
     *
     * @return the value of rd_payment_record.settleMerchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getSettlemerchantid() {
        return settlemerchantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.settleMerchantId
     *
     * @param settlemerchantid the value for rd_payment_record.settleMerchantId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setSettlemerchantid(String settlemerchantid) {
        this.settlemerchantid = settlemerchantid == null ? null : settlemerchantid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.terminalId
     *
     * @return the value of rd_payment_record.terminalId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getTerminalid() {
        return terminalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.terminalId
     *
     * @param terminalid the value for rd_payment_record.terminalId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid == null ? null : terminalid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.externalRefNumber
     *
     * @return the value of rd_payment_record.externalRefNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getExternalrefnumber() {
        return externalrefnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.externalRefNumber
     *
     * @param externalrefnumber the value for rd_payment_record.externalRefNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setExternalrefnumber(String externalrefnumber) {
        this.externalrefnumber = externalrefnumber == null ? null : externalrefnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.customerId
     *
     * @return the value of rd_payment_record.customerId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.customerId
     *
     * @param customerid the value for rd_payment_record.customerId
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.refNumber
     *
     * @return the value of rd_payment_record.refNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getRefnumber() {
        return refnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.refNumber
     *
     * @param refnumber the value for rd_payment_record.refNumber
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setRefnumber(String refnumber) {
        this.refnumber = refnumber == null ? null : refnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.responseCode
     *
     * @return the value of rd_payment_record.responseCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getResponsecode() {
        return responsecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.responseCode
     *
     * @param responsecode the value for rd_payment_record.responseCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode == null ? null : responsecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.transTime
     *
     * @return the value of rd_payment_record.transTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getTranstime() {
        return transtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.transTime
     *
     * @param transtime the value for rd_payment_record.transTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setTranstime(String transtime) {
        this.transtime = transtime == null ? null : transtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.entryTime
     *
     * @return the value of rd_payment_record.entryTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getEntrytime() {
        return entrytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.entryTime
     *
     * @param entrytime the value for rd_payment_record.entryTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setEntrytime(String entrytime) {
        this.entrytime = entrytime == null ? null : entrytime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.cardOrg
     *
     * @return the value of rd_payment_record.cardOrg
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getCardorg() {
        return cardorg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.cardOrg
     *
     * @param cardorg the value for rd_payment_record.cardOrg
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setCardorg(String cardorg) {
        this.cardorg = cardorg == null ? null : cardorg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.issuer
     *
     * @return the value of rd_payment_record.issuer
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.issuer
     *
     * @param issuer the value for rd_payment_record.issuer
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.storableCardNo
     *
     * @return the value of rd_payment_record.storableCardNo
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getStorablecardno() {
        return storablecardno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.storableCardNo
     *
     * @param storablecardno the value for rd_payment_record.storableCardNo
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setStorablecardno(String storablecardno) {
        this.storablecardno = storablecardno == null ? null : storablecardno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.authorizationCode
     *
     * @return the value of rd_payment_record.authorizationCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getAuthorizationcode() {
        return authorizationcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.authorizationCode
     *
     * @param authorizationcode the value for rd_payment_record.authorizationCode
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setAuthorizationcode(String authorizationcode) {
        this.authorizationcode = authorizationcode == null ? null : authorizationcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.signature
     *
     * @return the value of rd_payment_record.signature
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getSignature() {
        return signature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.signature
     *
     * @param signature the value for rd_payment_record.signature
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_payment_record.createTime
     *
     * @return the value of rd_payment_record.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_payment_record.createTime
     *
     * @param createtime the value for rd_payment_record.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}