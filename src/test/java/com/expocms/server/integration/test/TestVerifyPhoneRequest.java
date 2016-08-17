package com.expocms.server.integration.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSON;
import com.expocms.server.request.types.impl.MoreInfoRequest;
import com.expocms.server.request.types.impl.ProductDetailsRequest;
import com.expocms.server.request.types.impl.RecommendRequest;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;

public class TestVerifyPhoneRequest {
	public static void main(String[] args) {
		sendRequest();
	}
	public static String sendRequest(){
		HttpClient client = new HttpClient();
		
		String url = "http://localhost:8888/app/verifyPhone";
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		VerifyPhoneRequest test=new VerifyPhoneRequest();
		test.setPhoneNo("18501308512");
		postMethod.setRequestBody(JSON.toJSONString(test));
		System.out.println(JSON.toJSONString(test));
		try {
			client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = null;
		try {
			str = new String(postMethod.getResponseBody(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		postMethod.releaseConnection();
		return str;
	}
}
