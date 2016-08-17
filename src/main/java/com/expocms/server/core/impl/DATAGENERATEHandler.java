package com.expocms.server.core.impl;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.*;
import com.expocms.server.db.model.*;
import com.expocms.server.request.types.impl.DataGenerateRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolUtils;
import com.expocms.server.util.RandomSecurityCode;
import com.expocms.server.util.RandomSecurityCode.SecurityCodeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service ("DATAGENERATEHandler")
@Transactional
public class DATAGENERATEHandler extends AbsBaseHandler {

    @Autowired
    private RdAppuserMapper rdAppuserMapper;

    @Autowired
    private RdOrderMapper rdOrderMapper;

    @Autowired
    private RdActivitygiftMapper rdActivitygiftMapper;

    @Autowired
    private RdFixedgiftMapper rdFixedgiftMapper;

    @Autowired
    private RdProductMapper productMapper;

    @Autowired
    @Qualifier ("configproperties")
    Properties pro;

    public DATAGENERATEHandler() {
        super ();
    }

    @Override
    public BaseResponse handleRequest (final IRequest request) {
        final CommonResponse returnResp = new CommonResponse();
        
        // 1. nessary parameter
        final String customerId_ = ((DataGenerateRequest)request).getUserId();
        RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(customerId_);
        if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
        
        final String productId = ((DataGenerateRequest)request).getProductId();
        RdProduct product = productMapper.selectByPrimaryKey(productId);
        if(product == null) {
        	returnResp.setResultCode(1);
        	returnResp.setResultMsg("产品不存在");
			return returnResp;
		}
        
        // 支付成功，更新用户购买次数字段，判断是否首次支付，是否需要提供首次购买红包
        rdAppuser.setPurchaseNum(rdAppuser.getPurchaseNum() + 1);
        String giftId = getPurchaseFixedGift(rdAppuser.getPurchaseNum());
        if(giftId != null) {
        	if(rdAppuser.getFixedgift() != null && rdAppuser.getFixedgift().equals("") == false)
        		rdAppuser.setFixedgift(rdAppuser.getFixedgift() + "," + giftId);
        	else
        		rdAppuser.setFixedgift(giftId);
        }
        rdAppuserMapper.updateByIds(rdAppuser);
        
//            final RdAppuserOrders record = new RdAppuserOrders ();
//            record.setAmount (Integer.getInteger (amount_));
//            record.setBonusAmount (Integer.valueOf ("" + reduce));
//            record.setIds (ToolUtils.getUuidByJdk (true));
//            record.setIdsPayment (respXml.get ("ids"));
//            record.setIdsProduct (((QuickPaymentRequest)request).getProductId ());
//            record.setIdsUser (customerId_);
//				rdAppuserOrdersMapper.insert (record);
        
        final RdOrder order = new RdOrder();
        order.setIds(UUID.randomUUID().toString().substring(0, 32));
        //order.setAmount((float)amount / 100);
        //order.setActualamount((float)amount / 100 - reduce / 100);
        order.setAppuseridentity(rdAppuser.getIdcard());
        order.setAppusername(rdAppuser.getUsername() + UUID.randomUUID().toString().substring(0, 5));
        order.setAppUserPhone("139" + new Random().nextInt(10) + "" + new Random().nextInt(10) + "" + "6789" + new Random().nextInt(10) + "" + new Random().nextInt(10));
        order.setProductids(product.getIds());
        order.setProductno(productId);
        order.setNo(UUID.randomUUID().toString().substring(0, 10));
        order.setAssignmentid(product.getAssignid());
        order.setAssignno(product.getAssignmentno());
        //order.setGiftamount(Float.valueOf(reduce / 100));
        order.setDuration(product.getInvestigateduration());
        order.setInterest(product.getInterest());
        order.setAppuserid(rdAppuser.getIds());
        order.setIscheckok(0);
        order.setIsdeleted(0);
        Date d = new Date();
        d.setTime(System.currentTimeMillis() - new Random().nextInt(100000));
        order.setOrdercreatetime(d);
        order.setOrdertransfertime(d);
        order.setOrdertype("1");
        //order.setPayamount((float)amount / 100 - reduce / 100);
        //order.setRepaidamount(0.0f);
        order.setStatus(1);
        order.setSex(rdAppuser.getSex());
        rdOrderMapper.insertSelective(order);
        
        returnResp.setResultCode(Constants.succCode);
        returnResp.setResultMsg(Constants.succMsg);
        return returnResp;
    }


    /**
     * Method documentation to be filled TODO
     *
     * @return
     */
    private String getPurchaseFixedGift(final int type) {
        final RdFixedgift rdFixedgift = rdFixedgiftMapper.selectByType (Constants.purchaseFixedGift);
        
        String ids = null;
        if (rdFixedgift.getStatus().intValue() == Constants.fixedgiftDisabled) {
            return ids;
        }
        
        // 首次购买
        if(type == 1 || type == 2) {
            final Integer expiredays = rdFixedgift.getExpiredays();
            final Date expireDate = ToolUtils.getDate(expiredays.intValue());
            final Long amount = rdFixedgift.getAmount();
            ids = String.valueOf(RandomSecurityCode.getSecurityCode(8, SecurityCodeLevel.Hard, true));
            
            final RdActivitygift record = new RdActivitygift();
            record.setIds(ids);
            record.setExpiredate(expireDate);
            record.setValue(amount);
            if(type == 1)
            	record.setActivityname(Constants.purchaseFixedGiftString);
            else // type == 2
            	record.setActivityname(Constants.purchase2FixedGiftString);
            record.setCreatedate(new Date());
            record.setIsactivated(false);
            record.setIsused(false);
            rdActivitygiftMapper.insert(record);
            return ids;
        }

        return null;
    }

}
