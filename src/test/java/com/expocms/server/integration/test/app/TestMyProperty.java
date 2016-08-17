package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.MyPropertyRequest;
import com.expocms.server.request.types.impl.ProductDetailsRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestMyProperty extends InterfaceTestable{


    public static void main(String[] args){
        url = "/app/myProperty";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        MyPropertyRequest test = new MyPropertyRequest();
        
        test.setUserId(userId);
        //test.setUserId("9425bcfe499d414989170c51fc2411fa");
        String s = sendRequest(url, test);
        
        System.out.println(s);
    }
}
