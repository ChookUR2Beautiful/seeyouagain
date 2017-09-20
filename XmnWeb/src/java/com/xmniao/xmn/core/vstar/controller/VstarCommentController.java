/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.entity.VstarComment;
import com.xmniao.xmn.core.vstar.service.VstarCommentService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarCommentController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月16日 下午1:48:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/vstarComment")
public class VstarCommentController extends BaseController{

	@Autowired
	private VstarCommentService commentService;
	
	@RequestMapping("/init")
	public Object init(){
		return "vstar/comment/commentList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object list(VstarComment comment){
		Pageable<VstarComment> pageable = new Pageable<>(comment);
		pageable.setContent(commentService.getList(comment));
		pageable.setTotal(commentService.count(comment));
		return pageable;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id){
		try {
			commentService.delete(id);
			return Resultable.success();
		} catch (Exception e) {
			log.error("删除评论失败",e);
			return Resultable.defeat();
		}
	}
	
	
}
