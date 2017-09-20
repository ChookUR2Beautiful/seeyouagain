package com.xmniao.xmn.core.businessman.controller;

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
import com.xmniao.xmn.core.businessman.entity.TCommentPic;
import com.xmniao.xmn.core.businessman.service.CommentPicService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommentPicController
 * 
 * 类描述： 评论图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时42分58秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/commentPic")
public class CommentPicController extends BaseController {

	@Autowired
	private CommentPicService commentPicService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/commentPicList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TCommentPic commentPic) {
		Pageable<TCommentPic> pageable = new Pageable<TCommentPic>(commentPic);
		pageable.setContent(commentPicService.getList(commentPic));
		pageable.setTotal(commentPicService.count(commentPic));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="评论图片删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = commentPicService.delete(id.split(","));
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
		ModelAndView modelAndView = new ModelAndView("businessman/editCommentPic");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加评论图片")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TCommentPic commentPic) {
		Resultable resultable = null;
		try {
			commentPicService.add(commentPic);
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
		ModelAndView modelAndView = new ModelAndView("businessman/editCommentPic");
		modelAndView.addObject("isType", "update");
		try {
			TCommentPic commentPic = commentPicService.getObject(new Long(id));
			this.log.info(commentPic);
			modelAndView.addObject("commentPic", commentPic);
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
	@RequestLogging(name="修改评论图片")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TCommentPic  commentPic) {
		Resultable resultable = null;
		try {
			commentPicService.update(commentPic);
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