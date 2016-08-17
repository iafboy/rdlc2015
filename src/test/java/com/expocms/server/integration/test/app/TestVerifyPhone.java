package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import com.expocms.server.util.FileUtils;

public class TestVerifyPhone extends InterfaceTestable {

	public static void main(String[] args) {
		url = "/app/verifyPhone";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        VerifyPhoneRequest test;
        
        test = new VerifyPhoneRequest();
        test.setPhoneNo(null);
        sendRequest(url, test);
        
        test = new VerifyPhoneRequest();
        test.setPhoneNo("");
        sendRequest(url, test);
        
        test = new VerifyPhoneRequest();
        test.setPhoneNo("wrongPhoneNo");
        sendRequest(url, test);
        
        test = new VerifyPhoneRequest();
        test.setPhoneNo("15901308990");
        sendRequest(url, test);
	}

}
