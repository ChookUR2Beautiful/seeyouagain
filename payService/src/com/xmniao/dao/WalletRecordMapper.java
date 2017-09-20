package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.entity.WalletRecord;


public interface WalletRecordMapper {
	/*
	 * 获取钱包id
	 */
	Integer getAccountid(Map<String, Object> map);
	
	/*
	 * 通过钱包id查询钱包修改记录
	 */
	List<Map<String, Object>> getWalletRecord(Map<String, Object> map);

	/*
	 * 通过钱包id查询记录数
	 */
	Integer getListCount(Map<String, Object> map);
	
	/*
	 * 通过钱包id查询钱包记录总金额
	 */
	double getWalletAmount(Map<String, Object> map);
	
	/*
	 * 总钱包修改记录数
	 * @return
	 */
	int getWalletRecordCount(Map<String,String> map);
	
	//插入钱包修改
	int addWalletRecord(Map walletInfoMap);

	//查询修改记录
	int getRecordCount(Map<String,Object> map);
	
	int getRecordCountMap(Map<String,Object> map);
	/*
	 * 通过钱包id查询钱包修改记录
	 */
	List<Map<String, String>> getWalletRecordStr(Map<String, Object> map);
	
	/**
	 * 删除钱包修改记录
	 */
	int delWalletRecord(Map<String,Object> delMap);
	
	int getSellerRecordCount(Map<String,Object> map);
	
	List<Map<String,Object>> getSellerRecordList(Map<String,Object> map);
	
	/*
	 * 获取钱包修改记录
	 */
	WalletRecord getWalletRecordData(WalletRecord walletRecord);
	
	/**
	 * 获取钱包修改记录
	 * @Title: getWalletRecordList 
	 * @Description:
	 */
	List<WalletRecord> getWalletRecordList(Map<String,Object> map);
}
