package com.xmniao.xmn.core.businessman.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.Plan;
import com.xmniao.xmn.core.businessman.entity.ReqVipCardBean;
import com.xmniao.xmn.core.businessman.entity.TCardApply;
import com.xmniao.xmn.core.businessman.entity.TCardSeller;
import com.xmniao.xmn.core.businessman.entity.TIssueCardApply;
import com.xmniao.xmn.core.businessman.entity.TRechargeRecord;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerNexus;
import com.xmniao.xmn.core.businessman.entity.VipCardBean;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.service.VipCardService;
import com.xmniao.xmn.core.util.DateEditor;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VipCardController
 * 
 * 类描述： 商户会员卡管理模块Controller
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月20日 上午10:03:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value="businessman/vipCard")
public class VipCardController {
	
	private Logger log = LoggerFactory.getLogger(VipCardController.class);
	
	@Autowired
	private VipCardService vipCardService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private SellerService sellerService;
	
	//html页面相对WebInfo/view路径
	private String htmlPath="businessman/vipCard/";
	
	private String authorityPath="businessman/vipCard/";
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/*
	 * 跳转到商家会员卡管理模块
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="init")
	public ModelAndView toInitVipCard(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		List<String> accessScope = (List<String>)session.getAttribute("accessScope");
		if(accessScope != null){
			if(getInitView(authorityPath+"list",accessScope)){
				return toVipCardList();
			}else if(getInitView(authorityPath+"cardholderList",accessScope)){
				return toCardholderList();
			}else if(getInitView(authorityPath+"audit",accessScope)){
				return toVipCardAudit();
			}else if(getInitView(authorityPath+"issueaudit",accessScope)){
				return toIssueCardAudit();
			}else{
				return toPrepaidList();
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
	 * 跳转至会员卡管理列表界面
	 */
	@RequestMapping(value="listView")
	public ModelAndView toVipCardList(){
		return new ModelAndView(htmlPath+"vipCardList");
	}
	
	/*
	 * 跳转至会员卡详情界面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="list/detailView")
	public ModelAndView toVipCardView(Integer sellerId){
		ModelAndView mav = new ModelAndView(htmlPath+"vipCardDetail");
		try {
			ReqVipCardBean reqVipCardBean = vipCardService.getVipCardBeanDetail(sellerId);
			List<Plan> planList = vipCardService.getVipCardIssue(sellerId);
			
			TSellerNexus tSellerNexus = new TSellerNexus();
			tSellerNexus.setSellerId(sellerId);
			List<TSellerNexus> childList = vipCardService.getSupporSellerList(tSellerNexus);
	 		reqVipCardBean.setPlanList(planList);
			mav.addObject("reqVipCardBean",reqVipCardBean);
			mav.addObject("childList",childList);
			mav.addObject("isType","update");
		}catch(Exception e){
			this.log.error("页面初始化异常", e);
		}finally{
			return mav;
		}
	}
	
	/*
	 * 跳转至会员卡新增界面
	 */
	@RequestMapping(value="addView")
	public ModelAndView toVipCardAdd(){
		return new ModelAndView(htmlPath+"vipCardAdd").addObject("isType","add");
	}	
	
	/*
	 * 跳转至会员卡编辑界面
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="updateView")
	public ModelAndView toVipCardEdit(Integer sellerId){
		ModelAndView mav = new ModelAndView(htmlPath+"vipCardAdd");
		try {
		ReqVipCardBean reqVipCardBean = vipCardService.getVipCardBeanDetail(sellerId);
		List<Plan> planList = vipCardService.getVipCardIssue(sellerId);
		reqVipCardBean.setPlanList(planList);
		mav.addObject("reqVipCardBean",reqVipCardBean);
		mav.addObject("isType","update");
		}catch(Exception e){
			this.log.error("页面初始化异常", e);
		}finally{
			return mav;
		}
	}	
	
	
	/*
	 * 跳转至会员卡支持的门店列表界面
	 */
	@RequestMapping(value="list/supporSellerListView")
	public Object toVipCardSellerList(Integer sellerId){
		ModelAndView mav = new ModelAndView(htmlPath+"supportedChildShopList");
		mav.addObject("sellerId",sellerId);
		return mav;
	}
	
	/*
	 * 跳转至选择子店列表界面
	 */
	@RequestMapping(value = "list/chosenView")
	public Object chosenInit(Integer sellerId) {
		return new ModelAndView(htmlPath+"chosenShopList").addObject("sellerId", sellerId);
	}
	
	/*
	 * 获取总店
	 */
	@RequestMapping("list/getMulShopList")
	@ResponseBody
	public Object getFatherSeller(HttpServletRequest request){
		
		try{
			TSeller seller = new TSeller();
			seller.setStatus(3);
			seller.setFatherid(0);
			seller.setLimit(-1);
			return sellerDao.getFatherSeller(seller);
		}catch(Exception e){
			log.error("获取总店列表失败！",e);
			return new ArrayList<TSeller>();
		}
	}
	
	/*
	 *根据fatherid查询对应的商家信息
	 */
	@RequestMapping(value = "list/findSellerByFatherid")
	@ResponseBody
	public Object findSellerByFatherid(TSeller seller) {
		return sellerDao.findSellerByFatherid(seller);
	}
	
	
	/*
	 *根据fatherid查询对应的商家信息
	 */
	@RequestMapping(value = "list/findSelectedSellerByFatherid")
	@ResponseBody
	public Object findSellerSellerByFatherid(Integer sellerId) {
		List<Map<String,Object>> mSellerMap = vipCardService.getmSellerList(sellerId);
		return mSellerMap;
	}
	
	/*
	 *根据fatherid查询对应的商家信息
	 */
	@RequestMapping(value = "list/getChildShop")
	@ResponseBody
	public Object findShopBySeller(TSeller tSeller) {
		 Pageable<TSeller> pageable = new Pageable<TSeller>(tSeller);
		 pageable.setContent(sellerDao.getChildShopList(tSeller));
		 pageable.setTotal(sellerDao.getChildShopCount(tSeller));
		 return pageable;
	}
	
	/*
	 * 获取会员卡列表信息
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Object getVipCardList(VipCardBean vipCardBean){
		 Pageable<VipCardBean> pageable = new Pageable<VipCardBean>(vipCardBean);
		 pageable.setContent(vipCardService.getVipCardList(vipCardBean));
		 pageable.setTotal(vipCardService.getVipCardCount(vipCardBean));
		 return pageable;
	}
	
	/*
	 * 新增会员卡信息
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="add")
	@ResponseBody
	public Object addVipCrad(HttpServletRequest request, ReqVipCardBean reqVipCardBean){
		log.debug("新增会员卡信息："+JSON.toJSONString(reqVipCardBean));
		Resultable resultable = null;
		try{
			if(null != vipCardService.getVipCardBeanDetail(reqVipCardBean.getSellerId())){
				log.error("Id为"+reqVipCardBean.getSellerId()+"的商户:"+reqVipCardBean.getSellerName()+"已有会员卡，不能新增，只能编辑");
				//throw new Exception();
				resultable = new Resultable(false, "不允许重复添加商户会员卡");
			}else{
				vipCardService.addVipCardInfo(reqVipCardBean);
				resultable = new Resultable(true, "操作成功");
			}
		}catch(Exception e){
			log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		}finally{
			return resultable;
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="add/ajaxSellerStatus")
	@ResponseBody
	public Object getSellerHasVipcard(HttpServletRequest request, String sellerId){
		String result = "false";
		try{
			boolean flag = vipCardService.isSellerHasVipcard(Integer.parseInt(sellerId));
			result = flag?"该商家已添加过会员卡":"true";
		}catch(Exception e){
			log.error("查询异常", e);
		}finally{
			System.out.println("该店是允许添加会员卡："+result);
			return result;
		}
	}
	/*
	 * 更新会员卡信息
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="update")
	@ResponseBody
	public Object updateVipCard(HttpServletRequest request, ReqVipCardBean reqVipCardBean){
		Resultable resultable = null;
		log.info("修改会员卡信息："+JSON.toJSONString(reqVipCardBean));
		try{
			vipCardService.updateVipCardInfo(reqVipCardBean);
			resultable = new Resultable(true, "操作成功");
		}catch(Exception e){
			log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		}finally{
			return resultable;
		}
	}
	
	/*
	 * 获取开通会员卡的子门店列表
	 */
	@RequestMapping(value="listSupporSellerList")
	@ResponseBody
	public Object listSupporChildSeller(TSellerNexus tSellerNexus){
		Pageable<TSellerNexus> pageable = new Pageable<TSellerNexus>(tSellerNexus);
		pageable.setContent(vipCardService.getSupporSellerList(tSellerNexus));
		pageable.setTotal(vipCardService.getSupporSellerCount(tSellerNexus));
		return pageable;
	}
	
	/*
	 * 跳转至持卡详情列表页面
	 */
	@RequestMapping(value="cardholderListView")
	public ModelAndView toCardholderList(){
		return new ModelAndView(htmlPath+"cardholderList");
	}
	
	/*
	 * 获取持卡详情列表数据
	 */
	@RequestMapping(value="cardholderList")
	@ResponseBody
	public Object getCardholderList(TCardSeller cardSeller){
		cardSeller.setCardType(0);
		Pageable<TCardSeller> pageable = new Pageable<TCardSeller>(cardSeller);
		pageable.setContent(vipCardService.getCardholderList(cardSeller));
		pageable.setTotal(vipCardService.getCardholderCount(cardSeller));
		return pageable;
	}
	
	/*
	 * 锁定/解锁会员卡
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="cardholderList/lock")
	@ResponseBody
	public Resultable updateCardLock(TCardSeller tCardSeller){
		Resultable resultable = null;
		try{
			vipCardService.updateCardStatus(tCardSeller);
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
	 * 跳转会员卡审核页面
	 */
	@RequestMapping(value="vipCardAudit")
	public ModelAndView toVipCardAudit(){
		return new ModelAndView(htmlPath+"vipCardAudit");
	}
	/*
	 * 跳转充值方案审核页面
	 */
	@RequestMapping(value="issueCardAudit")
	public ModelAndView toIssueCardAudit(){
		return new ModelAndView(htmlPath+"issueCardAudit");
	}
	/*
	 * 获取充值记录列表数据
	 */
	@RequestMapping(value="prepaidList")
	@ResponseBody
	public Object getPrepaidList(TRechargeRecord tRechargeRecord){
		tRechargeRecord.setCardType(0);
		Pageable<TRechargeRecord> pageable = new Pageable<TRechargeRecord>(tRechargeRecord);
		pageable.setContent(vipCardService.getPrepaidList(tRechargeRecord));
		pageable.setTotal(vipCardService.getPrepaidCount(tRechargeRecord));
		return pageable;
	}
	
	/*
	 * 跳转充值记录详情页面
	 */
	@RequestMapping(value="prepaidList/prepaidDetailView")
	public ModelAndView toPrepaidDetail(TRechargeRecord tRechargeRecord){
		return new ModelAndView(htmlPath+"prepaidDetail").addObject("tRechargeRecord",tRechargeRecord);
	}
	
	/**
	 * @Title:getCardApplyList
	 * @Description:获取VIP卡审核信息
	 * @param tCardApply
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value="cardapplylist")
	@ResponseBody
	public Object getCardApplyList(TCardApply tCardApply){
		Pageable<TCardApply> pageable = new Pageable<TCardApply>(tCardApply);
		pageable.setContent(vipCardService.getCardApplyList(tCardApply));
		pageable.setTotal(vipCardService.getCardApplyListCount(tCardApply));
		return pageable;
	}
	/**
	 * @Title:toVipCardAuditView
	 * @Description:跳转至会员卡审核申请记录详情界面
	 * @param sellerId
	 * @return ModelAndView
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="cardapplylist/detailView")
	public ModelAndView toVipCardAuditView(Integer id){
		ModelAndView mav = new ModelAndView(htmlPath+"vipCardAuditDetail");
		try {
			TCardApply tCardApply = vipCardService.getVipCardAuditDetail(id);
			mav.addObject("tCardApply",tCardApply);
		}catch(Exception e){
			this.log.error("页面初始化异常", e);
		}finally{
			return mav;
		}
	}
	
	/**
	 * @Title:applyCensor
	 * @Description:批量审核
	 * @param tCardApply
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "censor")
	@ResponseBody
	public Object applyCensor(TCardApply tCardApply){
		Resultable resultable = null;
		if("2".equals(tCardApply.getOptype())){//不通过操作
			resultable = vipCardService.updateBatchNoPass(tCardApply);
		}else{
			resultable = vipCardService.updateBatch(tCardApply);
		}
		return resultable;
	}
	/**
	 * @Title:getIssueCardApplyList
	 * @Description:获取充值方案审核信息列表
	 * @param tissueCardApply
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value="issuecardapplylist")
	@ResponseBody
	public Object getIssueCardApplyList(TIssueCardApply tissueCardApply){
		Pageable<TIssueCardApply> pageable = new Pageable<TIssueCardApply>(tissueCardApply);
		pageable.setContent(vipCardService.getissueCardApplyList(tissueCardApply));
		pageable.setTotal(vipCardService.getissueCardApplyListCount(tissueCardApply));
		return pageable;
	}
	/**
	 * @Title:toIssueCardAuditView
	 * @Description:跳转充值方案审核申请记录详情界面
	 * @param id
	 * @return ModelAndView
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="issuecardapplylist/detailView")
	public ModelAndView toIssueCardAuditView(Integer id){
		ModelAndView mav = new ModelAndView(htmlPath+"issueCardAuditDetail");
		try {
			TIssueCardApply tissueCardApply = vipCardService.getIssueCardAuditDetail(id);
			mav.addObject("tissueCardApply",tissueCardApply);
		}catch(Exception e){
			this.log.error("页面初始化异常", e);
		}finally{
			return mav;
		}
	}
	
	/**
	 * @Title:applyCensor
	 * @Description:批量审核
	 * @param tCardApply
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "issuecensor")
	@ResponseBody
	public Object issuCardapplyCensor(TIssueCardApply tissueCardApply){
		Resultable resultable = null;
		if("2".equals(tissueCardApply.getOptype())){//不通过操作
			resultable = vipCardService.updateIssueNoPass(tissueCardApply);
		}else{
			resultable = vipCardService.updateIssuePass(tissueCardApply);
		}
		return resultable;
	}
	
}
