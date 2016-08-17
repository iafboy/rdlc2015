package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SetTradingPwdBoolRequest;
import com.expocms.server.util.FileUtils;

public class TestSetTradingPwdBool extends InterfaceTestable {
	
	public static void main(String[] args) {
		url = "/app/setTradingPwdBool";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        SetTradingPwdBoolRequest test;
        
        test = new SetTradingPwdBoolRequest();
        test.setUserId(null);
        sendRequest(url, test);
        
        test = new SetTradingPwdBoolRequest();
        test.setUserId("");
        sendRequest(url, test);
        
        test = new SetTradingPwdBoolRequest();
        test.setUserId("wrongUserId");
        sendRequest(url, test);
        
        test = new SetTradingPwdBoolRequest();
        test.setUserId(userId);
        sendRequest(url, test);
	}
	
}
