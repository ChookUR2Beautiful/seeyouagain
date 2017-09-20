/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.entity.TSaasTag;
import com.xmniao.xmn.core.business_statistics.entity.TSaasTagCategory;
import com.xmniao.xmn.core.business_statistics.service.TSaasTagCategoryService;
import com.xmniao.xmn.core.business_statistics.service.TSaasTagService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasTagCategoryController
 * 
 * 类描述： SaaS商户端标签分类实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-9 下午2:20:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("saasTag/manage")
public class TSaasTagCategoryController extends BaseController {
	
	/**
	 * 注入SaaS标签服务
	 */
	@Autowired
	private TSaasTagCategoryService saasCategoryService;
	
	
	/**
	 * 注入SaaS标签选项服务
	 */
	@Autowired
	private TSaasTagService saasTagService;
	
	
	
	/**
	 * 跳转到标签列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_statistics/saasCategoryManage";
	}
	
	/**
	 * 
	 * 方法描述：加载标签列表数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param saasTagCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object anchorImageList(TSaasTagCategory saasTagCategory, Model model) {
		Pageable<TSaasTagCategory> pageabel = new Pageable<TSaasTagCategory>(saasTagCategory);
		try {
			List<TSaasTagCategory> list = saasCategoryService.getList(saasTagCategory);
			Long count = saasCategoryService.count(saasTagCategory);
			pageabel.setContent(list);
			pageabel.setTotal(count);
			JSON.toJSON(pageabel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageabel;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加SaaS标签页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:26:08 <br/>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(Model model){
		model.addAttribute("isType", "add");
		return "business_statistics/saasCategoryEdit";
		
	}
	
	/**
	 * 
	 * 方法描述：保存SaaS标签信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:29:51 <br/>
	 * @param saasCategory
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TSaasTagCategory saasCategory){
		Resultable result=null;
		result=saasCategoryService.saveInfo(saasCategory);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加SaaS标签选项页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:26:08 <br/>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="saasTagEdit")
	public String saasTagEdit(Model model){
		return "business_statistics/saasTagEdit";
	}
	
	/**
	 * 
	 * 方法描述：跳转到SaaS标签编辑页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午5:08:14 <br/>
	 * @param saasCategoryReq
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TSaasTagCategory saasCategoryReq){
		ModelAndView modelAndView =new ModelAndView();
		Long id = saasCategoryReq.getId();
		TSaasTagCategory saasCategory = saasCategoryService.getObject(id);
		modelAndView.addObject("isType", "update");
		modelAndView.addObject("saasCategory",saasCategory);
		modelAndView.setViewName("business_statistics/saasCategoryEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：保存SaaS标签信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:29:51 <br/>
	 * @param saasCategory
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TSaasTagCategory saasCategory){
		Resultable result=null;
		result=saasCategoryService.updateInfo(saasCategory);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：根据标签分类Id获取标签选项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="getSaasTags")
	@ResponseBody
	public Object getSaasTags(TSaasTag saasTag){
		Pageable<TSaasTag> pageable = new Pageable<TSaasTag>(saasTag);
		//filterVal 为tagCategoryId的值 
		String filterVal = saasTag.getFilterVal();
		if(StringUtils.isNotBlank(filterVal)){
			saasTag.setTagCategoryId(Long.valueOf(filterVal));
		}
		List<TSaasTag> saasTagList = saasTagService.getList(saasTag);
		pageable.setContent(saasTagList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：根据标签属性Type获取关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="getTagsByType")
	@ResponseBody
	public Object getTagsByType(TSaasTag saasTag){
		Pageable<TSaasTag> pageable = new Pageable<TSaasTag>(saasTag);
		//filterVal 为t_saas_tag_category的type值 
		String filterVal = saasTag.getFilterVal();
		if(StringUtils.isNotBlank(filterVal)){
			saasTag.setType(Long.valueOf(filterVal));
		}
		List<TSaasTag> saasTagList = saasTagService.getTagsByType(saasTag);
		pageable.setContent(saasTagList);
		return pageable;
	}

}
