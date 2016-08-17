package com.expocms.server.core.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdBankMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdBank;
import com.expocms.server.request.types.impl.LoginRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.LoginResponse;
import com.expocms.server.response.types.impl.MyBankCard;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;
import com.google.common.collect.Lists;

@Service("LOGINHandler")
@Transactional
public class LOGINHandler extends AbsBaseHandler {	
	
	private static Logger logger = Logger.getLogger(LOGINHandler.class);
	
	private final int MAX_LOGIN_PWD_WRONG_ALLOWED_TIMES = 6;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdBankMapper rdBankMapper;
	
	public LOGINHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		LoginResponse returnResp = new LoginResponse();
		
		String phoneNo = ((LoginRequest)request).getPhoneNo();
		String password = ((LoginRequest)request).getPassword();
		
		RdAppuser rdappuser = rdAppuserMapper.selectByPhone(phoneNo);
		if(rdappuser == null){
			logger.error("手机号未被注册");
			returnResp.setResultCode (2);
	        returnResp.setResultMsg("手机号未被注册");
			return returnResp;
		}
		
		if(rdappuser.getIsblock()) {
			logger.error("账号已锁：" + phoneNo);
			returnResp.setResultCode (2);
	        returnResp.setResultMsg("账号已锁");
			return returnResp;
		}
		
		// get today ...
		Calendar today = ToolDays.getTodayCalendar();
		
		// handle case that acount blocked caused of password wrong more than 6 times ...
		if(rdappuser.getBlockCount() != null && rdappuser.getBlockCount() >= MAX_LOGIN_PWD_WRONG_ALLOWED_TIMES) {
			// if block count is equals or greater than 6 times ...
			if(rdappuser.getUnblockDate() != null && ToolDays.compareDate(today.getTime(), rdappuser.getUnblockDate()) < 0) {
				returnResp.setResultCode (1);
		        returnResp.setResultMsg("密码输入错误达到" + MAX_LOGIN_PWD_WRONG_ALLOWED_TIMES + "次，账户已锁");
				return returnResp;
			}
		}
		
		if(rdappuser.getPwd().equals(password) == false) {
			logger.error("密码不正确：" + password);
			
			// handle case that password failed ...
			if(rdappuser.getUnblockDate() != null && ToolDays.compareDate(today.getTime(), rdappuser.getUnblockDate()) >= 0) {
				// if there's unblock date and today is equals or after unblock date, then need to set block count to 1 ...
				rdappuser.setBlockCount(1);
				rdappuser.setBlockTime(new Date());
				rdappuser.setUnblockDate(ToolDays.getNextDay(today).getTime());
			} else {
				// increase block count, and set unblock date to tomorrow ...
				rdappuser.setBlockCount((rdappuser.getBlockCount() != null ? rdappuser.getBlockCount() : 0) + 1);
				rdappuser.setBlockTime(new Date());
				rdappuser.setUnblockDate(ToolDays.getNextDay(today).getTime());
			}
			if(rdAppuserMapper.updatePwdBlockInfoByIds(rdappuser) != 1) {
				logger.error("记录密码输入错误信息（次数/时间/解锁日期）失败！");
			}

			returnResp.setLoginCount(rdappuser.getBlockCount() != null ? rdappuser.getBlockCount() : 0);
			
			returnResp.setResultCode (1);
	        returnResp.setResultMsg("密码不正确");
			return returnResp;
		}
		
		// clear password wrong info if password is correct ...
		if(rdappuser.getBlockCount() != null && rdappuser.getBlockCount() > 0) {
			rdappuser.setBlockCount(0);
			rdappuser.setBlockTime(null);
			rdappuser.setUnblockDate(null);
			if(rdAppuserMapper.updatePwdBlockInfoByIds(rdappuser) != 1) {
				logger.error("清除密码输入错误信息（次数/时间/解锁日期）失败！");
			}
		}
		
		returnResp.setLoginCount(rdappuser.getBlockCount() != null ? rdappuser.getBlockCount() : 0);
		
		returnResp.setUserId(rdappuser.getIds());
		if(rdappuser.getUsername() != null)
			returnResp.setName(rdappuser.getUsername());
		if(rdappuser.getCardHolder() != null)
			returnResp.setName(rdappuser.getCardHolder());
		if(rdappuser.getIdcard() != null)
			returnResp.setIdCard(rdappuser.getIdcard());
		
		final List<MyBankCard> myBankCard = Lists.newArrayList();
		final MyBankCard mbc = new MyBankCard();
		mbc.setBankCardId(rdappuser.getBankcardno());
		
		Long bankQuota = rdappuser.getBankQuota();
		String supportphone = rdappuser.getSupportPhone();
		if(rdappuser.getBankname() != null && rdappuser.getBankname().equals("") == false) {
			RdBank rdBank = rdBankMapper.selectByBankName(rdappuser.getBankname());
			if(rdBank != null) {
				bankQuota = rdBank.getLimitPerPurchase();
				supportphone = rdBank.getSupportPhone();
			}
		}
		
		mbc.setBankQuota(bankQuota);
		mbc.setIssuer(rdappuser.getBankname());
		mbc.setSupportPhone(supportphone);
		myBankCard.add(mbc);
		returnResp.setMyBankCard(mbc);
		
		//int orderCount = rdOrderMapper.getOrderNumOfCurrentUser(rdappuser.getIds());
		int orderCount = rdappuser.getPurchaseNum();
		returnResp.setOrderCount(orderCount);
		
		returnResp.setResultCode (Constants.succCode);
        returnResp.setResultMsg (Constants.succMsg);
		return returnResp;
	}

}
