package com.expocms.server.cache;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.impl.MYPROPERTYHandler;
import com.expocms.server.db.dao.MyPropertyMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.model.RdOrder;
import com.expocms.server.response.types.impl.AlreadyBuyItem;
import com.expocms.server.response.types.impl.RedeemItem;
import com.expocms.server.tools.ToolDays;

final class UserCache {
	
	private static Logger logger = Logger.getLogger(UserCache.class);
	
	private String userId_ = null;
	
	private boolean redeemItemsDirty_ = false;
	private List<RedeemItem> redeemItems_ = null;
	
	private boolean alreadyBuyItemsDirty_ = false;
	private List<AlreadyBuyItem> alreadyBuyItems_ = null;
	
	private boolean orderListDirty_ = false;
	private List<RdOrder> orderList_ = null;
	
	public UserCache(String userId) {
		this.userId_ = userId;
	}

	public void dirty() {
		this.redeemItemsDirty_ = true;
		this.alreadyBuyItemsDirty_ = true;
		this.orderListDirty_ = true;
	}
	
	List<RedeemItem> getRedeemItems(final MyPropertyMapper myPropertyMapper) {
		if(myPropertyMapper == null)
			return null;
		
		assert(this.userId_ != null && this.userId_.equals("") == false);
		
		if(this.redeemItemsDirty_) {
			this.redeemItemsDirty_ = false;
			
			try {
				this.redeemItems_ = myPropertyMapper.getRedeemItemEntityList(this.userId_);
			} catch(Exception e) {
				logger.error("getRedeemItems for user " + this.userId_ + " failed!");
				return null;
			}
			
			if(this.redeemItems_ != null && this.redeemItems_.size() != 0) {
				for(RedeemItem ri: this.redeemItems_) {
					if(ri.getBankName() != null && ri.getBankName().equals("") == false) {
						String bankCardNo = ri.getBankCard();
				        String storableCardNo = "";
				        if(bankCardNo != null && bankCardNo.length() > 4)
				        	storableCardNo = bankCardNo.substring(bankCardNo.length() - 4);
				        ri.setBank(ri.getBankName() + "(尾号" + storableCardNo + ")");
				        
				        ri.setBankName(null);
						ri.setBankCard(null);
					}
					
					if(ri.getValueDateDB() != null) {
						ri.setValueDate(Constants.SDF_SHORT.format(ri.getValueDateDB()));
						ri.setValueDateDB(null);
					}
					
					if(ri.getExpireDateDB() != null && ri.getOrderStatus() != null) {
						String status = RdOrder.ORDER_STATUS_MAP.get(ri.getOrderStatus());
						if(status != null && status.equals("") == false)
							ri.setExpireDate(Constants.SDF_SHORT.format(ri.getExpireDateDB()) + " (" + status + ")");
						ri.setExpireDateDB(null);
						ri.setOrderStatus(null);
					}
					
					if(ri.getPredictGetTimeDB() != null) {
						Date realDate = ToolDays.getDayAfter(ri.getPredictGetTimeDB(), 3);
						ri.setPredictGetTime(Constants.SDF_SHORT.format(realDate));
						ri.setPredictGetTimeDB(null);
					}
					
					if(ri.getBuyTimeDB() != null) {
						ri.setBuyTime(Constants.SDF_SHORT.format(ri.getBuyTimeDB()));
						ri.setBuyTimeDB(null);
					}
				}
			}
		}
		
		return this.redeemItems_;
	}
	
	public List<AlreadyBuyItem> getAlreadyBuyItems(final MyPropertyMapper myPropertyMapper) {
		if(myPropertyMapper == null)
			return null;
		
		assert(this.userId_ != null && this.userId_.equals("") == false);
		
		if(this.alreadyBuyItemsDirty_) {
			this.alreadyBuyItemsDirty_ = false;
			
			try {
				this.alreadyBuyItems_ = myPropertyMapper.getAlreadybuyItemEntityList(this.userId_);
			} catch(Exception e) {
				logger.error("getAlreadyBuyItems for user " + this.userId_ + " failed!");
				return null;
			}
			
			if(this.alreadyBuyItems_ != null && this.alreadyBuyItems_.size() != 0) {
				Calendar today = ToolDays.getTodayCalendar();
				
				for(AlreadyBuyItem ai: this.alreadyBuyItems_) {
					if(ai.getBankName() != null && ai.getBankName().equals("") == false) {
						String bankCardNo = ai.getBankCard();
				        String storableCardNo = "";
				        if(bankCardNo != null && bankCardNo.length() > 4)
				        	storableCardNo = bankCardNo.substring(bankCardNo.length() - 4);
				        ai.setBank(ai.getBankName() + "(尾号" + storableCardNo + ")");
				        
				        ai.setBankName(null);
						ai.setBankCard(null);
					}
					
					RdOrder order = new RdOrder();
					order.setAmount(ai.getPrincipal());
					order.setInterest((float)ai.getPredictYearEarnings() / 10);
					order.setIntereststartdate(ai.getValueDateDB());
					order.setInterestenddate(ai.getExpireDateDB());
					
					long allEarnings = MYPROPERTYHandler.calculateOrderEarningsByDate(order, today);
					ai.setAllEarnings(allEarnings);
					ai.setCurrentPoperty(ai.getPrincipal() + allEarnings);
					
					if(ai.getValueDateDB() != null) {
						ai.setValueDate(Constants.SDF_SHORT.format(ai.getValueDateDB()));
						ai.setValueDateDB(null);
					}
					
					if(ai.getExpireDateDB() != null && ai.getOrderStatus() != null) {
						String status = RdOrder.ORDER_STATUS_MAP.get(ai.getOrderStatus());
						if(status != null && status.equals("") == false)
							ai.setExpireDate(Constants.SDF_SHORT.format(ai.getExpireDateDB()) + " (" + status + ")");
						ai.setExpireDateDB(null);
						ai.setOrderStatus(null);
					}
					
					if(ai.getPredictGetTimeDB() != null) {
						Date realDate = ToolDays.getDayAfter(ai.getPredictGetTimeDB(), 3);
						ai.setPredictGetTime(Constants.SDF_SHORT.format(realDate));
						ai.setPredictGetTimeDB(null);
					}
					
					if(ai.getBuyTimeDB() != null) {
						ai.setBuyTime(Constants.SDF_SHORT.format(ai.getBuyTimeDB()));
						ai.setBuyTimeDB(null);
					}
				}
			}
		}
		
		return this.alreadyBuyItems_;
	}
	
	public List<RdOrder> getOrderList(final RdOrderMapper rdOrderMapper) {
		if(rdOrderMapper == null)
			return null;
		
		assert(this.userId_ != null && this.userId_.equals("") == false);
		
		if(this.orderListDirty_) {
			this.orderListDirty_ = false;
			
			try {
				/**
				 * 昨日的收益还是应该只算正在封闭期中的，而不算已赎回的。
				 * 累计收益算封闭中、赎回中、已赎回的所有金额，相当于是这个用户通过平台挣了多少钱。
				 */
				this.orderList_ = rdOrderMapper.getOrdersByUser(this.userId_);
			} catch(Exception e) {
				logger.error("getOrderList for user " + this.userId_ + " failed!");
				return null;
			}
		}
		
		return this.orderList_;
	}
	
}
