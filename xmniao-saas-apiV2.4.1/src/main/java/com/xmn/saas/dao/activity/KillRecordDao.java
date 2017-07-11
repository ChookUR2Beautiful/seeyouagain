package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.KillRecord;

public interface KillRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(KillRecord record);

    int insertSelective(KillRecord record);

    KillRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KillRecord record);

    int updateByPrimaryKey(KillRecord record);
    
    /**
     * 
     * 方法描述：获得领取列表
     * 创建人：jianming  
     * 创建时间：2016年10月12日 下午3:04:06   
     * @param activityId
     * @param sellerId
     * @return
     */
	List<KillRecord> list(@Param("activityId")Integer activityId,@Param("sellerId")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：查询明细
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:53:16   
	 * @param recordId
	 * @param activityId
	 * @return
	 */
	KillRecord detail(@Param("id")Integer recordId, @Param("activityId")Integer activityId);

	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:48:17   
	 * @param activity
	 * @return
	 */
	Integer countRecord(Integer activity);
	
}