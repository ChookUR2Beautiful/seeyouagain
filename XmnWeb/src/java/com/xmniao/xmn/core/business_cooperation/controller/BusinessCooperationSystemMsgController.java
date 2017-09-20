package com.xmniao.xmn.core.business_cooperation.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TSystemMsg;
import com.xmniao.xmn.core.common.service.SystemMsgService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemMsgController
 * 
 * 类描述： 系统信息推送
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时05分04秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/systemMsg")
public class BusinessCooperationSystemMsgController extends BaseController {

	@Autowired
	private SystemMsgService systemMsgService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/systemMsgList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSystemMsg systemMsg) {
		Pageable<TSystemMsg> pageable = new Pageable<TSystemMsg>(systemMsg);
		pageable.setContent(systemMsgService.getList(systemMsg));
		pageable.setTotal(systemMsgService.count(systemMsg));
		return pageable;
	}
	
	@RequestMapping(value = "export")
	public void export(TSystemMsg systemMsg, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		systemMsg.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", systemMsgService.getList(systemMsg));
		doExport(request, response, "common/systemMsg.xls", params);
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商系统信息推送删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("sid") String sid) {
		Resultable resultable = null;
		try {
			Integer resultNum = systemMsgService.delete(sid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSystemMsg");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商系统信息推送添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSystemMsg systemMsg) {
		Resultable resultable = null;
		try {
			systemMsg.setSdate(new Date());
			systemMsgService.add(systemMsg);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("sid") String sid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSystemMsg");
		modelAndView.addObject("isType", "update");
		try {
			TSystemMsg systemMsg = systemMsgService.getObject(new Long(sid));
			this.log.info(systemMsg);
			modelAndView.addObject("systemMsg", systemMsg);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商系统信息推送修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSystemMsg  systemMsg) {
		Resultable resultable = null;
		try {
			systemMsgService.update(systemMsg);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

}