package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestForgetTradingPwd extends InterfaceTestable{

    public static void main(String[] args){
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        ForgetTradingPwdRequest test = null;
        SetVerifyCodeRequest test1 = null;
        
        url = "/app/forgetTradingPwd";
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(null);
        test.setBankCard("6214830106538837");
        test.setIdCard("11022319890430497X");
        test.setVerifyCode("12345");
        sendRequest(url, test);
        
        test = new ForgetTradingPwdRequest();
        test.setUserId("");
        test.setBankCard("6214830106538837");
        test.setIdCard("11022319890430497X");
        test.setVerifyCode("12345");
        sendRequest(url, test);
        
        test = new ForgetTradingPwdRequest();
        test.setUserId("wrongUserId");
        test.setBankCard("6214830106538837");
        test.setIdCard("11022319890430497X");
        test.setVerifyCode("12345");
        sendRequest(url, test);
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(userId);
        test.setBankCard("wrongBankCard");
        test.setIdCard("11022319890430497X");
        test.setVerifyCode("12345");
        sendRequest(url, test);
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(userId);
        test.setBankCard("6214830106538837");
        test.setIdCard("wrongIdCard");
        test.setVerifyCode("12345");
        sendRequest(url, test);
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(userId);
        test.setBankCard("1234 5678 9012 3456 7890");
        test.setIdCard("123456789012345678");
        test.setVerifyCode("wrongVerifyCode");
        sendRequest(url, test);
        
        url = "/app/setVerifyCode";
        
        test1 = new SetVerifyCodeRequest();
        test1.setPhoneNo("15901308990");
        test1.setVerifyCode("123456");
        sendRequest(url, test1);
        
        url = "/app/forgetTradingPwd";
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(userId);
        test.setBankCard("1234 5678 9012 3456 7890");
        test.setIdCard("123456789012345678");
        test.setVerifyCode("123456");
        sendRequest(url, test);
        
        url = "/app/setVerifyCode";
        
        test1 = new SetVerifyCodeRequest();
        test1.setPhoneNo("15901308990");
        test1.setVerifyCode("123456");
        sendRequest(url, test1);
        
        url = "/app/forgetTradingPwd";
        
        test = new ForgetTradingPwdRequest();
        test.setUserId(userId);
        test.setBankCard("1234 5678 9012 3456 7890");
        test.setIdCard("123456789012345678");
        test.setVerifyCode("123456");
        test.setPwd("newPwd123");
        sendRequest(url, test);
    }
}
