package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ProductIntroductionRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestProductIntroduction extends InterfaceTestable{


    public static void main(String[] args){
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        url = "/app/productIntroduction";
        ProductIntroductionRequest test = new ProductIntroductionRequest();
        test.setProductId("c4b92e2bf40640a59630f9fe38cb92bb");
        sendRequest(url, test);
    }
}
