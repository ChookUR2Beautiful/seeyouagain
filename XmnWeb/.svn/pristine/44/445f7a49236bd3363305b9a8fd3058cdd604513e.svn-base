/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.ActivityGroupDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：ActivityGroupService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月28日 下午8:24:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class ActivityGroupService extends BaseService<ActivityGroup> {

	@Autowired
	private ActivityGroupDao activityGroupDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return activityGroupDao;
	}

	/**
	 * 方法描述：根据编号和活动id查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午1:58:24 <br/>
	 * @param codeId
	 * @param id
	 * @return
	 */
	public List<ActivityGroup> getByCodeId(Long codeId, Integer id) {
		return activityGroupDao.getByCodeId(codeId,id);
	}

}
