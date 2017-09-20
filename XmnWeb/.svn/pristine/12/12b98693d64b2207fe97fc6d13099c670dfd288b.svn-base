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
import com.xmniao.xmn.core.businessman.entity.FcouspointRecord;
import com.xmniao.xmn.core.businessman.entity.FreetryRecord;
import com.xmniao.xmn.core.businessman.entity.KillRecord;
import com.xmniao.xmn.core.businessman.entity.RoulleteRecord;
import com.xmniao.xmn.core.businessman.entity.SellerActivity;
import com.xmniao.xmn.core.businessman.service.SellerActivityService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerActivityController
 * 
 * 类描述： 商家活动管理
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月9日 下午5:06:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/businessman/activity")
public class SellerActivityController extends BaseController{
	
	@Autowired
	private SellerActivityService sActivityService;
	
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(SellerActivityController.class);
	
	/**
	 * 
	 * 方法描述：页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月9日下午5:10:07 <br/>
	 */
	@RequestMapping("/init")
	public String init(){
		return "/businessman/activity/SellerActivityList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getList(SellerActivity sActivity){
		log.info("商家活动getList"+sActivity);
		Pageable<SellerActivity> pageable = new Pageable<>(sActivity);
		pageable.setContent(sActivityService.getList(sActivity));
		pageable.setTotal(sActivityService.count(sActivity));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：活动详情页初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:41:25 <br/>
	 * @param type
	 * @return
	 */
	
	@RequestMapping("/init/list/detail/init")
	public ModelAndView detailInit(@RequestParam("type") Integer type,@RequestParam("id") Integer id){
		log.info("活动详情页初始化: id:"+id+" type:"+type);
		ModelAndView mv = new ModelAndView();
		if(type == 1){
			KillRecord kRecord = new KillRecord();
			kRecord.setActivityId(id);
			mv.addObject("kRecord", kRecord);
			mv.setViewName("/businessman/activity/killDetailList");
		}else if(type == 2){
			FreetryRecord fRecord = new FreetryRecord();
			fRecord.setActivityId(id);
			mv.addObject("fRecord",fRecord);
			mv.setViewName("/businessman/activity/freetryDetailList");
		}else if(type == 3){
			RoulleteRecord rRecord = new RoulleteRecord();
			rRecord.setActivityId(id);
			mv.addObject("rRecord",rRecord);
			mv.setViewName("/businessman/activity/roulleteDetailList");
		}else if(type==4){
			FcouspointRecord focusRecord = new FcouspointRecord();
			focusRecord.setActivityId(id);
			mv.addObject("focusRecord",focusRecord);
			mv.setViewName("/businessman/activity/focusDetailList");
		}else{
			log.error("参数异常");
			return mv;
		}
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：免费尝新活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:43:26 <br/>
	 * @param fRecord
	 * @return
	 */
	@RequestMapping("/init/list/freeTry/list")
	@ResponseBody
	public Object getList(FreetryRecord fRecord){
		log.info("免费尝新活动getList"+fRecord);
		Pageable<FreetryRecord> pageable = new Pageable<>(fRecord);
		pageable.setContent(sActivityService.freeRecordList(fRecord));
		pageable.setTotal(sActivityService.countFreeRecord(fRecord));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述:秒杀活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:43:26 <br/>
	 * @param fRecord
	 * @return
	 */
	@RequestMapping("/init/list/kill/list")
	@ResponseBody
	public Object getKillRecord(KillRecord kRecord){
		log.info("秒杀活动getKillRecord"+kRecord);
		Pageable<KillRecord> pageable = new Pageable<>(kRecord);
		pageable.setContent(sActivityService.killRecordList(kRecord));
		pageable.setTotal(sActivityService.countKillRecord(kRecord));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述:轮盘活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:43:26 <br/>
	 * @param fRecord
	 * @return
	 */
	@RequestMapping("/init/list/roullete/list")
	@ResponseBody
	public Object getRoullRecord(RoulleteRecord rouRecord){
		log.info("轮盘活动getRoullRecord"+rouRecord);
		Pageable<RoulleteRecord> pageable = new Pageable<>(rouRecord);
		pageable.setContent(sActivityService.roullRecordList(rouRecord));
		pageable.setTotal(sActivityService.countRoullRecord(rouRecord));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述:集点活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:43:26 <br/>
	 * @param fRecord
	 * @return
	 */
	@RequestMapping("/init/list/focusPoint/list")
	@ResponseBody
	public Object focusPointRecord(FcouspointRecord fRecord){
		log.info("集点活动focusPointRecord"+fRecord);
		Pageable<FcouspointRecord> pageable = new Pageable<>(fRecord);
		try {
			pageable.setContent(sActivityService.focusRecordList(fRecord));
			pageable.setTotal(sActivityService.countFocusRecord(fRecord));
		} catch (Exception e) {
			log.error("加载数据异常",e);
		}
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
	public Object shutdown(SellerActivity sActivity){
		log.info("终止活动shutdown"+sActivity);
		Resultable resultable;
		try {
			Integer result =sActivityService.shutdown(sActivity);
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
}
