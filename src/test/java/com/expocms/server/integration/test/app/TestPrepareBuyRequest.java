package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.PrepareBuyRequest;
import com.expocms.server.util.FileUtils;

public class TestPrepareBuyRequest extends InterfaceTestable {
	
	public static void main(String[] args){
		url = "/app/prepareBuy";
		
		String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }

		PrepareBuyRequest test = new PrepareBuyRequest();

//		test.setUserId(null);
//		sendRequest(url, test);
//
//		test.setUserId("");
//		sendRequest(url, test);
//
//		test.setUserId("wrongUserId");
//		sendRequest(url, test);
		
//		test.setUserId("e1ec0f3626a743fc96478e1833b81e31");
//		sendRequest(url, test);
		
		test.setUserId(userId);
		sendRequest(url, test);
	}
}
