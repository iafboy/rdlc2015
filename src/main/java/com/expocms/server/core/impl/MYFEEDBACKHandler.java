package com.expocms.server.core.impl;

import java.util.List;

import com.expocms.server.db.dao.RdAdviceMapper;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAdvice;
import com.expocms.server.db.model.RdAppuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.impl.MyFeedbackRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.MyFeedbackResponse;
import com.expocms.server.response.types.impl.Question;
import com.expocms.server.response.types.inf.BaseResponse;
import com.google.common.collect.Lists;

@Service("MYFEEDBACKHandler")
@Transactional
public class MYFEEDBACKHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAdviceMapper adviceMapper;
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	public MYFEEDBACKHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		MyFeedbackResponse returnResp = new MyFeedbackResponse();
		
		String userId = ((MyFeedbackRequest) request).getUserId();
		if(userId == null || userId.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userId);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		int page = ((MyFeedbackRequest) request).getPage();
		int pageCount = ((MyFeedbackRequest) request).getPageCount();
		int pageStart = (page) * pageCount;
		
		List<RdAdvice> ls = null;
		try {
			ls = adviceMapper.queryMyAdviceExt(userId, pageStart, pageCount);
		} catch (Exception e) {
			// nothing to do ...
		}
		
		List<Question> questionList = Lists.newArrayList();
		if (ls != null && ls.size() > 0) {
			for (int i = 0, size = ls.size(); i < size; i ++) {
				RdAdvice advice = ls.get(i);
				Question qa = new Question();
				qa.setOpinionContent(advice.getContent());
				if(advice.getTime() != null) {
					qa.setOpinionTime(Constants.SDF_LONG.format(advice.getTime()));
				}
				qa.setOpinionResult(advice.getReply() != null ? advice.getReply() : "");
				questionList.add(qa);
			}
		}
		
		returnResp.setQuestionList(questionList);
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
