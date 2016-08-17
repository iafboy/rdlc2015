package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.GetPayVerifyCodeRequest;
import com.expocms.server.request.types.impl.QuickPaymentRequest;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.util.FileUtils;

public class TestBuy extends InterfaceTestable {
	
	public static void main(String[] args){
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\test\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        String productId = FileUtils.readFromFile("D:\\works\\rdlc2015\\test\\productId.txt");
        if(productId == null) {
        	System.out.println("no product!");
        	return;
        }
        
        while(true) {
        
	        GetPayVerifyCodeRequest test = null;
	        QuickPaymentRequest test2 = null;
	        
	        
	    	url = "/app/getPayVerifyCode";
	    	
	    	// for the 1st payment ...
	        test = new GetPayVerifyCodeRequest();
	        test.setAmount(10L * 100);
	        test.setBankName("中国银行");
	        test.setCardHolderId("123456789012345678");
	        test.setCardHolderId("123456789012345679");
	        test.setCardHolderName("闫海通");
	        test.setCardNo("1214831234910541");
	        test.setPayPhone("12345678901");
	        test.setProductId(productId);
	        test.setUserid(userId);
	        test.setTestCode(GetPayVerifyCodeRequest.TEST_CODE);
	        sendRequest(url, test);
	        
	        
	        url = "/app/setVerifyCode";
	        
	        SetVerifyCodeRequest test1 = new SetVerifyCodeRequest();
	        test1.setPhoneNo("15901308991");
	        test1.setVerifyCode("123456");
	        sendRequest(url, test1);
	        
	        
	        url = "/app/quickPayment";
	        
	        test2 = new QuickPaymentRequest();
	        test2.setUserId(userId);
	        test2.setProductId(productId);
	        test2.setOrderId(ToolUtils.getUuidByJdk(true));
	        test2.setInvestmentAmount(10L * 100);
	        test2.setVerifyCode("123456");
	        test2.setTestCode(QuickPaymentRequest.TEST_CODE);
	        sendRequest(url, test2);
	        
	        
	        try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
        }

    }

}
