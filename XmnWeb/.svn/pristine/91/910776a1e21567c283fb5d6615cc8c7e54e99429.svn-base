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
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.businessman.entity.FullReduction;
import com.xmniao.xmn.core.businessman.entity.GetRedPackageRecord;
import com.xmniao.xmn.core.businessman.entity.SellerRedPackage;
import com.xmniao.xmn.core.businessman.service.FullReductionService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FullRecductionController
 * 
 * 类描述： 满减活动
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 下午5:00:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/businessman/fullReduction")
@RequestLogging(name="满减活动")
public class FullRecductionController extends BaseController{
	
	@Autowired
	private FullReductionService fService;
	
	@Autowired
	private AllBillService aBillService;
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(FullRecductionController.class);
	
	/**
	 * 
	 * 方法描述：页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日上午10:30:17 <br/>
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		return "/businessman/coupon/FullReductionList";
	}
	
	/**
	 * 
	 * 方法描述：满减活动列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日下午2:00:06 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getRecordList(FullReduction fullReduction){
		log.info("满减活动列表getRecordList"+fullReduction);
		Pageable<FullReduction> pageable = new Pageable<>(fullReduction);
		pageable.setContent(fService.getList(fullReduction));
		pageable.setTotal(fService.count(fullReduction));
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
	@RequestMapping("/init/detail/init")
	@ResponseBody
	public ModelAndView detailInit(@RequestParam("id") String id){
		ModelAndView mv = new ModelAndView("/businessman/coupon/FullReductionDetailList");
		Bill bill = new Bill();
		bill.setFullReductionId(Integer.valueOf(id));
		mv.addObject("bill",bill);
		return mv;
	}
	
	
	/**
	 * 
	 * 方法描述：获取满减活动详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:08:48 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/init/detail")
	@ResponseBody
	public Object getFRList(Bill bill){
		log.info("获取满减活动详情列表getFRList"+bill);
		Pageable<Bill> pageable = new Pageable<>(bill);
		pageable.setContent(aBillService.getFullReductionList(bill));
		pageable.setTotal(aBillService.countFullReduction(bill));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：终止进行中的满减活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:10:46 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/shutdown")
	@ResponseBody
	public Object shutdownFR(FullReduction fullReduction){
		log.info("终止进行中的满减活动shutdownFR"+fullReduction);
		Resultable resultable;
		try {
			Integer result =fService.shutdownFR(fullReduction);
			if(result == 1){
				log.info("终止满减活动成功");
				resultable = new Resultable(true,"操作成功");
				return resultable;
			}else {
				log.error("终止满减活动失败");
				resultable = new Resultable(false,"操作失败");
				return resultable;
			}
		} catch (Exception e) {
			log.error("终止满减活动失败",e);
			resultable = new Resultable(false,"操作失败");
			return resultable;
		}
	}
}
