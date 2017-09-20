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
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerInternalInfo;
import com.xmniao.xmn.core.businessman.service.SellerInternalInfoService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：sellerInternalInfoController
 * 
 * 类描述：站内消息推送
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日15时57分01秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家站内消息推送管理")
@Controller
@RequestMapping(value = "businessman/sellerMsg")
public class SellerInternalInfoController extends BaseController {

	@Autowired
	private SellerInternalInfoService msgService;

	/**
	 * 商家service
	 */
	@Autowired
	private SellerService sellerService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/sellermsg/sellerMsgList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerInternalInfo sellerInternalInfo) {
		Pageable<TSellerInternalInfo> pageable = new Pageable<TSellerInternalInfo>(sellerInternalInfo);
		pageable.setContent(msgService.getList(sellerInternalInfo));
		pageable.setTotal(msgService.count(sellerInternalInfo));
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
	public void export(TSellerInternalInfo sellerInternalInfo, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		sellerInternalInfo.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", msgService.getList(sellerInternalInfo));
		doExport(request, response, "businessman/sellerInternalInfo.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="站内消息推送删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = msgService.delete(id.split(","));
			if (resultNum >SellerConstants.COMMON_PARAM_Z) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}else{
				resultable = new Resultable(true, "操作失败");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s={"商家站内消息编号",id,"删除","删除"};
			msgService.fireLoginEvent(s, resultable.getSuccess()?1:0);
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
		ModelAndView modelAndView = new ModelAndView("businessman/sellermsg/addSellerMsg");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="站内消息推送添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSellerInternalInfo sellerInternalInfo,HttpServletRequest request) {
		boolean isSuccess = true;
		try {
			msgService.addOrUpdateMsg(sellerInternalInfo,request);
		} catch (Exception e) {
			isSuccess = false;
			msgService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("站内消息推送添加异常", e, new Object[]{sellerInternalInfo, request}).getMessage()), 0);
		}
		return retuenInfo(isSuccess, "ADD", sellerInternalInfo);
	}
	
	/**
	 * 返回成功或者失败的页面提示信息
	 * @param isSuccess
	 * @param flag
	 * @return
	 */
	private Resultable retuenInfo(boolean isSuccess, String flag, TSellerInternalInfo sellerInternalInfo){
		if(isSuccess){  //成功
			if("ADD".equals(flag)){  //添加成功
				this.log.info("添加成功");
			}else{ //修改成功
				this.log.info("修改成功");
			}
			recordLog(flag, sellerInternalInfo);  //成功的时候记录日志信息
			return new Resultable(true, "操作成功");
		}else{  //失败
			if("ADD".equals(flag)){  //添加失败
				this.log.info("添加失败");
			}else{ //修改失败
				this.log.info("修改失败");
			}
			return new Resultable(false, "操作失败");
		}
	}

	/**
	 * 记录日志信息
	 * @param isSuccess
	 * @param flag
	 * @param sellerInternalInfo
	 */
	private void recordLog(String flag, TSellerInternalInfo sellerInternalInfo) {
		String[] s = null;
		if("ADD".equals(flag)){  //记录添加成功的日志信息
			s = new String[]{"站内消息", sellerInternalInfo.getTitle(), "新增", "新增"};
		}else{  //记录修改成功的日志信息
			s = new String[]{"站内消息", sellerInternalInfo.getMsgId().toString(), "修改", "修改"};
		}
		msgService.fireLoginEvent(s, 1);
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("msgId") String id) {
		ModelAndView modelAndView = new ModelAndView("businessman/sellermsg/addSellerMsg");
		modelAndView.addObject("isType", "update");
		try {
			TSellerInternalInfo sellerInternalInfo = msgService.getObject(new Long(id));
			this.log.info(sellerInternalInfo);
			modelAndView.addObject("sellerMsg", sellerInternalInfo);
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
	@RequestLogging(name="站内消息推送修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(HttpServletRequest request,TSellerInternalInfo  sellerInternalInfo) {
		boolean isSuccess = true;
		try {
			msgService.addOrUpdateMsg(sellerInternalInfo,request);
		} catch (Exception e) {
			isSuccess = false;
			msgService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("站内消息推送修改异常", e, new Object[]{sellerInternalInfo, request}).getMessage()), 0);
		}
		return retuenInfo(isSuccess, "UPDATE", sellerInternalInfo);
	}
	
	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "init/sellerInit")
	public String sellerInit() {
		return "businessman/sellermsg/sellerChooseList";
	}
	
	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "/init/sellerList")
	@ResponseBody
	public Object list(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable.setContent(sellerService.getSellerMsg(seller));
		pageable.setTotal(sellerService.getSellerMsgCount(seller));
		return pageable;
	}
	
	
	/**
	 * 
	 * addSeller(添加商家)
	 * 
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/init/addSendSeller")
	public Object addSendSeller(TSellerInternalInfo sellerInternalInfo) {
		Resultable resultable = null;
		try {
			//msgService.addMsg(sellerInternalInfo,request);
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
	@SuppressWarnings("finally")
	@RequestMapping(value = "/view/init")
	public ModelAndView viewInit(HttpServletRequest request, @RequestParam("msgId") String id) {
		ModelAndView modelAndView = new ModelAndView("businessman/sellermsg/viewSellerMsg");
		modelAndView.addObject("isType", "view");
		try {
			TSellerInternalInfo param = new TSellerInternalInfo();
			param.setMsgId(new Integer(id));
			TSellerInternalInfo sellerInternalInfo = msgService.getList(param).get(0);
			this.log.info(sellerInternalInfo);
			modelAndView.addObject("sellerMsg", sellerInternalInfo);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
}