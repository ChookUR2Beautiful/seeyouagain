package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.xmniao.xmn.core.businessman.entity.ValueCard;
import com.xmniao.xmn.core.businessman.service.ValueCardService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ValueCardController
 * 
 * 类描述： 储值卡商家管理
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月16日 上午10:18:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestLogging(name="储值卡商家管理")
@RequestMapping("/businessman/valueCard")
public class ValueCardController extends BaseController{
	
	@Autowired
	private ValueCardService vCardService;
	
	/**
	 * 
	 * 方法描述：初始化储值卡列表页面
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月16日上午10:20:09 <br/>
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		return "/businessman/valueCard/valueCardList";
	}
	
	@RequestMapping(value="/init/list")
	@ResponseBody
	public Object getList(ValueCard vCard){
		log.info("获取储值卡列表："+vCard);
		Pageable<ValueCard> pageable = new Pageable<ValueCard>(vCard);
		try {
			List<ValueCard> list = vCardService.getList(vCard);
			pageable.setContent(list);
			pageable.setTotal(vCardService.count(vCard));
			log.info("查询成功");
		} catch (Exception e) {
			log.error("查询处置卡信息异常:",e);
		}
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：新增商家储值卡
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月17日上午11:30:34 <br/>
	 * @param valueCard
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(ValueCard valueCard){
		log.info("新增商家储值卡add："+valueCard);
		Resultable resultable;
		try {
			vCardService.add(valueCard);
			resultable = new Resultable(true,"新增成功！");
		} catch (Exception e) {
			log.error("新增商家储值卡异常:",e);
			resultable = new Resultable(false,"新增失败!");
		}
		return resultable;
	}
	
	
	/**
	 * 
	 * 方法描述：加载下拉列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月18日上午11:06:02 <br/>
	 * @param vCard
	 * @return
	 */
	@RequestMapping("/init/sellerList")
	@ResponseBody
	public Object getSellerList(ValueCard vCard){
		log.info("加载商家信息："+vCard);
		vCard.setLimit(20);
		Pageable<ValueCard> pageable = new Pageable<ValueCard>(vCard);
		if(3!=vCard.getSellerType()){
			pageable.setContent(vCardService.getSellerList(vCard));//连锁商家
		}else {
			pageable.setContent(vCardService.getAreaAgency(vCard));//区域代理
		}
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：释放储值卡
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午2:01:03 <br/>
	 * @param request
	 * @param vCard
	 * @param ucaptcha
	 * @return
	 */
	@RequestMapping("/close")
	@ResponseBody
	public Object closeValueCard(HttpServletRequest request,ValueCard vCard,@RequestParam(value="ucaptcha")String ucaptcha){
		log.info("释放商家储值卡："+vCard);
		Resultable resultable;
		HttpSession session  = request.getSession(true);
		Object  kaptcha = session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String code = (String)kaptcha;
		if(code != null && code.equals(ucaptcha)){//验证码
			try {
				
				vCard.setStatus(1);
				vCard.setUpdateTime(new Date());
				boolean update = vCardService.updateStatus(vCard);
				if(update){
					log.info("释放商家储值卡成功");
					resultable = new Resultable(true,"释放商家储值卡成功");
				}else {
					log.error("释放商家储值卡失败");
					resultable = new Resultable(false,"释放商家储值卡失败");
				}
			} catch (Exception e) {
				log.error("释放商家储值卡失败",e);
				resultable = new Resultable(false,"释放商家储值卡失败");
			}
		}else{
			log.error("验证码错误");
			resultable = new Resultable(false,"验证码错误！");
		}
		
		session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		return resultable;
	}
	
	
	@RequestMapping("/childSellerInit")
	public Object childSeller(ValueCard vCard){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/businessman/valueCard/valueCardChildList");
		mv.addObject("vCard",vCard);
		return mv;
	}
	
	/**
	 * 方法描述：获取连锁店下所有子商家
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午3:15:11 <br/>
	 * @param vcCard
	 * @return
	 */
	@RequestMapping("/childSellerInit/childSellerList")
	@ResponseBody
	public Object childSellerList(ValueCard vCard){
		log.info("获取商家所有自商家列表："+vCard);
		Pageable<TSeller> pageable = new Pageable<>(new TSeller());
		List<TSeller> childSellerList = vCardService.getChildSellerList(vCard);
		pageable.setContent(childSellerList);
		pageable.setTotal(vCardService.countChildSellerNum(vCard));
		return pageable;
	}
	
	@RequestMapping("/limit")
	@ResponseBody
	public Object limitSeller(TSeller tSeller){
		log.info("限制适用商户："+tSeller);
		Resultable resultable;
		try {
			vCardService.limitSeller(tSeller);
			log.info("更新成功");
			resultable = new Resultable(true,"更新成功");
		} catch (Exception e) {
			log.error("更新适用商户状态失败",e);
			resultable = new Resultable(false,"更新失败，系统异常");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：获取商家充值套餐列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月3日上午10:07:56 <br/>
	 * @param tSeller
	 * @return
	 */
	@RequestMapping("/add/init")
	@ResponseBody
	public Object getRechargeValueList(){
		log.info("获取商家充值套餐列表");
		Resultable resultable;
		try {
			List<Map<String, Object>> rechargeValue = vCardService.getRechargeValue();
			resultable=new Resultable(true,"获取成功",rechargeValue);
			log.info("获取商家充值套餐列表成功");
		} catch (Exception e) {
			log.error("获取商家充值套餐列表失败",e);
			resultable = new Resultable(false,"获取商家充值套餐列表失败");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：更新商家储值卡
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月17日上午11:30:34 <br/>
	 * @param valueCard
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(ValueCard valueCard){
		log.info("更新商家储值卡update："+valueCard);
		Resultable resultable;
		try {
			valueCard.setUpdateTime(new Date());
			Integer update = vCardService.update(valueCard);
			if(update != 1){
				log.error("更新商家储值卡失败");
				resultable = new Resultable(false,"更新商家储值卡失败!");
			}else{
				log.info("更新商家储值卡成功");
				resultable = new Resultable(true,"更新商家储值卡成功！");
			}
		} catch (Exception e) {
			log.error("更新商家储值卡异常:",e);
			resultable = new Resultable(false,"更新商家储值卡失败!");
		}
		return resultable;
	}
	
}
