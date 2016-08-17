package com.expocms.server.actions.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.expocms.server.actions.inf.IBaseRequestAction;
import com.expocms.server.constant.Constants;
import com.expocms.server.core.inf.AbsBaseHandler;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.CommonResponse;
import com.expocms.server.response.types.inf.BaseResponse;

@Service("ExtRequestAction")
public class ExtRequestAction implements IBaseRequestAction {
	
	private static Logger logger = Logger.getLogger(ExtRequestAction.class);
	
	//@Resource(name="TestHandler")
	//private IBaseHandler extBaseHandler;
	public BaseResponse handleRequest(IRequest request) {
		return handle(request);
		//return extBaseHandler.handleRequest(request);
	}
	
	private BaseResponse handle(IRequest request) {
		int idx = request.getClass().getSimpleName().toUpperCase().lastIndexOf("REQUEST");
		String reqAliaName = request.getClass().getSimpleName().toUpperCase().substring(0, idx) + "Handler";
		AbsBaseHandler handler = (AbsBaseHandler) Constants.applicationContext.getBean(reqAliaName);
		
		BaseResponse response = null;
		try {
			response = handler.handleRequest(request);
		} catch(Exception e) {
			logger.error("exception happened while execute request: " + e.getMessage());
			handler.handleException(request);
			
			response = new CommonResponse();
			response.setResultCode(21);
			response.setResultMsg(e.getMessage());
		}
		return response;
	}
	
}
