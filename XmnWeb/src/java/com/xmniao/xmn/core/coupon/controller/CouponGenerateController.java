package com.xmniao.xmn.core.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.coupon.entity.TCoupon;
import com.xmniao.xmn.core.coupon.entity.TCouponSeller;
import com.xmniao.xmn.core.coupon.service.CouponService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：CouponGenerateController
 * 
 * @类描述： 优惠券生成
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年5月29日 下午3:20:44
 * 
 */
@Controller
@RequestLogging(name = "优惠券生成")
@RequestMapping(value = "/coupon/generate")
public class CouponGenerateController extends BaseController {

	@Autowired
	private CouponService couponService;

	/**
	 * 优惠券列表初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "coupon/generate/init";
	}

	/**
	 * 优惠券列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object initList(TCoupon coupon) {
		Pageable<TCoupon> pageable = new Pageable<TCoupon>(coupon);
		pageable.setContent(couponService.getList(coupon));
		pageable.setTotal(couponService.count(coupon));
		return pageable;
	}

	/**
	 * 添加优惠券初始页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/init")
	public String add(Model model) {
		model.addAttribute("type", "add");
		return "coupon/generate/couponAdd";
	}

	@RequestMapping(value = "/add/init2")
	public String add2(Model model){
		model.addAttribute("type", "add");
		return "coupon/generate/couponAdd2";
	}

	@RequestMapping(value = "/add")
	@RequestLogging(name = "添加优惠券")
	@ResponseBody
	public Object add(TCoupon coupon) {
		this.log.info(coupon);
		Resultable resultable = new Resultable();
		couponService.addOrUpdateCoupon(coupon, resultable);
		return resultable;
	}
	/**
	 * @Title:addFreshCoupon
	 * @Description:商城优惠劵添加
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "freshcoupon/add")
	@ResponseBody
	public Object addFreshCoupon(TCoupon coupon){
		this.log.info(coupon);
		Resultable resultable = new Resultable();
		couponService.addOrUpdateCoupon(coupon, resultable);
		return resultable;
	}
	/**
	 * 修改优惠券初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update/init")
	public String update( @RequestParam Integer ctype, @RequestParam Long cid, Model model) {
		couponService.getUpdateCouponInfo(cid, model);
		model.addAttribute("type", "update");
		if(ctype==1){//如果是商城优惠劵就跳转到商城优惠劵修改页面
			return "coupon/generate/freshCouponUpdate";
		}
		return "coupon/generate/couponUpdate";
		
	}
	/**
	 * add by HLS
	 * @Title:updateFreshCoupon
	 * @Description:修改商城优惠劵
	 * @param coupon
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "freshcoupon/update")
	@ResponseBody
	public Object updateFreshCoupon(TCoupon coupon) {
		this.log.info(coupon);
		Resultable resultable = new Resultable();
		couponService.addOrUpdateCoupon(coupon, resultable);
		return resultable;
	} 

	/**
	 * 取得已关联的包含商家
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/update/getIncludeSellers")
	@ResponseBody
	public Object getIncluderSellers(Long cid) {
		return couponService.getSellersByCid(cid,
				TCouponSeller.INCLUDE.INCLUDE.getValue());
	}

	/**
	 * 取得已关联的不包含商家
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/update/getExcludeSellers")
	@ResponseBody
	public Object getExcludeSellers(Long cid) {
		return couponService.getSellersByCid(cid,
				TCouponSeller.INCLUDE.EXCLUDE.getValue());
	}

	@RequestMapping(value = "/update")
	@RequestLogging(name = "修改优惠券")
	@ResponseBody
	public Object update(TCoupon coupon) {
		this.log.info(coupon);
		Resultable resultable = new Resultable();
		couponService.addOrUpdateCoupon(coupon, resultable);
		return resultable;
	}

	/**
	 * 查看优惠券商家列表
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/viewSellers/init")
	public String sellerListInit(Long cid, Model model) {
		model.addAttribute("cid", cid);
		return "coupon/generate/viewSellers";
	}

	@RequestMapping(value = "/viewSellers/list")
	@ResponseBody
	public Object sellerList(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		couponService.getCouponSellerList(seller, pageable);
		return pageable;
	}
}
