package com.expocms.server.core.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.expocms.server.request.types.impl.MoreInfoRequest;
import com.expocms.server.response.types.impl.MoreInfoResponse;

import junit.framework.Assert;

public class MOREINFOHandlerTest extends BaseTest{
	MOREINFOHandler handler;

	@Test
	public void testHandleRequest() {
		handler=(MOREINFOHandler) ac.getBean("MOREINFOHandler");
		MoreInfoRequest request=new MoreInfoRequest();
		MoreInfoResponse response=(MoreInfoResponse) handler.handleRequest(request);
		Assert.assertEquals("www.roadoor.com", response.getGuanwangURL());
		Assert.assertEquals(0, response.getHelpCentre().size());
	}

}
