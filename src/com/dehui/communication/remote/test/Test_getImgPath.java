package com.dehui.communication.remote.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import sun.misc.BASE64Encoder;

import com.digitalchina.frame.util.Algorithm3DES;
import com.digitalchina.frame.util.AlgorithmData;
import com.digitalchina.frame.util.MD5Util;
import com.google.gson.Gson;

public class Test_getImgPath {


	/**
	 * @param args
	 * @throws Exception 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, Exception {
		// TODO Auto-generated method stub
	    // String verifyUrl = "http://uatsms.laiyifen.com:7080/MTIMG";
		 //String verifyUrl = "http://localhost:8080/MTIMG";
		 String verifyUrl = "http://120.132.6.202/MTIMG";

			PostMethod postMethod = new PostMethod(verifyUrl + "/service/IMG001");    
			//String appid = "ARD-310000-0001";
			//String appkey = "d9893b98d5ae542ccd206d6b83cb456286044307681abf16";
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> head = new HashMap<String, Object>();
			Map<String, Object> body = new HashMap<String, Object>();
			data.put("head", head);
			//head.put("appid", appid);
			head.put("version", "2.0");
			data.put("body", body);
			  File file=new File("D:/MQ/1496995211.jpg");
			  String encodeImgageToBase64=encodeImgageToBase64(file);
			 body.put("img",encodeImgageToBase64); //图片base64
			 body.put("type","id_card"); //分类 caiwu
			 body.put("compress","");  //压缩格式--预留
			//head.put("sign", signRequest(appid, body, appkey));
			Gson gson = new Gson();
			String str = gson.toJson(data);
			RequestEntity re = new StringRequestEntity(str, "application/json",	"utf-8");
			postMethod.setRequestEntity(re);
			new HttpClient().executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			System.out.println(result);
			/*String str1 = gson.toJson(head);
			System.out.println("head"+str1);
			String str2 = gson.toJson(body);
			System.out.println("body"+str2);*/
	}

	public static String signRequest(String appid, Object src, String appkey)
			throws Exception {
		Gson gson = new Gson();
		String srcText = gson.toJson(src);
		String base64Text = new String(Base64.encodeBase64(
				(appid + srcText).getBytes(), false));

		String destText = MD5Util.md5Digest(base64Text);
		AlgorithmData data = new AlgorithmData();
		data.setDataMing(destText);
		data.setKey(appkey);
		Algorithm3DES.encryptMode(data);
		return data.getDataMi();
	}
	
	
	public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
	}
}
