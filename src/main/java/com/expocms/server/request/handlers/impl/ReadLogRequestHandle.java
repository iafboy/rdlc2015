package com.expocms.server.request.handlers.impl;

import com.expocms.server.constant.Constants;
import com.expocms.server.request.handlers.inf.RequestHandle;

import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
@Controller
@Scope("prototype")
public class ReadLogRequestHandle extends RequestHandle{
    private static Logger logger = Logger.getLogger(ReadLogRequestHandle.class);
    public ReadLogRequestHandle(){
    	
    }
	public ReadLogRequestHandle(ChannelHandlerContext ctx, String contentStr,String className_) {
		//super(ctx, contentStr,className_);
	}

	@Override
	public String getHandleResult() {
		FileReader fr;
		StringBuffer buffer = new StringBuffer();
		try {
			
			fr = new FileReader(Constants.readlog);
			BufferedReader br=new BufferedReader(fr);
	        while(br.readLine()!=null){
	    		buffer.append(br.readLine()+"<br />");
	        }
	        br.close();
	        return buffer.toString();
		} catch (IOException e) {
			return "日志不存在！";
		}
	}

}
