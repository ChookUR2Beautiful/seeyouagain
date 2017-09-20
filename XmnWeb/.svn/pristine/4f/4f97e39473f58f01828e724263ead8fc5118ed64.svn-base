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
import com.xmniao.xmn.core.business_statistics.entity.TCelebrity;
import com.xmniao.xmn.core.business_statistics.service.TCelebrityService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityController
 * 
 * 类描述： SaaS网红角色Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:56:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("saasRole/manage")
public class TCelebrityController extends BaseController {
	
	/**
	 * 注入网红角色服务
	 */
	@Autowired
	private TCelebrityService celebrityService;
	
	/**
	 * 
	 * 方法描述：跳转到角色列表页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "business_statistics/saasCelebrityManage";
	}
	
	
	/**
	 * 
	 * 方法描述：加载角色列表数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param celebrity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object initList(TCelebrity celebrity, Model model) {
		Pageable<TCelebrity> pageabel = new Pageable<TCelebrity>(celebrity);
		try {
			List<TCelebrity> list = celebrityService.getList(celebrity);
			Long count = celebrityService.count(celebrity);
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
	 * 方法描述：跳转到添加角色页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "business_statistics/saasCelebrityEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加角色 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TCelebrity celebrity){
		Resultable result = new Resultable();
		try {
			celebrityService.add(celebrity);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加saas角色失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑saas角色页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param celebrity
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TCelebrity celebrity){
		ModelAndView modelAndView = new ModelAndView();
		long id = celebrity.getId();
		TCelebrity celebrityInfo = celebrityService.getObject(id);
		modelAndView.addObject("celebrity", celebrityInfo);
		modelAndView.setViewName("business_statistics/saasCelebrityEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改精彩视频 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param celebrity
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TCelebrity celebrity){
		Resultable result=new Resultable();
		try {
			celebrityService.update(celebrity);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改SaaS角色失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除精彩视频信息
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
				count=celebrityService.delete(ids.split(","));
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
	 * 方法描述：批量更新视频上线状态<br/>
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
		result=celebrityService.updateBatch(ids,status);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：根据网红类型获取网红角色列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29上午10:25:29 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@RequestMapping(value="getCelebrities")
	@ResponseBody
	public Object getCelebrities(TCelebrity celebrity){
		Pageable<TCelebrity> pageable = new Pageable<TCelebrity>(celebrity);
		//filterVal 为type的值 
		String filterVal = celebrity.getFilterVal();
		if(StringUtils.isNotBlank(filterVal)){
			celebrity.setType(Integer.valueOf(filterVal));
		}
		List<TCelebrity> celebrityList = celebrityService.getList(celebrity);
		pageable.setContent(celebrityList);
		return pageable;
	}
	

	/**
	 * 
	 * 方法描述：获取网红角色信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13上午9:57:22 <br/>
	 * @param celebrity
	 * @return
	 */
	@RequestMapping(value = "getCelebrityInfoById")
	@ResponseBody
	public Object getCelebrityInfoById(TCelebrity celebrity) {
		TCelebrity celebrityInfo = celebrityService.getObject(celebrity.getId());
		return celebrityInfo;
	}
}
