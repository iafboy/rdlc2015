package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.cache.AuthCodeCacheManager;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.ForgetTradingPwdRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("FORGETTRADINGPWDHandler")
@Transactional
public class FORGETTRADINGPWDHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;

	public FORGETTRADINGPWDHandler() {
		super();
	}

	public BaseResponse handleRequest(IRequest request) {
		CommonResponse returnResp = new CommonResponse();
		
		String ids = ((ForgetTradingPwdRequest) request).getUserId();
		
		RdAppuser rdAppuser = rdAppuserMapper.selectByPrimaryKey(ids);
		if(rdAppuser == null) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("用户不存在");
			return returnResp;
		}
		
		String newVc = ((ForgetTradingPwdRequest) request).getVerifyCode();
		String idCard = ((ForgetTradingPwdRequest) request).getIdCard();
		String bankcard = ((ForgetTradingPwdRequest) request).getBankCard();
		
		if (idCard != null && idCard.trim().equals(rdAppuser.getIdcard()) &&
				bankcard != null && bankcard.trim().equals(rdAppuser.getBankcardno())) {
			String code = AuthCodeCacheManager.getInstance().findAuthCodeInCache(rdAppuser.getPhone());
			
			if (newVc != null && newVc.equals(code)) {
				AuthCodeCacheManager.getInstance().cleanCodeInCache(rdAppuser.getPhone());
				
				String pwd = ((ForgetTradingPwdRequest) request).getPwd();
				if(pwd != null && pwd.equals("") == false) {
					rdAppuser.setTradingPwd(pwd);
					
					if(rdAppuserMapper.updateByPrimaryKeySelective(rdAppuser) == 1) {
						returnResp.setResultCode(Constants.succCode);
						returnResp.setResultMsg(Constants.succMsg);
					} else {
						returnResp.setResultCode(1);
						returnResp.setResultMsg("更新交易密码失败");
					}
					
					return returnResp;
				}
				
				returnResp.setResultCode(Constants.succCode);
				returnResp.setResultMsg(Constants.succMsg);
			} else {
				returnResp.setResultCode(1);
				returnResp.setResultMsg("短信校验码验证失败");
			}
		} else {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("证件号或银行卡号错误");
		}
		
		return returnResp;
	}

}
