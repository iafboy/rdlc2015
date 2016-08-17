package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.SharePortRequest;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestSharePort extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/sharePort";
        
        SharePortRequest test = new SharePortRequest();
        String s = sendRequest(url, test);
        
        System.out.println(s);
    }
}
