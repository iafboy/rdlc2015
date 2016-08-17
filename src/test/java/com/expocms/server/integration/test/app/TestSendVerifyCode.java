package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SendVerifyCodeRequest;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestSendVerifyCode extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/sendVerifyCode";
        
        SendVerifyCodeRequest test = new SendVerifyCodeRequest();
        test.setPhoneNo("15901308990");
        sendRequest(url, test);
    }
    
}
