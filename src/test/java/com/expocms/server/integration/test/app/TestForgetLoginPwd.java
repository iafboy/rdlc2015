package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ForgetLoginPwdRequest;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestForgetLoginPwd extends InterfaceTestable{

    public static void main(String[] args){
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        String s;
        
        
        url = "/app/setVerifyCode";
        
        SetVerifyCodeRequest test1 = new SetVerifyCodeRequest();
        test1.setPhoneNo("15901308999");
        test1.setVerifyCode("123456");
        s = sendRequest(url, test1);
        
        System.out.println(s);
        
        
        url = "/app/forgetLoginPwd";
        
        ForgetLoginPwdRequest test2 = null;
        
        test2 = new ForgetLoginPwdRequest();
        test2.setMobile("15901308999");
        //test2.setIdCard("123456789012345678");
        test2.setVerifyCode("123456");
        test2.setResetPwd("111111");
        s = sendRequest(url, test2);
        
        System.out.println(s);
    }
}
