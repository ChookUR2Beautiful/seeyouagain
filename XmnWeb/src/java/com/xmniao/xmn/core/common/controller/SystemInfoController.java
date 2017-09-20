package com.xmniao.xmn.core.common.controller;

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
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.common.entity.TSystemInfo;
import com.xmniao.xmn.core.common.service.SystemInfoService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemInfoController
 * 
 * 类描述： 系统信息发布
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时58分46秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "common/systemInfo")
public class SystemInfoController extends BaseController {

	@Autowired
	private SystemInfoService systemInfoService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/systemInfoList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSystemInfo systemInfo) {
		Pageable<TSystemInfo> pageable = new Pageable<TSystemInfo>(systemInfo);
		pageable.setContent(systemInfoService.getList(systemInfo));
		pageable.setTotal(systemInfoService.count(systemInfo));
		return pageable;
	}

	@RequestMapping(value = "export")
	public void export(TSystemInfo systemInfo, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		systemInfo.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", systemInfoService.getList(systemInfo));
		doExport(request, response, "common/systemInfo.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = systemInfoService.delete(id.split(","));
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
		ModelAndView modelAndView = new ModelAndView("common/editSystemInfo");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSystemInfo systemInfo) {
		Resultable resultable = null;
		try {
			systemInfo.setSdate(new Date());
			systemInfo.setStatus(0);
			systemInfoService.add(systemInfo);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("common/editSystemInfo");
		modelAndView.addObject("isType", "update");
		try {
			TSystemInfo systemInfo = systemInfoService.getObject(new Long(id));
			this.log.info(systemInfo);
			modelAndView.addObject("systemInfo", systemInfo);
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
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSystemInfo  systemInfo) {
		Resultable resultable = null;
		try {
			systemInfoService.update(systemInfo);
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