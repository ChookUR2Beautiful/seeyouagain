package com.xmniao.xmn.core.system_settings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.AuthorityDao;
import com.xmniao.xmn.core.system_settings.entity.TAuthority;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AuthorityService
 * 
 * 类描述： 资源
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时19分59秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class AuthorityService extends BaseService<TAuthority> {

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return authorityDao;
	}
	
}
