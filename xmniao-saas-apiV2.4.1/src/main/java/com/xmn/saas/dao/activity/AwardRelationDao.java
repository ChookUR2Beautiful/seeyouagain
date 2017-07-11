package com.xmn.saas.dao.activity;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.AwardRelation;

public interface AwardRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardRelation record);

    int insertSelective(AwardRelation record);

    AwardRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardRelation record);

    int updateByPrimaryKey(AwardRelation record);
    
    /**
     * 
     * 方法描述：获取活动奖品记录
     * 创建人：jianming  
     * 创建时间：2016年10月12日 上午11:03:16   
     * @param activityId
     * @param activityType
     * @return
     */
	List<AwardRelation> getActivityAward(@Param("activityId")Integer activityId, @Param("activityType")Integer activityType);
	
	/**
	 * 
	 * 方法描述：修改礼物记录状态
	 * 创建人：jianming  
	 * 创建时间：2016年10月17日 上午11:36:04   
	 * @param id
	 * @param status
	 */
	void updateStatus(@Param("id")Integer id, @Param("status")int status);
	
	/**
	 * 
	 * 方法描述：统计设置奖品总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:40:43   
	 * @param id
	 * @param activityType
	 * @return
	 */
	Integer sumAward(@Param("activityId")Integer activityId,@Param("activityType") Integer activityType);
    
}