package com.xmniao.xmn.core.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.CityService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：CityController
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年09月28日 上午10:41:43
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 * 
 */
@RequestLogging(name="城市管理")
@Controller
@RequestMapping(value = "common/cityManagerment")
public class CityController extends BaseController {

	@Autowired
	private CityService cityService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * @author wangzhimin
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "common/allCityList";
	}
	
	/**
	 * 
	 * list(已开通城市列表数据初始化)
	 * 
	 * @author wangzhimin
	 */
	@RequestMapping(value = "/open/list")
	@ResponseBody
	public Object openCityList(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable = cityService.getCityListPageable(area);
		return pageable;
	}
	
	/**
	 * 
	 * list(未开通城市列表数据初始化)
	 * 
	 * @author wangzhimin
	 */
	@RequestMapping(value = "/lock/list")
	@ResponseBody
	public Object loclCityList(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable = cityService.getCityListPageable(area);
		return pageable;
	}
	
	/**
	 * 开通或者关闭区域
	 * @param tArea
	 * @return
	 */
	@RequestLogging(name = "更新区域状态")
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public Object updateStatus(TArea tArea){
		log.info("更新城市状态: " + tArea);
		boolean flag = true;
		try{
			cityService.updateStatus(tArea);
			log.info("城市状态更新成功");
		}catch(Exception e){
			flag = false;
			log.info("城市状态更新失败: " + e);
			cityService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("城市状态更新异常", e, new Object[]{tArea}).getMessage()), 0);
		}
		return recordLogAndReturn(tArea, flag);
	}
	
	/**
	 * 记录日志
	 * @param tArea
	 * @param flag
	 * @return
	 */
	private Resultable recordLogAndReturn(TArea tArea, boolean flag) {
		if(flag){
			String[] str = new String[]{"城市编号为", tArea.getAreaId().toString(), "更新状态", "更新"};
			cityService.fireLoginEvent(str, 1);
		}
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}
}
