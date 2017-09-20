package com.xmniao.xmn.core.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.coupon.entity.TCouponStatistics;
import com.xmniao.xmn.core.coupon.service.CouponStatisticsService;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：CouponstatisticsController
 * 
 * @类描述： 优惠券统计
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间 ：2015年06月01日 下午14:00:44
 * 
 */
@Controller
@RequestMapping(value = "/coupon/statistics")
public class CouponStatisticsController extends BaseController {

	@Autowired
	private CouponStatisticsService couponStatisticsService;
	
	/**
	 * 优惠券统计列表初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "coupon/statistics/init";
	}

	/**
	 * 按活动统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "activity/init/list")
	@ResponseBody
	public Object activityInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable.setContent(couponStatisticsService.getList(couponStatistics));
		pageable.setTotal(couponStatisticsService.count(couponStatistics));
		return pageable;
	}
	
	/**
	 * 按活动统计中未使用列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "activity/unUse/init/list")
	public Object activityUnUseInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getUnUseOrUsed(couponStatistics);
		return pageable;
	}
	@RequestMapping(value = "activity/unUse/init")
	public ModelAndView activityUnUseInitList(@RequestParam("issueid")Integer issueid){
		ModelAndView modelAndView = new ModelAndView("coupon/statistics/unUseList");
		modelAndView.addObject("issueid", issueid);
		modelAndView.addObject("requestInit", "coupon/statistics/activity/unUse/list");
		return modelAndView;
	}
	
	
	/**
	 * 按活动统计中已使用列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "activity/used/init/list")
	public Object activityUsedInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getUnUseOrUsed(couponStatistics);
		return pageable;
	}
	@RequestMapping(value = "activity/used/init")
	public ModelAndView activityUsedInitList(@RequestParam("issueid")Integer issueid){
		ModelAndView modelAndView = new ModelAndView("coupon/statistics/usedList");
		
		modelAndView.addObject("issueid", issueid);
		modelAndView.addObject("requestInit", "coupon/statistics/coupon/used/list");
		return modelAndView;
	}
	
	/**
	 * 按优惠券统计
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "coupon/init/list")
	public Object getCouponInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getCouponInitList(couponStatistics);
		return pageable;
	}
	
	/**
	 * 按优惠券统计中未使用列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "coupon/couponUnUse/init/list")
	public Object couponUnUseInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getCouponUnUseOrUsed(couponStatistics);
		return pageable;
	}
	@RequestMapping(value = "coupon/couponUnUse/init")
	public ModelAndView couponUnUseInitList(@RequestParam("cid")Integer cid){
		ModelAndView modelAndView = new ModelAndView("coupon/statistics/couponUnUseList");
		modelAndView.addObject("cid", cid);
		modelAndView.addObject("requestInit", "coupon/statistics/coupon/couponUnUse/list");
		return modelAndView;
	}
	
	
	/**
	 * 按活动统计中已使用列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "coupon/couponUsed/init/list")
	public Object couponUsedInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getCouponUnUseOrUsed(couponStatistics);
		return pageable;
	}
	@RequestMapping(value = "coupon/couponUsed/init")
	public ModelAndView couponUsedInitList(@RequestParam("cid")Integer cid){
		ModelAndView modelAndView = new ModelAndView("coupon/statistics/couponUsedList");
		modelAndView.addObject("cid", cid);
		modelAndView.addObject("requestInit", "coupon/statistics/coupon/couponUsed/list");
		return modelAndView;
	}
	
	/**
	 * @author cao'yingde
	 * 查看区域
	 */
	@ResponseBody
	@RequestMapping(value = "coupon/viewArea/viewAreaInit/list")
	public Object checkInitList(TCouponStatistics couponStatistics) {
		this.log.info("CouponStatisticsController-->list couponStatistics=" + couponStatistics);
		Pageable<TCouponStatistics> pageable = new Pageable<TCouponStatistics>(couponStatistics);
		pageable = couponStatisticsService.getViewAreaByCid(couponStatistics);
		return pageable;
	}
	@RequestMapping(value = "coupon/viewArea/viewAreaInit")
	public ModelAndView checkInitList(@RequestParam("cid")Integer cid,@RequestParam Integer ctype){
		ModelAndView modelAndView = new ModelAndView("coupon/statistics/viewArea");
		modelAndView.addObject("cid", cid);
		modelAndView.addObject("ctype", ctype);
		modelAndView.addObject("requestInit", "coupon/statistics/coupon/viewArea/list");
		return modelAndView;
	}
}
