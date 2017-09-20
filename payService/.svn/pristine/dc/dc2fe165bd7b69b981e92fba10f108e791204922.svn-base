package com.xmniao.service;

import java.util.List;
import java.util.Map;

public interface SellerStatisticsService {

	/**
	 * 统计有营收及营收提现的商家总数
	 * @param paramMap
	 * @return
	 */
	int countSellerIncomeStatistics(Map<String,Object> paramMap);
	
	/**
	 * 统计有返利及返利提现的商家总数
	 * @param paramMap
	 * @return
	 */
	int countSellerCommisionStatistics(Map<String,Object> paramMap);
	
	/**
	 * 获取有营收及营收提现的商家 营收及营收提现的记录列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> getSellerIncomeStatistics(Map<String,Object> paramMap);
	
	/**
	 * 获取有返利及返利提现的商家 返利及返利提现的记录列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> getSellerCommisionStatistics(Map<String,Object> paramMap);
	
	/**
	 * 统计商家的营收返利提现的记录总条数
	 * @param paramMap
	 * @return
	 */
	int countSellerDetail(Map<String,Object> paramMap);
	
	/**
	 * 统计有商家营收返利提现列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getSellerDetailList(Map<String,Object> paramMap);
	
	/**
	 * 统计商家的营收返利提现的月统计
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> countSellerIncomeMonthlyStatistics(Map<String,Object> paramMap);
	
	
	/**
	 * 统计商家的返利返利提现的月统计
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> countSellerCommisionMonthlyStatistics(Map<String,Object> paramMap);
}
