/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.PostTemplate;
import com.xmniao.xmn.core.cloud_design.entity.Supplier;
import com.xmniao.xmn.core.cloud_design.service.SupplierManageService;
import com.xmniao.xmn.core.cloud_design.service.TransportFeeService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SupplierManagerController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月16日 上午9:43:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="供应商管理")
@Controller
@RequestMapping("supplier/manager")
public class SupplierManagerController extends BaseController{
	
	@Autowired
	private SupplierManageService suManageService;
	
	@Autowired
	private TransportFeeService tfFeeService;
	
	@RequestMapping("/init")
	public String init(){
		return "cloud_design/supplier/supplierList";
	}
	
	/**
	 * 
	 * 方法描述：获取供应商列表
	 * 创建人： chenJie 
	 * 创建时间：2016年11月16日上午11:58:12
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getSupplierList(Supplier supplier){
		log.info("获取供应商列表："+supplier);
		Pageable<Supplier> pageable = new Pageable<>(supplier);
		pageable.setContent(suManageService.getList(supplier));
		pageable.setTotal(suManageService.count(supplier));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：添加初始化
	 * 创建人： chenJie 
	 * 创建时间：2016年11月16日下午3:21:53
	 * @return
	 */
	@RequestMapping("/add/init")
	public ModelAndView addInit(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("action","supplier/manager/add.jhtml");
		modelAndView.setViewName("cloud_design/supplier/suplierView");
		modelAndView.addObject("postTemplate",tfFeeService.getListTwo(new PostTemplate()));
		modelAndView.addObject("isType","add");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：新增供应商
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日上午10:09:01 <br/>
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(Supplier supplier){
		log.info("新增供应商："+supplier);
		Resultable resultable = null;
		try {
			supplier.setCreateTime(new Date());//创建时间
			suManageService.add(supplier);
			resultable = new Resultable(true, "添加成功！");
		} catch (Exception e) {
			log.error("新增供应商失败",e);
			resultable = new Resultable(false, "添加失败！");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：检查该供应商是否有关联商品
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午2:16:48 <br/>
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/delete/checkData")
	@ResponseBody
	public Object checkSupplier(Supplier supplier){
		log.info("检查该供应商是否有关联商品"+supplier);
		Resultable resultable = null;
		try {
			boolean result = suManageService.checkData(supplier);
			if(result){
				resultable = new Resultable(true, "验证成功！");
			}else {
				resultable = new Resultable(false,"验证失败");
			}
		} catch (Exception e) {
			log.error("验证出错",e);
			return resultable = new Resultable(false, "系统异常");
		}
		return resultable;
	}
	/**
	 * 
	 * 方法描述：删除供应商
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午2:14:50 
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Supplier supplier){
		log.info("删除供应商："+supplier);
		Resultable resultable = null;
		try {
			suManageService.delete(supplier);
			resultable = new Resultable(true,"删除成功!");
		} catch (Exception e) {
			log.error("删除供应商异常",e);
			resultable = new Resultable(true,"删除失败！");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：初始化编辑页面
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午4:33:36 <br/>
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/edit/init")
	@ResponseBody
	public ModelAndView editInit(Supplier supplier){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("action","supplier/manager/edit.jhtml");
		modelAndView.setViewName("cloud_design/supplier/suplierView");
		modelAndView.addObject("postTemplate",tfFeeService.getListTwo(new PostTemplate()));
		modelAndView.addObject("supplier",suManageService.getSupplier(supplier));
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：更新商家信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午4:48:19 <br/>
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(Supplier supplier){
		log.info("修改供应商信息："+supplier);
		Resultable resultable = null;
		try {
			Integer result = suManageService.update(supplier);
			if (result==1) {
				log.info("修改成功");
				resultable = new Resultable(true,"修改成功");
			}else {
				log.info("修改失败");
				resultable = new Resultable(false,"修改失败");
			}
		} catch (Exception e) {
			log.info("修改失败",e);
			resultable = new Resultable(false,"修改失败");
		}
		return resultable;
	}
	
	
	/**
	 * 
	 * 方法描述：获取关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午4:30:28 <br/>
	 * @param productFailingSerial
	 * @return
	 */
	@RequestMapping(value = "getDesignerSuppliers",method=RequestMethod.POST)
	@ResponseBody
	public Object getTags(Supplier supplier) {
		Pageable<Supplier> pageable = new Pageable<Supplier>(supplier);
		supplier.setStatus(0);//0正常，1废弃
		List<Supplier> suppliers = suManageService.getList(supplier);
		pageable.setContent(suppliers);
		return pageable;
	}
}
