package com.xmniao.xmn.core.manor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.manor.entity.TManorMarketTrans;
import com.xmniao.xmn.core.manor.entity.TManorMarketTransDynamic;
import com.xmniao.xmn.core.manor.service.ManorMarketTransDynamicService;
import com.xmniao.xmn.core.manor.service.ManorMarketTransService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="庄园市集管理")
@Controller
@RequestMapping(value = "manorMarketTrans/manage")
public class ManorMarketTransController extends BaseController {
	
	/**
	 * 庄园市集管理服务
	 */
	@Autowired
	private ManorMarketTransService manorMarketTransService;
	
	@Autowired
	private ManorMarketTransDynamicService manorMarketTransDynamicService;
	
	
	@RequestMapping(value = "init")
	public String init() {
		return "golden_manor/manorMarketTransManage";
	}
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TManorMarketTrans manorMarketTrans) {
		Pageable<TManorMarketTrans> pageable = new Pageable<TManorMarketTrans>(manorMarketTrans);
		
		//用户类型 1 主播 2 普通用户
		try {
			pageable = manorMarketTransService.getManorMarketTransList(manorMarketTrans);
//			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(TManorMarketTrans manorMarketTrans){
		Resultable result=new Resultable();
		try {
			manorMarketTransService.saveUpdateActivity(manorMarketTrans);
			
			result.setMsg("更新成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
//			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}

	
	@RequestMapping(value = "list/viewDynamic")
	@ResponseBody
	public Object getManorMemberLowerLevelList(TManorMarketTransDynamic manorMarketTransDynamic) {
		Pageable<TManorMarketTransDynamic> pageable = new Pageable<TManorMarketTransDynamic>(manorMarketTransDynamic);
		try {
			pageable = manorMarketTransDynamicService.getManorMarketTransDynamicList(manorMarketTransDynamic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	

	
	
}
