package com.xmniao.xmn.core.system_settings.controller;

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
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.system_settings.service.RoleService;
import com.xmniao.xmn.core.system_settings.service.UserService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RoleController
 * 
 * 类描述： 角色
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时08分19秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@RequestLogging(name="角色管理")
@Controller
@RequestMapping(value = "system_settings/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	

	@Autowired
	private UserService userService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "system_settings/roleList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TRole role) {
		Pageable<TRole> pageable = new Pageable<TRole>(role);
		pageable.setContent(roleService.getList(role));
		pageable.setTotal(roleService.count(role));
		return pageable;
	}
	
	
	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/roleUserList/init")
	public ModelAndView roleUserInit() {
		ModelAndView modelAndView = new ModelAndView("system_settings/roleUserList");
		modelAndView.addObject("requestInit", "system_settings/role/init/roleUserList/list");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/roleUserList/list")
	@ResponseBody
	public Object roleUserList(TUser user) {
		Pageable<TUser> pageable = new Pageable<TUser>(user);
		pageable.setContent(userService.getList(user));
		pageable.setTotal(userService.count(user));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除角色")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("roleId") String roleId) {
		Resultable  resultable= null;
		try {
			roleService.delRole(roleId);
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("删除角色异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"角色编号",roleId,"删除操作","删除"};
			roleService.fireLoginEvent(s, resultable.getSuccess()?1:0);	
		}
		return resultable;
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("system_settings/editRole");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加角色")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TRole role) {
		Resultable resultable = null;
		try {
			role.setIsSystem(0);
			roleService.add(role);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"角色名称",role.getRoleName(),"新增"};
			roleService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("roleId") String roleId) {
		ModelAndView modelAndView = new ModelAndView("system_settings/editRole");
		modelAndView.addObject("isType", "update");
		try {
			TRole role = roleService.getObject(new Long(roleId));
			this.log.info(role);
			modelAndView.addObject("role", role);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="修改角色")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TRole  role) {
		Resultable resultable = null;
		String[] s={"角色编号",role.getRoleId().toString(),"修改操作","修改"};
		try {
			roleService.update(role);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			roleService.fireLoginEvent(s, resultable.getSuccess()?1:0);
			return resultable;
		}
	}
	
	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="修改角色内置属性")
	@RequestMapping(value = "/updateNz")
	@ResponseBody
	public Object updateNz(TRole  role) {
		Resultable resultable = null;
		try {
			roleService.update(role);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"角色编号",role.getRoleId().toString(),"修改角色内置操作","修改"};
			roleService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

}