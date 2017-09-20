package com.xmniao.xmn.core.common.controller;

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
import com.xmniao.xmn.core.common.entity.TAppVersion;
import com.xmniao.xmn.core.common.service.AppVersionService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AppVersionController
 * 
 * 类描述： 版本
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时51分56秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "common/appVersion")
@RequestLogging(name="app版本管理")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/appVersionList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAppVersion appVersion) {
		Pageable<TAppVersion> pageable = new Pageable<TAppVersion>(appVersion);
		pageable.setContent(appVersionService.getList(appVersion));
		pageable.setTotal(appVersionService.count(appVersion));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="app版本删除")
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		
		try {
			Integer resultNum = appVersionService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"app版本编号",id,"删除操作","删除"};
			appVersionService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "add/init")
	public ModelAndView addInit(@RequestParam("type")Integer type) {
		ModelAndView modelAndView = new ModelAndView(typeView(type));
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * 类型视图
	 * @param type
	 * @return
	 */
	private String typeView(int type){
		String viewName=null;
		switch (type) {
		case 1:
			viewName="common/editAppVersionAndroid";
			break;
		case 2:
			viewName="common/editAppVersionIOS";
			break;
		case 3:
			viewName="common/editAppVersionWP";
			break;
		case 4:
			viewName="common/editAppVersionOther";
			break;
		
		}
		return viewName;
		
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="app版本添加")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(TAppVersion appVersion) {
		Resultable resultable = null;
		try {
			appVersionService.addNewVersion(appVersion);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"版本类型",getType(appVersion.getVtype()),"新增"};
			appVersionService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}
	
	private String getType(int t){
		String type ="";
		switch (t) {
		case 1:
			type ="Android";
			break;

		case 2:
			type ="Ios";
			break;


		case 3:
			type ="Wp";
			break;
		case 4:
			type ="其他";
			break;
		}
		return type;
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isType", "update");
		try {
			TAppVersion appVersion = appVersionService.getObject(new Long(id));
			modelAndView.setViewName(typeView(appVersion.getVtype()));
			this.log.info(appVersion);
			modelAndView.addObject("appVersion", appVersion);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="app版本修改")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(TAppVersion  appVersion) {
		Resultable resultable = null;
		try {
			appVersionService.updateVersion(appVersion);
			this.log.info("app版本修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("app版本修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"app版本编号",appVersion.getId().toString(),"修改操作","修改"};
			appVersionService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

}