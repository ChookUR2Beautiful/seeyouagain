package com.xmniao.xmn.core.verification.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.verification.entity.UrsInfo;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：UrsInfoDao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-20上午11:49:55
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Repository
public interface UrsInfoDao {

	/**
	 * 插入记录
	 * @param record
	 * @return
	 */
	@DataSource("burs") 
    int insertSelective(UrsInfo record);
    
	/**
	 * 更新记录
	 * @param record
	 * @return
	 */
	@DataSource("burs") 
    int updateByPrimaryKey(UrsInfo record);
	
	/**
	* @Title: queryUrsInfoByUid
	* @Description: 根据用户ID查询用户详细信息
	* @param uid 用户ID
	* @return UrsInfo
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs") 
	UrsInfo queryUrsInfoByUid(Integer uid);
	/**
	 * 
	* @Title: queryUrsByuid
	* @Description: 查询用户昵称
	* @return String    返回类型
	* @throws
	 */
	@DataSource("burs")
	String queryUrsByUid(Integer uid);

	/**
	 * @Title: 根据用户的uid查询用户的头像
	 * @Description: 
	 * @param uidTo
	 * @return
	 */
	@DataSource("burs") 
	String queryUrsAvatarByUid(Integer uid);
	
	/**
	 * 根据用户id查询用户信息
	 * @param list
	 * @return
	 */
	@DataSource("burs") 
	List<Map<Object,Object>> queryUserInfoByUidList(Map<Object,Object> map);
	
	/**
	 * 通过用户ID查询用户详情集合
	 * @param list
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object,Object>> findFollowNameByUid(@Param("uids")List<Integer> list);
}