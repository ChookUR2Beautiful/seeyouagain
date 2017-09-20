package com.xmniao.xmn.core.coupon.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.coupon.entity.TCoupon;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 *
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：CouponDao
 *
 * @类描述： 
 *
 * @创建人：zhang'zhiwen
 *
 * @创建时间 ：2015年5月29日 下午2:06:22
 *
 */
public interface CouponDao extends BaseDao<TCoupon> {
	
	/**
	 * 获取下拉列表数据
	 * @param business
	 * @return
	 */
	@DataSource("slave")
	public List<TCoupon> getSelect(TCoupon coupon);

}
