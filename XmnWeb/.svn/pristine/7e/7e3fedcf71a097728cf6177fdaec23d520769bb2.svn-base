package com.xmniao.xmn.core.businessman.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSpread;
import com.xmniao.xmn.core.businessman.entity.TSpreadConfig;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.service.SpredService;
import com.xmniao.xmn.core.businessman.service.WaiterConfigService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 *@ClassName:SellerSubsidyController
 *@Description:商家服务员推广
 *@author hls
 *@date:2016年2月27日下午12:18:44
 */
@RequestLogging(name="商家服务员推广")
@Controller
@RequestMapping(value = "/businessman/sellerSubsidy")
public class SellerSubsidyController extends BaseController {
	/**
	 * 注入商家服务员推广配置service
	 */
	@Autowired
	private WaiterConfigService waiterConfigService;
	/**
	 * 注入商家服务员推广管理信息service
	 */
	@Autowired
	private SpredService spredService;
	/**
	 * 注入商户service
	 */
	@Autowired
	private SellerService sellerService;
	
	// 微信商城扫码支付地址
	public String WXAPI_URL = PropertiesUtil.readValue("wxapi.url");
	/**
	 * 初始化商家推广页面
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("businessman/sellerSubsidyList");
		mav.addObject("wxapiUrl", WXAPI_URL);
		return mav;
	}
	/**
	 * 获取服务员推广配置列表
	 * 
	 * @param tSpreadConfig
	 * @return
	 */
	@RequestMapping(value = "/config")
	@ResponseBody
	public Object waiterConfigList(TSpreadConfig tSpreadConfig) {
		this.log.info("sellerSubsidyController-->list tSpreadConfig=" + tSpreadConfig);
		Pageable<TSpreadConfig> pageable = new Pageable<TSpreadConfig>(tSpreadConfig);
		pageable.setContent(waiterConfigService.getList(tSpreadConfig));
		pageable.setTotal(waiterConfigService.count(tSpreadConfig));
		return pageable;
	}
	/**
	 * @Title:list
	 * @Description:获取服务员推广信息列表
	 * @param tSpread
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/manager")
	@ResponseBody
	public Object tSpreadlist(TSpread tSpread) {
		this.log.info("sellerSubsidyController-->list tSpread=" + tSpread);
		Pageable<TSpread> pageable = new Pageable<TSpread>(tSpread);
		pageable.setContent(spredService.getList(tSpread));
		pageable.setTotal(spredService.count(tSpread));
		return pageable;
	}
	/**
	 * @Title:waiterConfigAddInit
	 * @Description:服务员推广配置添加页面
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "waiterConfig/add/init")
	@ResponseBody
	public ModelAndView waiterConfigAddInit(){
		ModelAndView modelAndView = new ModelAndView("businessman/addWaiterConfig");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * @Title:addInit
	 * @Description:服务员推广表添加页面
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "/spread/add/init")
	@RequestToken(createToken=true,tokenName="sellerSubsidyToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerSubsidy");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * 初始化服务员推广配置编辑页面
	 */
	@RequestMapping(value = "waiterConfig/update/init")
	@RequestToken(createToken=true,tokenName="sellerSubsidyToken")
	public ModelAndView updateConfigInit(ModelAndView model,@RequestParam("id") Integer id) {
		model.addObject("TSpreadConfig",waiterConfigService.getObject(Long.valueOf(id)));
		model.addObject("isType","update");
		model.setViewName("businessman/addWaiterConfig");
		return model;
	}
	/**
	 * 初始化商家服务员推广管理编辑页面
	 */
	@RequestMapping(value = "updateTSpread/init")
	@RequestToken(createToken=true,tokenName="sellerSubsidyToken")
	public ModelAndView updateInitTSpread(ModelAndView model,@RequestParam("id") Integer id) {
		spredService.initTSpreadInfo(id, model);//根据id取得用于修改的记录填充到修改页面
		model.addObject("requestUrl", "businessman/sellerSubsidy/updateSellerSpread");
		model.addObject("isType","update");
		model.setViewName("businessman/editSellerSubsidy");
		return model;
	}
	/**
	 * @Title:addWaiterConfig
	 * @Description:添加商家推广服务员配置
	 * @param tSpreadConfig
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@RequestLogging(name="添加服务员推广配置")
	@RequestMapping(value = "/waiterConfig/add")
	@ResponseBody
	public Resultable addWaiterConfig(TSpreadConfig tSpreadConfig, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			waiterConfigService.addWaiterConfig(tSpreadConfig,request);
			resultable = new Resultable(true, "添加成功！");
			this.log.info("添加成功!");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			waiterConfigService.fireLoginEvent(new String[]{"新增商家推广服务员配置",tSpreadConfig.getId().toString(),"添加",""}, resultable.getSuccess()?1:0);
		}
		return resultable; 
	}
	/**
	 * 修改商家推广服务员配置
	 * @return
	 */
	@RequestLogging(name="修改商家推广服务员配置")
	@RequestMapping(value = "/waiterConfig/update")
	@ResponseBody
	public Resultable updateWaiterConfig(TSpreadConfig tSpreadConfig, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			// 修改商户信息
			waiterConfigService.updateWaiterConfig(tSpreadConfig, request);
			resultable = new Resultable(true, "修改成功！");
			this.log.info("修改成功!");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			waiterConfigService.fireLoginEvent(new String[]{"修改商家推广服务员配置",tSpreadConfig.getId().toString(),"更新",""}, resultable.getSuccess()?1:0);
		} 
		return resultable;
	}

	/**
	 * @Title:addSellerSpread
	 * @Description:添加服务员推广
	 * @param tSpread
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@RequestLogging(name="添加服务员推广")
	@RequestMapping(value = "/spread/add")
	@ResponseBody
	public Resultable addSellerSpread(TSpread tSpread, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			spredService.addSellerSpread(tSpread,request);
			resultable = new Resultable(true, "添加成功！");
			this.log.info("添加成功!");
		} catch (Exception e) {
 			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			spredService.fireLoginEvent(new String[]{"新增商家服务员推广",tSpread.getId().toString(),"添加",""}, resultable.getSuccess()?1:0);
		}
		return resultable; 
	}
	/**
	 * @Title:updateSellerSpread
	 * @Description:修改商家服务员推广
	 * @param tSpread
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@RequestLogging(name="修改商家服务员推广")
	@RequestMapping(value = "/spread/start")
	@ResponseBody
	public Resultable updateSellerSpread(TSpread tSpread, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			spredService.updateSellerSpread(tSpread,request);
			resultable = new Resultable(true, "修改成功！");
			this.log.info("修改成功!");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			spredService.fireLoginEvent(new String[]{"修改商家服务员推广",tSpread.getId().toString(),"更新",""}, resultable.getSuccess()?1:0);
		} 
		return resultable;
	}
	
	/**
	 * @Title:deleteSellerSpread
	 * @Description:删除商家服务员推广
	 * @param aid
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@RequestLogging(name="删除商家服务员推广")
	@RequestMapping(value = "/spread/delete")
	@ResponseBody
	public Resultable deleteSellerSpread(HttpServletRequest request,@RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			spredService.deleteSellerSpread(aid.split(","));
			resultable = new Resultable(true, "删除成功！");
			this.log.info("删除成功!");
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			spredService.fireLoginEvent(new String[]{"删除商家服务员推广",aid.toString(),"删除",""}, resultable.getSuccess()?1:0);
		} 
		return resultable;
	}
	/**
	 * 获取商家列表
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "BargainSellerInit")
	@ResponseBody
	public Object getSellerIdAndSellerName(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = sellerService.getSellerIdAndSellerName(seller);
		return pageable;
	}
	/**
	 * @Title:getWaiterBySllerid
	 * @Description:获取某个商家服务员列表
	 * @param request
	 * @param response
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/spread/add/SelectWaiterList")
	@ResponseBody
	public Object getWaiterBySllerid(HttpServletRequest request, HttpServletResponse response){
		int sellerid = Integer.parseInt(request.getParameter("sellerid").trim());
		List<TSeller> waiterList = new ArrayList<>();
		waiterList = sellerService.getWaiterList(sellerid);
		return waiterList;
	}
	/**
	 * 初始化挑选商家信息
	 */
	@RequestMapping(value = "init/chosen")
	public ModelAndView chosenInit(ModelAndView model,HttpServletRequest request) {
		String tabFlag = request.getParameter("tabFlag");
		model.addObject("tabFlag",tabFlag);
		model.setViewName("businessman/sellerSubsidy/chosenShopList");
		return model;
	}
//	public String chosenInit() {
//		return "businessman/sellerSubsidy/chosenShopList";
//	}
	/**
	 * @Title:base64ToEncrypt
	 * @Description:给字符串加密并返回
	 * @param request
	 * @param response
	 * @return String
	 * @throw
	 */
	@RequestMapping(value = "base64ToEncrypt")
	@ResponseBody
	public String base64ToEncrypt(HttpServletRequest request, HttpServletResponse response) {
		String sellerid = request.getParameter("sellerid");
		String sellername = request.getParameter("sellername");
		String aid = request.getParameter("aid");
		Map<String, String> codeMap = new HashMap<>();
		codeMap.put("sellerid", sellerid);
		codeMap.put("sellername", sellername);
		codeMap.put("aid", aid);
		String codeStr = JsonUtil.toJSONString(codeMap);//转成json字符串
		String encryptCode = Base64.getBase64(codeStr);//加密
//		System.out.println(Base64.getFromBase64(encryptCode));//解密
		return encryptCode;//返回
	}
	
	/**
	 * @Title:chosenList
	 * @Description:获取商家列表(只查询已推广配置的商家)
	 * @param seller
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/chosenSellerToSpread/list")
	@ResponseBody
	public Object chosenList(TSeller seller) {
		seller.setIsChain(0);
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = spredService.getListTospread(seller);
		return pageable;
	}
	/**
	 * 获取商家列表（已上线已签约的商家并且未配置）
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/chosenSellerToConfig/list")
	@ResponseBody
	public Object chosenSellerList(TSeller seller) {
		seller.setIsChain(0);
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = spredService.getListToConfig(seller);
		return pageable;
	}
	/**
	 * @Title:vailStaff
	 * @Description:验证服务员账号是否添加过
	 * @param staff
	 * @return Object
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/spread/add/vailstaff")
	@ResponseBody
	public Boolean vailStaff(TSeller seller) {
		boolean existFlag = false;
		try {
			existFlag  = spredService.vailStaff(seller);
			this.log.info("验证账号成功");
		} catch (Exception e) {
			this.log.error("验证账号异常", e);
		} finally {
			return existFlag;
		}
	}
	
	/*
	 * 服务员推广名单导出
	 */
	@RequestMapping("spread/export")
	public void exportSubsidyManageExcel(TSpread tSpread,HttpServletRequest request,HttpServletResponse response){
		tSpread.setOrder(PageConstant.NOT_ORDER);
		tSpread.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", spredService.getListDeail(tSpread));
		doExport(request, response, "businessman/sellerSubsidyManage.xls", params);
	}
	
	/*
	 * 服务员推广产生的订单导出
	 */
	@RequestMapping("spread/exportBill")
	public void exportSubsidyBillExcel(@RequestParam("date") String date,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", spredService.getSellerSpreadBillList(date));
		doExport(request, response, "businessman/sellerSubsidyBill.xls", params);
	}
	
}
