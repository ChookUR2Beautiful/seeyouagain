package com.xmniao.dao.coupon;

import java.util.List;

import com.xmniao.domain.coupon.UserCoupon;
/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserCouponDao
 * 
 * 类描述： 用户获取商家优惠券DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月24日 下午1:47:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface UserCouponDao {
    
    /*
     * 统计用户获取的优惠券总数
     */
    int getUserCouponCout(UserCoupon userCoupon);
    
    /*
     * 发送用户优惠券
     */
    int addUserCoupon(UserCoupon userCoupon);
    
    /*
     * 获取用户消费优惠券信息
     */
    UserCoupon getUserCouponInfo(Long bid);
    
    /*
     * 获取用户消费优惠券信息
     */
    UserCoupon getUserCouponInfo2(UserCoupon userCoupon);
    
    /*
     * 更新用户消费优惠券的使用状态
     */
    int modifyUserCoupon(UserCoupon userCoupon);

	public int addUserCouponList(List<UserCoupon> userCouponList);
	
	/*
	 * 获取订单所赠送的所有优惠券
	 */
	List<UserCoupon> getUserCouponList(Long bid);
	
	/*
	 * 删除该所赠送的商家的优惠券
	 */
	int delectUserCoupon(UserCoupon userCoupon);
}