package com.xmniao.xmn.core.business_cooperation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.StaffDao;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：StaffService
 * 
 * 类描述： 合作商员工
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时26分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class StaffService extends BaseService<TStaff> {

	@Autowired
	private StaffDao staffDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return staffDao;
	}

	public List<TStaff> getStaffsByJointid(Long jointid) {
		return staffDao.getStaffsByJointid(jointid);
	}
	
}
