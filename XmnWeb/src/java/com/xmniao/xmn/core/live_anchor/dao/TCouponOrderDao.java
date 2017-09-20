package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TCouponOrder;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCouponOrderDao
 * 
 * 类描述： 直播券订单Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-3 下午4:23:18 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TCouponOrderDao extends BaseDao<TCouponOrder>{
	
	/**
	 * 
	 * 方法描述：根据订单编号查询粉丝券发放记录详细信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-21下午2:26:09 <br/>
	 * @param orderNos
	 * @return
	 */
	@DataSource(value="slave")
	public List<TCouponOrder> getDetailInfoListByOrderNo(Object[] orderNos);
	
	/**
	 * 
	 * 方法描述：获取粉丝券统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1上午11:44:59 <br/>
	 * @param couponOrder
	 * @return
	 */
	@DataSource(value="slave")
	public Map<String,Object> getTitleInfo(TCouponOrder couponOrder);
	
	/**
	 * 
	 * 方法描述：获取指定订单ID的粉丝券使用状态，使用时间，分账状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1上午11:44:59 <br/>
	 * @param couponOrder
	 * @return
	 */
	@DataSource(value="slave")
	public TCouponOrder getDetailInfoById(TCouponOrder couponOrder);
	
}