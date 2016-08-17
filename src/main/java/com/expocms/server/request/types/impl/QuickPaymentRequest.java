package com.expocms.server.request.types.impl;

import java.util.ArrayList;
import java.util.List;

import com.expocms.server.db.model.RdOrder;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.QuickPaymentActivityGift;

public class QuickPaymentRequest implements IRequest {
	
	public static final String TEST_CODE = "THIS is thE tEst code For QuickPaymentRequest";
	
	private String userId;
	private String productId;
	private String orderId;
	private Long investmentAmount;
	private String verifyCode;
	private List<QuickPaymentActivityGift> redPackages = new ArrayList<QuickPaymentActivityGift>();
	private Long commissionAmount;
	
	private String testCode;
	private RdOrder order;
	private RdProduct product;

	public boolean validate() {
		if(userId != null && !"".equals(userId) &&
				productId != null && !"".equals(productId.trim()) &&
				orderId != null && !"".equals(orderId) &&
				investmentAmount.longValue() > -1 &&
				verifyCode != null && !"".equals(verifyCode))
			return true;
		return false;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Long getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public List<QuickPaymentActivityGift> getRedPackages() {
		return redPackages;
	}
	public void setRedPackages(List<QuickPaymentActivityGift> redPackages) {
		this.redPackages = redPackages;
	}
	
	public Long getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(Long commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	
	public RdOrder getOrder() {
		return order;
	}
	public void setOrder(RdOrder order) {
		this.order = order;
	}
	
	public RdProduct getProduct() {
		return product;
	}
	public void setProduct(RdProduct product) {
		this.product = product;
	}
	
}
