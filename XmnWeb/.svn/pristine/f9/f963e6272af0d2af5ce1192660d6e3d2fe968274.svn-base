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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.FoodDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerDetailedDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerApply;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;
import com.xmniao.xmn.core.businessman.entity.TSellerPicApply;
import com.xmniao.xmn.core.businessman.service.SellerApplyService;
import com.xmniao.xmn.core.businessman.service.SellerPicService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerApplyController
 * 
 * 类描述： 商户信息修改申请
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日15时57分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商户信息修改申请")
@Controller
@RequestMapping(value = "businessman/sellerApply")
public class SellerApplyController extends BaseController {

	@Autowired
	private SellerApplyService sellerApplyService;
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private SellerDetailedDao sellerDetailedDao;
	
	@Autowired
	private SellerPicService sellerPicService;
	

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/sellerApplyList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerApply sellerApply) {
		Pageable<TSellerApply> pageable = new Pageable<TSellerApply>(sellerApply);
		pageable.setContent(sellerApplyService.getList(sellerApply));
		pageable.setTotal(sellerApplyService.count(sellerApply));
		return pageable;
	}

	/**
	 * 导出列表
	 * @param comment
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TSellerApply sellerApply, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		sellerApply.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", sellerApplyService.getList(sellerApply));
		doExport(request, response, "businessman/sellerApply.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家信息修改申请删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerApplyService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerApply");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家信息修改申请添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSellerApply sellerApply) {
		Resultable resultable = null;
		try {
			sellerApplyService.add(sellerApply);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerApply");
		modelAndView.addObject("isType", "update");
		try {
			TSellerApply sellerApply = sellerApplyService.getObject(new Long(id));
			this.log.info(sellerApply);
			modelAndView.addObject("sellerApply", sellerApply);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家信息修改申请修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSellerApply  sellerApply) {
		Resultable resultable = null;
		try {
			sellerApplyService.update(sellerApply);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		}  
		return resultable;
		
	}
	

	/**
	 * 
	 * 审批初始化(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "updateState/init")
	public ModelAndView stateInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/applyReason");
		return modelAndView;
	}


	/**
	 * 
	 * 批量审批
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家信息修改申请批量审批")
	@RequestMapping(value = "updateState")
	@ResponseBody
	public Object updateState(TSellerApply sellerApply) {
		Resultable resultable = null;
		try {
			resultable=sellerApplyService.updateApplyStauts(sellerApply);
		} catch (Exception e) {
			this.log.info("商家信息修改申请批量审批异常："+e);
			sellerApplyService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("商家信息修改申请批量审批", e, new Object[]{sellerApply}).getMessage()),0);
			throw new ApplicationException("商家信息修改申请批量审批异常",e,new Object[]{sellerApply});
		}
		return resultable;
	}
	/**
	 * 查看商家申请信息
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/view/init")
	public ModelAndView viewInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("businessman/viewSellerApply");
		try {
			
			/**
			 * 根据申请表id查询商家申请基本资料（基本资料、logo和使用说明）
			 */
			TSellerApply sellerApply = sellerApplyService.getObject(new Long(id));
			
			Integer source = sellerApply.getSource();
			/**
			 * 根据申请表id(sellerApply.getId();)查询t_seller_pic_apply申请修改的图片（环境图）
			 */
			if(source!=null && source==2){
				TSellerPicApply tp = new TSellerPicApply();
				tp.setSaid(Integer.parseInt(id));
				List<TSellerPicApply> piclist = sellerApplyService.getPicApplyList(tp);
				sellerApply.setSellerPicApply(piclist);
			}else{
				log.info("商家申请修改商家基本信息");
			}
			
			/**
			 * 原商家基本资料（基本资料和使用说明）
			 */
			TSeller seller = sellerDao.getObject(new Long(sellerApply.getSellerid()));

			/**
			 * 原商家图片（logo和环境图）
			 */
			TSellerPic ts = new TSellerPic();
			ts.setSellerid(sellerApply.getSellerid());
			
			//1表示商家图片LOGO
			ts.setIslogo(1);
			List<TSellerPic> pList = sellerPicService.getList(ts);
			if(!pList.isEmpty() && pList.size() > 0){
				TSellerPic logo = pList.get(0);
				seller.setUrl(logo.getPicurl());
			}
			if(source!=null && source==2){
				//0表示商家环境图 add by hls
//				ts.setIslogo(0);
				ts.setIslogo(null);
				List<TSellerPic> purlList = sellerPicService.getList(ts);
				if(!purlList.isEmpty()){
					seller.setPurlList(purlList);
				}
				
				for (TSellerPicApply tSellerPic : sellerApply.getSellerPicApply()) {
					if(tSellerPic.getType() == 0){
						modelAndView.addObject("type0",0);//logo图
					}
					if(tSellerPic.getType() == 1){
						modelAndView.addObject("type1",0);//环境图
					}
					if(tSellerPic.getType() == 2){
						modelAndView.addObject("type2",0);//推荐图
					}
				}
			}else{
				log.info("商家申请修改商家基本信息");
			}
			this.log.info(seller);
			this.log.info(sellerApply);
			modelAndView.addObject("selleridList", seller);
			modelAndView.addObject("sellerApply", sellerApply);
			
		
			
		} catch (Exception e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

}