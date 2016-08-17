package com.expocms.server.schedule;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.request.types.impl.CommissionWithdrawSyncRequest;
import com.expocms.server.request.types.impl.OrderManagementRequest;
import com.expocms.server.request.types.impl.OrderRepaymentRequest;
import com.expocms.server.tools.ToolDays;

public final class OrderManagementTask implements Runnable {
	
	private static Logger logger = Logger.getLogger(OrderManagementTask.class);
	
	public static final int ORDER_REPAYMENT_CHECK_TIMEOUT       = 1 * 60 * 60 * 1000;
	public static final int COMMISSION_WITHDRAW_CHECK_TIMEOUT   = 1 * 60 * 60 * 1000;
	
	private Thread thread_ = null;
	private boolean run_flag_ = false;
	
	public void start() {
		if(this.thread_ == null) {
			this.run_flag_ = true;
			this.thread_ = new Thread(this);
			this.thread_.start();
		}
	}
	
	public void stop() {
		if(this.thread_ != null) {
			this.run_flag_ = false;
			try {
				this.thread_.join();
			} catch (InterruptedException e) {
				logger.error("wait for OrderManagementTask stop failed!");
			}
		}
	}

	public void run() {
		try {
			Thread.sleep(60 * 1000);
			//Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			logger.error("sleep in OrderManagementTask failed!");
			return;
		}
		
		boolean order_management_dirty = true;
		Calendar today = ToolDays.getTodayCalendar();
		
		boolean order_repayment_dirty = true;
		long order_repayment_start_timestamp = System.currentTimeMillis();
		
		boolean commission_withdraw_dirty = true;
		long commission_withdraw_start_timestamp = System.currentTimeMillis();
		
		while(this.run_flag_) {
			long current_timestamp = System.currentTimeMillis();
			
			// ��������֪ͨ ...
			Calendar newDay = ToolDays.getTodayCalendar();
			if(newDay.after(today)) {
				Calendar time = Calendar.getInstance();
				time.setTime(new Date());
				if(time.get(Calendar.HOUR_OF_DAY) == 10) {
					today = newDay;
					order_management_dirty = true;
				}
			}
			
			if(order_management_dirty) {
				order_management_dirty = false;
				String url = "http://localhost:" + Constants.servicePort + "/app/orderManagement";
				OrderManagementRequest test = new OrderManagementRequest();
				OrderManagementTask.sendRequest(url, test);
			}

			// �����Զ���أ���֪ͨ�� ...
			if(current_timestamp - order_repayment_start_timestamp > ORDER_REPAYMENT_CHECK_TIMEOUT) {
				order_repayment_start_timestamp = current_timestamp;
				order_repayment_dirty = true;
			}

			if(order_repayment_dirty) {
				order_repayment_dirty = false;
				String url = "http://localhost:" + Constants.servicePort + "/app/orderRepayment";
				OrderRepaymentRequest test = new OrderRepaymentRequest();
				OrderManagementTask.sendRequest(url, test);
			}
			
			// Ӷ������ ...
			if(current_timestamp - commission_withdraw_start_timestamp > COMMISSION_WITHDRAW_CHECK_TIMEOUT) {
				commission_withdraw_start_timestamp = current_timestamp;
				commission_withdraw_dirty = true;
			}

			if(commission_withdraw_dirty) {
				commission_withdraw_dirty = false;
				String url = "http://localhost:" + Constants.servicePort + "/app/commissionWithdrawSync";
				CommissionWithdrawSyncRequest test = new CommissionWithdrawSyncRequest();
				OrderManagementTask.sendRequest(url, test);
			}
			
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				logger.error("sleep in OrderManagementTask failed!");
				break;
			}
		}
	}
	
	public static String sendRequest(String url, Object test) {
		if(url == null || url.equals(""))
			return null;
		
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if(test != null)
        	postMethod.setRequestBody(JSON.toJSONString(test));

        try {
            client.executeMethod(postMethod);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String str = null;
        try {
            str = new String(postMethod.getResponseBody(), "utf-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        postMethod.releaseConnection();
        return str;
    }

}
