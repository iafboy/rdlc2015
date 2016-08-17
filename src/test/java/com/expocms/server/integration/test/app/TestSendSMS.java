package com.expocms.server.integration.test.app;

import java.util.ArrayList;
import java.util.List;

import com.expocms.server.integration.test.InterfaceTestable;
import com.payment.server.agent.command.params.KQ_QueryRepaymentInfo;
import com.sms.server.agent.command.params.SMSBCInfo;

public class TestSendSMS extends InterfaceTestable {

	public static void main(String[] args) {
		
		String s = null;
		
		
//		KQ_QueryRepaymentInfo testQ = new KQ_QueryRepaymentInfo();
//		testQ.setDealBeginDate("2015-11-01 00:00:00");
//		testQ.setDealEndDate("2015-11-30 23:59:59");
//		url = "/payment/KQ_QueryRepayment";
//		s = sendRequest(url, testQ);
//		System.out.println("s = " + s);
		
		
		
		/*
		KQ_GetVerifyCodeInfo testVC = new KQ_GetVerifyCodeInfo();
		testVC.setBankInfo(new KQ_BankInfo());
		testVC.setUserId("05aa7e6761234a9fa0c8247790f8ca2b");
		testVC.setExternalRefNo("15aa7e676e784a9fa0c9247790f8c890");
		testVC.setCustomerId("05aa7e6761234a9fa0c8247790f8ca2b");
		testVC.setAmount("1.00");
		testVC.setIdType("0");
		//testVC.getBankInfo().setBankName("中国银行");
		//testVC.getBankInfo().setBankCardNo("6013820100006058450");
		//testVC.getBankInfo().setBankName("中国交通银行");
		//testVC.getBankInfo().setBankCardNo("6222600910039025422");
//		testVC.getBankInfo().setBankName("交通银行");
//		testVC.getBankInfo().setBankCardNo("6222600910061218762");
//		testVC.getBankInfo().setBankCardHolderName("魏麟");
//		testVC.getBankInfo().setBankCardHolderId("31022619770122471X");
//		testVC.getBankInfo().setPayPhone("15901308990");
//		testVC.getBankInfo().setBankName("交通银行");
//		testVC.getBankInfo().setBankCardNo("6222600910050245081");
//		testVC.getBankInfo().setBankCardHolderName("王翠馨");
//		testVC.getBankInfo().setBankCardHolderId("110224197708181820");
//		testVC.getBankInfo().setPayPhone("13520323928");
		testVC.getBankInfo().setBankName("招商银行");
		testVC.getBankInfo().setBankCardNo("6225880116576878");
		testVC.getBankInfo().setBankCardHolderName("彭博");
		testVC.getBankInfo().setBankCardHolderId("220203198610016244");
		testVC.getBankInfo().setPayPhone("13581861548");
		url = "/payment/KQ_GetVerifyCode";
		s = sendRequest(url, testVC);
		System.out.println("s = " + s);
		*/
		
		
		/*
		KQ_ResetBankInfo testRS = new KQ_ResetBankInfo();
		testRS.setBankInfo(new KQ_BankInfo());
		testRS.setUserId("05aa7e676e784a9fa0c8247790f8ca2b");
		testRS.setExternalRefNo("15aa7e676e784a9fa0c9247790f8ca2b");
		testRS.setCustomerId("05aa7e676e784a9fa0c8247790f8ca2b");
		testRS.setIdType("0");
		testRS.setStorableCardNo("0061218762");
		testRS.setBankId("BCOM");
		//testVC.getBankInfo().setBankName("中国银行");
		//testVC.getBankInfo().setBankCardNo("6013820100006058450");
		//testVC.getBankInfo().setBankName("中国交通银行");
		//testVC.getBankInfo().setBankCardNo("6222600910039025422");
		testRS.getBankInfo().setBankName("中国交通银行");
		testRS.getBankInfo().setBankCardNo("6222600910061218762");
		testRS.getBankInfo().setBankCardHolderName("魏麟");
		testRS.getBankInfo().setBankCardHolderId("31022619770122471X");
		testRS.getBankInfo().setPayPhone("15901308990");
		url = "/payment/KQ_ResetBank";
		s = sendRequest(url, testRS);
		System.out.println("s = " + s);
		*/
		
		
	
		/*
		url = "/sms/sendTriggerSMS";
		
		SMSInfo testSMS = new SMSInfo();
        testSMS.setPhoneNo("13520323928");
        testSMS.setSmsContent("123456（融道验证码），有效期5分钟，千万不要告诉别人哦！你正在使用融道登录。【融道理财】");
        sendRequest(url, testSMS);
        */
        
        url = "/sms/sendBroadcastSMS";
        
        SMSBCInfo testBCSMS = new SMSBCInfo();
        List<String> phoneNoList = new ArrayList<String>();
        phoneNoList.add("15901308990");
        phoneNoList.add("13520323928");
        testBCSMS.setPhoneNoList(phoneNoList);
        testBCSMS.setSmsContent("亲爱的融道用户，盛夏7月，爆款也疯狂，利率最高提升0.9%。挑战融道最强打闹，赢百元话费。7.15日不见不散。退订回复TD!【融道理财】");
        sendRequest(url, testBCSMS);
        
	}

}
