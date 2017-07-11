package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.Kill;


public interface KillDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Kill record);

    int insertSelective(Kill record);

    Kill selectByPrimaryKey(@Param("id")Integer id,@Param("sellerId") Integer sellerId);
    
    Kill findByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Kill record);

    int updateByPrimaryKey(Kill record);

	/**
	 * 
	 * 方法描述：获取活动进行中列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午4:07:42   
	 * @param sellerId
	 * @return
	 */
	List<Kill> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动已结束列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午4:12:41   
	 * @param sellerId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Kill> listEnd(@Param("sellerId")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：统计正在进行中活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午5:07:02   
	 * @param activityType
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(@Param("sellerId")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:44:18   
	 * @param id
	 * @param sellerId 
	 */
	void endActivity(@Param("id")Integer id,@Param("sellerId") Integer sellerId);
	
	Kill selectByPrimaryKeyAndGiveAwardCount(@Param("id")Integer id,@Param("sellerId") Integer sellerId);
}