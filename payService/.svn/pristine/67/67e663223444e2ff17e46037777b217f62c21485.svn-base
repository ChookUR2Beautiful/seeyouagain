package com.xmniao.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GetMentionLedgerMapper {
	/*
	 * 通过传递用户id，获取信息
	 */
	 Map<String,Object> getMention(@Param("uId") String uId);
	 Map<String,Object> getLedger(@Param("uId") String uId);
	 
	 Map<String,Object> selectByAccountid(@Param("uId") String uId);
	 
	 int insertByAccountid(Map<String, Object> paramMap);

}
