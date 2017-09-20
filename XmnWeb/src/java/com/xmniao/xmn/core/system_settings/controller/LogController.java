package com.xmniao.xmn.core.system_settings.controller;

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
import com.xmniao.xmn.core.system_settings.entity.TLog;
import com.xmniao.xmn.core.system_settings.service.LogService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LogController
 * 
 * 类描述： 操作日志
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日17时06分56秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="日志管理")
@Controller
@RequestMapping(value = "system_settings/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "system_settings/logList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLog log) {
		Pageable<TLog> pageable = new Pageable<TLog>(log);
		pageable.setContent(logService.getList(log));
		pageable.setTotal(logService.count(log));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除日志")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("logId") String logId) {
		Resultable resultable = null;
		try {
			Integer resultNum = logService.delete(logId.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"删除日志",logId,"删除","删除"};
				logService.fireLoginEvent(s,1);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"删除日志",logId,"删除","删除"};
			logService.fireLoginEvent(s,0);
		} 
		return resultable;
		
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("system_settings/editLog");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加日志")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TLog log) {
		Resultable resultable = null;
		try {
			logService.add(log);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("logId") String logId) {
		ModelAndView modelAndView = new ModelAndView("system_settings/editLog");
		modelAndView.addObject("isType", "update");
		try {
			TLog log = logService.getObject(new Long(logId));
			this.log.info(log);
			modelAndView.addObject("log", log);
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
	@RequestLogging(name="修改日志")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TLog  log) {
		Resultable resultable = null;
		try {
			logService.update(log);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
	}

}