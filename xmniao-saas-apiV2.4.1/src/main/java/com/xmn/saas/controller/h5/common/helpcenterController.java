package com.xmn.saas.controller.h5.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmn.saas.base.AbstractController;

@Controller(value = "h5-helpcenter-controller")
@RequestMapping("h5/helpcenter")
public class helpcenterController extends AbstractController{
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(){
		return "helpcenter/list";
	}
	
}
