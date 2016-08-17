package com.expocms.server.request;

import java.lang.reflect.Constructor;
import java.util.Properties;

import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;

import com.expocms.server.request.handlers.impl.ExtRequestHandle;
import com.expocms.server.request.handlers.impl.InternalErrorRequestHandle;
import com.expocms.server.request.handlers.impl.NoSuchAPIRequestHandle;
import com.expocms.server.request.handlers.inf.RequestHandle;

public class RequestHandleManager {
	private static Logger logger = Logger.getLogger(RequestHandleManager.class);

	@SuppressWarnings("unused")
	public static void forwardRequest(String uri, String requestStr,
			ChannelHandlerContext ctx) {
		logger.debug("HttpRequest URI: " + uri);
		String reqType=uri.substring(uri.lastIndexOf("/")+1);
		if(reqType!=null){
			String requestClassName=((Properties) Constants.applicationContext.getBean("configproperties")).getProperty("simpleappsrvcfg.request."+reqType.toLowerCase());
			Class<ExtRequestHandle> requestHandlerClass=ExtRequestHandle.class;
			if(requestHandlerClass!=null){
				try {
					Constructor<ExtRequestHandle> rc=requestHandlerClass.getDeclaredConstructor(new Class[]{ChannelHandlerContext.class,String.class,String.class});
					rc.setAccessible(true);
					RequestHandle requestHandle=(RequestHandle) rc.newInstance(new Object[]{ctx,requestStr,requestClassName});
					requestHandle.handle();
					return;
				} catch (Exception e) {
					logger.error("Error create the instance "+requestHandlerClass.getName(),e);
					e.printStackTrace();
					RequestHandle requestHandle = new InternalErrorRequestHandle(ctx,requestStr,null);
					requestHandle.handle();
				}
			}else{
				// 用户请求不匹配
				logger.debug("HttpRequest " + uri
						+ " can not find matched handler.Try to use "
						+ NoSuchAPIRequestHandle.class.getName());
				RequestHandle requestHandle = new NoSuchAPIRequestHandle(ctx,
						requestStr,null);
				requestHandle.handle();
			}
		}
		logger.debug("HttpRequest " + uri
				+ " can not find matched handler.Try to use "
				+ NoSuchAPIRequestHandle.class.getName());
		RequestHandle requestHandle = new NoSuchAPIRequestHandle(ctx,
				requestStr,null);
		requestHandle.handle();
	}
}