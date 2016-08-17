package com.expocms.server.util;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.expocms.server.util.MD5Util;

public class TestInterface {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		String gateway = "http://api.cannews.com.cn/";
	    String auth_key = "172fc5aee6c29ec1e6efd340de67b0cc";  // 接口公钥
	    String auth_secret = "dc82a1f2b6041cf88e73f7368c9a43df"; // 接口私钥
	    String api_url = "?app=page&controller=page&action=ls";

	    Map<String,String> get = new TreeMap<String,String>();
	    Map<String,String> post = new TreeMap<String,String>();
	    
	    get.put("pageid", "1");
	    
	    Map<String,String> all = get;
	    all.putAll(post);
	    
	    String sign = "";
	    for(String s : all.keySet()){
	    	sign += s + "=" + all.get(s) +"&";
	    }
	    if( sign.length()>0 )
	    	sign = sign.substring(0, sign.length()-1);
	    System.out.println(sign);
	    sign += auth_secret;
	    System.out.println(sign);
	    sign = MD5Util.MD5(sign);
	    System.out.println(sign);
	    
	    String request_url = gateway + api_url + "&key=" +auth_key + "&sign=" + sign+"&pageid=1"; 
	    System.out.println(request_url);
	    
		GetMethod getMethod = new GetMethod(request_url);
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		client.executeMethod(getMethod);
		String str = new String(getMethod.getResponseBody(),"utf-8");
		System.out.println(str);
		getMethod.releaseConnection();
	}
}
