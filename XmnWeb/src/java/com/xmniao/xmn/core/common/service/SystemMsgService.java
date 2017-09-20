package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.SystemMsgDao;
import com.xmniao.xmn.core.common.entity.TSystemMsg;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemMsgService
 * 
 * 类描述： 系统信息推送
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时05分04秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SystemMsgService extends BaseService<TSystemMsg> {

	@Autowired
	private SystemMsgDao systemMsgDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return systemMsgDao;
	}
	
}
