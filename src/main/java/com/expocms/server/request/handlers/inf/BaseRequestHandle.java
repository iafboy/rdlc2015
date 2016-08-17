package com.expocms.server.request.handlers.inf;

import com.alibaba.fastjson.JSON;
import com.expocms.server.actions.inf.IBaseRequestAction;
import com.expocms.server.request.types.inf.IRequest;
import com.expocms.server.response.types.impl.InvalidResponse;

import io.netty.channel.ChannelHandlerContext;

public abstract class BaseRequestHandle extends RequestHandle {
	
	protected IRequest request;
	protected IBaseRequestAction requestAction;
	
	public BaseRequestHandle() {}
	
	public BaseRequestHandle(ChannelHandlerContext ctx, String contentStr, String className) {
		super(ctx, contentStr, className);
	}
	
	public abstract String getHandleResult();
	
	public String sendInvoidHandleResult(Throwable e) {
		InvalidResponse invalidResponse = new InvalidResponse();
        invalidResponse.setResultCode(-2);
        invalidResponse.setResultMsg("参数错误！");
        return JSON.toJSONString(invalidResponse);
	}
}
