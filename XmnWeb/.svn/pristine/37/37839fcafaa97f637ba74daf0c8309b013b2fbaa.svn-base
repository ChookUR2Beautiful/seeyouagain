/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.cloud_design.entity.Supplier;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SupplierDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月16日 上午11:24:55 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface SupplierManageDao{
	
	/**
	 * 
	 * 方法描述：获取供应商列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日上午10:17:11 <br/>
	 * @param supplier
	 * @return
	 */
	@DataSource(value="designer")
	List<Supplier> getList(Supplier supplier);
	
	/**
	 * 
	 * 方法描述：统计记录总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日上午10:17:01 <br/>
	 * @param supplier
	 * @return
	 */
	@DataSource(value="designer")
	Long count(Supplier supplier);
	
	/**
	 * 
	 * 方法描述：新增
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日上午10:16:48 <br/>
	 * @param supplier
	 * @return
	 */
	@DataSource(value="designer")
	Integer add(Supplier supplier);
	
	/**
	 * 
	 * 方法描述：删除供应商
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午1:45:13 <br/>
	 * @param supplier
	 * @return
	 */
	@DataSource(value="designer")
	Integer delete(Supplier supplier);
	
	/**
	 * 
	 * 方法描述：查询供应商信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午3:40:08 <br/>
	 * @return
	 */
	@DataSource(value="designer")
	Supplier getSupplier(Supplier supplier);
	
	/**
	 * 
	 * 方法描述：更新供应商信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午4:37:16 <br/>
	 * @param supplier
	 * @return
	 */
	@DataSource(value="designer")
	Integer update(Supplier supplier);
	
	@DataSource(value="designer")
	Long checksData(Supplier supplier);
	
	@DataSource(value="designer")
	Long checkdData(Supplier supplier);
 }
