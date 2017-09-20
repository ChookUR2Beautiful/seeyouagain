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
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TComment;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.CommentService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommentController
 * 
 * 类描述： 商家评论
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月18日10时34分59秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SellerDao sellerDao;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/commentList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TComment comment) {
		Pageable<TComment> pageable = new Pageable<TComment>(comment);
		pageable.setContent(commentService.getList(comment));
		pageable.setTotal(commentService.count(comment));
		return pageable;
	}
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "seller/init")
	public String initSeller() {
		return "businessman/commentSellerList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "seller/init/list")
	@ResponseBody
	public Object listSeller(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		this.log.info("businessman/comment/seller/init/list");
		pageable.setContent(sellerDao.getCommnetSellerList(seller));
		pageable.setTotal(sellerDao.getCommnetSellerCount(seller));
		return pageable;
	}
	
	/**
	 * 导出数据
	 * @param comment
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TComment comment, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		comment.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", commentService.getList(comment));
		doExport(request, response, "businessman/comment.xls", params);
	}
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除评论")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("cid") String cid) {
		Resultable resultable = null;
		try {
			Integer resultNum = commentService.deleteByLogic(cid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				
				String[] s={"商家评论编号",cid,"删除","删除评论"};
				commentService.fireLoginEvent(s);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			
			String[] s={"商家评论编号",cid,"删除","删除评论"};
			commentService.fireLoginEvent(s,0);
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
		ModelAndView modelAndView = new ModelAndView("businessman/editComment");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加评论")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TComment comment) {
		Resultable resultable = null;
		try {
			commentService.add(comment);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("cid") String cid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editComment");
		modelAndView.addObject("isType", "update");
		try {
			TComment comment = commentService.getObject(new Long(cid));
			this.log.info(comment);
			modelAndView.addObject("comment", comment);
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
	@RequestLogging(name="修改评论")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TComment  comment) {
		Resultable resultable = null;
		try {
			commentService.update(comment);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			
			String[] s={"商家评论编号",comment.getCid().toString(),"修改","修改评论"};
			commentService.fireLoginEvent(s);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			
			String[] s={"商家评论编号",comment.getCid().toString(),"修改","修改评论"};
			commentService.fireLoginEvent(s,0);
		} finally {
			return resultable;
		}
	}

}