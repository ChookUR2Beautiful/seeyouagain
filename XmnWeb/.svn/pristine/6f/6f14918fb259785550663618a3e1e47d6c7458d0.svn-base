package com.xmniao.xmn.core.business_cooperation.controller;

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
import com.xmniao.xmn.core.business_cooperation.entity.TSubject;
import com.xmniao.xmn.core.business_cooperation.entity.TSubjectReply;
import com.xmniao.xmn.core.business_cooperation.service.SubjectReplyService;
import com.xmniao.xmn.core.business_cooperation.service.SubjectService;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SubjectController
 * 
 * 类描述： 话题
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时52分25秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/subject")
public class SubjectController extends BaseController {

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectReplyService subjectReplyService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/subjectList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSubject subject) {
		Pageable<TSubject> pageable = new Pageable<TSubject>(subject);
		pageable.setContent(subjectService.getList(subject));
		pageable.setTotal(subjectService.count(subject));
		return pageable;
	}

	/**
	 * 导出列表
	 * 
	 * @param subject
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TSubject subject, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		subject.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", subjectService.getList(subject));
		doExport(request, response, "business_cooperation/subject.xls", params);
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name = "话题删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestParam("subjectid") String subjectid) {
		Resultable resultable = null;
		try {
			Integer resultNum = subjectService.delete(subjectid.split(","));
			if (resultNum > PartnerConstants.RESULTNUM) {
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
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSubject");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name = "话题添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSubject subject) {
		Resultable resultable = null;
		try {
			subjectService.add(subject);
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
	public ModelAndView updateInit(HttpServletRequest request,
			@RequestParam("subjectid") String subjectid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSubject");
		modelAndView.addObject("isType", "update");
		try {
			TSubject subject = subjectService.getObject(new Long(subjectid));
			this.log.info(subject);
			modelAndView.addObject("subject", subject);
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
	@RequestLogging(name = "话题修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSubject subject) {
		Resultable resultable = null;
		try {
			subjectService.update(subject);
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
	 * updateInit(查看初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "view/init")
	public ModelAndView viewInit(HttpServletRequest request,
			@RequestParam("subjectid") String subjectid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/viewSubject");
		modelAndView.addObject("isType", "update");
		try {
			TSubject subject = subjectService.getObject(new Long(subjectid));
			this.log.info(subject);
			modelAndView.addObject("subject", subject);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * list(话题回复数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "view/init/subjectReplyList")
	@ResponseBody
	public Object list(TSubjectReply subjectReply) {
		Pageable<TSubjectReply> pageable = new Pageable<TSubjectReply>(subjectReply);
		pageable.setContent(subjectReplyService.getList(subjectReply));
		pageable.setTotal(subjectReplyService.count(subjectReply));
		return pageable;
	}

}