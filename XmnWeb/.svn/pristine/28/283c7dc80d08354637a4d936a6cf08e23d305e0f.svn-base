/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerOrder;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerUser;
import com.xmniao.xmn.core.live_anchor.service.LiveExperienceofficerUserService;
import com.xmniao.xmn.core.thrift.exception.FailureException;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveExperienceofficerUserController
 * 
 * 类描述：会员体验卡管理
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月8日 下午3:04:29 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/experienceofficer/user")
public class LiveExperienceofficerUserController extends BaseController{


	@Autowired
	private LiveExperienceofficerUserService experienceofficerUserService;
	
	@RequestMapping("/init")
	public String init(){
		return "live_anchor/experienceofficer/experienceofficerUser";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(TExperienceofficerUser experienceofficerUser) throws FailureException, TException, ParseException{
			return experienceofficerUserService.getList(experienceofficerUser);
	}
		
	/*
	 * 添加会员体验卡
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(TExperienceofficerOrder experienceofficerOrder){
		Resultable resultable = new Resultable(true,"成功");
		try{
			experienceofficerUserService.addExperienceCard(experienceofficerOrder);
		}catch(Exception e){
			log.error("发放失败",e);
			return new Resultable(false,"发放失败");
		}
		return resultable;
	}

	@RequestMapping("/add/init")
	public ModelAndView addInit(){
		return new ModelAndView("live_anchor/experienceofficer/addExperienceCard");
	}
	
	@RequestMapping("/add/urs")
	@ResponseBody
	public Object addUrs(@RequestParam Map<String,Object> map){
		System.out.println("map:"+map);
		Map<String,Object> resultData = new HashMap<>();
		resultData.put("content", experienceofficerUserService.getUrsList(map));
		return resultData;
	}

	/*
	 * 管理会员体验卡
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(TExperienceofficerUser experienceofficerUser){
		Resultable resultable = new Resultable(true,"处理成功");
		try {
			experienceofficerUserService.enableExperienceCard(experienceofficerUser);
		} catch (TException e) {
			log.error("更新失败",e);
			return new Resultable(false, "更新失败");
		}
		return resultable;
	}

	@RequestMapping("/update/init")
	public ModelAndView updateInit(Long id){
		return new ModelAndView("live_anchor/experienceofficer/updateExperienceCard");
	}
}
