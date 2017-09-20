package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.service.SellerAccountService;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;
import com.xmniao.xmn.core.xmnpay.service.WalletService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAccountController
 * 
 * 类描述： 商家账号
 * 
 * 说明：此处页面传递密码为明文传递，后台加密。
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日19时36分23秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "businessman/sellerAccount")
@RequestLogging(name="商家管理  >> 商家账号 ")
public class SellerAccountController extends BaseController {

	//寻蜜鸟用户默认注册类型：12(业务管理系统注册)
	private static final int regType = 12;
	
	//寻蜜鸟用户默认所属类型：1(普通用户)
	private static final int userType = 1;
	
	//商家店员用户默认所属类型：1(普通店员)
	private static final int sellerType =3;
	
	/**
	 * 商家店员账号Service
	 */
	@Autowired
	private SellerAccountService sellerAccountService;

	/**
	 * 寻蜜鸟用户Service
	 */
	@Autowired
	private BursService bursService;
	
	/**
	 * 支付服务Service
	 */
	@Autowired
	private WalletService walletService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/sellerAccountList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerAccount sellerAccount) {
		Pageable<TSellerAccount> pageable = new Pageable<TSellerAccount>(sellerAccount);
		pageable.setContent(sellerAccountService.getList(sellerAccount));
		pageable.setTotal(sellerAccountService.count(sellerAccount));
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	@RequestLogging(name="账号删除")
	public Object delete(HttpServletRequest request, @RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerAccountService.delete(aid.split(","));
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
	@RequestToken(createToken=true,tokenName="sellerAccountToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerAccount");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * @param isBinding 是否进行绑定
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="sellerToken")
	@RequestLogging(name="账号添加")
	public Object add(TSellerAccount sellerAccount,Boolean isBinding) {
		Resultable resultable = null;
		try {
			Burs burs = null;
			boolean isAddUrs = true;
			if(isBinding != null && isBinding){
				Burs bursParam  = new Burs();
				bursParam.setUname(sellerAccount.getAccount());
				burs = bursService.getUrs(bursParam);
				if(burs!=null){
					isAddUrs=false;
					log.info("该服务员已有寻蜜鸟账户，现对其进行绑定");
				}
			}
			if(isAddUrs){
				/*
				 * 添加用户寻蜜鸟账号
				 */
				burs = new Burs();
				burs.setUname(sellerAccount.getAccount());
				burs.setNname(sellerAccount.getNname());
				burs.setPassword(bursService.usrPasswordMD5(sellerAccount.getPhone().substring(sellerAccount.getPhone().length()-6)));	//MD5(手机号后六位),前后6位倒转
				burs.setRegtime(new Date());
				burs.setRegtype(regType);
				burs.setPhone(sellerAccount.getPhone());
				burs.setUsertype(userType);
				burs = bursService.addUrs(burs);
				log.info("成功添加对应寻蜜鸟会员账号");
			}
			/*
			 * 添加用户钱包
			 */
			if(walletService.checkAccount(sellerAccount.getAccount())){
				Bwallet wallet = new Bwallet();
				wallet.setAccount(sellerAccount.getAccount());
				wallet.setUid(burs.getUid());
				wallet.setSellername(burs.getNname());
				walletService.addWallet(wallet);
				log.info("成功添加对应寻蜜鸟会员钱包");
			}
			/*
			 * 添加店员账号	
			 */
			sellerAccount.setUid(burs.getUid());
			sellerAccount.setType(sellerType);
			sellerAccount.setSdate(new Date());
			sellerAccount.setPassword(NMD5.Encode(sellerAccount.getPassword()));
			sellerAccount.setLevelpass(StringUtils.isBlank(sellerAccount.getLevelpass())?null:NMD5.Encode(sellerAccount.getLevelpass()));
			sellerAccount.setPhone(sellerAccount.getAccount());
			sellerAccountService.add(sellerAccount);
			
			log.info("成功添加对应商家店员账号");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
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
	@RequestToken(createToken=true,tokenName="sellerAccountToken")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerAccount");
		modelAndView.addObject("isType", "update");
		try {
			TSellerAccount sellerAccount = sellerAccountService.getObject(new Long(aid));
			this.log.info(sellerAccount);
			modelAndView.addObject("sellerAccount", sellerAccount);
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
	@RequestMapping(value = "/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="sellerToken")
	@RequestLogging(name="账号修改")
	public Object update(TSellerAccount  sellerAccount) {
		Resultable resultable = null;
		try {
			sellerAccountService.update(sellerAccount);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
		
	}
	
	/**
	 * 
	 * 校验帐号唯一
	 * @param request
	 * @param account  商家帐号
	 * @return 返回帐号记录，记录为0条时 为true
	 */
	@RequestMapping(value = "/init/checkAccount")
	@ResponseBody
	public Object checkAccount(HttpServletRequest request, @RequestParam("account") String account) {
		TSeller tSeller = sellerAccountService.getSeller(account);
		if(tSeller==null){
			return "true";
		}else{
			return "此帐号已是【"+tSeller.getSellerid()+"-"+tSeller.getSellername()+"】店员,如需添加，请先删除该账号，并重新添加";
		}
	}
	
	/*
	 * 获取对应的账号的寻蜜鸟会员信息
	 */
	@RequestMapping(value = "/init/isConflict")
	@ResponseBody
	public Object getUrsInfo(String account) {
		Map<String,Object> ursInfoMap = new HashMap<String,Object>();
		boolean isConflict = true;	//true -冲突;false -不冲突
		Burs bursParam = new Burs();
		bursParam.setUname(account);
		Burs urs = bursService.getUrs(bursParam);
		if(urs==null){
			isConflict=false;
		}else{
			ursInfoMap.put("urs", urs);
			List<Bwallet> walletList=walletService.getWalletList(new Object[]{urs.getUid()});
			ursInfoMap.put("wallet",(walletList!=null && walletList.size()>0)?walletList.get(0):new Bwallet());
		}
		ursInfoMap.put("isConflict", isConflict);
		return ursInfoMap;
	}
}