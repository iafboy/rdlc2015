package com.expocms.server.core.impl;

import java.util.ArrayList;
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
import com.expocms.server.db.dao.RdDaysMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.ManageMoneyProductRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.AlRepayment;
import com.expocms.server.response.types.impl.AlSellOut;
import com.expocms.server.response.types.impl.AlSelling;
import com.expocms.server.response.types.impl.ManageMoneyProductResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolDays;

@Service("MANAGEMONEYPRODUCTHandler")
@Transactional
public class MANAGEMONEYPRODUCTHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(MANAGEMONEYPRODUCTHandler.class);
	
	@Autowired
	private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdDaysMapper daysMapper;
	
	public MANAGEMONEYPRODUCTHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		ManageMoneyProductResponse returnResp = new ManageMoneyProductResponse();
		
		String userId = ((ManageMoneyProductRequest)request).getUserId();
		
		RdAppuser rdAppuser = null;
		if(userId != null && userId.equals("") == false)
			rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		
		int orderCount = 0;
		if(rdAppuser != null)
			orderCount = rdAppuser.getPurchaseNum();
		
		returnResp.setOrderCount(orderCount);
		
		int alreadyRepaymentNumber = rdProductMapper.getAlreadyRepaymentNumber();
		
		Long objAlreadypaidMoney = rdProductMapper.getAlreadypaidMoney();
		long alreadypaidMoney = objAlreadypaidMoney == null ? 0 : objAlreadypaidMoney.longValue();
		
		int alreadySellOutNumber = rdProductMapper.getAlreadySellOutNumber();
		
		Long objAlreadyRepaymentMoney = rdProductMapper.getAlreadyRepaymentMoney();
		long alreadyRepaymentMoney = objAlreadyRepaymentMoney == null ? 0 : objAlreadyRepaymentMoney.longValue();
		
		/*
		List<AlSelling> selling = null;
		if(orderCount != 0) {
			try {
				selling = rdProductMapper.getSellingExcludeProductType("" + RdProduct.PRODUCT_TYPE_NEW_HAND);
			} catch (Exception e) {
				logger.error("exception happened while rdProductMapper.getSelling() " + e);
			}
		} else {
			try {
				selling = rdProductMapper.getSelling();
			} catch (Exception e) {
				logger.error("exception happened while rdProductMapper.getSelling() " + e);
			}
		}*/
		
		// 新手和没登录的用户能看到所有产品，不是新手看不到新手产品。
		List<AlSelling> selling = null;
		List<RdProduct> productList = rdProductMapper.getProductSaleBoard();
		if(productList != null && productList.size() != 0) {
			for(int i = 0; i < productList.size(); i ++) {
				RdProduct product = productList.get(i);
				
				/**
				 * 产品显示这里有一点小小的改动。
				 * 现在是购买过的用户登录后在理财产品页面看不到新手专享产品，现在需要改成可以看到，但不能购买。
				 * 这里是根据上线后的用户体验调整的，烦请帮忙调整一下。（精品推荐页面显示规则还是按照原来的规则不调整）具体显示如上图。
				 */
				//if(orderCount != 0 && ("" + RdProduct.PRODUCT_TYPE_NEW_HAND).equals(product.getProducttype()))
				//	continue;
				
				AlSelling as = new AlSelling();
				
				try {
					int productType = Integer.parseInt(product.getProducttype());
					as.setSellType(productType);
				} catch (NumberFormatException e) {
					continue;
				}
				
				as.setProductId(product.getIds());
				as.setProductName(product.getProductname() + '第' + product.getSeries() + '期');
				as.setAllMoney(product.getProductamount());
				
				if(product.getSoldamount() == null || product.getProductamount() == null)
					continue;
				if(product.getProductamount() != 0)
					as.setAlreadySell((int)(product.getSoldamount() * 10 * Constants.SOLD_AMOUNT_FACT / product.getProductamount()));
				else
					as.setAlreadySell(0);
				
				if(product.getInterest() == null)
					continue;
				as.setPredictYearEarnings((int)(product.getInterest() * 10));
				
				/*
				if(product.getInvestigateduration() == null)
					continue;
				as.setInvestmentTimeLimit(product.getInvestigateduration());
				*/
				int investDuration = 0;
				if(product.getStartinterestdate() != null && product.getEndinterestdate() != null) {
					Calendar startIDate = ToolDays.getCalendar(product.getStartinterestdate());
					Calendar endIDate = ToolDays.getCalendar(product.getEndinterestdate());
					Calendar buyDate = ToolDays.getCalendar(new Date(System.currentTimeMillis()));
					try {
						investDuration = QUICKPAYMENTHandler.calculateInterestPeriod(daysMapper, buyDate, startIDate, endIDate);
					} catch(RuntimeException e) {}
				}
				if(investDuration == 0)
					continue;
				as.setInvestmentTimeLimit(investDuration);
				
				as.setWhetherSellOut(product.getStatus() > 4 ? "YES" : "NO");
				
				if(selling == null)
					selling = new ArrayList<AlSelling>();
				selling.add(as);
			}
		}
		
		List<AlRepayment> alreadyRepayment = null;
		try {
			alreadyRepayment = rdProductMapper.getAlreadyRepayment();
		} catch (Exception e) {
			logger.error("exception happened while rdProductMapper.getAlreadyRepayment() " + e);
		}
		
		List<AlSellOut> alreadySellOut = null;
		try {
			alreadySellOut = rdProductMapper.getAlreadySellOut();
		} catch (Exception e) {
			logger.error("exception happened while rdProductMapper.getAlreadySellOut() " + e);
		}
		
		returnResp.setAlreadyRepaymentNumber(alreadyRepaymentNumber);
		returnResp.setAlreadypaidMoney(alreadypaidMoney);
		returnResp.setAlreadySellOutNumber(alreadySellOutNumber);
		returnResp.setAlreadyRepaymentMoney(alreadyRepaymentMoney);
		
		if(selling != null && selling.size() > 0)
			returnResp.setSelling(selling);
		if(alreadyRepayment != null && alreadyRepayment.size() > 0)
			returnResp.setAlreadyRepayment(alreadyRepayment);
		if(alreadySellOut != null && alreadySellOut.size() > 0)
			returnResp.setAlreadySellOut(alreadySellOut);
		
		returnResp.setResultCode (Constants.succCode);
        returnResp.setResultMsg (Constants.succMsg);
		return returnResp;
	}

}
