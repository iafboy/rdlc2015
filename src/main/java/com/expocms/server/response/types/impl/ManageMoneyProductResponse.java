package com.expocms.server.response.types.impl;

import java.util.List;

import com.expocms.server.response.types.inf.BaseResponse;

public class ManageMoneyProductResponse extends BaseResponse {
	
	private int orderCount;
	private int alreadyRepaymentNumber;
	private long alreadypaidMoney;
	private int alreadySellOutNumber;
	private long alreadyRepaymentMoney;	
	private List<AlSelling> selling;
	private List<AlRepayment> alreadyRepayment;
	private List<AlSellOut> alreadySellOut;
	
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	
	public int getAlreadyRepaymentNumber() {
		return alreadyRepaymentNumber;
	}
	public void setAlreadyRepaymentNumber(int alreadyRepaymentNumber) {
		this.alreadyRepaymentNumber = alreadyRepaymentNumber;
	}
	
	public long getAlreadyRepaymentMoney() {
		return alreadyRepaymentMoney;
	}
	public void setAlreadyRepaymentMoney(long alreadyRepaymentMoney) {
		this.alreadyRepaymentMoney = alreadyRepaymentMoney;
	}
	
	public int getAlreadySellOutNumber() {
		return alreadySellOutNumber;
	}
	public void setAlreadySellOutNumber(int alreadySellOutNumber) {
		this.alreadySellOutNumber = alreadySellOutNumber;
	}
	
	public long getAlreadypaidMoney() {
		return alreadypaidMoney;
	}
	public void setAlreadypaidMoney(long alreadypaidMoney) {
		this.alreadypaidMoney = alreadypaidMoney;
	}
	
	public List<AlSelling> getSelling() {
		return selling;
	}
	public void setSelling(List<AlSelling> selling) {
		this.selling = selling;
	}
	
	public List<AlRepayment> getAlreadyRepayment() {
		return alreadyRepayment;
	}
	public void setAlreadyRepayment(List<AlRepayment> alreadyRepayment) {
		this.alreadyRepayment = alreadyRepayment;
	}
	
	public List<AlSellOut> getAlreadySellOut() {
		return alreadySellOut;
	}
	public void setAlreadySellOut(List<AlSellOut> alreadySellOut) {
		this.alreadySellOut = alreadySellOut;
	}
	
}
