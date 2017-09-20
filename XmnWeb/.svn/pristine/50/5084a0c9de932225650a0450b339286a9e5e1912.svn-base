/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.UnsignedSeller;
import com.xmniao.xmn.core.businessman.service.UnsignedSellerService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UnsignedSellerController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月12日 下午2:29:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("businessman/unsignedSeller")
public class UnsignedSellerController extends BaseController {
	
	@Autowired
	private UnsignedSellerService unsignedSellerService;
	
	
	@RequestMapping("/init")
	public Object init(){
		return "businessman/unsignedSellerList";
	}
	
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(UnsignedSeller unsignedSeller){
		Pageable<UnsignedSeller> pageable = new Pageable<>(unsignedSeller);
		pageable.setContent(unsignedSellerService.getList(unsignedSeller));
		pageable.setTotal(unsignedSellerService.count(unsignedSeller));
		return pageable;
	}

	
	@RequestMapping("/updateState")
	@ResponseBody
	public Object updateState(@RequestParam(required=true)Integer unsignedSellerid,@RequestParam(required=true)Integer type){
		int i=unsignedSellerService.updateState(unsignedSellerid,type);
		if(i>0){
			return Resultable.success("成功");
		}else{
			return Resultable.defeat("操作失败");
		}
		
	}
	
}
