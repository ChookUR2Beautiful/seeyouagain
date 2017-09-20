/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerActivity;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.LiveExperienceofficerActivityService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveExperienceofficerActivityController
 * 
 * 类描述： 美食体验官活动场次Controller
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月8日 下午2:27:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/experienceofficer/activity")
public class LiveExperienceofficerActivityController extends BaseController{

	@Autowired
	private LiveExperienceofficerActivityService experienceofficerActivityService;
	
	@RequestMapping("/init")
	public String init(){
		return "live_anchor/experienceofficer/experienceActivity";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(TExperienceofficerActivity experienceofficerActivity){
		Pageable<TExperienceofficerActivity> pageable = new Pageable<TExperienceofficerActivity>(experienceofficerActivity);
//		Integer state = experienceofficerActivity.getActivityState();
//		if(state!=null){
//			
//			if(state==1){//未开始
//				experienceofficerActivity.setEnrollTime(new Date());	//开抢时间大于当前时间
//				experienceofficerActivity.setActivityState(1);	//状态正常
//			}else if(state==2){//抢位中
//				experienceofficerActivity.setEnrollTime(new Date());	//开抢时间小于当前时间
//				experienceofficerActivity.setActivityState(1);	//状态正常
//			}else if(state==3){//已售罄
//				experienceofficerActivity.setRemainderNum(0);//已售完
//				experienceofficerActivity.setActivityState(1);	//状态正常			
//			}else if(state==4){//已结束
//				experienceofficerActivity.setPlanEndDate(new Date());
//				experienceofficerActivity.setActivityState(1);	//状态正常		
//			}else if(state==5){//已取消
//				experienceofficerActivity.setActivityState(2);	//状态取消
//			}
//			experienceofficerActivity.setNow(new Date());
//		}
		Map<String,Object> map = new HashMap<>();
		map.put("state", experienceofficerActivity.getActivityState());
		map.put("now", new Date());
		map.put("sellername", experienceofficerActivity.getSellername());
		map.put("planStartDate", experienceofficerActivity.getPlanStartDate());
		map.put("zhiboTitle", experienceofficerActivity.getZhiboTitle());
		map.put("limit", experienceofficerActivity.getLimit());
		map.put("page", experienceofficerActivity.getPage());
		pageable.setContent(experienceofficerActivityService.getListByMap(map));
		pageable.setTotal(experienceofficerActivityService.countByMap(map));
		return pageable;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(TExperienceofficerActivity experienceofficerActivity){
		Resultable resultable = new Resultable(true,"添加成功");
		experienceofficerActivity.setCreateTime(new Date());
		experienceofficerActivity.setActivityState(1);
		experienceofficerActivity.setRemainderNum(experienceofficerActivity.getLimitNum());
		try{
			experienceofficerActivityService.add(experienceofficerActivity);
		}catch(Exception e){
			return new Resultable(false, "同一场直播只可配置一场体验活动");
		}
		return resultable;
	}

	@RequestMapping("/add/init")
	public String addInit(){
		return "live_anchor/experienceofficer/editExperienceActivity";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(TExperienceofficerActivity activity){
		Resultable resultable = new Resultable(true,"修改成功");
		if(experienceofficerActivityService.countEnroll(activity.getId())>activity.getLimitNum()){
			return new Resultable(false, "抢购名额不能小于当前已抢名额");
		}
		experienceofficerActivityService.update(activity);
		return resultable;
	}

	@RequestMapping("/update/init")
	public ModelAndView updateInit(Integer id){
		ModelAndView mv = new ModelAndView("live_anchor/experienceofficer/editExperienceActivity");
		TExperienceofficerActivity activity=experienceofficerActivityService.getObject((long)id);
		mv.addObject("activity",activity);
		mv.addObject("isAdd",false);
		return mv;
	}
	
	@RequestMapping("/cancel/init")
	public ModelAndView cancelInit(Integer id){
		ModelAndView mv = new ModelAndView("live_anchor/experienceofficer/cancelExperienceActivity");
		
		TExperienceofficerActivity activity=experienceofficerActivityService.getObject((long)id);
		int hasSold = 0;
		if(experienceofficerActivityService.countEnroll(id)>0){
			hasSold=1;
		}
		
		mv.addObject("activity",activity);
		mv.addObject("hasSold",hasSold);
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：取消并更换已报名的场次
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月18日下午8:42:03 <br/>
	 * @param cancelMap
	 * @return
	 */
	@RequestMapping("/cancel")
	@ResponseBody
	public Resultable cancel(@RequestParam Map<String,Object> cancelMap){
		
		try{
			experienceofficerActivityService.cancelActivity(cancelMap);
		}catch(Exception e){
			log.error("取消失败",e);
			return new Resultable(false,e.getMessage());
		}

		return new Resultable(true,"取消成功");
	}
	
	@RequestMapping("/init/liveRecord")
	public Object initLiveRecord(TLiveRecord liveRecord ,RedirectAttributes attr,HttpServletRequest request){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		liveRecord.setLiveStartType(0);//后台通告
		liveRecord.setZhiboType(0);
		liveRecord.setStartTime(sdf.format(new Date()));//当天或之后开播的通告
		attr.addFlashAttribute(liveRecord);  
        return "redirect:/liveRecord/manage/init/list.jhtml";  
	}
}
