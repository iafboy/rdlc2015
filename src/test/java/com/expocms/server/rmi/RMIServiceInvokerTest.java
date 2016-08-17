package com.expocms.server.rmi;

import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import little.ant.payment.pojo.Pay2BankEntity;
import little.ant.payment.pojo.QuickPayReqEntity;
import little.ant.payment.pojo.RedPackageEntity;
import little.ant.rmi.service.RMIServiceInvoker;
import little.ant.sms.bo.SMSMsgEntity;

public class RMIServiceInvokerTest {
	RMIServiceInvoker rmiInvoker;
	@Before
	public void setUp() throws Exception {
		rmiInvoker=new RMIServiceInvoker();
	}

	@After
	public void tearDown() throws Exception {
		rmiInvoker=null;
	}


	@Test
	public void testSendSMS() {
		SMSMsgEntity smsMsgEntity=new SMSMsgEntity();
		smsMsgEntity.setMessage("test");
		smsMsgEntity.setMsisdn("18501308512");
		if(!rmiInvoker.SendSMS(1, smsMsgEntity)){
			//fail("not OK to invoke");
		}
	}
	
	@Test
	public void testListRedPackage() {
		String userId = "27184beee1c24de89bc45e45737ec6b3";
		List<RedPackageEntity> redPackageList = rmiInvoker.listRedPackage(userId);
		if( redPackageList == null){
			System.out.println("no red package found");
		}
		else{
			System.out.println(JSON.toJSONString(redPackageList));
		}
	}
	
	@Test
	public void testApplyPackage() {
		String userId = "27184beee1c24de89bc45e45737ec6b3";
		String redPackageID = "30386ca749b2473cb08f9fd29883a89e";
		int flag = rmiInvoker.applyRedPackage(userId, redPackageID );
		System.out.println(flag);
	}
	
	@Test
	public void testRefound(){
		try {
			Pay2BankEntity p2aentity=new Pay2BankEntity("nj", "招商银行", "招商银行南京分行鼓楼支行", "张三", "6226584150965236", "1000.1", "fkdyh", null);
			rmiInvoker.refound(p2aentity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testPayment(){
		String cardNo_="4380880000000007";
		String storableCardNo_="4380880007";
		String expiredDate_="0911";
		String cvv2_="111";
		String amount_="1111111";
		String externalRefNumber_="";
		String customerId_="119999"; 
		String cardHolderName_="test";
		String cardHolderId_="32058219870706111X"; 
		String spFlag_="QuickPay";
		String idType_="0";
		String savePciFlag_="0";
		String phone_="15000258213";
		String payBatch_="1";
		String validCode_="";
		String token_="";
		QuickPayReqEntity qpr=new QuickPayReqEntity( cardNo_,  storableCardNo_,  expiredDate_,  cvv2_,  amount_,
				 externalRefNumber_,  customerId_,  cardHolderName_, cardHolderId_, spFlag_,
				 idType_, savePciFlag_, phone_, payBatch_, validCode_, token_);
		rmiInvoker.quickpay(qpr);
	}
	@Test
	public void testAutoOnboard(){
		String productType = "3";
		rmiInvoker.autoOnboard(productType);
	}
}
