package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.VerifyTradingPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;
import com.expocms.server.tools.ToolUtils;

@Service("VERIFYTRADINGPWDHandler")
@Transactional
public class VERIFYTRADINGPWDHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	public VERIFYTRADINGPWDHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		String userid=((VerifyTradingPwdRequest)request).getUserId();
		if(userid == null || userid.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
		String pwd = ((VerifyTradingPwdRequest)request).getPwd();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userid);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		String realPwd = rdAppuser.getTradingPwd();
		if(ToolUtils.verifyPwd(realPwd, pwd)) {
			returnResp.setResultCode (Constants.succCode);
	        returnResp.setResultMsg (Constants.succMsg);
		} else {
			returnResp.setResultCode (1);
	        returnResp.setResultMsg ("验证交易密码失败");
		}
		
		return returnResp;
	}

}
