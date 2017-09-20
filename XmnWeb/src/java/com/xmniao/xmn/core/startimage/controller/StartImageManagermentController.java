package com.xmniao.xmn.core.startimage.controller;

import java.util.Date;

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
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.marketingmanagement.entity.ExtensionSet;
import com.xmniao.xmn.core.startimage.entity.StartImage;
import com.xmniao.xmn.core.startimage.service.StartImageService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：StartImageManagermentController
 * 
 * 类描述： 启动图管理
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年8月04日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="启动图管理")
@Controller
@RequestMapping(value = "startImageManagerment/startImageSet")
public class StartImageManagermentController extends BaseController {

	@Autowired
	private StartImageService startImageService;
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "start_image/allStartImages";
	}
	
	
	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(StartImage startImage) {
		this.log.info("StartImageManagermentController-->list startImage=" + startImage);
		Pageable<StartImage> pageable = new Pageable<StartImage>(startImage);
		pageable.setContent(startImageService.getStartImageList(startImage));
		pageable.setTotal(startImageService.getStartImageListcount(startImage));
		return pageable;
	}
	
	
	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("start_image/saveOrUpdateStartImage");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：wangzhimin
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加启动图")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(StartImage startImage) {
		Resultable resultable = null;
		try {
			Date date = new Date();
			startImage.setSdate(date);
			startImage.setEdate(date);
			startImageService.add(startImage);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表  remark 字段
			/*  
			 * 模型界面已去掉备注
			String remarkes = startImage.getRemarks();
			String str = "";
			if (remarkes.length() <= 12){
				str = remarkes;
			}else{
				str = remarkes.substring(0, 12)+"...";
			}
			//
			String[] s={"启动图",str,"新增"};
			startImageService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			*/
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表  remark 字段
			String remarkes = startImage.getRemarks();
			String str = "";
			if (remarkes.length() <= 12){
				str = remarkes;
			}else{
				str = remarkes.substring(0, 12)+"...";
			}
			//
			String[] s={"启动图",str,"新增"};
			startImageService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 
	 * 查看启动图详细详细页面初始化
	 * 
	 * @author：wangzhimin
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/check")
	public ModelAndView view(HttpServletRequest request, @RequestParam("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("start_image/startImageDetail");
		try {
			StartImage startImage = startImageService.getStartObject(id);
			this.log.info(startImage);
			modelAndView.addObject("startImage", startImage);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：wangzhimin
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("start_image/saveOrUpdateStartImage");
		modelAndView.addObject("isType", "update");
		try {
			StartImage startImage = startImageService.getStartObject(id);
			modelAndView.addObject("startImage", startImage);
			this.log.info(startImage);
		} catch (Exception e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	
	/**
	 * 
	 * update(修改)
	 * 
	 * @author：wangzhimin
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改启动图详细")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(StartImage  startImage) {
		Resultable resultable = null;
		try {
			startImage.setEdate(new Date());
			startImageService.update(startImage);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"启动图编号",String.valueOf(startImage.getId()),"修改","修改"};
			startImageService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"启动图编号",String.valueOf(startImage.getId()),"修改","修改"};
			startImageService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 
	 * update(修改)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public Object updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
		Resultable resultable = null;
		try {
			StartImage startImage = startImageService.getStartObject(id);
			startImage.setEdate(new Date());
			startImage.setStatus(status);
			startImageService.update(startImage);
			this.log.info("修改状态成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改状态异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
		}
		
		
		return resultable;
	}
}
