package com.expocms.server.cache;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expocms.server.db.dao.MyPropertyMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.model.RdOrder;
import com.expocms.server.response.types.impl.AlreadyBuyItem;
import com.expocms.server.response.types.impl.RedeemItem;
import com.expocms.server.tools.ToolDays;

public final class CacheManager implements Runnable {
	
	private static CacheManager inst_ = null;
	
	private Thread thread_ = null;
	
	private Map<String, ProductCache> productMap_ = new HashMap<String, ProductCache>();
	private Map<String, UserCache> userMap_ = new HashMap<String, UserCache>();
	
	private CacheManager() {
		this.thread_ = new Thread(this);
		this.thread_.start();
	}
	
	public void run() {
		Calendar today = ToolDays.getTodayCalendar();
		
		while(true) {
			Calendar newDay = ToolDays.getTodayCalendar();
			
			if(newDay.after(today)) {
				today = newDay;
				
				this.setAllUserDirty();
			}
			
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {}
		}
	}
	
	public static CacheManager instance() {
		if(inst_ == null)
			inst_ = new CacheManager();
		return inst_;
	}
	
	public int getUserNumOfProduct(final RdOrderMapper rdOrderMapper, final String productId, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(productId == null || productId.equals(""))
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		synchronized(this.productMap_) {
			ProductCache productCache = this.productMap_.get(productId);
			if(productCache == null) {
				productCache = new ProductCache(productId);
				productCache.dirty();
				this.productMap_.put(productId, productCache);
			}
			return productCache.getUserNum(rdOrderMapper, userId);
		}
	}
	
	public int incUserNumOfProduct(final RdOrderMapper rdOrderMapper, final String productId, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(productId == null || productId.equals(""))
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		synchronized(this.productMap_) {
			ProductCache productCache = this.productMap_.get(productId);
			if(productCache == null) {
				productCache = new ProductCache(productId);
				productCache.dirty();
				this.productMap_.put(productId, productCache);
			}
			return productCache.incUserNum(rdOrderMapper, userId);
		}
	}
	
	public int decUserNumOfProduct(final RdOrderMapper rdOrderMapper, final String productId, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(productId == null || productId.equals(""))
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		synchronized(this.productMap_) {
			ProductCache productCache = this.productMap_.get(productId);
			if(productCache == null) {
				productCache = new ProductCache(productId);
				productCache.dirty();
				this.productMap_.put(productId, productCache);
			}
			return productCache.decUserNum(rdOrderMapper, userId);
		}
	}
	
	public void setUserDirty(final String userId) {
		if(userId == null || userId.equals(""))
			return;
		
		synchronized(this.userMap_) {
			UserCache userCache = this.userMap_.get(userId);
			if(userCache == null) {
				userCache = new UserCache(userId);
				userCache.dirty();
				this.userMap_.put(userId, userCache);
			}
			userCache.dirty();
		}
	}
	
	public void setAllUserDirty() {
		synchronized(this.userMap_) {
			for(Map.Entry<String, UserCache> entry:this.userMap_.entrySet()) {
				UserCache userCache = entry.getValue();
				userCache.dirty();
			}
		}
	}
	
	public List<RedeemItem> getUserRedeemItems(final MyPropertyMapper myPropertyMapper, final String userId) {
		if(myPropertyMapper == null)
			return null;
		if(userId == null || userId.equals(""))
			return null;
		
		synchronized(this.userMap_) {
			UserCache userCache = this.userMap_.get(userId);
			if(userCache == null) {
				userCache = new UserCache(userId);
				userCache.dirty();
				this.userMap_.put(userId, userCache);
			}
			return userCache.getRedeemItems(myPropertyMapper);
		}
	}
	
	public List<AlreadyBuyItem> getUserAlreadyBuyItems(final MyPropertyMapper myPropertyMapper, final String userId) {
		if(myPropertyMapper == null)
			return null;
		if(userId == null || userId.equals(""))
			return null;
		
		synchronized(this.userMap_) {
			UserCache userCache = this.userMap_.get(userId);
			if(userCache == null) {
				userCache = new UserCache(userId);
				userCache.dirty();
				this.userMap_.put(userId, userCache);
			}
			return userCache.getAlreadyBuyItems(myPropertyMapper);
		}
	}
	
	public List<RdOrder> getUserOrderList(final RdOrderMapper rdOrderMapper, final String userId) {
		if(rdOrderMapper == null)
			return null;
		if(userId == null || userId.equals(""))
			return null;
		
		synchronized(this.userMap_) {
			UserCache userCache = this.userMap_.get(userId);
			if(userCache == null) {
				userCache = new UserCache(userId);
				userCache.dirty();
				this.userMap_.put(userId, userCache);
			}
			return userCache.getOrderList(rdOrderMapper);
		}
	}

}
