package com.xmniao.xmn.core.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TTrade;
import com.xmniao.xmn.core.common.service.TradeService;
import com.xmniao.xmn.core.system_settings.service.CategoryService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TradeController
 * 
 * 类描述： 经营(行业)类别
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年12月01日11时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "common/trade")
public class TradeController extends BaseController {

	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/tradeList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object list(TTrade trade) {
		Pageable<TTrade> pageable = new Pageable<TTrade>(trade);
		pageable.setContent(tradeService.getList(trade));
		pageable.setTotal(tradeService.count(trade));
		return pageable;
	}
	
	@RequestMapping(value = "getLdAll",method=RequestMethod.POST)
	@ResponseBody
	public Object getAll() {
		return categoryService.getLdAll();
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("tid") String tid) {
		Resultable resultable = null;
		try {
			Integer resultNum = tradeService.delete(tid.split(","));
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
		ModelAndView modelAndView = new ModelAndView("common/editTrade");
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
	public Object add(TTrade trade) {
		Resultable resultable = null;
		try {
			tradeService.add(trade);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("tid") String tid) {
		ModelAndView modelAndView = new ModelAndView("common/editTrade");
		modelAndView.addObject("isType", "update");
		try {
			TTrade trade = tradeService.getObject(new Long(tid));
			this.log.info(trade);
			modelAndView.addObject("trade", trade);
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
	public Object update(TTrade  trade) {
		Resultable resultable = null;
		try {
			tradeService.update(trade);
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