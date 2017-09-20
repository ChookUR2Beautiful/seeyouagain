package com.xmniao.xmn.core.coupon.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.coupon.entity.TCouponSeller;

/**
 * 
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：CouponSellerDao
 * 
 * @类描述：
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年5月29日 下午5:37:02
 * 
 */
public interface CouponSellerDao extends BaseDao<TCouponSeller> {

	/**
	 * 取得优惠券的所使用的商家
	 * 
	 * @param cid
	 * @return
	 */
	List<TCouponSeller> getListByCid(Long cid);

	/**
	 * @param cid
	 * @param sellerids
	 */
	List<TCouponSeller> getListByCidAndSelleridsAndJoinSellerName(
			TCouponSeller couponSeller);

	List<TCouponSeller> getListByCidAndJoinSellerName(TCouponSeller couponSeller);

	/**
	 * 删除已经不和优惠券关联的商家
	 * 
	 * @param couponSeller
	 */
	void deleteByCidAndSellerids(TCouponSeller couponSeller);

	/**
	 * 删除优惠券的所有商家
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年6月25日 上午9:28:38
	 * @param cid
	 *            优惠券id
	 */
	void deleteAllByCid(Long cid);

	/**
	 * 删除所有包含的商家
	 */
	void deleteAllIncludeSellerByCid(Long cid);

	/**
	 * 删除所有排除的商家
	 */
	void deleteAllExcludeSellerByCid(Long cid);

	/**
	 * 删除所有包含的行业
	 * 
	 * @param cid
	 */
	void deleteAllIncludeTradeByCid(Long cid);

	/**
	 * 删除所有排除的行业
	 * 
	 * @param cid
	 */
	void deleteAllExcludeTradeByCid(Long cid);

}
