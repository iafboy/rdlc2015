package com.expocms.server.launcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;

public class HTTPSServerInitializer extends ChannelInitializer<SocketChannel> {
	
	private static Logger logger = Logger.getLogger(HTTPSServerInitializer.class);
	
	private SSLContext sslContext_ = null;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		if(this.sslContext_ == null)
			this.sslContext_ = createSSLContext2();
			
		SSLEngine engine = this.sslContext_.createSSLEngine();
		engine.setUseClientMode(false);
		engine.setNeedClientAuth(false);
		pipeline.addLast("ssl", new SslHandler(engine));
		
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("handler", new HTTPServerHandler());
	}
	
	private SSLContext createSSLContext() {
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e) {
			logger.error("create KeyStore instance failed!", e);
			return null;
		}
		
		InputStream in = null;
		try {
			in = new FileInputStream(Constants.sslCertificate);
		} catch (FileNotFoundException e) {
			logger.error("open KeyStore file failed!", e);
			return null;
		}
		
		try {
			ks.load(in, Constants.sslStorePwd.toCharArray());
		} catch (NoSuchAlgorithmException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} catch (CertificateException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} catch (IOException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		KeyManagerFactory kmf = null;
		try {
			kmf = KeyManagerFactory.getInstance("SunX509");
		} catch (NoSuchAlgorithmException e) {
			logger.error("create key manager factory failed!", e);
			return null;
		}
		
		try {
			kmf.init(ks, Constants.sslStorePwd.toCharArray());
		} catch (UnrecoverableKeyException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		} catch (KeyStoreException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		}
		
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSLv3");
		} catch (NoSuchAlgorithmException e) {
			logger.error("create SSL context failed!", e);
			return null;
		}
		
		try {
			sslContext.init(kmf.getKeyManagers(), null, null);
		} catch (KeyManagementException e) {
			logger.error("init SSL context failed!", e);
			return null;
		}
		
		return sslContext;
	}
	
	private SSLContext createSSLContext2() {
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e) {
			logger.error("create KeyStore instance failed!", e);
			return null;
		}
		
		InputStream in = null;
		try {
			in = new FileInputStream(Constants.sslCertificate);
		} catch (FileNotFoundException e) {
			logger.error("open KeyStore file failed!", e);
			return null;
		}
		
		try {
			ks.load(in, Constants.sslStorePwd.toCharArray());
		} catch (NoSuchAlgorithmException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} catch (CertificateException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} catch (IOException e) {
			logger.error("load KeyStore file failed!", e);
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		KeyManagerFactory kmf = null;
		try {
			kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
		} catch (NoSuchAlgorithmException e) {
			logger.error("create key manager factory failed!", e);
			return null;
		} catch (NoSuchProviderException e) {
			logger.error("create key manager factory failed!", e);
			return null;
		}
		
		try {
			kmf.init(ks, Constants.sslStorePwd.toCharArray());
		} catch (UnrecoverableKeyException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		} catch (KeyStoreException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			logger.error("init key manager factory failed!", e);
			return null;
		}
		
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			logger.error("create SSL context failed!", e);
			return null;
		}
		
		try {
			sslContext.init(kmf.getKeyManagers(), null, null);
		} catch (KeyManagementException e) {
			logger.error("init SSL context failed!", e);
			return null;
		}
		
		return sslContext;
	}

}
