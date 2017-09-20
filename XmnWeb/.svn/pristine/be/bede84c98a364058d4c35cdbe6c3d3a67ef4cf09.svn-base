package com.xmniao.xmn.core.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.AppVersionDao;
import com.xmniao.xmn.core.common.entity.TAppVersion;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AppVersionService
 * 
 * 类描述： 版本
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时51分56秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class AppVersionService extends BaseService<TAppVersion> {

	@Autowired
	private AppVersionDao appVersionDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return appVersionDao;
	}
	
	/**
	 * 添加
	 * @param app
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addNewVersion(TAppVersion app){
		setNewVersion(app);
		app.setSdate(new Date());
		super.add(app);
	}
	
	/**
	 * 更新
	 * @param app
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateVersion(TAppVersion app){
		setNewVersion(app);
		super.update(app);
	}
	
	/**
	 * 设置同其他版本的其他版本为停止更新
	 * @param type  版本类型
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void setNewVersion(TAppVersion app){
		int status = app.getStatus();
		if(status==1){
			appVersionDao.updateNewVersion(app);
		}
		
	}
	
}
