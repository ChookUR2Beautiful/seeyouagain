package com.xmniao.xmn.core.system_settings.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UserController
 * 
 * 类描述： 用户
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月13日 下午5:03:46
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */

@RequestLogging(name="用户管理")
@Controller
@RequestMapping(value = "system_settings/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "system_settings/user";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TUser user) {
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
	@RequestLogging(name="删除用户")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("userId") String userId) {
		Resultable resultable = null;
		try {
			List<String> die = new ArrayList<String>();
			StringUtils.paresToList(userId, die, ",");
			Integer resultNum = userService.deleteUid(die);
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");	
			}else{
				resultable = new Resultable(false, "操作失败");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"用户编号",userId,"删除操作","删除"};
			userService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @ author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("system_settings/editUser");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * author：zhou'sheng
	 */
	@RequestLogging(name="添加用户")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TUser user) {
		Resultable resultable = null;
		try {
			user.setPassword(NMD5.Encode("123456"));
			if (user.getIsLocked()) {
				user.setLockedDate(new Date());
			}
			userService.add(user);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"用户名称",user.getUsername(),"新增"};
			userService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("system_settings/editUser");
		modelAndView.addObject("isType", "update");
		try {
			TUser user = userService.getObject(new Long(userId));
			this.log.info(user);
			modelAndView.addObject("user", user);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * author：zhou'sheng
	 */
	@RequestLogging(name="修改用户")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TUser user) {
		Resultable resultable = null;
		try {
			if (null != user.getIsLocked() && user.getIsLocked()) {
				user.setLockedDate(new Date());
			}
			userService.update(user);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"用户编号",user.getUserId().toString(),"修改操作","修改"};
			userService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;
	}
	
	
	@RequestMapping(value = "/changePassword/init")
	public ModelAndView changePassword(HttpServletRequest request, @RequestParam("uid") String userId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("uid", userId);
		modelAndView.setViewName("system_settings/changePassword");
		return modelAndView;

	}
	
	@RequestLogging(name="修改用户密码")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePwd(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("repassword") String aginpassword) {
		boolean bool = true;
		Resultable result = null;
		try {
			if (userId.equals("") || !password.equals(aginpassword)) {
				bool = false;
			}
			if (password.length() > 20 || aginpassword.length() > 20) {
				bool = false;
			}
			if (!bool) {
				result =  new Resultable(false, "修改失败");
			}else{
				TUser user = new TUser();
				user.setUserId(Long.parseLong(userId));
				user.setPassword(NMD5.Encode(password));
				userService.update(user);
				result = new Resultable(true, "修改成功");
			}
		} catch (Exception e) {
			this.log.error("修改密码异常", e);
			result =  new Resultable(false, "修改失败");
		}finally{
			String[] s={"用户编号",userId,"修改密码操作","修改密码"};
			userService.fireLoginEvent(s, result.getSuccess()?1:0);
		}
		return result;
	}
	
	@RequestMapping(value = "/bindRole/init")
	public ModelAndView bindRoleInit(HttpServletRequest request, @RequestParam("uid") String userId) {
		TUser user = userService.getObject(Long.parseLong(userId));
		List<TRole> list = roleService.getOtherRoleList(user.getRoleId());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("uid", user.getUserId());
		modelAndView.addObject("role", roleService.getObject(user.getRoleId()));
		modelAndView.addObject("rList", list);
		modelAndView.setViewName("system_settings/getRole");
		return modelAndView;

	}
	
	@RequestLogging(name="绑定角色")
	@RequestMapping(value = "/bindRole")
	@ResponseBody
	public Object bindRole(TUser user) {
		Resultable resultable = null;
		try {
			if (null != user.getIsLocked() && user.getIsLocked()) {
				user.setLockedDate(new Date());
			}
			userService.update(user);
			this.log.info("绑定角色成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("绑定角色异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"用户编号",user.getUserId().toString(),"绑定角色操作","绑定角色"};
			userService.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
		return resultable;

	}
	
	@RequestMapping(value = "/checkUsername")
	@ResponseBody
	public boolean checkAccount(HttpServletRequest request, @RequestParam("username") String username) {
		long num = userService.getUsernameCount(username);
		return (num == 0);
	}

}
