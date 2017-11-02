package com.dehui.securityCall;

import com.dehui.base.appcert.bean.AppcertVO;


/**
 * 类名�?SecurityCallRs
 * <br>描述：应用验签
 * @author xingxiaobing
 * @date 2013-12-12 上午10:34:13 
 * @version 1.0
 * <br>Copyright by DCITS.
 * <br>Program Name:image
 */
public interface SecurityCallRs {
	
	/**
	 * <p>方法描述:<br>
	 * 根据验证ID获取验证KEY
	 * @author chengqp
	 * @param appId 验证ID
	 * @return String 验证KEY
	 * @throws Exception
	 */ 
	public String getAppkey(String appid)throws Exception;
	
	public AppcertVO getAppcert(String appid)throws Exception;
	
}
