package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SendBankInfoRequest;
import com.expocms.server.util.FileUtils;

public class TestSendBankInfo extends InterfaceTestable {
	
	public static void main(String[] args) {
		url = "/app/sendBankInfo";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        SendBankInfoRequest test;
        
//        test = new SendBankInfoRequest();
//        test.setUserId(null);
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId("");
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName(null);
//        test.setCardNo(null);
//        test.setCardHolderId(null);
//        test.setCardHolderName(null);
//        test.setPayPhone(null);
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("");
//        test.setCardNo("");
//        test.setCardHolderId("");
//        test.setCardHolderName("");
//        test.setPayPhone("");
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("");
//        test.setCardHolderId("");
//        test.setCardHolderName("");
//        test.setPayPhone("");
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("1234 5678 9012 3456 7890");
//        test.setCardHolderId("");
//        test.setCardHolderName("");
//        test.setPayPhone("");
//        sendRequest(url, test);
//     
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("1234 5678 9012 3456 7890");
//        test.setCardHolderId("123456789012345678");
//        test.setCardHolderName("");
//        test.setPayPhone("");
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("1234 5678 9012 3456 7890");
//        test.setCardHolderId("123456789012345678");
//        test.setCardHolderName("魏麟");
//        test.setPayPhone("");
//        sendRequest(url, test);
//        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("1234 5678 9012 3456 7890");
//        test.setCardHolderId("123456789012345678");
//        test.setCardHolderName("魏麟");
//        test.setPayPhone("15901308990");
//        sendRequest(url, test);
//        
        test = new SendBankInfoRequest();
        test.setUserId(userId);
        test.setBankName("招商银行");
        test.setCardNo("6225880116576878");
        test.setCardHolderId("220203198610016243");
        test.setCardHolderName("彭博");
        test.setPayPhone("13581861548");
        sendRequest(url, test);
        
        
//        test = new SendBankInfoRequest();
//        test.setUserId(userId);
//        test.setBankName("中国银行");
//        test.setCardNo("6013820100006058450");
//        test.setCardHolderId("31022619770122471X");
//        test.setCardHolderName("魏麟");
//        test.setPayPhone("15901308990");
//        sendRequest(url, test);
	}

}
