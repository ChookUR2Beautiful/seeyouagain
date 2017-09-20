package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SellerStatisticsMapper {
	
	/**
	 * 批量插入每天的商家营收/分账及对应的提现统计记录
	 * @param list 插入数据列表
	 * @param table 表名(s_income_statistics 或  s_commision_statistics)
	 * @return
	 */
	int insertSellerStatistics(@Param("list") List<Map<String,Object>> list,@Param("table") String table);
	
	/**
	 * 统计商家营收返利统计记录总和
	 * @param param
	 * @return
	 */
	int countRecordStatistics(Map<String,Object> param);
	
	/**
	 * 获取商家营收返利统计记录列表
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getStatisticsList(Map<String,Object> param);
	
	/**
	 * 获取商家营收返利提现的月统计
	 * @param param
	 * @return
	 */
	Map<String,Object> getMonthlyStatistics(Map<String,Object> param);
}
