package com.xmniao.xmn.core.business_statistics.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographModule;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographModuleDao
 * 
 * 类描述： 微图助力页面模块Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 上午11:43:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TMicrographModuleDao extends BaseDao<TMicrographModule>{
	
	
	/**
	 * 
	 * 方法描述：根据pageId删除页面模块资源信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午5:08:47 <br/>
	 * @param pageId
	 * @return
	 */
	@DataSource(value = "slave")
	public int deleteByPageId(Integer pageId);
	
}