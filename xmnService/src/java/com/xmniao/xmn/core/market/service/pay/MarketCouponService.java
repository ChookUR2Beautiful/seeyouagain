package com.xmniao.xmn.core.market.service.pay;

import java.util.List;

import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.request.market.pay.CouponListRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketCouponService    
* @Description:积分商城优惠卷   
* @author: liuzhihao   
* @date: 2016年12月22日 下午4:03:28
 */
public interface MarketCouponService {

	/**
	 * 查询用户可以使用的优惠卷
	 * @param baseRequest
	 * @return
	 */
	public Object queryCouponList(CouponListRequest couponListRequest);
	
	/**
	 * 
	* @Title: getUsableCouponsByUid 
	* @Description:查询用户可使用的优惠卷列表
	* @param:
	* @return:void返回类型 
	* @throws
	 */
	public MapResponse getUsableCouponsByUid(String uid,List<String> cartIds,int type);
}
