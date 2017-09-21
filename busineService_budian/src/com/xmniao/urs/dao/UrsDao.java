package com.xmniao.urs.dao;

import java.util.Map;
import java.util.Set;

public interface UrsDao {
	/**
	 * 
	 * @Title: updateUrsInfo 
	 * @Description:修改用户所属商家、合作商
	 */
	int updateUrsInfo(Map<String,String> ursMap);
	
	/**
	 * 
	 * @Title: getUrsAscription 
	 * @Description:查询用户所属商家、合作商
	 */
	Map<String,Object> getUrsAscription(Map<String,String> userMap);
	
	/**
	 * 根据uid查询用户信息
	 * @param uid
	 * @return
	 */
	Map<String,Object> getUrsByUid(String uid);
	
	/**
	 * 根据phone查询用户信息
	 * @param uid
	 * @return
	 */
	Map<String,Object> getUrsByPhone(String phone);
	
	/**
	 * 获取用户详细信息
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> getUsrInfo(Map<String,String> paraMap);
	
	/**
	 * 新增寻蜜鸟用户 
	 * @param paraMap
	 * @return
	 */
	Integer addUrs(Map<String,Object> paraMap);
	
	/**
	 * 插入urs_info
	 * @param paraMap
	 * @return
	 */
	Integer insertBursInfo(Map<String,String> paraMap);
	
	/**
	 * 根据uid查询主播id
	 * @param uid
	 * @return
	 */
	Integer getLiveId(Integer uid);
	
	/**
	 * 获取所有主播的UID
	 * @Title: getAnchorUidList 
	 * @Description:
	 */
	Set<Integer> getAnchorUidList(Map<String,Object> paraMap);
	
	Map<String,Object> getUserInfo(String uid);
}
