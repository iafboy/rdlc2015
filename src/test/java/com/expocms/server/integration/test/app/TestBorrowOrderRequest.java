package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;

import com.expocms.server.request.types.impl.BorrowOrderRequest;
import com.expocms.server.util.FileUtils;

public class TestBorrowOrderRequest extends InterfaceTestable{
	public static void main(String[] args) {
		String url = "/app/borrowOrder";
		
		String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
		
		BorrowOrderRequest test = null;
		
		test = new BorrowOrderRequest();
		test.setProductId(null);
		test.setUserId(null);
		sendRequest(url, test);
		
		test = new BorrowOrderRequest();
		test.setProductId("");
		test.setUserId("");
		sendRequest(url, test);
		
		test = new BorrowOrderRequest();
		test.setProductId("wrongProductId");
		test.setUserId("wrongUserId");
		sendRequest(url, test);
		
		test = new BorrowOrderRequest();
		test.setProductId("wrongProductId");
		test.setUserId(userId);
		sendRequest(url, test);
		
		test = new BorrowOrderRequest();
		test.setProductId("1a0e670510f64634998aeea601a86833");
		test.setUserId("wrongUserId");
		sendRequest(url, test);
		
		test = new BorrowOrderRequest();
		test.setProductId("1a0e670510f64634998aeea601a86833");
		test.setUserId(userId);
		sendRequest(url, test);

		test = new BorrowOrderRequest();
		test.setOrderId("0db319f27414425495ccd057ff48f897");
		test.setProductId("2ebd5036912b4510892597a46be8fa0a");
		test.setUserId("f89941c6af9946c0a533da74c29da252");
		sendRequest(url, test);
	}
}
