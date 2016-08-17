package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.ModifyLoginPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("MODIFYLOGINPWDHandler")
@Transactional
public class MODIFYLOGINPWDHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	public MODIFYLOGINPWDHandler(){
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp=new CommonResponse();
		
		String ids=((ModifyLoginPwdRequest)request).getUserId();
		if(ids == null || ids.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户id不能为空");
			return returnResp;
		}
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(ids);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		String oldPwd = ((ModifyLoginPwdRequest)request).getOldPwd();
		String newPwd = ((ModifyLoginPwdRequest)request).getNewPwd();
		
		if(oldPwd != null && oldPwd.trim().equals(rdAppuser.getPwd())) {
			rdAppuser.setPwd(newPwd);
			
			if(rdAppuserMapper.updateByPrimaryKeySelective(rdAppuser) == 1) {
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else {
				returnResp.setResultCode(1);
				returnResp.setResultMsg("修改密码失败");
			}
		} else {
			returnResp.setResultCode (1);
	        returnResp.setResultMsg("原密码错误");
		}
		
		return returnResp;
	
	}

}
