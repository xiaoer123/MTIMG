package com.dehui.communication.remote.api;

import java.util.Map;


public interface BasicRemote {
	void doSaveDevice(String empno, String clientId, String channelId, String device, String os, String appEdition,
			String type) throws Exception;

	void checkDevicee(String empno, String clientId) throws Exception;

	void doValidAndBindAllInOne(String empno, String clientId, String channelId, String device, String os,
			String appEdition, String type, String phonenum, String businesstype, String vcode) throws Exception;

	
	Map<String, Object> getSysDictionaryMap(String code) throws Exception;
}
