package com.expocms.server.launcher;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;
import com.expocms.server.request.RequestHandleManager;
import com.expocms.server.request.handlers.impl.EmptyContentRequestHandle;
import com.expocms.server.request.handlers.impl.NotPostRequestHandle;
import com.expocms.server.request.handlers.impl.ReadLogRequestHandle;
import com.expocms.server.request.handlers.inf.RequestHandle;
import com.expocms.server.util.ByteBufToBytes;
import com.expocms.server.util.MD5Util;
import com.expocms.server.util.UrlParse;
import com.google.common.hash.Hashing;

public class HTTPServerHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = Logger.getLogger(HTTPServerHandler.class);
	private boolean ONLY_POST = false;
	
	private boolean flag = true;
	private ByteBufToBytes reader;
	private HttpRequest httpRequest;

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if (msg instanceof HttpRequest) {
			logger.debug("Got request: "+((HttpRequest)msg).getUri());
			httpRequest = (HttpRequest) msg;
			if(Constants.authheard){
				String authvalue=httpRequest.headers().get("client_auth");
				if(authvalue==null||!authvalue.equals(Hashing.md5().hashString(Constants.authseed, Charset.defaultCharset()))){
					flag = false;
					return;
				}else{
					String paraStr = httpRequest.getUri().split("\\?")[1];
					Map<String,String[]> para = new HashMap<String, String[]>(); 
					try {
						UrlParse.parseParameters(para, paraStr, "utf-8");
						if(para.get("key") != null && para.get("sign") != null){
							String key = para.get("key")[0];
							String sign = para.get("sign")[0];
							if(!MD5Util.MD5(key).equals(sign)){
								flag = false;
								return;
							}
						}
						flag = false;
						return;
					} catch (Exception e) {
						logger.error("Auth error "+e.getMessage(),e);
					}
				}
			}
			//日志查看，上线删除
			if(httpRequest.getUri().equals("/GetLog")){
				RequestHandle requestHandle = new ReadLogRequestHandle(ctx, null,null);
				requestHandle.handle();
				return;
			}
			/*
			String paraStr = httpRequest.getUri().split("\\?")[1];
			
			Map<String,String[]> para = new HashMap<String, String[]>(); 
			try {
				UrlParse.parseParameters(para, paraStr, "utf-8");
				if(para.get("key") != null && para.get("sign") != null){
					String key = para.get("key")[0];
					String sign = para.get("sign")[0];
					if(!MD5Util.MD5(key+"food").equals(sign)){
						flag = 0;
						return;
					}
				}
				flag = 0;
				return;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			*/
			
			//拦截一切非POST请求
			if(ONLY_POST && httpRequest.getMethod() != io.netty.handler.codec.http.HttpMethod.POST){
				RequestHandle requestHandle = new NotPostRequestHandle(ctx, null,null);
				requestHandle.handle();
				flag = false;
				return;
			}

			if (HttpHeaders.isContentLengthSet(httpRequest)) {
				reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(httpRequest));
			}
		}

		if (flag && msg instanceof HttpContent) {
			
			HttpContent httpContent = (HttpContent) msg;
			
			ByteBuf content = httpContent.content();

			if (content.isReadable()) {
				reader.reading(content);
				content.release();

				if (reader.isEnd()) {
					String uri = httpRequest.getUri().split("\\?")[0];
					logger.warn("request uri:"+uri);
					String contentStr = null;
					
					try {
						contentStr = new String(reader.readFull(),"utf-8");
						logger.warn("request content: " + contentStr);
					} catch (Exception e) {
						RequestHandle requestHandle = new EmptyContentRequestHandle(ctx, null,null);
						requestHandle.handle();
						logger.error(e.getMessage(),e);
						e.printStackTrace();
						return;
						
					
					}
					RequestHandleManager.forwardRequest(uri, contentStr, ctx);
				}
			} else {
				RequestHandle requestHandle = new EmptyContentRequestHandle(ctx, null,null);
				requestHandle.handle();
			}
		}
	}

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
	}
}