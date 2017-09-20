package com.xmniao.xmn.core.userData_statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.userData_statistics.entity.TUserApplicationOverview;
import com.xmniao.xmn.core.userData_statistics.service.UserApplicationOverviewService;


@Controller
@RequestMapping(value = "userData_statistics/userApplicationOverview")
public class UserApplicationOverviewController extends BaseController{
	
	@Autowired
	private UserApplicationOverviewService applicationOverviewService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		try {
			List<TUserApplicationOverview> list = applicationOverviewService.getList();
			mv.addObject("list", list);
		} catch (Exception e) {
			this.log.error("获取用户统计 应用概括异常", e);
		}
		mv.addObject("requestInit","userData_statistics/userApplicationOverview/init/list");
		mv.setViewName("userData_statistics/userApplicationOverview/userApplicationOverviewList");
		return mv;
	}
}
