package com.expocms.server.core.impl;

import java.util.Calendar;
import java.util.List;

import com.expocms.server.db.dao.RdContractMapper;
import com.expocms.server.db.dao.RdDictMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.CacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.MyPropertyMapper;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.request.types.impl.MyPropertyRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.AlreadyBuyItem;
import com.expocms.server.response.types.impl.MyPropertyResponse;
import com.expocms.server.response.types.impl.RedeemItem;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

@Service("MYPROPERTYHandler")
@Transactional
public class MYPROPERTYHandler extends AbsBaseHandler {
	
	@Autowired
	private MyPropertyMapper myPropertyMapper;

	@Autowired
	private RdDictMapper dictMapper;

	@Autowired
	private RdContractMapper conMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdActivitygiftMapper rdActivitygiftMapper;
	
	@Autowired
	private RdOrderMapper rdOrderMapper;

	public MYPROPERTYHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		MyPropertyResponse returnResp = new MyPropertyResponse();
		
		String userId=((MyPropertyRequest)request).getUserId();
		if(userId == null || userId.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}

		
		String webroot = dictMapper.getValueFromDict("web.root");
		String url_1 = null;
		try {
			int seq_1 = Integer.parseInt(dictMapper.getValueFromDict("contract.digital"));
			url_1 = conMapper.queryUrlBySeq(seq_1);
		} catch(NumberFormatException e) {}
		if(webroot != null && url_1 != null){
			url_1 = webroot + url_1.replace("\\", "/");
		}
		
		
		List<RedeemItem> redeemItems = CacheManager.instance().getUserRedeemItems(myPropertyMapper, userId);
		if(redeemItems != null && redeemItems.size() != 0) {
			for(RedeemItem ri: redeemItems) {
				if(webroot != null && webroot.equals("") == false) {
					String url_2 = webroot + "/uploads/contracts/" + ri.getOrderId() + ".html";
					ri.setProtocolUrl(url_2);
				}
				ri.setNumccieURL(url_1);
			}
		}
		
		
		List<AlreadyBuyItem> alreadyBuyItems = CacheManager.instance().getUserAlreadyBuyItems(myPropertyMapper, userId);
		if(alreadyBuyItems != null && alreadyBuyItems.size() != 0) {
			for(AlreadyBuyItem ai: alreadyBuyItems) {
				if(webroot != null && webroot.equals("") == false) {
					String url_2 = webroot + "/uploads/contracts/" + ai.getOrderId() + ".html";
					ai.setProtocolUrl(url_2);
				}
				ai.setNumccieURL(url_1);
			}
		}
		
		
		// calculate data from order(s) ...
		/**
		 * 昨日的收益还是应该只算正在封闭期中的，而不算已赎回的。
		 * 累计收益算封闭中、赎回中、已赎回的所有金额，相当于是这个用户通过平台挣了多少钱。
		 */
		long allAmountTotal = 0;
		long yesterdayEarningAmountTotal = 0;
		long allEarningAmountTotal = 0;
		List<RdOrder> orderList = CacheManager.instance().getUserOrderList(rdOrderMapper, userId);
		if(orderList != null && orderList.size() != 0) {
			Calendar today = ToolDays.getTodayCalendar();
			for(RdOrder order : orderList) {
				if(order.getStatus() != RdOrder.ORDER_STATUS_REPAIED &&
						order.getStatus() != RdOrder.ORDER_STATUS_PAYING) {
					long orderAmount = order.getAmount();
					allAmountTotal += orderAmount;
				}
				
				if(order.getStatus() != RdOrder.ORDER_STATUS_REPAIED &&
						order.getStatus() != RdOrder.ORDER_STATUS_PAYING) {
					long yesterdayEarningAmount = calculateOrderEarningsPerDate(order, today);
					yesterdayEarningAmountTotal += yesterdayEarningAmount;
				}
				
				long allEarningAmount = calculateOrderEarningsByDate(order, today);
				allEarningAmountTotal += allEarningAmount;
				
				if(order.getStatus() != RdOrder.ORDER_STATUS_REPAIED &&
						order.getStatus() != RdOrder.ORDER_STATUS_PAYING)
					allAmountTotal += allEarningAmount;
			}
		}
		
		
		// calculate data from gift(s) ...
		long giftRestAmountTotal = 0;
		List<RdActivitygift> gifts = null;
		
		try {
			gifts = PREPAREBUYHandler.queryUserGifts(rdAppuser, rdActivitygiftMapper, true);
		} catch(Exception e) { e.printStackTrace(); }
		if(gifts != null && gifts.size() != 0) {
	        for(RdActivitygift gift : gifts) {
            	long restMoney = gift.getRestMoney();
                giftRestAmountTotal += restMoney;
	        }
        }

		
		returnResp.setAlreadybuyItem(alreadyBuyItems);
		returnResp.setRedeemItem(redeemItems);

		returnResp.setYesterdayEarnings(yesterdayEarningAmountTotal);
		returnResp.setAllEarnings(allEarningAmountTotal);
		returnResp.setAllProperty(allAmountTotal + giftRestAmountTotal);

		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg (Constants.succMsg);
		
		return returnResp;
	}
	
	public static long calculateOrderEarningsPerDate(final RdOrder order, final Calendar date) {
		if(order == null || date == null)
			//throw new RuntimeException("invalid arguments!");
			return 0;
		if(order.getIntereststartdate() == null || order.getInterestenddate() == null)
			//throw new RuntimeException("no start interest date or end interest date!");
			return 0;
		
		Calendar siDate = ToolDays.getCalendar(order.getIntereststartdate());
		Calendar eiDate = ToolDays.getCalendar(order.getInterestenddate());
		Calendar currentDate = date;
		Calendar nextDate = ToolDays.getNextDay(eiDate);
		
		if(ToolDays.compareDate(currentDate.getTime(), siDate.getTime()) <= 0)
			return 0;
		if(ToolDays.compareDate(currentDate.getTime(), nextDate.getTime()) > 0)
			return 0;
		
		int betweenDays = 1;
		long orderAmount = order.getAmount();
		float interest = order.getInterest();
		long earningAmount = Math.round((double)orderAmount * interest * betweenDays / 365 / 100);
		
		return earningAmount;
	}
	
	public static long calculateOrderEarningsByDate(final RdOrder order, final Calendar date) {
		if(order == null || date == null)
			//throw new RuntimeException("invalid arguments!");
			return 0;
		if(order.getIntereststartdate() == null || order.getInterestenddate() == null)
			//throw new RuntimeException("no start interest date or end interest date!");
			return 0;
		
		Calendar siDate = ToolDays.getCalendar(order.getIntereststartdate());
		Calendar eiDate = ToolDays.getCalendar(order.getInterestenddate());
		Calendar currentDate = date;
		Calendar nextDate = ToolDays.getNextDay(eiDate);
		
		if(ToolDays.compareDate(currentDate.getTime(), siDate.getTime()) <= 0)
			return 0;
		if(currentDate.after(nextDate))
			currentDate = nextDate;
		
		int betweenDays = ToolDays.getDatesBetweenOffset(currentDate, siDate);
		long orderAmount = order.getAmount();
		float interest = order.getInterest();
		long earningAmount = Math.round((double)orderAmount * interest * betweenDays / 365 / 100);
		
		return earningAmount;
	}

}
