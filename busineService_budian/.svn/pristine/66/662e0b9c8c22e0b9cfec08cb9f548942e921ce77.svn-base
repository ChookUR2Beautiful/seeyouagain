package com.xmniao.dao.sellerOrder;

import java.util.List;
import java.util.Map;

public interface ActivityKillOrderDao {
	
	/**
	 * 获取秒杀记录信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getActivityRecord(String id);
	
	/**
	 * 获取秒杀信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getActivity(String id);
	
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	Integer updateActivityRecordStatus(Map<String,String> paramMap);
	
	/**
	 * 查询活动关联的奖品信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> getActivityRelation(String id);
	
	/**
	 * 用户发放奖品
	 * @param paraMap
	 * @return
	 */
	Integer insertUserCoupon(Map<String,Object> paraMap);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Map<String,Object> getSellerCoupon(String id);
	
	/**
	 * 获取红包信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getRedPackage(String id);
	
	
	Map<String,Object> getActivityRelaction(String id);
}
