/**
 * 
 */
package com.xmniao.dao.live;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LivePayOrder;

/**
 * 项目名称：busineService
 * 
 * 类名称：LiveOrderMapper
 *
 * 创建人： chenJie
 * 
 * 创建时间：2016年8月22日下午2:13:42
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiveOrderDao {
	
	/**
	 * 
	 * 方法描述：更新鸟币充值订单状态
	 * 创建人： chenJie
	 * 创建时间：2016年8月22日下午2:14:57
	 * @param map
	 * @return
	 */
	int updateLiveOrder(LivePayOrder liveOrder);
	
	/**
	 * 
	 * 方法描述：根据订单编号查询订单信息
	 * 创建人： chenJie
	 * 创建时间：2016年8月22日下午2:23:46
	 * @param bid
	 * @return
	 */
	LivePayOrder getLiveOrder(String bid);
	
	/**
	 * 
	 * 方法描述：统计直接下级的充值总额
	 * 创建人： ChenBo
	 * 创建时间：2017年3月6日
	 * @param id
	 * @return BigDecimal
	 */
	BigDecimal countSubordinateOrderAmount(@Param("uid") Integer uid);
	
	/**
	 * 
	 * 方法描述：获取商家充值平台订单中正在分账金额最大的那个
	 * 创建人： ChenBo
	 * 创建时间：2017年4月7日
	 * @param paraMap
	 * @return LivePayOrder
	 */
	LivePayOrder getHighestSellerLedgerOrder(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 方法描述：统计V客的直属业绩
	 * 创建人： ChenBo
	 * 创建时间：2017年4月20日
	 * @param uid
	 * @return Map<String,Object>
	 */
	Map<String,Object> getVerDirectPerformance(Map<String,String> paramMap);
	
	/**
	 * 
	 * 方法描述：统计V客的间接业绩
	 * 创建人： ChenBo
	 * 创建时间：2017年4月20日
	 * @param uidStr
	 * @return Map<String,Object>
	 */
	Map<String,Object> getVerIndirectPerformance(Map<String,String> paramMap);
}
