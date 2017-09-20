package com.xmniao.xmn.core.nagivate.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.nagivate.service.CategoryNavigateService;
import com.xmniao.xmn.core.nagivate.util.NavigateConstant;
import com.xmniao.xmn.core.navigate.entity.CategoryNavigate;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CategoryNavigateComtroller
 * 
 * 类描述： 分类导航管理
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年9月22日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="分类导航管理")
@Controller
@RequestMapping(value = "user_terminal/navigate")
public class CategoryNavigateComtroller extends BaseController {

	@Autowired
	private CategoryNavigateService categoryNavigateService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "navigate/allNavigateList";
	}
	
	/**
	 * 
	 * list(一级导航列表数据初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(CategoryNavigate categoryNavigate) {
		this.log.info("CategoryNavigateComtroller-->list categoryNavigate=" + categoryNavigate);
		Pageable<CategoryNavigate> pageable = new Pageable<CategoryNavigate>(categoryNavigate);
		pageable.setContent(categoryNavigateService.getNavigateList(categoryNavigate));
		pageable.setTotal(getRecordCount(categoryNavigate));
		return pageable;
	}
	
	/**
	 * 
	 * addInit(一级导航添加初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("navigate/nagivateEdit");
		modelAndView.addObject("isType", "add");
		CategoryNavigate categoryNavigate = new CategoryNavigate();
		categoryNavigate.setType(NavigateConstant.ONE_LEVEL_NAVIGATE); //一级导航
		modelAndView.addObject("categoryNavigate", categoryNavigate);
		modelAndView.addObject("maxOrder", categoryNavigateService.getMaxOrderByType(categoryNavigate));
		return modelAndView;
	}
	
	/**
	 * 
	 * add(添加分类导航)
	 * 
	 * @author：wangzhimin
	 */
	@RequestLogging(name="添加一级分类导航信息")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(HttpServletRequest request, CategoryNavigate categoryNavigate) {
		boolean flag = true;
		try{
			if(ckeckOrder(categoryNavigate)){
				categoryNavigateService.add(setParameter(request, categoryNavigate, "add"));
				log.info("添加一级分类导航成功");
			}else{
				return new Resultable(false, "一级导航数量最多5条");
			}
		}catch(Exception e){
			log.info("添加一级分类导航失败: " + e);
			flag = false;
		}
		return getAddResultableAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 
	 * updateInit(一级导航信息修改初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(@RequestParam("nId") String nId) {
		ModelAndView modelAndView = new ModelAndView("navigate/nagivateEdit");
		modelAndView.addObject("isType", "update");
		try {
			modelAndView.addObject("categoryNavigate", categoryNavigateService.getNavigateObject(Long.valueOf(nId)));
		} catch (Exception e) {
			this.log.error("修改初始化异常", e);
		} 
		return modelAndView;
	}
	
	/**
	 * 
	 * update(修改)
	 * 
	 * @author：wangzhimin
	 */
	@RequestLogging(name="修改一级分类导航")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(HttpServletRequest request, CategoryNavigate categoryNavigate) {
		boolean flag = true;
		try {
			categoryNavigateService.update(setParameter(request, categoryNavigate, "update"));
			log.info("修改一级分类导航成功: " + categoryNavigate);
		} catch (Exception e) {
			log.info("修改一级分类导航失败: " + e);
			flag = false;
		}
		return getUpdateResultableAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 一级分类导航信息查看
	 * @param nId
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView showNavigateDetail(@RequestParam("nId") String nId){
		ModelAndView modelAndView = new ModelAndView("navigate/navigateView");
		try {
			modelAndView.addObject("categoryNavigate", categoryNavigateService.getNavigateObject(Long.valueOf(nId)));
		} catch (Exception e) {
			this.log.error("获取查看数据异常", e);
		} 
		return modelAndView;
	}
	
	/**
	 * 修改一级分类导航位置
	 * @param categoryNavigate
	 * @return
	 */
	@RequestLogging(name="修改一级分类导航位置")
	@RequestMapping("/sort")
	@ResponseBody
	public Object updateOrder(CategoryNavigate categoryNavigate){
		boolean flag = true;
		try {
			categoryNavigate.setType(NavigateConstant.ONE_LEVEL_NAVIGATE);
			String value = checkIsCanMove(categoryNavigate).get("key");
			if(null == value){
				categoryNavigateService.updateOrder(categoryNavigate);
				log.info("修改一级分类导航位置成功");
			}else{
				return new Resultable(false, value);	
			}
		}  catch (Exception e) {
			flag = false;
			log.info("修改一级分类导航位置失败");
			categoryNavigateService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("导航位置更新异常", e, new Object[]{categoryNavigate}).getMessage()), 0);
		}
		return  fireLogAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 验证移动的是否是第一个或者最后一个 
	 * @param categoryNavigate
	 * @return
	 */
	private Map<String, String> checkIsCanMove(CategoryNavigate categoryNavigate){
		Map<String, String> map = new HashMap<>();
		Integer order = categoryNavigate.getOrder();
		if(categoryNavigate.getFlag() == NavigateConstant.UP_MOVE){
			if(order <= 1){
				map.put("key", "该分类导航不能再次上移");
			}
		}else{
			if(order >= getRecordCount(categoryNavigate)){
				map.put("key", "该分类导航不能再次下移");
			}
		}
		return map;
	}
	
	/**
	 * 记录日志和返回结果
	 * @param flag
	 * @param categoryNavigate
	 * @return
	 */
	private Resultable fireLogAndReturn(boolean flag, CategoryNavigate categoryNavigate) {
		if(flag){
			String[] str = new String[]{categoryNavigate.getType() == NavigateConstant.ONE_LEVEL_NAVIGATE ? "一级分类导航编号为" : "二级分类导航编号为", categoryNavigate.getNId(), "更新位置", "更新"};
			categoryNavigateService.fireLoginEvent(str, 1);
		}
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}

	/**
	 * 
	 * secondLevelList(二级导航列表数据初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/secondLevel/init/list")
	@ResponseBody
	public Object secondLevelList(CategoryNavigate categoryNavigate) {
		this.log.info("CategoryNavigateComtroller-->list categoryNavigate=" + categoryNavigate);
		Pageable<CategoryNavigate> pageable = new Pageable<CategoryNavigate>(categoryNavigate);
		pageable.setContent(categoryNavigateService.getNavigateList(categoryNavigate));
		pageable.setTotal(getRecordCount(categoryNavigate));
		return pageable;
	}
	
	/**
	 * 
	 * secondLevelAddInit(二级导航添加初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/secondLevel/add/init")
	public ModelAndView secondLevelAddInit() {
		ModelAndView modelAndView = new ModelAndView("navigate/secondLevelNagivateEdit");
		modelAndView.addObject("isType", "add");
		CategoryNavigate categoryNavigate = new CategoryNavigate();
		categoryNavigate.setType(NavigateConstant.SECOND_LEVEL_NAVIGATE);
		modelAndView.addObject("categoryNavigate", categoryNavigate);
		modelAndView.addObject("maxOrder", categoryNavigateService.getMaxOrderByType(categoryNavigate));
		return modelAndView;
	}
	
	/**
	 * 
	 * secondLevelAdd(添加二级分类导航)
	 * 
	 * @author：wangzhimin
	 */
	@RequestLogging(name="添加二级分类导航信息")
	@RequestMapping(value = "/secondLevel/add")
	@ResponseBody
	public Object secondLevelAdd(HttpServletRequest request, CategoryNavigate categoryNavigate) {
		boolean flag = true;
		try{
			if(ckeckOrder(categoryNavigate)){
				categoryNavigateService.add(setParameter(request, categoryNavigate, "add"));
				log.info("添加二级分类导航成功");
			}else{
				return new Resultable(false, "二级导航数量最多3条");
			}
		}catch(Exception e){
			log.info("添加二级分类导航失败: " + e);
			flag = false;
		}
		return getAddResultableAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 
	 * secongLevelNavigateUpdateInit(二级导航信息修改初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "/secondLevel/update/init")
	public ModelAndView secongLevelNavigateUpdateInit(@RequestParam("nId") String nId) {
		ModelAndView modelAndView = new ModelAndView("navigate/secondLevelNagivateEdit");
		modelAndView.addObject("isType", "update");
		try {
			modelAndView.addObject("categoryNavigate", categoryNavigateService.getNavigateObject(Long.valueOf(nId)));
		} catch (Exception e) {
			this.log.error("修改初始化异常", e);
		} 
		return modelAndView;
	}
	
	/**
	 * 
	 * secongLevelNavigateUpdate(修改二级导航信息)
	 * 
	 * @author：wangzhimin
	 */
	@RequestLogging(name="修改二级分类导航")
	@RequestMapping(value = "/secondLevel/update")
	@ResponseBody
	public Object secongLevelNavigateUpdate(HttpServletRequest request, CategoryNavigate categoryNavigate) {
		boolean flag = true;
		try {
			categoryNavigateService.update(setParameter(request, categoryNavigate, "update"));
			log.info("修改二级分类导航成功: " + categoryNavigate);
		} catch (Exception e) {
			log.info("修改二级分类导航失败: " + e);
			flag = false;
		}
		return getUpdateResultableAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 二级分类导航信息查看
	 * @param nId
	 * @return
	 */
	@RequestMapping("/secondLevel/detail")
	public ModelAndView showSecondLevelNavigateDetail(@RequestParam("nId") String nId){
		ModelAndView modelAndView = new ModelAndView("navigate/navigateView");
		try {
			modelAndView.addObject("categoryNavigate", categoryNavigateService.getNavigateObject(Long.valueOf(nId)));
		} catch (Exception e) {
			this.log.error("获取查看数据异常", e);
		} 
		return modelAndView;
	}
	
	/**
	 * 修改二级分类导航位置
	 * @param categoryNavigate
	 * @return
	 */
	@RequestLogging(name="修改二级分类导航位置")
	@RequestMapping("/secondLevel/sort")
	@ResponseBody
	public Object updateSecondLevelOrder(CategoryNavigate categoryNavigate){
		boolean flag = true;
		try {
			categoryNavigate.setType(NavigateConstant.SECOND_LEVEL_NAVIGATE);
			String value = checkIsCanMove(categoryNavigate).get("key");
			if(null == value){
				categoryNavigateService.updateOrder(categoryNavigate);
				log.info("修改二级分类导航位置成功");
			}else{
				return new Resultable(false, value);	
			}
		}  catch (Exception e) {
			flag = false;
			log.info("修改二级分类导航位置失败");
			categoryNavigateService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("导航位置更新异常", e, new Object[]{categoryNavigate}).getMessage()), 0);
		}
		return  fireLogAndReturn(flag, categoryNavigate);
	}
	
	/**
	 * 验证添加数量是否在指定范围内
	 * @param categoryNavigate
	 * @return
	 */
	private boolean ckeckOrder(CategoryNavigate categoryNavigate){
		boolean flag = true;
		Integer order = categoryNavigate.getOrder();
		Integer type = categoryNavigate.getType();
		if(null == order){ //第一次添加
			categoryNavigate.setOrder(1);
		}else{
			categoryNavigate.setOrder(order + 1);
		}
		if(NavigateConstant.ONE_LEVEL_NAVIGATE == type){ //一级导航最多5条信息
			if(categoryNavigate.getOrder() > NavigateConstant.ONE_LEVEL_NAVIGATE_ORDER_SIZE){  
				flag = false;
			}
		}else if(NavigateConstant.SECOND_LEVEL_NAVIGATE == type){  //二级导航最多3条信息 
			if(categoryNavigate.getOrder() > NavigateConstant.SECOND_LEVEL_NAVIGATE_ORDER_SIZE){
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 设置必要的参数
	 * @param request
	 * @param categoryNavigate
	 * @return
	 */
	private CategoryNavigate setParameter(HttpServletRequest request, CategoryNavigate categoryNavigate, String flag){
		Date date = new Date();
		String userName = ResultUtil.getCurrentUser(request).getUsername();
		if("add".equals(flag)){
			categoryNavigate.setAdd_user(userName);
			categoryNavigate.setAdd_time(date);
			categoryNavigate.setUp_user(userName);
			categoryNavigate.setUp_time(date);
		}else if("update".equals(flag)){
			categoryNavigate.setUp_user(userName);
			categoryNavigate.setUp_time(date);
		}
		return categoryNavigate;
	}
	
	/**
	 * 记录添加日志信息
	 * @param flag
	 * @param categoryNavigate
	 * @return
	 */
	private Resultable getAddResultableAndReturn(boolean flag, CategoryNavigate categoryNavigate){
		String[] str = new String[]{categoryNavigate.getType() == NavigateConstant.ONE_LEVEL_NAVIGATE ? "一级分类导航信息" : "二级分类导航信息", categoryNavigate.getTitle(), "新增"};
		categoryNavigateService.fireLoginEvent(str, flag ? 1 : 0);
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}
	
	/**
	 * 记录修改日志信息
	 * @param flag
	 * @param categoryNavigate
	 * @return
	 */
	private Object getUpdateResultableAndReturn(boolean flag, CategoryNavigate categoryNavigate) {
		String[] str = new String[]{categoryNavigate.getType() == NavigateConstant.ONE_LEVEL_NAVIGATE ? "一级分类导航编号为" : "二级分类导航编号为", categoryNavigate.getNId(), "修改", "修改"};
		categoryNavigateService.fireLoginEvent(str, flag ? 1 : 0);
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}
	
	/**
	 * 根据类别获取记录总数
	 * @param categoryNavigate
	 * @return
	 */
	private Long getRecordCount(CategoryNavigate categoryNavigate){
		return categoryNavigateService.getNavigateListcount(categoryNavigate);
	}
}
