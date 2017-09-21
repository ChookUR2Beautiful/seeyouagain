package com.xmniao.dao.sellerAddress;

import java.util.List;
import java.util.Map;

public interface SellerAddressDao {
	
	/**
	 * 添加商家地址
	 * @param paraMap
	 * @return
	 */
	Integer add(Map<String,String> paraMap);
	
	/**
	 * 删除商家地址
	 * @param paraMap
	 * @return
	 */
	Integer delete(Map<String,String> paraMap);
	
	/**
	 * 更新商家地址
	 * @param paraMap
	 * @return
	 */
	Integer update(Map<String,String> paraMap);
	
	/**
	 * 获取商家地址
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getList(Map<String,String> paraMap);
	
	/**
	 * 根据区域id,获取区域名称
	 * @param areaId
	 * @return
	 */
	Map<String,Object> getArea(String areaId);
	
	/**
	 * 根据区域id获取所有下级区域信息
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> areaList(Map<String,String> paraMap);
	
	/**
	 * 根据区域名称获取区域信息
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> getAreaByTitle(Map<String,String> paraMap);
}
