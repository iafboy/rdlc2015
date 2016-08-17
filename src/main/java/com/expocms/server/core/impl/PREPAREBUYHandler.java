package com.expocms.server.core.impl;

import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.*;
import com.expocms.server.db.model.RdActivitygift;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdBank;
import com.expocms.server.request.types.impl.PrepareBuyRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.impl.PrepareBuyActivityGift;
import com.expocms.server.response.types.impl.PrepareBuyResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service ("PREPAREBUYHandler")
@Transactional
public class PREPAREBUYHandler extends AbsBaseHandler {

    @Autowired
    private RdAppuserMapper rdAppuserMapper;

    @Autowired
    private RdActivitygiftMapper rdActivitygiftMapper;

    @Autowired
    private RdDictMapper dictMapper;

    @Autowired
    private RdContractMapper contractMapper;
    
    @Autowired
	private RdBankMapper rdBankMapper;

    @Override
    public BaseResponse handleRequest (final IRequest request) {
        final CommonResponse returnResp = new CommonResponse();
        
        // 1. nessary parameter
        final String userId = ((PrepareBuyRequest)request).getUserId();
        if(userId == null || userId.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
        
        // 2. query app user
        final RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
        if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
        
        // 3. get bank No
        String bankNo = rdAppuser.getBankcardno();
        String storableCardNo_;
        if(bankNo != null && bankNo.length() > 4){
            storableCardNo_ = bankNo.substring(bankNo.length() - 4);
        }else{
            storableCardNo_ = "";
        }
        
        PrepareBuyResponse response = new PrepareBuyResponse();
        if(rdAppuser.getBankname() != null && !"".equals(rdAppuser.getBankname().trim())) {
        	response.setBankInfo1(rdAppuser.getBankname().trim() + "(尾号" + storableCardNo_ + ")");
        }
        
        Long bankQuota = rdAppuser.getBankQuota();
        if(rdAppuser.getBankname() != null && rdAppuser.getBankname().equals("") == false) {
        	RdBank rdBank = rdBankMapper.selectByBankName(rdAppuser.getBankname());
			if(rdBank != null)
				bankQuota = rdBank.getLimitPerPurchase();
		}
        
        if(bankQuota != null)
        	response.setBankInfo2("单笔限额" + ((double)(bankQuota / 100) / 10000) + "万");
        
        
        List<RdActivitygift> gifts = queryUserGifts(rdAppuser, rdActivitygiftMapper, true);
        
        long giftTotal = 0;
        List<PrepareBuyActivityGift> avaGifts = new ArrayList<PrepareBuyActivityGift>();
        if(gifts != null && gifts.size() > 0) {
	        for(RdActivitygift gift : gifts) {
            	long restMoney = gift.getRestMoney();
                giftTotal += restMoney;
                PrepareBuyActivityGift pbGift = new PrepareBuyActivityGift();
                pbGift.setRedPackageCode(gift.getIds());
                pbGift.setRestMoney(restMoney);
                avaGifts.add(pbGift);
	        }
        }
        
        response.setRedPackageAll(giftTotal);
        response.setRedPackages(avaGifts);
        
        response.setCommission(rdAppuser.getCommission());

        String seq = dictMapper.getValueFromDict("contract.digital");
        String url = "";
        if(seq != null) {
            try {
	            int seq_ = Integer.parseInt(seq);
	            url = contractMapper.queryUrlBySeq(seq_);
	            if(url == null) {
	                url = "";
	            }
	            String root = dictMapper.getValueFromDict("web.root");
	            url = root + url;
	            url = url.replace("\\","/");
            } catch(Exception e) {}
        }
        response.setProtocolURL(url);
        
        response.setResultCode(0);
        response.setResultMsg("");
        return response;
    }
    
    public static final List<RdActivitygift> queryUserGifts(
    		final RdAppuser user, final RdActivitygiftMapper giftMapper,
    		final boolean filterExpired) {
    	
    	if(user == null)
    		return null;
    	if(giftMapper == null)
    		return null;
    	
    	List<String> ids = new ArrayList<String>();
    	
        if(user.getActivitygift() != null && user.getActivitygift().trim().length() != 0) {
            String acts = user.getActivitygift();
            if(acts.contains(",")) {
                String[] arr = acts.split(",");
                if(arr != null && arr.length != 0) {
	                for(int i = 0; i < arr.length; i ++) {
	                    String s = arr[i];
	                    ids.add(s);
	                }
                }
            } else {
                ids.add(acts);
            }
        }
        
        if(user.getFixedgift() != null && user.getFixedgift().trim().length() > 0) {
            String acts = user.getFixedgift();
            if(acts.contains(",")) {
                String[] arr = acts.split(",");
                if(arr != null && arr.length != 0) {
	                for(int i = 0; i < arr.length; i ++) {
	                    String s = arr[i];
	                    ids.add(s);
	                }
                }
            } else {
                ids.add(acts);
            }
        }
        
        if(ids != null && ids.size() > 0) {
        	try {
        		if(filterExpired == false) {
        			return giftMapper.queryByIds(ids);
        		} else {
        			List<RdActivitygift> gifts = giftMapper.queryByIds(ids);
        			List<RdActivitygift> filteredGifts = null;
        			if(gifts != null && gifts.size() != 0) {
        				Date today = new Date(System.currentTimeMillis());
        				for(RdActivitygift gift : gifts) {
        	                //if valid
        		            boolean isValid = gift.getIsactivated() && gift.getRestMoney() > 0;
        		            if(isValid) {
        			            if(gift.getExpiredate() != null) {
        			            	if(ToolDays.compareDate(today, gift.getExpiredate()) > 0)
        			                    isValid = false;
        			            }
        		            }
        		            if(isValid) {
        		            	if(filteredGifts == null)
        		            		filteredGifts = new ArrayList<RdActivitygift>();
        		            	filteredGifts.add(gift);
        		            }
        		        }
        			}
        			return filteredGifts;
        		}
			} catch (Exception e) {
				System.err.println("query gifts failed!");
			}
        }
        
        return null;
    }
    
}
