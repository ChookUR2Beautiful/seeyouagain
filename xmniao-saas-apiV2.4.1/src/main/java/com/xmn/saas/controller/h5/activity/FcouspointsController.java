package com.xmn.saas.controller.h5.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.constants.ActivityConsts;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.controller.h5.activity.vo.AwardRequest;
import com.xmn.saas.controller.h5.activity.vo.FcouspontsRequest;
import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Fcouspoints;
import com.xmn.saas.entity.activity.FcouspointsConver;
import com.xmn.saas.entity.activity.FcouspointsRecord;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.service.activity.FcouspointsService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.utils.JsonUtils;

@Controller(value = "h5-activity-fcousponts-controller")
@RequestMapping(value = "h5/activity/fcouspoints")
public class FcouspointsController extends AbstractActiviryController{
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(FcouspointsController.class);
	@Autowired
	private FcouspointsService fcouspointsService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private GlobalConfig globalConfig;
	
	
	/**
	 * 
	 * 方法描述：查询活动明细
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:38:25   
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Object detail(@RequestParam(value = "id", required = true) Integer id) {
		logger.info("[查询集点活动明细]id"+id);
		//加载活动时间,浏览人数,参与人数,转化率
		ModelAndView modelAndView = new ModelAndView("activity/detail-fcouspoints");
		try {
			Integer sellerId = this.getSellerId();
			Fcouspoints fcouspoints = fcouspointsService.detail(id, sellerId);
			modelAndView.addObject("fcouspoints", fcouspoints);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}
	
	
	/**
	 * 
	 * 方法描述：活动详情页明细 
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:40:54   
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "detail_activity", method = RequestMethod.GET)
	@ResponseBody
	public Object detailActivity(@RequestParam(value = "id", required = true) Integer id) {
		logger.info("[调用活动详情页明细接口]id="+id);
		ModelAndView modelAndView = new ModelAndView("activity/detail-fcouspoints");
		try {
			Integer sellerId = this.getSellerId();
			Fcouspoints fcouspoints = fcouspointsService.detail(id, sellerId);
			AwardRelation awardRelation = fcouspointsService.getFcouspointsAward(fcouspoints.getId(),
					fcouspoints.getActivityType());
				fcouspoints.setAwardRelation(awardRelation);
			ShareRequest reqParams = new ShareRequest();
			reqParams.setId(fcouspoints.getId());// 活动id
			reqParams.setTitle(fcouspoints.getName());// 标题
			reqParams.setType(12);// 现金抵用券类型
			// 请求获取分享的地址
			String url = commonService.getShareUrl(reqParams);
			modelAndView.addObject("shareUrl", url);
			modelAndView.addObject("fcouspoints", fcouspoints);
			this.SetDefineDate(modelAndView);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
		
	}
	
	
	/**
	 * 
	 * 方法描述：活动进行中的活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:42:02   
	 * @return
	 */
	@RequestMapping(value = "list_being", method = RequestMethod.GET)
	@ResponseBody
	public Object listBeing() {
		List<Fcouspoints> fcouspointses;
		try {
			logger.info("[调用获取进行中集点活动接口]selleid="+this.getSellerId());
			fcouspointses = fcouspointsService.listBeing(this.getSellerId());
			return new Response(ResponseCode.SUCCESS, "成功", fcouspointses);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}
	
	
	/**
	 * 
	 * 方法描述：查询已结束的列表
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:42:53   
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value = "list_end", method = RequestMethod.GET)
	@ResponseBody
	public Object listEnd(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
		List<Fcouspoints> fcouspoints;
		try {
			logger.info("[调用获取已结束集点活动接口]selleid="+this.getSellerId());
			fcouspoints = fcouspointsService.listEnd(this.getSellerId(), pageSize, pageIndex);
			return new Response(ResponseCode.SUCCESS, "成功", fcouspoints);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}
	
	
	/**
	 * 
	 * 方法描述：增加活动
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:44:11   
	 * @param freetryRequest
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(FcouspontsRequest fcouspontsRequest) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			logger.info("【创建集点活动】sellerId=" + this.getSellerId());
			Fcouspoints fcouspoints = fcouspontsRequest.convertRequestToBean();
			Integer count =fcouspointsService.CountBeingActivity(this.getSellerId());
			if (count == 0) {
				SellerCouponDetail couponDetail = couponService.selectByPrimaryKey(fcouspontsRequest.getAwardId());
				AwardRelation awardRelation=new AwardRelation();
				awardRelation.setAwardName(couponDetail.getCname());
				awardRelation.setAmount(couponService.selectAwardCount(fcouspontsRequest.getAwardId()));
				awardRelation.setAwardType(couponDetail.getCouponType());
				awardRelation.setActivityType(ActivityConsts.ACTIVITY_TYPE_FCOUSPONTS);
				awardRelation.setAwardId(couponDetail.getCid());
				awardRelation.setId(couponDetail.getCid());
				fcouspoints.setSellerid(this.getSellerId());
				fcouspoints.setAwardRelation(awardRelation);
				fcouspoints.setCountPoints(0);
				fcouspoints.setJoinNumber(0);
				fcouspointsService.save(fcouspoints);
				modelAndView.addObject("activity", fcouspoints);
				ShareRequest reqParams = new ShareRequest();
				reqParams.setId(fcouspoints.getId());// 活动id
				reqParams.setTitle(fcouspoints.getName());// 标题
				reqParams.setType(12);// 现金抵用券类型
				// 请求获取分享的地址
				String url = commonService.getShareUrl(reqParams);
				modelAndView.addObject("shareUrl", url);
				modelAndView.setViewName("activity/viwe-activity");
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "添加失败");
	}
	
	
	/**
	 * 
	 * 方法描述：获取活动可选赠品券奖品
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:45:39   
	 * @param freetry
	 * @param sellerCouponDetailRequset
	 * @param awardRequest
	 * @return
	 */
	@RequestMapping(value = "list_award")
	@ResponseBody
	public Object listAward(Fcouspoints fcouspoints,AwardRequest awardRequest) {
		logger.info("[获取集点活动可领取奖品接口]id="+fcouspoints.getId());
		ModelAndView modelAndView = new ModelAndView("activity/list-award-fcouspoints");
		try {
			if (awardRequest != null && awardRequest.getAwardId() != null && awardRequest.getAwardType() != null) {
				try {
					Fcouspoints fcouspoints2=recordService.giveTempActivity(this.getSellerId(), Fcouspoints.class);
					if(fcouspoints2!=null){
						fcouspoints=fcouspoints2;
					}
					recordService.saveTempActivity(fcouspoints, this.getSellerId());
					Integer count = couponService.selectAwardCount(awardRequest.getAwardId());
					AwardRelation awardRelation = new AwardRelation();
					awardRelation.setAwardType(awardRequest.getAwardType());
					awardRelation.setAmount(count);
					awardRelation.setAwardId(awardRequest.getAwardId());
					modelAndView.addObject("awardRelation", JsonUtils.toJSONString(awardRelation));
					this.SetDefineDate(modelAndView);
				} catch (Exception e) {
					e.printStackTrace();
					modelAndView.setViewName("redirect:/h5/activity/fcouspoints/input");
				}

			}else{
					recordService.saveTempActivity(fcouspoints, getSellerId());
			}
			modelAndView.addObject("endDate", fcouspoints.getEndDate());
			// 赠品券
			List<SellerCouponDetail> zhengpinCoupons = couponService.getZhengpinCoupons(this.getSellerId());
					modelAndView.addObject("zhengpinCoupons", zhengpinCoupons);
						// 抵用券
			List<SellerCouponDetail> diyongCoupons = couponService.getDiyongCoupons(this.getSellerId());
					modelAndView.addObject("diyongCoupons", diyongCoupons);
			return modelAndView;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}
	
	/**
	 * 
	 * 方法描述：跳转到创建奖品页面 创建人：jianming 创建时间：2016年10月14日 上午11:27:50
	 * 
	 * @param freetry
	 */
	@RequestMapping(value = "input_award", method = RequestMethod.POST)
	public String inputAward(Integer awardType,
			RedirectAttributes attr) {
		logger.info("[集点活动跳转到创建奖品页面]awardType="+awardType);
		try {
			// 先把创建到一半的活动保存到redis中,创建完成奖品之后再还原回来
			String url = "/h5/activity/fcouspoints/list_award";
			attr.addAttribute("url", url);
			attr.addAttribute("couponType", awardType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "redirect:/h5/coupon/input";
	}
	
	/**
	 * 
	 * 方法描述：分页加载活动列表
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:53:52   
	 * @param pageSize
	 * @param pageIndex
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("activity/introduce");
		try {
			logger.info("[集点活动分页加载活动列表]awardType="+this.getSellerId());
			if (pageIndex == 1) {
				List<Fcouspoints> beingActivitys = fcouspointsService.list(this.getSellerId(), true, pageSize, pageIndex);
				List<Fcouspoints> endActivitys = fcouspointsService.list(this.getSellerId(), false, pageSize, pageIndex);
				if (beingActivitys.size() > 0 || endActivitys.size() > 0) {
					modelAndView.addObject("beingActivitys", beingActivitys);
					modelAndView.addObject("endActivitys", endActivitys);
					modelAndView.addObject("requestURL", request.getRequestURL());
					modelAndView.setViewName("activity/list-activity");
				}
			} else {
				List<Fcouspoints> endActivitys = fcouspointsService.list(this.getSellerId(), false, pageSize, pageIndex);
				return new Response(ResponseCode.SUCCESS, "成功", endActivitys);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：创建页面
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午3:58:01   
	 * @param fcouspoints
	 * @param sellerCouponDetailRequset
	 * @return
	 */
	@RequestMapping(value = "input")
	public Object input(AwardRelation awardRelation) {
		ModelAndView andView = new ModelAndView("activity/input-fcouspoints");
		logger.info("调用跳转集点活动创建页面");
			try {
				Integer awarySum = 0;
				Fcouspoints fcouspoints = recordService.giveTempActivity(this.getSellerId(), Fcouspoints.class);
				if(awardRelation.getAwardId()!=null){
					fcouspoints.setAwardRelation(awardRelation);
					awarySum = couponService.selectAwardCount(awardRelation.getAwardId());
					andView.addObject("awaryCount", 1);
				}else{
					if(fcouspoints!=null){
						fcouspoints.setAwardRelation(null);
					}
					andView.addObject("awaryCount", 0);
				}
				andView.addObject("awarySum", awarySum);
				this.SetDefineDate(andView);
				andView.addObject("fcouspoints", fcouspoints);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return andView;
	}
	
	
	@RequestMapping(value = "list_fcouspoints_init")
	public Object listFcouspointsInit(@RequestParam(value="id",required=true)Integer id){
		ModelAndView modelAndView = new ModelAndView("activity/list-fcouspoints-record");
		try {
			Fcouspoints fcouspoints = fcouspointsService.detail(id, this.getSellerId());
			//统计活动剩余奖品数
			modelAndView.addObject("fcouspoints", fcouspoints);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "list_record_count",method=RequestMethod.POST)
	public Object listCountRecord(@RequestParam(value="id",required=true)Integer id){
		logger.info("[调用集点活动加载集点总数接口]id="+id);
		List<Map<String,Object>> recordMap=fcouspointsService.countRecordByDate(id);
		return new Response(ResponseCode.SUCCESS, "加载集点总数成功", recordMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "list_conver_count",method=RequestMethod.POST)
	public Object listCountConver(@RequestParam(value="id",required=true)Integer id){
		logger.info("[调用集点活动加载兑换总数接口]id="+id);
		List<Map<String,Object>> recordMap=fcouspointsService.countConverByDate(id);
		return new Response(ResponseCode.SUCCESS, "加载兑换总数成功", recordMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "list_record")
	public Object listFcouspointsRecord(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex){
		logger.info("[调用加载集点记录接口]id="+id);
		try {
			List<ActivityRecord> fcouspointsRecord = fcouspointsService.listRecord(id, this.getSellerId(), pageSize, pageIndex);
			return new Response(ResponseCode.SUCCESS, "加载集点记录成功", fcouspointsRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.FAILURE, "加载集点记录成功");
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "list_conver")
	public Object listFcouspointsConver(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex){
		logger.info("[调用加载兑换记录接口]id="+id);
		try {
			List<ActivityRecord> fcouspointsConver = fcouspointsService.listConver(id, this.getSellerId(), pageSize, pageIndex);
			return new Response(ResponseCode.SUCCESS, "加载兑换记录成功", fcouspointsConver);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.FAILURE, "加载兑换记录失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "detail_conver")
	public Object detailMember(@RequestParam(required=true) Integer id) throws IOException{
		logger.info("[调用加载兑换记录明细接口]id="+id);
		ModelAndView modelAndView = new ModelAndView("activity/detail-conver");
			FcouspointsConver conver =  fcouspointsService.detailConver(id,this.getSellerId());
			Map<String,Object> map=fcouspointsService.detailUser(conver.getUid(),this.getSellerId());
			map.put("head", conver.getHead());
			map.put("usrName", conver.getUsrName());
			map.put("phone", conver.getPhone());
			map.put("vipName", conver.getVipName());
			map.put("attachTime", conver.getAttachTime());
			modelAndView.addObject("conver",map);
			return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "detail_revenue")
	public Object detailRevenue(@RequestParam(required=true) Integer id) throws IOException{
		logger.info("[调用加载集点记录明细接口]id="+id);
		ModelAndView modelAndView = new ModelAndView("activity/detail-revenue");	
		FcouspointsRecord fcouspointsRecord=fcouspointsService.detailRecord(id,this.getSellerId());
		modelAndView.addObject("record",fcouspointsRecord);
		modelAndView.addObject("sessionToken",this.getCookieToken());
		modelAndView.addObject("imageHost",globalConfig.getImageHost());
		return modelAndView;
		
	}
	
	
}
