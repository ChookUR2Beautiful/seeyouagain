package com.xmniao.xmn.core.business_statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.business_statistics.entity.TApplicationOverview;
import com.xmniao.xmn.core.business_statistics.service.ApplicationOverviewService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


@Controller
@RequestMapping(value ="business_statistics/applicationOverview")
public class ApplicationOverviewController extends BaseController {
	
	@Autowired
	private ApplicationOverviewService applicationOverviewService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView  mv = new ModelAndView();
		List<TApplicationOverview> appList =null;
		try{
			appList = applicationOverviewService.getDataList();
		}catch(Exception e){
			log.error("查询应用概况初始化异常", e);
		}
		mv.addObject("requestInit", "businessStatistics/ApplicationOverview/init");
		mv.addObject("appList",appList );
		mv.setViewName("business_statistics/applicationOverview");
		return mv;
	} 
	
	
}
