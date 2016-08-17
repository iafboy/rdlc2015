package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserCommissionHistoryMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserCommissionHistory;
import com.expocms.server.request.types.impl.CommissionHistoryRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommissionHistoryResponse;
import com.expocms.server.response.types.impl.CommissionRecord;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("COMMISSIONHISTORYHandler")
@Transactional
public class COMMISSIONHISTORYHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(COMMISSIONHISTORYHandler.class);
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdAppuserCommissionHistoryMapper rdAppuserCommissionHistoryMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		CommissionHistoryResponse chResp = new CommissionHistoryResponse();
		
		CommissionHistoryRequest chReq = (CommissionHistoryRequest)request;
		String userId = chReq.getUserId();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
		if(rdAppuser == null) {
			logger.error("user " + userId + " not exist!");
			chResp.setResultCode(1);
			chResp.setResultMsg("用户不存在");
			return chResp;
		}
		
		List<RdAppuserCommissionHistory> commissionHistory = rdAppuserCommissionHistoryMapper.getCommissionHistory(userId);
		if(commissionHistory != null && commissionHistory.size() != 0) {
			List<CommissionRecord> commissionRecordList = new ArrayList<CommissionRecord>();
			for(int i = 0; i < commissionHistory.size(); i ++) {
				RdAppuserCommissionHistory ach = commissionHistory.get(i);
				
				CommissionRecord commissionRecord = new CommissionRecord();
				commissionRecord.setInvestAmount(ach.getInvestAmount());
				commissionRecord.setInvestDuration(ach.getInvestPeriod());
				commissionRecord.setInvestorPhone(ach.getInvestorPhone());
				commissionRecord.setInvestTime(Constants.SDF_LONG.format(ach.getInvestTime()));
				commissionRecord.setCommission(ach.getCommission());
				
				commissionRecordList.add(commissionRecord);
			}
			chResp.setCommissionRecordList(commissionRecordList);
		}
		
		chResp.setCommission(rdAppuser.getCommission());
		
		chResp.setResultCode(Constants.succCode);
		chResp.setResultMsg(Constants.succMsg);
		return chResp;
	}

}
