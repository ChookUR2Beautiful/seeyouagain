package com.xmniao.xmn.core.marketingmanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterial;
import com.xmniao.xmn.core.marketingmanagement.service.MaterialService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 类名称：MaterialController
 * 类描述： 物料信息控制器
 * 创建人： yhl
 * 创建时间：2016年7月15日14:14:20
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="物料管理")
@Controller
@RequestMapping(value = "marketingManagement/material")
public class MaterialController extends BaseController{
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private MaterialService materialService;
	
	public String UPLOAD_URL = PropertiesUtil.readValue("file.upload.fastDFS.http");
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView("marketingManagement/materialList");
		view.addObject("upload_url", UPLOAD_URL);
		return view;
	}
	
	/*
	 * 物料列表
	 */
	@ResponseBody
	@RequestMapping(value = "init/list")
	public Object getMaterialList(TMaterial tMaterial){
		Pageable<TMaterial> pageable = new Pageable<TMaterial>(tMaterial);
		pageable.setContent(materialService.getMaterialList(tMaterial));
		pageable.setTotal(materialService.getMaterialListCount(tMaterial));
		return pageable;
	}
	
	/*
	 * 新增物料
	 */
	@RequestMapping(value = "init/add")
	public ModelAndView saveMaterial(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/materialAddOrEdit");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/*
	 * 获取物料信息- 到编辑页
	 */
	@RequestMapping(value = "init/edit")
	public ModelAndView editMaterial(HttpServletRequest request ,@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/materialAddOrEdit");
		try {
			TMaterial material = materialService.getMaterialInfo(new Long(id));
			this.log.info(material);
			modelAndView.addObject("material",material);
		} catch (Exception e) {
			this.log.error("处理异常",e);
		}
		return modelAndView;
	}
	
	/*
	 *保存或修改物料信息
	 */
	@ResponseBody
	@RequestMapping(value = {"init/add/save","init/edit/save"})
	public Object saveOrUpdate(TMaterial material) {
		Resultable resultable = null;
		try {
			if (null!= material) {
				if (null!=material.getId()) {
					materialService.update(material);
					resultable = new Resultable(true, "修改成功!");
				}else {
					materialService.add(material);
					resultable = new Resultable(true, "新增成功!");
				}
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
	
}
