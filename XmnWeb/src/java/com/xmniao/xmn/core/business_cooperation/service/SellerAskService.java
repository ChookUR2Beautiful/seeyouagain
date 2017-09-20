package com.xmniao.xmn.core.business_cooperation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.SellerAskDao;
import com.xmniao.xmn.core.business_cooperation.entity.TSellerAsk;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAskService
 * 
 * 类描述： 产品问答
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时48分28秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SellerAskService extends BaseService<TSellerAsk> {

	@Autowired
	private SellerAskDao sellerAskDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return sellerAskDao;
	}
	
}
