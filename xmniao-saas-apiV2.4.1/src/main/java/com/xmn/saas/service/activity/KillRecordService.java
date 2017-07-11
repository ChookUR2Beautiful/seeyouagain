package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.KillRecord;

public interface KillRecordService {
	
	/**
	 * 
	 * 方法描述：领取记录列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午3:00:52   
	 * @param activityId
	 * @param sellerId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<KillRecord> list(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：查询明细
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:52:31   
	 * @param recordId
	 * @param activityId
	 * @return
	 */
	KillRecord detail(Integer recordId, Integer activityId);
	
	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:47:18   
	 * @param id
	 * @return
	 */
	Integer countRecord(Integer id);

}
