package com.xmniao.xmn.core.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.system_settings.controller.UserController;
import com.xmniao.xmn.core.system_settings.entity.TAuthority;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.system_settings.service.RoleAuthorityService;
import com.xmniao.xmn.core.system_settings.service.UserService;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AuthorityHandler;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LoginController
 * 
 * 类描述： 登录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月4日 下午9:31:46
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	MongoBaseService mongoBaseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleAuthorityService roleAuthorityService;
	
	@Autowired
	private UserController userController;
	
	@RequestMapping(value = "login")
	public String loginPage(HttpServletRequest request) {
		this.log.info("LoginController-->login");
		HttpSession session  = request.getSession(false);
		if(null !=  session){
			Object user =session .getAttribute("currentUs");
			if(user!=null){
				return "redirect:main.jhtml";
			}else{
				return "login";
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "ulogin",method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, @RequestParam("uname") String uname, @RequestParam("upassword") String upassword, @RequestParam("ucaptcha") String ucaptcha) {
		this.log.info("LoginController-->ulogin");
		HttpSession session  = request.getSession(true);
		Object  kaptcha = session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(null !=kaptcha){
			String code = (String) kaptcha;
			boolean success=false;
			Integer statu = null;
			Object data=null;
			try {
				if(ucaptcha.equalsIgnoreCase(code)){
					TUser u= new TUser();
					u.setUsername(uname);
					u.setPassword(NMD5.Encode(upassword));
					TUser user =userService.loginCheck(u);
					statu = checkUser(user);
					if(statu==0){
						boolean bool  = setLoginInfo(user,request);
						if(bool){
							Cookie cookie = new Cookie("flag", "flag");
							response.addCookie(cookie);
							success=true;
							data = "main.jhtml";
						}else{
							statu = -6;
						}
					}
				}else{
					statu = -7;	
				}
			} catch (Exception e) {
				statu = -8;
				log.error(getMsg(statu),e);
			}finally{
				session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			}
			return new Resultable(success,getMsg(statu),data);
		}
		return new Resultable(false,"验证码未填写");
	}
	
	
	/**
	 * 检查用户状态
	 * @param user
	 * @return
	 */
	private int checkUser(TUser user){
		int statu=-1;
		try {
			if (null == user) {
				statu=-1;
			} else // 是否被启用
			if (false == user.getIsEnabled()) {
				statu = -2;
			} else if (true == user.getIsLocked()) {// 是否被锁定
				statu = -3;
			} /*else if (user.getLockedDate() != null && DateUtil.formatDate(user.getLockedDate(), "").equals(DateUtil.getNow(""))) {// 当前日期是否被锁定登录
				statu = -4;
			}*/else if(null == user.getRoleId()){
				statu = -5;
			}else{
				statu = 0;
			}
		} catch (Exception e) {
		}
		return statu;
	}
	
	private String getMsg(int statu){
		String msg=null;
		switch (statu) {
		case 0:
			msg="登录成功,正在为你跳转!";
			break;
		case -1:
			msg="用户名或者密码错误！";
			break;
		case -2:
			msg="该用户未被启用！";
			break;
		case -3:
			msg="该用户以被锁定，无法登录！";
			break;
		case -4:
			msg="今日登录以被锁定，无法登录！";
			break;
		case -5:
			msg="该用户未绑定角色！";
			break;
		case -6:
			msg="该用户无访问系统权限！";
			break;
		case -7:
			msg="验证码错误!";
			break;
		case -8:
			msg="登录异常,请稍后再试！";
			break;
			
		}
		return msg;
	}
	
	/**
	 * 设置用户登录信息
	 * @param user
	 * @param session
	 * @param request
	 */
	private boolean setLoginInfo(TUser user,HttpServletRequest request){
		HttpSession session = request.getSession(true);
		//用户权限内处理
		boolean bool = userAuhandle(user.getRoleId(),session);
		if(bool){
			//更新用户数据
			updateUserInfo(user,request);
			session.setAttribute("currentUs", user);
		}
		return bool;
	}
	
	/**
	 * 权限处理
	 * @param rid
	 */
	private boolean userAuhandle(Long rid,HttpSession session){
		if(rid==1L){
			//管理员权限
			setSeesionValue(null,session);
		}else{
			//用户对于角色拥有权限
			List<Long> authoritys = roleAuthorityService.getAuthority(rid);
			//用户无任何权限
			if(authoritys==null || authoritys.isEmpty()){
				return false;
			}
			//左侧栏与按钮权限
			setSeesionValue(authoritys,session);
		}	
		return true;
	}
	
	/**
	 * 设置权限
	 * @param authoritys
	 * @param session
	 */
	private void setSeesionValue(List<Long> authoritys,HttpSession session){
		//按钮权限模块
		Map<String, Boolean> btnAu = new HashMap<String, Boolean>();
		//左侧显示栏权限模块
		Map<Long, List<TAuthority>> leftShowAu =new HashMap<Long, List<TAuthority>>();
		
		//用户访问范围权限
		List<String> accessScope =new ArrayList<String>();
		if(null == authoritys){
			//超级管理员左侧栏与按钮权限
			AuthorityHandler.getAuthorityHanlde().getAdminUser(btnAu, leftShowAu, accessScope);
		}else{
			//左侧栏与按钮权限
			AuthorityHandler.getAuthorityHanlde().getUserAuth(authoritys, btnAu, leftShowAu,accessScope);
		}
		session.setAttribute("leftShow", leftShowAu);
		session.setAttribute("btnAu", btnAu);
		session.setAttribute("accessScope", accessScope);
	}
	
	/**
	 * 更新登录用户数据
	 * @param user
	 * @param request
	 */
	private void updateUserInfo(TUser user,HttpServletRequest request){
		//更新用户数据
		//登录ip与时间
		TUser u = new TUser(user.getUserId());
		u.setLoginDate(new Date());
		u.setLoginIp(StringUtils.getIpAddr(request));
		userService.update(u);
	}
	
	
	
	
	@RequestMapping(value = "left")
	public String left() {
		this.log.info("LoginController-->left");
		return "left";
	}
	
	@RequestMapping(value = "houmen")
	public String houmen(HttpServletRequest request) {
		this.log.info("LoginController-->houmen");
		HttpSession session  = request.getSession(true);
		TUser u= new TUser();
		u.setRoleId(1L);
		setSeesionValue(null,session);
		session.setAttribute("currentUs", u);
		return "main";
	}
	
	@RequestMapping(value = "top")
	public String top() {
		this.log.info("LoginController-->top");
		return "top";
	}
	
	@RequestMapping(value = "title")
	public String title() {
		this.log.info("LoginController-->title");
		return "title";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "right")
	public String right(HttpServletRequest request) {
		this.log.info("LoginController-->right");
/*		HttpSession session = request.getSession();
		if(null !=  session){
		 	Map<Long, List<TAuthority>> leftShowAu = (Map<Long, List<TAuthority>>)session.getAttribute("leftShow");
			if(null != leftShowAu && !leftShowAu.isEmpty()){
				String url=leftShowAu.get(leftShowAu.get(0l).get(0).getId()).get(0).getAuthorityUrl();
				return "redirect:"+url+".jhtml";
			}
		}*/
		return "right";
	}
	
	@RequestMapping(value = "main")
	public String main() {
		this.log.info("LoginController-->main");
		return "main";
	}
	
	@RequestMapping(value = "logout")
	public String loginOut(HttpServletRequest request,HttpServletResponse response) {
		this.log.info("LoginController-->logout");
		HttpSession session = request.getSession(false);
		if(null !=  session){
			Cookie cookie = new Cookie("flag", "flag");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			session.invalidate();
			session = null;
		}
		
		return "redirect:login.jhtml";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param password
	 * @param aginpassword
	 * @return
	 */
	@RequestMapping(value = "/currentChangePassword", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePwd(HttpServletRequest request,@RequestParam("password") String password, @RequestParam("repassword") String aginpassword) {
			return userController.updatePwd(request, String.valueOf(ResultUtil.getCurrentUser(request).getUserId()), password, aginpassword);
	}
}
