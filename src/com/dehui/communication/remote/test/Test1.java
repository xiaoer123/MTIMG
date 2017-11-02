package com.dehui.communication.remote.test;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import com.dehui.communication.remote.util.SignRequest;
import com.google.gson.Gson;

public class Test1 {
	 public static void main(String[] args)
			    throws Exception
			  {
			   String verifyUrl = "http://127.0.0.1:8080/MINGTOU_TEST";
			   //String verifyUrl = "http://uatsms.laiyifen.com:7080/sms_common";//测试环境
			    PostMethod postMethod = new PostMethod(verifyUrl + "/service/TEST001");
			    String appid = "ARD-310000-0001";
			    String appkey = "d9893b98d5ae542ccd206d6b83cb456286044307681abf16";
			    Map<String,Object> data = new HashMap<String,Object>();
			    Map<String,Object> head = new HashMap<String,Object>();
			    Map<String,Object> body = new HashMap<String,Object>();
			    data.put("head", head);
			    head.put("version", "2.0");
			    head.put("appid", appid);
			    data.put("body", body);

			    body.put("user", "wuyixiang");
			    body.put("password", "123456");
			 
			    head.put("sign", SignRequest.signRequest(appid, body, appkey));
			    Gson gson = new Gson();
			    String str = gson.toJson(data);
			    System.out.println("=============" + str);
			    RequestEntity re = new StringRequestEntity(str, "application/json", 
			      "utf-8");
			    postMethod.setRequestEntity(re);
			    new HttpClient().executeMethod(postMethod);
			    String result = postMethod.getResponseBodyAsString();
			    System.out.println(result);
			  }



}
