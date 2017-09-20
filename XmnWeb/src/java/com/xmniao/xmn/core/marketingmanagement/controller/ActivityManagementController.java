package com.xmniao.xmn.core.marketingmanagement.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.businessman.service.SellerMarketingService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityPrize;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.entity.TGiveMoneyInfo;
import com.xmniao.xmn.core.marketingmanagement.entity.TIntegralRule;
import com.xmniao.xmn.core.marketingmanagement.service.ActivityPrizeService;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityManagermentService;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityService;
import com.xmniao.xmn.core.marketingmanagement.util.MarketConstants;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.StringUtils;
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
* @创建人：yingde'cao
* 
* @创建时间：2015年01月14日10时21分34秒
* 
* @Copyright©广东寻蜜鸟网络技术有限公司
*/
@RequestLogging(name="营销管理")
@Controller
@RequestMapping(value = "marketingManagement/activityManagement")
public class ActivityManagementController extends BaseController {
	
	@Autowired
	private TActivityService activityService;
	@Autowired
	private TActivityManagermentService tActivityManagermentService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private SellerMarketingService sellerMarketingService;
	
	@Autowired
	private TActivityManagermentService activityManagermentService;
	@Autowired
	private ActivityPrizeService activityPrizeService;
	

	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "marketingManagement/activityManagermentList";
	}
	
	
	@RequestMapping(value = "manzeng/init/list")
	@ResponseBody
	public Object list(TActivity activity){
		this.log.info("ActivityController-->list activity=" + activity);
		//activity.setType(2);
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getList(activity));
		pageable.setTotal(activityService.count(activity));
		return pageable;
	}
	/**
	 * @Title:jifenlist
	 * @Description:满赠积分活动
	 * @param activity
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "manzengjf/init/list")
	@ResponseBody
	public Object jifenlist(TActivity activity){
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getList(activity));
		pageable.setTotal(activityService.count(activity));
		return pageable;
	}
	/**
	 * @author dong'jt
	 * 创建时间：2015年10月10日 上午10:56:20
	 * 描述：优惠券活动
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "youhuiquan/init/list")
	@ResponseBody
	public Object youhuiquanlist(TActivity activity){
		this.log.info("ActivityController-->list activity=" + activity);
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getList(activity));
		pageable.setTotal(activityService.count(activity));
		return pageable;
	}
	
	/**
	 * 满赠活动   已中奖金额列表
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manzeng/activityPrize/list")
	public Object list(TActivityPrize activityPrize){
		this.log.info("ActivityPrizeController-->list activityPrize=" + activityPrize);
		Pageable<TActivityPrize> pageable = new Pageable<TActivityPrize>(activityPrize);
		pageable.setContent(activityPrizeService.getList(activityPrize));
		pageable.setTotal(activityPrizeService.count(activityPrize));
		return pageable;
	}
	@RequestMapping(value = "/manzeng/activityPrize/init")
	public ModelAndView activityPrizeInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/activityPrizeList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/manzeng/activityPrize/list");
		return modelAndView;
	}
	
	
	/**
	 * 刮刮乐   已中奖金额列表
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/scratchCard/activityPrize/list")
	public Object gglList(TActivityPrize activityPrize){
		this.log.info("ActivityPrizeController-->list activityPrize=" + activityPrize);
		Pageable<TActivityPrize> pageable = new Pageable<TActivityPrize>(activityPrize);
		pageable.setContent(activityPrizeService.getList(activityPrize));
		pageable.setTotal(activityPrizeService.count(activityPrize));
		return pageable;
	}
	@RequestMapping(value = "/scratchCard/activityPrize/init")
	public ModelAndView gglActivityPrizeInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/activityPrizeList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/scratchCard/activityPrize/list");
		return modelAndView;
	}
	
	/**
	 * 满赠活动    参与商家列表
	 * @param joint
	 * @return
	 */
	@RequestMapping(value = "/manzeng/initSellerRelateNum/init")
	public ModelAndView choseJointInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/SellerRelateNumList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/manzeng/initSellerRelateNum/list");
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/manzeng/initSellerRelateNum/list")
	public Object list(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = tActivityManagermentService.getSellerInfoList(seller);
		return pageable;
	}
	
	/**
	 * 优惠券活动    参与商家列表
	 * @param joint
	 * @return
	 */
	@RequestMapping(value = "/youhuiquan/initSellerRelateNum/init")
	public ModelAndView youhuiquanchoseJointInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/youHuiQuanSellerRelateNumList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/list");
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/youhuiquan/initSellerRelateNum/list")
	public Object youhuiquanlist(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = tActivityManagermentService.getSellerInfoList(seller);
		return pageable;
	}
	
	
	/**
	 * 刮刮乐 活动    参与商家列表
	 * @param joint
	 * @return
	 */
	@RequestMapping(value = "/scratchCard/initSellerRelateNum/init")
	public ModelAndView gglChoseJointInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/scratchCardSellerRelateNumList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/manzeng/initSellerRelateNum/list");
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/scratchCard/initSellerRelateNum/list")
	public Object gglList(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = tActivityManagermentService.getSellerInfoList(seller);
		return pageable;
	}
	
	
	/*
	 * 删除活动
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除活动")
	@RequestMapping(value = "/manzeng/delete")
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
	 * @Title:addInit
	 * @Description:添加 满赠活动初始化
	 * @param request
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "/manzeng/add/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView addInit(HttpServletRequest request) {
		String type = request.getParameter("type");//type为2则是满赠金额活动，type为7则是满赠积分活动
		if("2".equals(type)){//跳转到满赠金额编辑页面
			ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityManagermentList");
			modelAndView.addObject("isType", "add");
			modelAndView.addObject("type", type);//把type共享到添加页面
			return modelAndView;
		}else{//跳转到满赠积分编辑页面
			ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityManagermentjifenList");
			//查询商家类别选项并共享到页面
			List<TIntegralRule> listGrade = activityService.getIntegralRulsPrade();
			List<TActivity> activityList = new ArrayList<>();
			for (int i = 0; i < listGrade.size(); i++) {
				String grade = listGrade.get(i).getGrade();
				TActivity activity = new TActivity();
				activity.setGrade(grade);
				activityList.add(activity);
			}
			String listGradeStr = JSON.toJSONString(activityList);
			modelAndView.addObject("listGrade",listGradeStr);
			modelAndView.addObject("isType", "add");
			modelAndView.addObject("type", type);//把type共享到添加页面
			return modelAndView;
		}
		
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：yingde'cao
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="添加活动")
	@RequestMapping(value = "/manzeng/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object add(TActivity tActivity, HttpServletRequest request ) {
		Resultable resultable = null;
		try {
				resultable = tActivityManagermentService.addActivity(tActivity, request);
				//添加到日志
				String word = tActivity.getAname();
				String str = "";
				if (word.length() <= MarketConstants.WORD_LENGTH){
					str = word;
				}else{
					str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
				}
				String[] s={"营销活动",str,"新增"};
				tActivityManagermentService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			
			String word = tActivity.getAname();
			String str = "";
			if (word.length() <= MarketConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
			}
			String[] s={"营销活动",str,"新增"};
			tActivityManagermentService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
	/**
	 * @Title:addJifenActivity
	 * @Description:添加满赠积分活动
	 * @param tActivity
	 * @param request
	 * @return Object
	 * @throw
	 */
	@RequestLogging(name="添加满赠积分活动")
	@RequestMapping(value = "/manzengjifen/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object addJifenActivity(TActivity tActivity, HttpServletRequest request ) {
		Resultable resultable = null;
		try {
				resultable = tActivityManagermentService.addManZengJifenActivity(tActivity, request);
				//添加到日志
				String word = tActivity.getAname();
				String str = "";
				if (word.length() <= MarketConstants.WORD_LENGTH){
					str = word;
				}else{
					str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
				}
				String[] s={"营销活动",str,"新增"};
				tActivityManagermentService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			String word = tActivity.getAname();
			String str = "";
			if (word.length() <= MarketConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
			}
			String[] s={"营销活动",str,"新增"};
			tActivityManagermentService.fireLoginEvent(s,MarketConstants.FIRELOGIN_NUMB);
		}
			return resultable;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月9日 下午5:15:25
	 * 描述：优惠券活动添加初始化页面
	 * @return
	 */
	@RequestMapping(value = "youhuiquan/add/init")
	public ModelAndView youhuiquanAddInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editYouHuiQuan");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月9日 下午5:19:12
	 * 描述：优惠券活动添加
	 * @param tActivity
	 * @param request
	 * @return
	 */
	@RequestLogging(name="优惠券活动添加")
	@RequestMapping(value = "youhuiquan/add")
	@ResponseBody
	public Object youhuiquanAdd(TActivity tActivity) {
		Resultable resultable = null;
		try {
			resultable = tActivityManagermentService.addYouHuiQuanActivity(tActivity);	
		} catch (Exception e) {
			resultable = new Resultable(false, "操作失败");
			this.log.error("优惠券活动添加："+e);
			tActivityManagermentService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("优惠券活动添加", e, new Object[]{tActivity}).getMessage()),0);
		} finally {
			return resultable;
		}
		
	}
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：yingde'cao
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/manzeng/update/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		String type = request.getParameter("type");
		if("7".equals(type)){//满赠积分修改
			ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityManagermentjifenList");
			modelAndView.addObject("type", type);
			modelAndView.addObject("isType", "update");
			List<TActivity> activityList = new ArrayList<>();
			String listGradeStr = JSON.toJSONString(activityList);
			modelAndView.addObject("listGrade",listGradeStr);
			try {
				//获得活动表数据
				TActivity activity = activityService.getObject(new Long(aid));
				//获取积分活动规则数据
				List<TIntegralRule> tiActivityRule = tActivityManagermentService.getiActivityRule((long)activity.getAid());
				activity.settIntegralRule(tiActivityRule);
				int isRelationSellerNum  = 0;
				if (tiActivityRule.size() > 0) {
					isRelationSellerNum  = tiActivityRule.get(0).getIsRelationSellerNum();
				}
				this.log.info(activity);
				modelAndView.addObject("isRelationSellerNum", isRelationSellerNum);
				modelAndView.addObject("activity", activity);
			} catch (NumberFormatException e) {
				this.log.error("修改初始异常", e);
			} finally {
				return modelAndView;
			}
		}else{
			ModelAndView modelAndView = new ModelAndView("marketingManagement/editActivityManagermentList");
			modelAndView.addObject("type", type);
			modelAndView.addObject("isType", "update");
			try {
				TActivity activity = activityService.getObject(new Long(aid));
				List<TActivityRule> tActivityRule = (List<TActivityRule>) tActivityManagermentService.gettActivityRule((long)activity.getAid());
				activity.settActivityRule(tActivityRule);
				int isRelationSellerNum  = 0;
				if (tActivityRule.size() > 0) {
					isRelationSellerNum  = tActivityRule.get(0).getIsRelationSellerNum();
				}
				this.log.info(activity);
				modelAndView.addObject("isRelationSellerNum", isRelationSellerNum);
				modelAndView.addObject("activity", activity);
			} catch (NumberFormatException e) {
				this.log.error("修改初始异常", e);
			} finally {
				return modelAndView;
			}
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：yingde'cao
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改活动")
	@RequestMapping(value = "/manzeng/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object update(TActivity  tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			resultable  = tActivityManagermentService.updateActivity(tActivity, request);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"营销活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
			tActivityManagermentService.fireLoginEvent(s,0);
		} finally {
			return resultable;
		}
	}
	/**
	 * @Title:updateManzengjifen
	 * @Description:修改满赠积分活动
	 * @param tActivity
	 * @param request
	 * @return Object
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改满赠积分活动")
	@RequestMapping(value = "/manzengjifen/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object updateManzengjifen(TActivity  tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			resultable  = tActivityManagermentService.updateJifenActivity(tActivity, request);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"营销活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
			tActivityManagermentService.fireLoginEvent(s,0);
		} finally {
			return resultable;
		}
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月9日 下午5:22:43
	 * 描述：优惠券活动修改初始化页面
	 * @param request
	 * @param aid
	 * @return
	 */
	@RequestMapping(value = "youhuiquan/update/init")
	public ModelAndView youHuiQuanUpdateInit(TActivity  tActivity) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/editYouHuiQuan");
		modelAndView.addObject("isType", "update");
		Integer isRelationSellerNum=tActivity.getIsRelationSeller();
		try {
			TActivity activity = activityService.getObject(new Long(tActivity.getAid()));
			this.log.info(activity);
			modelAndView.addObject("isRelationSellerNum", isRelationSellerNum);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月9日 下午5:23:24
	 * 描述：优惠券活动修改
	 * @param tActivity
	 * @param request
	 * @return
	 */
	@RequestLogging(name="优惠券活动修改")
	@RequestMapping(value = "youhuiquan/update")
	@ResponseBody
	public Object youHuiQuanUpdate(TActivity  tActivity) {
		Resultable resultable = null;
		try {
			resultable  = tActivityManagermentService.updateYouHuiQuanActivity(tActivity);
		} catch (Exception e) {
			resultable = new Resultable(false, "操作失败");
			this.log.error("优惠券活动修改："+e);
			tActivityManagermentService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("优惠券活动修改", e, new Object[]{tActivity}).getMessage()),0);
		} 
		return resultable;
		
	}
	/**
	 *满赠活动详情 
	 * @author：yingde'cao
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/manzeng/check/init")
	public ModelAndView checkInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		String type = request.getParameter("type");
		if("7".equals(type)){//满赠积分规则查询
			ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivityManagerment");
			modelAndView.addObject("type", type);
			modelAndView.addObject("isType", "check");
			modelAndView.addObject("activityType", "manzengjf");
			try {
				TActivity activity = activityService.getActivityAndIntegralRuls(Long.parseLong(aid));
				this.log.info(activity);
				modelAndView.addObject("activity", activity);
			} catch (NumberFormatException e) {
				this.log.error("查看初始异常", e);
			} finally {
				return modelAndView;
			}
		}else{
			ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivityManagerment");
			modelAndView.addObject("type", type);
			modelAndView.addObject("isType", "check");
			modelAndView.addObject("activityType", "manzeng");
			try {
				TActivity activity = activityService.getActivityAndRuls(Long.parseLong(aid));
				this.log.info(activity);
				modelAndView.addObject("activity", activity);
			} catch (NumberFormatException e) {
				this.log.error("查看初始异常", e);
			} finally {
				return modelAndView;
			}
		}
		
	}
	
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月9日 下午5:25:16
	 * 描述：优惠券活动详情 
	 * @param request
	 * @param aid
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/youhuiquan/check/init")
	public ModelAndView youHuiQuanCheckInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivityManagerment");
		modelAndView.addObject("isType", "check");
		modelAndView.addObject("activityType", "youhuiquan");
		try {
			TActivity activity = activityService.getActivityAndRuls(Long.parseLong(aid));
			this.log.info(activity);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * scratchCard
	 *刮刮卡活动详情 
	 * @author：yingde'cao
	 */
	@RequestMapping(value = "/scratchCard/check/init")
	public ModelAndView scratchCardcheckInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/checkActivityManagerment");
		modelAndView.addObject("isType", "check");
		modelAndView.addObject("activityType", "guaguale");
		try {
			TActivity activity = activityService.getActivityAndRuls(Long.parseLong(aid));
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
	 * addBatchInit(批量添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/addBatch/init")
	public ModelAndView addBatchInit() {
		ModelAndView modelAndView = new ModelAndView(
				"marketingManagement/editActivitySellerBatch");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/**
	 * 
	 * addBatch(批量添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/addBatch")
	@ResponseBody
	public Object addBatch(TSellerMarketing sellerMarketing) {//sellerMarketing的aid为活动type
		Resultable resultable = null;
		// 教育基金活动只能B跟C等级并且是以上线的店铺参与
			// 其他等级的商户或是未上线应不能参与该活动；消费赠送活动只能ABC店参加，其他等级的商户应不能参与该活动
			String str = sellerMarketing.getSellerids();
			String[] ids = str.split(",");
			String isonline = null;
			TSeller seller = new TSeller();
			boolean onlineflag = true;
			boolean sellerGradeflag = true;
			Integer sellerGrade;
			List<TSeller> sellerList = null;
			List list = new ArrayList(10);
			List<TSellerMarketing> tSellerMarketings = null;
			TSellerMarketing tSellerMarketing = null;
			TActivity currentActivity = sellerMarketingService.getTActivity(sellerMarketing
					.getActivityType());// 
			for (String id : ids) {
				seller = new TSeller();
				if ("null".equals(id) || id == "") {// 如果提交为空，避免出现类型转换错误
					continue;
				}
				seller.setSellerid(Integer.parseInt(id));
				Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
				pageable = sellerService.getSellerInfoList(seller);
				sellerList = pageable.getContent();
				if (sellerList.size() > SellerConstants.COMMON_PARAM_Z) {// 判断是否有数据被查出
					seller = sellerList.get(0);
				}else{
					if (!list.contains("商家编号为："+id+" 存在数异常")) {
						list.add("商家编号为："+id+" 存在数异常");
					}
					sellerGradeflag = false;
					continue;
				}
				tSellerMarketing = new TSellerMarketing();
				tSellerMarketing.setSellerid(Integer.parseInt(id));
				tSellerMarketing.setAid(currentActivity.getAid());
				tSellerMarketings = sellerMarketingService
						.getList(tSellerMarketing);// 查询是不是有重复设置活动
				if (tSellerMarketings.size() > SellerConstants.COMMON_PARAM_Z) {// 不能重复设置活动
					if (!list.contains(seller.getSellername())) {
						list.add(seller.getSellername());
					}
					sellerGradeflag = false;
					continue;
				}
			}
			if (!sellerGradeflag) {// 返回信息拼装
				Iterator iterator = list.iterator();
				StringBuffer sb = new StringBuffer();
				int i = 1;
				while (iterator.hasNext()) {
					sb.append(iterator.next() + " ");
					i++;
				}
				resultable = new Resultable(true, "不符合参加活动规则！");
				resultable
						.setData("<b>不符合参加活动规则的商家:</b> </br>" + sb.toString());
				return resultable;
			}
			deleteMutex(ids,sellerMarketing);//教育和消费活动互斥处理
			sellerMarketing.setAid(currentActivity.getAid());//添加活动aid
		try {
			sellerMarketingService.addSellerMarkerings(sellerMarketing);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	
	public  void  deleteMutex(String[] ids,TSellerMarketing sellerMarketing){//批量增加，互斥删除
    	List<TSellerMarketing> tSellerMarketings = null;
		TSellerMarketing tSellerMarketing = null;
		TActivity currentActivity = sellerMarketingService.getTActivity(sellerMarketing
				.getActivityType());// //sellerMarketing的aid查询
		TActivity educationActivity = sellerMarketingService.getTActivity(SellerConstants.ACTIVITY_TYPE_J);// 教育活动
		TActivity subsidyActivity = sellerMarketingService.getTActivity(SellerConstants.ACTIVITY_TYPE_X);// 消费补贴活动
		for (String id : ids) {
			tSellerMarketing = new TSellerMarketing();
			tSellerMarketing.setSellerid(Integer.parseInt(id));
			tSellerMarketing.setAid(currentActivity.getAid());
			// 消费补贴，教育基金互斥
			if (currentActivity.getType() == educationActivity.getType()) {
				tSellerMarketing.setAid(subsidyActivity.getAid());
				tSellerMarketings = sellerMarketingService
						.getList(tSellerMarketing);
				if (tSellerMarketings.size() > SellerConstants.COMMON_PARAM_Z) {
					sellerMarketingService.delete(String.valueOf(
							tSellerMarketings.get(0).getId() + ",").split(
							","));
				}
				tSellerMarketing.setAid(educationActivity.getAid());
			}
			if (currentActivity.getType() == subsidyActivity.getType()) {
				tSellerMarketing.setAid(educationActivity.getAid());
				tSellerMarketings = sellerMarketingService
						.getList(tSellerMarketing);
				if (tSellerMarketings.size() > SellerConstants.COMMON_PARAM_Z) {
					sellerMarketingService.delete(String.valueOf(
							tSellerMarketings.get(0).getId() + ",").split(
							","));
				}
				tSellerMarketing.setAid(subsidyActivity.getAid());
			}	
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
	
	/**
	 * 选择商家商页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "addBatch/init/choseSeller/init")
	public ModelAndView choseSellerInit(ModelAndView mv) {
		mv.setViewName("businessman/choseSeller");
		mv.addObject("listUrl",
				"businessman/sellerMarketing/addBatch/init/choseSeller/list.jhtml");
		return mv;
	}

	/**
	 * 选择合作商列表
	 * 
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addBatch/init/choseSeller/list")
	public Object choseSellerlist(TSeller seller) {
		int[] onlines=SellerConstants.SELLER_ONLINES;
		seller.setIsonlines(onlines);// 上线合作商家
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable.setContent(sellerService.getList(seller));
		pageable.setTotal(sellerService.count(seller));
		return pageable;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月21日 下午5:36:44
	 * @TODO manzeng活动与商家管理初始化
	 * @return
	 */
	@RequestMapping(value="/manzeng/activityManagerSeller/init")
	public ModelAndView manzengActivityAndSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		String doType=activity.getDoType();
		modelAndView.setViewName("marketingManagement/activityManagerSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
			activity.setDoType(doType);
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月21日 下午5:36:44
	 * @TODO discount活动与商家管理初始化
	 * @return
	 */
	@RequestMapping(value="/discount/activityManagerSeller/init")
	public ModelAndView discountActivityAndSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		String doType=activity.getDoType();
		modelAndView.setViewName("marketingManagement/activityManagerSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
			activity.setDoType(doType);
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月27日 下午11:36:44
	 * @TODO scratchCard活动与商家管理初始化
	 * @return
	 */
	@RequestMapping(value="/scratchCard/activityManagerSeller/init")
	public ModelAndView scratchCardActivityAndSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		String doType=activity.getDoType();
		modelAndView.setViewName("marketingManagement/activityManagerSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
			activity.setDoType(doType);
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月10日 上午10:50:24
	 * 描述：  优惠券活动与商家管理初始化
	 * @param activity
	 * @return
	 */
	@RequestMapping(value="/youhuiquan/activityManagerSeller/init")
	public ModelAndView youHuiQuanActivityAndSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		String doType=activity.getDoType();
		modelAndView.setViewName("marketingManagement/activityManagerSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
			activity.setDoType(doType);
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}

	/**
	 * @author dong'jietao 
	 * @date 2015年5月22日 上午10:13:01
	 * @TODO 添加商家
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestLogging(name="添加商家")
	@RequestMapping(value="/activityManagerSeller")
	@ResponseBody
	public Object activityAndSeller(HttpServletRequest request,TSellerMarketing sellerMarketing){
		Resultable resultable=new Resultable();
		activityService.addSellerMarkerings(sellerMarketing, resultable);
		return resultable;
	}
	
	/**
	 * 刮刮乐活动添加商家
	 * @param activity
	 * @return
	 */
	@RequestMapping(value="/guagualeActivityAddSeller/init")
	public ModelAndView guagualeActivityAddSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("marketingManagement/activityGuaGuaLeAddSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}
	
	/**
	 * 折扣补贴活动列表 初始化页面
	 */
	@RequestMapping(value = "discount/init/list")
	@ResponseBody
	public Object discountList(TActivity activity){
		this.log.info("ActivityController-->list activity=" + activity);
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getDiscountList(activity));
		pageable.setTotal(activityService.getDiscountListCount(activity));
		return pageable;
	}
	
	/**
	 *  已补贴金额 初始化页面(折扣补贴活动列表 )
	 */
	@RequestMapping(value = "discount/giveMoney/init")
	public ModelAndView giveMoneyInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/discountGiveMoneyList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/discount/giveMoney/init/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "discount/giveMoney/init/list")
	@ResponseBody
	public Object discountGiveMoneyList(TGiveMoneyInfo giveMoneyInfo){
		Pageable<TGiveMoneyInfo> pageable = new Pageable<TGiveMoneyInfo>(giveMoneyInfo);
		pageable.setContent(activityService.getDiscountGiveMoneyList(giveMoneyInfo));
		pageable.setTotal(activityService.getDiscountGiveMoneyListCount(giveMoneyInfo));
		return pageable;
	}
	
	/**
	 * 获取A市消费赠送活动 - 参与商家列表
	 * @return
	 */
	@RequestMapping(value = "discount/discountParticipatingMerchants/init")
	public ModelAndView discountParticipatingMerchantsInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/discountParticipatingMerchants");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/discount/discountParticipatingMerchants/init/list");
		return modelAndView;
	}

	/**
	 * 获取A市消费赠送活动 - 参与商家列表
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "discount/discountParticipatingMerchants/init/list")
	public Object discountParticipatingMerchantsList(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = tActivityManagermentService.getSellerInfoList(seller);
		return pageable;
	}
	
	
	
	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：yingde'cao
	 */
	@RequestMapping(value = "discount/add/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView discountAddInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/discountActivityModel");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：yingde'cao
	 */
	@RequestLogging(name="添加折扣补贴活动")
	@RequestMapping(value = "discount/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object discountAdd(TActivity tActivity, HttpServletRequest request) {
		Double maxMoney=tActivity.getMaxMoeny();
		if(null==maxMoney || "".equals(maxMoney)){
			tActivity.setMaxMoeny(9999.99);
		}
		double max=tActivity.getMaxMoeny();
		double min=tActivity.getMinMoeny();
		if(max >= min || max == 0){
			boolean l = activityService.addDiscountActivity(tActivity, request);
			return new Resultable(l, l?"操作成功":"操作失败");
		}else{
			return new Resultable(false, "修改失败，请输入"+min+" -- 9999.99之间的数字！");
		}
	}
	
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：yingde'cao
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "discount/update/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView discountUpdateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/discountActivityModel");
		modelAndView.addObject("isType", "update");
		try {
			TActivity activity = activityService.getDiscountInfo(aid);
			activity.settActivityRule(null);
			modelAndView.addObject("activity", activity);
			int getIsRelationSeller  = activity.getIsRelationSeller();
			modelAndView.addObject("isRelationSellerNum", getIsRelationSeller);
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
	 * @author：yingde'cao
	 */
	@RequestLogging(name="修改活动")
	@RequestMapping(value = "discount/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object discountUpdate(TActivity  tActivity, HttpServletRequest request) {
		boolean l = activityService.updateDiscountActivity(tActivity, request);
		return new Resultable(l, l?"操作成功":"操作失败");
	}
	
	
	@RequestMapping(value = "discount/discountActivityDetails")
	public ModelAndView discountActivityDetails(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/discountActivityDetails");
		try {
			TActivity activity = activityService.getDiscountInfo(aid);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("查询折扣补贴明细异常", e);
		} 
		return modelAndView;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月23日 下午4:13:46
	 * @TODO 修改参加营销活动状态
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="满赠活动商家是否参加营销活动状态更新")
	@RequestMapping(value = "/manzeng/initSellerRelateNum/updateSellerMarketingIsattend")
	@ResponseBody
	public Object updateSellerMarketingIsattend(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		tActivityManagermentService.updateSellerMarketingIsattend(sellerMarketing,resultable);
		return resultable;	
	}
	
	/**
	 * @author dong'jietao 
	 * @date 2015年5月23日 下午4:13:46
	 * @TODO 优惠券活动商家是否参加营销活动状态更新
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="优惠券活动商家是否参加营销活动状态更新")
	@RequestMapping(value = "/youhuiquan/initSellerRelateNum/updateSellerMarketingIsattend")
	@ResponseBody
	public Object updateYouHuiQuanSellerMarketingIsattend(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		tActivityManagermentService.updateSellerMarketingIsattend(sellerMarketing,resultable);
		return resultable;	
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年6月18日 上午11:17:23
	 * @TODO 刮刮卡活动商家是否参加营销活动状态更新
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="刮刮卡活动商家是否参加营销活动状态更新")
	@RequestMapping(value = "/scratchCard/initSellerRelateNum/updateSellerMarketingIsattend")
	@ResponseBody
	public Object scratchCardUpdateSellerMarketingIsattend(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		tActivityManagermentService.updateSellerMarketingIsattend(sellerMarketing,resultable);
		return resultable;	
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月26日 下午4:13:46
	 * @TODO 修改参加营销活动状态
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="折扣补贴>>商家是否参加营销活动状态更新")
	@RequestMapping(value = "/discount/initSellerRelateNum/discountUpdateSellerMarketingIsattend")
	@ResponseBody             
	public Object discountUpdateSellerMarketingIsattend(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			tActivityManagermentService.updateSellerMarketingIsattend(sellerMarketing,resultable);
			tActivityManagermentService.updateAgio(sellerMarketing, resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	
	/**
	 * @author dong'jietao 
	 * @date 2015年6月18日 上午9:53:24
	 * @TODO discount商家退出活动
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="商家退出活动")
	@RequestMapping(value = "/discount/initSellerRelateNum/discountExitActivity")
	@ResponseBody             
	public Object discountExitActivity(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			sellerMarketing.setDoType("discount");
			tActivityManagermentService.sellerExitActivity(sellerMarketing,resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年6月18日 上午10:37:01
	 * @TODO manzeng 商家退出活动
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="商家退出活动")
	@RequestMapping(value = "/manzeng/initSellerRelateNum/manzengExitActivity")
	@ResponseBody             
	public Object manzengExitActivity(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			sellerMarketing.setDoType(null);
			tActivityManagermentService.sellerExitActivity(sellerMarketing,resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月10日 下午4:07:10
	 * 描述：优惠券活动商家退出活动
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="优惠券商家退出活动")
	@RequestMapping(value = "/youhuiquan/initSellerRelateNum/youHuiQuanExitActivity")
	@ResponseBody             
	public Object youhuiquanExitActivity(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			sellerMarketing.setDoType(null);
			tActivityManagermentService.sellerExitActivity(sellerMarketing,resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年6月18日 上午10:36:36
	 * @TODO scratchCard 商家退出活动
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="商家退出活动")
	@RequestMapping(value = "/scratchCard/initSellerRelateNum/scratchCardExitActivity")
	@ResponseBody             
	public Object scratchCardExitActivity(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			sellerMarketing.setDoType(null);
			tActivityManagermentService.sellerExitActivity(sellerMarketing,resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	
	/**
	 * 刮刮卡初始化
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/scratchCard/init/list")
	public Object scratchCardList(TActivity activity){
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		activity.setType(1);
		pageable.setContent(activityService.getList(activity));
		pageable.setTotal(activityService.count(activity));
		return pageable;
	}
	
	@RequestMapping(value = "/scratchCard/init")
	public ModelAndView scratchCardInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/scratchCard/init/list");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * addInit(刮刮卡添加初始化)
	 * 
	 * @author：yingde'cao
	 */
	@RequestMapping(value = "scratchCard/add/init")
	public ModelAndView scratchCardAddInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/scratchCardModel");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	/**
	 * 
	 * add(刮刮卡添加)
	 * 
	 * @author：yingde'cao
	 */
	@RequestLogging(name="添加活动")
	@RequestMapping(value = "scratchCard/add")
	@ResponseBody
	public Object scratchCardAdd( @RequestBody TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		try {
			resultable = tActivityManagermentService.addGuaguleActivity(tActivity, request);
		} catch (Exception e) {
			this.log.error("刮刮乐活动添加异常", e);
		}
		return resultable;
		
	}
	

	
	/**
	 * 
	 * updateInit 刮刮卡修改
	 * @author：yingde'cao
	 */
	@RequestMapping(value = "scratchCard/update/init")
	public ModelAndView scratchCardUpdateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/scratchCardModel");
		modelAndView.addObject("isType", "update");
		try {
			TActivity activity = activityService.getObject(new Long(aid));
			List<TActivityRule> tActivityRule = (List<TActivityRule>) tActivityManagermentService.gettActivityRule((long)activity.getAid());
			activity.settActivityRule(tActivityRule);
			int isRelationSellerNum  = 0;
			if (tActivityRule.size() > 0) {
				isRelationSellerNum  = tActivityRule.get(0).getIsRelationSellerNum();
			}
			modelAndView.addObject("isRelationSellerNum", isRelationSellerNum);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("修改刮刮乐活动初始异常", e);
		} 
		return modelAndView;
	}

	/**
	 * 
	 * 刮刮卡修改
	 * @author：yingde'cao
	 */
	@RequestLogging(name="修改活动")
	@RequestMapping(value = "scratchCard/update")
	@ResponseBody
	public Object scratchCardUpdate(@RequestBody TActivity  tActivity, HttpServletRequest request) {
		tActivity.settActivityRule(tActivity.getTactivityrule());
		return  tActivityManagermentService.updateActivity(tActivity, request);
	}
	
	
	/**
	 * 选择商家商页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/choseSeller/init")
	public ModelAndView choseSeller(ModelAndView mv,String cityids) {
		this.log.info(cityids);
		mv.addObject("cityids", cityids);
		mv.setViewName("businessman/choseSeller");
		mv.addObject("listUrl", "marketingManagement/activityManagement/init/choseSeller/list.jhtml");
		return mv;
	}
	/**
	 * 选择合作商列表
	 * @param joint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "init/choseSeller/list")
	public Object choseSellerList(TSeller seller) {
		int[] onlines=SellerConstants.SELLER_ONLINES;
		seller.setIsonlines(onlines);// 上线合作商家
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable.setContent(sellerService.getList(seller));
		pageable.setTotal(sellerService.count(seller));
		return pageable;
	}
	

	/**
	 * 佣金补贴活动列表 初始化页面
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "commission/init/list")
	@ResponseBody
	public Object commissionList(TActivity activity){
		this.log.info("ActivityController-->list activity=" + activity);
		Pageable<TActivity> pageable = new Pageable<TActivity>(activity);
		pageable.setContent(activityService.getCommissionList(activity));
		pageable.setTotal(activityService.getCommissionCount(activity));
		return pageable;
	}
	
	
	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "commission/add/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView commissionAddInit() {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/commissionActivityModel");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：wangzhimin
	 */
	@RequestLogging(name="添加佣金补贴活动")
	@RequestMapping(value = "commission/add")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object commissionAdd(TActivity tActivity, HttpServletRequest request) {		
		boolean l = activityService.addCommissionActivity(tActivity, request);
		return new Resultable(l, l?"操作成功":"操作失败");
	}
	
	
	/**
	 * 
	 * 佣金补贴活动详情
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "commission/commissionActionDetail")
	public ModelAndView commissionActivityDetails(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/commissionActivityDetails");
		try {
			TActivity activity = activityService.getCommissionInfo(aid);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("查询佣金补贴明细异常", e);
		} 
		return modelAndView;
	}
	
	
	/**
	 * 
	 * updateInit(佣金补贴活动修改初始化)
	 * 
	 *  @author：wangzhimin
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "commission/update/init")
	@RequestToken(createToken=true,tokenName="activityToken")
	public ModelAndView commissionUpdateInit(HttpServletRequest request, @RequestParam("aid") String aid) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/commissionActivityModel");
		modelAndView.addObject("isType", "update");
		try {
			TActivity activity = activityService.getCommissionInfo(aid);
			activity.settActivityRule(null);
			modelAndView.addObject("activity", activity);
			int getIsRelationSeller  = activity.getIsRelationSeller();
			modelAndView.addObject("isRelationSellerNum", getIsRelationSeller);
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
	 *  @author：wangzhimin
	 */
	@RequestLogging(name="修改佣金补贴活动")
	@RequestMapping(value = "commission/update")
	@ResponseBody
	@RequestToken(removeToken=true,tokenName="activityToken")
	public Object commissionUpdate(TActivity  tActivity, HttpServletRequest request) {
		boolean l = activityService.updateCommissionActivity(tActivity, request);
		return new Resultable(l, l?"操作成功":"操作失败");
	}
	
	
	/**
	 * 获取佣金补贴活动 - 参与商家列表
	 * @return
	 * 
	 * @author：wangzhimin
	 */
	@RequestMapping(value = "commission/commissionParticiptingMerchants/init")
	public ModelAndView commissionParticipatingMerchantsInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/commissionParticipatingMerchants");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/commission/commissionParticipatingMerchants/init/list");
		return modelAndView;
	}
	/**
	 * 获取佣金补贴活动 - 参与商家列表
	 * @param seller
	 * @return
	 * 
	 * @author：wangzhimin
	 */
	@ResponseBody
	@RequestMapping(value = "commission/commissionParticipatingMerchants/init/list")
	public Object commissionParticipatingMerchantsList(TSeller seller) {
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = tActivityManagermentService.getSellerInfoList(seller);
		return pageable;
	}
	
	
	/**
	 * @author：wangzhimin
	 * @date 2015年08月11日 下午2:50:44
	 * @TODO 佣金补贴活动与商家管理初始化
	 * @return
	 */
	@RequestMapping(value="/commission/activityManagerSeller/init")
	public ModelAndView commissionActivityAndSellerInit(TActivity activity){
		ModelAndView modelAndView = new ModelAndView();
		String doType=activity.getDoType();
		modelAndView.setViewName("marketingManagement/activityManagerSeller");
		if(activity.getAid()!=null){
			activity=activityService.getActivityAndRuls(activity.getAid().longValue());
			activity.setDoType(doType);
		}
		modelAndView.addObject("activity", activity);
		return modelAndView;
	}
	
	
	/**
	 * @author：wangzhimin 
	 * @date 2015年8月11日 下午3:22:46
	 * @TODO 修改参加营销活动状态
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="佣金补贴商家是否参加营销活动状态更新")
	@RequestMapping(value = "/commission/updateSellerMarketingIsAttend")
	@ResponseBody             
	public Object commissionUpdateSellerMarketingIsattend(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			tActivityManagermentService.updateSellerMarketingIsattend(sellerMarketing,resultable);
			tActivityManagermentService.updateCommissionAgio(sellerMarketing, resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	
	
	/**
	 * @author：wangzhimin
	 * @date 2015年8月11日 15:37:01
	 * @TODO manzeng 商家退出活动
	 * @param sellerMarketing
	 * @return
	 */
	@RequestLogging(name="商家退出活动")
	@RequestMapping(value = "/commission/commissionExitActivity")
	@ResponseBody             
	public Object commissionExitActivity(TSellerMarketing sellerMarketing){
		Resultable resultable = new Resultable();
		try {
			tActivityManagermentService.commissionSellerExitActivity(sellerMarketing,resultable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultable;	
	}
	
	
	/**
	 *  已补贴金额 初始化页面(佣金补贴活动列表 )
	 */
	@RequestMapping(value = "commission/activityPrize/init")
	public ModelAndView commissionActivityPrizeInit(@RequestParam("aid")Integer aid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/commissionActivityPrizeList");
		modelAndView.addObject("aid", aid);
		modelAndView.addObject("requestInit", "marketingManagement/activityManagement/commission/ActivityPrize/init/list");
		return modelAndView;
	}
	@RequestMapping(value = "commission/ActivityPrize/init/list")
	@ResponseBody
	public Object commissionactivityPrizeList(TGiveMoneyInfo giveMoneyInfo){
		Pageable<TGiveMoneyInfo> pageable = new Pageable<TGiveMoneyInfo>(giveMoneyInfo);
		pageable.setContent(activityService.getCommissionGiveMoneyList(giveMoneyInfo));
		pageable.setTotal(activityService.getCommissionGiveMoneyListCount(giveMoneyInfo));
		return pageable;
	}
	
}
