package com.expocms.server.integration.test.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.LoginRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestLogin extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/login";
        
        LoginRequest test = new LoginRequest();
        test.setPhoneNo("15901308990");
        test.setPassword("111111");
        String resp = sendRequest(url, test);
        
        JSONObject json = JSON.parseObject(resp);
        
        int resultCode = json.getInteger("resultCode");
        if(resultCode != 0) {
        	System.out.println("login failed!");
        	System.out.println(json.getString("resultMsg"));
        	return;
        }
        
        String userId = json.getString("userId");
        FileUtils.writeToFile("D:\\works\\rdlc2015\\session_data\\userId.txt", userId);
    }
}
