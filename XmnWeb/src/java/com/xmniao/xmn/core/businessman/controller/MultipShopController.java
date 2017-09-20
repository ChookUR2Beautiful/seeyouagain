package com.xmniao.xmn.core.businessman.controller;

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
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.Card;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.service.MultipShopService;
import com.xmniao.xmn.core.businessman.service.SellerAccountService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 连锁店
 * @author Administrator
 *
 */
@RequestLogging(name="连锁店管理")
@Controller
@RequestMapping(value = "businessman/multipShop")
public class MultipShopController extends BaseController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private MultipShopService multipShopService;
	
	@Autowired
	private SellerAccountService sellerAccountService;
	
	/**
	 * 商家dao
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 初始化商家信息
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/multipleshop/multipShopList";
	}

	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = sellerService.getSellerInfoList(seller);
		this.log.info("MultipShopController-->list pageable=" + pageable);
		return pageable;
	}


	/**
	 * 初始化商家编辑页面
	 */
	@RequestMapping(value = "update/init")
	@RequestToken(createToken=true,tokenName="multipShopToken")
	public ModelAndView updateInit(ModelAndView model,
			@RequestParam("sellerid") Integer sellerid) {
		multipShopService.initSellerInfo(sellerid, model);
		model.setViewName("businessman/multipleshop/editMultipShop");
		return model;
	}


	/**
	 * 修改商家信息
	 * 
	 * @param seller
	 *            zhoude
	 * @return
	 */
	@RequestLogging(name="修改商家连锁店信息")
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "update")
	@RequestToken(removeToken=true,tokenName="multipShopToken")
	public Object updateSeller(TSeller seller, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			// 修改商户信息
			multipShopService.addOrUpdteMultipShop(seller);//新增修改
			resultable = new Resultable(true, "修改成功！");
			this.log.info("修改成功!");
			multipShopService.fireLoginEvent(new String[]{"商家连锁店",seller.getSellerid().toString(),"更新","更新"}, resultable.getSuccess()?1:0);
		} catch (Exception e) {
			this.log.error("修改商家连锁店信息："+e);
			multipShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("修改商家连锁店信息", e, new Object[]{seller,request}).getMessage()),0);
		} 
			return resultable;
	}

	/**
	 * 批量删除商家
	 * 
	 * @param seller
	 * @return
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "beachDeleteSeller")
	public Object beachDeleteSeller(String ids, HttpServletRequest request) {
		Resultable resultable = null;
		Object[] objects = {};
		if (null != ids) {
			objects = ids.split(",");
		}
		try {
			Integer resultNum = sellerService.deleteSeller(objects);
			if (resultNum >SellerConstants.COMMON_PARAM_Z) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			sellerService.fireLoginEvent(new String[]{"商家连锁店",ids,"删除",""},resultable.getSuccess()?1:0);
			return resultable;
		}
	}
	
	/**
	 * 
	 * addInit(连锁店添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true,tokenName="multipShopToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/multipleshop/editMultipShop");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 添加商家信息
	 * @return
	 */
	@RequestLogging(name="新增商家连锁店信息")
	@RequestMapping(value = "add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="multipShopToken")
	public Object add(TSeller seller, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			multipShopService.addOrUpdteMultipShop(seller);//新增修改
			resultable = new Resultable(true, "添加成功！");
			this.log.info("添加成功!");
			multipShopService.fireLoginEvent(new String[]{"商家连锁店",seller.getSellerid().toString(),"新增","新增"},1);
		} catch (Exception e) {
			this.log.error("新增商家连锁店信息："+e);
			resultable = new Resultable(false, "添加失败！");
			multipShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("新增商家连锁店信息", e, new Object[]{seller,request}).getMessage()),0);
		}
		return resultable;
	}
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "multipAccount/init")
	public String multipAccountInit() {
		return "businessman/sellerAccountList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "multipAccount/list")
	@ResponseBody
	public Object multipAccountList(TSellerAccount sellerAccount) {
		Pageable<TSellerAccount> pageable = new Pageable<TSellerAccount>(sellerAccount);
		pageable.setContent(sellerAccountService.getList(sellerAccount));
		pageable.setTotal(sellerAccountService.count(sellerAccount));
		return pageable;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月29日 下午3:53:58
	 * @TODO 子店页面初始化
	 * @return
	 */
	@RequestMapping(value="subShopinit")
	public String subShopInit(){
		return "businessman/subShopList";
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月29日 下午4:10:19
	 * @TODO 子店list(列表数据初始化)
	 * @param seller
	 * @return
	 */
	@RequestMapping(value="subShop/list")
	@ResponseBody
	public Object subShopList(TSeller seller){
		Pageable<TSeller> pageable=new Pageable<TSeller>(seller);
		sellerService.putSubShopPageable(pageable, seller);;
		return pageable;	
	}
	
	
	/**
	 * 初始化挑选商家信息
	 */
	@RequestMapping(value = "init/chosen")
	public String chosenInit() {
		return "businessman/multipleshop/chosenShopList";
	}

	/**
	 * 获取商家列表
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/chosen/list")
	@ResponseBody
	public Object chosenList(TSeller seller) {
		seller.setIsChain(0);
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		
		pageable.setContent(sellerService.getList(seller));
		pageable.setTotal(sellerService.count(seller));
		return pageable;
	}

	/**
	 *根据fatherid查询对应的商家信息
	 */
	@RequestMapping(value = "init/findSellerByFatherid")
	@ResponseBody
	public Object findSellerByFatherid(TSeller seller) {
		return sellerDao.findSellerByFatherid(seller);
	}
	
	/**
	 * 校验连锁店帐号唯一性
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/checkMultipShopAccount")
	@ResponseBody
	public boolean checkMultipShopAccount(HttpServletRequest request, @RequestParam("account") String account) {
		long num = sellerAccountService.checkMultipShopAccount(account);
		return  (num == 0);
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月30日 上午11:11:56
	 * @TODO 银行卡绑定List
	 * @param request
	 * @param sellerid
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/init")
	@ResponseBody
	public ModelAndView bindCardInit(HttpServletRequest request,Card  card){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("businessman/multipleshop/bindCardInit");
		mav.addObject("crad", card);
		return mav;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月30日 下午2:14:43
	 * @TODO 获取连锁店的银行卡list
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardList")
	@ResponseBody
	public Object bindCardList(HttpServletRequest request,Card card){
		Pageable<Card> pageable = new Pageable<Card>(card);
		pageable = multipShopService.getCardList(card);
		this.log.info("MultipShopController-->list pageable=" + pageable);
		return pageable;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月30日 上午11:11:56
	 * @TODO 连锁店银行卡添加初始化
	 * @param request
	 * @param sellerid
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/addCardInit")
	@ResponseBody
	public ModelAndView addCardInit(HttpServletRequest request,Card card){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("businessman/multipleshop/addCardInit");
		mav.addObject("card", card);
		return mav;
	}
	/**
	 * @author wangzhimin
	 * @date 2015年8月26日 下午2:19:32
	 * @TODO 连锁店银行卡添加
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestLogging(name="连锁店银行卡添加")
	@RequestMapping(value="/addCard")
	@ResponseBody
	public Object addCard(HttpServletRequest request,Card card){
		Resultable resultable = null;
		try{
			resultable = multipShopService.addCard(card);
			String[] s = {"连锁店", card.getSellername(), "绑定银行卡", "增加"};
			multipShopService.fireLoginEvent(s);
		}catch(Exception e){
			multipShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("连锁店添加银行卡异常", e, new Object[]{request, card}).getMessage()), 0);
		}
		return resultable;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月30日 下午5:36:06
	 * @TODO 银行卡信息修改初始化
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/updateCardInit")
	@ResponseBody
	public ModelAndView updateCardInit(HttpServletRequest request,Card card){
		ModelAndView mav=new ModelAndView();
		TSeller seller=new TSeller();
		seller.setSellerid(card.sellerid);
		sellerService.getSellerList(seller).get(0).getSellername();
		card.setSellername(sellerService.getSellerList(seller).get(0).getSellername());
		Card cardr=multipShopService.getUpdateByCard(card);
		cardr.setSellername(card.getSellername());
		mav.setViewName("businessman/multipleshop/updateCardInit");
		mav.addObject("card", cardr);
		return mav;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月30日 下午5:45:17
	 * @TODO 连锁店银行卡信息修改
	 * @param request
	 * @param card
	 * @return
	 * 
	 * update by wangzhimin 2015/08/26 14:21:33
	 */
	@RequestLogging(name="连锁店银行卡信息修改")
	@RequestMapping(value="/updateCard")
	@ResponseBody
	public Object updateCard(HttpServletRequest request,Card card){
		//return multipShopService.updateCard(card);
		Resultable resultable = null;
		try{
			resultable = multipShopService.updateCard(card);
			String[] s = {"连锁店", card.getSellername(), "修改银行卡", "修改"};
			multipShopService.fireLoginEvent(s);
		}catch(Exception e){
			multipShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("连锁店修改银行卡异常", e, new Object[]{request, card}).getMessage()), 0);
		}
		return resultable;
	}
	
	
	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/multipShopList")
	@ResponseBody
	public Object getMultipShopList(TSeller seller) {
		if (seller == null)
			seller = new TSeller();
		//是连锁店
		seller.setIsmultiple(1);
		
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = sellerService.getSellerInfoList(seller);
		this.log.info("MultipShopController-->list pageable=" + pageable);
		return pageable;
	}
}
