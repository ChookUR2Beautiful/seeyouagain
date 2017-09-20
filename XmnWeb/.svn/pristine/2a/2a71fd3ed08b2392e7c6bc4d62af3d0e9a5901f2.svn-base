package com.xmniao.xmn.core.cloud_design.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.dao.SupplierManageDao;
import com.xmniao.xmn.core.cloud_design.entity.PostTemplate;
import com.xmniao.xmn.core.cloud_design.entity.Supplier;
import com.xmniao.xmn.core.cloud_design.service.TransportFeeService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TransportFreeController
 * 
 * 类描述： 运费模板
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月25日 下午3:50:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/transportFee/manage")
public class TransportFeeController {
	
	/**
	 * 初始化日志类
	 */
	private Logger log = Logger.getLogger(TransportFeeController.class);
	
	@Autowired
	private TransportFeeService tFeeService;
	
	@Autowired
	private SupplierManageDao sManageDao;
	@RequestMapping("/init")
	public String init(){
		return "/cloud_design/postTemplate/postTemplateList";
	}
	
	/**
	 * 
	 * 方法描述：获取运费模板列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月26日下午3:25:27 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(PostTemplate postTemplate){
		Pageable<PostTemplate> pageable = new Pageable<>(postTemplate);
		pageable.setContent(tFeeService.getList(postTemplate));
		pageable.setTotal(tFeeService.count());
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转添加模板页面
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日上午11:43:45 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/addView")
	@ResponseBody
	public ModelAndView addView(PostTemplate postTemplate){
		ModelAndView mv = new ModelAndView("cloud_design/postTemplate/postTemplate");
		Supplier supplier = new Supplier();
		supplier.setLimit(-1);
		mv.addObject("suppliers",sManageDao.getList(supplier));
		mv.addObject("areas",tFeeService.getAreaList());
		mv.addObject("postTemplate",null);
		mv.addObject("isType","add");
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：新增
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午5:10:12 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(PostTemplate postTemplate){
		Resultable resultable;
		try {
			tFeeService.addTemplate(postTemplate);
			log.info("新增运费模板成功");
			resultable = new Resultable(true,"新增成功");
			return resultable;
		} catch (Exception e) {
			log.error("新增运费模板失败",e);
			resultable = new Resultable(false,"新增失败");
			return resultable;
		}
	}
	
	
	/**
	 * 
	 * 方法描述：删除运费模板
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月29日下午2:59:31 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/deleteTemplate")
	@ResponseBody
	public Object deleteTemplate(PostTemplate postTemplate){
		Resultable resultable;
		try {
			Integer result =tFeeService.deleteTemplate(postTemplate);
			if(result == 1){
				log.info("删除运费模板成功");
				resultable = new Resultable(true,"删除成功");
				return resultable;
			}else {
				log.info("删除运费模板失败");
				resultable = new Resultable(false,"删除失败");
				return resultable;
			}
		} catch (Exception e) {
			log.error("删除运费模板失败",e);
			resultable = new Resultable(false,"删除失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：跳转编辑页面
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日上午11:43:45 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/editView")
	@ResponseBody
	public ModelAndView editView(PostTemplate postTemplate){
		ModelAndView mv = new ModelAndView("cloud_design/postTemplate/postTemplate");
		mv.addObject("suppliers",sManageDao.getList(new Supplier()));
		mv.addObject("areas",tFeeService.getAreaList());
		mv.addObject("postTemplate",tFeeService.getList(postTemplate).get(0));
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：编辑
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午5:10:12 <br/>
	 * @param postTemplate
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(PostTemplate postTemplate){
		Resultable resultable;
		try {
			tFeeService.update(postTemplate);
			resultable = new Resultable(true,"更新成功");
			log.info("更新运费模板成功");
			return resultable;
		} catch (Exception e) {
			log.error("更新运费模板失败",e);
			resultable = new Resultable(false,"更新失败");
			return resultable;
		}
	}
}
