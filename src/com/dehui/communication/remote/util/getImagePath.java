package com.dehui.communication.remote.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.digitalchina.common.util.GUIDGen;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class getImagePath {

	
	/*
	 * 保存图片，返回图片路径----二进制图片流
	 */
	public static String getImagePath_2(String file_stream){
		String filPath="";
		try {
	        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");// 文件重命名
	        Date date = new Date();
	        String datename = String.valueOf(bartDateFormat.format(date));
	        String namefile =GUIDGen.getGUID();
	        String realpath ="D:/image/"+datename;
            File file = new File(realpath);
            if(!file.exists()){                //判断文件是否真正存在,如果不存在,创建一个;
                        file.mkdirs();
            }
            InputStream stream =   new   ByteArrayInputStream(file_stream.getBytes("UTF-8"));
            OutputStream bos = new FileOutputStream(realpath + "/" + namefile+".jpg");     // 建立一个上传文件的路径
           filPath=realpath + "/" + namefile+".jpg";
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);// 将文件写入服务器
            }
            bos.close();
            stream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			filPath="";
		}
		return filPath;
	}
	
/*	public static void main(String[] args) throws IOException {
		     File file=new File("C:/qqp.png");
            InputStream in = new FileInputStream(file);
            String s=inputStream2String(in);
            System.out.println("1---"+in);
            String path=getImagePath_2(s);
		     String encodeImgageToBase64=encodeImgageToBase64(file);
		     System.out.println(encodeImgageToBase64);
		     String path=saveImg(encodeImgageToBase64,"shuidian");
            System.out.println(path);
	}*/
	
	//InputStream --> String
	public static  String inputStream2String(InputStream is) throws IOException{
	 BufferedReader in = new BufferedReader(new InputStreamReader(is));
	StringBuffer buffer = new StringBuffer();
	String line = "";
	while ((line = in.readLine()) != null){
	buffer.append(line);
	 }
	 return buffer.toString();
	}
	
	
	/*
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 */
	public static String getImagePath_64(String base64,String piid,String iid){
		String path="";
		try {
	        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");// 文件重命名
	        Date date = new Date();
	        String datename = String.valueOf(bartDateFormat.format(date));
	       // String namefile =GUIDGen.getGUID();
	       String realpath ="/data/opt/IBM/HTTPServer/htdocs/images/"+datename;
	       //String realpath ="D:/image/"+datename;
            File file = new File(realpath);
            if(!file.exists()){                //判断文件是否真正存在,如果不存在,创建一个;
                        file.mkdirs();
            }
            BASE64Decoder decoder = new BASE64Decoder();
    		try {
    			String	filPath=realpath + "/" + piid+"_"+iid+".jpg";
    			FileOutputStream write = new FileOutputStream(new File(filPath));
    			byte[] decoderBytes = decoder.decodeBuffer(base64);
    			write.write(decoderBytes);
    			write.close();
    			//path="http://uatsms.laiyifen.com:7080/images/"+datename+ "/" + piid+"_"+iid+".jpg";
    			path="images/"+datename+ "/" + piid+"_"+iid+".jpg";
    			//path="D:/image/"+datename+ "/" + piid+"_"+iid+".jpg";
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			path="";
		}
		return path;
	}
	
	
	/**
	 * 将网络图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *			图片的url路径，如http://.....xx.jpg
	 * @return
	 */
	public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageUrl);
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

	/**
	 * 将本地图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *			图片的url路径，如http://.....xx.jpg
	 * @return
	 */
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
	

	
	/*
	 * 将Base64位编码的图片进行解码，并保存到指定目录，返回路径
	 */
	public static String saveImg(String base64,String type){
		String path="";
		try {
	        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 文件重命名
	        Date date = new Date();
	        String datename = String.valueOf(bartDateFormat.format(date));
	        String namefile =getRandomFromArray(10);
	        //String realpath ="D:/uploads/"+type+"/"+datename;
	       String realpath ="/data/opt/IBM/HTTPServer/htdocs/uploads/"+type+"/"+datename;
            File file = new File(realpath);
            if(!file.exists()){                //判断文件是否真正存在,如果不存在,创建一个;
                        file.mkdirs();
            }
            BASE64Decoder decoder = new BASE64Decoder();
    		try {
    			String	filPath=realpath  + "/" + namefile+".jpg";
    			FileOutputStream write = new FileOutputStream(new File(filPath));
    			byte[] decoderBytes = decoder.decodeBuffer(base64);
    			write.write(decoderBytes);
    			write.close();
    			//path="http://uatsms.laiyifen.com:7080/images/"+datename+ "/" + namefile+".jpg";
    			path="/uploads/"+type+"/"+datename+ "/" + namefile+".jpg";
    			//path="D:/image/"+datename+ "/" +namefile+".jpg";
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			path="";
		}
		return path;
	}
	
	/**
	 * 获取随机数
	 * @param count
	 * @return
	 */
	public static String getRandomFromArray(int count) {  
	    String[] a = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","Z","Y","Z"};  
	    String result = "";  
	    boolean r[] = new boolean[a.length];  
	    Random random = new Random();  
	    int m = count; // 要随机取的元素个数  
	    if (m > a.length || m < 0)  
	        return a.toString();  
	    int n = 0;  
	    while (true) {  
	        int temp = random.nextInt(a.length);  
	        if (!r[temp]) {  
	            if (n == m) // 取到足量随机数后退出循环  
	                break;  
	            n++;  
	           // System.out.println("得到的第" + n + "个随机数为：" + a[temp]);  
	            result += a[temp];  
	            r[temp] = true;  
	        }  
	    }  
	    return String.valueOf(result);  
	} 
	
	public static void main(String[] args) {
		System.out.println(getRandomFromArray(10));
	}
	
}
