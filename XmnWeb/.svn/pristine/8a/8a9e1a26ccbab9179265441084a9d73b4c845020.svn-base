package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ProductFailing;
import com.xmniao.xmn.core.fresh.entity.ProductFailingSerial;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ProductFailingDao
 *
 * 类描述：产品导入失败DAO
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-7-15下午2:15:27
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface ProductFailingDao extends BaseDao<ProductFailing>{
	
	/**
	 * 
	 * 方法描述：查询导入产品失败序列号信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-15下午2:54:59
	 * @param productFailing
	 * @return
	 */
	@DataSource("slave")
	List<ProductFailingSerial> getFailingSerialInfo(ProductFailingSerial productFailingSerial);
	
	/**
	 * 
	 * 方法描述：新增导入产品失败序列信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-15下午3:01:44
	 * @param record
	 * @return
	 */
	@DataSource("master")
	int insertFailingSerial(ProductFailingSerial record);
	
	/**
	 * 
	 * 方法描述：查询产品导入失败信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-15下午2:54:59
	 * @param productFailing
	 * @return
	 */
	@DataSource("slave")
	List<ProductFailing> getProductFailingList(ProductFailing  productFailing);
	
}