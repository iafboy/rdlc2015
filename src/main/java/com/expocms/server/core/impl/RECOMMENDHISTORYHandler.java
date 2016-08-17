package com.expocms.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdAppuserRecommendHistoryMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.db.model.RdAppuserRecommendHistory;
import com.expocms.server.request.types.impl.RecommendHistoryRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.RecommendHistoryResponse;
import com.expocms.server.response.types.impl.Recommendee;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("RECOMMENDHISTORYHandler")
@Transactional
public class RECOMMENDHISTORYHandler extends AbsBaseHandler {
	
	private static Logger logger = Logger.getLogger(RECOMMENDHISTORYHandler.class);
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
	private RdAppuserRecommendHistoryMapper rdAppuserRecommendHistoryMapper;

	@Override
	public BaseResponse handleRequest(IRequest request) {
		RecommendHistoryResponse rhResp = new RecommendHistoryResponse();
		
		RecommendHistoryRequest rhReq = (RecommendHistoryRequest)request;
		String userId = rhReq.getUserId();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userId);
		if(rdAppuser == null) {
			logger.error("user " + userId + " not exist!");
			rhResp.setResultCode(1);
			rhResp.setResultMsg("用户不存在");
			return rhResp;
		}
		
		List<RdAppuserRecommendHistory> recommendHistory = rdAppuserRecommendHistoryMapper.getRecommendHistory(userId);
		if(recommendHistory != null && recommendHistory.size() != 0) {
			List<Recommendee> recommendeeList = new ArrayList<Recommendee>();
			for(int i = 0; i < recommendHistory.size(); i ++) {
				RdAppuserRecommendHistory arh = recommendHistory.get(i);
				
				Recommendee recommendee = new Recommendee();
				recommendee.setTime(Constants.SDF_LONG.format(arh.getRecommendTime()));
				recommendee.setPhoneNo(arh.getRecommendeePhone());
				
				recommendeeList.add(recommendee);
			}
			rhResp.setRecommendeeList(recommendeeList);
		}
		
		rhResp.setResultCode(Constants.succCode);
		rhResp.setResultMsg(Constants.succMsg);
		return rhResp;
	}

}
