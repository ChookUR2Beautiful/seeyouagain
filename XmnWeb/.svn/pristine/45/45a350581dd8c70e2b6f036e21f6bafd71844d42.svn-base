package com.xmniao.xmn.core.reward_dividends.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.live_anchor.entity.BVerAnchorRelation;
import com.xmniao.xmn.core.reward_dividends.entity.TLivePrivilege;
import com.xmniao.xmn.core.reward_dividends.service.RecommendMemberService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * V客充值奖励
 * 
 * @author Administrator
 *
 */
@RequestLogging(name = "V客会员推荐管理")
@Controller
@RequestMapping(value = "recommendMember/manage")
public class RecommendMemberController extends BaseController {
	
	@Autowired
	private RecommendMemberService recommendMemberService;

	@Autowired
	private SellerService sellerService;
	
	@RequestMapping(value = "init")
	public String init() {
		return "reward_dividends/recommendMemberManage";
	}
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLivePrivilege livePrivilege){
		Pageable<TLivePrivilege> pageable = new Pageable<TLivePrivilege>(livePrivilege);
		
		pageable = recommendMemberService.getLivePrivilegeInfoList(livePrivilege);
		this.log.info("RecommendMemberController-->list pageable=" + pageable);
		
		return pageable;
	}
	
	
	@RequestMapping(value = "/init/countLivePrivilegeInfo")
	@ResponseBody
	public Object countLivePrivilegeInfo(TLivePrivilege livePrivilege) {
		Resultable resultable = null;
		try {
			Map<String, Object> resultMap = recommendMemberService.countLivePrivilegeInfo(livePrivilege);
			resultable = new Resultable(true, "查询成功", resultMap);
			return resultable;
			
		} catch (Exception e) {
			log.error("统计V客收益总数失败", e);
			resultable = new Resultable(false, "统计V客收益总数失败");
			return resultable;
		}
	}
	
	
	/**
	 * 方法描述：V客推荐的主播收益明细 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月4日上午11:49:59 <br/>
	 * @param livePrivilege
	 * @return
	 */
	@RequestMapping(value = "list/viewLiveDetail")
	@ResponseBody
	public Object getVerRecommendLiveDetail(TLivePrivilege livePrivilege) {
		Resultable resultable = null;
		try {
			List<BVerAnchorRelation> verAnchorRelationList = recommendMemberService.getVkeProfitLiveGiftDetailList(livePrivilege);
			resultable = new Resultable(true, "查询成功", verAnchorRelationList);
			return resultable;
			
		} catch (Exception e) {
			log.error("查询V客推荐的主播收益失败", e);
			resultable = new Resultable(false, "查询V客推荐的主播收益失败");
			return resultable;
		}

	}
	
	/**
	 * 方法描述：V客推荐的商家收益明细 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月4日上午11:49:38 <br/>
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "list/viewShopDetail")
	@ResponseBody
	public Object getVerRecommendShopDetail(TLivePrivilege livePrivilege) {
		Resultable resultable = null;
		try {
			List<Bill> billList = recommendMemberService.getVerRecommendSellerDetailList(livePrivilege);
			resultable = new Resultable(true, "查询成功", billList);
			return resultable;
			
		} catch (Exception e) {
			log.error("查询推荐的商家收益失败", e);
			resultable = new Resultable(false, "查询推荐的商家收益失败");
			return resultable;
		}
	}
	
	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午5:29:41 <br/>
	 * @param paramsId 商家编号
	 * @param uid 签约人编号
	 * @param type
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable abandon( @RequestParam("id") String paramsId, @RequestParam("uid") String uid,  @RequestParam("type") Integer type) {
		Resultable result = new Resultable();
		try {
			if (type == 0) {
				//放弃绑定推荐关系
			    recommendMemberService.abandonVerAnchorRelation(Integer.parseInt(paramsId));
			}else{
	            /* 调用寻蜜客放弃商家接口 */
				sellerService.abandonSeller(Integer.parseInt(paramsId), Integer.parseInt(uid));
			}
			result.setMsg("更新数据成功!");
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/*数据导出*/
	@RequestMapping(value="export")
	public void export(TLivePrivilege livePrivilege,HttpServletRequest request,HttpServletResponse response){
		livePrivilege.setOrder(PageConstant.NOT_ORDER);
		livePrivilege.setLimit(PageConstant.PAGE_LIMIT_NO);
		
		Map<String,Object> params=new HashMap<String,Object>();
		String path = "";
		params.put("list", recommendMemberService.getLivePrivilegeList(livePrivilege));
		path = "reward_dividends/vkeMember.xls";
		doExport(request,response, path,params);
		
	}
	
	
	/* ************************************* 添加V客关系 ****************************************** */
	
	@RequestMapping(value = "/init/leaveUseQuota")
	@ResponseBody
	public Object getVkeLeaveUseQuota(Integer vkeUid, Integer type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer leaveUseQuota = recommendMemberService.getVkeLeaveUseQuota(vkeUid, type);
		resultMap.put("leaveUseQuota", leaveUseQuota);
		return resultMap;
	}
	
	@RequestMapping(value = "/init/getVkeNameByLive")
	@ResponseBody
	public Object getVkeNameByLive(Integer liveUid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String vkeName = recommendMemberService.getVkeNameByLive(liveUid);
		resultMap.put("vkeName", vkeName);
		return resultMap;
	}
	
	@RequestMapping(value = "/init/getVkeNameBySeller")
	@ResponseBody
	public Object getVkeNameBySeller(Integer sellerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String vkeName = recommendMemberService.getVkeNameByLive(sellerId);
//		return verAnchorRelation;
		resultMap.put("vkeName", vkeName);
		return resultMap;
	}
	
	@RequestMapping(value = "/addRelationship")
	@ResponseBody
	public Resultable addRelationship(Integer type, Integer vkeUid, Integer relationId ) {
		Resultable resultable = new Resultable();
		try {
            if (type.equals(1)){
            	recommendMemberService.addLiveRelationship(vkeUid, relationId);
            }else if (type.equals(2)){
            	recommendMemberService.addSellerRelationship(vkeUid, relationId);
            }
			resultable = new Resultable(true, "绑定关系成功");
			return resultable;
			
		} catch (Exception e) {
			log.error("绑定关系失败", e);
			resultable = new Resultable(false, "绑定关系失败");
			return resultable;
		}
	}
}
