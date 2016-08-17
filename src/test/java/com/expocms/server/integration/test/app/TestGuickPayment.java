package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.GetPayVerifyCodeRequest;
import com.expocms.server.request.types.impl.QuickPaymentRequest;
import com.expocms.server.response.types.impl.QuickPaymentActivityGift;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestGuickPayment extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/quickPayment";
        QuickPaymentRequest test = new QuickPaymentRequest();
        test.setOrderId("215200006888888967ad88789066678");
        test.setUserId("5b182605c062439bb1800473dab4caa5");
        test.setVerifyCode("0009");
        test.setProductId("c283fa5b6a084369a11c8c322c2e5c1a");
        test.setInvestmentAmount(20000l);
        List<QuickPaymentActivityGift> list = new ArrayList<QuickPaymentActivityGift>();
        QuickPaymentActivityGift gift = new QuickPaymentActivityGift();
        gift.setRestMoney(11850l);
        gift.setRedPackageCode("ijksdf7K");
        list.add(gift);
        gift = new QuickPaymentActivityGift();
        gift.setRestMoney(1895l);
        gift.setRedPackageCode("iw5gTEgF");
        list.add(gift);
        test.setRedPackages(list);
        sendRequest(url, test);
    }
}
