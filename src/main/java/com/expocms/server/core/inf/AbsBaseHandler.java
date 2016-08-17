package com.expocms.server.core.inf;

import org.apache.log4j.Logger;

import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.inf.BaseResponse;

public abstract class AbsBaseHandler {
	protected static Logger logger;
	
	protected BaseResponse returnResp;
	
	public AbsBaseHandler(){
		logger = Logger.getLogger(this.getClass());
	}
	
	abstract public BaseResponse handleRequest(IRequest request);
	
	public void handleException(IRequest request) {}
	
}
