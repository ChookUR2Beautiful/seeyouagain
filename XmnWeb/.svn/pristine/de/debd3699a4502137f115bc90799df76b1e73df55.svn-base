package com.xmniao.xmn.core.business_area.controller;

import java.util.List;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.business_area.entity.BusinessArea;
import com.xmniao.xmn.core.business_area.service.BusinessAreaService;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.common.service.BusinessService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AreaController
 * 
 * 类描述： 区域合作商
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年12月4日 下午14:56:43
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 * 
 */
@Controller
@RequestMapping(value = "business_area/businessArea")
public class BusinessAreaController extends BaseController {
	@Autowired
	private BusinessAreaService businessAreaService;// 商务区域service
	/*
	 * 注入区域查询显示servic,位于common包中
	 * 用于根据城市areaId 查询其下辖的区县
	 */
	@Autowired
	private AreaService areaService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_area/businessAreaList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(BusinessArea businessArea) {
		Pageable<BusinessArea> pageable = new Pageable<BusinessArea>(businessArea);
		/*pageable.setContent(businessAreaService.getList(businessArea));
		pageable.setTotal(businessAreaService.count(businessArea));*/			
		pageable.setContent(businessAreaService.getList(businessArea));
		pageable.setTotal(businessAreaService.count(businessArea));
		return pageable;
	}
	
	/**
	 * 查看详情
	 * @param model
	 * 
	 * @param cityNameid 城市id
	 * 
	 * @return BusinessArea
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "getBusinessAreaByid")
	public ModelAndView getBusinessAreaByid(ModelAndView model,@RequestParam("cityNameid") String id) {
		List<BusinessArea> getusinessAreaList=  businessAreaService.getBusinessAreaByid(Long.parseLong(id));
		if(!getusinessAreaList.isEmpty()){
			model.addObject("getusinessAreaList", getusinessAreaList.get(0));
		}
		model.setViewName("business_area/businessAreaView");		
		return model;
	}
	/*
	 * 合作商区域分布页面中，在区域数量、商圈数量、合作商数量、已签约数量上 添加页面跳转显示详细信息
	 * 1.区域详情显示，显示城市Id查出所有区域，显示省、市、区三级 
	 * 初始话页面
	 */
	@RequestMapping(value="init/getBusinessAreaInfo/init")
	public ModelAndView AreaInfoInit (@RequestParam("cityNameid")String areaId){
		ModelAndView modelAndView = new ModelAndView("business_area/businessAreaInfo");
		modelAndView.addObject("cityNameid", areaId);
		return modelAndView;
	}
	/*
	 * 加载数据
	 */
	@RequestMapping(value="init/getBusinessAreaInfo/init/list")
	@ResponseBody
	public Object areaList(BusinessArea area){
		Pageable<BusinessArea> pageable = new Pageable<BusinessArea>(area);
		List<BusinessArea> areaList = businessAreaService.areaInfo(area);
		pageable.setContent(areaList);
		Long areaCount = businessAreaService.areaInfoCount(area);
		pageable.setTotal(areaCount);
		return pageable;
	}
	/*
	 * 2.商圈详情显示
	 */
	@RequestMapping(value="init/getBusinessDataInfo/init")
	public ModelAndView businessInfoInit (@RequestParam("cityNameid")String areaId){
		ModelAndView modelAndView = new ModelAndView("business_area/businessDataInfo");
		modelAndView.addObject("cityNameid", areaId);
		return modelAndView;
	}
	
	@RequestMapping(value="init/getBusinessDataInfo/init/list")
	@ResponseBody
	public Object businessList(BusinessArea area){
		Pageable<BusinessArea> pageable = new Pageable<BusinessArea>(area);
		List<BusinessArea> businessList = businessAreaService.businessInfo(area);
		pageable.setContent(businessList);
		Long businessCount = businessAreaService.businessInfoCount(area);
		pageable.setTotal(businessCount);
		return pageable;
	}
	/*
	 * 3.合作商详情显示
	 */
	@RequestMapping(value="init/getcooperatorInfo/init")
	public ModelAndView cooperatorInfoInit (@RequestParam("cityNameid")String areaId){
		ModelAndView modelAndView = new ModelAndView("business_area/cooperatorInfo");
		modelAndView.addObject("cityNameid", areaId);
		return modelAndView;
	}
	
	@RequestMapping(value="init/getcooperatorInfo/init/list")
	@ResponseBody
	public Object cooperatorInfoList(BusinessArea area){
		Pageable<BusinessArea> pageable = new Pageable<BusinessArea>(area);
		List<BusinessArea> businessList = businessAreaService.cooperatorInfo(area);
		pageable.setContent(businessList);
		Long businessCount = businessAreaService.cooperatorInfoCount(area);
		pageable.setTotal(businessCount);
		return pageable;
	}
	/*
	 * 4.已签约商家显示
	 */
	@RequestMapping(value="init/getbusinessSignedInfo/init")
	public ModelAndView businessSignedInfoInit (@RequestParam("cityNameid")String areaId){
		ModelAndView modelAndView = new ModelAndView("business_area/businessSignedInfo");
		modelAndView.addObject("cityNameid", areaId);
		return modelAndView;
	}
	
	@RequestMapping(value="init/getbusinessSignedInfo/init/list")
	@ResponseBody
	public Object businessSignedInfoList(BusinessArea area){
		Pageable<BusinessArea> pageable = new Pageable<BusinessArea>(area);
		List<BusinessArea> businessList = businessAreaService.businessSignedInfo(area);
		pageable.setContent(businessList);
		Long businessCount = businessAreaService.businessSignedInfoCount(area);
		pageable.setTotal(businessCount);
		return pageable;
	}
}
