package com.xmniao.dao;

import java.util.List;
import java.util.Map;

public interface XmerWallerMapper {
	
	/**
	 * 插入寻蜜客钱包
	 * @param map
	 * @return
	 */
	int addXmerWallet(Map<String, String> map);
	
	/**
	 * 根据用户id查询钱包id
	 * @param uid
	 * @return 钱包id
	 */
	Map<String,Object> getWalletByuid(String uid);
	
	/**
	 * 根据寻蜜客用户id，查询该用户寻蜜客钱包
	 * @param uid
	 * @return
	 */
	Map<String,String> selectXmerWallet(Map<String,String> map);
	
	/**
	 * 根据寻蜜客钱包id，查询该用户寻蜜客钱包
	 * @param uid
	 * @return
	 */
	Map<String,Object> selectXmerWalletById(String id);
	
	/**
	 * 根据id或uid修改寻蜜客钱包状态
	 * @param walletMap
	 * @return
	 */
	
	Integer updateWalletstate(Map<String,String> walletMap);
	
	/**
	 * 根据uid修改寻蜜客钱包状态
	 * @param walletMap
	 * @return
	 */
//	Integer updateWalletstateByUid(Map<String,String> walletMap);
	
	/**
	 * 更新钱包数据
	 * @param walletmMap
	 * @return
	 */
	Integer updateWalletById(Map<String,String> walletmMap);

	/**
	 * 插入钱包更新记录
	 * @param walletmMap
	 * @return
	 */
	Integer insertXmerWalletRecord(Map<String,String> walletmMap);
	
	/**
	 * 获取钱包记录总条数
	 * @Title: countWalletRecord 
	 * @Description:
	 */
	int countWalletRecord(Map<String,String> recordMap);
	
	
	/**
	 * 根据订单号查询记录
	 * @param remarks
	 * @return
	 */
	List<Map<String,Object>> getRecordsByRemarks(Map<String,Object> map);
}
