package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ProductListRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestProductList extends InterfaceTestable{

    public static final String TYPE_alreadyRepayment = "alreadyRepayment";
    public static final String TYPE_alreadySellOut = "alreadySellOut";


    public static void main(String[] args){
    	String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        
        url = "/app/productList";
        
        ProductListRequest test = null;
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setProductType(null);
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setProductType("");
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setProductType("wrongProductType");
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setProductType(TYPE_alreadyRepayment);
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setProductType(TYPE_alreadySellOut);
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        sendRequest(url, test);
        
        test = new ProductListRequest();
        test.setPage(0);
        test.setPageCount(10);
        test.setUserId(userId);
        sendRequest(url, test);
    }
}
