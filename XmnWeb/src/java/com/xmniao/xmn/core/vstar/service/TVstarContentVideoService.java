package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarContentVideoDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentVideo;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentVideoDao
 * 
 * 类描述： V客学堂视频Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:29:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TVstarContentVideoService extends BaseService<TVstarContentVideo> {

	@Autowired
	private TVstarContentVideoDao tVstarContentVideoDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return tVstarContentVideoDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日上午10:50:05 <br/>
	 * @param asList
	 * @return
	 */
	public List<TVstarContentVideo> getByIds(List<String> asList) {
		return tVstarContentVideoDao.getByIds(asList);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日上午11:21:26 <br/>
	 * @param id
	 */
	public void deleteById(Integer id) {
		tVstarContentVideoDao.deleteByPrimaryKey(id.longValue());
	}
}