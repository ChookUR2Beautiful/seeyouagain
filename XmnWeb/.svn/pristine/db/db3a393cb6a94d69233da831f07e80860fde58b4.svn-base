/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.businessman.entity.SellerCoupon;
import com.xmniao.xmn.core.businessman.entity.UserCoupon;
import com.xmniao.xmn.core.businessman.service.FullReductionService;
import com.xmniao.xmn.core.businessman.service.SellerCouponService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerCouponController
 * 
 * 类描述： 商家优惠券
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 下午5:00:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/businessman/coupon")
@RequestLogging(name="商家优惠券")
public class SellerCouponController extends BaseController{
	
	@Autowired
	private FullReductionService fService;
	
	@Autowired
	private AllBillService aBillService;
	
	@Autowired
	private SellerCouponService sCouponService;
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(SellerCouponController.class);
	
	/**
	 * 
	 * 方法描述：现金抵用券页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日上午10:30:17 <br/>
	 * @return
	 */
	@RequestMapping("/init1")
	public String init(){
		return "/businessman/coupon/dCouponList";
	}
	
	/**
	 * 
	 * 方法描述：赠品券页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午3:40:28 <br/>
	 * @return
	 */
	@RequestMapping("/init2")
	public String init2(){
		return "/businessman/coupon/giftCouponList";
	}
	
	/**
	 * 
	 * 方法描述：优惠券列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日下午2:00:06 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object getRecordList(SellerCoupon sCoupon){
		log.info("优惠券列表getRecordList"+sCoupon);
		Pageable<SellerCoupon> pageable = new Pageable<>(sCoupon);
		pageable.setContent(sCouponService.getList(sCoupon));
		pageable.setTotal(sCouponService.count(sCoupon));
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转详情页
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:08:48 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("list/detail/init")
	@ResponseBody
	public ModelAndView detailInit(@RequestParam("cid") String id){
		ModelAndView mv = new ModelAndView("/businessman/coupon/dCouponDetailList");
		UserCoupon userCoupon = new UserCoupon();
		userCoupon.setCid(Integer.valueOf(id));
		mv.addObject("userCoupon",userCoupon);
		return mv;
	}
	
	
	/**
	 * 
	 * 方法描述：获取商家优惠券领取详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:08:48 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("list/detail")
	@ResponseBody
	public Object getSCList(UserCoupon uCoupon){
		log.info("获取商家优惠券领取详情列表getSCList"+uCoupon);
		Pageable<UserCoupon> pageable = new Pageable<>(uCoupon);
		pageable.setContent(sCouponService.userCouponList(uCoupon));
		pageable.setTotal(sCouponService.countUserCoupon(uCoupon));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：终止进行中的活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:10:46 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/shutdown")
	@ResponseBody
	public Object shutdownSC(SellerCoupon sCoupon){
		log.info("终止活动shutdownSC"+sCoupon);
		Resultable resultable;
		try {
			Integer result =sCouponService.shutdown(sCoupon);
			if(result == 1){
				log.info("终止活动成功");
				resultable = new Resultable(true,"操作成功");
				return resultable;
			}else {
				log.error("终止活动失败");
				resultable = new Resultable(false,"操作失败");
				return resultable;
			}
		} catch (Exception e) {
			log.error("终止活动失败",e);
			resultable = new Resultable(false,"操作失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：验帐核销
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:10:46 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/shutdown/userCoupon")
	@ResponseBody
	public Object shutdownUC(UserCoupon uCoupon){
		log.info("停用用户优惠券shutdownUC"+uCoupon);
		Resultable resultable;
		try {
			Integer result =sCouponService.shutdownUC(uCoupon);
			if(result == 1){
				log.info("停用用户优惠券成功");
				resultable = new Resultable(true,"操作成功");
				return resultable;
			}else {
				log.error("停用用户优惠券失败");
				resultable = new Resultable(false,"操作失败");
				return resultable;
			}
		} catch (Exception e) {
			log.error("停用用户优惠券失败",e);
			resultable = new Resultable(false,"操作失败");
			return resultable;
		}
	}
}
