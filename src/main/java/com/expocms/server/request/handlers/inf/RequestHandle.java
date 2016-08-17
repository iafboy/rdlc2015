package com.expocms.server.request.handlers.inf;

import io.netty.channel.ChannelHandlerContext;

import com.expocms.server.response.ResponseHandleManager;

import org.apache.log4j.Logger;

public abstract class RequestHandle {

	protected static Logger logger = Logger.getLogger(RequestHandle.class);
	
	protected ChannelHandlerContext ctx;
	protected String contentStr;
	protected Class<?> classType;
	protected String className;
	
	public RequestHandle() {}
	
	public RequestHandle(ChannelHandlerContext ctx, String contentStr, String className) {
		this.ctx = ctx;
		this.contentStr = contentStr;
		this.className = className;
	}
	
	public abstract String getHandleResult();
	
	public void handle() {
		String handleResult = getHandleResult();
        logger.debug("Send response: "+ handleResult);
		ctx.writeAndFlush(ResponseHandleManager.getResponse(handleResult));
		ctx.close();
	}
	
	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}
	
}
