package com.expocms.server.integration.test.app;

import com.expocms.server.integration.test.InterfaceTestable;
import com.expocms.server.request.types.impl.CurrentPeriodBuyRequest;
import com.expocms.server.util.FileUtils;

/**
 * Created by Administrator on 2015/9/21.
 */
public class TestCurrentPeriodBuyRequest extends InterfaceTestable {
	
        public static void main(String[] args) {
            String url = "/app/CurrentPeriodBuy";
            
            String userId = FileUtils.readFromFile("D:\\works\\rdlc2015\\session_data\\userId.txt");
            if(userId == null) {
            	System.out.println("please login first!");
            	return;
            }
            
            CurrentPeriodBuyRequest test = null;
            
            test = new CurrentPeriodBuyRequest();
            test.setPage(0);
            test.setPageCount(10);
            test.setProductId(null);
            sendRequest(url, test);
            
            test = new CurrentPeriodBuyRequest();
            test.setPage(0);
            test.setPageCount(10);
            test.setProductId("");
            sendRequest(url, test);
            
            test = new CurrentPeriodBuyRequest();
            test.setPage(0);
            test.setPageCount(10);
            test.setProductId("wrongProductId");
            sendRequest(url, test);
            
            test = new CurrentPeriodBuyRequest();
            test.setPage(0);
            test.setPageCount(10);
            test.setProductId("00c499c1bafe41ba8ad0b88269561ad");
            sendRequest(url, test);
        }
}
