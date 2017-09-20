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

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrRelation;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialTag;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrRelationService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialAttrController
 * 
 * 类描述： 物料规格Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-9-28 下午2:38:02
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "规格管理")
@Controller
@RequestMapping(value = "materialAttr/manage")
public class MaterialAttrController extends BaseController {

	/**
	 * 注入物料规格服务
	 */
	@Autowired
	private DMaterialCategoryAttrService categoryAttrService;
	
	/**
	 * 注入物料分类规格关联关系服务
	 */
	@Autowired
	private DMaterialCategoryAttrRelationService categoryAttrRelationService;
	

	/**
	 * 
	 * 方法描述：跳转到规格管理列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "cloud_design/materialAttrManage";
	}
	
	/**
	 * 
	 * 方法描述：加载规格管理列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(DMaterialCategoryAttr categoryAttr) {
		Pageable<DMaterialCategoryAttr> pageable=new Pageable<DMaterialCategoryAttr>(categoryAttr);
		List<DMaterialCategoryAttr> list = categoryAttrService.getList(categoryAttr);
		long count = categoryAttrService.count(categoryAttr);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到新增规格页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:05 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(Model model){
		model.addAttribute("isType", "add");
		return "cloud_design/materialAttrEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(DMaterialCategoryAttr categoryAttr){
		Resultable result = new Resultable();
		try {
//			categoryAttrService.add(categoryAttr);
			categoryAttrService.save(categoryAttr);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}finally{
			String[] data={"雏鸟云设计规格编号",categoryAttr.getId().toString(),"添加","添加"};
			categoryAttrService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：删除规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:28:30 <br/>
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Resultable delete(DMaterialTag materialTag){
		Resultable result = new Resultable();
		try {
			Long id = materialTag.getId();
			int count = categoryAttrService.deleteById(id);
			if(count>0){
				result.setSuccess(true);
				result.setMsg("删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("删除失败!");
			this.log.error("删除规格失败："+e.getMessage(), e);
		}finally{
			String[] data={"雏鸟云设计规格编号",materialTag.getId().toString(),"删除","删除"};
			categoryAttrService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:26:09 <br/>
	 * @param materialTag
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="update/init")
	public String updateInit(DMaterialCategoryAttr categoryAttr,Model model){
		//TODO
		DMaterialCategoryAttr categoryAttrInfo = categoryAttrService.selectById(categoryAttr.getId());
		model.addAttribute("categoryAttrInfo", categoryAttrInfo);
		return "cloud_design/materialAttrEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新规格信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:26:27 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestLogging(name="雏鸟云设计规格管理")
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(DMaterialCategoryAttr categoryAttr){
		//TODO 2016-11-21 18:38
		Resultable result=new Resultable();
		result=categoryAttrService.updateAttrInfo(categoryAttr);
		return result;
		
	}
	
	/**
	 * 
	 * 方法描述：获取所有物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getAttrs",method=RequestMethod.POST)
	@ResponseBody
	public Object getAttrs(DMaterialCategoryAttr categoryAttr) {
		Pageable<DMaterialCategoryAttr> pageable = new Pageable<DMaterialCategoryAttr>(categoryAttr);
		List<DMaterialCategoryAttr> categoryAttrs = categoryAttrService.getList(categoryAttr);
		pageable.setContent(categoryAttrs);
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：获取指定分类关联的物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getRelationAttrs",method=RequestMethod.POST)
	@ResponseBody
	public Object getRelationAttrs(DMaterialCategoryAttrRelation relation) {
		Resultable result=new Resultable();
		try {
			List<DMaterialCategoryAttrRelation> list = categoryAttrRelationService.getList(relation);
			result.setSuccess(true);
			result.setData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
