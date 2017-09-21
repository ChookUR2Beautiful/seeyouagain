package com.xmniao.dao.coupon;

import java.util.List;

import com.xmniao.domain.coupon.SellerCoupon;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerCouponDao
 * 
 * 类描述： 商户优惠券DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月24日 下午1:44:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SellerCouponDao {
    
    /*
     * 获取商家优惠券列表
     */
    List<SellerCoupon> getSellerCouponList(SellerCoupon sellerCoupon);
    
    /*
     * 获取商家优惠券列表
     */
    List<SellerCoupon> getSellerCouponList2(SellerCoupon sellerCoupon);
    
    
    /*
     * 更新商家优惠券
     */
    int sendSellerCoupon(SellerCoupon sellerCoupon);


	public int mdfSellerCoupon(SellerCoupon sellerCoupon);
	
	/*
	 * 更新商家优惠券使用数+1
	 */
	int useSellerCoupon(SellerCoupon sellerCoupon);
}