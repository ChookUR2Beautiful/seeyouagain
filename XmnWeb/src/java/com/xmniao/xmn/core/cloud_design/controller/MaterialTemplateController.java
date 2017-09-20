/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarousel;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCarouselService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialTemplateController
 * 
 * 类描述： 物料模板Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-2 下午2:26:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name = "模板管理")
@Controller
@RequestMapping(value = "materialTemplate/manage")
public class MaterialTemplateController extends BaseController {
	
	@Autowired
	private DMaterialCarouselService carouselService;
	
	/**
	 * 
	 * 方法描述：跳转到新增模板页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-2下午2:44:33 <br/>
	 * @param materialCarousel
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(DMaterialCarousel materialCarousel,Model model){
		Long materialId = materialCarousel.getMaterialId();
		model.addAttribute("isType", "add");
		model.addAttribute("materialId", materialId);
		return "cloud_design/materialTemplateEdit";
	}
	
	/**
	 * 
	 * 方法描述：保存模板元数据信息(背景图、图片、文案) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午8:00:06 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(DMaterialCarousel materialCarousel){
		Resultable result=new Resultable();
		try {
			carouselService.saveInfo(materialCarousel);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("添加失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑模板页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午8:06:04 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(DMaterialCarousel materialCarousel){
		Long materialCarouselId = materialCarousel.getId();
		Long materialId = materialCarousel.getMaterialId();
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("isType", "update");
		modelAndView.addObject("materialId", materialId);
		modelAndView.addObject("materialCarouselId", materialCarouselId);
		modelAndView.setViewName("cloud_design/materialTemplateEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：保存模板元数据信息(背景图、图片、文案) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午8:06:04 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(DMaterialCarousel materialCarousel){
		Resultable result=new Resultable();
		try {
			result=carouselService.updateInfo(materialCarousel);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("保存失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取模板基础数据(模板名称，背景图等) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午9:54:27 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="update/getBaseInfo")
	@ResponseBody
	public Resultable updateGetBaseInfo(DMaterialCarousel materialCarousel){
		Resultable result=new Resultable();
		result=carouselService.setMaterialCarouselInfo(materialCarousel);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取模板元数据(文案框架，图片框架) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午9:54:27 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="update/getSourceInfo")
	@ResponseBody
	public Resultable updateGetSourceInfo(DMaterialCarousel materialCarousel){
		Resultable result=new Resultable();
		result=carouselService.setMaterialCarouselSrcInfo(materialCarousel);
		return result;
	}

}
