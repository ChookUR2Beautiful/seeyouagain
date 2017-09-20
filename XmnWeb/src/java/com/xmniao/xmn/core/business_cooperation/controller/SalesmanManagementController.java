package com.xmniao.xmn.core.business_cooperation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.business_cooperation.service.SalesmanManagementService;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 *@ClassName:salesmanManagementController
 *@Description:合作商业务员管理控制层
 *@author hls
 *@date:2016年3月14日下午8:01:28
 */
@RequestLogging(name="合作商业务员管理")
@Controller
@RequestMapping(value = "business_cooperation/salesmanManagement")
public class SalesmanManagementController extends BaseController {

	@Autowired
	private SalesmanManagementService salesmanService;
	
	/**
	 * @Title:init
	 * @Description:初始化业务员列表页面
	 * @return String
	 * @throw
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/salesmanManagementList";
	}

	/**
	 * @Title:list
	 * @Description:查询合作商业务员列表
	 * @param joint
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TJoint joint,TStaff staff) {
		Pageable<TStaff> pageable = new Pageable<TStaff>(staff);
		pageable.setContent(salesmanService.getSalesman(joint));
		pageable.setTotal(salesmanService.getSalesmanCount(joint));
		return pageable;
	}
	
	/**
	 * @Title:addInit
	 * @Description:添加业务员初始化
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true,tokenName="jointToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/addSalesman");
		//查询区域
//		Map<Integer , List<TArea>> areaList = AreaHandler.getAreaHandler().getArea();
//		this.log.info(areaList);
//		modelAndView.addObject("areaList", JSON.toJSONString(areaList));
		//查询合作商
		
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/**
	 * @Title:getJointList
	 * @Description:获取合作商编号和名称用于初始化下拉框
	 * @param request
	 * @param response
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/add/init/jointListInit")
	@ResponseBody
	public Object getJointList(HttpServletRequest request, HttpServletResponse response){
		List<TJoint> jointList = new ArrayList<>();
		jointList = salesmanService.getJointList();
		return jointList;
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
	@RequestMapping(value = "/update/init")
	@RequestToken(createToken=true,tokenName="jointToken")
	public ModelAndView updateInit(HttpServletRequest request,String staffid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/addSalesman");
		modelAndView.addObject("isType", "update");
		try {
			salesmanService.getSalesInfo(staffid, modelAndView);
			modelAndView.addObject("isType", "update");
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	/**
	 * @Title:update
	 * @Description:合作商业务员修改
	 * @param joint
	 * @return Object
	 * @throw
	 */
	@RequestLogging(name="合作商业务员修改")
	@RequestMapping({"/update/staff","/start"})
	@ResponseBody
	public Object update(TStaff tstaff) {
		Resultable resultable = null;
		try {
				salesmanService.updateSalesman(tstaff);
				this.log.info("修改成功");
				resultable = new Resultable(true, "修改成功");
				recordUpdateLog(tstaff.getStaffid(), PartnerConstants.FIRELOGIN_NUMA);
				
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			salesmanService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("修改合作商异常", e, new Object[]{tstaff}).getMessage()), 0);
		}
		return resultable;
	}
	
	private void recordUpdateLog(Integer staffid, int flag){
		String[] s={"合作商业务员编号",String.valueOf(staffid),"修改","修改"};
		salesmanService.fireLoginEvent(s, flag);
	}
	
	/**
	 * @Title:addStaff
	 * @Description:添加合作商业务员
	 * @param staff
	 * @return Object
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/add/staff")
	@ResponseBody
	public Object addStaff(TStaff staff) {
		//默认账号状态以及账号类型
		staff.setStatus(0);
		staff.setCategory(1);
		Resultable resultable = null;
		try {
			staff.setSdate(new Date());
			Integer staffid = salesmanService.addSalesman(staff);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			resultable.setData(staffid);
			String[] s={"合作商业务员编号",String.valueOf(staffid),"添加","添加"};
			salesmanService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Title:vailStaff
	 * @Description:验证业务员账号是否存在
	 * @param staff
	 * @return Object
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/add/init/vailstaff")
	@ResponseBody
	public Boolean vailStaff(TStaff staff) {
		boolean existFlag = false;
		try {
			existFlag  = salesmanService.vailStaff(staff);
			this.log.info("验证账号成功");
		} catch (Exception e) {
			this.log.error("验证账号异常", e);
		} finally {
			return existFlag;
		}
	}
	

}