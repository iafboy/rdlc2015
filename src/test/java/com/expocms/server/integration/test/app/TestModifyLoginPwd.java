package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ForgetLoginPwdRequest;
import com.expocms.server.request.types.impl.ModifyLoginPwdRequest;
import com.expocms.server.request.types.impl.ModifyTradingPwdRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestModifyLoginPwd extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/modifyLoginPwd";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        ModifyLoginPwdRequest test;
        
        test = new ModifyLoginPwdRequest();
        test.setUserId("");
        test.setNewPwd("654321");
        test.setOldPwd("123456");
        sendRequest(url, test);
        
        test = new ModifyLoginPwdRequest();
        test.setUserId(userId + "wrongId");
        test.setNewPwd("654321");
        test.setOldPwd("123456");
        sendRequest(url, test);
        
        test = new ModifyLoginPwdRequest();
        test.setUserId(userId);
        test.setNewPwd("654321");
        test.setOldPwd("wrongP");
        sendRequest(url, test);
        
        test = new ModifyLoginPwdRequest();
        test.setUserId(userId);
        test.setNewPwd("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        test.setOldPwd("123456");
        sendRequest(url, test);
        
        test = new ModifyLoginPwdRequest();
        test.setUserId(userId);
        test.setNewPwd("654321");
        test.setOldPwd("123456");
        sendRequest(url, test);
    }
}
