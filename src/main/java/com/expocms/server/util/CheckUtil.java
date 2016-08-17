package com.expocms.server.util;

public class CheckUtil {
	public static boolean checkUnEmpty(Object par){
		if(par == null){
			return false;
		}
		
		if(par instanceof String){
			if("".equals(par)){
				return false;
			}
		}
		
		if(par instanceof Integer){
			if((Integer)par == 0){
				return false;
			}
		}
		
		if(par instanceof Double){
			if((Math.abs((Double)par - 0) )< 0.0001){
				return false;
			}
		}
		return true;
	}
}
