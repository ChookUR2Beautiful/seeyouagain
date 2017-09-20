package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.businessman.service.AgioRecordService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AgioRecordController
 * 
 * 类描述： 折扣设置记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日17时11分35秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理 >> 折扣设置")
@Controller
@RequestMapping(value = "businessman/agioRecord")
public class AgioRecordController extends BaseController {

	@Autowired
	private AgioRecordService agioRecordService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/agioRecordList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAgioRecord agioRecord) {
		Pageable<TAgioRecord> pageable = new Pageable<TAgioRecord>(agioRecord);
		pageable.setContent(agioRecordService.getList(agioRecord));
		pageable.setTotal(agioRecordService.count(agioRecord));
		return pageable;
	}

	/**
	 * 导出列表
	 * @param agioRecord
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TAgioRecord agioRecord, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		agioRecord.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", agioRecordService.getList(agioRecord));
		doExport(request, response, "businessman/agioRecord.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除折扣")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = agioRecordService.delete(id.split(","));
			if (resultNum >SellerConstants.COMMON_PARAM_Z) {
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
		ModelAndView modelAndView = new ModelAndView("businessman/editAgioRecord");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加折扣")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAgioRecord agioRecord) {
		Resultable resultable = null;
		try {
			agioRecordService.add(agioRecord);
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
		ModelAndView modelAndView = new ModelAndView("businessman/editAgioRecord");
		modelAndView.addObject("isType", "update");
		try {
			TAgioRecord agioRecord = agioRecordService.getObject(new Long(id));
			this.log.info(agioRecord);
			modelAndView.addObject("agioRecord", agioRecord);
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
	@RequestLogging(name="修改折扣")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAgioRecord  agioRecord) {
		Resultable resultable = null;
		try {
			agioRecordService.update(agioRecord);
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