package com.expocms.server.response.types.impl;

/**
 * Created by Samuel on 2015/10/1.
 */
public class PrepareBuyActivityGift {
    private String redPackageCode;
    private Long restMoney;

    public String getRedPackageCode() {
        return redPackageCode;
    }

    public void setRedPackageCode(String redPackageCode) {
        this.redPackageCode = redPackageCode;
    }

    public Long getRestMoney() {
        return restMoney;
    }

    public void setRestMoney(Long restMoney) {
        this.restMoney = restMoney;
    }
}
