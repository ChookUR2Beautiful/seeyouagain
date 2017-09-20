package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户、商户、合作商 钱包余额记录表操作DAO
 * @author ChenBo
 *
 */
public interface WBRecordMapper {
	/*
	 * 创建用户表
	 */
	void createUserTableByname(Map<String,String> map);
	
	/*
	 * 创建商户表
	 */
	void createSellerTableByname(Map<String,String> map);
	
	/*
	 * 创建合作商表
	 */
	void createJonitTableByname(Map<String,String> map);
	
	/*
	 * 创建表,sql = create table abc (id int(12) primary key auto_increment,name varchar(50),....)
	 */
	void createTable(Map<String,String> map);
		
	/*
	 * 获取table表全部数据
	 */
	List<Map<String,Object>> getAllData(Map<String,Object> paramMap);
	
	/**
	 * 查询合作商营收和提现
	 * 
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> getJointIncome(Map<String, String> paramMap);
	
	/*
	 * 批量插入用户表数据
	 */
	int insertUserListData(@Param("list") List<Map<String,Object>> list,@Param("table") String table);

	/*
	 * 批量插入商户表数据
	 */
	int insertSellerListData(@Param("list") List<Map<String,Object>> list,@Param("table") String table);
	
	/*
	 * 批量插入合作商表数据
	 */
	int insertJointListData(@Param("list") List<Map<String,Object>> list,@Param("table") String table);
	
	/*
	 * 查询符合指定日期符合查询条件的钱包总数
	 */
	int getPastWalletCount(@Param("map") Map<String,Object> map,@Param("table") Map<String,String> tableName);
	
	/*
	 * 查询符合指定日期符合查询条件的钱包列表
	 */
	List<Map<String,String>> getPastWalletList(@Param("map") Map<String,Object> map,@Param("table") Map<String,String> tableName);

	
	/*
	 * 查询符合指定日期段符合查询条件的钱包总数
	 */
	int getPastDateWalletCount(@Param("map") Map<String,Object> map,@Param("list") List<String> dateMonth);

	
	/*
	 * 查询符合指定日期段符合查询条件的钱包列表
	 */
	List<Map<String,String>> getPastDateWalletList(@Param("map") Map<String,Object> map,@Param("list") List<String> dateMonth);

}
