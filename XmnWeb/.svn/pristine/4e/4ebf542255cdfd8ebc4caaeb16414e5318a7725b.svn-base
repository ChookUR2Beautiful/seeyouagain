package com.xmniao.xmn.core.user_terminal.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.entity.TTopicComment;
import com.xmniao.xmn.core.user_terminal.service.TTopicCommentService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
@Controller
@RequestMapping(value = "user_terminal/topicComment")
public class TTopicCommentController extends BaseController {
	
	@Autowired
	private TTopicCommentService topicCommentService;
	
	@RequestMapping(value = "init")
	public String init(){
		return "user_terminal/topicCommentList";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TTopicComment topicComment){
		Pageable<TTopicComment> pageable = new Pageable<TTopicComment>(topicComment);
	
		pageable.setContent(topicCommentService.getList(topicComment));
		pageable.setTotal(topicCommentService.count(topicComment));
		return pageable;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestParam("id") String id){
		Resultable resultable = null;
		try {
			Integer resultNum = topicCommentService.delete(id.split(","));
			if(resultNum >0){
				this.log.info("删除成功！");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.info("删除异常",e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"删除异常",id,"删除","删除"};
			topicCommentService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
			
		} finally{
			return resultable;
		}
	}
}
