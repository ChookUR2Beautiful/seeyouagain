/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRechargeCombo;
import com.xmniao.xmn.core.live_anchor.service.TLiveRechargeComboService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRechargeComboController
 * 
 * 类描述： 直播鸟币充值套餐Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-8-31 下午6:42:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播鸟币充值套餐管理")
@Controller
@RequestMapping(value = "liveRechargeCombo/manage")
public class LiveRechargeComboController extends BaseController {
	
	/**
	 * 注入直播鸟币充值套餐服务
	 */
	@Autowired
	private TLiveRechargeComboService liveRechargeComboService;
	
	@RequestMapping(value="init")
	public String init(){
		return "live_anchor/liveRechargeComboManage";
	}
	
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object initList(TLiveRechargeCombo liveRechargeCobo){
		Pageable<TLiveRechargeCombo> pageable = new Pageable<TLiveRechargeCombo>(liveRechargeCobo);
		List<TLiveRechargeCombo> list = liveRechargeComboService.getList(liveRechargeCobo);
		Long count = liveRechargeComboService.count(liveRechargeCobo);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到新增套餐页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1上午11:04:37
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "live_anchor/rechargeComboEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增鸟币充值套餐
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午2:29:45
	 * @param liveRechargeCombo
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TLiveRechargeCombo liveRechargeCombo){
		Resultable result=new Resultable();
		try {
			liveRechargeCombo.setUpdateDate(new Date());
			liveRechargeComboService.dealObjectOrienteds(liveRechargeCombo);
			liveRechargeComboService.add(liveRechargeCombo);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("新增鸟币充值套餐失败："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改充值套餐页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午3:01:05
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TLiveRechargeCombo liveRechargeCombo,Model model){
		TLiveRechargeCombo rechargeCombo = liveRechargeComboService.getObject(liveRechargeCombo.getId().longValue());
		model.addAttribute("rechargeCombo", rechargeCombo);
		return "live_anchor/rechargeComboEdit";
	}
	
	/**
	 * 
	 * 方法描述：修改鸟币充值套餐
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午2:29:45
	 * @param liveRechargeCombo
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TLiveRechargeCombo liveRechargeCombo){
		Resultable result=new Resultable();
		try {
			liveRechargeComboService.dealObjectOrienteds(liveRechargeCombo);
			Integer count = liveRechargeComboService.update(liveRechargeCombo);
			if(count>0){
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("修改鸟币充值套餐失败："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：修改鸟币充值套餐
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午2:29:45
	 * @param liveRechargeCobo
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Resultable delete(@RequestParam(value="ids") String ids){
		Resultable result=new Resultable();
		try {
			Map<String,Object> requstMap=new HashMap<String,Object>();
			requstMap.put("ids",ids.split(","));
			requstMap.put("status","2");//套餐状态 默认1 ： 1有效  2 无效
			liveRechargeComboService.updateBatchStatus(requstMap);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("修改鸟币充值套餐失败："+e.getMessage(), e);
		}
		return result;
	}

}
