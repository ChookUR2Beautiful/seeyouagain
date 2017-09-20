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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographPage;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographTemplate;
import com.xmniao.xmn.core.business_statistics.service.TMicrographPageService;
import com.xmniao.xmn.core.business_statistics.service.TMicrographTemplateService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateController
 * 
 * 类描述： SaaS模板Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:56:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("saasTemplate/manage")
public class TMicrographTemplateController extends BaseController {
	
	/**
	 * 注入saas模板服务
	 */
	@Autowired
	private TMicrographTemplateService micrographTemplateService;
	
	/**
	 * 
	 */
	@Autowired
	private TMicrographPageService micrographPageService;
	
	
	
	/**
	 * 
	 * 方法描述：跳转到模板列表页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "business_statistics/saasTemplateManage";
	}
	
	
	/**
	 * 
	 * 方法描述：加载模板列表数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object initList(TMicrographTemplate template, Model model) {
		Pageable<TMicrographTemplate> pageabel = new Pageable<TMicrographTemplate>(template);
		try {
			List<TMicrographTemplate> list = micrographTemplateService.getList(template);
			Long count = micrographTemplateService.count(template);
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
	 * 方法描述：跳转到添加模板页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "business_statistics/saasTemplateEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TMicrographTemplate  template){
		Resultable result = new Resultable();
		try {
			
			micrographTemplateService.saveInfo(template);
			
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加saas模板失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑saas模板页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param template
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TMicrographTemplate  template){
		ModelAndView modelAndView = new ModelAndView();
		long templateId = template.getId();
		TMicrographPage micrographPage=new TMicrographPage();
		micrographPage.setTemplateId((int) templateId);
		TMicrographTemplate templateInfo = micrographTemplateService.getObject(templateId);
		List<TMicrographPage> pageList = micrographPageService.getList(micrographPage);
		templateInfo.setPageList(pageList);
		modelAndView.addObject("templateInfo", templateInfo);
		modelAndView.setViewName("business_statistics/saasTemplateEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改SaaS模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param template
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TMicrographTemplate template){
		Resultable result=new Resultable();
		try {
			
			micrographTemplateService.updateInfo(template);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改SaaS模板失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除模板信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count=micrographTemplateService.delete(ids.split(","));
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量更新模板上线状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateBatch")
	@ResponseBody
	public Object updateBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=micrographTemplateService.updateBatch(ids,status);
		return result;
	}
	

}
