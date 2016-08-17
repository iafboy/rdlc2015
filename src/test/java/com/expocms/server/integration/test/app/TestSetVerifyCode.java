package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;

public class TestSetVerifyCode extends InterfaceTestable{


    public static void main(String[] args){
        url = "/app/setVerifyCode";
        
        SetVerifyCodeRequest test = new SetVerifyCodeRequest();
        test.setPhoneNo("15901308990");
        test.setVerifyCode("123456");
        String s = sendRequest(url, test);
        
        System.out.println(s);
    }
    
}
