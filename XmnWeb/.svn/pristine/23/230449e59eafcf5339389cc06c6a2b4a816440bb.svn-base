/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.vstar.entity.Division;
import com.xmniao.xmn.core.vstar.service.DivisionRegionService;
import com.xmniao.xmn.core.vstar.service.DivisionService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：DivisionController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月7日 下午3:45:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/division")
public class DivisionController extends BaseController{

	@Autowired
	private DivisionRegionService divisionRegionService;
	
	@Autowired
	private  DivisionService divisionService;
	
	
	
	@RequestMapping("/init")
	public Object init(){
		return "vstar/division/divisionList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(Division division){
		Pageable<Division> pageable = new Pageable<>(division);
		pageable.setContent(divisionService.getList(division));
		pageable.setTotal(divisionService.count(division));
		return pageable;
	}
	
	@RequestMapping("/add/init")
	public Object addInit(Integer id){
		ModelAndView modelAndView = new ModelAndView("vstar/division/divisionEdit");
		if(id!=null){
			Division division = divisionService.getObject(id.longValue());
			modelAndView.addObject("division", division);
		}
		return modelAndView;
	}
	
	
	@RequestMapping("/getCitys")
	@ResponseBody
	public Object getCitys(Integer id){
		Pageable<TArea> pageable = new Pageable<>();
		pageable.setContent(divisionRegionService.getNoChooseCitys(id));
		return pageable;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(Division division){
		try {
			if(division.getId()!=null){
				divisionService.update(division);
			}else{
				divisionService.add(division);
			}
			return Resultable.success();
		} catch (Exception e) {
			log.error("添加赛区失败",e);
			return Resultable.defeat();
		}
	}
	
	/**
	 * 
	 * 方法描述：初始化赛区下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initDivisionId",method=RequestMethod.POST)
	@ResponseBody
	public Object initDivisionId(Division division) {
		Pageable<Division> pageable = new Pageable<Division>(division);
		division.setOrder(PageConstant.NOT_ORDER);
		List<Division> divisionList = divisionService.getBaseList(division);
		pageable.setContent(divisionList);
		return pageable;
	}
	
}
