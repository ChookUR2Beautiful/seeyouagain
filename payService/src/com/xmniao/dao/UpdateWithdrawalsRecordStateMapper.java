package com.xmniao.dao;

import java.util.List;
import java.util.Map;

public interface UpdateWithdrawalsRecordStateMapper {
	/*
	 * 更新提现状态
	 */
	int updateWithdrawalsRecordState(Map<String,Object> paramMap);
	
	int updateWithdrawalsRecordStateByjointid(Map<String,Object> paramMap);
	/**
	 * 新增提现记录
	 * 
	 * @param paramMap
	 * @return
	 */
	int insertWRRecord(Map<String, Object> paramMap);
	
	/*
	 * 通过汇付天下方式代发款项
	 */
	Map<String, Object> selectByflowid(Map<String, Object> statusMap);
	
	Map<String, Object> selectByjointid(Map<String, Object> paramMap);
	
	//电子钱包(账户)提现记录表 按条件查询相关数据条数 
	Integer selectByflowidCounts(Map<String, Object> paramMap);
	
	//合作商提现记录表 按条件查询相关数据条数 
	Integer selectByjointidCounts(Map<String, Object> paramMap);
	//电子钱包(账户)提现记录表 按条件查询相关数据 
	List<Map<String, Object>> selectByflowids(Map<String, Object> paramMap);
	
	//合作商提现记录表 按条件查询相关数据 
	List<Map<String, Object>> selectByjointids(Map<String, Object> paramMap);
	
	/**
	 * 新增合作商提现记录
	 * 
	 * @param paramMap
	 * @return
	 */
	int insertJWRRecord(Map<String, Object> paramMap);
	
    //查询当天已提现金额
    double countTodayMoney(String id);
    
    
    int updateWithdrawalsRecord2Lock(int orderNumber);
    
    int updateWithdrawalsRecordJointid2Lock(int orderNumber);
    
    
    
}
