/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveLedgerRecord;
import com.xmniao.xmn.core.live_anchor.service.TLiveLedgerRecordService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveLedgerRecordController
 * 
 * 类描述： 直播分账记录Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-1 下午4:53:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播分账记录管理")
@Controller
@RequestMapping(value = "liveLedgerRecord/manage")
public class LiveLedgerRecordController extends BaseController {
	
	/**
	 * 注入直播分账记录Service
	 */
	@Autowired
	TLiveLedgerRecordService liveLedgerService;
	
	/**
	 * 注入寻蜜鸟用户服务
	 */
	@Autowired
	BursService bursService;
	
	/**
	 * 
	 * 方法描述：跳转到直播支付订单管理页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午4:55:00
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "live_anchor/liveLedgerRecordManage";
	}
	
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object initList(TLiveLedgerRecord liveLedgerRecord){
		Pageable<TLiveLedgerRecord> pageable=new Pageable<TLiveLedgerRecord>(liveLedgerRecord);
		List<TLiveLedgerRecord> list = liveLedgerService.getListContainUrsInfo(liveLedgerRecord);
		Long count = liveLedgerService.count(liveLedgerRecord);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	

	/**
	 * 
	 * 方法描述：导出直播分账记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2下午2:00:47
	 * @param liveLedgerRecord
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TLiveLedgerRecord liveLedgerRecord,HttpServletRequest request,HttpServletResponse response){
		liveLedgerRecord.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("list", liveLedgerService.getListContainUrsInfo(liveLedgerRecord));
		doExport(request,response,"live_anchor/liveLedgerRecord.xls",params);
	}

}
