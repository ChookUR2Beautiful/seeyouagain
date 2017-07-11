package com.xmn.saas.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.FcouspointsConver;

public interface FcouspointsConverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FcouspointsConver record);

    int insertSelective(FcouspointsConver record);

    FcouspointsConver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FcouspointsConver record);

    int updateByPrimaryKey(FcouspointsConver record);
    
    /**
     * 
     * 方法描述：查询兑换记录
     * 创建人：jianming  
     * 创建时间：2016年11月25日 下午3:43:29   
     * @param activityId
     * @param sellerId
     * @param pageSize
     * @param pageIndex
     * @return
     */
	List<FcouspointsConver> listConver(@Param("activityId")Integer activityId, @Param("sellerid")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	
	/**
	 * 
	 * 方法描述：根据日期统计兑换记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 上午11:02:20   
	 * @param activityId
	 * @return
	 */
	List<Map<String, Object>> countConverByDate(Integer activityId);
	
	
}