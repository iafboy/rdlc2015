package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.RedPackageIFXRequest;

public class TestRedPackageIFX extends InterfaceTestable {

	public static void main(String[] args) {
		url = "/app/redPackageIFX";
		
		RedPackageIFXRequest test = new RedPackageIFXRequest();
		test.setValue(70);
		sendRequest(url, test);
	}

}
