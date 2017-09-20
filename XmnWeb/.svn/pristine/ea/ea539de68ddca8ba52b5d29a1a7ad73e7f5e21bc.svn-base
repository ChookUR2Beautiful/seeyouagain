package com.xmniao.xmn.core.user_terminal.controller;

import java.util.List;

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
import com.xmniao.xmn.core.business_cooperation.entity.TSubject;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.user_terminal.entity.TPost;
import com.xmniao.xmn.core.user_terminal.entity.TPostPic;
import com.xmniao.xmn.core.user_terminal.service.TPostPicService;
import com.xmniao.xmn.core.user_terminal.service.TPostService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostController
 * 
 * 类描述：香蜜客圈子发帖表
 * 
 * 创建人： zhou'dekun
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="用户端管理")
@Controller
@RequestMapping(value="user_terminal/tPost")
public class TPostController extends BaseController {
	
	@Autowired
	private TPostService  tPostService;
	
	@Autowired
	private TPostPicService tPostPicService;//帖子图片service
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "user_terminal/postList";
	}

	/**
	 * 
	 * list(帖子列表列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "post/init")
	@ResponseBody
	public Object list(TPost tPost) {
		Pageable<TPost> pageable = new Pageable<TPost>(tPost);
		pageable.setContent(tPostService.getZeroList(tPost));
		pageable.setTotal(tPostService.getZeroListCount(tPost));
		return pageable;
	}
	
	/**
	 * 
	 * list(举报列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "report/init")
	@ResponseBody
	public Object list2(TPost tPost) {
		Pageable<TPost> pageable = new Pageable<TPost>(tPost);
		pageable.setContent(tPostService.getTwoList(tPost));
		pageable.setTotal(tPostService.getTwoListCount(tPost));
		return pageable;
	}
	
	/**
	 * 
	 * list(回收站数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "recycle/init")
	@ResponseBody
	public Object list3(TPost tPost) {
		Pageable<TPost> pageable = new Pageable<TPost>(tPost);
		pageable.setContent(tPostService.getOneList(tPost));
		pageable.setTotal(tPostService.getOneListCount(tPost));
		return pageable;
	}
	
	/**
	 * 删除（帖子列表更改状态）
	 * @param tPost
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="帖子列表 >> 删除帖子")
	@ResponseBody
	@RequestMapping(value = "post/updatePostStatus")
	public Object updatePostStatus(TPost tPost) {	
		this.log.info("TPostController-->updatePostStatus");		
		Object[] objects = {};
		Resultable resultable = null;
		try {
			if (null != tPost.getTids()) {
				objects = tPost.getTids().split(",");
			}
			tPostService.updatePostStatus(objects, tPost);				
			resultable = new Resultable(true, "操作成功！");
			//写入日志记录表
			String[] s={"帖子列表  编号",String.valueOf(tPost.getTids()),"删除","删除"};
			tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"帖子列表 编号",String.valueOf(tPost.getTids()),"删除","删除"};
			tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally{
			return resultable;
		}
		
	}
	
	/**
	 * 删除（举报帖子列表更改状态）
	 * @param tPost
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="举报列表 >> 删除帖子")
	@ResponseBody
	@RequestMapping(value = "report/updatePostStatusByjb")
	public Object updatePostStatusByjb(TPost tPost) {	
		this.log.info("TPostController-->updatePostStatusByjb");		
		Object[] objects = {};
		Resultable resultable = null;
		try {
			if (null != tPost.getTids()) {
				objects = tPost.getTids().split(",");
			}                  		
			tPostService.updatePostStatus(objects, tPost);				
			resultable = new Resultable(true, "操作成功！");
			//写入日志记录表
			String[] s={"举报列表 编号",String.valueOf(tPost.getTids()),"删除","删除"};
			tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"举报列表 编号",String.valueOf(tPost.getTids()),"删除","删除"};
			tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally{
			return resultable;
		}
		
	}
	
	/**
	 * 打回（举报帖子列表更改状态）
	 * @param tPost
	 * @return
	 */
	@RequestLogging(name="举报列表 >> 打回帖子")
	@ResponseBody
	@RequestMapping(value = "report/updatePostStatusByHome")
	public Object updatePostStatusByHome(TPost tPost) {	
		this.log.info("TPostController-->updatePostStatusByHome");		
		Object[] objects = {};
		if (null != tPost.getTids()) {
			objects = tPost.getTids().split(",");
		}                  		
		tPostService.updatePostStatus(objects, tPost);				
		return new Resultable(true, "操作成功！");
	}
	
	/**
	 * 恢复（回收站中1改成0）
	 * @param tPost
	 * @return
	 */
	@RequestLogging(name="回收站 >> 恢复帖子")
	@ResponseBody
	@RequestMapping(value = "recycle/updatePostStatusToOne")
	public Object updatePostStatusToOne(TPost tPost) {	
		this.log.info("TPostController-->updatePostStatusToOne");		
		Object[] objects = {};
		if (null != tPost.getTids()) {
			objects = tPost.getTids().split(",");
		}                  		
		tPostService.updatePostStatus(objects, tPost);				
		return new Resultable(true, "操作成功！");
	}
	
	/**
	 * 回收站中彻底删除
	 * @param request
	 * @param tid
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="回收站 >> 彻底删除帖子")
	@RequestMapping(value = "recycle/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("tids") String tids) {
		Resultable resultable = null;
		try {
			Integer resultNum = tPostService.delete(tids.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"彻底删除帖子",tids,"删除","删除"};
				tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"彻底删除帖子",tids,"删除","删除"};
			tPostService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 根据帖子tid查询(帖子列表)
	 * @param tid
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "tPostComment/post/init")
	public ModelAndView getPostBytid(HttpServletRequest request, @RequestParam("tid") Long tid) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editPost");
		try {
			TPost tposts=	tPostService.getObject(tid);
			this.log.info(tposts);
			//查图片
			List<TPostPic> piclist= tPostPicService.getListpic(tid);
			modelAndView.addObject("tposts", tposts);
			modelAndView.addObject("piclist", piclist);
			modelAndView.addObject("type",UserConstants.POST_COMMENT_1);
		} catch (Exception e) {
			this.log.error("查看初始异常", e);
		} finally {
			return modelAndView;
		 }				
		}
	
	/**
	 * 根据帖子tid查询(举报列表)
	 * @param tid
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "tPostComment/report/init")
	public ModelAndView getPostBytidjb(HttpServletRequest request, @RequestParam("tid") Long tid) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editPost");
		try {
			TPost tposts=	tPostService.getObject(tid);
			this.log.info(tposts);
			//查图片
			List<TPostPic> piclist= tPostPicService.getListpic(tid);
			modelAndView.addObject("tposts", tposts);
			modelAndView.addObject("piclist", piclist);
			modelAndView.addObject("type",UserConstants.POST_COMMENT_2);
		} catch (NumberFormatException e) {
			this.log.error("查看初始异常", e);
		} finally {
			return modelAndView;
		 }				
		}
	
	/**
	 * 根据帖子tid查询(回收站列表)
	 * @param tid
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "tPostComment/recycle/init")
	public ModelAndView getPostBytidhsz(HttpServletRequest request, @RequestParam("tid") Long tid) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editPost");
		try {
			TPost tposts=	tPostService.getObject(tid);
			this.log.info(tposts);
			//查图片
			List<TPostPic> piclist= tPostPicService.getListpic(tid);
			modelAndView.addObject("tposts", tposts);
			modelAndView.addObject("piclist", piclist);
			modelAndView.addObject("type",UserConstants.POST_COMMENT_3);
		} catch (NumberFormatException e) {
			this.log.error("查看初始异常", e);
		} finally {
			return modelAndView;
		 }				
		}
		
}
