/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecommendRecord;
import com.xmniao.xmn.core.live_anchor.service.TLiveRecommendRecordService;
import com.xmniao.xmn.core.vstar.entity.Dynamic;
import com.xmniao.xmn.core.vstar.service.DynamicService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：DynamicVideoController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月19日 上午10:36:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/dynamicVideo")
public class DynamicVideoController extends BaseController{
	
	@Autowired
	private DynamicService dynamicService;
	
	@Autowired
	private TLiveRecommendRecordService liveRecommendRecordService;
	
	
	@RequestMapping("/init")
	public Object init(){
		return "vstar/dynamicVideo/dynamicVideoList";
	}
	
	@RequestMapping("edit/dynamic/init")
	public Object dynamicInit(){
		return "vstar/dynamicVideo/dynamicEdit";
	}
	
	@RequestMapping("edit/video/init")
	public Object videoInit(){
		return "vstar/dynamicVideo/videoEdit";
	} 
	
	@RequestMapping("/init/dynamicList")
	@ResponseBody
	public Object dynamicList(Dynamic  dynamic){
			List<Dynamic> list = dynamicService.getList(dynamic);
			return Resultable.success("操作成功", list);
	}

	@RequestMapping("/init/vedioList")
	@ResponseBody
	public Object vedioList(TLiveRecommendRecord video){
		List<TLiveRecommendRecord> list=liveRecommendRecordService.getTVstarRecord(video);
		return Resultable.success("操作成功", list);
	}
	
	@RequestMapping("/edit/dynamic")
	@ResponseBody
	public Object addDynamic(Dynamic dynamic){
		dynamic.setCreateTime(new Date());
		dynamic.setUpdateTime(new Date());
		dynamicService.add(dynamic);
		return Resultable.success();
	}
	
	@RequestMapping("/edit/dynamic/sort")
	@ResponseBody
	public Object updateDynamicSort(@RequestParam(required=true)Integer id,@RequestParam(required=true)Integer sort){
		Dynamic dynamic = new Dynamic();
		dynamic.setId(id);
		dynamic.setSort(sort);
		dynamic.setUpdateTime(new Date());
		dynamicService.update(dynamic);
		return Resultable.success();
	}
	
	@RequestMapping("/delete/dynamic")
	@ResponseBody
	public Object deleteDynamic(@RequestParam(required=true)Integer id){
		Dynamic dynamic = new Dynamic();
		dynamic.setId(id);
		dynamic.setUpdateTime(new Date());
		dynamic.setState(2);
		dynamicService.update(dynamic);
		return Resultable.success();
	}
	
	@RequestMapping("/edit/video")
	@ResponseBody
	public Object addVideo(TLiveRecommendRecord video){
		try {
			video.setCreateTime(new Date());
			video.setUpdateTime(new Date());
			liveRecommendRecordService.addTVstarRecord(video);
			return Resultable.success();
		} catch (Exception e) {
			log.error("添加大赛精彩视频失败",e);
			return Resultable.defeat();
		}
	}
	
	
	@RequestMapping("/edit/video/sort")
	@ResponseBody
	public Object updateVideoSort(@RequestParam(required=true)Integer id,@RequestParam(required=true)Integer sort){
		try {
			liveRecommendRecordService.updateVideoSort(id,sort);
			return Resultable.success();
		} catch (Exception e) {
			log.error("修改精彩视频失败",e);
			return Resultable.defeat();
		}
	}
	
	@RequestMapping("/delete/video")
	@ResponseBody
	public Object deleteVideo(@RequestParam(required=true)Integer id){
		try {
			liveRecommendRecordService.deleteById(id);
			return Resultable.success();
		} catch (Exception e) {
			log.error("删除精彩视频失败",e);
			return Resultable.defeat();
		}
	}
	
	
}
