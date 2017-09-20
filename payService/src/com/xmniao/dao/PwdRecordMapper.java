package com.xmniao.dao;

import java.util.Map;

/**
 * @author ChenBo
 */
public interface PwdRecordMapper {
	
	/**
	 * 获取对应ID的记录条数
	 * @param map
	 * @return
	 */
	int selectCountRecordByuId(Map<String,Object> map);
	
	/**
	 * 根据用户类型插入修改密码记录
	 * @param paramMap
	 * @return
	 */
	int insertPwdRecordUid(Map<String,String> paramMap);
	int insertPwdRecordSellerid(Map<String,String> paramMap);
	int insertPwdRecordJointid(Map<String,String> paramMap);
	int insertPwdRecordAllianceid(Map<String,String> paramMap);
	
	void insertPwdRecord(Map<String,String> paramMap);
	
	/**
	 * 删除钱包修改记录
	 */
	void delPwdRecord(Map<String,String> paramMap);

}
