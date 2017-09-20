/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.Classify;
import com.xmniao.xmn.core.businessman.entity.ClassifyTag;
import com.xmniao.xmn.core.businessman.service.ClassifyService;
import com.xmniao.xmn.core.businessman.service.ClassifyTagService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ClassifyController
 * 
 * 类描述： 标签管理
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月24日 下午4:06:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("businessman/classify")
public class ClassifyController extends BaseController{
	
	/**
	 * 注入直播标签Service
	 */
	@Autowired
	private ClassifyTagService classifyTagService;
	
	/**
	 * 注入直播标签分类Service
	 */
	@Autowired
	private ClassifyService classifyService;
	
	@RequestMapping("/init")
	public Object init(){
		log.info("ClassifyController --> init");
		return "businessman/classify/classifyList";
	}
	
	@RequestMapping("init/list")
	@ResponseBody
	public Object list(ClassifyTag classifyTag){
		log.info("ClassifyController --> init/list");
		Pageable<ClassifyTag> pageable = new Pageable<>(classifyTag);
		pageable.setContent(classifyTagService.getList(classifyTag));
		pageable.setTotal(classifyTagService.count(classifyTag));
		return pageable;
	}
	
	@RequestMapping("/add/init")
	public Object addInit(){
		log.info("ClassifyController --> add/init");
		return "businessman/classify/classifyEdit";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(ClassifyTag classifyTag){
		log.info("ClassifyController --> add classifyTag: "+classifyTag);
		try {
			classifyTagService.addTag(classifyTag);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.info("执行添加标签时出错",e);
			return new Resultable(false, "操作失败");
		}
	}
	
	/*
	 * 获取直播标签分类
	 */
	@RequestMapping(value = "liveRecordClassifyInit", method = RequestMethod.POST)
	@ResponseBody
	public Object liveRecordClassifyInit(Classify classify) {
		Pageable<Classify> pageable = new Pageable<Classify>(classify);
		//分类类别 1.商家 2.主播 3.直播
		if(classify.getClassifyType()==null){
			classify.setClassifyType(3);
		}
		List<Classify> liveClassfyList = classifyService.getList(classify);
		pageable.setContent(liveClassfyList);
		return pageable;
	}
	
	/*
	 * 获取分类下的关联标签数据
	 */
	@RequestMapping(value = "liveRecordTagInit", method = RequestMethod.POST)
	@ResponseBody
	public Object liveRecordTagInit(ClassifyTag classifyTag) {
		Pageable<ClassifyTag> pageable = new Pageable<ClassifyTag>(classifyTag);
		String filterVal = classifyTag.getFilterVal();
		if(StringUtils.isNotBlank(filterVal)){
			classifyTag.setClassifyId(new Integer(filterVal));
		}
		List<ClassifyTag> liveTagList = classifyTagService.getList(classifyTag);
		pageable.setContent(liveTagList);
		return pageable;
	}
}
