package com.xmniao.xmn.core.user_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.user_terminal.entity.TPostComment;
import com.xmniao.xmn.core.user_terminal.service.TPostCommentService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostCommentController
 * 
 * 类描述：帖子评论
 * 
 * 创建人： zhou'dekun
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value="user_terminal/tPost/tPostComment")
public class TPostCommentController extends BaseController {
	
	@Autowired
	private TPostCommentService tPostCommentService;
	
/*	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 *//*
	@RequestMapping(value = "tPost/init")
	public String tPostInit() {
		return "user_terminal/postCommentList";
	}	*/

	/**
	 * 
	 * list(帖子列表列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "post/init/list")
	@ResponseBody
	public Object list(TPostComment tPostComment) {
		Pageable<TPostComment> pageable = new Pageable<TPostComment>(tPostComment);
		pageable.setContent(tPostCommentService.getStatusOneList(tPostComment));
		pageable.setTotal(tPostCommentService.getStatusOneListcount(tPostComment));
		return pageable;
	}
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
/*	@RequestMapping(value = "report/init")
	public String reportInit() {
		return "user_terminal/postCommentList";
	}
	*/
	/**
	 * 
	 * list(举报列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "report/init/list")
	@ResponseBody
	public Object list2(TPostComment tPostComment) {
		Pageable<TPostComment> pageable = new Pageable<TPostComment>(tPostComment);
		pageable.setContent(tPostCommentService.getStatusOneList(tPostComment));
		pageable.setTotal(tPostCommentService.getStatusOneListcount(tPostComment));
		return pageable;
	}
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
/*	@RequestMapping(value = "recycle/init")
	public String recycleInit() {
		return "user_terminal/postCommentList";
	}*/
	
	/**
	 * 
	 * list(回收站列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "recycle/init/list")
	@ResponseBody
	public Object list3(TPostComment tPostComment) {
		Pageable<TPostComment> pageable = new Pageable<TPostComment>(tPostComment);
		pageable.setContent(tPostCommentService.getStatusOneList(tPostComment));
		pageable.setTotal(tPostCommentService.getStatusOneListcount(tPostComment));
		return pageable;
	}

}
