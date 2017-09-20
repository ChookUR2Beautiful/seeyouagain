/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.ActivityOrderDao;
import com.xmniao.xmn.core.fresh.entity.ActivityOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ActivityOrderService
 * 
 * 类描述： 活动订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年3月1日 上午11:17:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class ActivityOrderService extends BaseService<ActivityOrder>{
	
	@Autowired
	private ActivityOrderDao aOrderDao;
	
	@Override
	protected BaseDao<ActivityOrder> getBaseDao() {
		return aOrderDao;
	}
	
	public ActivityOrder getActivityOrder(ActivityOrder aOrder){
		return aOrderDao.getActivityOrder(aOrder);
	}

	/**
	 * 方法描述：获取夺宝中奖订单
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月6日下午5:56:30 <br/>
	 * @param id
	 * @return
	 */
	public ActivityOrder getWinnerOrder(Integer id) {
		return aOrderDao.getWinnerOrder(id);
	}
	
	/**
	 * 
	 * 方法描述：获取物流公司列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月13日上午9:48:08 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getExpress(){
		return aOrderDao.getExpress();
	}
}
