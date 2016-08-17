package com.expocms.server.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.ProductListRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.ProductItem;
import com.expocms.server.response.types.impl.ProductListResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("PRODUCTLISTHandler")
@Transactional
public class PRODUCTLISTHandler extends AbsBaseHandler {	
	
	private static Logger logger = Logger.getLogger(PRODUCTLISTHandler.class);

	@Autowired
	private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	public PRODUCTLISTHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		ProductListResponse returnResp = new ProductListResponse();
		
		ProductListRequest productListRequest = (ProductListRequest)request;
		
		String userId = productListRequest.getUserId();
		
		RdAppuser rdAppuser = null;
		if(userId != null && userId.equals("") == false)
			rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);

		int orderCount = 0;
		if(rdAppuser != null)
			orderCount = rdAppuser.getPurchaseNum();

		int page = productListRequest.getPage();
		int pageCount = productListRequest.getPageCount();
		int min = page * pageCount;
		int max = (page + 1) * pageCount;
		
		int status = RdProduct.PRODUCT_STATUS_IN_SAIL;
		
		final String productType = productListRequest.getProductType();
		if(productType != null && productType.equals("") == false) {
			if(productType.toUpperCase().equals(RdProduct.PRODUCT_STATUS_STR_REPAIED.toUpperCase())) {
				status = RdProduct.PRODUCT_STATUS_REPAIED;
			} else if(productType.toUpperCase().equals(RdProduct.PRODUCT_STATUS_STR_SOLD_OUT.toUpperCase())) {
				status = RdProduct.PRODUCT_STATUS_SOLD_OUT;
			}
		}
		
		List<ProductItem> productItemList = null;
		if(orderCount != 0) {
			try {
				productItemList = rdProductMapper.getProductItemByStatusExcludeProductType(status, min, max, "" + RdProduct.PRODUCT_TYPE_NEW_HAND);
			} catch(Exception e) {
				logger.error("exception happened while getProductItemByStatus " + e);
			}
		} else {
			try {
				productItemList = rdProductMapper.getProductItemByStatus(status, min, max);
			} catch(Exception e) {
				logger.error("exception happened while getProductItemByStatus " + e);
			}
		}
		
		if(productItemList != null && productItemList.size() > 0)
			returnResp.setList(productItemList);
		
		returnResp.setResultCode (Constants.succCode);
        returnResp.setResultMsg (Constants.succMsg);
		return returnResp;
	}

}
