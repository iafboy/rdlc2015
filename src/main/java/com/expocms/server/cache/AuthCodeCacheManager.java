package com.expocms.server.cache;

import com.expocms.server.constant.Constants;

public class AuthCodeCacheManager {
	private static AuthCodeCacheManager instance;
	private AuthCodeCacheSerivce service;
	private AuthCodeCacheManager(){	
		if(service==null)
			service=(AuthCodeCacheSerivce) Constants.applicationContext.getBean("authCodeCacheService");
	}
	public synchronized static AuthCodeCacheManager getInstance(){
		if(instance==null)
			instance=new AuthCodeCacheManager();
		return instance;
	}
	public synchronized String findAuthCodeInCache(String key){
		return service.getAuthCode(key);
	}
	public synchronized void putAuthCodeInCache(String key,String value){
		service.update(key, value);
	}
	public synchronized void cleanAllCache(){
		service.removeAll();
	}
	public synchronized void cleanCodeInCache(String key){
		service.remove(key);
	}
}
