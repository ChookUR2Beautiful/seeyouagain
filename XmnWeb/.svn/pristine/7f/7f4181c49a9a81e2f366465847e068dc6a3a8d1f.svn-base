/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.entity.TCelebrityAriticle;
import com.xmniao.xmn.core.business_statistics.service.TCelebrityAriticleService;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityController
 * 
 * 类描述： SaaS网红文章Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:56:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("saasArticle/manage")
public class TCelebrityArticleController extends BaseController {
	
	/**
	 * 注入网红文章服务
	 */
	@Autowired
	private TCelebrityAriticleService articleService;
	
	
	/**
	 * 注入ckeditService服务
	 */
	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;
	
	/**
	 * 
	 * 方法描述：跳转到文章列表页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "business_statistics/saasArticleManage";
	}
	
	
	/**
	 * 
	 * 方法描述：加载文章列表数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object initList(TCelebrityAriticle article, Model model) {
		Pageable<TCelebrityAriticle> pageabel = new Pageable<TCelebrityAriticle>(article);
		try {
			List<TCelebrityAriticle> list = articleService.getList(article);
			Long count = articleService.count(article);
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
	 * 方法描述：跳转到添加文章页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-12下午2:14:53 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public ModelAndView addInit(TCelebrityAriticle article){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("article", article);
		modelAndView.addObject("isType", "add");
		modelAndView.setViewName( "business_statistics/saasArticleEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：添加文章 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TCelebrityAriticle article){
		Resultable result = new Resultable();
		try {
			articleService.saveInfo(article);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加saas文章失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑文章页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param article
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TCelebrityAriticle article){
		ModelAndView modelAndView = new ModelAndView();
		long id = article.getId();
		TCelebrityAriticle articleInfo = articleService.getObject(id);
		modelAndView.addObject("article", articleInfo);
		modelAndView.addObject("isType", "update");
		modelAndView.setViewName("business_statistics/saasArticleEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改文章 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param article
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TCelebrityAriticle article){
		Resultable result=new Resultable();
		try {
			articleService.updateInfo(article);
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
	 * 方法描述：富文本编辑器文件上传 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6下午9:33:21 <br/>
	 * @param filedata
	 * @param request
	 * @param response
	 */
	@RequestMapping(value ={"add/ckeditorUpload","update/ckeditorUpload"} , method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}
	
	/**
	 * 
	 * 方法描述：删除文章信息
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
				count=articleService.delete(ids.split(","));
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
	

}
