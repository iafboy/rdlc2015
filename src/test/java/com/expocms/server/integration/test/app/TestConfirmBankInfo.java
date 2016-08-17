package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ConfirmBankInfoRequest;
import com.expocms.server.util.FileUtils;

public class TestConfirmBankInfo extends InterfaceTestable {

	public static void main(String[] args) {
		url = "/app/confirmBankInfo";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        ConfirmBankInfoRequest test;
        
        
        
        test = new ConfirmBankInfoRequest();
        test.setUserId(userId);
        test.setValidCode("929668");
        sendRequest(url, test);
	}

}
