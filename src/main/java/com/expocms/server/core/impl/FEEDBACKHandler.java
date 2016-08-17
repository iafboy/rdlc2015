package com.expocms.server.core.impl;

import com.expocms.server.db.dao.RdAdviceMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAdvice;
import com.expocms.server.db.model.RdAppuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.FeedbackRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolUtils;

@Service("FEEDBACKHandler")
@Transactional
public class FEEDBACKHandler extends AbsBaseHandler  {

	@Autowired
	RdAdviceMapper adviceMapper;

	@Autowired
	RdAppuserMapper userMapper;

	public FEEDBACKHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		String userId = ((FeedbackRequest)request).getUserId();
		RdAppuser user = userMapper.selectByUserId(userId);
		if(user == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		String content = ((FeedbackRequest)request).getContent();
		
		RdAdvice advice = new RdAdvice();
		advice.setIds(ToolUtils.getUuidByJdk(true));
		advice.setAppuserid(userId);
		advice.setAppusername(user.getUsername());
		advice.setContent(content);
		advice.setStatus("1");
		advice.setPhone(user.getPhone());

		try{
			if(adviceMapper.insertSelective(advice) != 1) {
				returnResp.setResultCode(2);
				returnResp.setResultMsg("Failed");
				return returnResp;
			}
			returnResp.setResultCode(Constants.succCode);
			returnResp.setResultMsg(Constants.succMsg);
		}catch(Exception ex){
			returnResp.setResultCode(2);
			returnResp.setResultMsg("Failed");
			logger.error(ex.getMessage(), ex);
		}
		return returnResp;
	}

}
