package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.RedPackageExchangeRequest;
import com.expocms.server.util.FileUtils;

/**
 *
 * Created by Administrator on 2015/9/19.
 */
public class TestRedPackageExchange extends InterfaceTestable{

    public static void main(String[] args){
    	
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        url = "/app/redPackageExchange";
        
        
        RedPackageExchangeRequest test = new RedPackageExchangeRequest();
        test.setUserId(userId);
        test.setRedPackage("3sEB6asd");
        sendRequest(url, test);
    }
}
