/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.ActivityProductDao;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：ActivityProductService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月28日 下午8:25:38 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class ActivityProductService extends BaseService<ActivityProduct> {
	
	@Autowired
	private ActivityProductDao activityProductDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return activityProductDao;
	}

	/**
	 * 方法描述：根据活动查询列表
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午1:53:52 <br/>
	 * @param id
	 * @return
	 */
	public List<ActivityProduct> getByActivityId(Integer id) {
		return activityProductDao.getByActivityId(id);
	}

	/**
	 * 方法描述：根据moduleId查找
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日上午9:58:55 <br/>
	 * @param id
	 * @return
	 */
	public List<ActivityProduct> getByModuleId(Long id) {
		return activityProductDao.getByModuleId(id);
	}

	/**
	 * 方法描述：根据coodeId查询活动进行中的商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月7日上午11:14:05 <br/>
	 * @param codeId
	 * @return
	 */
	public List<ActivityProduct> getByCodeId(Long codeId) {
		return activityProductDao.getByBeginCodeId(codeId);
	}

}
