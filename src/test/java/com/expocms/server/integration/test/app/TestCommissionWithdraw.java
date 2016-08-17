package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.CommissionWithdrawRequest;
import com.expocms.server.request.types.impl.WithdrawDetailRequest;
import com.expocms.server.util.FileUtils;

public class TestCommissionWithdraw extends InterfaceTestable {

	public static void main(String[] args) {
		String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
		String s = null;
		userId = "f0a22253db5847fd96ca1107f085dd4f";
		
		
		
		url = "/sms/commissionWithdraw";
		
		CommissionWithdrawRequest test1 = new CommissionWithdrawRequest();
		test1.setUserId(userId);
		test1.setAmount(1000L);
		test1.setArrivalAmount(800L);
		test1.setPoundageAmount(200L);
		s = sendRequest(url, test1);
		System.out.println(s);
		
		
		
		url = "/sms/withdrawDetail";
		
		WithdrawDetailRequest test3 = new WithdrawDetailRequest();
		test3.setUserId(userId);
		s = sendRequest(url, test3);
		System.out.println(s);

	}
	
}
