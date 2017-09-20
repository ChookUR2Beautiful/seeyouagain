package com.xmniao.xmn.core.businessman.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.entity.TAppPush;
import com.xmniao.xmn.core.common.service.AppPushService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AppPushController
 * 
 * 类描述： APP推送信息
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日19时29分58秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家消息推送管理")
@Controller("businessmanAppPushController")
@RequestMapping(value = "businessman/appPush")
public class AppPushController extends BaseController {

	@Autowired
	private AppPushService appPushService;
	
	@Autowired
	private SellerService sellerService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init(Model model) {
		model.addAttribute("client", SellerConstants.CLIENT);
		return "common/appPushList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAppPush appPush) {
		Pageable<TAppPush> pageable = new Pageable<TAppPush>(appPush);
		pageable.setContent(appPushService.getList(appPush));
		pageable.setTotal(appPushService.count(appPush));
		return pageable;
	}

	/**
	 * 导出数据
	 * @param appPush
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TAppPush appPush, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		appPush.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", appPushService.getList(appPush));
		doExport(request, response, "common/appPush.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家端消息推送删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("tid") String tid) {
		Resultable resultable = null;
		try {
			Integer resultNum = appPushService.delete(tid.split(","));
			if (resultNum >SellerConstants.COMMON_PARAM_Z) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"商家消息推送编号",tid,"删除","删除"};
				appPushService.fireLoginEvent(s,1);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"商家消息推送编号",tid,"删除","删除"};
			appPushService.fireLoginEvent(s,0);
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
		ModelAndView modelAndView = new ModelAndView("common/editAppPush");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商家端消息推送添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAppPush appPush) {
		Resultable resultable = null;
		try {
			appPushService.add(appPush);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表  remark 字段
			String word = appPush.getTitle();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			String[] s={"商家消息推送",str,"新增"};
			appPushService.fireLoginEvent(s,1);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表  remark 字段
			String word = appPush.getTitle();
			String str = "";
			if (word.length() <=SellerConstants.WORD_LENTGH){
				str = word;
			}else{
				str = word.substring(0,SellerConstants.WORD_LENTGH)+"...";
			}
			String[] s={"商家消息推送",str,"新增"};
			appPushService.fireLoginEvent(s,0);
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
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("tid") String tid) {
		ModelAndView modelAndView = new ModelAndView("common/editAppPush");
		modelAndView.addObject("isType", "update");
		try {
			TAppPush appPush = appPushService.getObject(new Long(tid));
			this.log.info(appPush);
			modelAndView.addObject("appPush", appPush);
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
	@RequestLogging(name="商家端消息推送修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAppPush  appPush) {
		Resultable resultable = null;
		try {
			appPushService.update(appPush);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"商家消息推送编号",String.valueOf(appPush.getTid()),"修改","修改"};
			appPushService.fireLoginEvent(s,1);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={" 商家消息推送编号",String.valueOf(appPush.getTid()),"修改","修改"};
			appPushService.fireLoginEvent(s,0);
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 选择商家商页面
	 * @return
	 */
	@RequestMapping(value = "init/choseSeller/init")
	public ModelAndView choseSellerInit(ModelAndView mv){
		mv.setViewName("businessman/choseSeller");
		mv.addObject("listUrl", "businessman/appPush/init/choseSeller/list.jhtml");
		return mv;
	}

	/**
	 * 选择合作商列表
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "init/choseSeller/list")
	public Object choseSellerlist(TSeller seller) {
		int[] onlines=SellerConstants.SELLER_ONLINES;
		seller.setIsonlines(onlines);// 上线合作商家
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable.setContent(sellerService.getList(seller));
		pageable.setTotal(sellerService.count(seller));
		return pageable;
	}

}