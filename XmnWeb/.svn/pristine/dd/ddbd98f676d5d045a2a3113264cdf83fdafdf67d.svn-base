/**
 * 
 */
package com.xmniao.xmn.core.billmanagerment.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.billmanagerment.entity.PackageOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PackageOrderDao
 * 
 * 类描述： 套餐订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月24日 上午10:12:26 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface PackageOrderDao extends BaseDao<PackageOrder>{
	
	/**
	 * 
	 * 方法描述：根据订单号获取发放的套餐优惠券
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月24日下午5:58:10 <br/>
	 * @param pOrder
	 * @return
	 */
	List<Map<String,Object>> getPackageList(PackageOrder pOrder);
	
	/**
	 * 
	 * 方法描述：统计订单总金额，实际支付金额
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月25日下午2:56:16 <br/>
	 * @param pOrder
	 * @return
	 */
	Map<String,Object> titleInfo(PackageOrder pOrder);
	
	/**
	 * 
	 * 方法描述：更新套餐订单状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月24日上午10:03:56 <br/>
	 * @param orderNo
	 * @return
	 */
	Integer updateOrderStatus(Map<String,String> parMap);
	
	/**
	 * 
	 * 方法描述：更新套餐优惠券为注销
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月24日上午10:03:56 <br/>
	 * @param orderNo
	 * @return
	 */
	Integer updateGrantStatus(Map<String,String> parMap);
	
	/**
	 * 
	 * 方法描述：更新套餐抵用券为注销
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月24日上午10:03:56 <br/>
	 * @param orderNo
	 * @return
	 */
	Integer updateCouponStatus(Map<String,String> parMap);
	
	/**
	 * 
	 * 方法描述：统计套餐抵用券
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月24日上午10:54:10 <br/>
	 * @param parMap
	 * @return
	 */
	Long countCouponNum(Map<String,String> parMap);
}
