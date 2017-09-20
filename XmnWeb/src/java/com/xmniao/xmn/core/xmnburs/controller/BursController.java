/**
 * 
 */
package com.xmniao.xmn.core.xmnburs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：UserController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月12日 上午10:47:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/burs")
public class BursController {

	@Autowired
	private BursService bursService;
	
	@RequestMapping("/getUserChoose")
	@ResponseBody
	public Object  getUserChoose(Burs user){
		Pageable<Burs> pageable = new Pageable<>(user);
		pageable.setContent(bursService.getUrsList(user));
		return pageable;
	}
	
}
