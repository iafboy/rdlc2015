package com.expocms.server.launcher;

import javax.net.ssl.SSLEngine;

import io.netty.handler.ssl.SslHandler;

public class HTTPServerSSLHandler extends SslHandler {

	public HTTPServerSSLHandler(SSLEngine engine) {
		super(engine);
	}

}
