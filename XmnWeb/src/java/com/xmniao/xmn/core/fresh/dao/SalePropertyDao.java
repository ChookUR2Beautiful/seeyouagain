/**
 * 
 */
package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TSaleProperty;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：SalePropertyDao
 *
 * 类描述：商品属性dao 
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月6日下午5:54:08
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SalePropertyDao extends BaseDao<TSaleProperty> {

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月9日上午9:22:19
	 * @param pid
	 */
	@DataSource("master")
	void deleteByCodeId(long codeId);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月9日下午2:45:12
	 * @param key
	 */
	@DataSource("master")
	void deleteById(Integer id);

}
