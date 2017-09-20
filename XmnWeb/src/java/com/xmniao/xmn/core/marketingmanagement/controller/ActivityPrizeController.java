package com.xmniao.xmn.core.marketingmanagement.controller;

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
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityPrize;
import com.xmniao.xmn.core.marketingmanagement.service.ActivityPrizeService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
/**
* 
* @项目名称：XmnWeb
* 
* @类名称：TActivityPrizeController
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
@RequestMapping(value = "marketingManagement/activityPrize")
public class ActivityPrizeController extends BaseController {
	
	@Autowired
	private ActivityPrizeService activityPrizeService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "marketingManagement/activityPrizeList";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TActivityPrize activityPrize){
		this.log.info("ActivityPrizeController-->list activityPrize=" + activityPrize);
		Pageable<TActivityPrize> pageable = new Pageable<TActivityPrize>(activityPrize);
		pageable.setContent(activityPrizeService.getList(activityPrize));
		pageable.setTotal(activityPrizeService.count(activityPrize));
		return pageable;
	}
	
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			Integer resultNum = activityPrizeService.delete(aid.split(","));
			if (resultNum > 0) {
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
	 * @author：yang'xu
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true,tokenName="activityPrizeToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityPrize");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：yang'xu
	 */
	
	@RequestMapping(value = "/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityPrizeToken")
	public Object add(TActivityPrize activityPrize) {
		Resultable resultable = null;
		try {
			activityPrizeService.add(activityPrize);
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
	 * @author：yang'xu
	 */
	@RequestMapping(value = "/update/init")
	@RequestToken(createToken=true,tokenName="activityPrizeToken")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityPrize");
		modelAndView.addObject("isType", "update");
		try {
			TActivityPrize activityPrize = activityPrizeService.getObject(new Long(aid));
			this.log.info(activityPrize);
			modelAndView.addObject("activityPrize", activityPrize);
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
	
	@RequestMapping(value = "/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityPrizeToken")
	public Object update(TActivityPrize  activityPrize) {
		Resultable resultable = null;
		try {
			activityPrizeService.update(activityPrize);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
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
		ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivityPrize");
		modelAndView.addObject("isType", "check");
		try {
			TActivityPrize activityPrize = activityPrizeService.getObject(new Long(aid));
			this.log.info(activityPrize);
			modelAndView.addObject("activityPrize", activityPrize);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * 获取活动列表
	 * @param activityPrize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityPrizes")
	public Object getActivityPrizes(TActivityPrize activityPrize){
		Pageable<TActivityPrize> pageable = new Pageable<TActivityPrize>(activityPrize);
		pageable.setContent(activityPrizeService.getList(activityPrize));
		return pageable;
	}
	
	/**
	 * 获取活动数据
	 * @param aid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityPrize")
	public Object getActivityPrize(@RequestParam("aid") Long aid){
		return activityPrizeService.getObject(aid);
	}
	
	@RequestMapping(value = "/statistics/init")
	public String activityCountInit(){
		return "marketingManagement/activityStatistics";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/statistics/list")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getStatistics(TActivityPrize activityPrize){
		Pageable pageable = new Pageable(activityPrize);
		pageable.setContent(activityPrizeService.getStatistics(activityPrize));
		pageable.setTotal(activityPrizeService.getStatisticsCount(activityPrize));
		return pageable;
	}
	
}
