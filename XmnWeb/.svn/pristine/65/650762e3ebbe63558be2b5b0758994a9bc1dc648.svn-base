package com.xmniao.xmn.core.system_settings.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.UserDao;
import com.xmniao.xmn.core.system_settings.entity.TUser;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UserService
 * 
 * 类描述： 用户
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日15时07分03秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class UserService extends BaseService<TUser> {



	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return userDao;
	}
	
	
	
	
	/**
	 * 删除管理员帐号跳过
	 * @param list
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteUid(List<String> list) {
		int index = list.indexOf("0");
		if(index>0){
			list.remove(index);
		}
		String[] ids = new String[list.size()];
		return super.delete(list.toArray(ids));
	}
	
	
	
	public long getUsernameCount(String username){
		return userDao.getUsernameCount(username);
	}
	
	public TUser loginCheck(TUser u){
		return userDao.loginCheck(u);
	}
	
	
}
