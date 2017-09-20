/**
 * 
 */
package com.xmniao.xmn.core.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.member.entity.MemberWallet;
import com.xmniao.xmn.core.member.service.MemberWalletService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MemberWalletController
 * 
 * 类描述： 会员钱包管理
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-8-16 下午4:59:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value="memberWallet/manage")
public class MemberWalletController extends BaseController {

	
	/**
	 * 注入会员钱包服务
	 */
	@Autowired
	private MemberWalletService memberWalletService;
	
	/**
	 * 
	 * 方法描述：跳转到会员钱包页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-16下午5:22:20 <br/>
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "member/memberWalletManage";
	}
	
	/**
	 * 
	 * 方法描述：加载会员钱包数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-16下午5:27:00 <br/>
	 * @param memberWallet
	 * @return
	 */
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object initList(MemberWallet memberWallet){
		Pageable<MemberWallet> pageable = new Pageable<MemberWallet>(memberWallet);
		try {
			List<MemberWallet> memberWalletList = memberWalletService.getMemberWalletList(memberWallet);
			long count = memberWalletService.count(memberWallet);
			pageable.setContent(memberWalletList);
			pageable.setTotal(count);
			JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("加载会员钱包数据异常,"+e.getMessage());
		}
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到修改会员钱包页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17上午10:22:47 <br/>
	 * @param burs
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(MemberWallet memberWallet){
		ModelAndView mv= new ModelAndView();
		MemberWallet memberWalletInfo=memberWalletService.getMemberWalletInfo(memberWallet);
		mv.addObject("memberWalletInfo", memberWalletInfo);
		mv.setViewName("member/memberWalletEdit");
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：修改用户钱包 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17上午10:22:47 <br/>
	 * @param burs
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(MemberWallet memberWallet){
		Resultable result=new Resultable();
		try {
			memberWalletService.updateWallet(memberWallet);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("操作失败!");
			e.printStackTrace();
			this.log.error("修改用户钱包失败:"+e.getMessage());
			
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改直播钱包页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-18下午2:06:20 <br/>
	 * @param memberWallet
	 * @return
	 */
	@RequestMapping(value="update-live/init")
	public ModelAndView updateLiveInit(MemberWallet memberWallet){
		ModelAndView mv = new ModelAndView();
		MemberWallet memberWalletInfo=memberWalletService.getMemberWalletInfo(memberWallet);
		mv.addObject("memberWalletInfo", memberWalletInfo);
		mv.setViewName("member/liveWalletEdit");
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：修改用户钱包 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-17上午10:22:47 <br/>
	 * @param burs
	 * @return
	 */
	@RequestMapping(value="update-live")
	@ResponseBody
	public Resultable updateLive(MemberWallet memberWallet){
		Resultable result=new Resultable();
		try {
			memberWalletService.updateLiveWallet(memberWallet);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("操作失败!");
			e.printStackTrace();
			this.log.error("修改用户钱包失败:"+e.getMessage());
			
		}
		return result;
	}
	
}
