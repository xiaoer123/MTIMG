package com.dehui.communication.remote.util;

import org.apache.commons.codec.binary.Base64;

import com.digitalchina.frame.util.Algorithm3DES;
import com.digitalchina.frame.util.AlgorithmData;
import com.digitalchina.frame.util.MD5Util;
import com.google.gson.Gson;

public class SignRequest {

	  public static String signRequest(String appid, Object src, String appkey) throws Exception
	  {
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
}
