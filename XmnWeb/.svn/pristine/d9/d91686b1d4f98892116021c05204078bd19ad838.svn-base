package com.xmniao.xmn.core.userData_statistics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.userData_statistics.entity.TMerberAction;
import com.xmniao.xmn.core.userData_statistics.service.MerberActionService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


@Controller
@RequestMapping(value = "userData_statistics/merberAction")
public class MerberActionController  extends BaseController{
	
	@Autowired
	private MerberActionService actionService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "userData_statistics/merberAction/init/list");
		mv.addObject("order", 0);
		mv.setViewName("userData_statistics/merberAction/merberActionList");
		return mv;
	}
	@RequestMapping("init/list")
	public ModelAndView list(TMerberAction merberAction){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map=null;
		try{
			map = actionService.getList(merberAction);
		}catch(Exception e){
			this.log.error("获取用户统计 会员行为 列表异常",e);
		}
		mv.addObject("map", map);
		mv.setViewName("userData_statistics/merberAction/merberActionTable");
		return mv;
	}
	
}
