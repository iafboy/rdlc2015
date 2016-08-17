package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ManageMoneyProductRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestManageMoneyProduct extends InterfaceTestable{

    public static void main(String[] args){
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        url = "/app/manageMoneyProduct";
        
        ManageMoneyProductRequest test;
        
        
        test = new ManageMoneyProductRequest();
        sendRequest(url, test);
        
        test = new ManageMoneyProductRequest();
        test.setUserId(userId);
        sendRequest(url, test);
    }

}
