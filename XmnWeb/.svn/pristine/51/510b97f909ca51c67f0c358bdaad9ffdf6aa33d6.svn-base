/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：分类管理
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月19日 下午6:16:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping(value = "fresh/category")
public class Category2Controller extends BaseController {
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@RequestMapping(value="init")
	private Object init(){
		log.info("Category2Controller-->init");
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "fresh/manage/freshType/init/list");
		mv.setViewName("fresh/freshTypeList");
		return mv;
	}
	
	
	
	@RequestMapping(value="list")
	@ResponseBody
	private Object list(FreshType freshType){
		log.info("Category2Controller-->list");
		Pageable<FreshType> pageable=new Pageable<FreshType>(freshType);
		pageable.setContent(freshTypeService.getALLByPage(freshType));
		pageable.setTotal(freshTypeService.getFatherTotal());
		return pageable;
	}
	
	@RequestMapping(value="add/init")
	@ResponseBody
	private Object addInit(Integer id){
		log.info("Category2Controller-->add/init");
		ModelAndView modelAndView=new ModelAndView("fresh/categoryEdit");
		FreshType f=new FreshType();
		f.setLimit(-1);
		List<FreshType> freshTypes=freshTypeService.getALLFather(f);
		modelAndView.addObject("freshTypes",freshTypes);
		if(id!=null){
			FreshType freshType=freshTypeService.getObject(id.longValue());
			modelAndView.addObject("freshType",freshType);
		}
		return modelAndView;
		
	}
	
	@RequestMapping(value={"add","edit"})
	@ResponseBody
	private Object add(FreshType freshType){
		log.info("Category2Controller-->edit");
		try {
			Integer id = freshType.getId();
			if(id==null){
				log.info("执行添加方法");
				freshType.setRdate(new Date());
				freshType.setUdate(new Date());
				freshTypeService.add(freshType);
			}else{
				log.info("执行修改方法");
				freshType.setUdate(new Date());
				freshTypeService.update(freshType);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加修改失败",e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	private Object delete(@RequestParam(value
			="parentId",required=true)String parentId,@RequestParam(value
			="childId",required=true)String childId){
		log.info("Category2Controller-->delete");
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(parentId)){
				//判断分类下是否含有商品
				if(freshTypeService.hasParentBrand(parentId)){
					return new Resultable(false, "分类含有关联的品牌,不能删除");
				}
				if(freshTypeService.hasParentProduct(parentId)){
					return new Resultable(false, "分类含有关联的商品,不能删除");
				}
			}
			if(StringUtils.isNotBlank(childId)){
				if(freshTypeService.hasChildBrand(childId)){
					return new Resultable(false, "分类含有关联的品牌,不能删除");
				}
				if(freshTypeService.hasChildProduct(childId)){
					return new Resultable(false, "分类含有关联的商品,不能删除");
				}
			}
			if(StringUtils.isNotBlank(childId)||StringUtils.isNotBlank(parentId)){
				count+=freshTypeService.deleteMine(parentId,childId);
			}
			if(count>0){
				
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除失败",e);
			return new Resultable(false, "操作失败");
		}
	}
}
