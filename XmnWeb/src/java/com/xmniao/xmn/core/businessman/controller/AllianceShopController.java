package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.xmniao.xmn.core.businessman.entity.AllianceAccount;
import com.xmniao.xmn.core.businessman.entity.AllianceShop;
import com.xmniao.xmn.core.businessman.entity.Card;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.AllianceAccountService;
import com.xmniao.xmn.core.businessman.service.AllianceShopService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceShopController
 * 
 * 类描述：商家联盟店管理 现已更名为区域代理
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@RequestLogging(name="联盟店管理")
@Controller
@RequestMapping(value = "businessman/allianceShop")
public class AllianceShopController extends BaseController{
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private AllianceShopService allianceShopService;
	@Autowired
	private AllianceAccountService allianceAccountService;
	
	@Autowired
	private SellerDao sellerDao;
	
	
	
	
	/**
	 * 初始化商家信息
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/allianceShop/allianceShopList";
	}

	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(AllianceShop allianceShop) {
		Pageable<AllianceShop> pageable = new Pageable<AllianceShop>(allianceShop);
		pageable.setContent(allianceShopService.getList(allianceShop));
		pageable.setTotal(allianceShopService.count(allianceShop)); ;
		return pageable;
	}
	
	
	
	
	
	
	/**
	 * 初始化挑选商家信息
	 */
	@RequestMapping(value = "init/chosen")
	public String chosenInit() {
		return "businessman/allianceShop/chosenShopList";
	}

	/**
	 * 获取商家列表
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/chosen/list")
	@ResponseBody
	public Object chosenList(TSeller seller,@RequestParam("allianceId")String id) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		Map<String, Object> map  = new HashMap<String, Object>(1);
		map.put("seller", seller);
		map.put("allianceId", id);
		pageable.setContent(sellerDao.getAllianceSeller(map));
		pageable.setTotal(sellerDao.getAllianceSellerCount(map));
		return pageable;
	}
	
	
	
	/**
	 * 
	 * addInit(商家联盟店添加初始化)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true,tokenName="allianceShopToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/allianceShop/allianceShopModel");
		modelAndView.addObject("requestUrl", "businessman/allianceShop/add");
		return modelAndView;
	}

	/**
	 * 添加商家联盟店信息
	 * @return
	 */
	@RequestLogging(name="添加联盟店")
	@RequestMapping(value = "add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="allianceShopToken")
	public Object add(AllianceShop allianceShop,AllianceAccount allianceAccount,@RequestParam("ids")String ids) {
		boolean bool = allianceShopService.add(allianceShop, allianceAccount, ids);
		return new Resultable(bool, bool ?"添加成功":"添加失败");
		
	}
	
	
	
	/**
	 * 初始化商家联盟店信息编辑页面
	 */
	@RequestMapping(value = "update/init")
	@RequestToken(createToken=true,tokenName="allianceShopToken")
	public ModelAndView updateInit(ModelAndView model,@RequestParam("id") String allianceShopId) {
		model.addObject("allianceShop",allianceShopService.getObject(Long.valueOf(allianceShopId)));
		model.addObject("requestUrl", "businessman/allianceShop/update");
		model.setViewName("businessman/allianceShop/allianceShopModel");
		return model;
	}


	/**
	 * 修改商家联盟店信息
	 * 
	 * @param seller
	 *            
	 * @return
	 */
	@RequestLogging(name="修改联盟店")
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "update")
	@RequestToken(removeToken=true,tokenName="allianceShopToken")
	public Object updateSeller(AllianceShop allianceShop,@RequestParam("ids") String ids) {
		Resultable resultable = null;
		String[] s={"联盟点编号",allianceShop.getId().toString(),"修改操作","修改"};
		try {
			// 修改商户信息
			allianceShop.setUdate(new Date());
			allianceShopService.update(allianceShop);
			allianceShopService.fireLoginEvent(s);
			allianceShopService.deleteAllianceRelation(allianceShop.getId());
			String[] i={"联盟点编号",allianceShop.getId().toString(),"删除关联商家操作","删除关联商家"};
			allianceShopService.fireLoginEvent(i);
			allianceShopService.insertAllianceRelation(ids, allianceShop.getId());
			String[] r={"商家编号",ids,"关联联盟店操作","关联联盟店"};
			allianceShopService.fireLoginEvent(r);
			resultable = new Resultable(true, "修改成功！");
			this.log.info("修改成功!");
		} catch (Exception e) {
			this.log.error("修改商家联盟店信息异常", e);
			allianceShopService.fireLoginEvent(s,0);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 *根据fatherid查询对应的商家信息
	 */
	@RequestMapping(value = "init/findSellerByAllianceShopId")
	@ResponseBody
	public Object findSellerByAllianceShopId(@RequestParam("id")String id) {
		try{
			if(StringUtils.hasLength(id)){
				return sellerDao.findSellerByAllianceShopId(id);
			}
		}catch(Exception e){
			this.log.error("查询联盟店 编号为:"+id+"异常", e);
		}
		return "";
	}
	
	
	/**
	 * 
	 * checkAccount(检查帐号唯一性)
	 * 
	 * @author：chen'heng
	 */
	@RequestMapping(value = "init/checkAccount")
	@ResponseBody
	public Boolean checkAccount(@RequestParam("account")String account) {
		return !(allianceAccountService.checkAccount(account));
	}
	
	
	/**
	 * @author 
	 * @date 2017年5月29日 下午3:53:58
	 * @TODO 子店页面初始化
	 * @return
	 */
	@RequestMapping(value="subShopinit")
	public String subShopInit(){
		return "businessman/subAllianceShopList";
	}
	/**
	 * @author 
	 * @date 2015年5月29日 下午4:10:19
	 * @TODO 子店list(列表数据初始化)
	 * @param seller
	 * @return
	 */
	@RequestMapping(value="subShop/list")
	@ResponseBody
	public Object subAllianceShopList(AllianceShop allianceShop){
		TSeller tSeller = new TSeller();
		Pageable<TSeller> pageable=new Pageable<TSeller>(tSeller);
		sellerService.putSubShopPageableByAlliance(pageable, allianceShop);
		return pageable;	
	}
	
	/**
	 * 方法描述：区域代理绑定银行卡 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:35:30 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/init")
	@ResponseBody
	public ModelAndView bindCardInit(HttpServletRequest request,Card  card){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("businessman/allianceShop/bindAllianceCardInit");
		mav.addObject("crad", card);
		return mav;
	}

	/**
	 * 方法描述：区域代理绑定银行卡列表 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:36:02 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardList")
	@ResponseBody
	public Object bindCardList(HttpServletRequest request,Card card){
		Pageable<Card> pageable = new Pageable<Card>(card);
		pageable = allianceShopService.getCardList(card);
		this.log.info("MultipShopController-->list pageable=" + pageable);
		return pageable;
	}

	/**
	 * 方法描述：区域代理绑定银行卡新增初始化 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:36:31 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/addCardInit")
	@ResponseBody
	public ModelAndView addCardInit(HttpServletRequest request,Card card){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("businessman/allianceShop/addAllianceCardInit");
		mav.addObject("card", card);
		return mav;
	}

	/**
	 * 方法描述：区域代理绑定银行卡新增 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:37:20 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestLogging(name="区域代理银行卡添加")
	@RequestMapping(value="/addCard")
	@ResponseBody
	public Object addCard(HttpServletRequest request,Card card){
		Resultable resultable = null;
		try{
			resultable = allianceShopService.addCard(card);
			String[] s = {"区域代理", card.getSellername(), "绑定银行卡", "增加"};
			allianceShopService.fireLoginEvent(s);
		}catch(Exception e){
			allianceShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("区域代理添加银行卡异常", e, new Object[]{request, card}).getMessage()), 0);
		}
		return resultable;
	}

	/**
	 * 方法描述：区域代理绑定银行卡修改 初始化 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:37:36 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/bindCardInit/updateCardInit")
	@ResponseBody
	public ModelAndView updateCardInit(HttpServletRequest request,Card card){
		ModelAndView mav=new ModelAndView();
		AllianceShop allianceShop = new AllianceShop();
		allianceShop.setId(card.sellerid.longValue());
		card.setSellername(allianceShopService.getList(allianceShop).get(0).getAllianceName());
		Card cardr=allianceShopService.getUpdateByCard(card);
		cardr.setSellername(card.getSellername());
		mav.setViewName("businessman/allianceShop/updateAllianceCardInit");
		mav.addObject("card", cardr);
		return mav;
	}

	/**
	 * 方法描述：区域代理绑定银行卡修改 <br/>
	 * 创建人：  <br/>
	 * 创建时间：2017年3月24日下午3:38:09 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestLogging(name="区域代理银行卡信息修改")
	@RequestMapping(value="/updateCard")
	@ResponseBody
	public Object updateCard(HttpServletRequest request,Card card){
		//return multipShopService.updateCard(card);
		Resultable resultable = null;
		try{
			resultable = allianceShopService.updateCard(card);
			String[] s = {"连锁店", card.getSellername(), "修改银行卡", "修改"};
			allianceShopService.fireLoginEvent(s);
		}catch(Exception e){
			allianceShopService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("区域代理修改银行卡异常", e, new Object[]{request, card}).getMessage()), 0);
		}
		return resultable;
	}
	
	
	
}
