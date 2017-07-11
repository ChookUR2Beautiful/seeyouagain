/**
 * 
 */
package com.xmn.saas.service.coupon;

import java.util.List;
import java.util.Map;

import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.coupon.UserCoupon;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   商户优惠券服务
 * 创建人：huangk   
 * 创建时间：2016年9月26日 下午3:09:28      
 */

public interface CouponService {

	/**
	 * @param QueryCondition 查询参数
	 * @return List<SellerCouponDetail>	返回商户下所有符合条件的优惠券活动列表
	 */
	List<SellerCouponDetail> queryCouponList(QueryCondition query);
	
	/**
	 * 用户领取优惠券列表
	 * @param couponId 优惠券id
	 * @return List<SellerCouponDetail>	返回用户领取优惠券列表
	 */
	List<Map<String,Object>> queryUserCouponListByCid(int couponId,Integer sellerId);
	
	/**
	 * 查询商户单个优惠券信息
	 * @param 优惠券id
	 * @return SellerCouponDetail
	 * @throws
	 */
	SellerCouponDetail querySellerCouponById(int couponId);
	
	/**
	 * 查询用户优惠券详情
	 * @param recordId 领取记录id
	 * @return Map<String,Object>
	 * @throws
	 */
	UserCoupon queryUserCouponById(int recordId);
	
	/**
	 * 保存商户优惠券相关信息
	 * @param 优惠券定义实体
	 * @return 主键
	 */
	int saveSellerCoupon(SellerCouponDetail sellerCouponDetail);
	
	/**
	 * 更新商户优惠券相关信息
	 * @param 优惠券定义
	 * @return int
	 */
	int updateSellerCoupon(SellerCouponDetail sellerCouponDetail);
	
	/**
	 * 统计新绑定用户数
	 * @param 活动优惠券id
	 * @return int    返回类型
	 * @throws
	 */
	int countNewuer(int couponId);
	
	/**
	 * 
	 * 方法描述：根据礼物Id和类型获得优惠券名字
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午8:33:59   
	 * @param activityId
	 * @param awardType
	 * @return
	 */
	String getNameBy(Integer awardId, Integer awardType);
	
	/**
	 * 
	 * 方法描述：获取免尝活动可选赠品券奖品
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 上午11:11:14   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listFreetryAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：根据赠品券id获取优惠券列表
	 * 创建人：jianming  
	 * 创建时间：2016年9月30日 下午5:57:09   
	 * @param sellerCouponDetails
	 * @return
	 */
	List<SellerCouponDetail> selectByFreetry(AwardRelation[] awardRelation);
	
	/**
	 * 
	 * 方法描述：锁定奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 上午11:42:37   
	 * @param awardId
	 * @param couponType 
	 */
	void setAward(Integer awardId);
	
	/**
	 * 
	 * 方法描述：获取大转盘可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午10:01:46   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listAllAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得赠品券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:57:00   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getZhengpinCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得抵用券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:57:26   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getDiyongCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动优惠券奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:09:01   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<SellerCouponDetail> getActivityAward(Integer id, Integer activityType);
	
	/**
	 * 
	 * 方法描述：根据id查询
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 下午2:48:58   
	 * @param awardId
	 * @return
	 */
	SellerCouponDetail selectByPrimaryKey(Integer awardId);
	
	/**
	 * 
	 * 方法描述：修改领取数量
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 下午3:07:36   
	 * @param cid
	 * @param i
	 */
	void updateAwardNumber(Integer cid, int i);
	
	/**
	 * 方法描述：根据奖品表获取关联优惠券
	 * 创建人：jianming  
	 * 创建时间：2016年10月25日 上午9:56:27   
	 * @param awardId
	 * @param awardType
	 * @return
	 */
	SellerCouponDetail selectOneByAward(Integer awardId, Integer awardType);
	
	/**
	 * 
	 * 方法描述：计算优惠券可选择数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月25日 上午9:39:33   
	 * @param awardId
	 * @return
	 */
	Integer selectAwardCount(Integer awardId);
	
}
