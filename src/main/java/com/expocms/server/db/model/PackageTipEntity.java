package com.expocms.server.db.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2015/9/22.
 */
public class PackageTipEntity {

    private String userName;

    private List<SendablePackageInfo> gifts = new ArrayList<SendablePackageInfo>();

    private String sex;

    private String phone;

    private int giftCount;

    private double totalAmount;

    @Override
    public String toString() {
        String sexStr = "";
        if("F".equalsIgnoreCase(sex)){
            sexStr = "女士";
        }else if("M".equalsIgnoreCase(sex)){
            sexStr = "先生";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(userName+ sexStr+"您好，"+"");
        if(gifts != null && gifts.size() > 0){
            sb.append("您有"+giftCount+"个"+"红包共"+totalAmount+"元即将到期。");
        }
        sb.append("详情请查看融到理财APP。为避免过期，请尽快使用。");
        return sb.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SendablePackageInfo> getGifts() {
        return gifts;
    }

    public void setGifts(List<SendablePackageInfo> gifts) {
        this.gifts = gifts;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}