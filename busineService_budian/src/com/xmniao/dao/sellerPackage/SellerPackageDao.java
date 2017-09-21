package com.xmniao.dao.sellerPackage;

import java.util.List;
import java.util.Map;

public interface SellerPackageDao {
	
	
	/**
	 * 获取套餐订单信息
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> getPackageOrder(Map<String,String> paraMap);
	
	/**
	 * 更新订单
	 * @param paraMap
	 * @return
	 */
	Integer updatePackageOrder(Map<String,Object> paraMap);
	
	/**
	 * 获取套餐关联抵用券
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getPackageCoupon(String pid);
	
	/**
	 * 获取套餐信息
	 * @param pid
	 * @return
	 */
	Map<String,Object> getPackageIssue(String pid);
	
	/**
	 * 插入套餐优惠券
	 */
	Integer insertCoupon(List<Map<String,Object>> list);
	
	/**
	 * 还原优惠券库存
	 * @param num
	 * @return
	 */
	Integer updateStock(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 方法描述：获取套餐券使用信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param map
	 * @return Map<String,String>
	 */
	Map<String,Object> getSellerPackageGrantByConsume(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：获取消费使用套餐券的分账配置信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月8日
	 * @param bid
	 * @return Map<String,Object>
	 */
	Map<String,Object> getUserPackageInfo(Long bid);
	
	/**
	 * 
	 * 方法描述：更新套餐券为已使用
	 * 创建人： ChenBo
	 * 创建时间：2017年3月9日
	 * @param uMap
	 * @return int
	 */
	int useSellerPackageGrant(Map<String,Object> uMap);
	
	/**
	 * 更改套餐状态
	 * @param paraMap
	 * @return
	 */
	int updateSellerPackageState(Map<String, Object> paraMap);
}
