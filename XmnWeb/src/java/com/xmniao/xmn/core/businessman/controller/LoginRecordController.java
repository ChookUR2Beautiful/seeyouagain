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
import com.xmniao.xmn.core.businessman.entity.TLoginRecord;
import com.xmniao.xmn.core.businessman.service.LoginRecordService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LoginRecordController
 * 
 * 类描述： 商家账号登录记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日16时58分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/loginRecord")
public class LoginRecordController extends BaseController {

	@Autowired
	private LoginRecordService loginRecordService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/loginRecordList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLoginRecord loginRecord) {
		Pageable<TLoginRecord> pageable = new Pageable<TLoginRecord>(loginRecord);
		pageable.setContent(loginRecordService.getList(loginRecord));
		pageable.setTotal(loginRecordService.count(loginRecord));
		return pageable;
	}
	
	/**
	 * 导出列表
	 * @param loginRecord
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TLoginRecord loginRecord, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		loginRecord.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", loginRecordService.getList(loginRecord));
		doExport(request, response, "businessman/loginRecord.xls", params);
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
			Integer resultNum = loginRecordService.delete(id.split(","));
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
		ModelAndView modelAndView = new ModelAndView("businessman/editLoginRecord");
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
	public Object add(TLoginRecord loginRecord) {
		Resultable resultable = null;
		try {
			loginRecordService.add(loginRecord);
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
		ModelAndView modelAndView = new ModelAndView("businessman/editLoginRecord");
		modelAndView.addObject("isType", "update");
		try {
			TLoginRecord loginRecord = loginRecordService.getObject(new Long(id));
			this.log.info(loginRecord);
			modelAndView.addObject("loginRecord", loginRecord);
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
	public Object update(TLoginRecord  loginRecord) {
		Resultable resultable = null;
		try {
			loginRecordService.update(loginRecord);
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