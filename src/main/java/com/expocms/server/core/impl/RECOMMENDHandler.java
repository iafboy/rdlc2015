package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.expocms.server.db.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.model.RdAd;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdLoanAgreement;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.RecommandProduct;
import com.expocms.server.response.types.impl.RecommendResponse;
import com.expocms.server.response.types.impl.ShowFrame;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

@Service ("RECOMMENDHandler")
@Transactional
public class RECOMMENDHandler extends AbsBaseHandler {

    @Autowired
    private RdProductMapper rdProductMapper;

    @Autowired
    private RdAppuserMapper rdAppuserMapper;

    @Autowired
    private RdOrderMapper rdOrderMapper;

    @Autowired
    private RdAdMapper rdAdMapper;

    @Autowired
    private RdDictMapper dictMapper;
    
    @Autowired
	private RdLoanAgreementMapper rdLoanAgreementMapper;
    
    @Autowired
	private RdDaysMapper daysMapper;

    public RECOMMENDHandler () {
        super ();
    }

    public BaseResponse handleRequest (final IRequest request) {
        final RecommendResponse recommendResponse = new RecommendResponse();
        
        String userId = ((RecommendRequest)request).getUserId();
        
        RdAppuser rdAppuser = null;
		if(userId != null && userId.equals("") == false)
			rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);

        int orderCount = 0;
        if(rdAppuser != null)
			orderCount = rdAppuser.getPurchaseNum();
		
        final int allUser = rdAppuserMapper.getAllUser();
        final Long allProperty = rdOrderMapper.getAllProperty();
        
        RdProduct rdProduct = null;
        if(orderCount > 0) {
        	rdProduct = rdProductMapper.getRdProductWithIds("2");
        	if(rdProduct == null)
        		rdProduct = rdProductMapper.getSoldOutRdProductWithIds("2", 1);
        } else {
        	rdProduct = rdProductMapper.getRdProductWithIds("1");
        	if(rdProduct == null)
        		rdProduct = rdProductMapper.getSoldOutRdProductWithIds("1", 1);
        }
        
        //final List<RdAd> adList = rdAdMapper.selectAll();
        final List<RdAd> adList = rdAdMapper.selectAllAvail();

        recommendResponse.setOrderCount(orderCount);
        recommendResponse.setAllUser(allUser);
        recommendResponse.setAllProperty(allProperty != null ? allProperty : 0);

        final RecommandProduct recommandProduct = new RecommandProduct();
        if(rdProduct != null) {
	        recommandProduct.setAllMoney(
	        		rdProduct.getProductamount().intValue());
	        
	        if(rdProduct.getProductamount() != null && rdProduct.getProductamount() != 0) {
		        recommandProduct.setAlreadySell(
		        		rdProduct.getSoldamount() * 10 * Constants.SOLD_AMOUNT_FACT / rdProduct.getProductamount());
	        } else {
	        	recommandProduct.setAlreadySell(0);
	        }
	        
	        // calculate period of interest ...
	        boolean calOK = false;
			Calendar startIDate = null;
			Calendar endIDate = null;
			int investDuration = 0;
			if(rdProduct.getStartinterestdate() != null && rdProduct.getEndinterestdate() != null) {
				startIDate = ToolDays.getCalendar(rdProduct.getStartinterestdate());
				endIDate = ToolDays.getCalendar(rdProduct.getEndinterestdate());
				Calendar buyDate = ToolDays.getCalendar(new Date(System.currentTimeMillis()));
				try {
					investDuration = QUICKPAYMENTHandler.calculateInterestPeriod(daysMapper, buyDate, startIDate, endIDate);
					calOK = true;
				} catch(RuntimeException e) {}
			}
	        
			if(calOK) {
				recommandProduct.setStartDate(Constants.SDF_SHORT.format(startIDate.getTime()));
				recommandProduct.setEndDate(Constants.SDF_SHORT.format(endIDate.getTime()));
				recommandProduct.setInvestmentTimeLimit(investDuration);
			} else {
		        recommandProduct.setStartDate(
		        		rdProduct.getStartinterestdate() == null ?
		        				"" : rdProduct.getStartinterestdate().toString());
		        recommandProduct.setEndDate(
						rdProduct.getEndinterestdate() == null ?
								"" : rdProduct.getEndinterestdate().toString());
		        recommandProduct.setInvestmentTimeLimit(rdProduct.getInvestigateduration());
			}
	        
	        recommandProduct.setLowMoney(rdProduct.getMininvestigateamount());
	        recommandProduct.setHighMoney(rdProduct.getMaxinvestigateamount());
	        
	        recommandProduct.setMaySellMonty(
	        		rdProduct.getProductamount() - rdProduct.getSoldamount());
	        recommandProduct.setPoundage(Constants.poundage);
	        recommandProduct.setPredictYearEarnings(
                    (int)(rdProduct.getInterest() * 10));
	        recommandProduct.setProductName(RdProduct.getFullProudctName(rdProduct.getProductname(), rdProduct.getSeries()));
	        recommandProduct.setProId(rdProduct.getIds());
	        recommandProduct.setWhetherSellOut(rdProduct.getStatus() > 4 ? "YES" : "NO");
	        recommandProduct.setBuyNumber(rdProduct.getSuccessrecords() != null ? rdProduct.getSuccessrecords() : 0);
	        recommandProduct.setCurrentBuyUserNumber((int)(rdProduct.getSuccessrecords() != null ? rdProduct.getSuccessrecords() : 0));
	        
	        RdLoanAgreement loanAgreement = null;
			try {
				loanAgreement = PRODUCTDETAILSHandler.getLoanAgreement(rdLoanAgreementMapper, rdProduct);
			} catch (Exception e) {
				logger.error("exception happened while getLoanAgreement " + e);
			}
			if(loanAgreement != null) {
				recommandProduct.setSafeguard(loanAgreement.getProjectSource() != null ? loanAgreement.getProjectSource() : "");
				recommandProduct.setBriefContent(loanAgreement.getBriefContent() != null ? loanAgreement.getBriefContent() : "");
			} else {
				recommandProduct.setSafeguard("");
				recommandProduct.setBriefContent("");
			}
			
			Long redPackageMoney = null;
			try {
				redPackageMoney = rdOrderMapper.getAllRedPackageAmountByProductIds(rdProduct.getIds());
			} catch (Exception e) {
				logger.error("exception happened while getAllRedPackageAmountByProductIds " + e);
			}
			recommendResponse.setRedPackage(redPackageMoney != null ? redPackageMoney : 0);
	        
            recommendResponse.setRecommendProduct(recommandProduct);

        } else {
        	recommendResponse.setRecommendProduct(null);
        }
        
        final List<ShowFrame> showFrames = new ArrayList<ShowFrame>();
        if(adList != null && adList.size() != 0) {
	        for(final RdAd ad: adList) {
	            final ShowFrame showFrame = new ShowFrame();
	            String img = ad.getImg();
	            if(img != null) {
	                String root = dictMapper.getValueFromDict("web.root");
	                if(root != null && root.equals("") == false)
	                	img = root + img.replace("\\", "/");
	            }
	            showFrame.setImageURL(img);
	            showFrame.setWebURL(ad.getUrl());
	            showFrames.add(showFrame);
	        }
        }
        recommendResponse.setShowFrame(showFrames);
        
        recommendResponse.setResultCode(Constants.succCode);
        recommendResponse.setResultMsg(Constants.succMsg);
        return recommendResponse;
    }
}
