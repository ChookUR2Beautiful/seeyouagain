/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CatehomeActivityDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeActivity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CatehomeActivityService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月16日 下午3:54:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class CatehomeActivityService extends BaseService<CatehomeActivity>{

	@Autowired
	private CatehomeActivityDao catehomeActivityDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return catehomeActivityDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午7:04:18 <br/>
	 * @param id
	 * @param sort
	 */
	public int updateActivitySort(Long id, Integer sort) {
		return catehomeActivityDao.updateActivitySort(id,sort);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午7:07:42 <br/>
	 * @param id
	 */
	public int deleteActivity(Long id) {
		return catehomeActivityDao.deleteActivity(id);
	}

}
