package com.expocms.server.request.handlers.impl;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.handlers.inf.RequestHandle;
import com.expocms.server.response.types.impl.InvalidResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class InternalErrorRequestHandle extends RequestHandle {
    private static Logger logger = Logger.getLogger(InternalErrorRequestHandle.class);
    public InternalErrorRequestHandle(){
    	
    }
	public InternalErrorRequestHandle(ChannelHandlerContext ctx, String contentStr,String className) {
		super(ctx, contentStr,className);
	}

	@Override
	public String getHandleResult() {
		InvalidResponse invalidResponse = new InvalidResponse();
		invalidResponse.setResultCode(-1);
		invalidResponse.setResultMsg("内部错误");
		return JSON.toJSONString(invalidResponse);
	}

}
