package com.dehui.communication.remote.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dehui.communication.remote.api.BasicConstants;
import com.dehui.communication.remote.util.GetFilePath;
import com.dehui.communication.remote.util.StringUtils;
import com.dehui.communication.remote.util.getImagePath;
import com.digitalchina.frame.exception.BusinessException;
import com.digitalchina.frame.message.MessageObject;
import com.digitalchina.frame.message.Result;
import com.digitalchina.frame.message.ServiceContext;

@Service
public class ImgService {
	
	
	public void saveFile(ServiceContext context) throws Exception {
		MessageObject mo = context.getRequestData();
       	Assert.notNull(mo, "请求数据为空");
    	String jsonStr=StringUtils.objToStr(mo.getValue("jsonStr"));
    	String type=StringUtils.objToStr(mo.getValue("type"));
    	String member_id=StringUtils.objToStr(mo.getValue("member_id"));
    	String compress=StringUtils.objToStr(mo.getValue("compress"));
    	
		if (StringUtils.isEmpty(jsonStr)) throw new BusinessException(BasicConstants.ERROR, "jsonStr不可为空");
		if (StringUtils.isEmpty(type)) throw new BusinessException(BasicConstants.ERROR, "分类不可为空");
		if (StringUtils.isEmpty(member_id)) throw new BusinessException(BasicConstants.ERROR, "member_id不可为空");

		Map<String, Object> result = new HashMap<String, Object>();   
		 try {
			 String path=GetFilePath.uploadFile(jsonStr, type, member_id);
			 result.put("return", "true");
			 result.put("filePath", path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result.put("return", "false");
		}
		context.setResponseData(result);
       	context.setResult(Result.SUCCESS_RESULT);
	}
	
	/**
	 * 保存图片，返回路径---IM0002
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public void saveImg(ServiceContext context) throws Exception {
		MessageObject mo = context.getRequestData();
       	Assert.notNull(mo, "请求数据为空");
    	String img=StringUtils.objToStr(mo.getValue("img"));
    	String type=StringUtils.objToStr(mo.getValue("type"));
    	String compress=StringUtils.objToStr(mo.getValue("compress"));
    	
		if (StringUtils.isEmpty(img)) throw new BusinessException(BasicConstants.ERROR, "IMG不可为空");
		if (StringUtils.isEmpty(type)) throw new BusinessException(BasicConstants.ERROR, "分类不可为空");

		Map<String, Object> result = new HashMap<String, Object>();   
		 try {
			 String imgPath=getImagePath.saveImg(img,type);
			 result.put("return", "true");
			 result.put("imgPath", imgPath);
			 result.put("status", "0");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("return", "false");
			result.put("status", "0");
		}
		context.setResponseData(result);
       	context.setResult(Result.SUCCESS_RESULT);
	}
}
