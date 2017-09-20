package com.xmniao.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.SellerStatisticsMapper;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.service.SellerStatisticsService;

@Service
public class SellerStatisticsServiceImpl implements SellerStatisticsService {

	 //初始日志类
	private final Logger log = Logger.getLogger(SellerStatisticsServiceImpl.class);
	
	@Autowired
	private SellerStatisticsMapper sellerStatisticsMapper;
	
	@Autowired
	private WalletRecordMapper walletRecordMapper;
	
	@Override
	public List<Map<String,Object>> getSellerIncomeStatistics(
			Map<String, Object> paramMap) {
		log.info("[SellerStatisticsService:getSellerIncomeStatistics]传入参数："+paramMap);
		paramMap.put("table", "s_income_statistics");
		return sellerStatisticsMapper.getStatisticsList(paramMap);
	}

	@Override
	public List<Map<String,Object>> getSellerCommisionStatistics(
			Map<String, Object> paramMap) {
		log.info("[SellerStatisticsService:getSellerCommisionStatistics]传入参数："+paramMap);
		paramMap.put("table", "s_commision_statistics");
		return sellerStatisticsMapper.getStatisticsList(paramMap);
	}

	@Override
	public int countSellerIncomeStatistics(Map<String, Object> paramMap) {
		paramMap.put("table", "s_income_statistics");
		return sellerStatisticsMapper.countRecordStatistics(paramMap);
	}

	@Override
	public int countSellerCommisionStatistics(Map<String, Object> paramMap) {
		paramMap.put("table", "s_commision_statistics");
		return sellerStatisticsMapper.countRecordStatistics(paramMap);
	}

	@Override
	public int countSellerDetail(Map<String, Object> paramMap) {
		
		return walletRecordMapper.getSellerRecordCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> getSellerDetailList(
			Map<String, Object> paramMap) {
		return walletRecordMapper.getSellerRecordList(paramMap);
	}

	@Override
	public Map<String, Object> countSellerIncomeMonthlyStatistics(
			Map<String, Object> paramMap) {
		paramMap.put("table", "s_income_statistics");
		return sellerStatisticsMapper.getMonthlyStatistics(paramMap);
	}

	@Override
	public Map<String, Object> countSellerCommisionMonthlyStatistics(
			Map<String, Object> paramMap) {
		paramMap.put("table", "s_commision_statistics");
		return sellerStatisticsMapper.getMonthlyStatistics(paramMap);
	}

}
