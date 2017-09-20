/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.TFreshLabel;
import com.xmniao.xmn.core.fresh.service.TFreshLabelService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaController
 * 
 * 类描述： 竞拍
 * 
 * 创建人： 
 * 
 * 创建时间：2017年2月17日 上午11:56:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="标签管理")
@Controller
@RequestMapping("freshLabel/manage")
public class FreshLabelController extends BaseController{
	
	@Autowired
	private TFreshLabelService freshLabelService;
	
	@RequestMapping("/init")
	public Object init(){
		return "fresh/label/labelManage";
	}
	
	
	@RequestMapping("init/list")
	@ResponseBody
	public Object list(TFreshLabel freshLabel, @RequestParam("type") Integer type){
		this.log.info("TFreshLabelController-->list TFreshLabel=" + freshLabel);
		Pageable<TFreshLabel> pageable = new Pageable<>(freshLabel);
		freshLabel.setType(type);
		pageable.setContent(freshLabelService.getList(freshLabel));  //getFreshLabelInfoList(freshLabel)
		pageable.setContent(freshLabelService.getFreshLabelInfoList(freshLabel)); 
		
		pageable.setTotal(freshLabelService.count(freshLabel));
		return pageable;
	}
	
	
	@RequestMapping("add/init")
	public Object addInit(ModelAndView model, @RequestParam("type") Integer type){
		TFreshLabel freshLabelInfo = new TFreshLabel();
		freshLabelInfo.setType(type);
		
		model.addObject("labelInfo",freshLabelInfo);
		model.setViewName("fresh/label/labelEdit");
		model.addObject("isType", "add");
		
		return model;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object save(TFreshLabel freshLabel){
		try {
			Integer id = freshLabel.getId();
			if(id!=null){
				log.info("[执行修改商品标签方法]id="+id);
//				freshLabelService.updateTFreshLabel();
			}else{
				log.info("[执行添加商品标签方法]");
				freshLabel.setStatus(1);
				freshLabelService.saveActivity(freshLabel);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping("/update/init")
	public Object editInit(TFreshLabel freshLabel, Model model) throws Exception{
		ModelAndView modelAndView = new ModelAndView("fresh/label/labelEdit");
		Integer recordId = freshLabel.getId();
		TFreshLabel freshLabelInfo = freshLabelService.getObject(recordId.longValue());
		
		model.addAttribute("isType", "update");
		model.addAttribute("labelInfo",freshLabelInfo);
		return modelAndView;
	}
	
	@RequestMapping(value={"/update","setStatus"})
	@ResponseBody
	public Object update(TFreshLabel freshLabel){
		try {
			freshLabelService.update(freshLabel);
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id){
		try {
			freshLabelService.deleteActivity(id);
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping("/getLabelChoose")
	@ResponseBody
	public Object getLabelChoose(TFreshLabel freshLabel){
		Pageable<TFreshLabel> pageable = new Pageable<>(freshLabel);
		pageable.setContent(freshLabelService.getLabelChoose(freshLabel));
		return pageable;
	}
	
}
