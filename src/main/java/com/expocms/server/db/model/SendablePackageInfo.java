package com.expocms.server.db.model;

/**
 * Created by Administrator on 2015/9/22.
 */
public class SendablePackageInfo {

    public static final String TIP_TYPE_ACTIVATE  =  "领用期限";

    public static final String TIP_TYPE_EXPIRE  =  "使用期限";

    public static final String TYPE_FIXED = "固定红包";

    public static final String TYPE_ACTIVITY = "活动红包";

    private String ids;

    private String expireDays;

    //活动红包  固定红包
    private String type;
    //领用期限，使用期限
    private String tipType;

    private String activateDays;

    private double amount;

    @Override
    public String toString() {
        if(TIP_TYPE_ACTIVATE.equals(tipType)){
            return "1张"+amount+"元的"+type+","+tipType+"还有"+activateDays+"天";
        }else if(TIP_TYPE_EXPIRE.equals(tipType)){
            return "1张"+amount+"元的"+type+","+tipType+"还有"+expireDays+"天";
        }
        return "";
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(String expireDays) {
        this.expireDays = expireDays;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTipType() {
        return tipType;
    }

    public void setTipType(String tipType) {
        this.tipType = tipType;
    }

    public String getActivateDays() {
        return activateDays;
    }

    public void setActivateDays(String activateDays) {
        this.activateDays = activateDays;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
