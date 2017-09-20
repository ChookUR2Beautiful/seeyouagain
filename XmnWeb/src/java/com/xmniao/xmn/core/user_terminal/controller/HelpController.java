package com.xmniao.xmn.core.user_terminal.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.entity.THelpInfo;
import com.xmniao.xmn.core.user_terminal.entity.THelpItem;
import com.xmniao.xmn.core.user_terminal.service.HelpService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：HelpController
 *
 * 类描述：帮助信息管理
 * 
 * 创建人：ChenBo
 * 
 * 创建时间：2016年8月11日下午5:48:42
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@RequestLogging(name="帮助条目管理")
@Controller
@RequestMapping(value="user_terminal/help_manage")
public class HelpController extends BaseController{
	private  Logger log = LoggerFactory.getLogger(HelpController.class);
	
	@Autowired
	private HelpService helpService; 
	
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午5:41:54
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/helpInfoList";
	}
	
	/**
	 * 
	 * 方法描述：获取帮助列表
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(THelpInfo helpInfo){
		Pageable<THelpInfo> pageable = new Pageable<THelpInfo>(helpInfo);
		List<THelpInfo> list = helpService.getHelpList(helpInfo);
		long count = helpService.getHelpCount(helpInfo);
		pageable.setContent(list);
		pageable.setTotal(count);
 		return pageable;
	}
	
	/**
	 * 方法描述：编辑帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "update/init")
	@ResponseBody
	public Object updateInit(Integer id){
		ModelAndView mv = new ModelAndView("user_terminal/editHelpInfo");
		mv.addObject("isType", "update");
		THelpInfo helpInfo = helpService.getHelpInfo(id);
		mv.addObject("helpInfo", helpInfo);
		return mv;
	}	
	
	/**
	 * 方法描述：更新帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(THelpInfo helpInfo){
		Resultable resultable = new Resultable();
		try {
			helpService.updateHelpInfo(helpInfo);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	/**
	 * 方法描述：添加帮助
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "add/init")
	@ResponseBody
	public Object addInit(THelpInfo helpInfo){
		ModelAndView mv = new ModelAndView("user_terminal/editHelpInfo");
		mv.addObject("isType", "add");
		return mv;
	}
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(THelpInfo helpInfo){
		Resultable resultable = new Resultable();
		try {
			 helpService.insterHelpInfo(helpInfo);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：删除帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午6:20:04
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(Integer id){
		Resultable resultable = new Resultable();
		try {
			helpService.deleteHelpInfo(id);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}


	/**
	 * 
	 * 方法描述：在此处添加方法描述
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午5:41:54
	 * @return
	 */
	@RequestMapping(value="item/init")
	public String initItem(){
		return "user_terminal/helpItemList";
	}

	/**
	 * 
	 * 方法描述：获取帮助列表
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "item/list")
	@ResponseBody
	public Object itemList(HttpServletRequest request){
		THelpItem helpItem = new THelpItem();
		helpItem.setLimit(-1);
		Pageable<THelpItem> pageable = new Pageable<THelpItem>(helpItem);
		List<THelpItem> list = helpService.getHelpItemList(helpItem);
		pageable.setContent(list);
 		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：获取帮助列表
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "item/init/list")
	@ResponseBody
	public Object initItemList(THelpItem helpItem){
		Pageable<THelpItem> pageable = new Pageable<THelpItem>(helpItem);
		List<THelpItem> list = helpService.getHelpItemList(helpItem);
		long count = helpService.getHelpItemCount(helpItem);
		pageable.setContent(list);
		pageable.setTotal(count);
 		return pageable;
	}
	
	/**
	 * 方法描述：编辑帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "item/update/init")
	@ResponseBody
	public Object updateIniItem(Integer id){
		ModelAndView mv = new ModelAndView("user_terminal/editHelpItem");
		mv.addObject("isType", "update");
		THelpItem helpItem = helpService.getHelpItem(id);
		mv.addObject("helpItem", helpItem);
		return mv;
	}	
	
	/**
	 * 方法描述：更新帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "item/update")
	@ResponseBody
	public Object updateItem(THelpItem helpItem){
		Resultable resultable = new Resultable();
		try {
			helpService.updateHelpItem(helpItem);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	/**
	 * 方法描述：添加帮助
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "item/add/init")
	@ResponseBody
	public Object addInitItem(THelpItem helpItem){
		ModelAndView mv = new ModelAndView("user_terminal/editHelpItem");
		mv.addObject("isType", "add");
		return mv;
	}
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "item/add")
	@ResponseBody
	public Object addItem(THelpItem helpItem){
		Resultable resultable = new Resultable();
		try {
			 helpService.insterHelpItem(helpItem);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：删除帮助信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月11日下午6:20:04
	 * @param id
	 * @return
	 */
	@RequestMapping(value="item/delete")
	@ResponseBody
	public Object deleteItem(Integer id){
		Resultable resultable = new Resultable();
		try {
			helpService.deleteHelpItem(id);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}}
