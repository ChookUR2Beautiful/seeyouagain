package com.xmniao.xmn.core.marketingmanagement.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.ExtensionSet;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：ExtensionsetDao
 * 
 * 类描述：查询推广设置列表
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface ExtensionsetDao extends BaseDao<ExtensionSet>{
	
	/**
	 * 获取订单信息列表
	 */
	@DataSource("slave")
	public List<ExtensionSet> getExtensionSetList(ExtensionSet extensionSet);
		
	/**
	 * 获取客户信息列表billCount
	 */
	@DataSource("slave")
	public Long getExtensionSetCount(ExtensionSet extensionSet);
	
	/**
	 * 获取排序号是否重复
	 */
	@DataSource("slave")
	public Long getSortExist(ExtensionSet extensionSet);
}
