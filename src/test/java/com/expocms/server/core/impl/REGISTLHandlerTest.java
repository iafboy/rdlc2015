package com.expocms.server.core.impl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.expocms.server.db.DBOperatorService;
import com.expocms.server.request.types.impl.RegistRequest;

public class REGISTLHandlerTest extends BaseTest{
	REGISTHandler handler;
	
	@Test
	public void testHandleRequest() {
		handler=(REGISTHandler) ac.getBean("REGISTLHandler");
		RegistRequest request=new RegistRequest();
		request.setPassword("87654321");
		request.setPhoneNo("testPhoneNumber");
		handler.handleRequest(request);
	}

}
