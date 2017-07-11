package com.javmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringMVController {

	
	@RequestMapping("index")
	public Object init(){
		System.out.println(">>>>>>>>>> init");
		return "index";
	}
	
	
}
