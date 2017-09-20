/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.VstarLiverContentVideoDao;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentVideo;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarLiverContentVideoService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年7月18日 上午11:54:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VstarLiverContentVideoService extends BaseService<VstarLiverContentVideo>{

	@Autowired
	private VstarLiverContentVideoDao vstarLiverContentVideoDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return vstarLiverContentVideoDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月18日下午6:22:37 <br/>
	 * @param asList
	 * @return
	 */
	public List<VstarLiverContentVideo> getByIds(List<String> asList) {
		return vstarLiverContentVideoDao.getByIds(asList);
	}

}
