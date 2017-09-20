/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.ValueCard;
import com.xmniao.xmn.core.businessman.entity.VipValueCard;
import com.xmniao.xmn.core.businessman.service.ValueCardService;
import com.xmniao.xmn.core.businessman.service.VipValueCardService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UserValueCardController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月21日 上午11:37:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestLogging(name="会员储值卡管理")
@RequestMapping("businessman/vipValueCard")
public class VipValueCardController extends BaseController{
	
	@Autowired
	private VipValueCardService vvCardService;
	
	@Autowired
	private ValueCardService vCardService;
	
	@RequestMapping("/init")
	public String init(){
		return "/businessman/valueCard/vipValueCardList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getList(VipValueCard vvCard){
		Pageable<VipValueCard> pageable = new Pageable<>(vvCard);
		try {
			List<VipValueCard> list = vvCardService.getList(vvCard);
			pageable.setContent(list);
			pageable.setTotal(vvCardService.countValueCard(vvCard));
		} catch (Exception e) {
			log.error("查询会员储值卡列表异常");
		}
		return pageable;
	}
	
	@RequestMapping("/applySellerInit")
	public Object childSeller(@RequestParam(value="sellerid") Integer sellerid,@RequestParam(value="type") Integer type){
		ModelAndView mv = new ModelAndView();
		ValueCard vCard = new ValueCard();
		vCard.setSellerType(type);
		vCard.setSellerid(sellerid);
		mv.setViewName("/businessman/valueCard/vipValueCardChildList");
		mv.addObject("vCard",vCard);
		return mv;
	} 
	
	@RequestMapping("/applySellerInit/list")
	@ResponseBody
	public Object getApplySellerList(ValueCard vCard){
		log.info("获取商家所有自商家列表："+vCard);
		Pageable<TSeller> pageable = new Pageable<>(new TSeller());
		List<TSeller> childSellerList = vCardService.getChildSellerList(vCard);
		pageable.setContent(childSellerList);
		pageable.setTotal(vCardService.countChildSellerNum(vCard));
		return pageable;
	}
}
