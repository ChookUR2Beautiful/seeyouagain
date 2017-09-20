package com.xmniao.xmn.core.business_cooperation.controller;

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
import com.xmniao.xmn.core.business_cooperation.entity.TSubjectReply;
import com.xmniao.xmn.core.business_cooperation.service.SubjectReplyService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SubjectReplyController
 * 
 * 类描述： 话题回复
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月20日14时58分36秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/subjectReply")
public class SubjectReplyController extends BaseController {

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
		return "business_cooperation/subjectReplyList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSubjectReply subjectReply) {
		Pageable<TSubjectReply> pageable = new Pageable<TSubjectReply>(subjectReply);
		pageable.setContent(subjectReplyService.getList(subjectReply));
		pageable.setTotal(subjectReplyService.count(subjectReply));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="话题回复删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("rid") String rid) {
		Resultable resultable = null;
		try {
			Integer resultNum = subjectReplyService.delete(rid.split(","));
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
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSubjectReply");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="话题回复添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSubjectReply subjectReply) {
		Resultable resultable = null;
		try {
			subjectReplyService.add(subjectReply);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("rid") String rid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editSubjectReply");
		modelAndView.addObject("isType", "update");
		try {
			TSubjectReply subjectReply = subjectReplyService.getObject(new Long(rid));
			this.log.info(subjectReply);
			modelAndView.addObject("subjectReply", subjectReply);
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
	@RequestLogging(name="话题回复修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSubjectReply  subjectReply) {
		Resultable resultable = null;
		try {
			subjectReplyService.update(subjectReply);
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