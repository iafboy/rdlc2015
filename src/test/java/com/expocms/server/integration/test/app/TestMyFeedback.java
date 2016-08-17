package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.FeedbackRequest;
import com.expocms.server.request.types.impl.MyFeedbackRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestMyFeedback extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/myFeedback";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        MyFeedbackRequest test;
        
        test = new MyFeedbackRequest();
        test.setUserId(null);
        sendRequest(url, test);
        
        test = new MyFeedbackRequest();
        test.setUserId("wrongUserId");
        test.setPage(0);
        test.setPageCount(2);
        sendRequest(url, test);
        
        test = new MyFeedbackRequest();
        test.setUserId(userId);
        test.setPage(0);
        test.setPageCount(2);
        sendRequest(url, test);
        
        test = new MyFeedbackRequest();
        test.setUserId(userId);
        test.setPage(0);
        test.setPageCount(10);
        sendRequest(url, test);
        
        test = new MyFeedbackRequest();
        test.setUserId(userId);
        test.setPage(10);
        test.setPageCount(10);
        sendRequest(url, test);
    }
}
