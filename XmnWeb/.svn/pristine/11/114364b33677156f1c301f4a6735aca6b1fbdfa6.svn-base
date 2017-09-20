/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.FreshImage;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.entity.HotBrand;
import com.xmniao.xmn.core.fresh.entity.JsonToActivityBean;
import com.xmniao.xmn.core.fresh.entity.JsonToGroupBean;
import com.xmniao.xmn.core.fresh.entity.Module;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.service.ActivityGroupService;
import com.xmniao.xmn.core.fresh.service.ActivityProductService;
import com.xmniao.xmn.core.fresh.service.FreshImageService;
import com.xmniao.xmn.core.fresh.service.FreshManageService;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;
import com.xmniao.xmn.core.fresh.service.HotBrandService;
import com.xmniao.xmn.core.fresh.service.ModuleService;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：ModuleController
 * 
 * 类描述： 首页
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月30日 上午11:07:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "fresh/module")
public class ModuleController extends BaseController {
	
	@Autowired
	private HotBrandService hotBrandService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FreshImageService freshImageService;
	
	private ObjectMapper MAPPER=new ObjectMapper();
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private ActivityGroupService activityGroupService;
	
	
	/**
	 * 注入积分超市-产品管理服务
	 */
	@Autowired
	private FreshManageService freshManagermentService;
	
	@RequestMapping(value="init")
	@ResponseBody
	private Object init(@RequestParam(value="type",required=false,defaultValue="0")Long type) throws JsonProcessingException{
		log.info("ModuleController-->init------type="+type);
		ModelAndView modelAndView = new ModelAndView("fresh/moduleList");
		FreshType freshType = new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getALLFather(freshType);
		FreshImage freshImage = new FreshImage();
		freshImage.setState(1);
		freshImage.setTypeId(type);
		freshImage.setType(1);
		freshImage.setOrderBy("desc");
		List<FreshImage> bannerImages = freshImageService.getList(freshImage);
		if(bannerImages!=null&&bannerImages.size()>0){
			modelAndView.addObject("bannerImages",bannerImages);
		}
		FreshImage iconImage = new FreshImage();
		iconImage.setState(1);
		iconImage.setTypeId(type);
		iconImage.setType(2);
		iconImage.setOrderBy("desc");
		List<FreshImage> iconImages = freshImageService.getList(iconImage);
		if(iconImages!=null&&iconImages.size()>0){
			modelAndView.addObject("iconImages",iconImages);
		}
		FreshImage activityImage = new FreshImage();
		activityImage.setState(1);
		activityImage.setTypeId(type);
		activityImage.setType(3);
		activityImage.setOrderBy("desc");
		List<FreshImage> activityImages = freshImageService.getList(activityImage);
		if(activityImages!=null&&activityImages.size()>0){
			modelAndView.addObject("activityImages",activityImages);
		}
		Module module = new Module();
		module.setState(1);
		module.setTypeId(type);
		module.setModuleType(1);
		Module productModule=moduleService.getObject(module);
		if(productModule!=null){
			modelAndView.addObject("productModule",productModule);
		}
		HotBrand hotBrand = new HotBrand();
		hotBrand.setTypeId(type);
		hotBrand.setState(1);
		hotBrand.setOrderBy("desc");
		List<HotBrand> hotBrands = hotBrandService.getList(hotBrand);
		if(hotBrands!=null&&hotBrands.size()>0){
			modelAndView.addObject("hotBrands", hotBrands);
		}
		Module boutique = new Module();
		boutique.setState(1);
		boutique.setTypeId(type);
		boutique.setModuleType(2);
		Module boutiqueModule = moduleService.getObject(boutique);
		if(boutiqueModule!=null){
			modelAndView.addObject("boutiqueModule",boutiqueModule);
		}
		Module dedi = new Module();
		dedi.setState(1);
		dedi.setTypeId(type);
		dedi.setModuleType(3);
		dedi.setOrderBy("desc");
		List<Module> dediModules = moduleService.getList(dedi);
		if(dediModules!=null&&dediModules.size()>0){
			for (Module dediModule : dediModules) {
				List<ActivityProduct> activityProducts=activityProductService.getByModuleId(dediModule.getId());
				for (ActivityProduct activityProduct : activityProducts) {
					ProductInfo productInfo = freshManagermentService.getByCodeId(activityProduct.getCodeId());
					activityProduct.setPname(productInfo.getPname());
					HashMap<String,Object> vo = new HashMap<String,Object>();
					vo.put("codeId", activityProduct.getCodeId());
					vo.put("sort",activityProduct.getSort());
					ObjectMapper objectMapper = new ObjectMapper();
					String json = objectMapper.writeValueAsString(vo);
					vo.put("ProductJsonString", vo);
					activityProduct.setProductJsonString(json);
				}
				dediModule.setActivityProducts(activityProducts);
			}
			modelAndView.addObject("dediModules", dediModules);
		}
		modelAndView.addObject("freshTypes", freshTypes);
		modelAndView.addObject("type",type);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "saveFreshImage",method=RequestMethod.POST)
	@ResponseBody
	private Object saveFreshImage(String json) throws JsonParseException, JsonMappingException, IOException{
		log.info("[调用保存banner/图标保存接口]json="+json);
		try {
			if(StringUtils.isBlank(json)){
				return new Resultable(false, "操作失败");
			}
			JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, FreshImage.class);  
			List<FreshImage> list =  (List<FreshImage>)MAPPER.readValue(json, javaType);  
			freshImageService.saveBanner(list);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value = "saveModule",method=RequestMethod.POST)
	@ResponseBody
	private Object saveModal(Module module){
		log.info("ModuleController-->saveModule------module="+module);
		try {
			if(module.getId()!=null){
				//修改
				module.setUpdateTime(new Date());
				moduleService.update(module);
			}else{
				//添加
				module.setCreateTime(new Date());
				module.setUpdateTime(new Date());
				module.setState(1);
				moduleService.add(module);
			}
			return new Resultable(true, "操作成功",module.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value = "saveHotBrand",method=RequestMethod.POST)
	@ResponseBody
	private Object saveHotBrand(String json){
		log.info("ModuleController-->saveHotBrand------json="+json);
		try {
			if(StringUtils.isBlank(json)){
				return new Resultable(false, "操作失败");
			}
			JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, HotBrand.class);  
			List<HotBrand> list =  (List<HotBrand>)MAPPER.readValue(json, javaType);
			hotBrandService.saveHotBrand(list);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value = "saveDediModule",method=RequestMethod.POST)
	@ResponseBody
	private Object saveDediModule(Module module){
		log.info("ModuleController-->saveDediModule------module="+module);
		log.info("[调用保存专场接口]module="+module);
		try {
			moduleService.saveDediModule(module);
			return new Resultable(true, "操作成功",module.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value = "deleteBanner",method=RequestMethod.POST)
	@ResponseBody
	private Object deleteBanner(@RequestParam(value="typeId",required=true)Integer typeId,@RequestParam(value="type",required=true)Integer type){
		log.info("ModuleController-->deleteBanner------typeId="+typeId);
		try {
			moduleService.deleteBanner(typeId,type);
			return new Resultable(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Resultable(false, "删除失败");
		}
	}
	
	@RequestMapping(value = "deleteBrand",method=RequestMethod.POST)
	@ResponseBody
	private Object deleteBrand(@RequestParam(value="typeId",required=true)Integer typeId){
		log.info("ModuleController-->deleteBrand------typeId="+typeId);
		try {
			moduleService.deleteBrand(typeId);
			return new Resultable(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Resultable(false, "删除失败");
		}
	}
	
	@RequestMapping(value = "deleteModule",method=RequestMethod.POST)
	@ResponseBody
	private Object deleteModule(@RequestParam(value="id",required=true)Long id){
		log.info("ModuleController-->deleteModule------id="+id);
		try {
			
			Module module = moduleService.getObject(id);
			if(module.getTypeId()!=0&&module.getModuleType()==2){
				return new Resultable(false, "产品妹子说,分类首页必须有精选商品模块...");
			}
			moduleService.deleteModule(id);
			return new Resultable(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Resultable(false, "删除失败");
		}
	}
	
}
