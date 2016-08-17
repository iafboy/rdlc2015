package com.expocms.server.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdHelpCenterMapper;
import com.expocms.server.db.model.RdHelpCenter;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.HelpCenter;
import com.expocms.server.response.types.impl.MoreInfoResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.google.common.collect.Lists;

@Service("MOREINFOHandler")
public class MOREINFOHandler extends AbsBaseHandler {
	
	@Autowired
	private RdHelpCenterMapper rdHelpCenterMapper;
	
	public MOREINFOHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		MoreInfoResponse returnResp = new MoreInfoResponse();
		
		returnResp.setAppidURL("");
		returnResp.setGuanwangURL("www.roadoor.com");
		
		List<RdHelpCenter> ls = null;
		try {
			//ls = rdHelpCenterMapper.selectAll();
			ls = rdHelpCenterMapper.selectAllSeq();
		} catch (Exception e) {
			// nothing to do ...
		}
		
		if(ls != null && ls.size() > 0) {
			List<HelpCenter> helpCentre = Lists.newArrayList();
			for(int i = 0;i < ls.size(); i ++) {
				RdHelpCenter help = ls.get(i);
				HelpCenter helpC = new HelpCenter();
				helpC.setText(help.getAnswer());
				helpC.setTitle(help.getQuestion());
				helpCentre.add(helpC);
			}
			returnResp.setHelpCentre(helpCentre);
		}
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
