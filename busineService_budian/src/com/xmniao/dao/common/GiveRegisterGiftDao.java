package com.xmniao.dao.common;

import java.util.List;
import java.util.Map;

public interface GiveRegisterGiftDao {
	
	/**
	 * 获取注册礼包内容
	 * @return
	 */
	List<Map<String,Object>> getRegisterGift(); 
	
	/**
	 * 查询优惠券信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getCoupon(String id);
	
	/**
	 * 添加优惠券记录
	 * @param paraMap
	 * @return
	 */
	int addCouponDetail(Map<String,String> paraMap);
}
