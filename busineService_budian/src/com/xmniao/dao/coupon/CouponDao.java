/**    
 * 文件名：CouponDao.java    
 *    
 * 版本信息：    
 * 日期：2016年9月6日    
 * Copyright 足下 Corporation 2016     
 * 版权所有    
 *    
 */
package com.xmniao.dao.coupon;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.coupon.Coupon;
import com.xmniao.domain.coupon.CouponDetail;
import com.xmniao.domain.coupon.CouponRelation;
import com.xmniao.domain.order.CouponRelationBean;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：CouponDao
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年9月6日 上午10:05:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface CouponDao {
	/**
	 * 获取优惠券信息
	 * @Title: getCouponList 
	 * @Description:
	 */
	List<Map<String,Object>> getCouponList(Map<String,Object> map);
	
	/**
	 * 获取优惠券短信信息
	 * @Title: insertCouponIssue 
	 * @Description:
	 */
	List<Map<String,Object>> getCouponIssue(Integer cid);
	
	/**
	 * 插入优惠券派送信息
	 * @Title: insertCouponDetail 
	 * @Description:
	 */
	int insertCouponDetail(Map<String,Object> datailMap);
	
	/**
	 * 获取用户的赠送优惠券信息
	 * @Title: getCouponDetailList 
	 * @Description:
	 */
	List<Map<String,Object>> getCouponDetailList(Map<String,Object> map);
	
	/**
	 * 获取优惠券的使用关联表
	 * @Title: getCouponRelation 
	 * @Description:
	 */
	List<CouponRelation> getCouponRelation(Long bid);
	
	/**
	 * 获取优惠券
	 * @Title: getCouponDetail 
	 * @Description:
	 */
	CouponDetail getCouponDetail(Integer cdid);
	
	/**
	 * 更新优惠券
	 * @Title: updateCouponDetail 
	 * @Description:
	 */
	int updateCouponDetail(CouponDetail couponDetail);
	
	/**
	 * 获取粉丝券关联表
	 * @param anchorId
	 * @return
	 */
	Map<String,Object> getCouponAnchorRef(String anchorId);
	
	/**
	 * 
	 * 方法描述：获取优惠券Entity <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月8日上午11:32:50 <br/>
	 * @param coupon
	 * @return
	 */
	Coupon getCoupon(Coupon coupon);
	
	/**
	 * 批量插入优惠券派送信息
	 * @Title: insertCouponDetailList 
	 * @Description:
	 */
	int insertCouponDetailList(List<CouponDetail> detailList);
}
