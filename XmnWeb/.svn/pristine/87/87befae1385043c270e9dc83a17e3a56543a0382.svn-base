package com.xmniao.xmn.core.reward_dividends.controller;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.coupon.service.CouponIssueService;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.service.BLiveFansRankService;
import com.xmniao.xmn.core.reward_dividends.entity.TLivePrivilege;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationDetail;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationProject;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationReceive;
import com.xmniao.xmn.core.reward_dividends.service.RechargeRewardService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * V客充值奖励
 * @author Administrator
 *
 */
@RequestLogging(name="V客充值奖励管理")
@Controller
@RequestMapping(value = "rechargeReward/manage")
public class RechargeRewardController extends BaseController {
	
	@Autowired
	private RechargeRewardService rechargeRewardService;
	
	@Autowired
	private BLiveFansRankService liveFansRankService;
	
	@Autowired
	private CouponIssueService couponIssueService;
	
	
	@RequestMapping(value = "init")
	public String init() {
		return "reward_dividends/rechargeRewardManage";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLivePrivilege livePrivilege){
		Pageable<TLivePrivilege> pageable = new Pageable<TLivePrivilege>(livePrivilege);
		
		pageable = rechargeRewardService.getLivePrivilegeInfoList(livePrivilege);
		this.log.info("SpecialTopicController-->list pageable=" + pageable);
		
		return pageable;
	}
	
	

	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true, tokenName="verExcitationProjectToken")
	public ModelAndView addInit() {
		//@RequestParam(value = "liveType", required = false) String liveType
		ModelAndView modelAndView = new ModelAndView("reward_dividends/editRechargeReward");
		couponIssueService.goToAddInitPage(modelAndView);
		
		BLiveFansRank fansRank = new BLiveFansRank();
		fansRank.setRankType(2);
		List<BLiveFansRank> liveFansRankList = liveFansRankService.getListInfo(fansRank);
		modelAndView.addObject("liveFansRankList", liveFansRankList);
		
		List<TVerExcitationDetail> verExcitationDetailList = rechargeRewardService.getVerExcitationDetailList();
		modelAndView.addObject("excitationDetail", verExcitationDetailList);
		
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TVerExcitationProject verExcitationProject){
		log.info("verExcitationProjectController-->add-------verExcitationProject="+verExcitationProject);
		try {
			Integer id = verExcitationProject.getId();
			rechargeRewardService.deleteRelationData(verExcitationProject.getProjectName());  //根据奖励方案 A、B删除相关信息
			if(id==null){
				log.info("[执行修改V客配置方案]id="+id);
				rechargeRewardService.saveActivity(verExcitationProject);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("id") String id){
		Resultable result = new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(id)){
				count=rechargeRewardService.deleteById(Integer.parseInt(id));
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "update/init")
	public String updateInit(TVerExcitationProject verExcitationProject, Model model) {
		try {
			if (verExcitationProject != null) {
				model.addAttribute("isType", "update");
				model.addAttribute("verExcitationProjectInfo", rechargeRewardService.getVerExcitationProjectData(verExcitationProject));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "live_anchor/verExcitationProject/ediTVerExcitationProject";
	}

	@RequestMapping(value = { "update" })
	@ResponseBody
	public Resultable update(TVerExcitationProject verExcitationProject) {
		Resultable result = new Resultable();
		try {
			int count = 0;
			if (verExcitationProject != null) {
				count = rechargeRewardService.saveUpdateActivity(verExcitationProject);
			}
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				throw new Exception("更新数据出错");
			}
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "list/viewDetail")
	@ResponseBody
	public Object getVerExcitationReceiveDetail(Integer uid) {
		Resultable resultable = null;
		try {
			List<TVerExcitationReceive> verExcitationReceiveList = rechargeRewardService.getVerExcitationReceiveDetailList(null, uid);
			resultable = new Resultable(true, "查询成功", verExcitationReceiveList);
			return resultable;
			
		} catch (Exception e) {
			log.error("查询个人信息失败", e);
			resultable = new Resultable(false, "查询个人信息失败");
			return resultable;
		}

	}

	
}
