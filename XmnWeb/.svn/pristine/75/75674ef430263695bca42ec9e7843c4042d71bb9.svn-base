package com.xmniao.xmn.core.user_terminal.controller;

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
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.common.service.AdvertisingService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UserAdvertisingController
 * 
 * 类描述： 客户端后台管理广告轮播
 * 
 * 创建人： yang'xu
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="用户端管理")
@Controller
@RequestMapping(value="user_terminal/advertising")
public class UserAdvertisingController extends BaseController {

	@Autowired
	private AdvertisingService advertisingService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：yang'xu
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "user_terminal/userAdvertisingList";//修改了换回页面  注意补充
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：yang'xu
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAdvertising advertising) {
		Pageable<TAdvertising> pageable = new Pageable<TAdvertising>(advertising);
//		pageable.setContent(advertisingService.getList(advertising));
//		pageable.setTotal(advertisingService.count(advertising));
		//修改人：汪志民
		pageable.setContent(advertisingService.getADListForUser(advertising));
		pageable.setTotal(advertisingService.getUserADListcount(advertising));
		return pageable;
	}
	
	
		
	/**
	 * 导出列表
	 * @param advertising
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TAdvertising advertising, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		advertising.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", advertisingService.getADListForUser(advertising));
		doExport(request, response, "userTerminal/advertising.xls", params);//修改页面值  userClient/advertising.xls
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：yang'xu
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除用户端广告")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = advertisingService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"广告编号",id,"删除","删除"};
				advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"广告编号",id,"删除","删除"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：yang'xu
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editAdvertising");//修改返回页面为userClient/....
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：yang'xu
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加广告")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAdvertising advertising) {
		Resultable resultable = null;
		try {
			advertising.setIsshow(0);
			advertisingService.add(advertising);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表  remark 字段
			String word = advertising.getContent();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//
			String[] s={"广告轮播",str,"新增"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表  remark 字段
			String word = advertising.getContent();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			//
			String[] s={"广告轮播",str,"新增"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：yang'xu
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editAdvertising");//修改返回页面userClient/...
		modelAndView.addObject("isType", "update");
		try {
			TAdvertising advertising = advertisingService.getObject(new Long(id));
			this.log.info(advertising);
			modelAndView.addObject("advertising", advertising);
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
	 * @author：yang'xu
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改用户端广告")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAdvertising  advertising) {
		Resultable resultable = null;
		try {
			advertisingService.update(advertising);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"广告编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"广告编号",String.valueOf(advertising.getId()),"修改","修改"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 上线用户端广告.
	 * 
	 * @return
	 */
	@RequestMapping(value="/online")
	@RequestLogging(name="上线用户端广告")
	@ResponseBody
	public Resultable online(TAdvertising  advertising){
		Resultable resultable = null;
		try{
			advertisingService.onLineUserAdvertising(advertising);
			String[] s={"广告编号",String.valueOf(advertising.getId()),"上线","上线"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			resultable = new Resultable(true, "操作成功");
		}catch(Exception e){
			e.printStackTrace();
			String[] s={"广告编号",String.valueOf(advertising.getId()),"上线","上线"};
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	
	/**
	 * 下线用户端广告.
	 * 
	 * @param advertising
	 * @return
	 */
	@RequestLogging(name="下线用户端广告")
	@RequestMapping(value="/offline")
	@ResponseBody
	public Resultable offline(TAdvertising  advertising){
		Resultable resultable = null;
		String[] s={"广告编号",String.valueOf(advertising.getId()),"下线","下线"};
		try{
			advertisingService.offLineUserAdvertising(advertising);
			resultable = new Resultable(true, "操作成功");
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			resultable = new Resultable(false, "操作失败");
			advertisingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		}
		return resultable;
	}
}
