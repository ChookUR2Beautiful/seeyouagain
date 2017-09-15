package com.xmniao.xmn.core.api.controller.pageh5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.base.SessionTokenService;

@Controller
public class NewWelfareApi {
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="/newerWelfare")
	public String list(String sessionToken,Model model){
		String uid = sessionTokenService.getStringForValue(sessionToken)+"";
		if (uid!=null && !"".equals(uid)) {
			model.addAttribute("uid", uid);
		}
		return "homewelfare/index";			
	}
}