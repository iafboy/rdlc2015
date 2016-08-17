package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdAppuserMapper;
import com.expocms.server.db.dao.RdContractMapper;
import com.expocms.server.db.dao.RdDictMapper;
import com.expocms.server.db.model.RdAppuser;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.VerifyPhoneResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("VERIFYPHONEHandler")
@Transactional
public class VERIFYPHONEHandler extends AbsBaseHandler {
	
	@Autowired
	private RdAppuserMapper rdAppuserMapper;
	
	@Autowired
    private RdDictMapper rdDictMapper;
    
    @Autowired
    private RdContractMapper rdContractMapper;
	
	public VERIFYPHONEHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		VerifyPhoneResponse returnResp = new VerifyPhoneResponse();
		
		String phone = ((VerifyPhoneRequest)request).getPhoneNo();
		if(phone == null || phone.equals("")) {
			returnResp.setResultCode(1);
			returnResp.setResultMsg("手机号不能为空");
			return returnResp;
		}
		
		String contactUrl = rdDictMapper.getValueFromDict("web.root");
        if(contactUrl != null && contactUrl.equals("") == false) {
        	try {
    			int seq = Integer.parseInt(rdDictMapper.getValueFromDict("contract.user"));
    			String subUrl = rdContractMapper.queryUrlBySeq(seq);
    			if(subUrl != null && subUrl.equals("") == false) {
    				contactUrl += subUrl;
    				contactUrl = contactUrl.replace("\\", "/");
    				returnResp.setRegistProtocolURL(contactUrl);
    			}
    		} catch(NumberFormatException e) {}
        }
		
		RdAppuser rdappuser = rdAppuserMapper.selectByPhone(phone);
		if(rdappuser != null && rdappuser.getIdcard() != null && !"".equals(rdappuser.getIdcard())) {
			returnResp.setOrderCount(rdappuser.getPurchaseNum());
			returnResp.setResultCode (4);
	        returnResp.setResultMsg (Constants.succMsg);
			return returnResp;
		} else if(rdappuser != null) {
			returnResp.setOrderCount(rdappuser.getPurchaseNum());
			returnResp.setResultCode (2);
	        returnResp.setResultMsg (Constants.succMsg);
			return returnResp;
		} else if(rdappuser == null) {
			returnResp.setOrderCount(0);
			returnResp.setResultCode (3);
	        returnResp.setResultMsg (Constants.succMsg);
			return returnResp;
		} else {
			returnResp.setOrderCount(0);
			returnResp.setResultCode (1);
	        returnResp.setResultMsg (Constants.succMsg);
			return returnResp;
		}			
	}

}
