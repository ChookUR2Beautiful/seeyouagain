package com.xmniao.xmn.core.system_settings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.RoleAuthorityDao;
import com.xmniao.xmn.core.system_settings.entity.TRoleAuthority;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RoleAuthorityService
 * 
 * 类描述： 角色资源
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时11分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class RoleAuthorityService extends BaseService<TRoleAuthority> {

	@Autowired
	private RoleAuthorityDao roleAuthorityDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return roleAuthorityDao;
	}
	
	/**
	 * 获取角色拥有权限id
	 * @param id
	 * @return
	 */
	public List<Long> getAuthority(Long id){
		return roleAuthorityDao.getAuthority(id);
	}
	
	/**
	 * 获取角色拥有权限id
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByRole(Long id){
		roleAuthorityDao.deleteByRole(id);
	}
	
	
}
