package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.GetPayVerifyCodeRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestGetPayVerifyCode extends InterfaceTestable {

    public static void main(String[] args){
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        GetPayVerifyCodeRequest test = null;
        
        
    	url = "/app/getPayVerifyCode";
    	
    	/*
    	test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
        test.setUserid(userId);
        sendRequest(url, test);
    	
    	test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
        test.setUserid(userId);
        test.setTestCode(GetPayVerifyCodeRequest.TEST_CODE);
        sendRequest(url, test);
    	
        test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setBankName("招商银行");
        test.setCardHolderId("130983199103283016");
        test.setCardHolderName("闫海通");
        test.setCardNo("6214830112910541");
        test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
        test.setUserid(userId);
        sendRequest(url, test);
        
        test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setBankName("招商银行");
        test.setCardHolderId("130983199103283016");
        test.setCardHolderName("闫海通");
        test.setCardNo("6214830112910541");
        test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
        test.setUserid(userId);
        test.setTestCode(GetPayVerifyCodeRequest.TEST_CODE);
        sendRequest(url, test);
        */
        
    	
    	/*
    	// for the 2nd payment ...
        test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setProductId("1224841f86894b6c8145cfd44ec1bb0a");
        test.setUserid(userId);
        test.setTestCode(GetPayVerifyCodeRequest.TEST_CODE);
        sendRequest(url, test);
        */
        
        
    	// for the 1st payment ...
        test = new GetPayVerifyCodeRequest();
        test.setAmount(1L * 100);
        test.setBankName("中国银行");
        test.setCardHolderId("31222718770122471X");
        test.setCardHolderName("闫海通");
        test.setCardNo("1214831234910541");
        test.setPayPhone("12345678901");
        test.setProductId("2ebd5036912b4510892597a46be8fa0a");
        test.setUserid(userId);
        test.setTestCode(GetPayVerifyCodeRequest.TEST_CODE);
        sendRequest(url, test);

    }
}
