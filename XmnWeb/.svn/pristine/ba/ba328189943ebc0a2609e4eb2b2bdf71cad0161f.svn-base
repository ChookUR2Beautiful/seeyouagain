/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerConfig;
import com.xmniao.xmn.core.live_anchor.service.LiveExperienceofficerUserService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveExperienceofficerConfigController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月8日 下午3:07:46 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/experienceofficer/setting")
public class LiveExperienceofficerSettingController {

	@Autowired
	private LiveExperienceofficerUserService experienceofficerUserService;
	
	@RequestMapping("/init")
	public ModelAndView experienceSettingInit(){
		ModelAndView mv = new ModelAndView("live_anchor/experienceofficer/experienceSetting");
		TExperienceofficerConfig config = new TExperienceofficerConfig();
		config.setType(1);
		mv.addObject("config",experienceofficerUserService.getExperienceSetting(config));
		return mv;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object experienceSetting(TExperienceofficerConfig config){
		Resultable resultable = new Resultable(true,"成功");
		config.setUpdateTime(new Date());
		config.setType(1);
//		config.setTitle("美食体验卡");
		experienceofficerUserService.experienceSetting(config);
		return resultable;
	}
}
