package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.AwardRelation;

public interface AwardRelationService {
	
	/**
	 * 
	 * 方法描述：添加礼物
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 下午4:57:30   
	 * @param awardRelation
	 */
	void save(AwardRelation awardRelation);
	
	/**
	 * 
	 * 方法描述：保存礼物组
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 下午5:14:03   
	 * @param sellerCouponDetails
	 * @param integer 
	 * @param id
	 * @param activityType
	 */
	void saveSellerCouponDetails(AwardRelation[] awardRelation, Integer activityId, Integer activityType);
	
	/**
	 * 
	 * 方法描述：查询大转盘奖品信息
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:30:57   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<Object> getRoulleteAward(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：获取活动奖品记录
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午11:02:11   
	 * @param activityId
	 * @param activityType
	 * @return
	 */
	List<AwardRelation> getActivityAward(Integer activityId, Integer activityType);
	
	/**
	 * 
	 * 方法描述：修改礼物记录状态
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 下午2:59:16   
	 * @param id	
	 * @param status		
	 */
	void updateStatus(Integer id, int status);
	
	/**
	 * 
	 * 方法描述：统计设置奖品总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:39:43   
	 * @param id
	 * @param activityType
	 * @return
	 */
	Integer sumAward(Integer id, Integer activityType);
	
	
	

}
