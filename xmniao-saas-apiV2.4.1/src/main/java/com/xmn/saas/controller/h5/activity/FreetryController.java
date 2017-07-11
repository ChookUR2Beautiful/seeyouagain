package com.xmn.saas.controller.h5.activity;

import java.util.List;

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

import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.controller.h5.activity.vo.AwardRequest;
import com.xmn.saas.controller.h5.activity.vo.FreetryRequest;
import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.service.activity.FreetryService;
import com.xmn.saas.service.common.CommonService;

/**
 * 免费尝新
 * 
 * @author jianming
 *
 */
@Controller(value = "h5-activity-freetry-controller")
@RequestMapping(value = "h5/activity/freetry")
public class FreetryController extends AbstractActiviryController {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(FreetryController.class);

	@Autowired
	private FreetryService freetryService;

	@Autowired
	private CommonService commonService;

	/**
	 * 
	 * 方法描述：查询免尝明细 创建人：jianming 创建时间：2016年9月26日 下午2:10:57
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Object detail(@RequestParam(value = "id", required = true) Integer id) {
		// 统计刺激消费
		ModelAndView modelAndView = new ModelAndView("activity/detail-freetry");
		try {
			Integer sellerId = this.getSellerId();
			Freetry freetry = freetryService.detail(id, sellerId);
			modelAndView.addObject("freetry", freetry);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：活动详情页明细 创建人：jianming 创建时间：2016年10月12日 上午9:51:50
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail_activity", method = RequestMethod.GET)
	@ResponseBody
	public Object detailActivity(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("activity/detail-freetry");
		try {
			Integer sellerId = this.getSellerId();
			Freetry freetry = freetryService.detail(id, sellerId);
			List<AwardRelation> awardRelations = freetryService.getFreetryAway(freetry.getId(),
					freetry.getActivityType());
			if (awardRelations != null) {
				Integer awaryCount = 0;
				AwardRelation[] awardRelations2 = new AwardRelation[awardRelations.size()];
				for (int i = 0; i < awardRelations.size(); i++) {
					awardRelations2[i] = awardRelations.get(i);
					awaryCount += awardRelations.get(i).getAmount();
				}
				freetry.setAwardRelations(awardRelations2);
				modelAndView.addObject("awaryCount", awaryCount);
			}
			ShareRequest reqParams = new ShareRequest();
			reqParams.setId(freetry.getId());// 活动id
			reqParams.setTitle(freetry.getName());// 标题
			reqParams.setType(11);// 现金抵用券类型
			// 请求获取分享的地址
			String url = commonService.getShareUrl(reqParams);
			modelAndView.addObject("shareUrl", url);
			modelAndView.addObject("freetry", freetry);
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
	 * 方法描述：查询进行中的免费尝新列表 创建人：jianming 创建时间：2016年9月26日 上午10:59:26
	 * 
	 * @return
	 */
	@RequestMapping(value = "list_being", method = RequestMethod.GET)
	@ResponseBody
	public Object listBeing() {
		List<Freetry> freertys;
		try {
			freertys = freetryService.listBeing(this.getSellerId());
			return new Response(ResponseCode.SUCCESS, "成功", freertys);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：查询已结束的免费尝新列表 创建人：jianming 创建时间：2016年9月26日 上午10:59:26
	 * 
	 * @return
	 */
	@RequestMapping(value = "list_end", method = RequestMethod.GET)
	@ResponseBody
	public Object listEnd(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
		List<Freetry> freertys;
		try {
			freertys = freetryService.listEnd(this.getSellerId(), pageSize, pageIndex);
			return new Response(ResponseCode.SUCCESS, "成功", freertys);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：增加免费尝新活动 创建人：jianming 创建时间：2016年9月26日 上午11:04:01
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(FreetryRequest freetryRequest) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			logger.info("【创建免尝活动】sellerId=" + this.getSellerId());
			Freetry freetry = freetryRequest.convertRequestToBean();
			Integer count = freetryService.CountBeingActivity(this.getSellerId());
			if (count == 0) {

				freetry.setSellerid(this.getSellerId());
				freetry.setAwardRelations(this.transAwardRelation(freetryRequest.getSellerCouponDetails()));
				freetryService.save(freetry);
				modelAndView.addObject("activity", freetry);
				ShareRequest reqParams = new ShareRequest();
				reqParams.setId(freetry.getId());// 活动id
				reqParams.setTitle(freetry.getName());// 标题
				reqParams.setType(11);// 现金抵用券类型
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
	 * 方法描述：获取免尝活动可选赠品券奖品 创建人：jianming 创建时间：2016年9月28日 上午11:02:00
	 * 
	 * @return
	 */
	@RequestMapping(value = "list_award")
	@ResponseBody
	public Object listAward(Freetry freetry, SellerCouponDetailRequset sellerCouponDetailRequset,
			AwardRequest awardRequest) {
		ModelAndView modelAndView = new ModelAndView("activity/list-award-freetry");
		try {
			if (awardRequest != null && awardRequest.getAwardId() != null && awardRequest.getAwardType() != null) {
				try {
					Integer count = freetryService.selectAwardCount(awardRequest.getAwardId());
					Freetry freetry2 = freetryService.giveTempFreetry(this.getSellerId()); // 从redis拿回活动信息
					if (freetry2 == null) {
						modelAndView.setViewName("redirect:/h5/activity/freetry/input");
						return modelAndView;
					}
					AwardRelation[] awardRelations = new AwardRelation[1];
					AwardRelation awardProportion = new AwardRelation();
					awardProportion.setAwardType(4);
					awardProportion.setAmount(count);
					awardProportion.setId(awardRequest.getAwardId());
					awardRelations[0] = awardProportion;
					freetry2.setAwardRelations(awardRelations);
					modelAndView.addObject("freetry", freetry2);
					this.SetDefineDate(modelAndView);
				} catch (Exception e) {
					e.printStackTrace();
					modelAndView.setViewName("redirect:/h5/activity/freetry/input");
				}

			} else {
				if (sellerCouponDetailRequset != null && sellerCouponDetailRequset.getSellerCouponDetails() != null) {
					freetry.setAwardRelations(
							this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
				}
				modelAndView.addObject("freetry", freetry);
			}
			List<SellerCouponDetail> sellerCouponDetails = freetryService.listAward(this.getSellerId());
			modelAndView.addObject("sellerCouponDetails", sellerCouponDetails);
			return modelAndView;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：跳转到创建赠品券页面 创建人：jianming 创建时间：2016年10月14日 上午11:27:50
	 * 
	 * @param freetry
	 */
	@RequestMapping(value = "input_award", method = RequestMethod.POST)
	public String inputAward(Freetry freetry, SellerCouponDetailRequset sellerCouponDetailRequset,
			RedirectAttributes attr) {
		try {
			// 先把创建到一半的活动保存到redis中,创建完成奖品之后再还原回来
			freetry.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			freetryService.saveTempFreetry(freetry, this.getSellerId());
			String url = "/h5/activity/freetry/list_award";
			attr.addAttribute("url", url);
			attr.addAttribute("couponType", 4);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "redirect:/h5/coupon/input";

	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("activity/introduce");
		try {
			if (pageIndex == 1) {
				List<Freetry> beingActivitys = freetryService.list(this.getSellerId(), true, pageSize, pageIndex);
				List<Freetry> endActivitys = freetryService.list(this.getSellerId(), false, pageSize, pageIndex);
				if (beingActivitys.size() > 0 || endActivitys.size() > 0) {
					modelAndView.addObject("beingActivitys", beingActivitys);
					modelAndView.addObject("endActivitys", endActivitys);
					modelAndView.addObject("requestURL", request.getRequestURL());
					modelAndView.setViewName("activity/list-activity");
				}
			} else {
				List<Freetry> endActivitys = freetryService.list(this.getSellerId(), false, pageSize, pageIndex);
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
	 * 方法描述：创建页面 创建人：jianming 创建时间：2016年10月26日 下午5:56:17
	 * 
	 * @param freetry
	 * @param sellerCouponDetailRequset
	 * @param step
	 * @return
	 */
	@RequestMapping(value = "input")
	public Object inputFreetry(Freetry freetry, SellerCouponDetailRequset sellerCouponDetailRequset) {
		ModelAndView andView = new ModelAndView("activity/input-freetry");
		if (sellerCouponDetailRequset.getSellerCouponDetails() != null) {
			freetry.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			andView.addObject("awaryCount", freetry.getAwardRelations().length);
			Integer awarySum = 0;
			for (SellerCouponDetailRequset sellerCouponDetailRequset2 : sellerCouponDetailRequset
					.getSellerCouponDetails()) {
				awarySum += sellerCouponDetailRequset2.getSendNum();
			}
			andView.addObject("awarySum", awarySum);
		}
		this.SetDefineDate(andView);
		andView.addObject("freetry", freetry);
		return andView;
	}
}
