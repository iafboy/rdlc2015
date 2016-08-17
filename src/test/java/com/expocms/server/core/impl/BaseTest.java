package com.expocms.server.core.impl;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	protected ApplicationContext ac;
	@Before
	public void setUp() throws Exception {
		System.setProperty("simpleAppSrv.cachespace", "D:\\work\\workspace\\rdlc\\SimpleAppSrv_SVN\\cache");
		ac = new ClassPathXmlApplicationContext("simpleAppSrv.xml");
	}

	@After
	public void tearDown() throws Exception {
	}
}
