package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.RoulleteRecord;

public interface RoulleteRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RoulleteRecord record);

    int insertSelective(RoulleteRecord record);

    RoulleteRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoulleteRecord record);

    int updateByPrimaryKey(RoulleteRecord record);
    
    /**
     * 
     * 方法描述：获取领取列表
     * 创建人：jianming  
     * 创建时间：2016年10月12日 下午3:03:24   
     * @param activityId
     * @param sellerId
     * @param pageIndex 
     * @param pageSize 
     * @return
     */
	List<RoulleteRecord> list(@Param("activityId")Integer activityId,@Param("sellerId") Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：查询明细
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:49:19   
	 * @param recordId
	 * @param activityId
	 * @return
	 */
	RoulleteRecord detail(@Param("id")Integer recordId,@Param("activityId")Integer activityId);
	
	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:55:53   
	 * @param id
	 * @return
	 */
	Integer countRecord(Integer activityId);
	
	/**
	 * 
	 * 方法描述：领到优惠券明细
	 * 创建人：jianming  
	 * 创建时间：2016年11月14日 上午11:24:10   
	 * @param recordId
	 * @param activityId
	 * @return
	 */
	RoulleteRecord couponDetail(@Param("id")Integer recordId,@Param("activityId")Integer activityId);
	
	
}