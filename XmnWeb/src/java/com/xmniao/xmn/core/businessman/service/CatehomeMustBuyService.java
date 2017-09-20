/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CatehomeMustBuyDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeMustBuy;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CatehomeMustBuyService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月18日 下午4:05:49 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class CatehomeMustBuyService extends BaseService<CatehomeMustBuy>{
	
	@Autowired
	private CatehomeMustBuyDao catehomeMustBuyDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return catehomeMustBuyDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:28:51 <br/>
	 * @param id
	 * @param sort
	 */
	public int updateMustbuySort(Integer id, Integer sort) {
		return catehomeMustBuyDao.updateMustbuySort(id,sort);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:32:28 <br/>
	 * @param id
	 */
	public int deleteMustbuy(Integer id) {
		return catehomeMustBuyDao.deleteMustbuy(id);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:37:18 <br/>
	 * @param type
	 * @return
	 */
	public Long countByType(Integer type) {
		return catehomeMustBuyDao.countByType(type);
	}
	
	

}
