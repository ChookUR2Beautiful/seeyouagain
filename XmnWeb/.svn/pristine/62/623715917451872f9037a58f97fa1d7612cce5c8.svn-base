package com.xmniao.xmn.core.system_settings.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.system_settings.entity.TAuthority;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.system_settings.entity.TRoleAuthority;
import com.xmniao.xmn.core.system_settings.service.RoleAuthorityService;
import com.xmniao.xmn.core.system_settings.service.RoleService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AuthorityHandler;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RoleAuthorityController
 * 
 * 类描述： 角色资源
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时11分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@RequestLogging(name="角色权限管理")
@Controller
@RequestMapping(value = "system_settings/roleAuthority")
public class RoleAuthorityController extends BaseController {

	@Autowired
	private RoleAuthorityService roleAuthorityService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(@RequestParam("roleId")String roleId) {
		ModelAndView mv = new ModelAndView();
		try{
			Long rid =Long.parseLong(roleId);
			TRole tr = roleService.getObject(rid);
			if(null != tr){
				List<Long> lauAuthority = roleAuthorityService.getAuthority(rid);
				Map<Long, List<TAuthority>> map = AuthorityHandler.getAuthorityHanlde().getAuthorityMap(lauAuthority);
				mv.addObject("map",map);
				mv.addObject("key",0L);
				mv.addObject("tr",tr);
				mv.setViewName("system_settings/authority");
			}else{
				mv.setViewName("system_settings/roleList");
		}}catch(Exception e){
			this.log.error("获取权限列表异常", e);
			mv.setViewName("system_settings/roleList");
		}
		return mv;	
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object list(TRoleAuthority roleAuthority) {
		Pageable<TRoleAuthority> pageable = new Pageable<TRoleAuthority>(roleAuthority);
		pageable.setContent(roleAuthorityService.getList(roleAuthority));
		pageable.setTotal(roleAuthorityService.count(roleAuthority));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="删除角色权限")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = roleAuthorityService.delete(StringUtils.paresToArray(id, ","));
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
			String[] s={"角色权限编号",id,"删除操作","删除"};
			roleAuthorityService.fireLoginEvent(s, resultable.getSuccess()?1:0);
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
		ModelAndView modelAndView = new ModelAndView("system_settings/editRoleAuthority");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加角色资源")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TRoleAuthority roleAuthority) {
		Resultable resultable = null;
		try {
			roleAuthorityService.add(roleAuthority);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	
	
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="批量添加角色权限")
	@RequestMapping(value = "/init/addBatch")
	@ResponseBody
	public Object addBatch(@RequestBody TRoleAuthority[] lists) {
		Resultable resultable = null;
		try {
			if(lists.length>0){
				roleAuthorityService.deleteByRole(lists[0].getRoleId());
				String[] d={"角色编号",lists[0].getRoleId().toString(),"删除权限操作","删除权限"};
				roleAuthorityService.fireLoginEvent(d);
				if(lists.length>1){
					List<TRoleAuthority> list= new ArrayList<TRoleAuthority>(Arrays.asList(lists))  ;
					list.remove(0);
					roleAuthorityService.addBatch(list);
				}
				this.log.info("添加成功");
				resultable = new Resultable(true, "操作成功");
			}else{
				resultable = new Resultable(false, "参数无效");
			}
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"角色编号",lists[0].getRoleId().toString(),"权限添加操作","权限添加"};
			roleAuthorityService.fireLoginEvent(s, resultable.getSuccess()?1:0);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("system_settings/editRoleAuthority");
		modelAndView.addObject("isType", "update");
		try {
			TRoleAuthority roleAuthority = roleAuthorityService.getObject(new Long(id));
			this.log.info(roleAuthority);
			modelAndView.addObject("roleAuthority", roleAuthority);
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
	@RequestLogging(name="修改角色权限")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TRoleAuthority  roleAuthority) {
		Resultable resultable = null;
		try {
			roleAuthorityService.update(roleAuthority);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;	
	}

}