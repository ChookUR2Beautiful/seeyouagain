package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.Activity;
import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.common.SellerAccount;

public interface RecordService {
	
	/**
	 * 
	 * 方法描述：获取所有 营销活动列表,beingorend代表true:进行中,false:已结束
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 下午2:22:50   
	 * @param sellerAccount
	 * @param pageIndex 当前页数
	 * @param pageSize 每页显示数
	 * @param b 
	 * @return
	 */
	List<Object> getList(SellerAccount sellerAccount, boolean beingorend, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：获取活动详情
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 下午7:43:02   
	 * @param activityId
	 * @param integer 
	 * @param activityId2
	 * @return
	 */
	Activity detailActivity(Integer activityId, Integer activityType, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动领取列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午2:46:28   
	 * @param activityId
	 * @param activityType
	 * @param pageIndex 
	 * @param pageSize 
	 * @param integer 
	 * @return
	 */
	List<ActivityRecord> listActivityRecord(Integer activityId, Integer activityType, Integer sellerId, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：获取活动领取记录
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:31:33   
	 * @param activityId
	 * @param activityType
	 * @param recordId
	 * @param sellerId 
	 * @return
	 */
	ActivityRecord detailRecord(Integer activityId, Integer activityType, Integer recordId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：从redis中取回临时活动对象
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午2:22:09   
	 * @param freetry
	 * @param sellerId
	 */
	void saveTempActivity(Activity activity, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：从redis中取回临时活动对象json
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午2:28:04   
	 * @param sellerId
	 * @param class1 
	 * @return
	 */
	<T> T giveTempActivity(Integer sellerId, Class<? extends Activity> activityClass);
	
	
	
	/**
	 * 
	 * 方法描述：统计正在进行中获得数量
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午4:30:49   
	 * @param activityType
	 * @param sellerId
	 * @return
	 */
	Integer hasActivity(Integer activityType, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：统计活动剩余奖品数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:29:43   
	 * @param activity
	 * @return
	 */
	Integer countResidue(Activity activity);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:40:19   
	 * @param activityId
	 * @param activityType
	 * @param sellerId 
	 */
	void endActivity(Integer activityId, Integer activityType, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取用户信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 下午4:14:31   
	 * @param list
	 * @param sellerId
	 * @return
	 */
	List<ActivityRecord> getUserMsg(List<? extends ActivityRecord> list, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取用户信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月29日 下午4:14:31   
	 * @param list
	 * @param sellerId
	 * @return
	 */
	ActivityRecord getUserMsg(ActivityRecord activityRecord, Integer sellerId);

}
