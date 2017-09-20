package com.xmniao.xmn.core.userData_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.userData_statistics.service.ChannelAnalysisService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


@Controller
@RequestMapping(value = "userData_statistics/channelAnalysis")
public class ChannelAnalysisController  {
	
	@Autowired
	private ChannelAnalysisService analysisService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "userData_statistics/channelAnalysis/init/list");
		mv.setViewName("userData_statistics/channelAnalysis/channelAnalysisList");
		return mv;
	}
	
}
