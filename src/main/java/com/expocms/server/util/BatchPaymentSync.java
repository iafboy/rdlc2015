package com.expocms.server.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.schedule.OrderManagementTask;
import com.payment.server.agent.command.params.KQ_QueryRepaymentDealInfo;
import com.payment.server.agent.command.params.KQ_QueryRepaymentInfo;
import com.payment.server.agent.command.params.KQ_QueryRepaymentResult;

public final class BatchPaymentSync {
	
	private static Logger logger = Logger.getLogger(BatchPaymentSync.class);
	
	private static BatchPaymentSync inst_;
	
	private long lastSampleTime_ = 0;
	private final int SAMPLE_INTERVAL = 2 * 3600 * 1000;
	private List<KQ_QueryRepaymentDealInfo> dealQueryList_ = null;
	private Map<String, KQ_QueryRepaymentDealInfo> dealQueryMap_ = null;
	
	private BatchPaymentSync() {}
	
	public static BatchPaymentSync instance() {
		if(inst_ == null)
			inst_ = new BatchPaymentSync();
		return inst_;
	}
	
	private synchronized void sample() {
		boolean dirty = false;
		
		long curTime = System.currentTimeMillis();
		if(curTime - lastSampleTime_ > SAMPLE_INTERVAL)
			dirty = true;
		
		if(dirty) {
			Date currentTime = new Date();
			Calendar startTime = Calendar.getInstance();
			startTime.setTime(currentTime);
			startTime.add(Calendar.HOUR_OF_DAY, -24);
			
			KQ_QueryRepaymentInfo qri = new KQ_QueryRepaymentInfo();
			qri.setDealBeginDate(Constants.SDF_LONG2.format(startTime.getTime()));
			qri.setDealEndDate(Constants.SDF_LONG2.format(currentTime));
			qri.setPageSize(1000L);
			
			String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_QueryRepayment";
			//String url = "http://123.56.109.118:" + 38888 + "/payment/KQ_QueryRepayment";
			String s = OrderManagementTask.sendRequest(url, qri);
			if(s == null || s.equals("")) {
				logger.error("get repayment info failed!");
				return;
			}
			logger.info("original response from TPS:\n" + s);
			
			KQ_QueryRepaymentResult resp = null;
			try {
				resp = JSON.parseObject(s, KQ_QueryRepaymentResult.class);
			} catch (Exception e) {
				logger.error("Exception happened while parse string to KQ_QueryRepaymentResult object! " + e);
				return;
			}
			
			if("00".equals(resp.getCode()) == false) {
				logger.error("query batch repayment result failed!\n" + resp.getDetailMessage());
				return;
			}
			logger.info(resp.getDetailMessage());
			
			List<KQ_QueryRepaymentDealInfo> dealList = resp.getDealList();
			if(dealList == null || dealList.size() == 0) {
				logger.error("no repayment order found!");
				return;
			}
			
			lastSampleTime_ = curTime;
			
			dealQueryMap_ = new HashMap<String, KQ_QueryRepaymentDealInfo>();
			for(int i = 0; i < dealList.size(); i ++) {
				KQ_QueryRepaymentDealInfo deal = dealList.get(i);
				dealQueryMap_.put(deal.getDealId(), deal);
			}
			
			dealQueryList_ = dealList;
		}
	}
	
	public List<KQ_QueryRepaymentDealInfo> getDealQueryList() {
		sample();
		return dealQueryList_;
	}
	
	public Map<String, KQ_QueryRepaymentDealInfo> getDealQueryMap() {
		sample();
		return dealQueryMap_;
	}

}
