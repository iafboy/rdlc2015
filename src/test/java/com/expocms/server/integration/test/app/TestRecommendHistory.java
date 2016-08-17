package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.CommissionHistoryRequest;
import com.expocms.server.request.types.impl.RecommendHistoryRequest;
import com.expocms.server.util.FileUtils;

public class TestRecommendHistory extends InterfaceTestable {

	public static void main(String[] args) {
		String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
		String s = null;
		userId = "f0a22253db5847fd96ca1107f085dd4f";
		
		
		url = "/sms/recommendHistory";
		
		RecommendHistoryRequest test1 = new RecommendHistoryRequest();
		test1.setUserId(userId);
		s = sendRequest(url, test1);
		System.out.println(s);
		
		
		
		url = "/sms/commissionHistory";
		
		CommissionHistoryRequest test2 = new CommissionHistoryRequest();
		test2.setUserId(userId);
		s = sendRequest(url, test2);
		System.out.println(s);
		
		
	}

}
