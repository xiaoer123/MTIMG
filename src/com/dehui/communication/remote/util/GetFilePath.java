package com.dehui.communication.remote.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetFilePath {

	public static void main(String[] args) throws IOException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss.ssss");// 文件重命名
		Date date = new Date();
		String datename = String.valueOf(bartDateFormat.format(date));
		System.out.println(datename);
	}

	public static String uploadFile(String jsonStr,String type,String member_id) throws IOException {
		String filPath = "";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss.ssss");// 文件重命名
		Date date = new Date();
		String datename = String.valueOf(bartDateFormat.format(date));
		//String realpath = "D:/mx/";//+type+"/" + member_id
		String realpath = "/data/opt/IBM/HTTPServer/htdocs/mx/";
		File file = new File(realpath);
		if (!file.exists()) { // 判断文件是否真正存在,如果不存在,创建一个;
			file.mkdirs();
		}
	/*	JSONArray jsonObj = JSONArray.fromObject(jsonStr);
		String string = jsonObj.toString();*/

		InputStream stream = new ByteArrayInputStream(
				jsonStr.getBytes("UTF-8"));
		OutputStream bos = new FileOutputStream(realpath + type +"." + member_id + "." + datename
				+ ".log"); // 建立一个上传文件的路径
		filPath = type + "." + member_id + "." + datename + ".log";
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);// 将文件写入服务器
		}
		bos.close();
		stream.close();
		return filPath;
	}

}
