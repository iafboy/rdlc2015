package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.impl.ModifyLoginPwdRequest;
import com.expocms.server.request.types.impl.ModifyTradingPwdRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestModifyTradingPwd extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/modifyTradingPwd";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        ModifyTradingPwdRequest test;
        
        test = new ModifyTradingPwdRequest();
        test.setUserId("");
        test.setTransactionPwd("654321");
        sendRequest(url, test);
        
        test = new ModifyTradingPwdRequest();
        test.setUserId(userId + "wrongId");
        test.setTransactionPwd("654321");
        sendRequest(url, test);
        
        test = new ModifyTradingPwdRequest();
        test.setUserId(userId);
        test.setTransactionPwd("");
        sendRequest(url, test);
        
        test = new ModifyTradingPwdRequest();
        test.setUserId(userId);
        test.setTransactionPwd("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        sendRequest(url, test);
        
        test = new ModifyTradingPwdRequest();
        test.setUserId(userId);
        test.setTransactionPwd("654321");
        sendRequest(url, test);
    }
}
