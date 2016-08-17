package com.expocms.server.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.expocms.server.core.impl.BaseTest;
import com.expocms.server.db.dao.RdDictMapper;

public class TestRdDictMapper extends BaseTest {
	RdDictMapper rdDictMapper;
	@Test
	public void test() {
		rdDictMapper=(RdDictMapper) ac.getBean("RdDictMapper");
		String value=rdDictMapper.getValueFromDict("key");
		Assert.assertEquals("test", value);
	}

}
