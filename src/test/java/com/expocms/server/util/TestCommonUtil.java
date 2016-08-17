package com.expocms.server.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestCommonUtil {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void test() {
		TestObject1 testObject1 = new TestObject1();
    	TestObject2 testObject2 = new TestObject2();
    	testObject1.setData1("data1");
    	testObject1.setData2("data2");
        System.out.println ("data1 = " + testObject1.getData1() + " : " + testObject1.getData2());
        System.out.println ("data2 = " + testObject2.getData1() + " : " + testObject2.getData2());
        CommonUtil.getObject(testObject1, testObject2);
        System.out.println ("data2 = " + testObject2.getData1() + " : " + testObject2.getData2());
	}
	
    private class TestObject1 {
    	private String data1;
    	private String data2;
		public String getData1() {
			return data1;
		}
		public void setData1(String data1) {
			this.data1 = data1;
		}
		public String getData2() {
			return data2;
		}
		public void setData2(String data2) {
			this.data2 = data2;
		}
    }

	private class TestObject2 {
    	private String data1;
    	private String data2;
		public String getData1() {
			return data1;
		}
		public void setData1(String data1) {
			this.data1 = data1;
		}
		public String getData2() {
			return data2;
		}
		public void setData2(String data2) {
			this.data2 = data2;
		}
    }

}
