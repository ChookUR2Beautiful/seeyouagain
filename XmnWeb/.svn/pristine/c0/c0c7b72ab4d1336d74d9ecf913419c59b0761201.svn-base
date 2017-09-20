/**
 * 
 */
package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ActivityOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ActivityOrderDao
 * 
 * 类描述：活动订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年3月1日 上午11:17:26 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface ActivityOrderDao extends BaseDao<ActivityOrder>{
	
	ActivityOrder getActivityOrder(ActivityOrder aOrder);

	/**
	 * 方法描述：获取夺宝中奖订单
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月6日下午5:57:24 <br/>
	 * @param id
	 * @return
	 */
	ActivityOrder getWinnerOrder(Integer id);
	
	/**
	 * 
	 * 方法描述：获取物流公司
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月13日上午9:45:45 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getExpress();
}
