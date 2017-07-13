package com.javmin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javmin.entity.User;
import com.javmin.service.IUserService;

@Controller
public class SpringMVController {
	
	@Resource(name="userService")
	private IUserService userService; 
	
	@RequestMapping("index")
	public Object init(Model model){
		System.out.println(">>>>>>>>>> init");
		User user = userService.getUserById(1);
		model.addAttribute("user",user);
		return "index";
	}
	
	
}
