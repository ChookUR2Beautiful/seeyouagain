package com.xmniao.xmn.core.fresh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSupplier;
import com.xmniao.xmn.core.fresh.service.FreshManageService;
import com.xmniao.xmn.core.fresh.service.SupplierService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称： XmnWeb
 * 类名称： SupplierController.java
 * 类描述：供应商管理
 * 创建人： lifeng
 * 创建时间： 2016年6月16日下午5:59:15
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name="供应商管理")
@Controller
@RequestMapping(value = "/fresh/supplier")
public class SupplierController extends BaseController {

	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private FreshManageService freshManageService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "fresh/supplier/supplierList";
	}
	
	/**
	 * @Description: 查询供应商列表
	 * @Param:供应商
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月16日下午5:58:48
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object getSupplierList(TSupplier tSupplier){
		this.log.info("SupplierController-->tsupplier=" + tSupplier);
		Pageable<TSupplier> pageable = new Pageable<TSupplier>(tSupplier);
		pageable.setContent(supplierService.getTSupplierList(tSupplier));
		pageable.setTotal(supplierService.tSupplierCount(tSupplier));
		return pageable;
	}
	
	/**
	 * @Description: 添加初始化
	 * @return:ModelAndView
	 * @author:lifeng
	 * @time:2016年6月16日下午8:38:28
	 */
	@RequestMapping(value = "add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("action","fresh/supplier/add.jhtml");
		modelAndView.setViewName("fresh/supplier/supplierModel");
		return modelAndView;
	}

	/**
	 * @Description: 添加供应商
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月16日下午8:39:57
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加供应商")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(TSupplier tSupplier) {
		Resultable resultable = null;
		try {
			supplierService.addSupplier(tSupplier);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Description: 跳转到编辑供应商的页面
	 * @return:ModelAndView
	 * @author:lifeng
	 * @time:2016年6月16日下午8:38:28
	 */
	@RequestMapping(value = "edit/init")
	public ModelAndView editInit(String supplierId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("action","fresh/supplier/edit.jhtml");
		modelAndView.addObject("supplier",supplierService.getTSupplierById(supplierId));
		modelAndView.setViewName("fresh/supplier/supplierModel");
		return modelAndView;
	}

	/**
	 * @Description: 编辑供应商
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月16日下午8:39:57
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="编辑供应商")
	@RequestMapping(value = "edit")
	@ResponseBody
	public Object edit(TSupplier tSupplier) {
		Resultable resultable = null;
		try {
			supplierService.editSupplier(tSupplier);
			this.log.info("编辑成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("编辑异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * @Description: 删除供应商
	 * @Param:supplierId
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月17日下午3:26:03
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除招聘岗位")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("supplierId") String supplierId) {
		Resultable resultable = null;
		try {
			Integer resultNum = supplierService.deleteById(supplierId);
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"供应商编号",supplierId,"删除","删除"};
				supplierService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"供应商编号",supplierId,"删除","删除"};
			supplierService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Description: 检查产品
	 * @Param:supplierId
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月18日下午2:26:03
	 */
	@RequestLogging(name="检查供应商id是否存在对应的产品")
	@RequestMapping(value = "/checkSupplierId")
	@ResponseBody
	public Object checkSupplierId(HttpServletRequest request, @RequestParam("supplierId") String supplierId) {
		ProductInfo productInfo = freshManageService.getProductByParam(supplierId);
		return productInfo;
	}
	
	/**
	 * @Description: 
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月23日下午3:29:27
	 */
	@RequestMapping(value = "checkSupplierName",method=RequestMethod.POST)
	@ResponseBody
	public Object getSupplierById(@RequestParam("supplierName")String supplierName){
		return supplierService.getSupplierBySupplierName(supplierName);
	}
	
	/**
	 * @Description:校验手机号码的唯一性 
	 * @Param:
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月30日下午5:27:40
	 */
	@ResponseBody
	@RequestMapping(value="/checkPhone",method=RequestMethod.POST)
	public Long checkPhone(@RequestParam("phone") String phone){
		return supplierService.getSupplierByPhone(phone);
	}
}
