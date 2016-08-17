package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdActivitygiftMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdFixedgiftMapper;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdFixedgift;
import com.expocms.server.request.types.impl.RedPackageRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.RedPackageResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolMoney;

import little.ant.payment.pojo.RedPackageEntity;

@Service("REDPACKAGEHandler")
@Transactional
public class REDPACKAGEHandler extends AbsBaseHandler {	
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdFixedgiftMapper rdFixedgiftMapper;
	
	@Autowired
    private RdActivitygiftMapper rdGiftMapper;
	
	public REDPACKAGEHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		RedPackageResponse returnResp = new RedPackageResponse();
		RedPackageRequest redPackageRequest = (RedPackageRequest)request;
		
		String userId = redPackageRequest.getUserId();
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		/*
		RMIServiceInvoker rmiInvoker = new RMIServiceInvoker();
		List<RedPackageEntity> redPackages = rmiInvoker.listRedPackage(userId);
		long redPackageMoney = 0;
		
		if (redPackages != null) {		
			for (RedPackageEntity redPackage : redPackages) {
				long restMoney = redPackage.getRestMoney() != null ? redPackage.getRestMoney() : 0;
				redPackageMoney = redPackageMoney + restMoney;
			}
		}
		*/
		
		List<RdActivitygift> gifts = PREPAREBUYHandler.queryUserGifts(rdAppuser, rdGiftMapper, false);
		
		List<RedPackageEntity> redPackages = null;
		long redPackageMoney = 0;
		if(gifts != null && gifts.size() != 0) {
			for(int i = 0; i < gifts.size(); i ++) {
				RdActivitygift gift = gifts.get(i);
				RedPackageEntity rpe = new RedPackageEntity();
				
				rpe.setRedPackageCode(gift.getIds());
				rpe.setActive(gift.getIsactivated() != null ? (String.valueOf(gift.getIsactivated())) : "false");
				rpe.setMoney(gift.getValue() != null ? gift.getValue() : 0);
				String activeDate = gift.getActivatedate() != null ? Constants.SDF_SHORT.format(gift.getActivatedate()) : "";
				String expireDate = gift.getExpiredate() != null ? Constants.SDF_SHORT.format(gift.getExpiredate()) : "";
				rpe.setValidDate(activeDate + "至" + expireDate);
				rpe.setRestMoney(gift.getRestMoney() != null ? gift.getRestMoney() : 0);
				
				redPackageMoney += rpe.getRestMoney();
				if(redPackages == null)
					redPackages = new ArrayList<RedPackageEntity>();
				redPackages.add(rpe);
			}
		}
		
		returnResp.setRedPackageList(redPackages);
		returnResp.setRedPackageMoney(redPackageMoney);
		
		
		long registGiftAmount = 0;
		long purchase1GiftAmount = 0;
		long purchase2GiftAmount = 0;
		List<RdFixedgift> giftList = rdFixedgiftMapper.getAllFixedGifts();
		if(giftList != null && giftList.size() != 0) {
			for(int i = 0; i < giftList.size(); i ++) {
				RdFixedgift gift = giftList.get(i);
				if(gift == null)
					continue;
				if("1".equals(gift.getType()))
					registGiftAmount = gift.getAmount() != null ? gift.getAmount() : 0;
				if("2".equals(gift.getType()))
					purchase1GiftAmount = gift.getAmount() != null ? gift.getAmount() : 0;
				if("3".equals(gift.getType()))
					purchase2GiftAmount = gift.getAmount() != null ? gift.getAmount() : 0;
			}
		}
		
		String s = "";
		if(registGiftAmount != 0) {
			s += "注册成功送" + ToolMoney.F2Y(registGiftAmount) + "元";
		}
		if(purchase1GiftAmount != 0) {
			if(s != null && s.equals("") == false)
				s += "+";
			s += "首次投资送" + ToolMoney.F2Y(purchase1GiftAmount) + "元";
		}
		if(purchase2GiftAmount != 0) {
			if(s != null && s.equals("") == false)
				s += "+";
			s += "再次投资送" + ToolMoney.F2Y(purchase2GiftAmount) + "元";
		}
		
		returnResp.setRedPackageUseState(s);
		returnResp.setRedPackageRewards(
				ToolMoney.F2Y(registGiftAmount + purchase1GiftAmount + purchase2GiftAmount) +
				"元新人礼包奖励办法");
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		
		return returnResp;
	}

}
