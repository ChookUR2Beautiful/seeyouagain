package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;
import com.xmniao.xmn.core.businessman.service.SellerDetailedService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerDetailedController
 * 
 * 类描述：商家详情
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月18日20时39分45秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家信息管理")
@Controller
@RequestMapping(value = "businessman/sellerDetailed")
public class SellerDetailedController extends BaseController {

	@Autowired
	private SellerDetailedService sellerDetailedService;
	
	
	public BaseService<?> getService(){
		return sellerDetailedService;
	}
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/sellerDetailedList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerDetailed sellerDetailed) {
		Pageable<TSellerDetailed> pageable = new Pageable<TSellerDetailed>(sellerDetailed);
		pageable.setContent(sellerDetailedService.getList(sellerDetailed));
		pageable.setTotal(sellerDetailedService.count(sellerDetailed));
		return pageable;
	}
	
	/**
	 * 导出数据
	 * @param sellerDetailed
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = "export")
	public void export(TSellerDetailed sellerDetailed, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		sellerDetailed.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", sellerDetailedService.getList(sellerDetailed));
		doExport(request, response, "businessman/sellerDetailed.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("sellerid") String sellerid) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerDetailedService.delete(sellerid.split(","));
			if (resultNum > SellerConstants.COMMON_PARAM_Z) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerDetailed");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSellerDetailed sellerDetailed) {
		Resultable resultable = null;
		try {
			sellerDetailedService.add(sellerDetailed);
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
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("sellerid") String sellerid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerDetailed");
		modelAndView.addObject("isType", "update");
		try {
			TSellerDetailed sellerDetailed = sellerDetailedService.getObject(new Long(sellerid));
			this.log.info(sellerDetailed);
			modelAndView.addObject("sellerDetailed", sellerDetailed);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSellerDetailed  sellerDetailed) {
		Resultable resultable = null;
		try {
			if(null != sellerDetailed.getSellerid() && 0 != sellerDetailed.getSellerid()){ 
				sellerDetailedService.update(sellerDetailed);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
			}else{
				this.log.info("修改失败！");
				resultable = new Resultable(false, "修改失败！");
			}
			
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * batchUpdateIsFirstInit(批量设置是否开启首次初始化)
	 * @return
	 */
	@RequestMapping(value="/updateIsFirstBatch/init")
	public ModelAndView batchUpdateIsFirstInit(TSellerDetailed sellerDetailed){
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerDetailedIsFirstBatch");
		log.info("sellerIds = "+sellerDetailed.getSellerIds());
		sellerDetailedService.getBatchUpdateIsFirstInfo(sellerDetailed,modelAndView);
		return modelAndView;
	}

	/**
	 * batchUpdateIsFirstInit(批量设置是否开启首次)
	 * @return
	 */
	@RequestLogging(name="批量设置是否开启首次")
	@RequestMapping(value="/updateIsFirstBatch")
	@ResponseBody
	public Object batchUpdateIsFirst(TSeller seller){
		Resultable resultable = null;
		try{
			sellerDetailedService.batchUpdateIsFirst(seller);
			resultable = new Resultable(true, "操作成功");
		}catch(Exception e){
			log.error("更新异常",e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年7月6日 下午2:27:12
	 * @TODO 初始化页面
	 * @param sellerDetailed
	 * @return
	 */
	@RequestMapping(value="/authorizedInit")
	public ModelAndView AuthorizedInit(TSellerDetailed sellerDetailed){
		ModelAndView modelAndView = new ModelAndView("businessman/authorizedInit");
		sellerDetailed=sellerDetailedService.getSellerDetailed(sellerDetailed.getSellerid().longValue());
		modelAndView.addObject("sellerDetailed", sellerDetailed);
		return modelAndView;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年7月6日 下午2:27:26
	 * @TODO 数据更新
	 * @param sellerDetailed
	 * @return
	 */
	@RequestLogging(name="授权管理")
	@RequestMapping(value="/authorized")
	@ResponseBody
	public Object Authorized(TSellerDetailed sellerDetailed){
		Resultable resultable = null;
		try{
			sellerDetailedService.update(sellerDetailed);
			resultable = new Resultable(true, "操作成功");

		}catch(Exception e){
			log.error("更新异常",e);
			resultable = new Resultable(false, "操作失败");
		}finally{
			String[] s={"连锁店编号",String.valueOf(sellerDetailed.getSellerid()),"详细信息更新","更新"};
			sellerDetailedService.fireLoginEvent(s, resultable.getSuccess()?1:0);
			return resultable;
		}
	}	
}
