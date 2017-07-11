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
import com.xmn.saas.controller.h5.activity.vo.RoulleteRequest;
import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Roullete;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.activity.RoulleteService;
import com.xmn.saas.service.common.CommonService;

/**
 * 大转盘活动
 * 
 * @author jianming
 *
 */
@Controller(value = "h5-activity-roullete-controller")
@RequestMapping("h5/activity/roullete")
public class RoulleteController extends AbstractActiviryController {
	private static final Logger logger = LoggerFactory.getLogger(RoulleteController.class);

	@Autowired
	private RoulleteService roulleteService;

	@Autowired
	private CommonService commonService;

	/**
	 * 
	 * 方法描述：查询转盘列表 创建人：jianming 创建时间：2016年9月26日 上午10:59:26
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("activity/introduce");
		try {
			if (pageIndex == 1) {
				List<Roullete> beingActivitys = roulleteService.list(this.getSellerId(), true, pageSize, pageIndex);
				List<Roullete> endActivitys = roulleteService.list(this.getSellerId(), false, pageSize, pageIndex);
				if (beingActivitys.size() > 0 || endActivitys.size() > 0) {
					modelAndView.addObject("beingActivitys", beingActivitys);
					modelAndView.addObject("endActivitys", endActivitys);
					modelAndView.addObject("requestURL", request.getRequestURL());
					modelAndView.setViewName("activity/list-activity");
				}
			} else {
				List<Roullete> endActivitys = roulleteService.list(this.getSellerId(), false, pageSize, pageIndex);
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
	 * 方法描述：增加转盘活动 创建人：jianming 创建时间：2016年9月26日 上午11:04:01
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(RoulleteRequest roulleteRequest) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Roullete roullete = roulleteRequest.convertRequestToBean();
			Integer count = roulleteService.CountBeingActivity(this.getSellerId());
			if (count == 0) {
				roullete.setSellerid(this.getSellerId());
				roullete.setAwardRelations(this.transAwardRelation(roulleteRequest.getSellerCouponDetails()));
				roulleteService.save(roullete);
				modelAndView.addObject("activity", roullete);
				ShareRequest reqParams = new ShareRequest();
				reqParams.setId(roullete.getId());// 活动id
				reqParams.setTitle(roullete.getName());// 标题
				reqParams.setType(10);// 现金抵用券类型
				// 请求获取分享的地址
				String url = commonService.getShareUrl(reqParams);
				modelAndView.addObject("shareUrl", url);
				modelAndView.setViewName("activity/viwe-activity");
				return modelAndView;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "添加失败");
	}

	/**
	 * 
	 * 方法描述：获取大转盘可选奖品 创建人：jianming 创建时间：2016年9月28日 上午11:02:00
	 * 
	 * @return
	 */
	@RequestMapping(value = "list_award_roullete")
	@ResponseBody
	public Object listAward(Roullete roullete, SellerCouponDetailRequset sellerCouponDetails,
			AwardRequest awardRequest) {
		ModelAndView modelAndView = new ModelAndView("activity/list-award-roullete");
		try {
			if (awardRequest != null && awardRequest.getAwardId() != null && awardRequest.getAwardType() != null) {
				try {
					Roullete roullete2 = roulleteService.giveTempRoullete(this.getSellerId()); // 从redis拿回活动信息
					if (roullete2 == null) {
						modelAndView.setViewName("redirect:/h5/activity/roullete/input");
						return modelAndView;
					}
					AwardRelation[] awardRelations = new AwardRelation[1];
					AwardRelation awardProportion = new AwardRelation();
					awardProportion.setAwardType(awardRequest.getAwardType());
					if (!(awardRequest.getAwardType() == 5)) {
						Integer count = roulleteService.selectAwardCount(awardRequest.getAwardId());
						awardProportion.setAmount(count);
					}
					awardProportion.setId(awardRequest.getAwardId());
					awardRelations[0] = awardProportion;
					roullete2.setAwardRelations(awardRelations);
					modelAndView.addObject("roullete", roullete2);
				} catch (Exception e) {
					logger.info("【在redis拿回大转盘活动失败】");
					logger.error(e.getMessage(), e);
					e.printStackTrace();
					modelAndView.setViewName("redirect:/h5/activity/roullete/input");
				}

			} else {
				if (sellerCouponDetails != null && sellerCouponDetails.getSellerCouponDetails() != null) {
					roullete.setAwardRelations(this.transAwardRelation(sellerCouponDetails.getSellerCouponDetails()));
				}
				modelAndView.addObject("roullete", roullete);
			}
			// 赠品券
			List<SellerCouponDetail> zhengpinCoupons = roulleteService.getZhengpinCoupons(this.getSellerId());
			modelAndView.addObject("zhengpinCoupons", zhengpinCoupons);
			// 抵用券
			List<SellerCouponDetail> diyongCoupons = roulleteService.getDiyongCoupons(this.getSellerId());
			modelAndView.addObject("diyongCoupons", diyongCoupons);
			// 红包
			List<Redpacket> redpackets = roulleteService.getRedpackets(this.getSellerId());
			modelAndView.addObject("redpackets", redpackets);
			return modelAndView;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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
		ModelAndView modelAndView = new ModelAndView("activity/detail-roullete");
		try {
			Roullete roullete = roulleteService.detail(id, this.getSellerId());
			if (roullete != null) {
				List<AwardRelation> awardRelations = roulleteService.getRoulleteAward(roullete.getId(),
						roullete.getActivityType());
				if (awardRelations != null) {
					Integer awaryCount = 0;
					AwardRelation[] awardRelations2 = new AwardRelation[awardRelations.size()];
					for (int i = 0; i < awardRelations.size(); i++) {
						awardRelations2[i] = awardRelations.get(i);
						awaryCount += awardRelations.get(i).getAmount();
					}
					roullete.setAwardRelations(awardRelations2);
					modelAndView.addObject("awaryCount", awaryCount);
				}
			}
			ShareRequest reqParams = new ShareRequest();
			reqParams.setId(roullete.getId());// 活动id
			reqParams.setTitle(roullete.getName());// 标题
			reqParams.setType(10);// 现金抵用券类型
			// 请求获取分享的地址
			String url = commonService.getShareUrl(reqParams);
			modelAndView.addObject("shareUrl", url);
			modelAndView.addObject("roullete", roullete);
			this.SetDefineDate(modelAndView);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}


	/**
	 * 
	 * 方法描述：跳转到创奖品页面 创建人：jianming 创建时间：2016年10月14日 上午11:27:50
	 * 
	 * @param freetry
	 */
	@RequestMapping(value = "input_award", method = RequestMethod.POST)
	public String inputAward(Roullete roullete, Integer awardType, SellerCouponDetailRequset sellerCouponDetailRequset,
			RedirectAttributes attr) {
		String returnUrl = null;
		try {
			// 先把创建到一半的活动保存到redis中,创建完成奖品之后再还原回来
			roullete.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			roulleteService.saveTempRoullete(roullete, this.getSellerId());
			System.out.println(this.getSellerId());
			String url = "/h5/activity/roullete/list_award_roullete";

			switch (awardType) {
			case 3:
				// 跳到抵用券
				returnUrl = "redirect:/h5/coupon/input";
				attr.addAttribute("couponType", 3);
				break;
			case 4:
				// 跳到赠品券
				returnUrl = "redirect:/h5/coupon/input";
				attr.addAttribute("couponType", 4);
				break;
			case 5:
				// 创建红包
				attr.addAttribute("redpacketType", 4);
				attr.addAttribute("sellerid", this.getSellerId());
				returnUrl = "redirect:/h5/redpacket/input";
				url = "/h5/activity/roullete/list_award_roullete?awardType=5";
				break;
			default:
				return null;
			}
			attr.addAttribute("url", url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnUrl;

	}

	@RequestMapping(value = "input")
	public Object inputActivity(Roullete roullete, SellerCouponDetailRequset sellerCouponDetailRequset) {
		ModelAndView andView = new ModelAndView("activity/input-roullete");
		if (sellerCouponDetailRequset != null && sellerCouponDetailRequset.getSellerCouponDetails() != null) {
			roullete.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			andView.addObject("awaryCount", roullete.getAwardRelations().length);
			Integer awarySum = 0;
			for (SellerCouponDetailRequset sellerCouponDetailRequset2 : sellerCouponDetailRequset
					.getSellerCouponDetails()) {
				awarySum += sellerCouponDetailRequset2.getSendNum();
			}
			andView.addObject("awarySum", awarySum);
		}
		this.SetDefineDate(andView);
		andView.addObject("roullete", roullete);
		this.SetDefineDate(andView);
		return andView;
	}

}
