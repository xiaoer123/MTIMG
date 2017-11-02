package com.dehui.base.appcert.dao;

import java.util.List;

import com.dehui.base.appcert.bean.AppcertVO;
import com.digitalchina.frame.dao.BaseDao;

public interface AppcertDao extends BaseDao{
	
	public AppcertVO selectByPrimaryKey(String appid);
	
	public int deleteByPrimaryKey(String appid);
	
	public List<AppcertVO> findAppcertByAppid(String appid);

}
