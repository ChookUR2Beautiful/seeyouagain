/**
 * 
 */
package com.xmniao.xmn.core.manor.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.manor.entity.ManorLevel;
import com.xmniao.xmn.core.manor.service.ManorLevelService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb_Manor
 * 
 * 类名称：ManorLevelController
 * 
 * 类描述： 庄园等级
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月12日 下午4:44:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="庄园等级管理")
@Controller
@RequestMapping("manor/level")
public class ManorLevelController extends BaseController{

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ManorLevelService manorLevelService;
	
	
	@RequestMapping("init")
	private String init(){
		return "golden_manor/manorLevelList";
	}
	
	@RequestMapping("init/list")
	@ResponseBody
	private Object initList(ManorLevel manorLevel){
		Pageable<ManorLevel> pageable = new Pageable<>(manorLevel);
		pageable.setContent(manorLevelService.getList(manorLevel));
		pageable.setTotal(manorLevelService.count(manorLevel));
		return pageable;
	}
	
	@RequestMapping(value={"add/init","update/init"})
	private ModelAndView addInit(ManorLevel manorLevel){
		ModelAndView mv = new ModelAndView("golden_manor/manorLevelAdd");
		if(manorLevel.getId()!=null){
			ManorLevel level = manorLevelService.getObject(manorLevel);
			mv.addObject("manorLevel",level);
			mv.addObject("isadd",false);
		}else{
			ManorLevel level = new ManorLevel();
			ManorLevel maxLevel =manorLevelService.getMaxObject();
			Integer maxNo = maxLevel==null?0:maxLevel.getNo();
			level.setNo(maxNo+1);
			mv.addObject("manorLevel",level);
			mv.addObject("isadd",true);
		}
		return mv;
	}
	
	@RequestMapping("add")
	@ResponseBody
	private Object add(ManorLevel manorLevel){
		try{
			manorLevelService.add(manorLevel);
			return new Resultable(true,"添加成功！");
		}catch (Exception e){
			log.error("添加等级失败",e);
			return new Resultable(false,"添加失败！");
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	private Object update(ManorLevel manorLevel){
		try{
			manorLevelService.update(manorLevel);
			return new Resultable(true,"修改成功！");
		}catch (Exception e){
			log.error("修改等级失败",e);
			return new Resultable(false,"修改失败！");
		}
	}
	
	@RequestMapping("activate/setting/init")
	private ModelAndView manorActivityInit(){
		ModelAndView mv = new ModelAndView("golden_manor/manorLevelAdd");
		// 表: b_manor_activate_manage
		List<Map<String,String>> settingList = manorLevelService.getActivateSetting();
		mv.addObject(settingList);
		return mv;
	}
	
	@RequestMapping("activate/setting")
	@ResponseBody
	private Object manorActivitysetting(){
		try{
			manorLevelService.saveActivateSetting();
			return new Resultable(true,"保存成功!");
		}catch(Exception e){
			log.error("保存失败",e);
			return new Resultable(false, "保存失败!");
		}
	}
	
	@RequestMapping("init/coupon")
	private String initCoupon(){
		return "forward:/coupon/generate/init/list.jhtml";
	}
}
