package com.xmniao.xmn.core.user_terminal.controller;

import java.util.ArrayList;
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
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.entity.TTopic;
import com.xmniao.xmn.core.user_terminal.entity.TTopicComment;
import com.xmniao.xmn.core.user_terminal.entity.TopicGlobal;
import com.xmniao.xmn.core.user_terminal.service.TTopicCommentService;
import com.xmniao.xmn.core.user_terminal.service.TTopicService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * @项目名称：XmnWeb
 * 
 * @类名称：TTopicController.java
 * 
 * @类描述：寻蜜鸟话题类
 * 
 * @创建者：yang'xu
 * 
 * @创建时间：2014-12-23 13：47：46
 * 
 * @Copyright © 广东寻蜜鸟网络科技有限公司
 */
@RequestLogging(name="用户端管理")
@Controller
@RequestMapping(value = "user_terminal/topic")
public class TTopicController extends BaseController {
	
	@Autowired
	private TTopicService topicService;
	@Autowired
	private TTopicCommentService commentService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "user_terminal/topicList";
	}
	
	/*
	 * 获取话题列表所有信息(包含条件筛选查询结果)
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TTopic topic){
		
		Pageable<TTopic> pageable = new Pageable<TTopic>(topic);
		List<TTopic> list = new ArrayList<>();
		Long count=0l;
		try{
			list = topicService.getList(topic);
			count = topicService.count(topic);
		}catch(Exception e){
			this.log.error("获取列表异常",e);
		}finally{
			pageable.setContent(list);
			pageable.setTotal(count);
		}
		return pageable;
	}
	
	/*
	 * 评论数据初始化
	 */
	@RequestMapping(value = "init/commentList")
	@ResponseBody
	public Object list(TTopicComment topicComment){
		topicComment.setPid(0);
		topicComment.setType(0);
		Pageable<TTopicComment> pageable = new Pageable<TTopicComment>(topicComment);
	
		pageable.setContent(commentService.getList(topicComment));
		pageable.setTotal(commentService.count(topicComment));
		return pageable;
	}
	
	/*
	 * 回复数据初始化
	 */
	@RequestMapping(value = "init/replyList")
	@ResponseBody
	public Object replyList(TTopicComment reply){
		Pageable<TTopicComment> pageable = new Pageable<TTopicComment>(reply);
	
		pageable.setContent(commentService.getList(reply));
		pageable.setTotal(commentService.count(reply));
		return pageable;
	}

	/*
	 * 删除记录
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除话题")
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestParam("id") String id){
		Resultable resultable = null;
		try {
			Integer resultNum = topicService.deleteTopic(id.split(","));
//			String  deleteNum = id;
			if(resultNum >0){
				this.log.info("删除成功！");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"成长记编号",id,"删除","删除"};
				topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.info("删除异常",e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"成长记编号",String.valueOf(topicService.deleteTopic(id.split(","))),"删除","删除"};
			topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally{
			return resultable;
		}
	}
	/*
	 * 删除某一条评论     deleteComment()
	 * 级联删除该评论的所有方法   
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除评论或回复")
	@RequestMapping(value = "deleteComment")
	@ResponseBody
	public Object deleteComment(HttpServletRequest request,@RequestParam("commentId") String id){
		Resultable resultable = null;
		try {
			//删除评论或者回复记录
			TTopicComment comm = commentService.getObject(new Long(id));
			Integer  topicId = comm.getTopicId();
			//拿到评论的状态值，为1为显示，0为不显示，如果状态为0，即使删除，评论的数目也不做更改
			Integer commStatus = comm.getStatus();
			//删除评论时，修改该话题的评论数   先拿到该话题信息
			TTopic topic = topicService.getObject(new Long(topicId));
			Integer commPid = comm.getPid();
			Integer commType = comm.getType();
			Integer resultNum = topicService.deleteComment(id.split(","));
			Integer replyNum = topicService.deleteReply(id.split(","));
			
			if( commPid == UserConstants.TOPIC_BLANK && commType ==UserConstants.TOPIC_BLANK ){
				this.log.info("删除记录: "+ resultNum +" 条评论");
				Integer commentNum = topic.getCommentNum();
				if(commentNum!=UserConstants.TOPIC_BLANK && commStatus == UserConstants.TOPIC_SHOW){
					topic.setCommentNum(commentNum-resultNum);
					topicService.update(topic);
				}
				this.log.info("话题回复数更新，话题回复减少" + resultNum + "条");
				this.log.info("删除: "+ replyNum +" 条回复");
			}else{
				this.log.info("删除记录: "+ resultNum +" 条回复");
			}
			
			if(resultNum >0){
				this.log.info("删除成功！");
				resultable = new Resultable(true, "操作成功");
				String[] s={"评论或回复编号",id,"删除","删除"};
				topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.info("删除异常",e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"评论或回复编号",id,"删除","删除"};
			topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally{
			return resultable;
		}
	}
	/*
	 * 修改话题的显示状态，将是否显示放置在删除评论权限下，
	 * 所以权限配置出现改变时，是否显示请求路径也应做相应更改
	 */
	@ResponseBody
	@RequestMapping(value="deleteComment/isShowComm")
	public Object isShowComm(HttpServletRequest request,@RequestParam("commentId") String id,@RequestParam("status") Integer status){
		TTopicComment comment = commentService.getObject(new Long(id));
		TTopic topic = topicService.getObject(new Long(comment.getTopicId()));
		if (status ==0){
			comment.setStatus(1);
			this.log.info("评论显示增加1条！");
			commentService.update(comment);
			Integer commNum = topic.getCommentNum()+1;
			topic.setCommentNum(commNum);
			topicService.update(topic);
			this.log.info("更改话题评论数,评论增加1条！");
		}else{
			comment.setStatus(0);
			this.log.info("评论显示减少1条！");
			commentService.update(comment);
			Integer num = topic.getCommentNum();
			if (num >0){
				Integer commNum = num - 1;
				topic.setCommentNum(commNum);
				topicService.update(topic);
			}
			
			this.log.info("更改话题评论数,评论减少1条！");
		}
		
		return "sucess";
	}
	
	/*
	 * 添加话题初始页面
	 */
	@RequestMapping(value = "add/init")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("user_terminal/updateTopic");
		mv.addObject("isType", "add");
		return mv;
	}
	
	/*
	 * 添加话题页面
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="新增话题")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add (TopicGlobal topicGlobal){
		Resultable resultable = null;
		try{
			resultable=topicService.addTopicGlobalService(topicGlobal);//添加
		}catch (Exception e) {
			this.log.error("新增话题："+e);
			topicService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("新增话题", e, new Object[]{topicGlobal}).getMessage()),UserConstants.FIRELOGIN_ERROR);
		}
		return resultable;
	}
	/*
	 * 修改话题初始化页面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request,@RequestParam("id") String id){
		ModelAndView mv = new ModelAndView("user_terminal/updateTopic");
		mv.addObject("isType", "update");
		mv.addObject("pageType", "update");
		try{
			TopicGlobal topicGlobal = topicService.getTopicInfo(new Long(id));
			this.log.info(topicGlobal);
			mv.addObject("topicGlobal", topicGlobal);
		} catch(NumberFormatException e){
			this.log.info("修改时获取数据异常",e);
		} finally{
			return mv;
		}
	}
	
	/*
	 * 修改话题
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改话题")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(TopicGlobal topicGlobal){
		Resultable resultable = null;
		try{
			resultable=topicService.updateTopicGlobalServer(topicGlobal);
		 }catch (Exception e) {
			this.log.error("修改话题："+e);
			topicService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("修改话题", e, new Object[]{topicGlobal}).getMessage()),UserConstants.FIRELOGIN_ERROR);
		 } 
		return resultable;
	}
	
	/*
	 * 查看话题、话题图片、话题回复页面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "check/init")
	public ModelAndView checkInit(HttpServletRequest request,@RequestParam("id") String id){
		ModelAndView mv = new ModelAndView("user_terminal/checkComm");
		try{
			TopicGlobal topicGlobal = topicService.getTopicInfo(new Long(id));
			this.log.info(topicGlobal);
			mv.addObject("pageType", "check");
			mv.addObject("topicGlobal", topicGlobal);
			mv.addObject("commentList", topicGlobal.getTopicCommentList());
			mv.addObject("topicComment", new TTopicComment());
		} catch(NumberFormatException e){
			this.log.info("修改异常",e);
		} finally{
			return mv;
		}
	}
	
	/*
	 * 添加评论
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加评论")
	@RequestMapping(value = "check/addComment")
	@ResponseBody
	public Object addComment(TopicGlobal topicGlobal,HttpServletRequest request){
		Resultable resultable = null;
		try {
			TTopicComment topicComment = topicService.addComment(topicGlobal,ResultUtil.getCurrentUser(request));
			this.log.info(topicComment);
			this.log.info("查看话题-->添加评论成功");
			resultable = new Resultable(true, "操作成功",topicComment);
		} catch (Exception e) {
			this.log.info("查看话题-->添加评论异常");
			resultable = new Resultable(false, "操作失败");
		} finally{
			return resultable;
		}
	}
	
	/**
	 * 增加对评论的回复 
	 */
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "reply/init")
	public ModelAndView replyInit(HttpServletRequest request,@RequestParam("pid") String pid){
		ModelAndView mv = new ModelAndView("user_terminal/replyList");
		try{
			//获取回复列表需要的 参数对象comment:pid,type
			TTopicComment param = new TTopicComment();
			param.setPid(new Integer(pid));
			param.setType(1);
			List<TTopicComment> replyList = commentService.getList(param);
			this.log.info(replyList);
			this.log.info("查看话题-->获取回复列表成功");
			//获取父对象---评论
			TTopicComment fatherComment = commentService.getObject(new Long(pid));
			this.log.info(fatherComment);
			this.log.info("获取回复对象-->评论成功");
			//初始化回复对象（TTopicComment）
			TTopicComment reply = new TTopicComment();
			reply.setPid(new Integer(pid));
			reply.setType(UserConstants.TOPIC_TYPE_1);//type:0  代表是话题评论，1 代表回复评论。评论是针对话题的，  回复是指向评论的
			reply.setTopicId(fatherComment.getTopicId());
			mv.addObject("reply", reply);
			mv.addObject("fatherComment",fatherComment);
			mv.addObject("commList", replyList);
			mv.addObject("pageType", "check");
		} catch(NumberFormatException e){
			this.log.info("获取话题回复页面失败",e);
		} finally{
			return mv;
		}
	}
	
	/*
	 * 添加回复
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加回复")
	@RequestMapping(value = "reply")
	@ResponseBody
	public Object addReply(TTopicComment reply,HttpServletRequest request){
		Resultable resultable = null;
		try {
			
			TTopicComment topicComment = topicService.addReply(reply, ResultUtil.getCurrentUser(request));
			this.log.info("添加回复对象: "+topicComment);
			
			this.log.info("回复成功");
			resultable = new Resultable(true, "操作成功",topicComment);
			String word = "";
			if(reply.getContent().length()>12){
				 word = reply.getContent().substring(0, 12);
			}else{
				word = reply.getContent().substring(0, 1);
			}
			String[] s={"回复",word+"...","新增"};
			topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.info("回复异常");
			resultable = new Resultable(false, "操作失败");
			
			String word = reply.getContent().substring(0, 12);
			String[] s={"回复",word+"...","新增"};
			topicService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally{
			return resultable;
		}
	}
	
}
