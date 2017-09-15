package com.xmniao.xmn.core.api.controller.pageh5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerWelfareApi {
	
	@RequestMapping(value="/sellerWelfare")
	public String list(Model model){
		   
		return "live/welfare";			
	}
}