package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.RegistRequest;
import com.expocms.server.request.types.impl.SendRegistVerifyCodeRequest;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestRegist extends InterfaceTestable{

    public static void main(String[] args){
//    	url = "/app/sendRegistVerifyCode";
//
//        SendRegistVerifyCodeRequest test3 = new SendRegistVerifyCodeRequest();
//        test3.setPhoneNo("13520323928");
//        sendRequest(url, test3);
    	
    	
    	
    	url = "/app/setVerifyCode";

        SetVerifyCodeRequest test1 = new SetVerifyCodeRequest();
        test1.setPhoneNo("15901308995");
        test1.setVerifyCode("123456");
        sendRequest(url, test1);
        
        
        url = "/app/regist";
        
        RegistRequest test = new RegistRequest();
        test.setPhoneNo("15901308995");
        test.setPassword("111111");
        test.setVerifyCode("123456");
        test.setRecommendCode("15901308990");
        sendRequest(url, test);
    }
}
