package com.expocms.server.launcher;

import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HTTPSServer implements Runnable {
	
	private static Logger logger = Logger.getLogger(HTTPSServer.class);
	
	private final int port;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	public HTTPSServer(int port) {
		this.port = port;
	}

	public void run() {
		this.bossGroup = new NioEventLoopGroup();
		this.workerGroup = new NioEventLoopGroup();
		try {
			// start HTTP server ...
			ServerBootstrap b = new ServerBootstrap();
			b.group(this.bossGroup, this.workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new HTTPSServerInitializer());
			logger.info("binding to port " + port);
			ChannelFuture f = b.bind(port).sync();
			//f.addListener(ChannelFutureListener.CLOSE);
			logger.info("HTTPS server start OK!");
			f.channel().closeFuture().sync();
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			try {
				// stop HTTP server ...
				shutdown();
			} catch(Exception ex) {
				logger.error(ex);
			}
		}
	}
	
	public void shutdown() throws Exception {
		if(this.workerGroup != null && this.bossGroup != null) {
			this.workerGroup.shutdownGracefully();
			this.bossGroup.shutdownGracefully();
			logger.info("Server shutdown!");
		}
	}

}
