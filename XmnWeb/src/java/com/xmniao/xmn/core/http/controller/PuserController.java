package com.xmniao.xmn.core.http.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
//import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.http.entity.Member;
import com.xmniao.xmn.core.http.entity.PUserRequestSelect;
import com.xmniao.xmn.core.http.entity.PUserResponseSelect;
import com.xmniao.xmn.core.http.entity.PhpHttpPageable;
import com.xmniao.xmn.core.http.service.PuserService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PuserController
 * 
 * 类描述： PHP 用户数据
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月20日 下午6:00:10
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
@RequestLogging(name="会员管理")
@Controller
@RequestMapping(value = "member/memberList")
public class PuserController extends BaseController {
	protected final Logger log = Logger.getLogger(PuserController.class);
	@Autowired PuserService puserService;
	/*
	 * 会员列表增加钱包详细信息显示功能
	 * i.调用分账系统支付服务接口数据
	 * ii.调用joinService.getWallet()方法获取展示数据
	 */
	@Autowired
	private JointService jointService;
	
	
	/*private QueryCallback<PUserRequestSelect> callback = new QueryCallback<PUserRequestSelect>() {
		@Override
		public List<?> query(Object service,PUserRequestSelect entity, int page, int limit) {
			try {
			entity.setPage(String.valueOf(page));
			entity.setPageSize(String.valueOf(limit));
			return ((PuserService)service).getPHPMemberData(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	};*/
	
	
	/**
	 * 初始化会员列表
	 * update by wangzhimin  2015/08/17 16:11 (下午)
	 * @return
	 */
	@RequestMapping("init")
	public String init(){
		
		//ModelAndView mv = new ModelAndView();
	//	mv.addObject("requestInit", "member/memberList/init/list");
		//mv.setViewName("member/memberList/memberList");
		//return mv;
		
		return "member/memberList/memberList";
	}
	
	
//	@RequestMapping(value = "init/list")
//	public ModelAndView list(PUserRequestSelect puser) {
//		ModelAndView mv = new ModelAndView();
//		puser.setPageSize("15");
//		puserService.getPuserInfo(mv,puser,true);
//		mv.setViewName("member/memberList/memberTable");
//		return mv;
//	}
	
	/**
	 * 获取会员列表信息
	 * @param puser
	 * @return
	 * @author wangzhimin
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(PUserRequestSelect puser) {
		puser.setPageSize("15");
		return puserService.getUserInfo(puser);
	}
	
	
	/**
	 * 校验手机号码唯一（账号）
	 * @param puser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "init/checkPhone")
	@ResponseBody
	public boolean checkPhone(PUserRequestSelect puser) {
		try {
			if(null!=puser&&StringUtils.hasLength(puser.getPhone())){
				PhpHttpPageable<PUserResponseSelect> p =puserService.getuserInfo(puser);
				List<PUserResponseSelect> l = (List<PUserResponseSelect>)p.getData();
				return !(null!=l&&!l.isEmpty());
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	
	
	/**
	 * 导出记录
	 * @param advertising
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export/init")
	public ModelAndView exportInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberList/exportForm");
		return mv;
	}
		
	
	
	/**
	 * 导出记录
	 * @param advertising
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(PUserRequestSelect puser, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("list", puserService.getPHPMemberData(puser));
		doExport(request, response, "member/memberList.xls", params);
	/*	puser.setPageSize("1");
		PhpHttpPageable<PUserResponseSelect> p;
		try {
			p = puserService.getuserInfo(puser);
			doThreadExport(request, response, "member/memberList.xls", Long.valueOf(p.getTotal()), callback, puserService, puser,1000);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
	
	/**
	 * 初始化添加
	 * @return
	 */
	@RequestMapping("add/init")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("action","member/memberList/add.jhtml");
		mv.setViewName("member/memberList/memberModel");
		return mv;
	}
	
	/**
	 * 添加会员
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestLogging(name="会员添加")
	@RequestMapping("add")
	@ResponseBody
	public Object add(HttpServletRequest req,Member request){
		boolean statusflag=false;
		try {
			PhpHttpPageable<PUserResponseSelect> p =  puserService.add(req,request);
			 statusflag=p.getStatus();
			return  new Resultable(p.getStatus(), p.getStatus()?"操作成功":"操作失败");
		}catch (Exception e) {
			log.error("获取添加会员失败", e);
		}finally{
			String[] s={"用户昵称",String.valueOf(request.getNname()),"会员添加","添加"};
			puserService.fireLoginEvent(s, statusflag?1:0);
		}
		return  new Resultable(false, "操作失败");
	}
	
	/**
	 * 修改初始化
	 * @param puser
	 * @return
	 */
	@RequestMapping("update/init")
	public ModelAndView updateInit(PUserRequestSelect puser){
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,false);
		mv.addObject("action","member/memberList/update.jhtml");
		mv.setViewName("member/memberList/memberModel");
		return mv;
	}
	
	/**
	 * 修改（调会员接口） 
	 * @param request
	 * @return 	
	 */
	@RequestLogging(name="会员信息修改")
	@RequestMapping("update")
	@ResponseBody
	public Object update(Member request){
		boolean statusflag=false;
		try {
			PhpHttpPageable<PUserResponseSelect> p= puserService.update(request);
			 statusflag=p.getStatus();
			return  new Resultable(p.getStatus(), p.getStatus()?"操作成功":"操作失败");
		}  catch (Exception e) {
			log.error("更新-- "+request+" --会员信息失败", e);
		}finally{
			String[] s={"用户昵称",String.valueOf(request.getNname()),"会员修改","修改"};
			puserService.fireLoginEvent(s, statusflag?1:0);
		}
		return  new Resultable(false, "操作失败");
	}
	/**
	 * 修改（调会员接口） 
	 * @param request
	 * @return 	
	 */
	@RequestLogging(name="普通会员黑名单恢复到列表")
	@RequestMapping("status/update")
	@ResponseBody
	public Object statusupdate(Member request){
		String uid=request.getUid();
		boolean statusflag=false;
		try {
			PhpHttpPageable<PUserResponseSelect> p= puserService.updateStatus(request);
			 statusflag=p.getStatus();
			return  new Resultable(p.getStatus(), p.getStatus()?"操作成功":"操作失败");
		}  catch (Exception e) {
			log.error("更新-- "+request+" --会员恢复到列表失败", e);
		}finally{
			String[] s={"会员编号",String.valueOf(uid),"恢复到列表","恢复到列表"};
			puserService.fireLoginEvent(s, statusflag?1:0);
		}
		return  new Resultable(false, "操作失败");
	}
	
	
	/**
	 * 查看初始化
	 * @param puser
	 * @return
	 */
	@RequestMapping("details")
	public ModelAndView details(PUserRequestSelect puser){
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,false);
		mv.setViewName("member/memberList/memberDetails");
		return mv;
	}
	
	/**
	 * 查看钱包
	 */

	@RequestMapping(value = "viewWallet")
	public ModelAndView viewWallet(ModelAndView model,
			@RequestParam("uid") Integer uid) {
			model.setViewName("member/memberList/userViewWallet");
			model.addObject("wallet", jointService.getWallet(uid.toString(),"1"));
		return model;
	}
	
}
