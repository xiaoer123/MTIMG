package com.dehui.securityCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dehui.base.appcert.bean.AppcertVO;
import com.dehui.base.appcert.dao.AppcertDaoWrapper;
import com.digitalchina.frame.exception.BusinessException;
import com.digitalchina.frame.exception.ExceptionConstants;


@Component
public class SecurityCallManager implements SecurityCallRs {

	@Autowired
	private AppcertDaoWrapper appcertDaoWrapper;
	public String getAppkey(String appid) throws Exception {
		AppcertVO app = appcertDaoWrapper.selectByPrimaryKey(appid);
		if (app != null) {
			return app.getAppkey();
		} else {
			throw new BusinessException(
					ExceptionConstants.RTN_REMOTE_CALL_NO_KEY_ERROR,
					"appkey不存在");
		}
	}

	public AppcertVO getAppcert(String appid) throws Exception {
		return appcertDaoWrapper.selectByPrimaryKey(appid);
	}

}
