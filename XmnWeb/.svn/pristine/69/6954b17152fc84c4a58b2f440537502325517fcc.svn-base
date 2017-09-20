/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.entity.AbnormalSeller;
import com.xmniao.xmn.core.user_terminal.service.AbnormalSellerService;
import com.xmniao.xmn.core.util.DateEditor;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AbnromalSellerController
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： chenjie
 * 
 * 创建时间：2016年8月4日下午5:40:20
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */


@RequestLogging(name="商家举报信息管理")
@Controller
@RequestMapping(value="user_terminal/abnormal_seller")
public class AbnormalSellerController extends BaseController{
	
	@Autowired
	private AbnormalSellerService abnormalSellerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * 初始化页面
	 * 创建时间：2016年8月4日下午6:37:49
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/abnormalSeller/abnormalList";
	}
	
	/**
	 * 
	 * 方法描述：查询待处理举报商家信息列表
	 * 创建人： chenjie
	 * 创建时间：2016年8月5日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getAbnormalSellerList(AbnormalSeller abnormalSeller){
		log.info("getAbnormalSellerList: " +abnormalSeller);
		Pageable<AbnormalSeller> pageable = new Pageable<AbnormalSeller>(abnormalSeller);
		pageable.setContent(abnormalSellerService.getAbnormalSellerList(abnormalSeller));
		pageable.setTotal(abnormalSellerService.getCount(abnormalSeller));
		return pageable;
	}
	
	
	
	/**
	 * 方法描述：处理举报商家
	 * 创建人： huang'tao
	 * 创建时间：2016年8月5日下午6:19:38
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Object dealAbnormalSeller(AbnormalSeller abnormalSeller){
		log.info("dealAbnormalSeller: "+abnormalSeller);
		Resultable resultable = abnormalSellerService.dealInfo(abnormalSeller);
		return resultable;
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
	public Object deleteInfo(String id){
		log.info("deleteInfo : id = "+id);
		Resultable resultable = new Resultable();
		try {
			abnormalSellerService.deleteInfoById(id);
			resultable.setSuccess(true);
			resultable.setMsg("删除成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(false);
			resultable.setMsg("删除失败");
			return resultable;
		}
	}
	
	@RequestMapping(value="export")
	public void export(AbnormalSeller abnormalSeller,HttpServletRequest request,HttpServletResponse response){

		abnormalSeller.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		List<AbnormalSeller> list = abnormalSellerService.getAbnormalSellerList(abnormalSeller);
		params.put("list",list);
		doExport(request, response, "userTerminal/abnormalSeller.xls", params);
	}
}



