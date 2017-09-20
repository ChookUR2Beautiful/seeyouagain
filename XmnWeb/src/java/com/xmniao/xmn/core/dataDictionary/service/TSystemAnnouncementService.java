/**
 * 
 */
package com.xmniao.xmn.core.dataDictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.dataDictionary.dao.TSystemAnnouncementDao;
import com.xmniao.xmn.core.dataDictionary.entity.TSystemAnnouncement;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSystemAnnouncementService
 * 
 * 类描述： 系统公告Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-17 下午4:47:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public  class TSystemAnnouncementService extends BaseService<TSystemAnnouncement> {
	
	/**
	 * 注入系统公告Dao
	 */
	@Autowired
	private TSystemAnnouncementDao announcementDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return announcementDao;
	}

}
