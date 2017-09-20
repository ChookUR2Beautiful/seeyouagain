package com.xmniao.xmn.core.marketingmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityService;
import com.xmniao.xmn.core.marketingmanagement.util.MarketConstants;
import com.xmniao.xmn.core.user_terminal.entity.TTopic;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
/**
* 
* @项目名称：XmnWeb
* 
* @类名称：TActivityController
* 
* @类描述： 营销活动管理
* 
* @创建人：yang'xu
* 
* @创建时间：2015年01月14日10时21分34秒
* 
* @Copyright©广东寻蜜鸟网络技术有限公司
*/
@RequestLogging(name="营销管理")
@Controller
@RequestMapping(value = "marketingManagement/activitymanagement")
public class TActivityController extends BaseController {
	
	@Autowired
	private TActivityService activityService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "marketingManagement/activityList";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TActivity activity){
		this.log.info("ActivityController-->list activity=" + activity);
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getList(activity));
		pageable.setTotal(activityService.count(activity));
		return pageable;
	}
	/*
	 * 删除活动
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除活动")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			Integer resultNum = activityService.delete(aid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"营销活动编号",aid,"删除","删除"};
				activityService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"营销活动编号",aid,"删除","删除"};
			activityService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
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
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivity");
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
	@RequestLogging(name="添加活动")
	@RequestMapping(value = "/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object add(TActivity activity) {
		Resultable resultable = null;
		try {
			boolean istrue = activityService.getType(activity);
			if (istrue) {
				activityService.add(activity);
				this.log.info("添加成功");
				resultable = new Resultable(true, "操作成功");
				//添加到日志
				String word = activity.getAname();
				String str = "";
				if (word.length() <= MarketConstants.WORD_LENGTH){
					str = word;
				}else{
					str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
				}
				String[] s={"营销活动",str,"新增"};
				activityService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
			}else{
				this.log.error("添加异常");
				resultable = new Resultable(false, "该类型的活动已存在，不能重复添加！");
				
				String word = activity.getAname();
				String str = "";
				if (word.length() <= MarketConstants.WORD_LENGTH){
					str = word;
				}else{
					str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
				}
				String[] s={"营销活动",str,"新增"};
				activityService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
			}
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			
			String word = activity.getAname();
			String str = "";
			if (word.length() <= MarketConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
			}
			String[] s={"营销活动",str,"新增"};
			activityService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
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
	@RequestMapping(value = "/update/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivity");
		modelAndView.addObject("isType", "update");
		try {
			TActivity activity = activityService.getObject(new Long(aid));
			this.log.info(activity);
			modelAndView.addObject("activity", activity);
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
	@RequestLogging(name="修改活动")
	@RequestMapping(value = "/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object update(TActivity  activity) {
		Resultable resultable = null;
		try {
			boolean istrue = activityService.getType(activity);
			boolean ischange = activityService.getAid(activity);
			if (istrue) {
				activityService.update(activity);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"营销活动编号",String.valueOf(activity.getAid()),"修改","修改"};
				activityService.fireLoginEvent(s,1);
			}else if(ischange){
				activityService.update(activity);
				this.log.info("修改成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"营销活动编号",String.valueOf(activity.getAid()),"修改","修改"};
				activityService.fireLoginEvent(s,1);
				
			}else{
				this.log.error("修改异常");
				resultable = new Resultable(false, "该类型的活动已存在，不能重复添加！");
				
				String word = activity.getAname();
				String str = "";
				if (word.length() <= 12){
					str = word;
				}else{
					str = word.substring(0, 12)+"...";
				}
				//
				String[] s={"营销活动",str,"修改"};
				activityService.fireLoginEvent(s,0);
			}
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"营销活动编号",String.valueOf(activity.getAid()),"修改","修改"};
			activityService.fireLoginEvent(s,0);
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
	@RequestMapping(value = "/check/init")
	public ModelAndView checkInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivity");
		modelAndView.addObject("isType", "check");
		try {
			TActivity activity = activityService.getObject(new Long(aid));
			this.log.info(activity);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivitys")
	public Object getActivitys(TActivity activity){
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getList(activity));
		return pageable;
	}
	
	/**
	 * 获取活动数据
	 * @param aid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivity")
	public Object getActivity(@RequestParam("aid") Long aid){
		return activityService.getObject(aid);
	}
	/**
	 * 获取活动数据
	 * @param aid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityType")
	public Object getActivityType(@RequestParam("type") Long type){
		return activityService.getObjectType(type);
	}
	
}
