package com.xmniao.xmn.core.userData_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.userData_statistics.service.AccessedPageService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


@Controller
@RequestMapping(value = "userData_statistics/accessedPage")
public class AccessedPageController  {
	
	@Autowired
	private AccessedPageService accessedPageService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "userData_statistics/accessedPage/init/list");
		mv.setViewName("userData_statistics/accessedPage/accessedPageList");
		return mv;
		
	}
	
	
}
