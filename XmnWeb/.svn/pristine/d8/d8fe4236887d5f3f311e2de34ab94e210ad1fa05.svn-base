package com.xmniao.xmn.core.marketingmanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.marketingmanagement.entity.ExtensionSet;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.service.ExtensionSetService;
import com.xmniao.xmn.core.marketingmanagement.util.MarketConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：ExtensionSetController
 * 
 * 类描述：查询推广设置列表
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestLogging(name = "引流商家列表")
@RequestMapping(value = "marketingManagement/extensionSet")
public class ExtensionSetController extends BaseController {

	@Autowired
	private ExtensionSetService extensionSetService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：caoyingde
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "marketingManagement/extensionSetList";
	}

	/**
	 * 获取订单列表
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(ExtensionSet extensionSet) {
		this.log.info("extensionSetController-->list extensionSet=" + extensionSet);
		Pageable<ExtensionSet> pageable = new Pageable<ExtensionSet>(extensionSet);
		pageable = extensionSetService.getExtensionSetList(extensionSet);
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除引流商家列表")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = extensionSetService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"热门搜索编号",id,"删除","删除"};
				extensionSetService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"热门搜索编号",id,"删除","删除"};
			extensionSetService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editExtensionSet");
		modelAndView.addObject("isType", "update");
		try {
			ExtensionSet extensionSet = extensionSetService.getObject(new Long(id));
			modelAndView.addObject("extensionSet", extensionSet);
			this.log.info(extensionSet);
		} catch (Exception e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改引流商家列表")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ExtensionSet  extensionSet) {
		Resultable resultable = null;
		try {
				extensionSetService.update(extensionSet);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"热门搜索编号",String.valueOf(extensionSet.getId()),"修改","修改"};
				extensionSetService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"热门搜索编号",String.valueOf(extensionSet.getId()),"修改","修改"};
			extensionSetService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
	
}