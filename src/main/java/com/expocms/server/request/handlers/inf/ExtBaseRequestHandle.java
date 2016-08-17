package com.expocms.server.request.handlers.inf;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSON;
import com.expocms.server.response.types.inf.BaseResponse;

public class ExtBaseRequestHandle extends BaseRequestHandle {
	
	public ExtBaseRequestHandle() {
	}

	public ExtBaseRequestHandle(ChannelHandlerContext ctx, String contentStr,String className) {
		super(ctx, contentStr, className);
	}
	
	@Override
	public String getHandleResult() {
		
		if(contentStr != null && contentStr.equals("") == false)
			logger.info(contentStr);
		
		request = null;
		try {
			request =((com.expocms.server.request.types.inf.IRequest)JSON.parseObject(contentStr, classType));
			if (!request.validate()) {
				return sendInvoidHandleResult(null);
			}
		} catch (Exception e) {
			logger.error(e);
			return sendInvoidHandleResult(e);
		}
		
		BaseResponse resp = requestAction.handleRequest(request);
		if (resp == null) {
			return sendInvoidHandleResult(null);
		}
		
		String returnResp = "";
		try {
			returnResp = JSON.toJSONString(resp);
		} catch (Exception e) {
			logger.error(e);
			return sendInvoidHandleResult(e);
		}
		
		if(returnResp != null && returnResp.equals("") == false)
			logger.info(returnResp);
		
		return returnResp;
	}

}
