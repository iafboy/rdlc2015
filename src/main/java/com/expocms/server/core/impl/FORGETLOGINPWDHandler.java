package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.AuthCodeCacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.ForgetLoginPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("FORGETLOGINPWDHandler")
@Transactional
public class FORGETLOGINPWDHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;

	public FORGETLOGINPWDHandler() {
		super();
	}

	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();

		String mobile = ((ForgetLoginPwdRequest) request).getMobile();
		if(mobile == null || mobile.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("手机号不能为空");
			return returnResp;
		}
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPhone(mobile);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("手机号未找到");
			return returnResp;
		}
		
		String idCard = ((ForgetLoginPwdRequest) request).getIdCard();
		String pwd = ((ForgetLoginPwdRequest) request).getResetPwd();
		String newVc = ((ForgetLoginPwdRequest) request).getVerifyCode();
		
		if(idCard != null && !"".equals(idCard)) {
			if(rdAppuser.getIdcard() != null&& !idCard.equals(rdAppuser.getIdcard().trim())) {
				returnResp.setResultCode(2);
				returnResp.setResultMsg("身份证信息不符");
				return returnResp;
			}
		}

		String code = AuthCodeCacheManager.getInstance().findAuthCodeInCache(mobile);
		//System.out.println(request.getClass().getSimpleName() + "|" + newVc + "|" + code + "|" + newVc.equals(code));
		if(newVc != null && newVc.equals(code)) {
			AuthCodeCacheManager.getInstance().cleanCodeInCache(mobile);
			
			rdAppuser.setPwd(pwd);
			rdAppuser.setBlockCount(0);
			rdAppuser.setBlockTime(null);
			rdAppuser.setUnblockDate(null);
			
			//if(rdAppuserMapper.updateByPrimaryKeySelective(rdAppuser) == 1) {
			if(rdAppuserMapper.resetPwdByIds(rdAppuser) == 1) {
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else {
				returnResp.setResultCode(1);
				returnResp.setResultMsg("更新密码失败");
			}
		} else {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("短信校验码验证失败");
		}

		return returnResp;
	}

}
