package com.expocms.server.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.db.dao.RdDictMapper;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.SharePortResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("SHAREPORTHandler")
public class SHAREPORTHandler extends AbsBaseHandler {
	
	@Autowired
	private RdDictMapper rdDictMapper;
	
	public SHAREPORTHandler() {
		super();
	}
	
	public BaseResponse handleRequest(IRequest request) {
		SharePortResponse returnResp=new SharePortResponse();
		
		String s;
		
		s = rdDictMapper.getValueFromDict("ShareContent");
		returnResp.setShareContent(s != null ? s : "这里是融道理财,请关注我们吧!");
		
		s = rdDictMapper.getValueFromDict("ShareURL");
		returnResp.setShareURL(s != null ? s : "http://www.baidu.com");
		
		s = rdDictMapper.getValueFromDict("ShareTitle");
		returnResp.setShareTitle(s != null ? s : "融道理财");
		
		s = rdDictMapper.getValueFromDict("erweimaURL");
		returnResp.setErweimaURL(s != null ? s : "http://xxx.png");
		
		returnResp.setResultCode(Constants.succCode);
		returnResp.setResultMsg(Constants.succMsg);
		return returnResp;
	}

}
