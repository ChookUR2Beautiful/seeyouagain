/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographPage;
import com.xmniao.xmn.core.business_statistics.service.TMicrographPageService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateController
 * 
 * 类描述： 微图助力模板页面Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:56:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("saasPage/manage")
public class TMicrographPageController extends BaseController {
	
	/**
	 * 注入微图助力模板页面Service
	 */
	@Autowired
	private TMicrographPageService pageService;
	
	/**
	 * 
	 * 方法描述：跳转到新增模板page页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-2下午2:44:33 <br/>
	 * @param materialCarousel
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(TMicrographPage micrograpPage,Model model){
		Integer templateId = micrograpPage.getTemplateId();
		model.addAttribute("isType", "add");
		model.addAttribute("templateId", templateId);
		return "business_statistics/saasPageEdit";
	}
	
	/**
	 * 
	 * 方法描述：保存页面信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午4:58:45 <br/>
	 * @param micrograpPage
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TMicrographPage micrograpPage){
		Resultable result=new Resultable();
		try {
			pageService.saveInfo(micrograpPage);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("执行TMicrographPageController---add方法异常:"+e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑模板page页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-2下午2:44:33 <br/>
	 * @param materialCarousel
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TMicrographPage micrograpPage,Model model){
		Integer id = micrograpPage.getId();
		TMicrographPage pageInfo = pageService.getObject(new Long(id));
		Integer templateId = pageInfo.getTemplateId();
		model.addAttribute("isType", "update");
		model.addAttribute("templateId", templateId);
		model.addAttribute("pageInfo", pageInfo);
		return "business_statistics/saasPageEdit";
	}
	
	/**
	 * 
	 * 方法描述：修改模板page信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param micrograpPage
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TMicrographPage micrograpPage){
		Resultable result=new Resultable();
		try {
			
			pageService.updateInfo(micrograpPage);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改模板页面失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：获取模板page基础数据(模板page名称，背景图等) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午9:54:27 <br/>
	 * @param materialCarousel
	 * @return
	 */
	@RequestMapping(value="update/getBaseInfo")
	@ResponseBody
	public Resultable updateGetBaseInfo(TMicrographPage micrograpPage){
		Resultable result=new Resultable();
		result=pageService.setPageBaseInfo(micrograpPage);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取模板元数据(文案框架，图片框架) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午9:54:27 <br/>
	 * @param micrograpPage
	 * @return
	 */
	@RequestMapping(value="update/getSourceInfo")
	@ResponseBody
	public Resultable updateGetSourceInfo(TMicrographPage micrograpPage){
		Resultable result=new Resultable();
		result=pageService.setPageSrcInfo(micrograpPage);
		return result;
	}
}
