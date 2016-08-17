package com.expocms.server.constant;

import java.util.Map;

import com.google.common.collect.Maps;

public final class BankConstants {
	public final static Map<String, String[]> bankParam;
	static{
		bankParam=Maps.newHashMap();
		//工商银行
		String[] ICBC={"200000","95588"};
		bankParam.put("工商银行", ICBC);
		//农业银行
		String[] ABC={"200000","95588"};
		bankParam.put("农业银行", ABC);
		//中国银行
		String[] BOC={"200000","95566"};
		bankParam.put("中国银行", BOC);
		//建设银行 
		String[] CCB={"200000","95533"};
		bankParam.put("建设银行 ", CCB);
		//招商银行 
		String[] CMB={"200000","95555"};
		bankParam.put("招商银行 ", CMB);
		//交通银行 
		String[] BCM={"200000","95559"};
		bankParam.put("交通银行", BCM);
		//中信银行 
		String[] CITIC={"200000","95558"};
		bankParam.put("中信银行", CITIC);
		//浦发银行 
		String[] SPDB={"200000","95528"};
		bankParam.put("浦发银行", SPDB);
		//兴业银行 
		String[] CIB={"200000","95561"};
		bankParam.put("兴业银行", CIB);
		//广发银行 
		String[] GDB={"200000","4008308003"};
		bankParam.put("广发银行", GDB);
		//平安银行 
		String[] PAB={"200000","95511"};
		bankParam.put("平安银行", PAB);
		//华夏银行 
		String[] HXB={"200000","95577"};
		bankParam.put("华夏银行", HXB);
		//光大银行 
		String[] CEB={"200000","95595"};
		bankParam.put("光大银行", CEB);
		//民生银行 
		String[] CMBC={"200000","95568"};
		bankParam.put("民生银行 ", CMBC);
	}
}
