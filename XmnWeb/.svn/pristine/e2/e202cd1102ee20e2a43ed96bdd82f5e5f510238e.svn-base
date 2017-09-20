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
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;
import com.xmniao.xmn.core.marketingmanagement.service.HotWordsService;
import com.xmniao.xmn.core.marketingmanagement.util.MarketConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：HotWordsController
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
@RequestLogging(name = "热门搜索设置")
@RequestMapping(value = "marketingManagement/hotWords")
public class HotWordsController extends BaseController {

	@Autowired
	private HotWordsService hotWordsService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：caoyingde
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "marketingManagement/hotWordsList";
	}

	/**
	 * 获取订单列表
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(HotWords hotWords) {
		this.log.info("hotWordsController-->list hotWords=" + hotWords);
		Pageable<HotWords> pageable = new Pageable<HotWords>(hotWords);
		pageable = hotWordsService.getHotWordsList(hotWords);
		return pageable;
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：caoyingde
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView(
				"marketingManagement/editHotWord");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name = "添加热门搜索设置")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(HotWords hotWords) {
		Resultable resultable = null;
		try {
			// 添加到日志记录表 remark 字段
			String word = hotWords.getHotWord();
			String message = hotWordsService.isHotWordsOfArea(hotWords);
			if (message.equals("success")) {
				hotWordsService.add(hotWords);
				this.log.info("添加成功");
				resultable = new Resultable(true, "操作成功");
				String str = "";
				if (word.length() <= MarketConstants.WORD_LENGTH) {
					str = word;
				} else {
					str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH) + "...";
				}
				//
				String[] s = { "引流商家设置", str, "新增" };
				hotWordsService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
			}else{
				resultable = new Resultable(false, "操作失败,"+message+"");
				// 写入 日志记录表
				String[] s = { "热门搜索设置编号", String.valueOf(hotWords.getHid()), "修改",
						"修改" };
				hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMB);
			}
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			// 添加到日志记录表 remark 字段
			String word = hotWords.getHotWord();
			String str = "";
			if (word.length() <= MarketConstants.WORD_LENGTH) {
				str = word;
			} else {
				str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH) + "...";
			}
			//
			String[] s = { "引流商家设置", str, "新增" };
			hotWordsService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
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
	public ModelAndView updateInit(HttpServletRequest request,
			@RequestParam("hid") String hid) {
		ModelAndView modelAndView = new ModelAndView(
				"marketingManagement/editHotWord");
		modelAndView.addObject("isType", "update");
		try {
			HotWords hotWords = hotWordsService.getObject(new Long(hid));
			this.log.info(hotWords);
			modelAndView.addObject("hotWords", hotWords);
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
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name = "修改热门搜索设置")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(HotWords hotWords) {
		Resultable resultable = null;
		try {
			String message = hotWordsService.isChangeHotWords(hotWords);
			String message2 = hotWordsService.isHotWordsOfArea(hotWords);
			if (message.equals("success")) {
				hotWordsService.update(hotWords);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
				// 写入 日志记录表
				String[] s = { "热门搜索设置编号", String.valueOf(hotWords.getHid()), "修改",
						"修改" };
				hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMA);
			}else if(message2.equals("success")){
				hotWordsService.update(hotWords);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
				// 写入 日志记录表
				String[] s = { "热门搜索设置编号", String.valueOf(hotWords.getHid()), "修改",
						"修改" };
				hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMA);
			}else{
				resultable = new Resultable(false,""+message+"");
				// 写入 日志记录表
				String[] s = { "热门搜索设置编号", String.valueOf(hotWords.getHid()), "修改",
						"修改" };
				hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMB);
			}
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			// 写入 日志记录表
			String[] s = { "热门搜索设置编号", String.valueOf(hotWords.getHid()), "修改",
					"修改" };
			hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：caoyingde
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name = "删除热门搜索设置")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,
			@RequestParam("hid") String hid) {
		Resultable resultable = null;
		try {
			Integer resultNum = hotWordsService.delete(hid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				// 写入 日志记录表
				String[] s = { "热门搜索编号", hid, "删除", "删除" };
				hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			// 写入 日志记录表
			String[] s = { "热门搜索编号", hid, "删除", "删除" };
			hotWordsService.fireLoginEvent(s, MarketConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
}