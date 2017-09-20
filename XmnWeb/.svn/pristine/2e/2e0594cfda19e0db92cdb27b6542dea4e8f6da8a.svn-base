package com.xmniao.xmn.core.business_statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.business_statistics.dao.BusinessActionDao;
import com.xmniao.xmn.core.business_statistics.entity.BusinessAction;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessActionService
 * 
 * 类描述：商家行为统计业务类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BusinessActionService{

	@Autowired private BusinessActionDao businessActionDao;
	
	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	public List<BusinessAction> getList(BusinessAction businessAction){
		return businessActionDao.getList(businessAction);
	}
	
	/**
	 * 汇总
	 * @return
	 */
	public BusinessAction getCensusTotal(BusinessAction businessAction){
		return businessActionDao.getCensusTotal(businessAction);
	}
	
	/**
	 * 数量
	 * @param businessAction
	 * @return
	 */
	public Long count(BusinessAction businessAction){
		return businessActionDao.count(businessAction);
	}
	
	

}
