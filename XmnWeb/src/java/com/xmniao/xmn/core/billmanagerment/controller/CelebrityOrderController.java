/**
 * 
 */
package com.xmniao.xmn.core.billmanagerment.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.entity.CelebrityOrder;
import com.xmniao.xmn.core.billmanagerment.service.CelebirtyOrderService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CelebrityOrderController
 * 
 * 类描述： 名嘴食评
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月20日 下午3:30:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("billmanagerment/celebraty")
public class CelebrityOrderController extends BaseController{
	
	@Autowired
	private CelebirtyOrderService cOrderService;
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(CelebrityOrderController.class);
	
	/**
	 * 
	 * 方法描述：页面初始化
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月20日下午3:34:27 <br/>
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		return "/billmanagerment/celebrityOrderList";
	}
	
	/**
	 * 
	 * 方法描述：获取订单列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月20日下午3:34:43 <br/>
	 * @param cOrder
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object getList(CelebrityOrder cOrder){
		log.info("获取名嘴食评订单列表："+cOrder);
		Pageable<CelebrityOrder> pageable = new Pageable<>(cOrder);
		pageable.setContent(cOrderService.getList(cOrder));
		pageable.setTotal(cOrderService.count(cOrder));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转订单详情页
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月21日上午10:13:26 <br/>
	 * @param cOrder
	 * @return
	 */
	@RequestMapping("/update/init")
	public String updateInit(CelebrityOrder cOrder){
		return "/billmanagerment/celebrityOrderDetails";
	}
	
	/**
	 * 
	 * 方法描述：更新订单状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月21日上午10:13:00 <br/>
	 * @param cOrder
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(CelebrityOrder cOrder){
		Resultable resultable = null;
		
		return resultable;
	}
}
