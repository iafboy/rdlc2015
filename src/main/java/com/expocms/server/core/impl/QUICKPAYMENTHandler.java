package com.expocms.server.core.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.expocms.server.db.dao.*;
import com.expocms.server.db.model.*;
import com.expocms.server.response.types.impl.QuickPaymentActivityGift;
import com.expocms.server.response.types.impl.QuickPaymentGift;
import com.expocms.server.response.types.impl.RedPackage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.expocms.server.cache.CacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.QuickPaymentRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.impl.OrderGift;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.schedule.OrderManagementTask;
import com.expocms.server.tools.ToolNo;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.tools.ToolDays;
import com.expocms.server.tools.ToolIDCardUtil;
import com.expocms.server.tools.ToolMoney;
import com.expocms.server.util.BankInfo;
import com.expocms.server.util.BankInfoManager;
import com.expocms.server.util.RandomSecurityCode;
import com.expocms.server.util.SyncObjects;
import com.google.common.io.Files;
import com.payment.server.agent.command.params.KQ_BankInfo;
import com.payment.server.agent.command.params.KQ_QuickPaymentInfo;
import com.payment.server.agent.command.params.KQ_QuickPaymentResult;
import com.sms.server.agent.command.params.SMSInfo;

import little.ant.rmi.service.RMIServiceInvoker;

@Service("QUICKPAYMENTHandler")
@Transactional
public class QUICKPAYMENTHandler extends AbsBaseHandler {

	private static Logger logger = Logger.getLogger(QUICKPAYMENTHandler.class);

	@Autowired
	private RdAppuserMapper rdAppuserMapper;

	@Autowired
	private RdOrderMapper rdOrderMapper;

	@Autowired
	private RdActivitygiftMapper rdActivitygiftMapper;

	@Autowired
	private RdFixedgiftMapper rdFixedgiftMapper;

	@Autowired
	private RdProductMapper productMapper;

	@Autowired
	private RdActivitygiftStatMapper activitygiftStatMapper;

	@Autowired
	private RdRepayorderMapper rdRepayorderMapper;

	@Autowired
	private RdNoMapper noMapper;

	@Autowired
	private RdDaysMapper daysMapper;

	@Autowired
	private RdDictMapper dictMapper;

	@Autowired
	private RdContractMapper contractMapper;

	@Autowired
	private RdExceptionOrderMapper rdExceptionOrderMapper;
	
	@Autowired
	private RdAppuserCommissionHistoryMapper rdAppuserCommissionHistoryMapper;

	@Autowired
	@Qualifier("configproperties")
	Properties pro;

	public QUICKPAYMENTHandler() {
		super();
	}

	@Override
	public BaseResponse handleRequest(final IRequest request) {
		returnResp = new CommonResponse();
		QuickPaymentRequest qpRequest = (QuickPaymentRequest)request;
		String userId = null;

		try {
			// get necessary parameter ...
			userId = qpRequest.getUserId();
			if(userId == null || userId.equals("")) {
				returnResp.setResultCode(1);
				returnResp.setResultMsg("用户id不能为空");
				return returnResp;
			}
			
			final String productId = qpRequest.getProductId();
			final String orderId = qpRequest.getOrderId();
			
			logger.info("start payment for user : " + userId + ", product : " + productId + ", order : " + orderId + " ...");
			
			Object userSyncObj = SyncObjects.instance().getUserSyncObject(userId);
			synchronized (userSyncObj) {
				logger.info("enter QUICKPAYMENTHandler user synchronization section ...");
			
				// get user object ...
				RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
				if (rdAppuser == null) {
					logger.error("user " + userId + " does not exist!");
					returnResp.setResultCode(1);
					returnResp.setResultMsg("用户不存在");
					return returnResp;
				}
				
				RdAppuser recommendUser = null;
				String recommendCode = rdAppuser.getRecommendCode();
				if(recommendCode != null && recommendCode.equals("") == false) {
					recommendUser = rdAppuserMapper.selectByPhone(recommendCode);
					if(recommendUser != null) {
						logger.info("user " + userId + " found recommend user " + recommendUser.getIds() + ", recommendCode = " + recommendCode);
					}
				}

				logger.info("user " + userId + " found ...");
	
				// get synchronization object, all payment actions should be
				// protected ...
				Object productSyncObj = SyncObjects.instance().getProductSyncObject(productId);
				synchronized (productSyncObj) {
					logger.info("enter QUICKPAYMENTHandler product synchronization section ...");
	
					// get product object ...
					RdProduct product = productMapper.selectByPrimaryKey(productId);
					if (product == null) {
						logger.error("product " + productId + " does not exist!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("产品不存在");
						return returnResp;
					}
	
					logger.info("product " + productId + " found ...");
	
					// if product status is not in sail, should reject this payment
					// ...
					if (product.getStatus() != RdProduct.PRODUCT_STATUS_IN_SAIL) {
						logger.error("product " + productId
								+ ", is not in sail, please check whether you attached correct productId!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("非在售产品");
						return returnResp;
					}
	
					logger.info(
							"product " + productId + ", could be sold, it's state is in OK (PRODUCT_STATUS_IN_SAIL) ...");
	
					// REMOVED: product can only be bought by one user one time ...
					/*
					 * int productOrderNum =
					 * rdOrderMapper.getProductOrderNumOfCurrentUser(customerId_,
					 * productId); if(productOrderNum != 0) { logger.error(
					 * "product " + productId + " not exist!");
					 * returnResp.setResultCode(1);
					 * returnResp.setResultMsg("已经购买此产品"); return returnResp; }
					 */
	
					// declare necessary arguments ...
					final Date nowDateTime = new Date(System.currentTimeMillis());
					final String spFlag_ = "QuickPay";
					String idType_ = "0";
					String savePciFlag_ = "";
					String payBatch_ = "1";
					String expiredDate_ = "";
					String cvv2_ = "";
	
					long orderAmount = qpRequest.getInvestmentAmount();
					long actualAmount = qpRequest.getInvestmentAmount();
					String externalRefNumber_ = qpRequest.getOrderId();
					final List<QuickPaymentActivityGift> redpacks = qpRequest.getRedPackages();
					final long commissionAmount = qpRequest.getCommissionAmount() != null ? qpRequest.getCommissionAmount() : 0;
	
					String validCode_ = qpRequest.getVerifyCode();
					String token_ = rdAppuser.getToken();
					String cardNo_ = rdAppuser.getBankcardno();
					String storableCardNo_ = "";
					String cardHolderName_ = rdAppuser.getCardHolder();
					String cardHolderId_ = rdAppuser.getIdcard();
					String payPhone_ = rdAppuser.getPhone();
	
					// check already sold amount + current buy amount is greater
					// than product amount, if so, need to reject the payment ...
					long currentSoldAmount = product.getSoldamount() != null ? product.getSoldamount() : 0;
					currentSoldAmount += orderAmount;
					if (currentSoldAmount > product.getProductamount()) {
						logger.error("product " + productId + ", (previous sold amount + invest amount) : "
								+ currentSoldAmount + " exceeds (product amount) : " + product.getProductamount() + "!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("已超出产品可购金额");
						return returnResp;
					}
	
					logger.info(
							"product " + productId + ", could be sold, invest amount is OK, to be continue verified ...");
	
					// check the invest amount meet the MIN/MAX invest limits or
					// not, if not, need to reject the payment ...
					long minInvestAmount = product.getMininvestigateamount() != null ? product.getMininvestigateamount()
							: 0;
					long maxInvestAmount = product.getMaxinvestigateamount() != null ? product.getMaxinvestigateamount()
							: product.getProductamount();
					if (maxInvestAmount == 0)
						maxInvestAmount = product.getProductamount();
					if (orderAmount < minInvestAmount || orderAmount > maxInvestAmount) {
						logger.error("product " + productId + ", invest amount : " + orderAmount
								+ " must in investment scope (MIN:" + minInvestAmount + " - MAX:" + maxInvestAmount + ")!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("投资金额必须在允许起投范围内(" + minInvestAmount + "-" + maxInvestAmount + ")");
						return returnResp;
					}
	
					logger.info("product " + productId + ", could be sold, invest amount is OK ...");
	
					// check start interest date and end interest date is valid or
					// not, if not, need to reject the payment, cause here we can't
					// calculate the period of interest ...
					if (product.getStartinterestdate() == null || product.getEndinterestdate() == null) {
						logger.error("product " + productId + ", start interest date or end interest date is not valid!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("产品信息错误");
						return returnResp;
					}
	
					logger.info("product " + productId
							+ ", could be sold, interest period seems OK, to be continue verified ...");
	
					// create 'Calendar' objects for calculating interest ...
					Calendar startIDate = ToolDays.getCalendar(product.getStartinterestdate());
					Calendar endIDate = ToolDays.getCalendar(product.getEndinterestdate());
					Calendar buyDate = ToolDays.getCalendar(nowDateTime);
					Calendar endDate = ToolDays.getNextDay(endIDate);
	
					// calculate period of interest and check arguments is OK or not
					// ...
					int investDuration = 0;
					try {
						investDuration = calculateInterestPeriod(daysMapper, buyDate, startIDate, endIDate);
					} catch (RuntimeException e) {
						logger.error("product " + productId + ", verified failed, error as " + e.getMessage());
						returnResp.setResultCode(1);
						returnResp.setResultMsg(e.getMessage());
						return returnResp;
					}
	
					logger.info("product " + productId + ", could be sold, interest period is OK ...");
	
					// if invest duration equals and less than 0, then need to
					// reject the payment ...
					if (investDuration <= 0) {
						logger.error("product " + productId + ", investment period is 0!");
						returnResp.setResultCode(1);
						returnResp.setResultMsg("产品投资周期为0");
						return returnResp;
					}
	
					logger.info("product " + productId + ", investment period is OK (greater than 0 day) ...");
	
					// check previously, user had payment or not, if already, then
					// no need bank info and continue, or need to check bank info
					// ...
					//int numberOfOrder = rdOrderMapper.getOrderNumOfCurrentUser(customerId_);
					int numberOfOrder = rdAppuser.getPurchaseNum();
					if (numberOfOrder == 0) {
						// get bank info from previous step ...
						BankInfo bankInfo = BankInfoManager.instance().getBankInfo(userId);
						if (bankInfo != null) {
							cardNo_ = bankInfo.getBankCardNo();
							cardHolderName_ = bankInfo.getBankCardHolderName();
							cardHolderId_ = bankInfo.getBankCardHolderId();
							payPhone_ = bankInfo.getPayPhone();
						}
	
						// check bank info ...
						if (cardNo_ == null || "".equals(cardNo_) || cardHolderName_ == null || "".equals(cardHolderName_)
								|| cardHolderId_ == null || "".equals(cardHolderId_) || payPhone_ == null
								|| "".equals(payPhone_)) {
							logger.error("product " + productId
									+ ", can't continue payment cause information is not enough (bank card no, card holder name, card holder ID)!");
							returnResp.setResultCode(3);
							returnResp.setResultMsg("卡信息参数不全，无法申请支付");
							return returnResp;
						}
	
						storableCardNo_ = cardNo_.substring(0, 6) + cardNo_.substring(cardNo_.length() - 4);
						payBatch_ = "1";
						savePciFlag_ = "1";
					} else {
						BankInfo bankInfo = BankInfoManager.instance().getBankInfo(userId);
						if (bankInfo != null) {
							cardNo_ = bankInfo.getBankCardNo();
							cardHolderName_ = bankInfo.getBankCardHolderName();
							cardHolderId_ = bankInfo.getBankCardHolderId();
							payPhone_ = bankInfo.getPayPhone();
							storableCardNo_ = cardNo_.substring(0, 6) + cardNo_.substring(cardNo_.length() - 4);
						} else {
							cardNo_ = "";
							cardHolderName_ = "";
							cardHolderId_ = "";
							payPhone_ = "";
							storableCardNo_ = "";
						}
	
						payBatch_ = "2";
						savePciFlag_ = "0";
						idType_ = "";
	
						if (("" + RdProduct.PRODUCT_TYPE_NEW_HAND).equals(product.getProducttype())) {
							logger.error("product " + productId
									+ ", product for new hand could only be bought at the 1st time of user " + userId
									+ "!");
							returnResp.setResultCode(3);
							returnResp.setResultMsg("新手专享类产品只能用于首次购买");
							return returnResp;
						}
					}
	
					logger.info("product " + productId + ", payment info verified OK ...");
	
					// verify and calculate RED package and amount ...
					List<OrderGift> orderGiftList = null;
					long redPackageReducedAmount = 0;
					if (redpacks != null && redpacks.size() > 0) {
						for (int i = 0; i < redpacks.size(); i++) {
							QuickPaymentActivityGift acgift = redpacks.get(i);
							if (acgift == null)
								continue;
							// code in request
							final String redPackCode = acgift.getRedPackageCode();
							// package in db
							final RdActivitygift gift = rdActivitygiftMapper.selectByPrimaryKey(redPackCode);
							// not exist
							if (gift == null)
								continue;
	
							if (gift.getRestMoney() > 0 && acgift.getRestMoney() <= gift.getRestMoney()) {
								String giftIds = gift.getGiftId();
								String type = gift.getType();
	
								if ("1".equals(type)) {
									// activity
									RdActivitygiftStat rdActivitygiftStat = activitygiftStatMapper.selectByPrimaryKey(giftIds);
									System.out.println("-------------------------------rdActivitygiftStat  = " + (rdActivitygiftStat == null));
									if (rdActivitygiftStat != null) {
										rdActivitygiftStat.setUsedcount(rdActivitygiftStat.getUsedcount() + 1);
									}
									if (activitygiftStatMapper.updateByPrimaryKey(rdActivitygiftStat) != 1) {
										logger.error("product " + productId + ", update activity RED package into DB failed!");
										throw new RuntimeException("支付失败");
									}
								} else if ("2".equals(type)) {
									RdFixedgift fixedgift = rdFixedgiftMapper.selectByPrimaryKey(giftIds);
									System.out.println("-------------------------------fixedgift  = " + (fixedgift == null));
									if (fixedgift != null) {
										fixedgift.setUsednum(fixedgift.getUsednum() + 1);
									}
									if (rdFixedgiftMapper.updateByPrimaryKey(fixedgift) != 1) {
										logger.error("product " + productId + ", update fixed RED package into DB failed!");
										throw new RuntimeException("支付失败");
									}
								}
	
								long usedMoney = gift.getRestMoney() - acgift.getRestMoney();
								redPackageReducedAmount += usedMoney;

								OrderGift orderGift = new OrderGift();
								orderGift.setRedPackageCode(redPackCode);
								orderGift.setUsedMoney(usedMoney);
								orderGift.setType(type);
								orderGift.setOriginalRedPackageId(giftIds);

								if(orderGiftList == null)
									orderGiftList = new ArrayList<OrderGift>();
								orderGiftList.add(orderGift);
	
								gift.setRestMoney(acgift.getRestMoney());
								if (rdActivitygiftMapper.updateByPrimaryKey(gift) != 1) {
									logger.error("product " + productId + ", update RED package for user " + userId + " into DB failed!");
									throw new RuntimeException("支付失败");
								}
							} else {
								logger.error("product " + productId
										+ ", restMoney calculated by APP wrong, submitted restMoney : "
										+ acgift.getRestMoney() + " greater which in DB : " + gift.getRestMoney() + "!");
								throw new RuntimeException("支付失败");
							}
						}
					}
					
					logger.info("product " + productId + ", RED package calculation OK ...");
					
					// record used red package in this order ...
					QuickPaymentGift quickPaymentGift = null;
					if(orderGiftList != null && orderGiftList.size() != 0) {
						if(quickPaymentGift == null)
							quickPaymentGift = new QuickPaymentGift();
						quickPaymentGift.setOrderGiftList(orderGiftList);
					}
	
					// check RED package amount exceed the allowed percent of
					// payment amount or not ...
					final int max_percent = Constants.packageMaxPercent;
					if (orderAmount * max_percent / 100 < redPackageReducedAmount) {
						logger.error("product " + productId + ", RED package amount exceeds the allowed percent "
								+ max_percent + "% of invest amount!");
						throw new RuntimeException("红包金额不可大于订单金额的" + max_percent + "%");
					}
	
					logger.info("product " + productId + ", RED package percent / invest amount verified OK ...");

					// actual payment amount should exclude the amount from RED
					// package ...
					actualAmount -= redPackageReducedAmount;
					actualAmount -= commissionAmount;
					logger.info("product " + productId + ", invest amount : " + orderAmount +
							", RED package amount : " + redPackageReducedAmount +
							", commission amount : " + commissionAmount +
							", payment amount : " + actualAmount);

					/**
					 * update information into DB ...
					 */
					// update user purchase number and purchase gift (RED package)
					rdAppuser.setPurchaseNum(rdAppuser.getPurchaseNum() + 1);
					String giftId = getPurchaseFixedGift(rdAppuser.getPurchaseNum(), nowDateTime);
					if(giftId != null && giftId.equals("") == false) {
						RedPackage redPackage = new RedPackage();
						redPackage.setRedPackageCode(giftId);
						redPackage.setPurchaseNum(rdAppuser.getPurchaseNum());
						
						if(quickPaymentGift == null)
							quickPaymentGift = new QuickPaymentGift();
						quickPaymentGift.setPurchaseGift(redPackage);
					}
					
					// increase a new order ...
					final RdOrder order = new RdOrder();
					order.setAmount(orderAmount);
					order.setActualamount(actualAmount);
					order.setAppuserid(userId);
	
					// 第一次支付或银行信息有修改的话，需要从缓存的数据中拿五要素 ...
					BankInfo bankInfo = BankInfoManager.instance().getBankInfo(userId);
					if (bankInfo != null) {
						order.setBankname(bankInfo.getBankName());
						order.setBankCardNo(bankInfo.getBankCardNo());
						order.setCardno(bankInfo.getBankCardNo());
						order.setAppusername(bankInfo.getBankCardHolderName());
						order.setAppuseridentity(bankInfo.getBankCardHolderId());
					} else {
						order.setBankname(rdAppuser.getBankname());
						order.setBankCardNo(rdAppuser.getBankcardno());
						order.setCardno(rdAppuser.getBankcardno());
						order.setAppusername(rdAppuser.getUsername());
						order.setAppuseridentity(rdAppuser.getIdcard());
					}
					
					String SEX = "";
					if(order.getAppuseridentity() != null &&
							(order.getAppuseridentity().length() == ToolIDCardUtil.CHINA_ID_MIN_LENGTH ||
							 order.getAppuseridentity().length() == ToolIDCardUtil.CHINA_ID_MAX_LENGTH)) {
						SEX = ToolIDCardUtil.getGenderByIdCard(order.getAppuseridentity());
					}
					
					int age = ToolIDCardUtil.getAgeByIdCard(order.getAppuseridentity());
	
					order.setSex(SEX);
					order.setAge(age);
					order.setProductids(productId);
					order.setAssignmentid(product.getAssignid());
					order.setAssignno(product.getAssignmentno());
					order.setPayamount(actualAmount);
					order.setGiftamount(redPackageReducedAmount);
					order.setIscheckok(0);
					order.setIsdeleted(0);
	
					order.setIntereststartdate(startIDate.getTime());
					order.setInterestenddate(endIDate.getTime());
					order.setEnddate(endDate.getTime());
					order.setPredictrepaydatetime(endDate.getTime());
					order.setDuration(investDuration);
	
					String no = noMapper.getNoByName("rd_order");
					String nextno = ToolNo.getNextNo(no);
					if (noMapper.increaseNoByName("rd_order", nextno) != 1) {
						logger.error("product " + productId + ", order NO generation and saving into DB failed!");
						throw new RuntimeException("支付失败");
					}
					order.setNo(nextno);
					order.setOrdercreatetime(nowDateTime);
					order.setOrdertransfertime(nowDateTime);
					order.setOrdertype("1");
	
					// TODO: calculate interest, need to have more attention here
					// ...
					float interest = product.getInterest() != null ? product.getInterest() : 0;
					long orderReturnM = orderAmount +
							Math.round((double) orderAmount * interest * order.getDuration() / 365 / 100);
					long actualReturnM = actualAmount +
							Math.round((double) actualAmount * interest * order.getDuration() / 365 / 100);
					order.setInterest(interest);
					order.setRepaidamount(orderReturnM);
					order.setCurrentPoperty(actualReturnM);
	
					order.setStatus(RdOrder.ORDER_STATUS_SEALED);
					order.setRepaystatus(0);
					order.setProductname(product.getProductname());
					order.setProductseries(product.getSeries());
					order.setProductno(product.getNo());
					order.setAppUserPhone(rdAppuser.getPhone());
					order.setIds(orderId);
					
					// record used red package in this order ...
					if(quickPaymentGift != null) {
						String orderGiftCode = JSON.toJSONString(quickPaymentGift);
						if(orderGiftCode != null && orderGiftCode.equals("") == false)
							order.setGiftCode(orderGiftCode);
					}
					
					// 生成协议
					String contractContent = GenerateContracts(dictMapper, order, rdAppuser, nowDateTime);
					if(contractContent != null && contractContent.equals("") == false) {
						final File contractfile = new File(Constants.contractfilepath + File.separatorChar + order.getIds() + ".html");
						try {
							Files.write(contractContent.getBytes("GBK"), contractfile);
						} catch(IOException e) {}
						//order.setContractContent(contractContent);
					}
					if (rdOrderMapper.insert(order) != 1) {
						logger.error("product " + productId + ", order creation into DB failed!");
						throw new RuntimeException("支付失败");
					}
	
					logger.info("product " + productId + ", order creation OK ...");
	
					// update user information into DB ...
					rdAppuser.setSex(SEX);
					long totalAssets = rdAppuser.getTotalAssets() != null ? rdAppuser.getTotalAssets() : 0;
					rdAppuser.setTotalAssets(totalAssets + orderReturnM);
					rdAppuser.setLasttransferorderid(order.getIds());
					rdAppuser.setLasttransferorderno(order.getNo());
					rdAppuser.setLasttransfertime(nowDateTime);
					if (rdAppuserMapper.updateByPrimaryKey(rdAppuser) != 1) {
						logger.error("product " + productId + ", update user info into DB failed!");
						throw new RuntimeException("支付失败");
					}
	
					logger.info("product " + productId + ", update user info OK ...");
					
					// insert commission history ...
					if(recommendUser != null) {
						Object recommenderSyncObj = SyncObjects.instance().getUserSyncObject(recommendUser.getIds());
						synchronized(recommenderSyncObj) {
							int commissionPercent = Constants.commissionPercent;
							long commission = Math.round((double) actualAmount * commissionPercent * order.getDuration() / 365 / 100);
							recommendUser.setCommission((recommendUser.getCommission() != null ? recommendUser.getCommission() : 0) + commission);
							if (rdAppuserMapper.updateCommissionByIds(recommendUser) != 1) {
								logger.error("product " + productId + ", update commission into DB failed!");
								throw new RuntimeException("支付失败");
							}
							
							RdAppuserCommissionHistory record = new RdAppuserCommissionHistory();
							record.setIds(ToolUtils.getUuidByJdk(true));
							record.setRecommenderId(recommendUser.getIds());
							record.setCommission(commission);
							record.setOrderId(orderId);
							record.setInvestorId(rdAppuser.getIds());
							record.setInvestAmount(actualAmount);
							record.setInvestPeriod(investDuration);
							record.setInvestorPhone(rdAppuser.getPhone());
							record.setInvestorName(rdAppuser.getCardHolder());
							record.setInvestTime(nowDateTime);
							if (rdAppuserCommissionHistoryMapper.insert(record) != 1) {
								logger.error("product " + productId + ", insert commission record into DB failed!");
								throw new RuntimeException("支付失败");
							}
						}
					}

					// increate 流水 info ...
					RdRepayorder repayorder = new RdRepayorder();
					repayorder.setIds(ToolUtils.getUuidByJdk(true));
					repayorder.setAmount(order.getAmount());
					repayorder.setAppuserid(order.getAppuserid());
					repayorder.setAppusername(order.getAppusername());
					repayorder.setAssignid(order.getAssignmentid());
					repayorder.setAssignno(order.getAssignno());
					repayorder.setGiftamount(order.getGiftamount());
					repayorder.setOrderid(order.getIds());
					repayorder.setOrderno(order.getNo());
					repayorder.setPayamount(order.getPayamount());
					repayorder.setProductid(order.getProductids());
					repayorder.setProductno(order.getProductno());
					repayorder.setResultmsg("交易成功");
					repayorder.setType("1");
					repayorder.setTransfertime(nowDateTime);
					if (rdRepayorderMapper.insert(repayorder) != 1) {
						logger.error("product " + productId + ", repayment info creation into DB failed!");
						throw new RuntimeException("支付失败");
					}
	
					logger.info("product " + productId + ", repayment info creation OK ...");
	
					// update product information into DB ...
					product.setSoldamount(currentSoldAmount);
					if (currentSoldAmount >= product.getProductamount()) {
						product.setStatus(RdProduct.PRODUCT_STATUS_SOLD_OUT);
						product.setOfflineReason(RdProduct.PRODUCT_OFFLINE_FULL_SCALE);
						product.setUnsaleDateTime(nowDateTime);
						// 尝试自动上架同类产品
						if ("1".equals(dictMapper.getValueFromDict("autoOnboard.enabled"))) {
							RMIServiceInvoker rmiInvoker = new RMIServiceInvoker();
							rmiInvoker.autoOnboard(product.getProducttype());
						}
					}
					product.setSuccessrecords((product.getSuccessrecords() != null ? product.getSuccessrecords() : 0) + 1);
					product.setSuccessamount((product.getSuccessamount() != null ? product.getSuccessamount() : 0) + orderAmount);
					int buyerNum = CacheManager.instance().incUserNumOfProduct(rdOrderMapper, productId, userId);
					product.setBuyernum((long)buyerNum);
					// if(productMapper.updateByPrimaryKey(product) != 1) {
					if (productMapper.updateQuickPayment(product) != 1) {
						logger.error("product " + productId + ", product info updating into DB failed!");
						throw new RuntimeException("支付失败");
					}
	
					logger.info("product " + productId + ", product info update OK ...");
	
					logger.info("product " + productId + ", start actual payment now (to KUAI_QIAN) ...");
	
					// quick pay to KUAI_QIAN ...
					//Map<String, String> returnMap = null;
					KQ_QuickPaymentResult qpr = null;
					
					boolean isOK = false;
					Random r = new Random(System.currentTimeMillis());
					int n = Math.abs(r.nextInt()) % 100;
					if(n < Constants.RED_PACKAGE_IFX)
						isOK = true;
					if(isOK == false) {
						qpr = new KQ_QuickPaymentResult();
					} else {
						String testCode = ((QuickPaymentRequest) request).getTestCode();
						if (Constants.TEST_IF_ENABLED && testCode != null && testCode.equals(QuickPaymentRequest.TEST_CODE)) {
							//returnMap = new HashMap<String, String>();
							//returnMap.put("responseCode", Constants.KQ_CODE_TICKET_SUCCESS);
							//returnMap.put("responseCode", Constants.KQ_CODE_TICKET_IN_PROGRESS_1);
							
							qpr = new KQ_QuickPaymentResult();
							qpr.setCode(Constants.KQ_CODE_TICKET_SUCCESS);
						} else {
							/*
							final QuickPayReqEntity qpr = new QuickPayReqEntity(cardNo_, storableCardNo_, expiredDate_, cvv2_,
									ToolMoney.F2Y(actualAmount), externalRefNumber_, userId, cardHolderName_,
									cardHolderId_, spFlag_, idType_, savePciFlag_, payPhone_, payBatch_, validCode_, token_);
							*/
							
							KQ_QuickPaymentInfo qpi = new KQ_QuickPaymentInfo();
							qpi.setBankInfo(new KQ_BankInfo());
							qpi.getBankInfo().setBankCardNo(cardNo_);
							qpi.setStorableCardNo(storableCardNo_);
							qpi.setExpiredDate(expiredDate_);
							qpi.setCvv2(cvv2_);
							qpi.setAmount(actualAmount);
							qpi.setExternalRefNo(externalRefNumber_);
							qpi.setUserId(userId);
							qpi.getBankInfo().setBankCardHolderName(cardHolderName_);
							qpi.getBankInfo().setBankCardHolderId(cardHolderId_);
							qpi.setSpFlag(spFlag_);
							qpi.setIdType(idType_);
							qpi.setSavePciFlag(savePciFlag_);
							qpi.getBankInfo().setPayPhone(payPhone_);
							qpi.setPayBatch(payBatch_);
							qpi.setValidCode(validCode_);
							qpi.setToken(token_);
		
							//RMIServiceInvoker rmiServiceInvoker = new RMIServiceInvoker();
							//returnMap = rmiServiceInvoker.quickpay(qpr);
							
							String url = "http://localhost:" + Constants.TPS_port + "/payment/KQ_QuickPayment";
							String s = OrderManagementTask.sendRequest(url, qpi);
							logger.info("original response from TPS:\n" + s);
							
							qpr = JSON.parseObject(s, KQ_QuickPaymentResult.class);
							
							//if (returnMap == null || returnMap.size() == 0) {
							if(qpr == null) {
								logger.error("product " + productId + ", payment to KUAI_QIAN failed, cause no response from KUAI_QIAN!");
								
								// don't need to change 'buyerNum' of product object, cause at this time, data will be rollback ...
								// TODO: we can't say this product never bought by this user in other order(s) ...
								//CacheManager.instance().decUserNumOfProduct(rdOrderMapper, productId, userId);
								
								((QuickPaymentRequest) request).setOrder(order);
								product.setFailrecords((product.getFailrecords() != null ? product.getFailrecords() : 0) + 1);
								product.setFailamount((product.getFailamount() != null ? product.getFailamount() : 0) + orderAmount);
								((QuickPaymentRequest) request).setProduct(product);
								
								throw new RuntimeException("支付失败：无法得到快钱返回结果信息");
							}
						}
		
						logger.info("product " + productId + ", actual payment (to KUAI_QIAN) finished, and OK ...");
					}
	
					// check information from KUAI_QIAN ...
					//String responseCode = returnMap.get("responseCode");
					String responseCode = qpr.getCode();
					// logger.info("product " + productId + ", payment response from
					// KUAI_QIAN : " + externalRefNumber_ + " result: " +
					// responseCode);
					if (responseCode != null &&
							(Constants.KQ_CODE_TICKET_SUCCESS.equals(responseCode.trim()) || Constants.KQ_CODE_TICKET_IN_PROGRESS_1.equals(responseCode.trim()) || Constants.KQ_CODE_TICKET_IN_PROGRESS_2.equals(responseCode.trim()))) {
						// congratulations, payment OK ...
						if (Constants.KQ_CODE_TICKET_IN_PROGRESS_1.equals(responseCode.trim()) || Constants.KQ_CODE_TICKET_IN_PROGRESS_2.equals(responseCode.trim())) {
							logger.info("product " + productId + ", 支付进行中，请稍后(" + responseCode + ")...");
							
							order.setStatus(RdOrder.ORDER_STATUS_PAYING);
							if (rdOrderMapper.updateOrderStatus(order) != 1) {
								logger.error("product " + productId + ", update order status to 'ORDER_STATUS_PAYING' failed!");
							}
						} else {
							if(giftId != null && giftId.equals("") == false) {
								if (rdAppuser.getFixedgift() != null && rdAppuser.getFixedgift().equals("") == false)
									rdAppuser.setFixedgift(rdAppuser.getFixedgift() + "," + giftId);
								else
									rdAppuser.setFixedgift(giftId);

								if (rdAppuserMapper.updateFixedGiftByIds(rdAppuser) != 1) {
									logger.error("product " + productId + ", add user fixed gift code into DB failed!");
								}
							}
						}
						
						CacheManager.instance().setUserDirty(userId);
	
						// save bank info ...
						// BankInfo bankInfo =
						// BankInfoManager.instance().getBankInfo(customerId_);
						if (bankInfo != null) {
							logger.info("product " + productId + ", save user bank info ...");
							rdAppuser.setIsBankModified(0); // anyway, reset the flag ...
							rdAppuser.setBankname(bankInfo.getBankName());
							rdAppuser.setBankcardno(bankInfo.getBankCardNo());
							rdAppuser.setCardHolder(bankInfo.getBankCardHolderName());
							rdAppuser.setIdcard(bankInfo.getBankCardHolderId());
							rdAppuser.setUsername(bankInfo.getBankCardHolderName());
							rdAppuser.setIssuer(bankInfo.getBankName());
							rdAppuser.setPayPhone(bankInfo.getPayPhone());
							if (rdAppuserMapper.updateBankInfoByIds(rdAppuser) != 1) {
								logger.error("product " + productId + ", update bank info into DB failed!");
							}
						}
						
						String phoneNo = rdAppuser.getPayPhone();
						if(phoneNo == null || phoneNo.equals(""))
							phoneNo = rdAppuser.getPhone();
	
						if (phoneNo != null && phoneNo.equals("") == false) {
							if(giftId != null && giftId.equals("") == false) {
								long giftTotal = 0;
								RdActivitygift gift = rdActivitygiftMapper.selectByPrimaryKey(giftId);
								if(gift != null)
									giftTotal = gift.getValue() != null ? gift.getValue() : 0;

								String smsContent = null;
								if(rdAppuser.getPurchaseNum() == 1)
									smsContent = "恭喜您首笔购买成功，您已激活" + ToolMoney.F2Y(giftTotal) + "元首次投资红包，快登录融道理财查看详情。【融道理财】";
								else if(rdAppuser.getPurchaseNum() == 2)
									smsContent = "恭喜您二次购买成功，您已激活" + ToolMoney.F2Y(giftTotal) + "元再次投资红包，快登录融道理财查看详情。【融道理财】";

								if(smsContent != null && smsContent.equals("") == false) {
									logger.info("product " + productId + ", plan to send the " + rdAppuser.getPurchaseNum() + " time purchase SMS with content \"" + smsContent + "\" ...");
									try {
										sendSMS_via_PINGTAI(phoneNo, smsContent);
										logger.info("product " + productId + ", the " + rdAppuser.getPurchaseNum() + " time purchase SMS sent out ...");
									} catch (RuntimeException e) {
										logger.error("product " + productId + ", " + e.getMessage());
									}
								}
							}

							String smsContent = "恭喜，您于" + Constants.SDF_LONG_CHN.format(nowDateTime) +
												"在融道理财成功购买" + ToolMoney.F2Y(orderAmount) +
												"元" + ToolUtils.convertProductTypeIdToString(product.getProducttype()) +
												"（期限" + investDuration +
												"天，预期年化收益" + interest +
												"%）。【融道理财】";
							logger.info("product " + productId + ", plan to send purchase SMS with content \"" + smsContent + "\" ...");
							try {
								sendSMS_via_PINGTAI(phoneNo, smsContent);
								logger.info("product " + productId + ", purchase SMS sent out ...");
							} catch (RuntimeException e) {
								logger.error("product " + productId + ", " + e.getMessage());
							}
						}
	
						if (Constants.KQ_CODE_TICKET_IN_PROGRESS_1.equals(responseCode.trim()) || Constants.KQ_CODE_TICKET_IN_PROGRESS_2.equals(responseCode.trim())) {
							returnResp.setResultCode(1);
							returnResp.setResultMsg("支付进行中，请稍后(" + responseCode + ")...");
						} else {
							returnResp.setResultCode(Constants.succCode);
							returnResp.setResultMsg(Constants.succMsg);
						}
					} else {
						if (responseCode == null || responseCode.trim().equals(""))
							responseCode = "未知代码";
	
						//String responseMessage = returnMap.get("responseTextMessage");
						String responseMessage = qpr.getDetailMessage();
						if (responseMessage == null || responseMessage.equals(""))
							responseMessage = "未知原因";
	
						logger.error("product " + productId + ", 支付失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
						
						// don't need to change 'buyerNum' of product object, cause at this time, data will be rollback ...
						// TODO: we can't say this product never bought by this user in other order(s) ...
						//CacheManager.instance().decUserNumOfProduct(rdOrderMapper, productId, userId);
						
						((QuickPaymentRequest) request).setOrder(order);
						product.setFailrecords((product.getFailrecords() != null ? product.getFailrecords() : 0) + 1);
						product.setFailamount((product.getFailamount() != null ? product.getFailamount() : 0) + orderAmount);
						((QuickPaymentRequest) request).setProduct(product);
	
						throw new RuntimeException("支付失败，错误码:" + responseCode + "，错误信息:" + responseMessage);
					}
	
					logger.info("leave QUICKPAYMENTHandler product synchronization section.");
				} // end of 'synchronized(productSyncObj) {'
				
				logger.info("leave QUICKPAYMENTHandler user synchronization section.");
			} // end of 'synchronized(userSyncObj) {'

			logger.info("payment for user = " + userId + ", product = " + productId + ", order = " + orderId + " finished.");
		} catch (Exception e) {
			logger.error("exception happened at payment time, " + e.getMessage(), e);
			if (e instanceof RuntimeException) {
				throw new RuntimeException(e.getMessage());
			} else {
				throw new RuntimeException("支付失败");
			}
		} finally {
			BankInfoManager.instance().removeBankInfo(userId);
		}

		return returnResp;
	}

	private String GenerateContracts(
			final RdDictMapper dictMapper,
			final RdOrder order, final RdAppuser rdAppuser,
			final Date nowDateTime) {
		
		if(dictMapper == null)
			return null;
		if(order == null)
			return null;
		if(rdAppuser == null)
			return null;
		
		String contractContent = null;
		
		String seq = dictMapper.getValueFromDict("contract.assign");
		if (seq != null) {
			try {
				int seq_ = Integer.parseInt(seq);
				contractContent = contractMapper.queryContentBySeq(seq_);
			} catch (Exception e) {}
		}
		
		if(contractContent == null || contractContent.equals(""))
			return null;
		
		// 签署日期
		assert(nowDateTime != null);
		contractContent = contractContent.replace("$_signdate_$", Constants.SDF_SHORT_CHN.format(nowDateTime));
		// 用户名
		assert(order.getAppusername() != null);
		contractContent = contractContent.replace("$_owner_$", order.getAppusername());
		// 用户证件号
		assert(order.getAppuseridentity() != null);
		contractContent = contractContent.replace("$_ownerid_$", order.getAppuseridentity());
		// 出借金额 - 人民币大写
		assert(order.getAmount() != null);
		contractContent = contractContent.replace("$_loan_RMB_$", ToolMoney.F2ChineseY(order.getAmount()));
		// 出借金额
		assert(order.getAmount() != null);
		contractContent = contractContent.replace("$_loan_$", ToolMoney.F2Y(order.getAmount()));
		// 起息日
		assert(order.getIntereststartdate() != null);
		contractContent = contractContent.replace("$_cfrom_day_$", Constants.SDF_SHORT_CHN.format(order.getIntereststartdate()));
		// 到期日
		assert(order.getInterestenddate() != null);
		contractContent = contractContent.replace("$_cend_day_$", Constants.SDF_SHORT_CHN.format(order.getInterestenddate()));
		// 银行名
		assert(order.getBankname() != null);
		contractContent = contractContent.replace("$_issuer_$", order.getBankname());
		// 银行卡号
		assert(order.getBankCardNo() != null);
		contractContent = contractContent.replace("$_bankaccount_$", order.getBankCardNo());
		// 计息周期
		assert(order.getDuration() != null);
		contractContent = contractContent.replace("$_closeduration_$", "" + order.getDuration());
		// 第七大条第一小条：用户预期本息收入 / 订单金额（实际支付+红包支付）
		assert(order.getRepaidamount() != null);
		if(order.getAmount() != 0)
			contractContent = contractContent.replace("$_percent_$", String.format("%#.3f", (float)(order.getRepaidamount() * 100 / order.getAmount())));
		else
			contractContent = contractContent.replace("$_percent_$", String.format("%#.3f", (float)0));
		// 年化借款利率不低于____/年
		assert(order.getInterest() != null);
		contractContent = contractContent.replace("$_interest_$", "" + order.getInterest());
		
		return contractContent;
	}

	@Override
	public void handleException(IRequest request) {
		if (request == null)
			return;

		QuickPaymentRequest qpRequest = (QuickPaymentRequest) request;

		RdOrder order = qpRequest.getOrder();
		if (order != null) {
			if (rdExceptionOrderMapper.insert(order) != 1)
				logger.error("product " + order.getProductids() + ", exception order creation into DB failed!");
			else
				logger.info("product " + order.getProductids() + ", exception order inserted into DB.");
		}

		RdProduct product = qpRequest.getProduct();
		if (product != null) {
			if (productMapper.updateQuickPaymentFailed(product) != 1)
				logger.error("product " + product.getIds() + ", failed product updating into DB failed!");
			else
				logger.info("product " + product.getIds() + ", failed product updated into DB.");
		}
	}

	/**
	 * Method documentation to be filled TODO
	 *
	 * @return
	 */
	private String getPurchaseFixedGift(final int type, final Date nowDateTime) {
		RdFixedgift rdFixedgift = null;
		if(type == 1)
			rdFixedgift = rdFixedgiftMapper.selectByType(Constants.purchaseFixedGift);
		else if(type == 2)
			rdFixedgift = rdFixedgiftMapper.selectByType(Constants.purchase2FixedGift);

		if (rdFixedgift == null ||
				rdFixedgift.getStatus() == null ||
				rdFixedgift.getStatus().intValue() == Constants.fixedgiftDisabled) {
			logger.info("no available purchase red package found!");
			return null;
		}

		// 首次或二次购买
		if (type == 1 || type == 2) {
			Long result = rdFixedgift.getAmount();

			int activatedNum = rdFixedgift.getActivatednum() != null ? rdFixedgift.getActivatednum() : 0;
			rdFixedgift.setActivatednum(activatedNum + 1);

			if (rdFixedgiftMapper.updateByPrimaryKeySelective(rdFixedgift) != 1) {
				logger.error("update red package into DB failed!");
				throw new RuntimeException("更新红包失败");
			}

			final Integer expiredays = rdFixedgift.getExpiredays();
			final Date expireDate = ToolUtils.getDate(expiredays.intValue());
			String ids = String.valueOf(RandomSecurityCode.getSecurityCodeDefault());
			final RdActivitygift record = new RdActivitygift();

			record.setIds(ids);
			record.setCreatedate(nowDateTime);
			record.setValue(result);
			if (type == 1)
				record.setActivityname(Constants.purchaseFixedGiftString);
			else // type == 2
				record.setActivityname(Constants.purchase2FixedGiftString);
			record.setIsactivated(true);
			record.setIsused(false);
			record.setType("2");
			record.setExpiredate(expireDate);
			record.setActivatedate(nowDateTime);
			record.setActivateTime(new Timestamp(System.currentTimeMillis()));
			record.setRestMoney(result);
			record.setGiftId(rdFixedgift.getIds());

			if (rdActivitygiftMapper.insert(record) != 1) {
				logger.error("insert red package into DB failed!");
				throw new RuntimeException("分配红包失败");
			}

			return ids;
		}

		return null;
	}

	public static int calculateInterestPeriod(final RdDaysMapper daysMapper, final Calendar buyDate,
			Calendar startIDate, final Calendar endIDate) {

		if (daysMapper == null)
			throw new RuntimeException("invalid argument");
		if (buyDate == null)
			throw new RuntimeException("invalid argument");
		if (startIDate == null || endIDate == null)
			throw new RuntimeException("invalid argument");

		// if buy date after end interest date, then can't continue buy ...
		if (buyDate.after(endIDate)) {
			logger.error("购买日大于到期日");
			throw new RuntimeException("购买日大于到期日");
		}

		// get next date of buy date, to take it as the expected start interest
		// date, but need more check, ...
		Calendar nextDate = ToolDays.getNextDay(buyDate);

		/**
		 * if today date greater then start date, then need to calculate real
		 * start date ... else, direct use start date ...
		 */
		if (nextDate.after(startIDate)) {
			RdDays rdDay = ToolDays.instance().findClosedWorkingDay(daysMapper, nextDate);
			if (rdDay == null) {
				logger.error("查找最近的工作日失败");
				throw new RuntimeException("查找最近的工作日失败");
			}

			startIDate.set(Calendar.YEAR, rdDay.getYear());
			startIDate.set(Calendar.DAY_OF_YEAR, rdDay.getDayOfYear());
			startIDate.set(Calendar.HOUR_OF_DAY, 0);
			startIDate.set(Calendar.MINUTE, 0);
			startIDate.set(Calendar.SECOND, 0);
			startIDate.set(Calendar.MILLISECOND, 0);
		}

		// if start interest date greater than end interest date, ...
		if (startIDate.after(endIDate)) {
			logger.error("起息日大于到期日");
			throw new RuntimeException("起息日大于到期日");
		}

		return ToolDays.getDatesBetweenOffset(endIDate, startIDate) + 1;
	}

	public static void sendSMS_via_PINGTAI(final String phone, final String smsContent) {
		if (phone == null || phone.equals(""))
			return;
		if (smsContent == null || smsContent.equals(""))
			return;

		/*
		try {
			RMIServiceInvoker smsServiceInvoker = new RMIServiceInvoker();
			SMSMsgEntity smsMsgEntity = new SMSMsgEntity();
			smsMsgEntity.setMsisdn(phone);
			smsMsgEntity.setMessage(smsContent);

			if (smsServiceInvoker.SendSMS(1, smsMsgEntity) == false) {
				throw new RuntimeException("send SMS to " + phone + " failed!");
			}
		} catch (Exception e) {
			throw new RuntimeException("exception happened while send SMS, " + e.getMessage());
		}
		*/
		
		String url = "http://localhost:" + Constants.TPS_port + "/sms/sendTriggerSMS";
		SMSInfo sms = new SMSInfo();
		sms.setPhoneNo(phone);
		sms.setSmsContent(smsContent);
		String s = OrderManagementTask.sendRequest(url, sms);
		
		if(s == null || s.equals(""))
			throw new RuntimeException("send SMS to " + phone + " failed!");
		logger.info("original response from TPS:\n" + s);
	}

}
