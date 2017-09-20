/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.IndianaRobot;
import com.xmniao.xmn.core.fresh.service.IndianaRobotService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RobotController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月1日 下午4:03:26 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("fresh/robot")
public class RobotController extends BaseController{
	
	@Autowired
	private IndianaRobotService indianaRobotService;
	
	@RequestMapping("/init")
	public Object init(){
		return "fresh/robotList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object list(IndianaRobot indianaRobot){
		Pageable<IndianaRobot> pageable = new Pageable<>(indianaRobot);
		pageable.setContent(indianaRobotService.getList(indianaRobot));
		pageable.setTotal(indianaRobotService.count(indianaRobot));
		return pageable;
	}
	
	@RequestMapping("/add/init")
	public Object addInit(){
		return "fresh/robotAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(IndianaRobot indianaRobot){
		log.info("批量添加机器人  数量:"+indianaRobot.getAddNum());
		try {
			indianaRobotService.addRobot(indianaRobot);
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.info(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	@RequestMapping("/update/init")
	public Object updateInit(Integer id){
		ModelAndView modelAndView = new ModelAndView("fresh/robotEdit");
		IndianaRobot indianaRobot = indianaRobotService.getObject(id.longValue());
		modelAndView.addObject("robot",indianaRobot);
		return modelAndView;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(IndianaRobot indianaRobot){
		log.info("修改夺宝机器人  "+indianaRobot);
		try {
			indianaRobotService.update(indianaRobot);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.info(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam(required=true)String  ids){
		log.info("批量删除机器人    ids="+ids);
		try {
			indianaRobotService.delete(ids.split(","));
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping(value="/deleteAll",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteAll(){
		try {
			indianaRobotService.deleteAll();
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}

}
