/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerCouponDao;
import com.xmniao.xmn.core.businessman.entity.SellerCoupon;
import com.xmniao.xmn.core.businessman.entity.UserCoupon;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerCouponService
 * 
 * 类描述：商家优惠券
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 下午4:08:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class SellerCouponService extends BaseService<SellerCoupon>{
	
	@Autowired
	private SellerCouponDao sCouponDao;
	
	@Override
	protected BaseDao<SellerCoupon> getBaseDao() {
		return sCouponDao;
	}
	
	/**
	 * 
	 * 方法描述：用户领取优惠券列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:24:32 <br/>
	 * @param ucCoupon
	 * @return
	 */
	public List<UserCoupon> userCouponList(UserCoupon uCoupon){
		return sCouponDao.getUserCouponDetail(uCoupon);
	}
	
	public Long countUserCoupon(UserCoupon uCoupon){
		return sCouponDao.countUserCoupon(uCoupon);
	}
	
	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:36:46 <br/>
	 * @param sCoupon
	 * @return
	 */
	public Integer shutdown(SellerCoupon sCoupon){
		return sCouponDao.shutdown(sCoupon);
	}
	
	/**
	 * 
	 * 方法描述：停用用户优惠券
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午2:36:46 <br/>
	 * @param sCoupon
	 * @return
	 */
	public Integer shutdownUC(UserCoupon uCoupon){
		return sCouponDao.shutdownUC(uCoupon);
	}
}
