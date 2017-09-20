package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.OrderinvoiceDao;
import com.xmniao.xmn.core.businessman.entity.TOrderinvoice;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：OrderinvoiceService
 * 
 * 类描述： 商家申请发票
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日18时23分50秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class OrderinvoiceService extends BaseService<TOrderinvoice> {

	@Autowired
	private OrderinvoiceDao orderinvoiceDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return orderinvoiceDao;
	}
	
}
