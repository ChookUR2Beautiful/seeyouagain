package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.RoulleteRecord;

public interface RoulleteRecordService {
	
	/**
	 * 
	 * 方法描述：领取记录列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午3:00:23   
	 * @param activityId
	 * @param sellerId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<RoulleteRecord> list(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：记录明细
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:47:50   
	 * @param recordId
	 * @param activityId
	 * @return
	 */
	RoulleteRecord detail(Integer recordId, Integer activityId);
	
	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:47:08   
	 * @param id
	 * @return
	 */
	Integer countRecord(Integer id);

}
