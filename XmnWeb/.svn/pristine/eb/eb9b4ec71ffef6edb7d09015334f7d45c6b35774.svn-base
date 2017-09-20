package com.xmniao.xmn.core.user_terminal.controller;

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
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.entity.TSellerNotice;
import com.xmniao.xmn.core.user_terminal.service.TSellerNoticeService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
@RequestLogging(name="用户须知")
@Controller
@RequestMapping(value="user_terminal/tsellernotice")
public class TSellerNoticeController extends BaseController {
	@Autowired
	private TSellerNoticeService tSellerNoticeService;
	
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/sellernotice/SellerNoticeServiceList";
	}
	
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object list(TSellerNotice tSellerNotice){
		Pageable<TSellerNotice> pageable = new Pageable<TSellerNotice>(tSellerNotice);
		tSellerNoticeService.putPageable(tSellerNotice, pageable);
		return pageable;	
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月8日 上午11:21:00
	 * 描述：用户须知增加页面初始化
	 * @return
	 */
	@RequestMapping(value="/add/init")
	public ModelAndView addInit(){
		ModelAndView modelAndView = new ModelAndView("user_terminal/sellernotice/editSellerNotice");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
    /**
     * 
     * @author dong'jt
     * 创建时间：2015年10月8日 下午4:25:29
     * 描述：用户须知新增
     * @param request
     * @param tSellerNotice
     * @return
     */
	@SuppressWarnings("finally")
	@RequestLogging(name="用户须知新增")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(HttpServletRequest request,TSellerNotice tSellerNotice) {
		Resultable resultable = null;
		try {
			resultable=tSellerNoticeService.addTSellerNotice(request,tSellerNotice);
		} catch (Exception e) {
			resultable = new Resultable(false, "新增异常！");
			this.log.error("用户须知新增："+e);
			tSellerNoticeService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("用户须知新增", e, new Object[]{request,tSellerNotice}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月8日 下午4:25:06
	 * 描述：用户须知修改数据初始化
	 * @param request
	 * @param id
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/sellernotice/editSellerNotice");
		try {
			tSellerNoticeService.updateTSellerNoticeInitData(modelAndView,id);//更新前初始化数据
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
    /**
     * 
     * @author dong'jt
     * 创建时间：2015年10月8日 下午4:24:45
     * 描述：用户须知修改
     * @param request
     * @param tSellerNotice
     * @return
     */
	@SuppressWarnings("finally")
	@RequestLogging(name="用户须知修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(HttpServletRequest request,TSellerNotice tSellerNotice) {
		Resultable resultable = null;
		try {
			resultable=tSellerNoticeService.updateTSellerNoticeData(request,tSellerNotice);
		} catch (Exception e) {
			resultable = new Resultable(false, "修改异常！");
			this.log.error("活动banner修改："+e);
			tSellerNoticeService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("活动banner修改", e, new Object[]{request,tSellerNotice}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
   
}
