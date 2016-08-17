package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.VerifyTradingPwdRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestVerifyTradingPwd extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/verifyTradingPwd";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        VerifyTradingPwdRequest test;
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(null);
        test.setPwd("123456");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId("");
        test.setPwd("123456");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId("wrongUserId");
        test.setPwd("123456");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(userId);
        test.setPwd(null);
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(userId);
        test.setPwd("");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(userId);
        test.setPwd("123456");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(userId);
        test.setPwd("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        sendRequest(url, test);
        
        test = new VerifyTradingPwdRequest();
        test.setUserId(userId);
        test.setPwd("654321");
        sendRequest(url, test);
    }
}
