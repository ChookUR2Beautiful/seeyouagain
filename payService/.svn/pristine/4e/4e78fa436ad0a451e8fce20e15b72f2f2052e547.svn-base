package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UpdateWalletBalanceMapper {
	Map<String, String> selectByuid(Integer uid);
	Map<String, String> selectBysellerid(Integer sellerid);
	Map<String, String> selectByjointid(Integer jointid);
	/*
	 * 根据用户id获取钱包信息(余额)
	 */
	Map<String, String> getWalletByUserId(Map<String, String> paramMap);
	/*
	 * 修改余额类型
	 */
	int updateBalanceType( Map<String, Object> paramMap);
	/*
	 * 插入分账记录
	 */
	int insertWalletRecord(Map<String, Object> paramMap);
	/*
	 * 插入分账记录（新）
	 */
	int insertWR(@Param("list") List<Map<String, Object>> list);
	/**
	 * 查询钱包记录是否重复
	 * 
	 * @param paramMap
	 * @return
	 */
	int getWRCount(Map<String, Object> paramMap);
}
