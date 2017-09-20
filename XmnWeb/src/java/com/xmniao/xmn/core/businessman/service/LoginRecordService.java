package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.LoginRecordDao;
import com.xmniao.xmn.core.businessman.entity.TLoginRecord;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LoginRecordService
 * 
 * 类描述： 商家账号登录记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日16时58分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class LoginRecordService extends BaseService<TLoginRecord> {

	@Autowired
	private LoginRecordDao loginRecordDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return loginRecordDao;
	}
	
}
