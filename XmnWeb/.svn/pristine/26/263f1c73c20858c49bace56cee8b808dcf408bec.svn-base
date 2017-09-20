package com.xmniao.xmn.core.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.member.entity.MemberCard;
import com.xmniao.xmn.core.member.service.MemberBankCardService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@Controller
@RequestMapping("member/MemberBankCard")
@RequestLogging(name="会员银行卡管理")
public class MemberBankCardController {
	
	@Autowired
	private MemberBankCardService bankCardService;
	
	
	
	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "member/MemberBankCard/init/list");
		mv.setViewName("member/memberBankCard/bankCardList");
		return mv;	
	}
	
	/**
	 * 查询
	 * @param puser
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(MemberCard card) {
		return bankCardService.getCardList(card);
	}
	
	@RequestMapping(value = "unbundlingBankCard")
	@RequestLogging(name="会员银行卡解绑")
	@ResponseBody
	public Object unbundlingBankCard(MemberCard card) {
		Object object=null;
		try {
			object=bankCardService.unbundlingBankCard(card);
		} catch (Exception e) {
			object = new Resultable(false, "解绑失败！");
			bankCardService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("会员银行卡解绑", e, new Object[]{card}).getMessage()),0);
		}
		return object;
	}

}
