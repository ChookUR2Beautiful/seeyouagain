package com.xmn.saas.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.FcouspointsRecord;

public interface FcouspointsRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FcouspointsRecord record);

    int insertSelective(FcouspointsRecord record);

    FcouspointsRecord selectByPrimaryKey(@Param("id")Integer id, @Param("sellerid")Integer sellerId);

    int updateByPrimaryKeySelective(FcouspointsRecord record);

    int updateByPrimaryKey(FcouspointsRecord record);
    
    /**
     * 
     * 方法描述：统计活动参与人数
     * 创建人：jianming  
     * 创建时间：2016年11月24日 下午7:45:58   
     * @param id
     * @return
     */
	Integer countByActivityId(Integer id);
	
	/**
	 * 
	 * 方法描述：查询集点记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 下午2:11:43   
	 * @param activityId
	 * @param activityId2
	 * @param pageSize
	 * @param pageSize2
	 * @return
	 */
	List<FcouspointsRecord> listRecord(@Param("activityId")Integer activityId,@Param("sellerid")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex") Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：根据日期统计参与记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 上午10:56:37   
	 * @param activityId
	 * @return
	 */
	List<Map<String, Object>> countRecordByDate(Integer activityId);
}