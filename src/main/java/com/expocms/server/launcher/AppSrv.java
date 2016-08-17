package com.expocms.server.launcher;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.expocms.server.constant.Constants;
import com.expocms.server.schedule.OrderManagementTask;

public final class AppSrv {
	
	private static Logger logger = Logger.getLogger(AppSrv.class);
	
	private static Properties properties_ = null;
	private static Thread httpThread_ = null;
	private static Thread httpsThread_ = null;
	private static OrderManagementTask omTask_ = null;

	public static void main(String[] args) {
		// load configuration ...
		Constants.applicationContext = new ClassPathXmlApplicationContext(Constants.mainCfg);
		properties_ = (Properties)Constants.applicationContext.getBean("configproperties");
		initParam();
		
		// start HTTP server ...
		logger.info("start HTTP server ...");
		try {
			HTTPServer server = new HTTPServer(Constants.servicePort);
			httpThread_ = new Thread(server);
			httpThread_.start();
		} catch (Exception e) {
			logger.error("HTTP server start failed!");
			logger.error(e.getMessage(), e);
			return;
		}
		
		// start HTTPS server ...
		logger.info("start HTTPS server ...");
		try {
			HTTPSServer server = new HTTPSServer(Constants.serviceSslPort);
			httpsThread_ = new Thread(server);
			httpsThread_.start();
		} catch (Exception e) {
			logger.error("HTTPS server start failed!");
			logger.error(e.getMessage(), e);
			return;
		}

		// start order management task ...
		logger.info("start order management task ...");
		omTask_ = new OrderManagementTask();
		omTask_.start();

		// main thread need to be pending ...
		logger.info("system started ...");
		boolean flag = true;
		while(flag) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		// stop order management task ...
		omTask_.stop();
		
		logger.info("system stopped.");
	}
	
	private static void initParam() {
		try {
			Constants.servicePort = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.serviceport").trim());
			Constants.serviceSslPort = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.servicesslport").trim());
			Constants.TPS_port = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.TPS_port").trim());
			Constants.sslCertificate = properties_.getProperty("simpleappsrvcfg.sslcertificate");
			Constants.sslStorePwd = properties_.getProperty("simpleappsrvcfg.sslstorepwd");
			Constants.readlog = properties_.getProperty("simpleappsrvcfg.log.rqhandeler").trim();
			Constants.dbpoolCfg = properties_.getProperty("simpleappsrvcfg.dbpoolcfg").trim();
			Constants.pageItemnumber = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.pageItemnumber"));
			Constants.packageMaxPercent = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.packageMaxPercent"));
			Constants.httpgzip = Boolean.valueOf(properties_.getProperty("simpleappsrvcfg.httpgizp").toLowerCase());
			Constants.authheard = Boolean.valueOf(properties_.getProperty("simpleappsrvcfg.auth").toLowerCase());
			Constants.authseed = properties_.getProperty("simpleappsrvcfg.authseed");
			Constants.rmiserivce = properties_.getProperty("simpleappsrvcfg.rmiserivce");
			Constants.accountKey = properties_.getProperty("simpleappsrvcfg.accountKey");
			Constants.contractfilepath = properties_.getProperty("simpleappsrvcfg.contractfilepath");
			Constants.usercontractfilepath = properties_.getProperty("simpleappsrvcfg.usercontractfilepath");
			Constants.commissionPercent = Integer.parseInt(properties_.getProperty("simpleappsrvcfg.commissionPercent").trim());
			getRequestBiMap();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void getRequestBiMap() {
		String[] requests = properties_.getProperty("simpleappsrvcfg.requests").split(",");
		if(requests != null && requests.length > 0) {
			for(int i = 0, size = requests.length; i < size; i ++) {
				Constants.pcfg.put(requests[i], properties_.getProperty("simpleappsrvcfg.request." + requests[i].toLowerCase(), ""));
			}
		}
	}

}
