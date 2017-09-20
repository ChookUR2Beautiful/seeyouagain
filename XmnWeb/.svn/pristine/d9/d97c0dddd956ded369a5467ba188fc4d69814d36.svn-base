/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.IndianaBoutDao;
import com.xmniao.xmn.core.fresh.entity.Indiana;
import com.xmniao.xmn.core.fresh.entity.IndianaBout;
import com.xmniao.xmn.core.fresh.util.FreshConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaBout
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月23日 上午11:02:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class IndianaBoutService extends BaseService<IndianaBout>{
	
	@Autowired
	private IndianaBoutDao indianaBoutDao;
	
	
	@Override
	protected BaseDao getBaseDao() {
		return indianaBoutDao;
	}

	/**
	 * 方法描述：根据活动id查找第一个
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日上午10:36:08 <br/>
	 * @param id
	 * @return
	 */
	public IndianaBout getFirstIndianaBout(Integer activityId) {
		return indianaBoutDao.getByActivityId(activityId);
	}

	/**
	 * 方法描述：根据id删除记录
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日下午2:39:32 <br/>
	 * @param id
	 */
	public void remove(Integer id) {
		indianaBoutDao.deleteByPrimaryKey(id);
	}

	

}
