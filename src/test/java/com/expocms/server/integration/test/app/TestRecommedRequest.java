package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;

import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.util.FileUtils;

public class TestRecommedRequest extends InterfaceTestable {
	public static void main(String[] args) {
		url = "/app/recommend";
		
		String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        RecommendRequest test = null;
		
		test = new RecommendRequest();
		sendRequest(url,test);
		
		test = new RecommendRequest();
		test.setUserId(userId);
		sendRequest(url,test);
	}

}
