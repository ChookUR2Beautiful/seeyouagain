package com.xmniao.xmn.core.business_area.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_area.entity.BusinessArea;
import com.xmniao.xmn.core.business_area.entity.TstaffRanking;
import com.xmniao.xmn.core.business_area.service.BusinessAreaService;
import com.xmniao.xmn.core.business_area.service.TstaffRankingService;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TstaffRankingController
 * 
 * 类描述：员工排行
 * 
 * 创建人： zhou' dekun
 * 
 * 创建时间：2014年12月5日 下午11:56:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * 
 */

@Controller
@RequestMapping(value = "business_area/staffRanking")
public class TstaffRankingController extends BaseController {
	@Autowired
	private TstaffRankingService tstaffRankingService;// 员工排行service

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_area/staffRankingList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TstaffRanking tstaffRanking) {
		Pageable<TstaffRanking> pageable = new Pageable<TstaffRanking>(
				tstaffRanking);
		pageable.setContent(tstaffRankingService.getList(tstaffRanking));
		pageable.setTotal(tstaffRankingService.count(tstaffRanking));
		return pageable;
	}
   /**
    * 签约数详细信跳转页面
    * @param staffid
    * @return
    */
	@RequestMapping(value = "init/signedInfo/init")
	public ModelAndView initAreaInBusiness(@RequestParam("staffid") Long staffid) {
		ModelAndView modelAndView = new ModelAndView("business_area/signedInfo");
		modelAndView.addObject("requestInit",
				"business_area/staffRanking/init/signedIn");
		modelAndView.addObject("staffid", staffid);
		return modelAndView;
	}
    /**
     * 签约数详细信息获取
     * @param tstaffRanking
     * @return
     */
	@RequestMapping(value = "init/signedIn")
	public ModelAndView areaInBusiness(TstaffRanking tstaffRanking) {
		ModelAndView modelAndView = new ModelAndView(
				"business_area/signedInformation");
		modelAndView.addObject("page", tstaffRanking.getPage());
		try {
			Long id = new Long(tstaffRanking.getStaffid());
			modelAndView.addObject("tstaffRanking",
					tstaffRankingService.getSignedInfo(tstaffRanking));
			modelAndView.addObject("total",
					tstaffRankingService.getSignedInfoCount(tstaffRanking));
		} catch (Exception e) {
			this.log.error("获取签约数列表异常", e);
		} finally {
			return modelAndView;
		}
	}
	/**
	    * 签到数详细信跳转页面
	    * @param staffid
	    * @return
	    */
		@RequestMapping(value = "init/initSign/init")
		public ModelAndView initSign(@RequestParam("staffid") Long staffid) {
			ModelAndView modelAndView = new ModelAndView("business_area/signed");
			modelAndView.addObject("requestInit",
					"business_area/staffRanking/init/initSignIn");
			modelAndView.addObject("staffid", staffid);
			return modelAndView;
		}
	    /**
	     * 签到数详细信息获取
	     * @param tstaffRanking
	     * @return
	     */
		@RequestMapping(value = "init/initSignIn")
		public ModelAndView initSignIn(TstaffRanking tstaffRanking) {
			ModelAndView modelAndView = new ModelAndView(
					"business_area/signedIn");
			modelAndView.addObject("page", tstaffRanking.getPage());
			try {
				Long id = new Long(tstaffRanking.getStaffid());
				modelAndView.addObject("tstaffRanking",
						tstaffRankingService.getInitSignIn(tstaffRanking));
				modelAndView.addObject("total",
						tstaffRankingService.getInitSignInCount(tstaffRanking));
			} catch (Exception e) {
				this.log.error("获取签到数列表异常", e);
			} finally {
				return modelAndView;
			}

		}
	
		/**
		    * 商家详细信跳转页面
		    * @param staffid
		    * @return
		    */
			@RequestMapping(value = "init/initBusinesses/init")
			public ModelAndView initBusinesses(@RequestParam("staffid") Long staffid) {
				ModelAndView modelAndView = new ModelAndView("business_area/businesses");
				modelAndView.addObject("requestInit",
						"business_area/staffRanking/init/initBusinessesInfo");
				modelAndView.addObject("staffid", staffid);
				return modelAndView;
			}
		    /**
		     * 商家详细信息获取
		     * @param tstaffRanking
		     * @return
		     */
			@RequestMapping(value = "init/initBusinessesInfo")
			public ModelAndView initBusinessesInfo(TstaffRanking tstaffRanking) {
				ModelAndView modelAndView = new ModelAndView(
						"business_area/businessesInfo");
				modelAndView.addObject("page", tstaffRanking.getPage());
				try {
					Long id = new Long(tstaffRanking.getStaffid());
					modelAndView.addObject("tstaffRanking",
							tstaffRankingService.getBusinesses(tstaffRanking));
					modelAndView.addObject("total",
							tstaffRankingService.getBusinessesCount(tstaffRanking));
				} catch (Exception e) {
					this.log.error("获取商家数列表异常", e);
				} finally {
					return modelAndView;
				}

			}
			/**
			    * 审核中商家信息跳转页面
			    * @param staffid
			    * @return
			    */
				@RequestMapping(value = "init/ExamineBusinesses/init")
				public ModelAndView ExamineBusinesses(@RequestParam("staffid") Long staffid) {
					ModelAndView modelAndView = new ModelAndView("business_area/businesses");
					modelAndView.addObject("requestInit",
							"business_area/staffRanking/init/ExamineBusinessesInfo");
					modelAndView.addObject("staffid", staffid);
					return modelAndView;
				}
			    /**
			     * 审核中商家详细信息获取
			     * @param tstaffRanking
			     * @return
			     */
				@RequestMapping(value = "init/ExamineBusinessesInfo")
				public ModelAndView ExamineBusinessesInfo(TstaffRanking tstaffRanking) {
					ModelAndView modelAndView = new ModelAndView(
							"business_area/businessesInfo");
					modelAndView.addObject("page", tstaffRanking.getPage());
					try {
						Long id = new Long(tstaffRanking.getStaffid());
						modelAndView.addObject("tstaffRanking",
								tstaffRankingService.getExamineBusinesses(tstaffRanking));
						modelAndView.addObject("total",
								tstaffRankingService.getExamineBusinessesCount(tstaffRanking));
					} catch (Exception e) {
						this.log.error("获取商家数列表异常", e);
					} finally {
						return modelAndView;
					}

				}
				/**
				    *未通过商家信息跳转页面
				    * @param staffid
				    * @return
				    */
					@RequestMapping(value = "init/NoPassBusinesses/init")
					public ModelAndView NoPassBusinesses(@RequestParam("staffid") Long staffid) {
						ModelAndView modelAndView = new ModelAndView("business_area/businesses");
						modelAndView.addObject("requestInit",
								"business_area/staffRanking/init/NoPassBusinessesInfo");
						modelAndView.addObject("staffid", staffid);
						return modelAndView;
					}
				    /**
				     * 未通过商家详细信息获取
				     * @param tstaffRanking
				     * @return
				     */
					@RequestMapping(value = "init/NoPassBusinessesInfo")
					public ModelAndView NoPassBusinessesInfo(TstaffRanking tstaffRanking) {
						ModelAndView modelAndView = new ModelAndView(
								"business_area/businessesInfo");
						modelAndView.addObject("page", tstaffRanking.getPage());
						try {
							Long id = new Long(tstaffRanking.getStaffid());
							modelAndView.addObject("tstaffRanking",
									tstaffRankingService.getNoPassBusinesses(tstaffRanking));
							modelAndView.addObject("total",
									tstaffRankingService.getNoPassBusinessesCount(tstaffRanking));
						} catch (Exception e) {
							this.log.error("获取商家数列表异常", e);
						} finally {
							return modelAndView;
						}

					}

}
