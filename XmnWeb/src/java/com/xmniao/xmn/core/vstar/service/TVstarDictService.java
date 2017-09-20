package com.xmniao.xmn.core.vstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarDictDao;
import com.xmniao.xmn.core.vstar.entity.TVstarDict;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarDictDao
 * 
 * 类描述： V客学堂教学分类DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:30:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TVstarDictService extends BaseService<TVstarDict> {

	@Autowired
	private TVstarDictDao tVstarDictDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return tVstarDictDao;
	}
}