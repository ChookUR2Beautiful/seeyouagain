/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;
import com.xmniao.xmn.core.fresh.service.HotSaleManageService;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：HotSaleManageController
 * 
 * 类描述： 
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月11日 下午5:48:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value = "fresh/hotsalemanage")
public class HotSaleManageController {
	
	private Logger log = LoggerFactory.getLogger(HotSaleManageController.class);
	
	/**
	 * 注入积分超市-产品管理服务
	 */
	@Autowired
	private HotSaleManageService hotSaleManageService;
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	/*
	 * 跳转到精选管理界面
	 */
	@RequestMapping(value = "init")
	public Object freshManage() {
		log.info("info-页面跳转->" + "fresh/hotSaleList.jsp");
		ModelAndView modelAndView = new ModelAndView("fresh/manage/hotSaleList");
		FreshType freshType = new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		modelAndView.addObject("freshTypes",freshTypes);
		return modelAndView;
	}

	/*
	 * 加载精选管理数据
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object getFreshInfoList(ProductInfo freshInfo) {
		log.info("查询条件：" + freshInfo);
		Pageable<ProductInfo> pageable = new Pageable<ProductInfo>(freshInfo);
		pageable.setContent(hotSaleManageService
				.selectProductInfoList(freshInfo));
		pageable.setTotal(hotSaleManageService
				.selectProductInfoCount(freshInfo));
		return pageable;
	}
	
	/**
	 * 方法描述：修改精选排序
	 * 创建人： lifeng
	 * 创建时间：2016年8月12日下午2:21:56
	 * @param materialOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "init/updateChoiceSort")
	public Object updateChoiceSortByPid(ProductInfo productInfo) {
		Resultable resultable = null;
		try {
			if (null != productInfo) {
				hotSaleManageService.updateChoiceSortByPid(productInfo);
				resultable = new Resultable(true, "修改成功!");
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
}
