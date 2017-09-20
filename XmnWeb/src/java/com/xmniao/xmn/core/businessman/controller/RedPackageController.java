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
import com.xmniao.xmn.core.businessman.entity.GetRedPackageRecord;
import com.xmniao.xmn.core.businessman.entity.SellerRedPackage;
import com.xmniao.xmn.core.businessman.service.SellerRedPackageService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RedPackageController
 * 
 * 类描述： 商户会员红包
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月7日 上午10:26:50 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/businessman/redPackage")
@RequestLogging(name="商户会员红包")
public class RedPackageController extends BaseController{
	
	@Autowired
	private SellerRedPackageService sPackageService;
	
	private Logger log = Logger.getLogger(RedPackageController.class);
	
	/**
	 * 
	 * 方法描述：页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日上午10:30:17 <br/>
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		return "/businessman/redPackage/redPackageList";
	}
	
	/**
	 * 
	 * 方法描述：获取商户红包数据列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日下午2:00:06 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getRecordList(SellerRedPackage sPackage){
		log.info("获取商户红包数据列表getRecordList"+sPackage);
		Pageable<SellerRedPackage> pageable = new Pageable<>(sPackage);
		pageable.setContent(sPackageService.getList(sPackage));
		pageable.setTotal(sPackageService.count(sPackage));
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
		ModelAndView mv = new ModelAndView("/businessman/redPackage/redPackageDetailList");
		GetRedPackageRecord gRecord = new GetRedPackageRecord();
		gRecord.setRedPackageId(Long.valueOf(id));
		mv.addObject("gRecord",gRecord);
		return mv;
	}
	
	
	/**
	 * 
	 * 方法描述：获取红包领取明细
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:08:48 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/init/detail")
	@ResponseBody
	public Object getRPDetail(GetRedPackageRecord gRecord){
		log.info("获取红包领取详情列表列表getRecordList"+gRecord);
		Pageable<GetRedPackageRecord> pageable = new Pageable<>(gRecord);
		pageable.setContent(sPackageService.getRecordList(gRecord));
		pageable.setTotal(sPackageService.countRecord(gRecord));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：终止进行中的红包
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:10:46 <br/>
	 * @param sPackage
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object shutDownRedPackage(SellerRedPackage sPackage){
		log.info("终止红包活动shutDownRedPackage"+sPackage);
		Resultable resultable;
		try {
			Integer result = sPackageService.shutDownRedPackage(sPackage) ;
			if(result == 1){
				log.info("终止红包活动成功");
				resultable = new Resultable(true,"操作成功");
				return resultable;
			}else {
				log.error("终止红包活动失败");
				resultable = new Resultable(false,"操作失败");
				return resultable;
			}
		} catch (Exception e) {
			log.error("终止红包活动失败",e);
			resultable = new Resultable(false,"操作失败");
			return resultable;
		}
	}
}
