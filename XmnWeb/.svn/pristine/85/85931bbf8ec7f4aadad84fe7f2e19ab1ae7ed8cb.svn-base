/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.entity.RechargeCardRecord;
import com.xmniao.xmn.core.businessman.service.RechargeCardRecordService;
import com.xmniao.xmn.core.fresh.entity.ActivityOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ValueCardRechargeContorller
 * 
 * 类描述：储蓄卡充值记录
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月22日 上午11:23:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/businessman/rechargeDetail")
public class ValueCardRechargeContorller extends BaseController{
	
	@Autowired
	private RechargeCardRecordService rService;
	
	@RequestMapping("/init")
	public ModelAndView init(RechargeCardRecord rCardRecord){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("businessman/valueCard/rechargeCardList");
		mv.addObject("rCardRecord", rCardRecord);
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：列表页面
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月22日上午11:25:36 <br/>
	 * @param vvCard
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getList(RechargeCardRecord rrcRecord){
		Pageable<RechargeCardRecord> pageable = new Pageable<>(rrcRecord);
		List<RechargeCardRecord> list = rService.getList(rrcRecord);
		pageable.setContent(list);
		pageable.setTotal(rService.count(rrcRecord));
		return pageable;
	}
	
	/**
	 * 导出订单列表
	 * @param bill
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/export")
	public void export(RechargeCardRecord rrcRecord, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		log.info("活动订单列表导出getList："+rrcRecord);
		rrcRecord.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", rService.getList(rrcRecord));
		doExport(request, response, "businessman/rechargeRecord.xls", params);
	}
}
