package com.expocms.server.request.handlers.impl;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.handlers.inf.RequestHandle;
import com.expocms.server.response.types.impl.InvalidResponse;
//import com.google.gson.Gson;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class NotPostRequestHandle extends RequestHandle{
    private static Logger logger = Logger.getLogger(NotPostRequestHandle.class);
    public NotPostRequestHandle(){
    	
    }
	public NotPostRequestHandle(ChannelHandlerContext ctx, String contentStr,String className_) {
		//super(ctx, contentStr);
	}

	@Override
	public String getHandleResult() {
		InvalidResponse invalidResponse = new InvalidResponse();
		invalidResponse.setResultCode(0);
		invalidResponse.setResultMsg("Only post method is allowed.只允许Post请求！");
		return JSON.toJSONString(invalidResponse);
		
	}

}
