package com.xmniao.xmn.core.user_terminal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.system_settings.entity.Category;
import com.xmniao.xmn.core.user_terminal.entity.TSellerTrader;
import com.xmniao.xmn.core.user_terminal.service.SellerTraderService;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 用户端商家分类处理
 * @author ch
 *
 */
@RequestLogging(name = "用户端商家分类")
@RequestMapping("user_terminal/sellerTrader")
@Controller
public class SellerTraderController {
	
	
	@Autowired SellerTraderService sellerTraderService;
	
	/**
	 * 一级分类初始化
	 * @return
	 */
	@RequestMapping("init")
	public String init(){
		return "user_terminal/sellerTrader/firstTraderList";
	}
	
	/**
	 * 一级分类列表
	 * @return
	 */
	@RequestMapping("init/list")
	@ResponseBody
	public Object initList(Category category){
		return sellerTraderService.getFirstTraderList(category);
	}
	
	/**
	 * 二级分类初始化
	 * @param view
	 * @param tid 一级分类编号
	 * @return
	 */
	@RequestMapping("twoTrader/init")
	public ModelAndView twoTraderInit(ModelAndView view,@RequestParam("tid") String tid){
		view.setViewName("user_terminal/sellerTrader/twoTraderList");
		view.addObject("pid", tid);
		return view;
	}
	
	/**
	 * 二级分类列表
	 * @param tid 一级分类编号
	 * @return
	 */
	@RequestMapping("twoTrader/init/list")
	@ResponseBody
	public Object twoTraderInitList(Category category){
		return sellerTraderService.getTwoTraderListByPid(category);
	}
	
	/**
	 * 二级分类商家管理初始化
	 * @param view
	 * @param tid
	 * @return
	 */
	@RequestMapping("twoTrader/SellerMgt/init")
	public ModelAndView twoTraderSellerMgtInit(ModelAndView view,@RequestParam("tid") String tid,@RequestParam("pid") String pid){
		view.setViewName("user_terminal/sellerTrader/sellerTraderView");
		view.addObject("tid", tid);
		view.addObject("pid", pid);
		return view;
	}
	
	
	@RequestMapping("twoTrader/SellerMgt/init/addSeller/init/list")
	@ResponseBody
	public Object twoTraderAddSellerList(TSeller seller){
		return sellerTraderService.getTwoTraderAddSellerList(seller);
	}
	
	
	@RequestMapping("twoTrader/SellerMgt/init/noAddSeller/init/list")
	@ResponseBody
	public Object twoTraderNoAddSellerList(TSeller seller){
		return sellerTraderService.getTwoTraderNoAddSellerList(seller);
	}
	
	
	@RequestLogging(name = "添加商家分类关系")
	@RequestMapping("twoTrader/SellerMgt/add")
	@ResponseBody
	public Object twoTraderNoAddSellerAdd(TSellerTrader sellerTrader,HttpServletRequest request){
		Resultable resultable = null;
		try{
			resultable =   sellerTraderService.addTSellerTrader(sellerTrader, ResultUtil.getCurrentUser(request).getUserId().toString());
		}catch(Exception e){
			resultable = new Resultable(Boolean.FALSE, "添加失败");
		}
		return resultable;
	}
	
	
	@RequestLogging(name = "移除商家分类关系")
	@RequestMapping("twoTrader/SellerMgt/remove")
	@ResponseBody
	public Object twoTraderNoAddSellerRemove(TSellerTrader sellerTrader,HttpServletRequest request){
		Resultable resultable = null;
		try{
			resultable =   sellerTraderService.removeTSellerTrader(sellerTrader, ResultUtil.getCurrentUser(request).getUserId().toString());
		}catch(Exception e){
			resultable = new Resultable(Boolean.FALSE, "删除失败");
		}
		return resultable;
	}
	
}
