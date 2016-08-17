package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.FeedbackRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/19.
 */
public class TestFeedback extends InterfaceTestable{

    public static void main(String[] args){
        url = "/app/feedback";
        
        String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
        if(userId == null) {
        	System.out.println("please login first!");
        	return;
        }
        
        FeedbackRequest test = null;
        
        test = new FeedbackRequest();
        test.setUserId(null);
        test.setContent("test whether is added 1.");
        sendRequest(url, test);
        
        test = new FeedbackRequest();
        test.setUserId("");
        test.setContent("test whether is added 2.");
        sendRequest(url, test);
        
        test = new FeedbackRequest();
        test.setUserId("wrongUserId");
        test.setContent("test whether is added 3.");
        sendRequest(url, test);
        
        test = new FeedbackRequest();
        test.setUserId(userId);
        test.setContent("test whether is added 4.");
        sendRequest(url, test);
    }
}
