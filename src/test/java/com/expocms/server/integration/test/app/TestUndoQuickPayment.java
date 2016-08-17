package com.expocms.server.integration.test.app;

import com.expocms.server.db.model.RdOrder;
import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.UndoQuickPaymentRequest;

public class TestUndoQuickPayment extends InterfaceTestable {
	
	public static void main(String[] args) {
		
		url = "/app/undoQuickPayment";
		
		UndoQuickPaymentRequest test = new UndoQuickPaymentRequest();
		
		test.setOrderId("b59424d7f2d44ab9994cd16813232a47");
		//test.setOrderSuccess(1);
		//test.setOrderState(RdOrder.ORDER_STATUS_SEALED);
		sendRequest(url, test);
		
	}

}
