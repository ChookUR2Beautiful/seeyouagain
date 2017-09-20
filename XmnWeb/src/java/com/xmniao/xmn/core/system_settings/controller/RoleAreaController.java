package com.xmniao.xmn.core.system_settings.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.system_settings.entity.TRoleArea;
import com.xmniao.xmn.core.system_settings.service.RoleAreaService;
import com.xmniao.xmn.core.system_settings.service.RoleService;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.handler.AuthorityAreaHandler;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="角色数据权限管理")
@Controller
@RequestMapping(value = "system_settings/dataAuthority")
public class RoleAreaController {
	
	private static final Log log = LogFactory.getLog(RoleAreaController.class);
	
	@Autowired RoleAreaService roleAreaService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "init")
	public ModelAndView init(@RequestParam("roleId") Long roleId){
		ModelAndView mv = new ModelAndView();
		TRole tr = roleService.getObject(roleId);
		AuthorityAreaHandler authorityAreaHandler = AuthorityAreaHandler.getInstance();
		mv.addObject("parentAuthorityAreaList", authorityAreaHandler.getParentAuthorityAreaList());
		mv.addObject("subAuthorityArea",authorityAreaHandler.getSubAuthorityArea());
		mv.addObject("tr", tr);
		mv.addObject("roleId", roleId);
		mv.setViewName("system_settings/dataAuthority");
		return mv;	
	}
	
	
	@RequestMapping(value = "init/authorityAreaInfo")
	public ModelAndView authorityAreaInfo(@RequestParam("roleId") Long roleId,@RequestParam("authorityId") Long authorityId){
		ModelAndView mv = new ModelAndView("system_settings/dataAuthorityForm");
		try{
			roleAreaService.getAuthorityAreaInfo(mv, roleId, authorityId);
		}catch(Exception e){
			log.error("查询区域权限详情异常", e);
			mv.clear();
			mv.setViewName("system_settings/dataAuthorityForm");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value = "init/save")
	@ResponseBody
	public Object save(HttpServletRequest request,@RequestBody TRoleArea[] areaList){

		Resultable resultable = null;
		try{
			roleAreaService.save(areaList,ResultUtil.getCurrentUser(request).getUsername());
			resultable = new Resultable(true, "操作成功");
		}catch(Exception e){
			log.error("保存区域权限设置异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;	
	}
	

}
