/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.Indiana;
import com.xmniao.xmn.core.fresh.entity.IndianaBout;
import com.xmniao.xmn.core.fresh.entity.IndianaDduonum;
import com.xmniao.xmn.core.fresh.service.IndianaBoutService;
import com.xmniao.xmn.core.fresh.service.IndianaDduonumService;
import com.xmniao.xmn.core.fresh.service.IndianaService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaController
 * 
 * 类描述： 一元夺宝
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月17日 上午11:56:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("fresh/indiana")
public class IndianaController extends BaseController{
	
	@Autowired
	private IndianaService indianaService;
	
	@Autowired
	private IndianaBoutService indianaBoutService;
	
	@Autowired
	private IndianaDduonumService indianaDduonumService;
	
	
	@RequestMapping("/init")
	public Object init(){
		return "fresh/indianaList";
	}
	
	@RequestMapping("/add/init")
	public Object addInit(Integer id){
		if(id==null){
			return "fresh/indianaEdit";
		}else{
			ModelAndView modelAndView = new ModelAndView("fresh/indianaEdit");
			IndianaBout bout = indianaBoutService.getObject(id.longValue());
			Indiana object = indianaService.getObject(bout.getActivityId().longValue());
			modelAndView.addObject("activity",object);
			return modelAndView;
		}
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(Indiana indiana){
		log.info("添加夺宝活动   "+indiana);
		Integer boutNum = indiana.getBoutNum();
		if(boutNum==null||boutNum<=0){
			return new Resultable(false,"期数有误");
		}
		try {
			if(indiana.getId()!=null){
				indianaService.updateIndiana(indiana);
			}else{
				indianaService.addIndiana(indiana);
			}
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	@RequestMapping("init/list")
	@ResponseBody
	public Object list(IndianaBout indianaBout){
		Pageable<IndianaBout> pageable = new Pageable<>(indianaBout);
		pageable.setContent(indianaBoutService.getList(indianaBout));
		pageable.setTotal(indianaBoutService.count(indianaBout));
		return pageable;
	}
	
	@RequestMapping("end")
	@ResponseBody
	public Object end(@RequestParam(required=true)Integer id){
		log.info("终止夺宝活动  id="+id);
		try {
			indianaService.end(id);
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(@RequestParam(required=true)Integer id){
		log.info("删除夺宝活动   id="+id);
		try {
			indianaService.delete(id);
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	@RequestMapping("winner/init")
	@ResponseBody
	public Object setWinner(@RequestParam("id")Integer id,Integer type){
		ModelAndView modelAndView = new ModelAndView("fresh/indianaWinnerList");
		modelAndView.addObject("boutId",id);
		modelAndView.addObject("type",type);
		return modelAndView;
	}
	
	@RequestMapping("winner/init/list")
	@ResponseBody
	public Object winnerList(IndianaDduonum indianaDduonum){
		Pageable<IndianaDduonum> pageable = new Pageable<>(indianaDduonum);
		pageable.setContent(indianaDduonumService.getList(indianaDduonum));
		pageable.setTotal(indianaDduonumService.count(indianaDduonum));
		return pageable;
	}
	
	
	@RequestMapping("winner/setWinner")
	@ResponseBody
	public Object setWinner(@RequestParam("id")Long id){
		log.info("设置默认中奖人   编号为:"+id);
		try {
			indianaDduonumService.setWinner(id);
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	@RequestMapping("winner/cancel")
	@ResponseBody
	public Object cancel(@RequestParam("id")Long id){
		try {
			indianaDduonumService.cancel(id);
			return new Resultable(true,"操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false,"操作失败");
		}
	}
	
	
}
