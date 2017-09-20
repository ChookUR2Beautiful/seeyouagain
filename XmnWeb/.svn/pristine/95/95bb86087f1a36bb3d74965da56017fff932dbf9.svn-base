package com.xmniao.xmn.core.fresh.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.PostageTemplate;
import com.xmniao.xmn.core.fresh.service.PostageTemplateService;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
	/**
	 *@ClassName:YunFeiMuBanConcroller
	 *@Description:运费模板管理
	 *@author hls
	 *@date:2016年6月16日下午4:18:05
	 */
	@RequestLogging(name="运费模板管理")
	@Controller
	@RequestMapping(value = "fresh/postagetemplate")
	public class PostageTemplateConcroller extends BaseController {
		
		@Autowired
		private PostageTemplateService postageTemplateService;
		
		/*
		 * 初始化页面
		 */
		@RequestMapping(value = "init")
		public Object init(){
			ModelAndView modelAndView = new ModelAndView("fresh/order/postagetemplate");
			PostageTemplate postageTemplate = new PostageTemplate();
			postageTemplate.setLimit(-1);
			List<PostageTemplate> postageTemplateList = postageTemplateService.getPostageTemplateList(postageTemplate);
			if(postageTemplateList!=null&&postageTemplateList.size()>0){
				modelAndView.addObject("postageTemplateList",postageTemplateList);
			}
			return modelAndView;
		}
		/**
		 * @Title:addInit
		 * @Description:添加运费模板初始化页面
		 * @return String
		 * @throw
		 */
		@RequestMapping(value = "add/init")
		public String addInit(){
			return "fresh/order/addAndUpdatePostageTemplate";
		}
		
		/**
		 * @Title:getList
		 * @Description:查询寻蜜客成员列表
		 * @param bxmer
		 * @return Object
		 * @throw
		 */
		@RequestMapping(value = "list")
		@ResponseBody
		public Object getList(HttpServletRequest req,PostageTemplate postageTemplate){
			    postageTemplate.setLimit(3);//设置每页显示记录条数
				List<PostageTemplate> postageTemplateList = postageTemplateService.getPostageTemplateList(postageTemplate);
				Long postageTemplateCount = postageTemplateService.getPostageTemplateCount(postageTemplate);
				Long pageCount = ((postageTemplateCount-1)/postageTemplate.getLimit())+1;
				Map<Object, Object> resulMap = new HashMap<>();
				resulMap.put("result", postageTemplateList);
				resulMap.put("pageCount", pageCount);
				String postageTemplateStr = JsonUtil.toJSONString(resulMap);
				return postageTemplateStr;
		}
		 /**
	     * @Title:updateInit
	     * @Description:修改业务员初始化
	     * @param request
	     * @param jointid
	     * @return ModelAndView
	     * @throw
	     */
	    @SuppressWarnings("finally")
	    @RequestMapping(value = "update/init")
	    public ModelAndView updateInit(HttpServletRequest request,String tid) {
	    	Integer id = Integer.parseInt(tid);
	        ModelAndView modelAndView = new ModelAndView("fresh/order/addAndUpdatePostageTemplate");
	        modelAndView.addObject("isType", "update");
	        try {
	        	PostageTemplate postageTemplate = postageTemplateService.getPostageTemplate(id);
	            modelAndView.addObject("postageTemplate", postageTemplate);
	        } catch (NumberFormatException e) {
	            this.log.error("修改初始异常", e);
	        } finally {
	            return modelAndView;
	        }
	    }
	    /**
	     * @Title:update
	     * @Description:修改运费模板
	     * @param postageTemplate
	     * @return Object
	     * @throw
	     */
		@RequestMapping(value = "update")
		@ResponseBody
		public Object update(PostageTemplate postageTemplate) {
			Resultable resultable = null;
			try {
				postageTemplateService.updatePostageTemplate(postageTemplate);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
			} catch (Exception e) {
				this.log.error("修改异常", e);
				resultable = new Resultable(false, "操作失败");
			}  
			return resultable;
		}
		/**
		 * @Title:add
		 * @Description:新建运费模板
		 * @param postageTemplate
		 * @return Object
		 * @throw
		 */
		@RequestMapping(value = "add")
		@ResponseBody
		public Object add(PostageTemplate postageTemplate) {
			Resultable resultable = null;
			try {
				postageTemplateService.addPostageTemplate(postageTemplate);
				this.log.info("新增成功");
				resultable = new Resultable(true, "操作成功");
			} catch (Exception e) {
				this.log.error("新增异常", e);
				resultable = new Resultable(false, "操作失败");
			}  
			return resultable;
		}
		/**
		 * @Title:delete
		 * @Description:删除运费模板
		 * @param banner
		 * @param request
		 * @return Object
		 * @throw
		 */
		@RequestMapping(value = "delete")
		@ResponseBody
		public Object delete(PostageTemplate postageTemplate,HttpServletRequest request) {
			Resultable resultable = null;
			try {
				postageTemplateService.deletePostageTemplate(postageTemplate);
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			} catch (Exception e) {
				this.log.error("删除异常", e);
				resultable = new Resultable(false, "操作失败");
			}  
			return resultable;
		}
		/**
		 * @Title:addCopy
		 * @Description:复制运费模板
		 * @param postageTemplate
		 * @return Object
		 * @throw
		 */
		@RequestMapping(value = "add/copy")
		@ResponseBody
		public Object addCopy(PostageTemplate postageTemplate) {
			Object obj = null;
			try {
				obj = postageTemplateService.addCopyPostageTemplate(postageTemplate);
				this.log.info("复制运费模板成功");
			} catch (Exception e) {
				this.log.error("复制运费模板异常", e);
			}  
			return obj;
		}
		/**
		 * @Title:initArea
		 * @Description:区域初始化
		 * @return Object
		 * @throw
		 */
		@RequestMapping(value = "initArea")
		@ResponseBody
		public Object initArea() {
			Object obj = null;
			try {
				obj = postageTemplateService.initArea();
				this.log.info("区域初始化成功");
			} catch (Exception e) {
				this.log.error("区域初始化异常", e);
			}  
			return JSON.toJSONString(obj);
		}
	}
