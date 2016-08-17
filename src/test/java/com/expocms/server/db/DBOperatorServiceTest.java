package com.expocms.server.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.exceptions.CmsException;
import com.expocms.server.request.types.impl.BuyProductRequest;

public class DBOperatorServiceTest {

	private DBOperatorService dboper;

	@Before
	public void before() throws Exception {
		System.setProperty("simpleAppSrv.cachespace", "D:\\work\\workspace\\rdlc\\SimpleAppSrv_SVN\\cache");
		ApplicationContext ac = new ClassPathXmlApplicationContext("simpleAppSrv.xml");
		dboper = (DBOperatorService) ac.getBean("dboperatorservice");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckPaymentRecord() {

	}

	@Test
	public void testBuyProduct() {

	}

	@Test
	public void testForgetLoginPwd() {

	}

	@Test
	public void testForgetTradingPwd() {
	}

	@Test
	public void testRegist() {

	}

	@Test
	public void testModifyTradingPwd() {
	}

	@Test
	public void testModifyLoginPwd() {
	}

	@Test
	public void testModifyBankInfo() {
	}

	@Test
	public void testLogin() {
		RdAppuser rdAppUser = dboper.getAppUser("123456789", "");
		Assert.assertEquals(rdAppUser.getIdcard(), "7766772838428340843");

	}

}
