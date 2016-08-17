package com.expocms.server.response;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;
import com.expocms.server.util.GzipUtil;

public class ResponseHandleManager {
	private static Logger logger = Logger.getLogger(ResponseHandleManager.class);

	public static HttpResponse getResponse(String responseStr) {

		FullHttpResponse response = null;
		try {
			if(!Constants.httpgzip){
			response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseStr
							.getBytes("UTF-8")));
			response.headers().set("CONTENT_TYPE", "text/plain; charset=utf-8");
			response.headers().set("CONTENT_LENGTH",
					response.content().readableBytes());
			}else{
				byte[] data=null;
				try {
					data=GzipUtil.compress(responseStr.getBytes("UTF-8"));
				} catch (Exception e) {
					logger.error("gzip error",e);
				}
				response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
						HttpResponseStatus.OK, Unpooled.wrappedBuffer(data));
				response.headers().set("CONTENT_TYPE", "text/plain; charset=utf-8");
				response.headers().set("CONTENT_LENGTH",
						data.length);
			}

		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}

		return response;
	}
}
