package com.expocms.server.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class AuthCodeCacheSerivce {
    public String getAuthCode(String key){  
    	 String value = null;
         CacheManager manager = CacheManager.create();
         Cache cache = manager.getCache("authCodeCache");
         if(cache.isKeyInCache(key)){
             Element el = cache.get(key);
             value= (String)el.getObjectValue();
         }
         return value;
    } 
    
    public void update(String key,String code){  
    	CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("authCodeCache");
        Element el=new Element(key, code);
        if(cache.isKeyInCache(key)){
        	cache.remove(key);
        }
        cache.put(el);
        //cache.flush();
    } 
    public void removeAll(){  
    	CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("authCodeCache");
        cache.removeAll();
        //cache.flush();
    } 
    public void remove(String key){  
    	CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("authCodeCache");
        cache.remove(key);
        //cache.flush();
    }  
}
