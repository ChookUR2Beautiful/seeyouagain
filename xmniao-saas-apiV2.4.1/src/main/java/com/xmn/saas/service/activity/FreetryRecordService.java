package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.FreetryRecord;

public interface FreetryRecordService {
	/**
	 * 
	 * 方法描述：获取免尝记录列表
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 下午3:20:44   
	 * @param sellerId
	 * @param activityId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<FreetryRecord> list(Integer sellerId, Integer activityId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：记录明细
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 下午5:24:44   
	 * @param id
	 * @param activityId
	 * @return
	 */
	FreetryRecord detail(Integer id, Integer activityId);
	
	/**
	 * 
	 * 方法描述：根据id查询免尝活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月9日 上午10:13:52   
	 * @param activityId
	 * @param sellerId 
	 * @return
	 */
	Freetry getFreetryById(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：统计获奖人数
	 * 创建人：jianming  
	 * 创建时间：2016年10月9日 上午10:30:49   
	 * @param activityId
	 * @return
	 */
	Integer countFreetryRecord(Integer activityId);
	
	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:46:54   
	 * @param id
	 * @return
	 */
	Integer countRecord(Integer id);


}
