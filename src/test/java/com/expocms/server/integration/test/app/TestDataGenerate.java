package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.DataGenerateRequest;
import com.expocms.server.util.FileUtils;
import com.google.common.collect.Lists;

import java.util.List;

public class TestDataGenerate extends InterfaceTestable {
	public static void main(String[] args){
	    url = "/app/dataGenerate";
	     
	    String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
	       	System.out.println("please login first!");
	       	return;
	    }

		for(int i = 0; i < 1; i ++) {
			DataGenerateRequest  test = new DataGenerateRequest();
			test.setUserId(userId);
			test.setOrderId("uselessId");
			test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
			
			List<String> rdList = Lists.newArrayList();
			test.setRedPackageMark(rdList);
			test.setInvestmentAmount(100L);
			
			sendRequest(url, test);
		}
	}
}
