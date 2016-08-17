package com.expocms.server.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.expocms.server.cache.CacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdActivitygiftStatMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdExceptionOrderMapper;
import com.expocms.server.db.dao.RdFixedgiftMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdActivitygiftStat;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdFixedgift;
import com.expocms.server.db.model.RdOrder;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.UndoQuickPaymentRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.impl.OrderGift;
import com.expocms.server.response.types.impl.QuickPaymentGift;
import com.expocms.server.response.types.impl.RedPackage;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.util.SyncObjects;

@Service("UNDOQUICKPAYMENTHandler")
@Transactional
public class UNDOQUICKPAYMENTHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(UNDOQUICKPAYMENTHandler.class);
	
	@Autowired
	private RdOrderMapper rdOrderMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdExceptionOrderMapper rdExceptionOrderMapper;
	
	@Autowired
	private RdActivitygiftMapper rdGiftMapper;
	
	@Autowired
	private RdActivitygiftStatMapper rdActivityGiftMapper;
	
	@Autowired
	private RdFixedgiftMapper rdFixedGiftMapper;
	
	@Override
	public BaseResponse handleRequest(final IRequest request) {
		assert(request != null);
		UndoQuickPaymentRequest uqpRequest = (UndoQuickPaymentRequest)request;

		final String orderId = uqpRequest.getOrderId();
		assert(orderId != null && orderId.equals("") == false);
		final boolean orderSuccess = uqpRequest.getOrderSuccess() != null ? (uqpRequest.getOrderSuccess() != 0) : false;
		
		returnResp = new CommonResponse();

		logger.info("orderId = " + orderId + ", get order from DB ...");
		RdOrder order = rdOrderMapper.selectByPrimaryKey(orderId);
		if(order == null) {
			logger.error("orderId = " + orderId + ", order not found!");
			returnResp.setResultCode(Constants.failCode);
			returnResp.setResultMsg("订单不存在");
			return returnResp;
		}
		
		if(order.getStatus() == null || RdOrder.ORDER_STATUS_PAYING != order.getStatus()) {
			logger.error("orderId = " + orderId + ", order status doesn't support UNDO!");
			returnResp.setResultCode(Constants.failCode);
			returnResp.setResultMsg(RdOrder.ORDER_STATUS_MAP.get(RdOrder.ORDER_STATUS_PAYING) + "订单才能回滚");
			return returnResp;
		}
		
		logger.info("orderId = " + orderId + ", get userId from order ...");
		String userId = order.getAppuserid();
		if(userId == null || userId.equals("")) {
			logger.error("orderId = " + orderId + ", userId is null!");
			returnResp.setResultCode(Constants.failCode);
			returnResp.setResultMsg("用户id为空");
			return returnResp;
		}

		Object userSyncObj = SyncObjects.instance().getUserSyncObject(userId);
		synchronized (userSyncObj) {
			logger.info("enter UNDOQUICKPAYMENTHandler user synchronization section ...");

			logger.info("orderId = " + orderId + ", get user from DB ...");
			RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
			if(rdAppuser == null) {
				logger.error("orderId = " + orderId + ", user " + userId + " not found!");
				returnResp.setResultCode(Constants.failCode);
				returnResp.setResultMsg("用户不存在");
				return returnResp;
			}

			logger.info("orderId = " + orderId + ", get productId from order ...");
			String productId = order.getProductids();
			if(productId == null || productId.equals("")) {
				logger.error("orderId = " + orderId + ", productId is null!");
				returnResp.setResultCode(Constants.failCode);
				returnResp.setResultMsg("产品id为空");
				return returnResp;
			}

			Object productSyncObj = SyncObjects.instance().getProductSyncObject(productId);
			synchronized (productSyncObj) {
				logger.info("enter UNDOQUICKPAYMENTHandler product synchronization section ...");
				
				logger.info("orderId = " + orderId + ", get product from DB ...");
				RdProduct rdProduct = rdProductMapper.selectByPrimaryKey(productId);
				if(rdProduct == null) {
					logger.error("orderId = " + orderId + ", product " + productId + " not found!");
					returnResp.setResultCode(Constants.failCode);
					returnResp.setResultMsg("产品不存在");
					return returnResp;
				}
				
				logger.info("orderId = " + orderId + ", get red package code(s) and used amount(s) from order ...");
				QuickPaymentGift quickPaymentGift = null;
				String orderGiftCode = order.getGiftCode();
				if(orderGiftCode != null && orderGiftCode.equals("") == false) {
					try {
						quickPaymentGift = JSON.parseObject(orderGiftCode, QuickPaymentGift.class);
					} catch (Exception e) {
						logger.error("orderId = " + orderId + ", parse gift code failed!");
						returnResp.setResultCode(Constants.failCode);
						returnResp.setResultMsg("解析红包code失败");
						return returnResp;
					}
				}
				
				if(orderSuccess) {
					final int orderState = uqpRequest.getOrderState() != null ? uqpRequest.getOrderState() : -1;
					if(orderState != RdOrder.ORDER_STATUS_SEALED &&
							orderState != RdOrder.ORDER_STATUS_REPAYING &&
							orderState != RdOrder.ORDER_STATUS_REPAIED) {
						logger.error("orderId = " + orderId + ", order status doesn't support cancel UNDO!");
						returnResp.setResultCode(Constants.failCode);
						returnResp.setResultMsg("订单新状态不正确(1,2,3)");
						return returnResp;
					}
					
					if(quickPaymentGift != null && quickPaymentGift.getPurchaseGift() != null) {
						RedPackage purchaseGift = quickPaymentGift.getPurchaseGift();
						String giftId = purchaseGift.getRedPackageCode();
						if(giftId != null && giftId.equals("") == false) {
							if (rdAppuser.getFixedgift() != null && rdAppuser.getFixedgift().equals("") == false)
								rdAppuser.setFixedgift(rdAppuser.getFixedgift() + "," + giftId);
							else
								rdAppuser.setFixedgift(giftId);

							if (rdAppuserMapper.updateFixedGiftByIds(rdAppuser) != 1) {
								logger.error("orderId " + orderId + ", add user fixed gift code into DB failed!");
								throw new RuntimeException("更新用户固定红包失败");
							}
						}
					}
					
					order.setStatus(orderState);
					if (rdOrderMapper.updateOrderStatus(order) != 1) {
						logger.error("orderId = " + orderId + ", update order status to '" + RdOrder.ORDER_STATUS_MAP.get(orderState) + "' failed!");
						throw new RuntimeException("更新订单状态失败");
					}
					
					CacheManager.instance().setUserDirty(userId);
					
					returnResp.setResultCode(Constants.succCode);
					returnResp.setResultMsg(Constants.succMsg);
					return returnResp;
				}

				logger.info("orderId = " + orderId + ", get amount(s) from order ...");
				long amount = order.getAmount();                 // 产品金额 = 支付金额 + 红包支付金额
				long actualAmount = order.getActualamount();     // 实际支付金额
				long giftAmount = order.getGiftamount();         // 红包支付金额
				// check amount(s) of order ...
				if(amount != (actualAmount + giftAmount)) {
					logger.error("orderId = " + orderId + ", amount in order is wrong!");
					returnResp.setResultCode(Constants.failCode);
					returnResp.setResultMsg("订单金额不正确");
					return returnResp;
				}
				assert(amount == (actualAmount + giftAmount));

				logger.info("orderId = " + orderId + ", rollback red package information from DB ...");
				if(quickPaymentGift != null) {
					// delete purchase gift ...
					if(quickPaymentGift.getPurchaseGift() != null) {
						RedPackage purchaseGift = quickPaymentGift.getPurchaseGift();
						
						String giftId = purchaseGift.getRedPackageCode();
						if(giftId != null && giftId.equals("") == false) {
							if(rdGiftMapper.deleteByPrimaryKey(giftId) != 1) {
								logger.error("delete purchase gift from DB failed!");
								throw new RuntimeException("删除赠送的固定红包失败");
							}
						}
						
						Integer purchaseNum = purchaseGift.getPurchaseNum();
						if(purchaseNum != null) {
							RdFixedgift rdFixedGift = null;
							if (purchaseNum == 1)
								rdFixedGift = rdFixedGiftMapper.selectByType(Constants.purchaseFixedGift);
							else if(purchaseNum == 2)
								rdFixedGift = rdFixedGiftMapper.selectByType(Constants.purchase2FixedGift);
							
							if(rdFixedGift != null) {
								int activatedNum = rdFixedGift.getActivatednum() != null ? rdFixedGift.getActivatednum() : 0;
								if(activatedNum <= 0)
									logger.error("orderId = " + orderId + ", fixed gift activatedNum less than or equals 0 ???");
								if(activatedNum > 0)
									activatedNum -= 1;
								rdFixedGift.setActivatednum(activatedNum);
								
								if(rdFixedGiftMapper.updateByPrimaryKey(rdFixedGift) != 1) {
									logger.error("update red package into DB failed!");
									throw new RuntimeException("更新固定红包失败");
								}
							}
						}
					}
					
					// two matters to be recoverd ...
					List<OrderGift> orderGiftList = quickPaymentGift.getOrderGiftList();
					if(orderGiftList != null && orderGiftList.size() != 0) {
						for(int i = 0; i < orderGiftList.size(); i ++) {
							OrderGift orderGift = orderGiftList.get(i);
							
							String rdCode = orderGift.getRedPackageCode();
							long rdAmount = orderGift.getUsedMoney();
							String type = orderGift.getType();
							String ordId = orderGift.getOriginalRedPackageId();
							
							if(rdCode == null || rdCode.equals("")) {
								logger.error("orderId = " + orderId + ", red package code " + i + " is null ???");
								continue;
							}
							if(rdAmount == 0) {
								logger.error("orderId = " + orderId + ", red package amount " + i + " is 0 ???");
								continue;
							}
							if(type == null || type.equals("")) {
								logger.error("orderId = " + orderId + ", red package type " + i + " is null ???");
								continue;
							}
							if(ordId == null || ordId.equals("")) {
								logger.error("orderId = " + orderId + ", red package original Id " + i + " is null ???");
								continue;
							}
							
							// recover used amount of gift ...
							RdActivitygift gift = rdGiftMapper.selectByPrimaryKey(rdCode);
							if(gift == null) {
								logger.error("orderId = " + orderId + ", red package " + rdCode + " not found ???");
								continue;
							}
							gift.setRestMoney((gift.getRestMoney() != null ? gift.getRestMoney() : 0) + rdAmount);
							if (rdGiftMapper.updateByPrimaryKey(gift) != 1) {
								logger.error("orderId = " + orderId + ", rollback amount to red package failed!");
								throw new RuntimeException("回滚红包失败");
							}
							
							// recover used count of original gift ...
							if("1".equals(type)) {
								RdActivitygiftStat rdActivityGift = rdActivityGiftMapper.selectByPrimaryKey(ordId);
								int usedCount = rdActivityGift.getUsedcount() != null ? rdActivityGift.getUsedcount() : 0;
								if(usedCount <= 0) {
									logger.error("orderId = " + orderId + ", red package " + rdCode + " usedCount is 0 ???");
									continue;
								}
								rdActivityGift.setUsedcount(usedCount - 1);
								if(rdActivityGiftMapper.updateByPrimaryKey(rdActivityGift) != 1) {
									logger.error("orderId = " + orderId + ", rollback original activity red package usedCount failed!");
									throw new RuntimeException("回滚原始活动红包使用次数失败");
								}
							} else if("2".equals(type)) {
								RdFixedgift rdFixedGift = rdFixedGiftMapper.selectByPrimaryKey(ordId);
								int usedCount = rdFixedGift.getUsednum() != null ? rdFixedGift.getUsednum() : 0;
								if(usedCount <= 0) {
									logger.error("orderId = " + orderId + ", red package " + rdCode + " usedCount is 0 ???");
									continue;
								}
								rdFixedGift.setUsednum(usedCount - 1);
								if(rdFixedGiftMapper.updateByPrimaryKey(rdFixedGift) != 1) {
									logger.error("orderId = " + orderId + ", rollback original fixed red package usedCount failed!");
									throw new RuntimeException("回滚原始固定红包使用次数失败");
								}
							} else {
								logger.error("orderId = " + orderId + ", red package type " + i + " is " + type + " ???");
								continue;
							}
						}
					}
				}
				
				logger.info("orderId = " + orderId + ", rollback user information from DB ...");
				// reduce purchase number of user ...
				int purchaseNum = rdAppuser.getPurchaseNum();
				if(purchaseNum <= 0)
					logger.error("orderId = " + orderId + ", user purchaseNum less than or equals 0 ???");
				if(purchaseNum > 0)
					purchaseNum -= 1;
				rdAppuser.setPurchaseNum(purchaseNum);
				// reduce repaid amount ...
				long totalAssets = rdAppuser.getTotalAssets() != null ? rdAppuser.getTotalAssets() : 0;
				long repaidAmount = order.getRepaidamount() != null ? order.getRepaidamount() : 0;
				if(totalAssets < repaidAmount)
					logger.error("orderId = " + orderId + ", user totalAssets less than order repaidAmount ???");
				if(totalAssets >= repaidAmount)
					totalAssets -= repaidAmount;
				// update user info into DB ...
				if(rdAppuserMapper.updateByPrimaryKey(rdAppuser) != 1) {
					logger.error("orderId = " + orderId + ", user information rollback from DB failed!");
					throw new RuntimeException("回滚用户数据失败");
				}
				
				logger.info("orderId = " + orderId + ", rollback product information from DB ...");
				// reduce sold amount ...
				long soldAmount = rdProduct.getSoldamount() != null ? rdProduct.getSoldamount() : 0;
				if(soldAmount <= 0)
					logger.error("orderId = " + orderId + ", product soldAmount less than or equals 0 ???");
				if(soldAmount >= amount)
					soldAmount -= amount;
				rdProduct.setSoldamount(soldAmount);
				// reduce success records ...
				int successRecords = rdProduct.getSuccessrecords() != null ? rdProduct.getSuccessrecords() : 0;
				if(successRecords <= 0)
					logger.error("orderId = " + orderId + ", product successRecords less than or equals 0 ???");
				if(successRecords >= 1)
					successRecords -= 1;
				rdProduct.setSuccessrecords(successRecords);
				// reduce success amount ...
				long successAmount = rdProduct.getSuccessamount() != null ? rdProduct.getSuccessamount() : 0;
				if(successAmount <= 0)
					logger.error("orderId = " + orderId + ", product successAmount less than or equals 0 ???");
				if(successAmount >= amount)
					successAmount -= amount;
				rdProduct.setSuccessamount(successAmount);
				// reduce buyer number ...
				// TODO: we can't say this product never bought by this user in other order(s) ...
				//long buyerNum = CacheManager.instance().decUserNumOfProduct(rdOrderMapper, productId, userId);
				//rdProduct.setBuyernum(buyerNum);
				// update product info into DB ...
				if (rdProductMapper.updateQuickPayment(rdProduct) != 1) {
					logger.error("orderId = " + orderId + ", product sold information rollback from DB failed!");
					throw new RuntimeException("回滚产品数据失败");
				}
				// increase failed records and failed amount ...
				rdProduct.setFailrecords((rdProduct.getFailrecords() != null ? rdProduct.getFailrecords() : 0) + 1);
				rdProduct.setFailamount((rdProduct.getFailamount() != null ? rdProduct.getFailamount() : 0) + amount);
				if (rdProductMapper.updateQuickPaymentFailed(rdProduct) != 1) {
					logger.error("orderId = " + orderId + ", product failed information rollback from DB failed!");
					throw new RuntimeException("回滚产品数据(增加失败数和失败金额)失败");
				}
				
				logger.info("orderId = " + orderId + ", rollback order information from DB ...");
				logger.info("orderId = " + orderId + ", insert order into exception order table ...");
				if(rdExceptionOrderMapper.insert(order) != 1) {
					logger.error("orderId = " + orderId + ", insert order into exception order table failed!");
					throw new RuntimeException("回滚订单数据（写异常订单）失败");
				}
				logger.info("orderId = " + orderId + ", delete order from order table ...");
				if(rdOrderMapper.deleteByPrimaryKey(orderId) != 1) {
					logger.error("orderId = " + orderId + ", delete order from order table failed!");
					throw new RuntimeException("回滚订单数据（删正常订单）失败");
				}
				
				CacheManager.instance().setUserDirty(userId);

				logger.info("leave UNDOQUICKPAYMENTHandler product synchronization section.");
			}

			logger.info("leave UNDOQUICKPAYMENTHandler user synchronization section.");
		}

		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
