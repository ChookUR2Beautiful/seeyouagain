/**
 * 
 */
package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TSalePropertyValue;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：SalePropertyValueDao
 *
 * 类描述：SalePropertyValueDao
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月6日下午5:55:33
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SalePropertyValueDao extends BaseDao<TSalePropertyValue> {

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月9日上午9:42:03
	 * @param propIds
	 */
	@DataSource("master")
	void batchDelete(List<Integer> propIds);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月9日下午3:06:41
	 * @param id
	 */
	@DataSource("master")
	void deleteById(Integer id);

}
