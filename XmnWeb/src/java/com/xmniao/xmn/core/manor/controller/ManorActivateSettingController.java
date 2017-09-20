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
@RequestLogging(name="庄园激活管理")
@Controller
@RequestMapping("manor/activate")
public class ManorActivateSettingController extends BaseController{

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ManorLevelService manorLevelService;
	

	@RequestMapping("setting/init")
	private ModelAndView manorActivityInit(){
		ModelAndView mv = new ModelAndView("golden_manor/manorActivateSetting");
		// 表: b_manor_activate_manage
		List<Map<String,String>> settingList = manorLevelService.getActivateSetting();
		mv.addObject("list",settingList);
		return mv;
	}
	
	@RequestMapping("setting")
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
}
