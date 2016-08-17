package com.expocms.server.core.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdActivitygiftStatMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdActivitygiftStat;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.RedPackageExchangeRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.RedPackageExchangeResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;
import com.expocms.server.tools.ToolMoney;

import little.ant.payment.pojo.RedPackageEntity;

@Service("REDPACKAGEEXCHANGEHandler")
@Transactional
public class REDPACKAGEEXCHANGEHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(REDPACKAGEEXCHANGEHandler.class);
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
    private RdActivitygiftMapper rdGiftMapper;
	
	@Autowired
    private RdActivitygiftStatMapper rdGiftStatMapper;
	
	public REDPACKAGEEXCHANGEHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		RedPackageExchangeResponse returnResp = new RedPackageExchangeResponse();
		RedPackageExchangeRequest redPackageExchangeRequest = (RedPackageExchangeRequest)request;
		
		String userId = redPackageExchangeRequest.getUserId();
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		String rpCode = redPackageExchangeRequest.getRedPackage();
		if(rpCode == null || rpCode.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("红包id不正确");
			return returnResp;
		}
		
		/*
		if(rdAppuser.getActivitygift() == null || rdAppuser.getActivitygift().indexOf(rpCode) == -1) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("红包不属于当前用户");
			return returnResp;
		}
		*/
		
		RdActivitygift actGift = rdGiftMapper.selectByPrimaryKey(rpCode);
		if(actGift == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("红包id不存在");
			return returnResp;
		}
		
		String orgRPcode = actGift.getGiftId();
		if(orgRPcode == null || orgRPcode.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("原始红包id为空");
			return returnResp;
		}
		
		RdActivitygiftStat orgGift = rdGiftStatMapper.selectByPrimaryKey(orgRPcode);
		if(orgGift == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("原始红包id不存在");
			return returnResp;
		}
		
		
		/*
		RMIServiceInvoker rmiInvoker = new RMIServiceInvoker();
		String message = "";
		int code = 0 ;
		int flag = rmiInvoker.applyRedPackage(userId, redPackageExchangeRequest.getRedPackage());
		if(flag == 0) {
			message = "红包兑换成功";
		} else if(flag == 1) {
			code = 1;
			message = "红包兑换失败";
		} else if(flag == 2) {
			code = 2;
			message = "红包已过有效期";
		} else if(flag == 3) {
			code = 3;
			message = "红包不存在或已被兑换";
		} else {
			code = 4;
			message = "红包状态不正常";
		}
		*/
		
		/*
		List<RedPackageEntity> redPackages = rmiInvoker.listRedPackage(userId);
		long redPackageMoney = 0;

		if(redPackages == null) {
			redPackages = new ArrayList<RedPackageEntity>();
		} else {
			for(RedPackageEntity redPackage : redPackages) {
				long restMoney = redPackage.getRestMoney() != null ? redPackage.getRestMoney() : 0;
				redPackageMoney = redPackageMoney + restMoney;
				if(redPackageExchangeRequest.getRedPackage().equals(redPackage.getRedPackageCode())) {
					returnResp.setRedPackageList(redPackage);
				}
			}
		}
		*/
		
		/*
		RdActivitygift rdGift = rdGiftMapper.selectByPrimaryKey(redPackageExchangeRequest.getRedPackage());
		String rdGiftStatIds = rdGift.getGiftId();
		RdActivitygiftStat rdGiftStat = rdGiftStatMapper.selectByPrimaryKey(rdGiftStatIds);
		int expiredays =  rdGiftStat.getExpiredays();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, expiredays);
		final Date expireDate = cal.getTime();
		
		RedPackageEntity rpe = new RedPackageEntity();
		
		rpe.setRedPackageCode(rdGift.getIds());
		rpe.setActive("true");
		rpe.setMoney(rdGift.getValue() != null ? rdGift.getValue() : 0);
		String activeDateString = rdGift.getActivatedate() != null ? Constants.SDF_SHORT.format(rdGift.getActivatedate()) : "";
		String expireDateString = Constants.SDF_SHORT.format(expireDate);
		rpe.setValidDate(activeDateString + "至" + expireDateString);
		rpe.setRestMoney(rdGift.getRestMoney() != null ? rdGift.getRestMoney() : 0);
		
		returnResp.setRedPackageList(rpe);
		*/
		
		
		int flag = 0;
		int expireDays = orgGift.getExpiredays() != null ? orgGift.getExpiredays() : 0;
		if(expireDays <= 0)
			flag = 4;
		
		Calendar today = ToolDays.getTodayCalendar();
		Calendar expireDate = ToolDays.getDayAfter(today, expireDays - 1);
		if(flag == 0) {
			if(today.after(expireDate))
				flag = 2;
			if(actGift.getIsactivated() != null && actGift.getIsactivated().booleanValue())
				flag = 3;
			
			if(actGift.getActivatedate() != null) {
				Calendar activeDate = ToolDays.getCalendar(actGift.getActivatedate());
				if(today.after(activeDate))
					flag = 2;
			}
		}
		
		
		// 兑换红包 ...
		if(flag == 0) {
			// 更新红包 ...
			actGift.setValue(orgGift.getValue() != null ? orgGift.getValue() : 0);
			actGift.setRestMoney(orgGift.getValue() != null ? orgGift.getValue() : 0);
			actGift.setIsactivated(true);
			actGift.setIsused(false);
			actGift.setActivatedate(today.getTime());
			actGift.setExpiredate(expireDate.getTime());
			actGift.setActivateTime(new Timestamp(System.currentTimeMillis()));
			if(rdGiftMapper.updateByPrimaryKey(actGift) != 1) {
				logger.error("更新红包失败");
				flag = 1;
				throw new RuntimeException("红包兑换失败");
			}
			
			if(flag == 0) {
				// 更新原始红包 ...
				orgGift.setActivecount((orgGift.getActivecount() != null ? orgGift.getActivecount() : 0) + 1);
				if(rdGiftStatMapper.updateByPrimaryKey(orgGift) != 1) {
					logger.error("更新原始红包失败");
					flag = 1;
					throw new RuntimeException("红包兑换失败");
				}
			}
			
			if(flag == 0) {
				// 更新用户 ...
				if(rdAppuser.getActivitygift() != null && rdAppuser.getActivitygift().equals("") == false)
					rdAppuser.setActivitygift(rdAppuser.getActivitygift() + "," + rpCode);
				else
					rdAppuser.setActivitygift(rpCode);
				if(rdAppuserMapper.updateActivityGiftByIds(rdAppuser) != 1) {
					logger.error("更新用户失败");
					flag = 1;
					throw new RuntimeException("红包兑换失败");
				}
			}
		}
		

		// 枚举红包金额 和 当前兑换的红包 ...
		List<RdActivitygift> gifts = PREPAREBUYHandler.queryUserGifts(rdAppuser, rdGiftMapper, false);
		long redPackageMoney = 0;
		RedPackageEntity rpe = null;
		if(gifts != null && gifts.size() != 0) {
			for(int i = 0; i < gifts.size(); i ++) {
				RdActivitygift gift = gifts.get(i);
				
				if(rpCode.equals(gift.getIds()) && flag != 0) {
					logger.error("兑换失败的红包不应该参与红包金额计算 = " + rpCode);
					continue;
				}
				
				if(rpCode.equals(gift.getIds()) && flag == 0) {
					if(rpe != null) {
						logger.error("奇怪，难道同id的红包有多个？ = " + rpCode);
						continue;
					}
					
					rpe = new RedPackageEntity();
					rpe.setRedPackageCode(gift.getIds());
					rpe.setActive(gift.getIsactivated() != null ? (String.valueOf(gift.getIsactivated())) : "false");
					rpe.setMoney(gift.getValue() != null ? gift.getValue() : 0);
					String activeDateStr = gift.getActivatedate() != null ? Constants.SDF_SHORT.format(gift.getActivatedate()) : "";
					String expireDateStr = gift.getExpiredate() != null ? Constants.SDF_SHORT.format(gift.getExpiredate()) : "";
					rpe.setValidDate(activeDateStr + "至" + expireDateStr);
					rpe.setRestMoney(gift.getRestMoney() != null ? gift.getRestMoney() : 0);
				}
				
				long restMoney = gift.getRestMoney() != null ? gift.getRestMoney() : 0;
				redPackageMoney += restMoney;
			}
		}
		returnResp.setRedPackageMoney(redPackageMoney);
		returnResp.setRedPackageList(rpe);
		
		
		if(flag == 0) {
			String phoneNo = rdAppuser.getPhone();
			if(phoneNo != null && phoneNo.equals("") == false) {
				String smsContent = "恭喜您成功领取" + ToolMoney.F2Y(rpe.getMoney()) +
									"元融道红包，此红包有效期：" + rpe.getValidDate() +
									"，有效期" + expireDays +
									"天，请您在有效期内使用，详情请登录融道理财APP（我的奖励）查看。【融道理财】";
				try {
					QUICKPAYMENTHandler.sendSMS_via_PINGTAI(phoneNo, smsContent);
				} catch(RuntimeException e) {
					logger.error("exception happened while exchange RED package to customer " + phoneNo + "! " + e.getMessage());
				}
			}
		}

		
		String message = "";
		int code = 0 ;
		if(flag == 0) {
			message = "红包兑换成功";
		} else if(flag == 1) {
			code = 1;
			message = "红包兑换失败";
		} else if(flag == 2) {
			code = 2;
			message = "红包已过有效期";
		} else if(flag == 3) {
			code = 3;
			message = "红包不存在或已被兑换";
		} else {
			code = 4;
			message = "红包状态不正常";
		}

		returnResp.setResultCode(code);
		returnResp.setResultMsg(message);
		return returnResp;
	}

}
