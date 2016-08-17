package com.expocms.server.integration.test;

import com.alibaba.fastjson.JSON;
import com.expocms.server.constant.Constants;
import com.expocms.server.request.types.impl.VerifyPhoneRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2015/9/19.
 */
public abstract class InterfaceTestable {
    protected static String pre ="";
            static{
//                Properties p = (Properties) Constants.applicationContext.getBean("configproperties");
//                int port = Integer.parseInt(p.getProperty("simpleappsrvcfg.serviceport"));
                pre ="http://localhost:9999";
            	//pre = "http://localhost:13000";
            	//pre = "http://123.57.152.218:38888";
            	//pre = "http://123.57.152.218:39999";
            	//pre = "http://123.56.109.118:38888";
            }
    protected static String url = "";
    protected static Object test = null;
    protected static Map<String,Object> params = new HashMap<String, Object>();
    protected static String sendRequest(String url,Object test){
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(pre+url);
        postMethod.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
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
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        postMethod.releaseConnection();
        return str;
    }

}
