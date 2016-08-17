package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.SetTradingPwdBoolRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.SetTradingPwdBoolResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service ("SETTRADINGPWDBOOLHandler")
@Transactional
public class SETTRADINGPWDBOOLHandler extends AbsBaseHandler {

    @Autowired
    private RdAppuserMapper rdAppuserMapper;

    public SETTRADINGPWDBOOLHandler () {
        super ();
    }

    public BaseResponse handleRequest (final IRequest request) {
        final SetTradingPwdBoolResponse setTradingPwdBoolResponse = new SetTradingPwdBoolResponse ();
        
        SetTradingPwdBoolRequest setTradingPwdBoolRequest = (SetTradingPwdBoolRequest)request;
        
        String userid = setTradingPwdBoolRequest.getUserId();
        if(userid == null || userid.equals("")) {
        	setTradingPwdBoolResponse.setResultCode(1);
        	setTradingPwdBoolResponse.setResultMsg("用户id不能为空");
			return setTradingPwdBoolResponse;
		}
        
        RdAppuser rdAppuser = rdAppuserMapper.selectByUserId(userid);
        if(rdAppuser == null) {
        	setTradingPwdBoolResponse.setResultCode(1);
        	setTradingPwdBoolResponse.setResultMsg("用户不存在");
			return setTradingPwdBoolResponse;
		}
        
        if(rdAppuser.getTradingPwd() != null && !"".equals(rdAppuser.getTradingPwd().trim())) {
        	setTradingPwdBoolResponse.setResultCode(0);
        	setTradingPwdBoolResponse.setResultMsg(Constants.succMsg);
        	setTradingPwdBoolResponse.setSetTradingPwd(1);
        } else {
        	setTradingPwdBoolResponse.setResultCode(0);
        	setTradingPwdBoolResponse.setResultMsg(Constants.succMsg);
        	setTradingPwdBoolResponse.setSetTradingPwd(0);
        }
        
        return setTradingPwdBoolResponse;
    }

}
