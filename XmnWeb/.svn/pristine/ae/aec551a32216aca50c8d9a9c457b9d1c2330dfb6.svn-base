package com.xmniao.xmn.core.business_statistics.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographPage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographPageDao
 * 
 * 类描述： 微图助力模板页面Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午3:20:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TMicrographPageDao extends BaseDao<TMicrographPage>{

	/**
	 * 方法描述：获取指定模板最大页面序号 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午6:26:58 <br/>
	 * @return
	 */
	@DataSource(value = "slave")
	Integer getMaxIdByTemplateId(TMicrographPage micrograpPage);
}