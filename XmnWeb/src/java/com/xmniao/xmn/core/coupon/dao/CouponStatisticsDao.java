package com.xmniao.xmn.core.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.coupon.entity.TCouponCity;
import com.xmniao.xmn.core.coupon.entity.TCouponStatistics;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 *
 * @项目名称：XmnWeb
 * 
 * @类名称：CouponStatisticsDao
 *
 * @类描述： 
 *
 * @创建人：cao'yingde
 *
 * @创建时间 ：2015年6月02日 下午10:06:22
 *
 */
public interface CouponStatisticsDao extends BaseDao<TCouponStatistics> {
	/*
	 * 按活动  获取未使用
	 */
	@DataSource("slave")
	public List<TCouponStatistics> getUnUseList(TCouponStatistics couponStatistics);
		
	@DataSource("slave")
	public Long getUnUseListCount(TCouponStatistics couponStatistics);
	
	
	/*
	 * 按优惠券 获取未使用
	 */
	@DataSource("slave")
	public List<TCouponStatistics> getCouponUnUseOrUsedList(TCouponStatistics couponStatistics);
		
	@DataSource("slave")
	public Long getCouponUnUseOrUsedCount(TCouponStatistics couponStatistics);
	
	
	
	/*
	 * 获取已使用
	 */
	@DataSource("slave")
	public List<TCouponStatistics> getUsedInitList(TCouponStatistics couponStatistics);
		
	@DataSource("slave")
	public Long getUsedInitListCount(TCouponStatistics couponStatistics);
	
	/*
	 * 按优惠券统计
	 */
	@DataSource("slave")
	public List<TCouponStatistics> getCouponList(TCouponStatistics couponStatistics);
		
	@DataSource("slave")
	public Long getCouponCount(TCouponStatistics couponStatistics);
	
	
	/*
	 * 查看区域
	 */
	@DataSource("slave")
	public List<TCouponStatistics> getViewAreaList(TCouponStatistics couponStatistics);
		
	@DataSource("slave")
	public Long getViewAreaCount(TCouponStatistics couponStatistics);
}
