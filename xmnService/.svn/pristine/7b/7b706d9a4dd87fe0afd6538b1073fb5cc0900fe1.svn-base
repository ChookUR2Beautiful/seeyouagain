package com.xmniao.xmn.core.coupon.service;

import java.util.List;

import com.xmniao.xmn.core.coupon.request.CouponCardInfoRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardInfoSubRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardsRequest;
import com.xmniao.xmn.core.coupon.response.ApplyStoresResponse;
import com.xmniao.xmn.core.coupon.response.CouponCardsResponse;
import com.xmniao.xmn.core.coupon.response.UseCardRecordResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponCardsService    
* @Description:我的卡卷接口   
* @author: liuzhihao   
* @date: 2017年2月27日 上午10:19:12
 */
public interface CouponCardsService {

	/**
	 * 获取我的卡卷列表
	 * @return
	 */
	public Object getCouponCards(CouponCardsRequest request);
	
	/**
	 * 获取储值卡列表
	 * @return
	 */
	public List<CouponCardsResponse> getStoreCards(String uid,String sellerid,Integer isOverdue,Integer page,Integer pageSize) throws Exception;
	
	/**
	 * 获取寻蜜鸟礼券列表
	 * @return
	 */
	public List<CouponCardsResponse> getXMNCoupons(String uid,Integer isOverdue,Integer page,Integer pageSize)throws Exception;
	
	/**
	 * 获取平台礼券列表
	 * @return
	 */
	public List<CouponCardsResponse> getPlatCoupons(String uid,Integer isOverdue,Integer page,Integer pageSize)throws Exception;

	/**
	 * 充值卡详情
	 * @return
	 */
	public Object getCardInfo(CouponCardInfoRequest request);
	
	/**
	 * 充值卡详情子列表
	 * @param request
	 * @return
	 */
	public Object getCardInfoSubList(CouponCardInfoSubRequest request);
	
	/**
	 * 充值卡适用门店列表
	 */
	public List<ApplyStoresResponse> queryApplyStoreList(String uid,String sellerid,Double lon,Double lat,Integer sellerType,Integer page);
	
	/**
	 * 充值卡用卡记录列表
	 */
	public List<UseCardRecordResponse> queryUseCardRecordList(String uid,String sellerid,Integer rtype,Integer page,Integer pageSize);
	
}
