package com.xmniao.xmn.core.coupon.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.coupon.entity.TCouponIssue;

/**
 * 
 * @author dong'jietao
 * @date 2015年6月1日 下午3:49:30
 * @TODO 优惠券发放管理
 */
public interface CouponIssueDao extends BaseDao<TCouponIssue> {

	/**
	 * 获取短信发送优惠券列表信息
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年6月10日 上午11:32:31
	 * @param tCouponIssue
	 * @return
	 */
	List<TCouponIssue> getListOfSendShortMessage(TCouponIssue tCouponIssue);

	/**
	 * @author zhang'zhiwen
	 * @date 2015年6月10日 上午11:33:01
	 * @param tCouponIssue
	 * @return
	 */
	Long countOfGetListOfSendShortMessage(TCouponIssue tCouponIssue);

	/**
	 * 取得刮刮卡优惠券列表
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月23日 上午10:11:38
	 * @param tCouponIssue
	 * @return
	 */
	List<TCouponIssue> getGuaCouponList(TCouponIssue tCouponIssue);

	/**
	 * @author zhang'zhiwen
	 * @date 2015年8月24日 下午6:28:28
	 * @TODO 
	 * @param longValue
	 * @return
	 */
	TCouponIssue getObjectAndJoinCid(long longValue);
	/**
	 * 根据cid查询ctype
	 * @Title:selectCtype
	 * @Description:TODO
	 * @param tCouponIssue
	 * @return Integer
	 * @throw
	 */
	Integer selectCtype(Integer cid);

}
