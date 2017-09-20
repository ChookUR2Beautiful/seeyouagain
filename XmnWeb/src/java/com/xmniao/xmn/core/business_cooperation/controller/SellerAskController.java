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
import com.xmniao.xmn.core.business_cooperation.entity.TSellerAsk;
import com.xmniao.xmn.core.business_cooperation.service.SellerAskService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAskController
 * 
 * 类描述： 产品问答
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时48分28秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/sellerAsk")
public class SellerAskController extends BaseController {

	@Autowired
	private SellerAskService sellerAskService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/sellerAskList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerAsk sellerAsk) {
		Pageable<TSellerAsk> pageable = new Pageable<TSellerAsk>(sellerAsk);
		pageable.setContent(sellerAskService.getList(sellerAsk));
		pageable.setTotal(sellerAskService.count(sellerAsk));
		return pageable;
	}

	/**
	 * 导出列表
	 * @param sellerAsk
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TSellerAsk sellerAsk, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		sellerAsk.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", sellerAskService.getList(sellerAsk));
		doExport(request, response, "business_cooperation/sellerAsk.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="产品问答删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerAskService.delete(aid.split(","));
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
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSellerAsk");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="产品问答添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSellerAsk sellerAsk) {
		Resultable resultable = null;
		try {
			sellerAsk.setSdate(new Date());
			sellerAskService.add(sellerAsk);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSellerAsk");
		modelAndView.addObject("isType", "update");
		try {
			TSellerAsk sellerAsk = sellerAskService.getObject(new Long(aid));
			this.log.info(sellerAsk);
			modelAndView.addObject("sellerAsk", sellerAsk);
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
	@RequestLogging(name="产品问答修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSellerAsk  sellerAsk) {
		Resultable resultable = null;
		try {
			sellerAskService.update(sellerAsk);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
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
	@RequestMapping(value = "/view/init")
	public ModelAndView viewInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/viewSellerAsk");
		modelAndView.addObject("isType", "update");
		try {
			TSellerAsk sellerAsk = sellerAskService.getObject(new Long(aid));
			this.log.info(sellerAsk);
			modelAndView.addObject("sellerAsk", sellerAsk);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

}