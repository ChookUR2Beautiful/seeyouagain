/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.service.FreshBrandService;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BrandConcroller
 * 
 * 类描述： 品牌管理
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月16日 上午10:55:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping(value = "fresh/brand")
public class BrandConcroller extends BaseController{
	@Autowired
	private FreshBrandService freshBrandService;
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@RequestMapping(value="init")
	private Object Init(){
		log.info("BrandConcroller-->fresh/brand");
		ModelAndView modelAndView=new ModelAndView("fresh/brandList");
		FreshType freshType=new FreshType();
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		modelAndView.addObject("freshTypes",freshTypes);
		return modelAndView;
	}
	
	@RequestMapping(value="add/init")
	private Object addInit(Integer id){
		log.info("BrandConcroller-->add/init");
		ModelAndView modelAndView=new ModelAndView("fresh/brandEdit");
		if(id!=null){
			FreshBrand freshBrand = freshBrandService.getObject(id.longValue());
			modelAndView.addObject("freshBrand",freshBrand);
			
		}
		FreshType freshType=new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		modelAndView.addObject("freshTypes",freshTypes);
		return modelAndView;
	}
	
	@RequestMapping(value="import/init")
	private Object importInit(){
		log.info("BrandConcroller-->import/init");
		return "fresh/importBrand";
	}
	
	@RequestMapping(value="init/list")
	@ResponseBody
	private Object list(FreshBrand brand){
		log.info("BrandConcroller-->init/list");
			Pageable<FreshBrand> pageable=new Pageable<FreshBrand>(brand);
			pageable.setContent(freshBrandService.getList(brand));
			pageable.setTotal(freshBrandService.getTotal(brand));
			return pageable;
	}
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(FreshBrand brand){
		log.info("BrandConcroller-->add");
		try {
			if(brand.getId()!=null){
				brand.setUpdateTime(new Date());
				freshBrandService.update(brand);
			}else{
				brand.setStatus(0);
				brand.setCreateTime(new Date());
				brand.setUpdateTime(new Date());
				freshBrandService.add(brand);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加品牌失败",e);
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	private Object delete(String ids){
		log.info("BrandConcroller-->delete");
		try {
			if(StringUtils.isBlank(ids)){
				return new Resultable(false, "参数为空");
			}
			if(freshBrandService.hasProduct(ids)){
				return new Resultable(false, "该品牌下有商品,不能删除");
			}
			freshBrandService.delete(ids);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除品牌失败",e);
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value="export/check",method=RequestMethod.POST)
	@ResponseBody
	private Object checkExport(FreshBrand brand){
		log.info("BrandConcroller-->export/check");
		if(brand.getMaxNum()!=null&&brand.getMinNum()!=null){
			if(brand.getMaxNum()<brand.getMinNum()){
				Integer temp=brand.getMaxNum();
				brand.setMaxNum(brand.getMinNum());
				brand.setMinNum(temp);
			}
		}
		if(brand.getMaxPage()!=null&&brand.getMinPage()!=null){
			if(brand.getMaxPage()<brand.getMinPage()){
				Integer temp=brand.getMaxPage();
				brand.setMaxPage(brand.getMinNum());
				brand.setMinPage(temp);
			}
		}
		List<FreshBrand> list=freshBrandService.getExport(brand);
		if(list==null||list.size()==0){
			return new Resultable(false, "没有可导出的数据");
		}
		return new Resultable(true, "赶紧导出");
	}
	
	@RequestMapping(value="export",method=RequestMethod.POST)
	@ResponseBody
	private Object export(FreshBrand brand,HttpServletRequest request,
			HttpServletResponse response){
		log.info("BrandConcroller-->export");
		log.info("开始导出品牌列表");
		if(brand.getMaxNum()!=null&&brand.getMinNum()!=null){
			if(brand.getMaxNum()<brand.getMinNum()){
				Integer temp=brand.getMaxNum();
				brand.setMaxNum(brand.getMinNum());
				brand.setMinNum(temp);
			}
		}	
		if(brand.getMaxPage()!=null&&brand.getMinPage()!=null){
			if(brand.getMaxPage()<brand.getMinPage()){
				Integer temp=brand.getMaxPage();
				brand.setMaxPage(brand.getMinNum());
				brand.setMinPage(temp);
			}
		}
		List<FreshBrand> list=freshBrandService.getExport(brand);
		if(list==null||list.size()==0){
			return new Resultable(false, "没有可导出的数据");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		doExport(request, response, "fresh/brandExport.xls", params);
		return new Resultable(true, "操作成功");
	}
	
	@RequestMapping(value="importBrand/importData",method=RequestMethod.POST)
	@ResponseBody
	private void importExcel(@RequestParam("importData")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("BrandConcroller-->importData");
		log.info("开始导入品牌");
		PrintWriter printWriter = response.getWriter();
		try {
			printWriter.println(JSON.toJSON(freshBrandService.importBrandInfo(multipartFile)));
		} catch (Exception e) {
			printWriter.println(JSON.toJSON(new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, "导入失败,请重新导入")));
			log.error("文件上传失败", e);
		}
	}
	
	@RequestMapping(value="importProduct/downloadTemplate",method=RequestMethod.GET)
	@ResponseBody
	private void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		log.info("BrandConcroller-->importProduct/downloadTemplate");
		super.downloadTemplate(request, response, "fresh/importBrandTemplate.xls", "品牌导入模版");
	}
	
}
