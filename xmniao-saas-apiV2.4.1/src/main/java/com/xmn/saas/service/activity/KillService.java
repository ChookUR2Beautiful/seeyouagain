package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.Kill;
import com.xmn.saas.entity.coupon.SellerCouponDetail;

public interface KillService {
	/**
	 * 
	 * 方法描述：获取活动列表,beingorend代表true:进行中,false:已结束
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午3:57:20   
	 * @param sellerId
	 * @param beingorend
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Kill> list(Integer sellerId, boolean beingorend, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：加载礼物列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 上午10:10:27   
	 * @param awardRelations
	 * @return
	 */
	List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelations);
	
	/**
	 * 
	 * 方法描述：查询秒杀可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 上午10:18:33   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：添加秒杀活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 上午10:41:03   
	 * @param kill
	 */
	Integer save(Kill kill);
	
	/**
	 * 
	 * 方法描述：获取活动详情
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 下午7:48:45   
	 * @param activityId
	 * @param sellerId
	 */
	Kill detail(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:00:32   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<AwardRelation> getKillAward(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：把临时免尝对象保存到redis
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午3:36:18   
	 * @param kill
	 * @param sellerId
	 */
	void saveTempKill(Kill kill, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：从redis中取回临时免尝对象
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午3:36:22   
	 * @param sellerId
	 * @return
	 */
	Kill giveTempKill(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：统计正在进行中的活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午5:27:12   
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:56:19   
	 * @param activityId
	 * @param sellerId
	 */
	void endActivity(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询优惠券礼物的剩余数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月1日 下午4:09:12   
	 * @param awardId
	 * @return
	 */
	Integer selectAwardCount(Integer awardId);

}
