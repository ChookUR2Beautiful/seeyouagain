package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ValueCardDao{
	
	/**
	 * 获取会员储值卡
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getValueCard(Map<String,String> paraMap);
	
	/**
	 * 新增储值卡
	 * @param paraMap
	 * @return
	 */
	Integer addValueCard(Map<String,String> paraMap);
	
	/**
	 * 更新储值卡
	 * @param paraMap
	 * @return
	 */
	Integer updateValueCard(Map<String,String> paraMap);
	
	/**
	 * 添加更新储值卡记录
	 * @param paraMap
	 * @return
	 */
	Integer addValueCardRecord(Map<String,String> paraMap);
	
	
	List<Map<String,Object>> getCardList(Map<String,Object> paraMap);
	
	Long countCard(Map<String,Object> pMap);
	
	Map<String,Object> countBalance(String sellerid);
	
	Integer updateCardStatus(@Param("sellerid")String sellerid,@Param("sellertype")String sellertype);
	
	/**
	 * 批量插入会员卡注销记录
	 */
	Integer insertRecord(List<Map<String,Object>> paraList);
	
	/**
	 * 获取储值卡收支记录
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getRecords(Map<String,String> paraMap);
	
	/**
	 * 查询 收支总额，充值会员人数，记录总数
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> countQuotaByDate(Map<String,String> paraMap);
	
	/**
	 * 按天分组，查询每天交易总额
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getRecordGroupByDay(Map<String,String> paraMap);
	
	/**
	 * 查询某天所有所有记录
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getRecordListByDay(Map<String,Object> paraMap);
	
	
	Map<String,Object> getUserRecordInfo(Map<String,String> paraMap);
	
	/**
	 * 统计用户可用充值卡数量
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> countCardNum(Map<String,String> paraMap);
	
	/**
	 * 统计用户在某个商家消费次数
	 * @param sellerid
	 * @param uid
	 * @return
	 */
	Long countConsumeTime(Map<String,String> paraMap);
}
