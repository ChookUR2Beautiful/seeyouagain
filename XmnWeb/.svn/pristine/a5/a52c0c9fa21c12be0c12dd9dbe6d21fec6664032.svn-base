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
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.common.service.AdvertisingService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AdvertisingController
 * 
 * 类描述： 广告轮播
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时27分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "common/advertising")
public class AdvertisingController extends BaseController {

	@Autowired
	private AdvertisingService advertisingService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/advertisingList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAdvertising advertising) {
		Pageable<TAdvertising> pageable = new Pageable<TAdvertising>(advertising);
		pageable.setContent(advertisingService.getList(advertising));
		pageable.setTotal(advertisingService.count(advertising));
		return pageable;
	}

	/**
	 * 导出列表
	 * @param advertising
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TAdvertising advertising, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		advertising.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", advertisingService.getList(advertising));
		doExport(request, response, "common/advertising.xls", params);
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
			Integer resultNum = advertisingService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"广告编号",id,"删除","删除"};
				advertisingService.fireLoginEvent(s,1);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"广告编号",id,"删除","删除"};
			advertisingService.fireLoginEvent(s,1);
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
		ModelAndView modelAndView = new ModelAndView("common/editAdvertising");
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
	public Object add(TAdvertising advertising) {
		Resultable resultable = null;
		try {
			advertisingService.add(advertising);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表  remark 字段
			String word = advertising.getContent();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//
			String[] s={"广告轮播",str,"新增"};
			advertisingService.fireLoginEvent(s);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表  remark 字段
			String word = advertising.getContent();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//
			String[] s={"广告轮播",str,"新增"};
			advertisingService.fireLoginEvent(s);
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
		ModelAndView modelAndView = new ModelAndView("common/editAdvertising");
		modelAndView.addObject("isType", "update");
		try {
			TAdvertising advertising = advertisingService.getObject(new Long(id));
			this.log.info(advertising);
			modelAndView.addObject("advertising", advertising);
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
	public Object update(TAdvertising  advertising) {
		Resultable resultable = null;
		try {
			advertisingService.update(advertising);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"广告编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,1);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"广告编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,1);
		} finally {
			return resultable;
		}
	}

}