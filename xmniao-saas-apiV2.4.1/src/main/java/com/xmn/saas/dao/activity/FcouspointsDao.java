package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.Fcouspoints;

public interface FcouspointsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Fcouspoints record);

    int insertSelective(Fcouspoints record);

    Fcouspoints selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fcouspoints record);

    int updateByPrimaryKey(Fcouspoints record);
    
    /**
     * 
     * 方法描述：查询活动
     * 创建人：jianming  
     * 创建时间：2016年11月24日 下午6:33:11   
     * @param id
     * @param sellerId
     * @return
     */
	Fcouspoints selectBySellerKey(@Param("id")Integer id, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询进行中活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午8:21:21   
	 * @param sellerId
	 * @return
	 */
	List<Fcouspoints> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询已结束活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午8:44:20   
	 * @param sellerId
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<Fcouspoints> listEnd(@Param("sellerid")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：统计进行中活动数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午9:18:25   
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午8:50:11   
	 * @param activityId
	 * @param sellerId
	 */
	void endActivity(@Param("activityId")Integer activityId, @Param("sellerid")Integer sellerId);
}