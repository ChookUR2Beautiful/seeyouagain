package com.xmniao.xmn.core.fresh.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TCardSeller;
import com.xmniao.xmn.core.businessman.entity.TRechargeRecord;
import com.xmniao.xmn.core.fresh.entity.TConsumeRecord;
import com.xmniao.xmn.core.fresh.service.FreshUserManageService;
import com.xmniao.xmn.core.util.DateEditor;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshUserManageController
 * 
 * 类描述： 生鲜会员(卡)管理模块Controller
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年2月16日 上午10:08:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value="fresh/card")
public class FreshUserManageController {
	
	private Logger log = LoggerFactory.getLogger(FreshUserManageController.class);
	
	@Autowired
	private FreshUserManageService freshUserManageService;
	
	//html页面相对WebInfo/view路径
	private String htmlPath="fresh/card/";
	
	private String authorityPath="fresh/card/";
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	@RequestMapping(value="")
	@SuppressWarnings("unchecked")
	public ModelAndView toInitVipCard(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		List<String> accessScope = (List<String>)session.getAttribute("accessScope");
		if(accessScope != null){
			if(getInitView(authorityPath+"userList",accessScope)){
				return toUserFreshCardList();
			}else if(getInitView(authorityPath+"prepaidList",accessScope)){
				return toPrepaidList();
			}else {
				return toConsumeList();
			}
		}
		return null;
	}
	
	/*
	 * 检测当前账号是否有该子模块的访问权限
	 */
	private boolean getInitView(String reqUrl,List<String> urlList){
		for(String url:urlList){
			if(reqUrl.equals(url)){
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * 跳转至生鲜会员(卡)列表页面
	 */
	@RequestMapping(value="userListView")
	public ModelAndView toUserFreshCardList(){
		return new ModelAndView(htmlPath+"cardholderList");
	}
	
	/*
	 * 获取生鲜会员(卡)列表数据
	 */
	@RequestMapping(value="userList")
	@ResponseBody
	public Object getCardholderList(TCardSeller cardSeller){
		cardSeller.setCardType(1);
		Pageable<TCardSeller> pageable = new Pageable<TCardSeller>(cardSeller);
		pageable.setContent(freshUserManageService.getCardholderList(cardSeller));
		pageable.setTotal(freshUserManageService.getCardholderCount(cardSeller));
		return pageable;
	}
	
	/*
	 * 锁定/解锁会员卡
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="userList/lock")
	@ResponseBody
	public Resultable updateCardLock(TCardSeller tCardSeller){
		Resultable resultable = null;
		try{
			freshUserManageService.updateCardStatus(tCardSeller);
			resultable = new Resultable(true, tCardSeller.getCardStatus()==3?"已锁定":"已解锁");
		}catch(Exception e){
			log.error("设定会员卡锁定状态异常", e);
			resultable = new Resultable(false, "操作失败");
		}finally{
			return resultable;
		}
	}
	
	/*
	 * 跳转充值记录列表页面
	 */
	@RequestMapping(value="prepaidListView")
	public ModelAndView toPrepaidList(){
		return new ModelAndView(htmlPath+"prepaidList");
	}
	
	/*
	 * 获取充值记录列表数据
	 */
	@RequestMapping(value="prepaidList")
	@ResponseBody
	public Object getPrepaidList(TRechargeRecord tRechargeRecord){
		tRechargeRecord.setCardType(1);
		Pageable<TRechargeRecord> pageable = new Pageable<TRechargeRecord>(tRechargeRecord);
		pageable.setContent(freshUserManageService.getPrepaidList(tRechargeRecord));
		pageable.setTotal(freshUserManageService.getPrepaidCount(tRechargeRecord));
		return pageable;
	}
	
	/*
	 * 跳转充值记录详情页面
	 */
	@RequestMapping(value="prepaidDetailView")
	public ModelAndView toPrepaidDetail(TRechargeRecord tRechargeRecord){
		return new ModelAndView(htmlPath+"prepaidDetail").addObject("tRechargeRecord",tRechargeRecord);
	}

	/*
	 * 跳转消费记录列表页面
	 */
	@RequestMapping(value="consumeListView")
	public ModelAndView toConsumeList(){
		return new ModelAndView(htmlPath+"consumeList");
	}
	
	/*
	 * 获取消费记录列表数据
	 */
	@RequestMapping(value="consumeList")
	@ResponseBody
	public Object getConsumeList(TConsumeRecord tConsumeRecord){
		tConsumeRecord.setCardType(1);
		Pageable<TConsumeRecord> pageable = new Pageable<TConsumeRecord>(tConsumeRecord);
		pageable.setContent(freshUserManageService.getConsumeList(tConsumeRecord));
		pageable.setTotal(freshUserManageService.getConsumeCount(tConsumeRecord));
		return pageable;
	}
}
