package com.expocms.server.launcher;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HTTPServerInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("handler", new HTTPServerHandler());
	}
	
}
