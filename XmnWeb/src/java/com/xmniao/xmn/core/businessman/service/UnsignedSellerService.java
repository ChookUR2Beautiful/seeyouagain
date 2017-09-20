/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.UnsignedSellerDao;
import com.xmniao.xmn.core.businessman.entity.UnsignedSeller;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UnsignedSellerService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月12日 下午2:28:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class UnsignedSellerService extends BaseService<UnsignedSeller> {

	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return unsignedSellerDao;
	}

	/**
	 * 方法描述：更新商铺状态
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月15日下午4:40:28 <br/>
	 * @param unsignedSellerid
	 * @param type
	 * @return
	 */
	public int updateState(Integer unsignedSellerid, Integer type) {
		return unsignedSellerDao.updateState(unsignedSellerid,type);
	}
	
	

}
