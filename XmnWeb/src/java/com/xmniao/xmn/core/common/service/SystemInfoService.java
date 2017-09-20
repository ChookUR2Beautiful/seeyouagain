package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.SystemInfoDao;
import com.xmniao.xmn.core.common.entity.TSystemInfo;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemInfoService
 * 
 * 类描述： 系统信息发布
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时58分46秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SystemInfoService extends BaseService<TSystemInfo> {

	@Autowired
	private SystemInfoDao systemInfoDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return systemInfoDao;
	}
	
}
