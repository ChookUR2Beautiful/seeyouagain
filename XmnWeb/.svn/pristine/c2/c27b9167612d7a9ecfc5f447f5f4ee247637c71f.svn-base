/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.entity.TRegisterGift;
import com.xmniao.xmn.core.user_terminal.service.RegisterGiftService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：RegisterGiftController
 *
 * 类描述：注册赠送礼包
 * 
 * 创建人：ChenBo
 * 
 * 创建时间：2016年8月9日下午5:48:42
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@RequestLogging(name="会员注册礼包管理")
@Controller
@RequestMapping(value="user_terminal/register_gift")
public class RegisterGiftController extends BaseController{
	private  Logger log = LoggerFactory.getLogger(RegisterGiftController.class);
	
	@Autowired
	private RegisterGiftService registerGiftService; 
	
	
	/**
	 * 初始化页面
	 * 创建时间：2016年8月4日下午6:37:49
	 * @return
	 */
	@RequestMapping(value="init")
	public Object init(){
		ModelAndView mv = new ModelAndView("user_terminal/registerGiftList");
		Map<String,String> picMap = registerGiftService.getRegisterGiftPic();
		mv.addObject("img", picMap);
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：获取礼包列表数据
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getGiftList(TRegisterGift registerGift){
		Pageable<TRegisterGift> pageable = new Pageable<TRegisterGift>(registerGift);
		List<TRegisterGift> list = registerGiftService.getGiftList(registerGift);
		long count = registerGiftService.getGiftCount(registerGift);
		pageable.setContent(list);
		pageable.setTotal(count);
 		return pageable;
	}
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "update/init")
	@ResponseBody
	public Object updateinit(Integer id){
		ModelAndView mv = new ModelAndView("user_terminal/editRegisterGift");
		mv.addObject("isType", "update");
		TRegisterGift registerGift = registerGiftService.getRegisterGift(id);
		mv.addObject("registerGift", registerGift);
		return mv;
	}	
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(TRegisterGift registerGift){
		
		return registerGiftService.updateRegisterGift(registerGift);
	}
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "add/init")
	@ResponseBody
	public Object addinit(TRegisterGift registerGift){
		ModelAndView mv = new ModelAndView("user_terminal/editRegisterGift");
		mv.addObject("isType", "add");
		return mv;
	}
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(TRegisterGift registerGift){
		Resultable resultable = new Resultable();
		try {
			 registerGiftService.insterRegisterGift(registerGift);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：删除举报商家信息
	 * 创建人： chenjie
	 * 创建时间：2016年8月5日下午6:20:04
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(Integer id){
		return registerGiftService.deleteRegisterGift(id);
	}
	
	/**
	 * 方法描述：编辑注册图
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "img/update/init")
	@ResponseBody
	public Object initImg(){
		ModelAndView mv = new ModelAndView("user_terminal/editRegisterGiftImg");
		Map<String,String> picMap = registerGiftService.getRegisterGiftPic();
		mv.addObject("img", picMap);
		return mv;
	}
	
	/**
	 * 方法描述：编辑注册图
	 * 创建人： ChenBo
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "img/update")
	@ResponseBody
	public Object updateImg(@RequestParam("registerImg") String registerImg,@RequestParam("giftImg") String giftImg,@RequestParam("registerImgId")Integer registerImgId,@RequestParam("giftImgId")Integer giftImgId){
		Resultable resultable = new Resultable();
		try {
			Map<String,Object> picMap = new HashMap<String,Object>();
			picMap.put("registerImg", registerImg);
			picMap.put("giftImg", giftImg);
			picMap.put("registerImgId", registerImgId);
			picMap.put("giftImgId", giftImgId);
			registerGiftService.updateRegisterGiftImg(picMap);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
}
