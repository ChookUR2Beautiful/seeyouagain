package com.xmniao.xmn.core.marketingmanagement.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.marketingmanagement.entity.TPhoneBill;
import com.xmniao.xmn.core.marketingmanagement.service.PhoneBillService;

@Controller
@RequestMapping(value = "marketingManagement/phoneBill")
public class PhoneBillController extends BaseController{
	
	@Autowired
	private PhoneBillService phoneBillService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView  mv = new ModelAndView();
		mv.addObject("requestInit", "marketingManagement/phoneBill/init/list");
		mv.setViewName("marketingManagement/phoneBill/phoneBillList");
		return mv;
	} 

	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@RequestMapping("init/list")
	public ModelAndView getList(TPhoneBill tphoneBill){
		ModelAndView  mv = new ModelAndView();
		Pageable<TPhoneBill> pageable = new Pageable<TPhoneBill>(tphoneBill);
		TPhoneBill total =null;
		 try{
			 total =phoneBillService.getCountList(tphoneBill);
			 pageable.setContent(phoneBillService.getList(tphoneBill));
			 pageable.setTotal(phoneBillService.count(tphoneBill));
		 }catch(Exception e){
			log.error("查询iphone6活动统计列表页面异常", e);
		 }
		mv.addObject("pageable", pageable);
		mv.addObject("total", total);
		mv.setViewName("marketingManagement/phoneBill/phoneBillTable");
		return mv;
	}
	
	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@RequestMapping("viewOrder")
	public ModelAndView viewOrder(TPhoneBill tphoneBill){
		ModelAndView  mv = new ModelAndView();
		 try{
			 mv = phoneBillService.viewOrder(tphoneBill);
		 }catch(Exception e){
			log.error("订单查看页面异常", e);
		 }
		return mv;
	}
	
	
	/**
	 * 导出商户列表
	 * 
	 * @param seller
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TPhoneBill tphoneBill, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		tphoneBill.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", phoneBillService.getList(tphoneBill));
		doExport(request, response, "marketingManagement/phoneBill.xls", params);
	}
	
}
