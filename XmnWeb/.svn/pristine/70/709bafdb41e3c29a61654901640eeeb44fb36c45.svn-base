/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.ExperienceComment;
import com.xmniao.xmn.core.businessman.service.ExperienceCommentService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ExperienceCommentController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月15日 下午5:23:36 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("businessman/experience/comment")
public class ExperienceCommentController extends BaseController{
	
	@Autowired
	private ExperienceCommentService experienceCommentService;

	
	@RequestMapping("/init")
	public Object init(Integer sellerType,Integer sellerid){
		ModelAndView modelAndView = new ModelAndView("businessman/experienceCommentList");
		modelAndView.addObject("sellerType", sellerType);
		modelAndView.addObject("sellerid", sellerid);
		return modelAndView;
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object list(ExperienceComment experienceComment){
		Pageable<ExperienceComment> pageable = new Pageable<>(experienceComment);
		pageable.setContent(experienceCommentService.getExperienceCommentList(experienceComment));
		pageable.setTotal(experienceCommentService.experienceCommentCount(experienceComment));
		return pageable;
	}
	
	@RequestMapping("/updateIsRecommend")
	@ResponseBody
	public Object updateIsRecommend(@RequestParam(required=true)Integer id,@RequestParam(required=true)Integer isRecommend){
		try {
			int i= experienceCommentService.updateIsRecommend(id,isRecommend);
			if(i<=0){
				return Resultable.defeat("操作失败");
			}
			return Resultable.defeat("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping("/updateReviewState")
	@ResponseBody
	public Object updateReviewState(@RequestParam(required=true)Integer id,@RequestParam(required=true)Integer reviewState,String refuseRemark){
		try {
			int i= experienceCommentService.updateReviewState(id,reviewState,refuseRemark);
			if(i<=0){
				return Resultable.defeat("操作失败");
			}
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping("init/experienceCommentDetail")
	public Object experienceCommentDetail(@RequestParam(required=true)Integer id){
		ModelAndView modelAndView = new ModelAndView("businessman/experienceCommentDetail");
		ExperienceComment  detail= experienceCommentService.getExperienceCommentDetail(id);
		modelAndView.addObject("detail", detail);
		return modelAndView;
	}
	
	
}
