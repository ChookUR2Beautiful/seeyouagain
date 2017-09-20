/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarPlayerSettingDao;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerSetting;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerSettingService
 * 
 * 类描述： 大赛报名审核Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-7 上午11:00:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TVstarPlayerSettingService extends BaseService<TVstarPlayerSetting> {

	/**
	 * 导入报名审核DAO
	 */
	@Autowired
	private TVstarPlayerSettingDao playerSettingDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return playerSettingDao;
	}

	/**
	 * 方法描述：获取报名设置配置(唯一记录) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-7上午11:06:39 <br/>
	 * @param long1
	 * @return
	 */
	public TVstarPlayerSetting getFirstObject() {
		return playerSettingDao.getFirstObject();
	}

}
