package com.xmniao.xmn.core.marketingmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.marketingmanagement.dao.ExtensionsetDao;
import com.xmniao.xmn.core.marketingmanagement.entity.ExtensionSet;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：ExtensionSetService
 * 
 * 类描述：推广设置列表
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class ExtensionSetService extends BaseService<ExtensionSet> {

	@Autowired
	private ExtensionsetDao extensionSetDao;

	@Override
	protected BaseDao getBaseDao() {
		return extensionSetDao;
	}

	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<ExtensionSet> getExtensionSetList(ExtensionSet extensionSet) {
		Pageable<ExtensionSet> extensionSetList = new Pageable<ExtensionSet>(
				extensionSet);
		// 订单列表内容
		extensionSetList.setContent(extensionSetDao
				.getExtensionSetList(extensionSet));
		// 总条数
		extensionSetList.setTotal(extensionSetDao
				.getExtensionSetCount(extensionSet));
		return extensionSetList;
	}
	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public String isSortExist(ExtensionSet extensionSet) {
		// 查询排序是否重复
		Long isExist = extensionSetDao.getSortExist(extensionSet);
		if (isExist > 0) {
			return "已存在排序";
		}
		return "success";
	}
	
}
