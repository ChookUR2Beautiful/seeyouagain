/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.entity.AbnormalSeller;
import com.xmniao.xmn.core.user_terminal.service.DealedAbnormalService;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：DealedAbnormalSellerControler
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016年8月6日上午11:19:52
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value="user_terminal/DealedAbnormalseller")
public class DealedAbnormalSellerControler extends BaseController{
	
	@Autowired
	private DealedAbnormalService dasService;
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/abnormalSeller/reportInfoList";
	}
	
	/**
	 * 
	 * 方法描述：查询已处理举报商家信息列表
	 * 创建人： chenjie
	 * 创建时间：2016年8月5日上午11:31:45
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getAbnormalSellerList(AbnormalSeller abnormalSeller){
		log.info("getAbnormalSellerList: " +abnormalSeller);
		Pageable<AbnormalSeller> pageable = new Pageable<AbnormalSeller>(abnormalSeller);
		pageable.setContent(dasService.getAbnormalSellerList(abnormalSeller));
		pageable.setTotal(dasService.getCount(abnormalSeller));
		return pageable;
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
			dasService.deleteInfoById(id);
			resultable.setSuccess(true);
			resultable.setMsg("删除成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(false);
			resultable.setMsg("删除失败");
			return resultable;
		}
	}
}
