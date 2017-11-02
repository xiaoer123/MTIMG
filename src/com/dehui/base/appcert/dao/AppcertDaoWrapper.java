package com.dehui.base.appcert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dehui.base.appcert.bean.AppcertVO;
import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
@Repository
public class AppcertDaoWrapper {
	@Autowired
	private AppcertDao appcertDao;
    //@ReadThroughSingleCache(expiration=CacheConfiguration.EXPIRED_DAY,namespace="AppcertVO:selectByPrimaryKey")
	public AppcertVO selectByPrimaryKey(String appid){
    	System.out.println("123 test");
		return appcertDao.selectByPrimaryKey(appid);
	}
	
	//@InvalidateSingleCache(namespace = "AppcertVO:selectByPrimaryKey")
	public int deleteByPrimaryKey(String appid){
		return appcertDao.deleteByPrimaryKey(appid);
	}
	
	//@ReadThroughSingleCache(expiration=CacheConfiguration.EXPIRED_DAY,namespace="AppcertVO:findAppcertByAppid")
	public List<AppcertVO> findAppcertByAppid(String appid){
		return appcertDao.findAppcertByAppid(appid);
	}

}
