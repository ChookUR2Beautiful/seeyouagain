package com.xmniao.xmn.core.business_cooperation.controller;

import java.util.List;

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
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.business_cooperation.service.StaffService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：StaffController
 * 
 * 类描述： 合作商员工
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时26分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/staff")
public class StaffController extends BaseController {

	@Autowired
	private StaffService staffService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/staffList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TStaff staff) {
		Pageable<TStaff> pageable = new Pageable<TStaff>(staff);
		pageable.setContent(staffService.getList(staff));
		pageable.setTotal(staffService.count(staff));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商员工删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("staffid") String staffid) {
		Resultable resultable = null;
		try {
			Integer resultNum = staffService.delete(staffid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editStaff");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商员工添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TStaff staff) {
		Resultable resultable = null;
		try {
			staffService.add(staff);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("staffid") String staffid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editStaff");
		modelAndView.addObject("isType", "update");
		try {
			TStaff staff = staffService.getObject(new Long(staffid));
			this.log.info(staff);
			modelAndView.addObject("staff", staff);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商员员工修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TStaff  staff) {
		Resultable resultable = null;
		try {
			staffService.update(staff);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 校验帐号唯一性
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "init/checkPhone")
	public Long checkPhone(@RequestParam("phoneid") String phoneid){
		TStaff  staff = new TStaff();
		staff.setPhoneid(phoneid);
		Long  num = staffService.count(staff);
		return num;
	}
	
	/**
	 * 获取合作商下面所有员工
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getStaffsByJointid")
	public Object getStaffByJointid(@RequestParam("jointid") Long jointid){
		List<TStaff> staffs = staffService.getStaffsByJointid(jointid);
		return staffs;
	}

}