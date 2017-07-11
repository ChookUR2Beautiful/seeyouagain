package com.xmn.saas.service.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.coupon.SellerCouponDetail;

public interface FreetryService {

	Freetry detail(Integer id, Integer sellerId);

	Integer save(Freetry freetry);
	
	/**
	 * 
	 * 方法描述：获取活动列表,beingorend代表true:进行中,false:已结束
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午3:56:13   
	 * @param sellerId
	 * @param beingorend
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Freetry> list(Integer sellerId, boolean beingorend, Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：获取免尝获得可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 上午11:08:09   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询进行中的活动
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 下午7:55:38   
	 * @param sellerId
	 * @return
	 */
	List<Freetry> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询已结束的活动
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 下午9:16:16   
	 * @param sellerId
	 * @return
	 */
	List<Freetry> listEnd(Integer sellerId,Integer pageSize, Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：根据赠品券id获取优惠券列表
	 * 创建人：jianming  
	 * 创建时间：2016年9月30日 下午5:52:13   
	 * @param awardRelations
	 * @return
	 */
	List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelations);
	
	/**
	 * 
	 * 方法描述：获取活动礼物列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午1:43:47   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<AwardRelation> getFreetryAway(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：把临时免尝对象保存到redis
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 上午11:52:11   
	 * @param freetry
	 * @param sellerId
	 */
	void saveTempFreetry(Freetry freetry, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：从redis中取回临时免尝对象
	 * 创建人：jianming  
	 * 创建时间：2016年10月14日 下午2:12:05   
	 * @param sellerId
	 * @return
	 */
	Freetry giveTempFreetry(Integer sellerId);
	
	
	/**
	 * 
	 * 方法描述：统计进行中活动数
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午5:24:37   
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:43:06   
	 * @param id
	 * @param sellerId 
	 */
	void endActivity(Integer id, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询优惠券礼物的剩余数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月1日 下午4:00:51   
	 * @param awardId
	 * @return
	 */
	Integer selectAwardCount(Integer awardId);

}
