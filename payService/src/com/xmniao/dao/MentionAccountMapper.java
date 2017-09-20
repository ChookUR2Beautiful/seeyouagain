package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MentionAccountMapper {
	// 添加银行卡信息
	int insertSelective(Map<String, Object> map);

	// 获取银行卡信息
	List<Map<String, Object>> selectByuid(Map<String, Object> map);

	// 获取银行卡信息
	List<Map<String, Object>> selectByUids(List<Map<String, String>> list);

	// 修改更新银行卡信息
	int updateSelective(Map<String, Object> map);

	/**
	 * 根据ID查询提现方式
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> selectMentionById(@Param("id") String id);

	/*
	 * 根据用户id查询银行卡帐号是否存在
	 */
	List<Map<String, Object>> checkByUid(Map<String, Object> map);

	// 查询当天已提现金额
	double countTodayMoney(Map<String, Object> map);
	
	/**
	 * 统计提现总额（含手续费）
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> countWithdraw(Map<String,String> paraMap);
	/*
	 * 银行卡列表查询接口
	 */
	List<Map<String, Object>> getAccountList(Map<String, Object> map);

	/*
	 * 银行卡查询统计
	 */
	int getCountPage(Map<String, Object> map);
	
	//根据id查询对应MentionAccount
	Map<String,Object> getMentionAccountById(int id);
	
	//
	Map<String,Object> getMentionAccount(Map<String,String> map);
	
	//删除银行卡
	int delMentionAccount(String id);

}