package com.xmniao.xmn.core.user_terminal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.entity.TUserProposal;
import com.xmniao.xmn.core.user_terminal.service.BannerAdvertisingService;
import com.xmniao.xmn.core.user_terminal.service.TUserProposalService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
@RequestLogging(name="用户端管理")
@Controller
@RequestMapping(value="user_terminal/userComplaints")
public class UserComplaintsController extends BaseController {
	@Autowired
	private TUserProposalService tUserProposalService;
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月25日 下午5:03:58
	 * 描述：用户投诉页面初始化
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/UserComplaintsList";
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月25日 下午5:03:42
	 * 描述：用户投诉列表查询
	 * @param tUserProposal
	 * @return
	 */
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object list(TUserProposal tUserProposal){
		Pageable<TUserProposal> pageable = new Pageable<TUserProposal>(tUserProposal);
		tUserProposalService.putPageable(tUserProposal, pageable);
		return pageable;	
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月25日 下午5:03:19
	 * 描述：用户投诉处理初始化
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateStatusInit(){
		ModelAndView modelAndView=new ModelAndView("user_terminal/updateUserComplaints");
		return modelAndView;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月25日 下午5:03:06
	 * 描述：用户投诉处理
	 * @param tUserProposal
	 * @param request
	 * @return
	 */
	@RequestLogging(name = "用户投诉")
	@RequestMapping(value="update")
	@ResponseBody
	public Object updateStatus(TUserProposal tUserProposal,HttpServletRequest request){
		Resultable resultable=null;
		try {
			resultable=tUserProposalService.updateStatus(tUserProposal,request);
		} catch (Exception e) {
			resultable = new Resultable(false,"操作异常");
			this.log.error("用户投诉处理："+e);
			tUserProposalService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("用户投诉处理", e, new Object[]{tUserProposal,request}).getMessage()),0);
		}
		return resultable;	
	}
}
