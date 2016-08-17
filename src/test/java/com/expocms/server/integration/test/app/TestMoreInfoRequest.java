package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;

import com.expocms.server.request.types.impl.MoreInfoRequest;

public class TestMoreInfoRequest extends InterfaceTestable{
	public static void main(String[] args) {
		
		MoreInfoRequest test = new MoreInfoRequest();
		
		String s = sendRequest("/app/moreInfo", test);
		
		System.out.println(s);
	}

}
