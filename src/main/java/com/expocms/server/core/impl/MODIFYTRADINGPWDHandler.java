package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.ModifyTradingPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("MODIFYTRADINGPWDHandler")
@Transactional
public class MODIFYTRADINGPWDHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	public MODIFYTRADINGPWDHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		String userid=((ModifyTradingPwdRequest)request).getUserId();
		if(userid == null || userid.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
		String pwd=((ModifyTradingPwdRequest)request).getTransactionPwd();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(userid);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		rdAppuser.setTradingPwd(pwd);
		if(rdAppuserMapper.updateByPrimaryKeySelective(rdAppuser) == 1) {
			returnResp.setResultCode (Constants.succCode);
	        returnResp.setResultMsg (Constants.succMsg);
		}else{
			returnResp.setResultCode (1);
	        returnResp.setResultMsg ("设置交易密码失败");
		}
		
		return returnResp;
	}

}
