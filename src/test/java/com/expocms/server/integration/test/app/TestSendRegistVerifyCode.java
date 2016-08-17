package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SendRegistVerifyCodeRequest;

public class TestSendRegistVerifyCode extends InterfaceTestable {
	
	public static void main(String[] args){
        url = "/app/sendRegistVerifyCode";
        
        SendRegistVerifyCodeRequest test = new SendRegistVerifyCodeRequest();
        test.setPhoneNo("15901308990");
        sendRequest(url, test);
    }

}
