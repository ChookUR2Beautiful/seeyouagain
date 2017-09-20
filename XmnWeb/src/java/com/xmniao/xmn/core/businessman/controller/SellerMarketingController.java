package com.xmniao.xmn.core.businessman.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.businessman.service.SellerMarketingService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerMarketingController
 * 
 * 类描述： 商家营销信息
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时42分58秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "商家管理")
@Controller
@RequestMapping(value = "businessman/sellerMarketing")
public class SellerMarketingController extends BaseController {

	@Autowired
	private TActivityService activityService;

	@Autowired
	private SellerMarketingService sellerMarketingService;
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
		return "businessman/sellerMarketingList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * @throws ParseException 
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TSellerMarketing sellerMarketing) throws ParseException {
		Pageable<TSellerMarketing> pageable = new Pageable<TSellerMarketing>(
				sellerMarketing);
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
		if(sellerMarketing.getSdateStart()!= null && sellerMarketing.getSdateEnd()!=null){
			//获取开始日期
			String startDataS = formatDate.format(sellerMarketing.getSdateStart());
			String startDataE = formatDate.format(sellerMarketing.getSdateEnd());
			sellerMarketing.setStartDataS(startDataS);
			sellerMarketing.setStartDataE(startDataE);
			
			//获取开始时间
			String startTimeS = formatTime.format(sellerMarketing.getSdateStart());
			String startTimeE = formatTime.format(sellerMarketing.getSdateEnd());
			sellerMarketing.setStartTimeS(startTimeS);
			sellerMarketing.setStartTimeE(startTimeE);
		}
		pageable.setContent(sellerMarketingService.getList(sellerMarketing));
		pageable.setTotal(sellerMarketingService.count(sellerMarketing));
		this.log.info("TSellerMarketingController-->list sellerMarketing=" + sellerMarketing);
		return pageable;
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name = "商家营销信息删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,
			@RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerMarketingService.delete(id.split(","));
			if (resultNum > SellerConstants.COMMON_PARAM_Z) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			String[] s = { "商家营销活动", String.valueOf(id), "营销信息删除", "删除" };
			sellerMarketingService.fireLoginEvent(s,
					resultable.getSuccess() ? 1 : 0);
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
	public ModelAndView addInit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(
				"businessman/editSellerMarketing");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name = "商家营销信息添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TSellerMarketing sellerMarketing) {
		Resultable resultable = null;
		TSellerMarketing tm = new TSellerMarketing();
		tm.setSellerid(sellerMarketing.getSellerid());
		List<TSellerMarketing> tSellerMarketings;
		TSellerMarketing tSellerMarketing = null;
		TActivity currentActivity = sellerMarketingService.getTActivity(sellerMarketing.getActivityType());// sellerMarketing的aid保存的为活动type
		TActivity educationActivity = sellerMarketingService.getTActivity(SellerConstants.ACTIVITY_TYPE_J);// 教育活动
		TActivity subsidyActivity = sellerMarketingService.getTActivity(SellerConstants.ACTIVITY_TYPE_X);// 消费补贴活动
		tSellerMarketing = new TSellerMarketing();
		tSellerMarketing.setSellerid(sellerMarketing.getSellerid());
		tm.setAid(currentActivity.getAid());
		tSellerMarketings = sellerMarketingService.getList(tm);
		if (tSellerMarketings.size() >SellerConstants.COMMON_PARAM_Z) {
			this.log.info("该商家已参加此活动，不能重复设置！");
			resultable = new Resultable(true, "该商家已参加此活动，不能重复设置！");
			resultable.setData("该商家已参加此活动，不能重复设置！");
			return resultable;
		}
		// 消费补贴，教育基金互斥
		if (currentActivity.getType() == educationActivity.getType()) {
			tSellerMarketing.setAid(subsidyActivity.getAid());
			tSellerMarketings = sellerMarketingService
					.getList(tSellerMarketing);
			if (tSellerMarketings.size() > SellerConstants.COMMON_PARAM_Z) {
				sellerMarketingService.delete(String.valueOf(
						tSellerMarketings.get(0).getId() + ",").split(","));
			}
			tSellerMarketing.setAid(educationActivity.getAid());
		}
		if (currentActivity.getType() == subsidyActivity.getType()) {
			tSellerMarketing.setAid(educationActivity.getAid());
			tSellerMarketings = sellerMarketingService
					.getList(tSellerMarketing);
			if (tSellerMarketings.size() > SellerConstants.COMMON_PARAM_Z) {
				sellerMarketingService.delete(String.valueOf(
						tSellerMarketings.get(0).getId() + ",").split(","));
			}
			tSellerMarketing.setAid(subsidyActivity.getAid());
		}
		sellerMarketing.setAid(currentActivity.getAid());
		try {
			sellerMarketingService.add(sellerMarketing);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			
			//添加到日志记录表
			String word = subsidyActivity.getAname();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			
			String[] s={"商家营销信息",str,"新增"};
			sellerMarketingService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表
			String word = subsidyActivity.getAname();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			
			String[] s={"商家营销信息",str,"新增"};
			sellerMarketingService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			String[] s = { "商家号",
					String.valueOf(sellerMarketing.getSellerid()), "营销信息添加",
					"添加" };
			sellerMarketingService.fireLoginEvent(s,
					resultable.getSuccess() ? 1 : 0);
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
	public ModelAndView updateInit(HttpServletRequest request,
			@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView(
				"businessman/editSellerMarketing");
		modelAndView.addObject("isType", "update");
		try {
			TSellerMarketing sellerMarketing = sellerMarketingService
					.getObject(new Long(id));
			this.log.info(sellerMarketing);
			modelAndView.addObject("sellerMarketing", sellerMarketing);
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
	@RequestLogging(name = "商家营销信息修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TSellerMarketing sellerMarketing) {
		Resultable resultable = null;
		try {
			sellerMarketingService.update(sellerMarketing);
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
	 * addBatchInit(批量添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/addBatch/init")
	public ModelAndView addBatchInit() {
		ModelAndView modelAndView = new ModelAndView(
				"businessman/editSellerMarketingBatch");
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
				if ("null".equals(String.valueOf(seller.getSellerGrade()))) {// 商家等级校验
					// resultable = new Resultable(true, "商家等级信息不全");
					if (!list.contains(seller.getSellername())) {
						list.add(seller.getSellername());
					}
					sellerGradeflag = false;
					continue;
				}
				sellerGrade = seller.getSellerGrade();
				if(seller.getIsonline()==SellerConstants.SELLER_PREPARE_ONLINE&&currentActivity.getType()==SellerConstants.ACTIVITY_TYPE_J){//预上线(2)商家不能参加教育基金活动(3)
					if (!list.contains(seller.getSellername())) {
						list.add(seller.getSellername());
					}
					sellerGradeflag = false;
					continue;
				}
				if (null!=currentActivity.getType() && (currentActivity.getType() == SellerConstants.ACTIVITY_TYPE_J || currentActivity.getType() == SellerConstants.ACTIVITY_TYPE_X)) {// 教育基金活动只能B跟C等级的店铺参与
					if (sellerGrade == SellerConstants.SELLER_GRADE_A) {
						if (!list.contains(seller.getSellername())) {
							list.add(seller.getSellername());
						}
						sellerGradeflag = false;
					}
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
}