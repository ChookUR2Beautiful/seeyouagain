package com.xmniao.xmn.core.seller.service;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.common.request.RemoveCouponRequest;
import com.xmniao.xmn.core.common.request.UserCouponRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserCouponSevice    
* @Description:用户礼券接口   
* @author: liuzhihao   
* @date: 2016年11月26日 下午2:58:51
 */
public interface UserSellerAndPlatCouponSevice {

	/***
	 * 查询用户的优惠券列表
	 * @param userCouponRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryUserCoupons(UserCouponRequest userCouponRequest);
	
	/**
	 * 删除优惠卷
	 * @param map
	 * @return
	 */
	public int removeCoupon(RemoveCouponRequest removeCouponRequest);

	/**
	 * 
	* @Title: queryRecommendFansCoupon
	* @Description: 查询推荐的粉丝券 1、根据用户定位，按距离显示附近店铺粉丝券   2、根据可使用时间，由近至远排序
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> queryRecommendFansCoupon(UserCouponRequest userCouponRequest);
}
