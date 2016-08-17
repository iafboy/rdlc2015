package com.expocms.server.cache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Assert;

public class AuthCodeCacheSerivceTest {
	AuthCodeCacheSerivce instance;

	@Before
	public void setUp() throws Exception {
		System.setProperty("simpleAppSrv.cachespace", "D:\\work\\workspace\\rdlc\\SimpleAppSrv_SVN\\cache");
		ApplicationContext ac = new ClassPathXmlApplicationContext("simpleAppSrv.xml");
		instance = (AuthCodeCacheSerivce) ac.getBean("authCodeCacheService");
	}

	@After
	public void tearDown() throws Exception {
		if (instance != null)
			instance.removeAll();
	}

	@Test
	public void testGetAuthCode() {
		instance.update("test", "value");
		Assert.assertEquals("value", instance.getAuthCode("test"));
	}

}
