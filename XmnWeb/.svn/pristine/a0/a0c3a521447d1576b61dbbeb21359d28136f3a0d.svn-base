package com.xmniao.xmn.core.coupon.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.coupon.entity.TCouponIssueRef;
/**
 * 
 * @author dong'jietao 
 * @date 2015年6月1日 下午3:49:30
 * @TODO 优惠券发放管理
 */
public interface CouponIssueRefDao extends BaseDao<TCouponIssueRef> {

	 public List<TCouponIssueRef> getCouponIssueRefs(TCouponIssueRef tCouponIssueRef);
	 
	 public Long getCouponIssueRefsCount(TCouponIssueRef tCouponIssueRef);

	/**
	 * 删除不包含在ids中的其他的记录
	 * @author zhang'zhiwen
	 * @date 2015年7月29日 上午10:41:19
	 * @param ids
	 */
	public void deleteNotInTheIds(TCouponIssueRef couponIssueRef);
}
