package com.expocms.server.core.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdDaysMapper;
import com.expocms.server.db.dao.RdLoanAgreementMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.RdLoanAgreement;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.ProductDetailsRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.ProductDetailInfo;
import com.expocms.server.response.types.impl.ProductDetailsResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

@Service("PRODUCTDETAILSHandler")
@Transactional
public class PRODUCTDETAILSHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(PRODUCTDETAILSHandler.class);
	
	@Autowired
	private RdOrderMapper rdOrderMapper;
	
	@Autowired
	private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdLoanAgreementMapper rdLoanAgreementMapper;
	
	@Autowired
	private RdDaysMapper daysMapper;

	public PRODUCTDETAILSHandler() {
		super();
	}

	public BaseResponse handleRequest(IRequest request) {
		ProductDetailsResponse returnResp = new ProductDetailsResponse();

		ProductDetailsRequest productDetailsRequest = (ProductDetailsRequest) request;
		String productId = productDetailsRequest.getProductId();

		RdProduct rdProduct = rdProductMapper.selectByPrimaryKey(productId);
		if (rdProduct == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("产品不存在");
			return returnResp;
		}

		ProductDetailInfo productDetailInfo = new ProductDetailInfo();
		
		Long productAmount = rdProduct.getProductamount();
		Long soldAmount = rdProduct.getSoldamount();
		if (productAmount != null && productAmount != 0) {
			productDetailInfo.setAllMoney(productAmount);
			productDetailInfo.setAlreadySell(soldAmount * 10 * Constants.SOLD_AMOUNT_FACT / productAmount);
		} else {
			productDetailInfo.setAllMoney(0L);
			productDetailInfo.setAlreadySell(0L);
		}
		
		productDetailInfo.setBuyNumber((long)(rdProduct.getSuccessrecords() != null ? rdProduct.getSuccessrecords() : 0));
		productDetailInfo.setCurrentBuyUserNumber((long)(rdProduct.getSuccessrecords() != null ? rdProduct.getSuccessrecords() : 0));
		productDetailInfo.setLowMoney(rdProduct.getMininvestigateamount() != null ? rdProduct.getMininvestigateamount() : 0);
		productDetailInfo.setHighMoney(rdProduct.getMaxinvestigateamount() != null ? rdProduct.getMaxinvestigateamount() : 0);
		productDetailInfo.setMaySellMoney(productAmount - soldAmount);
		productDetailInfo.setPoundage((long)Constants.poundage);
		productDetailInfo.setPredictYearEarnings((long)(rdProduct.getInterest() * 10));
		productDetailInfo.setProductName(RdProduct.getFullProudctName(rdProduct.getProductname(), rdProduct.getSeries()));

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
			productDetailInfo.setStartDate(Constants.SDF_SHORT.format(startIDate.getTime()));
			productDetailInfo.setEndDate(Constants.SDF_SHORT.format(endIDate.getTime()));
			productDetailInfo.setInvestmentTimeLimit((long)investDuration);
		} else {
			productDetailInfo.setStartDate(
					rdProduct.getStartinterestdate() == null ?
							"" : rdProduct.getStartinterestdate().toString());
			productDetailInfo.setEndDate(
					rdProduct.getEndinterestdate() == null ?
							"" : rdProduct.getEndinterestdate().toString());
			productDetailInfo.setInvestmentTimeLimit(
					(long)(rdProduct.getInvestigateduration() != null ?
							rdProduct.getInvestigateduration() : 0));
		}

		Long redPackageMoney = null;
		try {
			redPackageMoney = rdOrderMapper.getAllRedPackageAmountByProductIds(productId);
		} catch (Exception e) {
			logger.error("exception happened while getAllRedPackageAmountByProductIds " + e);
		}
		productDetailInfo.setRedPackageMoney(redPackageMoney != null ? redPackageMoney : 0);
		
		RdLoanAgreement loanAgreement = null;
		try {
			loanAgreement = getLoanAgreement(rdLoanAgreementMapper, rdProduct);
		} catch (Exception e) {
			logger.error("exception happened while getLoanAgreement " + e);
		}
		if(loanAgreement != null) {
			productDetailInfo.setSafeguard(loanAgreement.getProjectSource() != null ? loanAgreement.getProjectSource() : "");
			productDetailInfo.setBriefContent(loanAgreement.getBriefContent() != null ? loanAgreement.getBriefContent() : "");
		} else {
			productDetailInfo.setSafeguard("");
			productDetailInfo.setBriefContent("");
		}

		returnResp.setInfo(productDetailInfo);
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}
	
	public static RdLoanAgreement getLoanAgreement(
			final RdLoanAgreementMapper rdLoanAgreementMapper, final RdProduct rdProduct) {
		
		if(rdLoanAgreementMapper == null)
			return null;
		if(rdProduct == null)
			return null;
		
		if(rdProduct.getAssignid() == null || rdProduct.getAssignid().equals(""))
			return null;
		
		return rdLoanAgreementMapper.selectByPrimaryKey(rdProduct.getAssignid());
	}

}
