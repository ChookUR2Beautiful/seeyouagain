package com.xmniao.xmn.core.system_settings.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.RoleAuthorityDao;
import com.xmniao.xmn.core.system_settings.dao.RoleDao;
import com.xmniao.xmn.core.system_settings.dao.UserDao;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.util.StringUtils;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RoleService
 * 
 * 类描述： 角色
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时08分19秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class RoleService extends BaseService<TRole> {

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 角色资源表
	 */
	@Autowired
	private RoleAuthorityDao authorityDao;
	
	/**
	 * 用户表
	 */
	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return roleDao;
	}
	
	/**
	 * 删除角色时删除与角色关联的资源以及让解绑拥有该角色的用户
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delRole(String id){
		List<String> idContainer =new ArrayList<String>();
		/**
		 * 解析成数组
		 */
		StringUtils.paresToList(id,idContainer,",");
		Object[] ids = idContainer.toArray();
		authorityDao.delete(ids);
		userDao.deleteRole(ids);
		super.delete(ids);
	}
	
	public List<TRole> getOtherRoleList(Long id){
		return roleDao.getotherRoleList(id);
	}
	
}
