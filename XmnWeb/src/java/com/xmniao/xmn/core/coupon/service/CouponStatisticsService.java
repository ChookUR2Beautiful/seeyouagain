package com.xmniao.xmn.core.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.coupon.dao.CouponStatisticsDao;
import com.xmniao.xmn.core.coupon.entity.TCouponCity;
import com.xmniao.xmn.core.coupon.entity.TCouponStatistics;

/**
 * 
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：CouponstatisticsController
 * 
 * @类描述： 优惠券统计
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间 ：2015年06月01日 下午14:00:44
 * 
 */
@Service
public class CouponStatisticsService extends BaseService<TCouponStatistics> {
	@Autowired
	private CouponStatisticsDao couponStatisticsDao;

	@Override
	protected BaseDao<TCouponStatistics> getBaseDao() {
		return couponStatisticsDao;
	}
	
	/**
	 * 活动 获取未使用列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<TCouponStatistics> getUnUseOrUsed(TCouponStatistics couponStatistics) {
		Pageable<TCouponStatistics> list = new Pageable<TCouponStatistics>(couponStatistics);
		// 未使用列表内容
		list.setContent(couponStatisticsDao.getUnUseList(couponStatistics));
		// 总条数
		list.setTotal(couponStatisticsDao.getUnUseListCount(couponStatistics));
		return list;
	}
	
	/**
	 * 优惠券  获取未使用列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<TCouponStatistics> getCouponUnUseOrUsed(TCouponStatistics couponStatistics) {
		Pageable<TCouponStatistics> list = new Pageable<TCouponStatistics>(couponStatistics);
		// 未使用列表内容
		list.setContent(couponStatisticsDao.getCouponUnUseOrUsedList(couponStatistics));
		// 总条数
		list.setTotal(couponStatisticsDao.getCouponUnUseOrUsedCount(couponStatistics));
		return list;
	}
	
	/**
	 * 获取按优惠券统计列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<TCouponStatistics> getCouponInitList(TCouponStatistics couponStatistics) {
		Pageable<TCouponStatistics> list = new Pageable<TCouponStatistics>(couponStatistics);
		// 未使用列表内容
		list.setContent(couponStatisticsDao.getCouponList(couponStatistics));
		// 总条数
		list.setTotal(couponStatisticsDao.getCouponCount(couponStatistics));
		return list;
	}
	
	/**
	 * 根据cid区域信息
	 * @param cid
	 * @return
	 */
	public Pageable<TCouponStatistics> getViewAreaByCid(TCouponStatistics couponStatistics) {
		Pageable<TCouponStatistics> list = new Pageable<TCouponStatistics>(couponStatistics);
		// 未使用列表内容
		list.setContent(couponStatisticsDao.getViewAreaList(couponStatistics));
		// 总条数
		list.setTotal(couponStatisticsDao.getViewAreaCount(couponStatistics));
		return list;
	}
}
