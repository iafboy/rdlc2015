package com.expocms.server.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.CurrentPeriodBuyRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CurrentBuyUser;
import com.expocms.server.response.types.impl.CurrentPeriodBuyResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service ("CURRENTPERIODBUYHandler")
@Transactional
public class CURRENTPERIODBUYHandler extends AbsBaseHandler {

    @Autowired
    private RdAppuserMapper rdAppuserMapper;
    
    @Autowired
    private RdProductMapper rdProductMapper;

    public CURRENTPERIODBUYHandler () {
        super ();
    }

    public BaseResponse handleRequest (final IRequest request) {
        final CurrentPeriodBuyResponse currentPeriodBuyResponse = new CurrentPeriodBuyResponse ();
        
        CurrentPeriodBuyRequest currentPeriodBuyRequest = (CurrentPeriodBuyRequest)request;
        
        String productId = currentPeriodBuyRequest.getProductId();
        RdProduct product = rdProductMapper.selectByPrimaryKey(productId);
        if(product == null) {
        	currentPeriodBuyResponse.setResultCode(1);
        	currentPeriodBuyResponse.setResultMsg("产品不存在");
			return currentPeriodBuyResponse;
		}
        
        int page = currentPeriodBuyRequest.getPage();
        int count = currentPeriodBuyRequest.getPageCount();
        
        List<CurrentBuyUser> currentBuyUser = null;
        try {
        	currentBuyUser = rdAppuserMapper.currentPeriodBuy(productId, page * count , (page + 1) * count);
        } catch(Exception e) {}
        
        if(currentBuyUser != null && currentBuyUser.size() != 0) {
        	for(int i = 0; i < currentBuyUser.size(); i ++) {
        		CurrentBuyUser user = currentBuyUser.get(i);
        		
        		if(user.getBuyTimeDB() != null)
        			user.setBuyTime(Constants.SDF_LONG.format(user.getBuyTimeDB()));
        	}
        	currentPeriodBuyResponse.setCurrentBuyUser(currentBuyUser);
        }
        
        currentPeriodBuyResponse.setResultCode(Constants.succCode);
        currentPeriodBuyResponse.setResultMsg(Constants.succMsg);
        return currentPeriodBuyResponse;
    }

}
