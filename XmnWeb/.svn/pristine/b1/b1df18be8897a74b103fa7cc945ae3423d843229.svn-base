/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.FullReductionDao;
import com.xmniao.xmn.core.businessman.entity.FullReduction;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：满减活动
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 下午4:08:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class FullReductionService extends BaseService<FullReduction>{
	
	@Autowired
	private FullReductionDao fReductionDao;
	
	@Override
	protected BaseDao<FullReduction> getBaseDao() {
		return fReductionDao;
	}
	
	/**
	 * 
	 * 方法描述：终止进行中的满减活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日下午9:03:01 <br/>
	 * @param fReduction
	 * @return
	 */
	public Integer shutdownFR(FullReduction fReduction){
		return fReductionDao.shutdownFR(fReduction);
	}
}
