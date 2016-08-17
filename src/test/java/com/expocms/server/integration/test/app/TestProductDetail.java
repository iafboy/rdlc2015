package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.ProductDetailsRequest;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestProductDetail extends InterfaceTestable{


    public static void main(String[] args){
        url = "/app/productDetails";
        
        ProductDetailsRequest test = null;
        
        test = new ProductDetailsRequest();
        test.setProductId(null);
        sendRequest(url, test);
        
        test = new ProductDetailsRequest();
        test.setProductId("");
        sendRequest(url, test);
        
        test = new ProductDetailsRequest();
        test.setProductId("wrongProductId");
        sendRequest(url, test);
        
        test = new ProductDetailsRequest();
        test.setProductId("c4b92e2bf40640a59630f9fe38cb92bb");
        sendRequest(url, test);
    }
}
