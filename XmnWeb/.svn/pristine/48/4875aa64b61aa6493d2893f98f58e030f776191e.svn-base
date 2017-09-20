package com.xmniao.xmn.core.system_settings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.LogDao;
import com.xmniao.xmn.core.system_settings.entity.TLog;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LogService
 * 
 * 类描述： 操作日志
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日17时04分29秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class LogService extends BaseService<TLog> {

	@Autowired
	private LogDao logDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return logDao;
	}
	
}
