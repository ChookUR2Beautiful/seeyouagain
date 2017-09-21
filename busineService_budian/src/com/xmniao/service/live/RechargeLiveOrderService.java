/**    
 * 文件名：RechargeLiveOrderService.java    
 *    
 * 版本信息：    
 * 日期：2017年2月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：RechargeLiveOrderService
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月23日 下午5:39:55 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface RechargeLiveOrderService<T> {
	
	/**
	 * 
	 * 方法描述：初始化会员关系链
	 * 创建人： ChenBo
	 * 创建时间：2017年2月23日
	 * @param t
	 * @return void
	 */
	void initUidRelation(T t);
	
	/**
	 * 
	 * 方法描述：初始化订单分账
	 * 创建人： ChenBo
	 * 创建时间：2017年2月23日
	 * @param t
	 * @return T
	 */
	T initRechargeOrderLedger(T t);
	
	/**
	 * 
	 * 方法描述：订单给上级推荐分账
	 * 创建人： ChenBo
	 * 创建时间：2017年2月23日
	 * @param t void
	 */
	void recommendOrderLedger(T t);
	
	/**
	 * 
	 * 方法描述：可对上级产生推荐奖励的充值订单的最小额
	 * 创建人： ChenBo
	 * 创建时间：2017年2月27日
	 * @return BigDecimal
	 */
	public BigDecimal getMinRecommendLedgerPayment();
}
