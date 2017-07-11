package com.xmn.saas.service.activity;

import java.util.List;
import java.util.Map;

import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Fcouspoints;
import com.xmn.saas.entity.activity.FcouspointsConver;
import com.xmn.saas.entity.activity.FcouspointsRecord;
import com.xmn.saas.entity.coupon.SellerCouponDetail;

public interface FcouspointsService {
	
	/**
	 * 
	 * 方法描述：获取活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午4:23:43   
	 * @param id
	 * @param sellerId
	 * @return
	 */
	Fcouspoints detail(Integer id, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动礼物
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午7:58:53   
	 * @param id
	 * @param activityType
	 * @return
	 */
	AwardRelation getFcouspointsAward(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：查询进行中活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午8:05:43   
	 * @param sellerId
	 * @return
	 */
	List<Fcouspoints> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询已结束活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午8:43:29   
	 * @param sellerId
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<Fcouspoints> listEnd(Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：统计进行中活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午9:17:21   
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：保存活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午9:20:51   
	 * @param fcouspoints
	 */
	void save(Fcouspoints fcouspoints);
	
	/**
	 * 
	 * 方法描述：获取可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 上午9:59:09   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取进行中活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 上午10:12:37   
	 * @param sellerId
	 * @param b
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<Fcouspoints> list(Integer sellerId, boolean b, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：获取获点记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 下午2:06:41   
	 * @param activityId
	 * @param sellerId
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<ActivityRecord> listRecord(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：兑换记录列表
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 下午3:05:59   
	 * @param activityId
	 * @param sellerId
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<ActivityRecord> listConver(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：根据日期统计参与记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 上午10:55:36   
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> countRecordByDate(Integer id);
	
	/**
	 * 
	 * 方法描述：根据日期统计兑换记录
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 上午10:59:39   
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> countConverByDate(Integer id);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午8:49:20   
	 * @param activityId
	 * @param sellerId
	 */
	void endActivity(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询兑换记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:06:55   
	 * @param id
	 * @param sellerId 
	 * @return
	 */
	FcouspointsConver detailConver(Integer id, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询会员消费信息
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 上午11:15:35   
	 * @param uid
	 * @param sellerId 
	 * @return
	 */
	Map<String, Object> detailUser(Integer uid, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：活动记录明细
	 * 创建人：jianming  
	 * 创建时间：2016年12月5日 下午4:00:53   
	 * @param id
	 * @param sellerId
	 * @return
	 */
	FcouspointsRecord detailRecord(Integer id, Integer sellerId);
}
