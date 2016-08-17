package com.expocms.server.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdLoanAgreementMapper;
import com.expocms.server.db.dao.RdOrderMapper;
import com.expocms.server.db.dao.RdProductMapper;
import com.expocms.server.db.model.BorrowOrderEntity;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdOrder;
import com.expocms.server.db.model.RdProduct;
import com.expocms.server.request.types.impl.BorrowOrderRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.BorrowOrderResponse;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service ("BORROWORDERHandler")
@Transactional
public class BORROWORDERHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(BORROWORDERHandler.class);
	
	@SuppressWarnings("serial")
	public final static Map<String, String> DEBITER_TYPE_MAP = new HashMap<String, String>() {{
		put("1", "工薪");
		put("2", "企业法人");
		put("3", "车主");
		put("4", "房主");
	}};
	
	@SuppressWarnings("serial")
	public final static Map<String, String> DEBITER_PURPOSE_MAP = new HashMap<String, String>() {{
		put("1", "消费");
	    put("2", "资金周转");
	    put("3", "旅游");
	    put("4", "购房");
	    put("5", "购车");
	    put("6", "装修");
	    put("7", "结婚");
	}};
	
	@SuppressWarnings("serial")
	public final static Map<String, String> DEPT_METHOD_MAP = new HashMap<String, String>() {{
		put("1", "按月付息，到期还本");
		put("2", "一次性还本付息");
		put("3", "等额本息");
	}};
	
	@Autowired
	private RdOrderMapper rdOrderMapper;
	
	@Autowired
    private RdAppuserMapper rdAppuserMapper;
    
    @Autowired
    private RdProductMapper rdProductMapper;
	
	@Autowired
	private RdLoanAgreementMapper rdLoanAgreementMapper;
	
	public BORROWORDERHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		BaseResponse returnResp = new BorrowOrderResponse();
		
		try {
			String orderId = ((BorrowOrderRequest)request).getOrderId();
			RdOrder order = rdOrderMapper.selectByPrimaryKey(orderId);
	        if(order == null) {
	        	returnResp.setResultCode(1);
	        	returnResp.setResultMsg("订单不存在");
				return returnResp;
			}
			
			String productId = ((BorrowOrderRequest)request).getProductId();
			RdProduct product = rdProductMapper.selectByPrimaryKey(productId);
	        if(product == null) {
	        	returnResp.setResultCode(1);
	        	returnResp.setResultMsg("产品不存在");
				return returnResp;
			}
			
			String userId = ((BorrowOrderRequest)request).getUserId();
			RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
			if(rdAppuser == null) {
				returnResp.setResultCode(1);
				returnResp.setResultMsg("用户不存在");
				return returnResp;
			}
			
			BorrowOrderEntity boe = null;
			try {
				boe = rdLoanAgreementMapper.getBorrowOrder(orderId, productId, userId);
			} catch (Exception e) {
				logger.error("没有找到对应的借款记录, orderId = " + orderId + ", productId = " + productId + ", userId = " + userId);
			}
			if(boe != null) {
				long actualAmount = boe.getActualAmount() != null ? boe.getActualAmount() : 0;
				
				/**
				 * 借款人姓名
				 */
				((BorrowOrderResponse)returnResp).setName(boe.getName());
				
				/**
				 * 借款人身份证号
				 */
				((BorrowOrderResponse)returnResp).setIdCard(boe.getIdCard());
				
				/**
				 * 借款人身份
				 */
				String debiterType = DEBITER_TYPE_MAP.get(boe.getIdentity());
				((BorrowOrderResponse)returnResp).setIdentity(debiterType != null ? debiterType : "");
				
				/**
				 * 借款协议ID
				 */
				((BorrowOrderResponse)returnResp).setBorrowingNumber(boe.getBorrowingNumber() != null ? boe.getBorrowingNumber() : "");
				
				/**
				 * 出借咨询与服务编号 - 订单ID
				 */
				((BorrowOrderResponse)returnResp).setLoanNumber(orderId);
				
				/**
				 * 借款用途 - 借款协议目的(ZJP)
				 * 
				 * 对应关系：
				 * 消费, 资金周转, 旅游, 购房, 购车, 装修, 结婚
				 */
				String debiterPurpose = DEBITER_PURPOSE_MAP.get(boe.getPurpose());
				((BorrowOrderResponse)returnResp).setPurpose(debiterPurpose != null ? debiterPurpose : "");
				
				/**
				 * 资金出借、回收方式 - 资金出借方式(ZJP)
				 */
				String investType = DEPT_METHOD_MAP.get(boe.getInvestType());
				((BorrowOrderResponse)returnResp).setInvestType(investType != null ? investType : "");

				/**
				 * （借款协议？）创建时间
				 */
				((BorrowOrderResponse)returnResp).setStartDate(boe.getStartDate());
				
				/**
				 * 开始还款日期
				 */
				((BorrowOrderResponse)returnResp).setStartRepayment(boe.getStartRepayment());
				
				/**
				 * 利率（来自产品表），预期年化利率
				 */
				((BorrowOrderResponse)returnResp).setPredictEarnings(boe.getPredictEarnings());
				
				/**
				 * 项目简介
				 */
				((BorrowOrderResponse)returnResp).setBriefContent(boe.getBriefContent());

				/**
				 * 出借金额 - 已借到的钱
				 * 
				 * 公式：round((l.totalMoneyDays - l.restMoneyDays) / l.debitDuration) as lendMoney
				 */
//				long lendMoney = boe.getLendMoney() != null ? boe.getLendMoney() : 0;
//				if(lendMoney < 0)
//					lendMoney = 0;
//				((BorrowOrderResponse)returnResp).setLendMoney(lendMoney);
				long lendMoney = actualAmount;
				((BorrowOrderResponse)returnResp).setLendMoney(lendMoney);
				
				/**
				 * 申请出借金额 - 借款总额（万元）
				 */
				//((BorrowOrderResponse)returnResp).setApplyMoney(boe.getApplyMoney() != null ? boe.getApplyMoney() : 0);
				long applyMoney = actualAmount;
				((BorrowOrderResponse)returnResp).setApplyMoney(applyMoney);

				/**
				 * 实际出借金额 - （实现金额）已借到的钱
				 * 
				 * 公式：round((l.totalMoneyDays - l.restMoneyDays) / l.debitDuration) as lendMoney
				 */
//				long realityMoney = boe.getRealityMoney() != null ? boe.getRealityMoney() : 0;
//				if(realityMoney < 0)
//					realityMoney = 0;
//				((BorrowOrderResponse)returnResp).setRealityMoney(realityMoney);
				long realityMoney = actualAmount;
				((BorrowOrderResponse)returnResp).setRealityMoney(realityMoney);

				/**
				 * 剩余资金（还差多少钱）
				 * 
				 * 公式：round(l.restMoneyDays / l.debitDuration) as surplusMoney
				 */
				((BorrowOrderResponse)returnResp).setSurplusMoney(applyMoney - realityMoney);
				
				returnResp.setResultCode(Constants.succCode);
		        returnResp.setResultMsg(Constants.succMsg);
			} else {
				returnResp = new CommonResponse();
				returnResp.setResultCode(1);
			    returnResp.setResultMsg("没有获取到借出单");
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			returnResp = new CommonResponse();
			returnResp.setResultCode(1);
		    returnResp.setResultMsg("获取借出单失败");
		}
		
		return returnResp;
	}
}
