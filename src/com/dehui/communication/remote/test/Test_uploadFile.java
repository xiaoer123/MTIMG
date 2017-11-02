package com.dehui.communication.remote.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.gson.Gson;

public class Test_uploadFile {
	public static void main(String[] args) throws HttpException, IOException {
		//String verifyUrl = "http://localhost:8080/MTIMG";
		String verifyUrl = "http://uatsms.laiyifen.com:7080/MTIMG";
		PostMethod postMethod = new PostMethod(verifyUrl + "/service/FILE001");    
		//String appid = "ARD-310000-0001";
		//String appkey = "d9893b98d5ae542ccd206d6b83cb456286044307681abf16";
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> head = new HashMap<String, Object>();
		Map<String, Object> body = new HashMap<String, Object>();
		data.put("head", head);
		//head.put("appid", appid);
		head.put("version", "2.0");
		data.put("body", body);
	 
		 body.put("jsonStr","[{a:1,b:{b1:[{a:2},{a:1}]},c:3},{a:1},{b:1}]"); //json shuju 
		 body.put("type","originalData"); //分类 caiwu
		 body.put("compress","");  //压缩格式--预留
		 body.put("member_id","4");
		//head.put("sign", signRequest(appid, body, appkey));
		Gson gson = new Gson();
		String str = gson.toJson(data);
		RequestEntity re = new StringRequestEntity(str, "application/json",	"utf-8");
		postMethod.setRequestEntity(re);
		new HttpClient().executeMethod(postMethod);
		String result = postMethod.getResponseBodyAsString();
		System.out.println(result);
	}

}
