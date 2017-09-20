/**
 * 
 */
package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.SellerCoupon;
import com.xmniao.xmn.core.businessman.entity.UserCoupon;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerCouponDao
 * 
 * 类描述： 商家优惠券
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 下午4:07:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface SellerCouponDao extends BaseDao<SellerCoupon>{
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:33:54 <br/>
	 * @param sellerCoupon
	 * @return
	 */
	Integer shutdown(SellerCoupon sellerCoupon);
	
	/**
	 * 
	 * 方法描述：停用用户优惠券
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:33:54 <br/>
	 * @param sellerCoupon
	 * @return
	 */
	Integer shutdownUC(UserCoupon uCoupon);
	
	/**
	 * 
	 * 方法描述：用户领取优惠券列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:24:32 <br/>
	 * @param ucCoupon
	 * @return
	 */
	List<UserCoupon> getUserCouponDetail(UserCoupon uCoupon);
	
	/**
	 * 
	 * 方法描述：用户领取优惠券总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:24:32 <br/>
	 * @param ucCoupon
	 * @return
	 */
	Long countUserCoupon(UserCoupon uCoupon);
}
