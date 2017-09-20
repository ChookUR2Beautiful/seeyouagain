/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategory;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrRelationService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryController
 * 
 * 类描述： 物料分类Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-17 上午11:15:43 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name = "物料分类")
@Controller
@RequestMapping(value = "materialCategory/manage")
public class DMaterialCategoryController extends BaseController {
	
	/**
	 * 注入物料分类服务
	 */
	@Autowired
	private DMaterialCategoryService categoryService;
	
	/**
	 * 注入物料规格服务
	 */
	/*@Autowired
	private DMaterialCategoryAttrService categoryAttrService;*/
	
	
	/**
	 * 
	 * 方法描述：跳转到物料列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "cloud_design/categoryManage";
	}
	
	/**
	 * 
	 * 方法描述：加载物料分类列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(DMaterialCategory category) {
		Pageable<DMaterialCategory> pageable=new Pageable<DMaterialCategory>(category);
		List<DMaterialCategory> list = categoryService.getList(category);
		long count = categoryService.count(category);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加物料分类页面 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(Model model) {
		model.addAttribute("isType", "add");
		return "cloud_design/categoryEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加物料分类<br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Resultable add(DMaterialCategory categoryRequest) {
		//TODO 
		Resultable result=categoryService.saveCategoryInfo(categoryRequest);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到物料分类编辑页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午4:08:10 <br/>
	 * @param categoryRequest
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(DMaterialCategory categoryRequest){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("cloud_design/categoryEdit");
		DMaterialCategory categoryInfo = categoryService.selectById(categoryRequest.getId());
		modelAndView.addObject("categoryInfo", categoryInfo);
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：更新物料信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午4:44:39 <br/>
	 * @param categoryRequest
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(DMaterialCategory categoryRequest){
		Resultable result = new Resultable();
		result=categoryService.updateCategoryInfo(categoryRequest);
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：获取关联分类 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getCategorys",method=RequestMethod.POST)
	@ResponseBody
	public Object getCategorys(DMaterialCategory category) {
		Pageable<DMaterialCategory> pageable = new Pageable<DMaterialCategory>(category);
		List<DMaterialCategory> categorys = categoryService.getList(category);
		pageable.setContent(categorys);
		return pageable;
	}
	
	
	
	
}
