package com.expocms.server.integration.test.app;

import java.util.ArrayList;
import java.util.List;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.QuickPaymentRequest;
import com.expocms.server.request.types.impl.SetVerifyCodeRequest;
import com.expocms.server.response.types.impl.QuickPaymentActivityGift;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.util.FileUtils;

public class TestQuickPayment extends InterfaceTestable {
	 public static void main(String[] args){
	        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
	        if(userId == null) {
	        	System.out.println("please login first!");
	        	return;
	        }
	        
	        
	        url = "/app/setVerifyCode";
	        
	        SetVerifyCodeRequest test1 = new SetVerifyCodeRequest();
	        test1.setPhoneNo("15901308991");
	        test1.setVerifyCode("123456");
	        sendRequest(url, test1);
	        
	        
	        url = "/app/quickPayment";
	        
	        QuickPaymentRequest test = null;
	        List<QuickPaymentActivityGift> redPackages = null;
	        QuickPaymentActivityGift gift = null;
	        
	        
	        /*
	        test = new QuickPaymentRequest();
	        test.setUserId(userId);
	        test.setProductId("1a0e670510f64634998aeea601a86833");
	        test.setOrderId(ToolUtils.getUuidByJdk(true));
	        //List<String> rdList = Lists.newArrayList();
//	        test.setRedPackageMark(rdList);
	        
	        redPackages = new ArrayList<QuickPaymentActivityGift>();
	        gift = new QuickPaymentActivityGift();
	        gift.setRedPackageCode("dFruFqb9");
	        gift.setRestMoney(1000L);
	        redPackages.add(gift);

	        test.setRedPackages(redPackages);
	        test.setInvestmentAmount(3000L);
	        test.setVerifyCode("123456");
	        sendRequest(url, test);
	        */
	        
	        
	        test = new QuickPaymentRequest();
	        test.setUserId(userId);
	        //test.setProductId("1a0e670510f64634998aeea601a86833");
	        test.setProductId("00c499c1bafe41ba8ad0b88269561ad");
	        test.setOrderId(ToolUtils.getUuidByJdk(true));
	        //List<String> rdList = Lists.newArrayList();
//	        test.setRedPackageMark(rdList);
	        
//	        redPackages = new ArrayList<QuickPaymentActivityGift>();
//	        gift = new QuickPaymentActivityGift();
//	        gift.setRedPackageCode("KHA1p7mI");
//	        gift.setRestMoney(9L * 100);
//	        redPackages.add(gift);
//	        gift = new QuickPaymentActivityGift();
//	        gift.setRedPackageCode("CFdxk11m");
//	        gift.setRestMoney(15L * 100);
//	        redPackages.add(gift);
//	        gift = new QuickPaymentActivityGift();
//	        gift.setRedPackageCode("TyVDTxtp");
//	        gift.setRestMoney(16L * 100);
//	        redPackages.add(gift);

	        test.setRedPackages(redPackages);
	        test.setInvestmentAmount(90L * 100);
	        test.setVerifyCode("123456");
	        test.setTestCode(QuickPaymentRequest.TEST_CODE);
	        sendRequest(url, test);
	    }
}
