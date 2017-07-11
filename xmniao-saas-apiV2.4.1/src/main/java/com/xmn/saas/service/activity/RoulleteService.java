package com.xmn.saas.service.activity;

import java.util.List;

import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Roullete;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;

public interface RoulleteService {
	/**
	 * 
	 * 方法描述：获取活动列表,beingorend代表true:进行中,false:已结束
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午3:57:33   
	 * @param sellerId
	 * @param beingorend
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Roullete> list(Integer sellerId, boolean beingorend, Integer pageSize, Integer pageIndex);

	/**
	 * 
	 * 方法描述：根据赠品券id获取优惠券列表
	 * 创建人：jianming  
	 * 创建时间：2016年9月30日 下午5:52:13   
	 * @param sellerCouponDetails
	 * @return
	 */
	List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelations);
	
	/**
	 * 
	 * 方法描述：获取大转盘可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午9:51:09   
	 * @param sellerId
	 * @return
	 */
	List<Object> listAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取赠品券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:54:35   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getZhengpinCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得抵用券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:55:00   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getDiyongCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得红包可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:55:19   
	 * @param sellerId
	 * @return
	 */
	List<Redpacket> getRedpackets(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：添加大转盘
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 下午4:53:21   
	 * @param roullete
	 */
	Integer save(Roullete roullete);
	
	/**
	 * 
	 * 方法描述：获取活动信息
	 * 创建人：jianming  
	 * 创建时间：2016年10月11日 下午7:48:04   
	 * @param activityId
	 * @param sellerId
	 */
	Roullete detail(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得大转盘奖品信息
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:27:55   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<AwardRelation> getRoulleteAward(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：把临时大转盘对象保存到redis
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午4:12:56   
	 * @param roullete
	 * @param sellerId
	 */
	void saveTempRoullete(Roullete roullete, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：从redis中取回临时活动对象json
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午4:13:04   
	 * @param sellerId
	 * @return
	 */
	Roullete giveTempRoullete(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：统计正在进行中的活动数
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午5:28:34   
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:54:32   
	 * @param activityId
	 * @param sellerId
	 */
	void endActivity(Integer activityId, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询优惠券礼物的剩余数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月1日 下午4:22:14   
	 * @param awardId
	 * @return
	 */
	Integer selectAwardCount(Integer awardId);

	
}
