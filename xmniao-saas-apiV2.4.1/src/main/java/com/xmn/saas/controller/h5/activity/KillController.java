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
import com.xmn.saas.controller.h5.activity.vo.KillRequest;
import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Kill;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.service.activity.KillService;
import com.xmn.saas.service.common.CommonService;

/**
 * 秒杀活动
 * 
 * @author jianming
 *
 */
@Controller(value = "h5-activity-kill-controller")
@RequestMapping("h5/activity/kill")
public class KillController extends AbstractActiviryController {

	private static final Logger logger = LoggerFactory.getLogger(KillController.class);

	@Autowired
	private KillService killService;

	@Autowired
	private CommonService commonService;

	/**
	 * 
	 * 方法描述：查询秒杀列表 创建人：jianming 创建时间：2016年9月26日 上午10:59:26
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
				List<Kill> beingActivitys = killService.list(this.getSellerId(), true, pageSize, pageIndex);
				List<Kill> endActivitys = killService.list(this.getSellerId(), false, pageSize, pageIndex);
				if (beingActivitys.size() > 0 || endActivitys.size() > 0) {
					modelAndView.addObject("beingActivitys", beingActivitys);
					modelAndView.addObject("endActivitys", endActivitys);
					modelAndView.addObject("requestURL", request.getRequestURL());
					modelAndView.setViewName("activity/list-activity");
				}
			} else {
				List<Kill> endActivitys = killService.list(this.getSellerId(), false, pageSize, pageIndex);
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
	 * 方法描述：增加秒杀活动 创建人：jianming 创建时间：2016年9月26日 上午11:04:01
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(KillRequest killRequest) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			logger.info("【创建秒杀活动】sellerId=" + this.getSellerId());
			Kill kill = killRequest.convertRequestToBean();
			Integer count = killService.CountBeingActivity(this.getSellerId());
			if (count == 0) {
				kill.setSellerid(this.getSellerId());
				kill.setAwardRelations(this.transAwardRelation(killRequest.getSellerCouponDetails()));
				killService.save(kill);
				modelAndView.setViewName("activity/viwe-activity");
				modelAndView.addObject("activity", kill);
				ShareRequest reqParams = new ShareRequest();
				reqParams.setId(kill.getId());// 活动id
				reqParams.setTitle(kill.getName());// 标题
				reqParams.setType(9);// 活动类型
				// 请求获取分享的地址
				String url = commonService.getShareUrl(reqParams);
				modelAndView.addObject("shareUrl", url);
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
	 * 方法描述：获取免尝活动可选赠品券奖品 创建人：jianming 创建时间：2016年9月28日 上午11:02:00
	 * 
	 * @return
	 */
	@RequestMapping(value = "list_award_kill")
	@ResponseBody
	public Object listAward(Kill kill, SellerCouponDetailRequset sellerCouponDetailRequets, AwardRequest awardRequest) {
		ModelAndView modelAndView = new ModelAndView("activity/list-award-kill");
		try {
			if (awardRequest != null && awardRequest.getAwardId() != null && awardRequest.getAwardType() != null) {
				try {
					Integer count = killService.selectAwardCount(awardRequest.getAwardId());
					Kill kill2 = killService.giveTempKill(this.getSellerId()); // 从redis拿回活动信息
					if (kill2 == null) {
						modelAndView.setViewName("redirect:/h5/activity/kill/input");
						return modelAndView;
					}
					AwardRelation[] awardRelations = new AwardRelation[1];
					AwardRelation awardProportion = new AwardRelation();
					awardProportion.setAwardType(4);
					awardProportion.setAmount(count);
					awardProportion.setId(awardRequest.getAwardId());
					awardRelations[0] = awardProportion;
					kill2.setAwardRelations(awardRelations);
					modelAndView.addObject("kill", kill2);
				} catch (Exception e) {
					e.printStackTrace();
					modelAndView.setViewName("redirect:/h5/activity/kill/input");
				}

			} else {
				if (sellerCouponDetailRequets != null && sellerCouponDetailRequets.getSellerCouponDetails() != null) {
					kill.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequets.getSellerCouponDetails()));
				}
				modelAndView.addObject("kill", kill);
			}
			List<SellerCouponDetail> sellerCouponDetails = killService.listAward(this.getSellerId());
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
	 * 方法描述：活动详情页明细 创建人：jianming 创建时间：2016年10月12日 上午9:51:50
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail_activity", method = RequestMethod.GET)
	@ResponseBody
	public Object detailActivity(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("activity/detail-kill");
		try {
			Kill kill = killService.detail(id, this.getSellerId());
			List<AwardRelation> awardRelations = killService.getKillAward(kill.getId(), kill.getActivityType());
			if (awardRelations != null) {
				Integer awaryCount = 0;
				AwardRelation[] awardRelations2 = new AwardRelation[awardRelations.size()];
				for (int i = 0; i < awardRelations.size(); i++) {
					awardRelations2[i] = awardRelations.get(i);
					awaryCount += awardRelations.get(i).getAmount();
				}
				kill.setAwardRelations(awardRelations2);
				modelAndView.addObject("awaryCount", awaryCount);
			}
			ShareRequest reqParams = new ShareRequest();
			reqParams.setId(kill.getId());// 活动id
			reqParams.setTitle(kill.getName());// 标题
			reqParams.setType(9);// 现金抵用券类型
			// 请求获取分享的地址
			String url = commonService.getShareUrl(reqParams);
			modelAndView.addObject("shareUrl", url);
			modelAndView.addObject("kill", kill);
			this.SetDefineDate(modelAndView);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 
	 * 方法描述：跳转到创建赠品券页面 创建人：jianming 创建时间：2016年10月14日 上午11:27:50
	 * 
	 * @param kill
	 */
	@RequestMapping(value = "input_award", method = RequestMethod.POST)
	public String inputAward(Kill kill, SellerCouponDetailRequset sellerCouponDetailRequset, RedirectAttributes attr) {
		try {
			logger.info("【跳转到创建赠品券页面】sellerId=" + this.getSellerId());
			// 先把创建到一半的活动保存到redis中,创建完成奖品之后再还原回来
			kill.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			killService.saveTempKill(kill, this.getSellerId());
			String url = "/h5/activity/kill/list_award_kill";
			attr.addAttribute("url", url);
			attr.addAttribute("couponType", 4);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "redirect:/h5/coupon/input";

	}

	@RequestMapping(value = "input")
	public Object inputActivity(Kill kill, SellerCouponDetailRequset sellerCouponDetailRequset) {
		ModelAndView andView = new ModelAndView("activity/input-kill");
		if (sellerCouponDetailRequset != null && sellerCouponDetailRequset.getSellerCouponDetails() != null) {
			kill.setAwardRelations(this.transAwardRelation(sellerCouponDetailRequset.getSellerCouponDetails()));
			andView.addObject("awaryCount", kill.getAwardRelations().length);
			Integer awarySum = 0;
			for (SellerCouponDetailRequset sellerCouponDetailRequset2 : sellerCouponDetailRequset
					.getSellerCouponDetails()) {
				awarySum += sellerCouponDetailRequset2.getSendNum();
			}
			andView.addObject("awarySum", awarySum);
		}
		this.SetDefineDate(andView);
		andView.addObject("kill", kill);
		this.SetDefineDate(andView);
		return andView;
	}

}
