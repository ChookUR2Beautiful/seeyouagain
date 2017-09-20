package com.xmniao.xmn.core.businessman.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.VipCardDao;
import com.xmniao.xmn.core.businessman.entity.Plan;
import com.xmniao.xmn.core.businessman.entity.ReqVipCardBean;
import com.xmniao.xmn.core.businessman.entity.TCardApply;
import com.xmniao.xmn.core.businessman.entity.TCardSeller;
import com.xmniao.xmn.core.businessman.entity.TIssueCardApply;
import com.xmniao.xmn.core.businessman.entity.TRechargeRecord;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerNexus;
import com.xmniao.xmn.core.businessman.entity.VipCardBean;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VipCardService
 * 
 * 类描述： 商户会员卡管理Service
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月20日 上午10:04:54
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class VipCardService {

	private Logger log = LoggerFactory.getLogger(VipCardService.class);

	@Autowired
	private VipCardDao vipCardDao;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private TSequenceService tSequenceService;

	public List<VipCardBean> getVipCardList(VipCardBean vipCardBean) {
		return vipCardDao.getVipCardList(vipCardBean);
	}

	public Long getVipCardCount(VipCardBean vipCardBean) {
		return vipCardDao.getVipCardCount(vipCardBean);
	}

	/*
	 * 新增商家会员卡管理
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void addVipCardInfo(ReqVipCardBean reqVipCardBean) {
		// 分解子店String
		String[] childShop = reqVipCardBean.getChildSeller().split(",");
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		List<String> chlidList = Arrays.asList(childShop);
		List<Map<String, Object>> childShopList = sellerDao
				.getSellerName(chlidList);

		// 1.新增商户配置表
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("sellerId", reqVipCardBean.getSellerId());
		insertMap.put("sellerName", reqVipCardBean.getSellerName());
		insertMap.put("sellerLogo", reqVipCardBean.getSellerLogo());
		insertMap.put("cardName", reqVipCardBean.getCardName());
		insertMap.put("isPay", reqVipCardBean.getIsPay());
		insertMap.put("sellerNum", childShop.length);
		insertMap.put("rights", reqVipCardBean.getRights());
		insertMap.put("cardStatus", reqVipCardBean.getCardStatus());
		insertMap.put("rdate", nowDate);
		insertMap.put("udate", nowDate);
		insertMap.put("dstatus", 0);
		vipCardDao.addVipCardSellerConfig(insertMap);

		// 2.新增子店表
		String msellerId = "";
		for (int index = 0; index < childShop.length; index++) {
			msellerId = childShop[index];
			insertMap.put("msellerId", msellerId);
			for (Map<String, Object> map : childShopList) {
				if (map.get("sellerid").toString().equals(msellerId)) {
					insertMap.put("msellerName", map.get("sellername"));
					break;
				}
			}
			vipCardDao.addVipCardSellerNexus(insertMap);
		}

		// 3.新增会员卡管理表
		List<Plan> planList = reqVipCardBean.getPlanList();
		planList.get(reqVipCardBean.getDefaultPlan()).setIsDefault(1);
		Iterator<Plan> it = planList.iterator();
		while (it.hasNext()) {
			Plan plan = it.next();
			if (plan.getPrice() == null && plan.getRetail() == null) {
				System.out.println("删除的无效数据，删除");
				it.remove();
			}
		}
		System.out.println("defaultPlan:" + reqVipCardBean.getDefaultPlan());
		Collections.sort(planList);
		for (int index = 0; index < reqVipCardBean.getPlanList().size(); index++) {
			insertMap.put("batchId", tSequenceService.getAndUpdateSid(100004));// 约定！
			insertMap.put("price", planList.get(index).getPrice());
			insertMap.put("retail", planList.get(index).getRetail());
			insertMap.put("cash", planList.get(index).getPrice());
			insertMap.put("isDefault", planList.get(index).getIsDefault());
			vipCardDao.addVipCardIssueCard(insertMap);
		}
	}

	/*
	 * 更新会员卡管理
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void updateVipCardInfo(ReqVipCardBean reqVipCardBean) {
		// 分解子店String
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		List<Map<String, Object>> childShopList = sellerDao
				.getSellerName(reqVipCardBean.getChildSellerList());

		// 1.新增商户配置表
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("sellerId", reqVipCardBean.getSellerId());
		insertMap.put("sellerName", reqVipCardBean.getSellerName());
		insertMap.put("sellerLogo", reqVipCardBean.getSellerLogo());
		insertMap.put("cardName", reqVipCardBean.getCardName());
		insertMap.put("isPay", reqVipCardBean.getIsPay());
		insertMap.put("sellerNum", reqVipCardBean.getSellerNum());
		insertMap.put("rights", reqVipCardBean.getRights());
		insertMap.put("cardStatus", reqVipCardBean.getCardStatus());
		insertMap.put("udate", nowDate);
		vipCardDao.updateSellerConfig(insertMap);

		insertMap.put("rdate", nowDate);
		insertMap.put("dstatus", 0);
		// 2.新增子店表
		// 2.0 获取当前会员卡门店对应关系
		// 2-1 对比 1.相同则不处理;2.有修改全部更新
		String childStr = vipCardDao.getSupporSellerString(reqVipCardBean
				.getSellerId());
		String childStr2 = reqVipCardBean.getChildSeller().substring(0,
				reqVipCardBean.getChildSeller().length() - 1);
		if (childStr.equals(childStr2)) {
			System.out.println("适用门店关系没有修改过");
		} else {
			vipCardDao.removeSellerNexus(reqVipCardBean.getSellerId());

			// 2-2 再添加现在的子店对应关系
			for (String sid : reqVipCardBean.getChildSellerList()) {
				insertMap.put("msellerId", sid);
				for (Map<String, Object> map : childShopList) {
					if (map.get("sellerid").toString().equals(sid)) {
						insertMap.put("msellerName", map.get("sellername"));
						break;
					}
				}
				vipCardDao.addVipCardSellerNexus(insertMap);
			}
		}

		// 3.会员卡充值方案
		List<Plan> planList = vipCardDao.getVipCardIssue(reqVipCardBean
				.getSellerId());
		Collections.sort(planList);

		List<Plan> newPlanList = reqVipCardBean.getPlanList();
		newPlanList.get(reqVipCardBean.getDefaultPlan()).setIsDefault(1);
		Iterator<Plan> it = newPlanList.iterator();
		while (it.hasNext()) {
			Plan plan = it.next();
			if (plan.getPrice() == null && plan.getRetail() == null) {
				System.out.println("删除的无效数据，删除");
				it.remove();
			}
		}

		Collections.sort(newPlanList);

		if (planList.toString().equals(newPlanList.toString())) {

			System.out.println("会员卡充值方案没有修改过");
		} else {
			vipCardDao.removeIssueCard(reqVipCardBean.getSellerId());

			// 3.2将现在的方案添加到表中
			for (Plan plan : newPlanList) {
				insertMap.put("batchId",
						tSequenceService.getAndUpdateSid(100004));// 约定！
				insertMap.put("price", plan.getPrice());
				insertMap.put("retail", plan.getRetail());
				insertMap.put("cash", plan.getPrice());
				insertMap.put("isDefault", plan.getIsDefault());
				vipCardDao.addVipCardIssueCard(insertMap);
			}
		}
	}

	/*
	 * 更新会员卡管理
	 */
	@Transactional
	public void updateVipCardInfoOld(ReqVipCardBean reqVipCardBean) {
		// 分解子店String
		String[] childShop = reqVipCardBean.getChildSeller().split(",");
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		List<String> chlidList = Arrays.asList(childShop);
		List<Map<String, Object>> childShopList = sellerDao
				.getSellerName(chlidList);

		// 1.新增商户配置表
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("sellerId", reqVipCardBean.getSellerId());
		insertMap.put("sellerName", reqVipCardBean.getSellerName());
		insertMap.put("sellerLogo", null);
		insertMap.put("cardName", reqVipCardBean.getCardName());
		insertMap.put("isPay", reqVipCardBean.getIsPay());
		insertMap.put("sellerNum", childShop.length);
		insertMap.put("rights", reqVipCardBean.getRights());
		insertMap.put("cardStatus", reqVipCardBean.getCardStatus());
		insertMap.put("udate", nowDate);
		vipCardDao.updateSellerConfig(insertMap);

		// 2.新增子店表
		// 2-1 先删除所有子店对应关系
		vipCardDao.removeSellerNexus(reqVipCardBean.getSellerId());
		// 2-2 再添加现在的子店对应关系
		String msellerId = "";
		for (int index = 0; index < childShopList.size(); index++) {
			msellerId = childShop[index];
			insertMap.put("msellerId", msellerId);
			for (Map<String, Object> map : childShopList) {
				if (map.get("sellerid").toString().equals(msellerId)) {
					insertMap.put("msellerName", map.get("sellername"));
					break;
				}
			}
			vipCardDao.addVipCardSellerNexus(insertMap);
		}

		// 3.新增会员卡管理表
		// 3.1先删除所有对应方案
		vipCardDao.removeIssueCard(reqVipCardBean.getSellerId());
		// 3.2将现在的方案添加到表中
		for (int index = 0; index < reqVipCardBean.getPlanList().size(); index++) {

			insertMap.put("batchId", tSequenceService.getAndUpdateSid(100004));// 约定！
			insertMap.put("price", reqVipCardBean.getPlanList().get(index)
					.getPrice());
			insertMap.put("retail", reqVipCardBean.getPlanList().get(index)
					.getRetail());
			vipCardDao.addVipCardIssueCard(insertMap);
		}
	}

	public List<TSellerNexus> getSupporSellerList(TSellerNexus tSellerNexus) {
		return vipCardDao.getSupporSellerList(tSellerNexus);
	}

	public long getSupporSellerCount(TSellerNexus tSellerNexus) {
		return vipCardDao.getSupporSellerCount(tSellerNexus);
	}

	public ReqVipCardBean getVipCardBeanDetail(Integer sellerId) {
		return vipCardDao.getVipCardBeanDetail(sellerId);
	}

	public List<Plan> getVipCardIssue(Integer sellerId) {
		return vipCardDao.getVipCardIssue(sellerId);
	}

	/*
	 * 获取持卡信息列表
	 */
	public List<TCardSeller> getCardholderList(TCardSeller tCardSeller) {
		List<TCardSeller> cardSellerList = vipCardDao
				.getCardholderList(tCardSeller);
		if (cardSellerList.size() == 0) {

		} else {
			TCardSeller cardSeller = vipCardDao.getCountAmount(tCardSeller);
			cardSellerList.get(0).setTotalAmount(cardSeller.getTotalAmount());
			cardSellerList.get(0).setTotalConsumeAmount(
					cardSeller.getTotalConsumeAmount());
			cardSellerList.get(0).setTotalNoConsumeAmount(
					cardSeller.getTotalNoConsumeAmount());
		}
		return cardSellerList;
	}

	/*
	 * 获取持卡信息列表总数
	 */
	public Long getCardholderCount(TCardSeller tCardSeller) {
		return vipCardDao.getCardholderCount(tCardSeller);
	}

	/*
	 * 获取充值记录列表
	 */
	public List<TRechargeRecord> getPrepaidList(TRechargeRecord tRechargeRecord) {

		return vipCardDao.getPrepaidList(tRechargeRecord);
	}

	/*
	 * 获取充值记录列表总数
	 */
	public Long getPrepaidCount(TRechargeRecord tRechargeRecord) {

		return vipCardDao.getPrepaidCount(tRechargeRecord);
	}

	/*
	 * 修改会员卡状态
	 */
	public void updateCardStatus(TCardSeller tCardSeller) {
		vipCardDao.updateCardSellerStatus(tCardSeller);
	}

	/*
	 * 临时方法
	 */
	public List<Map<String, Object>> getmSellerList(Integer sellerId) {
		return vipCardDao.getMulShopList(sellerId);
	}

	/*
	 * 获取该商家是否已添加会员卡
	 */
	public boolean isSellerHasVipcard(Integer sellerId) {
		return vipCardDao.getSellerCardCount(sellerId) == 0 ? false : true;
	}

	/*
	 * 获取申请审核的VIP卡信息
	 */
	public List<TCardApply> getCardApplyList(TCardApply tCardApply) {
		return vipCardDao.getCardApplyList(tCardApply);
	}
	/**
	 * 
	 * @Title:getissueCardApplyList
	 * @Description:获取充值方案审核信息列表
	 * @return List<TIssueCardApply>
	 * @throw
	 */
	public List<TIssueCardApply> getissueCardApplyList(TIssueCardApply tissueCardApply){
		return vipCardDao.getissueCardApplyList(tissueCardApply);
	}

	/*
	 * 获取充值记录列表总数
	 */
	public Long getCardApplyListCount(TCardApply tCardApply) {
		return vipCardDao.getCardApplyListCount(tCardApply);
	}
	
	/*
	 * 获取充值方案审核信息总数
	 */
	public Long getissueCardApplyListCount(TIssueCardApply tissueCardApply) {
		return vipCardDao.getissueCardApplyListCount(tissueCardApply);
	}
	
	/**
	 * @Title:getVipCardAuditDetail
	 * @Description:查看会员卡审核申请记录详情
	 * @param id
	 * @return TCardApply
	 * @throw
	 */
	public TCardApply getVipCardAuditDetail(Integer id) {
		return vipCardDao.getVipCardAuditDetail(id);
	}
	
	/**
	 * @Title:getVipCardAuditDetail
	 * @Description:查看充值方案审核申请记录详情
	 * @param id
	 * @return TCardApply
	 * @throw
	 */
	public TIssueCardApply getIssueCardAuditDetail(Integer id) {
		return vipCardDao.getIssueCardAuditDetail(id);
	}

	/**
	 * @Title:updateBatch
	 * @Description:批量会员审核通过
	 * @param tActivityApply
	 *            void
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateBatch(TCardApply tCardApply) {
		Resultable resultable = null;
		try {
			String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());// 当前系统时间
			/*
			 * 批量查询要审核的信息
			 */
			List<TCardApply> carapplyList = vipCardDao
					.selectTCardApplyList(tCardApply.getIds().split(","));
			// 开通会员卡
			int sellerid = 0;
			String sellername = "";
			for (int i = 0; i < carapplyList.size(); i++) {
				if (carapplyList.get(i).getApplytype() == 1) {// 申请类型:1 申请开通预付卡
																// 2 申请修改会员卡说明
					// 1.查询商家是否已开通会员卡
					sellerid = carapplyList.get(i).getSellerid();
					sellername = carapplyList.get(i).getSellername();
					if (vipCardDao.checkExist(sellerid) == 0) {// 没开通
						// 2.查询是否是连锁店的子店
						if (vipCardDao.checkIsmultiple(sellerid) == 0) {// 不是连锁店的子店
							// 3.如果是商家总店，查询适用商家数量，如果是单店则适用商家数量为1
							int sellerNum = 0;
							// 5.得到子店selleid和sellername插入适用商家表t_seller_nexus
							List<TSellerNexus> sellernexuslst = null;
							TSellerNexus sellernexus = null;
							int subsellerid = 0;
							String subsellername = "";
							sellernexuslst = new ArrayList<TSellerNexus>();
							if (vipCardDao.checkIstotalbusiness(sellerid) == 0) { // 不是单店
								List<TSeller> subList = vipCardDao
										.getIsmultipleSub(sellerid);// 获取适用商家
								sellerNum = subList.size();

								for (int j = 0; j < subList.size(); j++) {
									subsellerid = subList.get(j).getSellerid();
									subsellername = subList.get(j)
											.getSellername();
									sellernexus = new TSellerNexus();
									sellernexus.setSellerId(sellerid);// 总店id
									sellernexus.setSellerName(sellername);// 总店名称
									sellernexus.setMsellerId(subsellerid);// 子店id
									sellernexus.setMsellerName(subsellername);// 子店名称
									sellernexus.setCardStatus(0);// 卡状态
									sellernexus.setDstatus(0);// 数据状态
									sellernexus.setRdate(DateUtil
											.smartFormat(nowDate));// 创建时间
								}
								sellernexuslst.add(sellernexus);
								vipCardDao.batchAddSellerNexus(sellernexuslst);
							} else {// 单店
								sellerNum = 1;

								sellernexus = new TSellerNexus();
								sellernexus.setSellerId(sellerid);// 总店id
								sellernexus.setSellerName(sellername);// 总店名称
								sellernexus.setMsellerId(sellerid);// 子店id
								sellernexus.setMsellerName(sellername);// 子店名称
								sellernexus.setCardStatus(1);// 卡状态
								sellernexus.setDstatus(0);// 数据状态
								sellernexus.setRdate(DateUtil
										.smartFormat(nowDate));// 创建时间
								sellernexuslst.add(sellernexus);
								vipCardDao.batchAddSellerNexus(sellernexuslst);
							}
							// 4.插入会员卡配置表t_card_config
							Map<String, Object> insertMap = new HashMap<String, Object>();
							insertMap.put("sellerId", sellerid);
							insertMap.put("sellerName", sellername);
							insertMap.put("sellerLogo", "");
							insertMap.put("cardName", "");
							insertMap.put("isPay", 0);
							insertMap.put("sellerNum", sellerNum);
							insertMap.put("rights", "");
							insertMap.put("cardStatus", 1);
							insertMap.put("rdate", nowDate);
							insertMap.put("udate", null);
							insertMap.put("dstatus", 0);
							vipCardDao.addVipCardSellerConfig(insertMap);
						}
						// 批量更新 t_card_apply的审核状态为通过
						for (int j = 0; j < carapplyList.size(); j++) {
							carapplyList.get(j).setApplystatus(1);
						}
						vipCardDao.batchUpdateCarApply(carapplyList);
					} else {
						// 提示商家已开通过会员卡
						this.log.info("商家" + sellerid + "已开通过会员卡");
						resultable = new Resultable(false, "商家编号为"+ sellerid + "的商家已开通过会员卡");
						return resultable;
					}
				} else if (carapplyList.get(i).getApplytype() == 2) {
					for (int j = 0; j < carapplyList.size(); j++) {
						carapplyList.get(j).setApplystatus(1);
						carapplyList.get(j).setUdate(nowDate);
					}
					// 批量更新使用说明
					vipCardDao.batchUpdateCarConfig(carapplyList);
					// 批量更新 t_card_apply的审核状态为通过
					vipCardDao.batchUpdateCarApply(carapplyList);
				}

			}
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	/**
	 * @Title:updateBatchNoPass
	 * @Description:批量审核会员卡不通过
	 * @param tCardApply
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateBatchNoPass(TCardApply tCardApply) {
		Resultable resultable = null;
		try {
			/*
			 * 批量查询要审核的信息
			 */
			List<TCardApply> carapplyList = vipCardDao.selectTCardApplyList(tCardApply.getIds().split(","));
			for (int i = 0; i < carapplyList.size(); i++) {
				carapplyList.get(i).setApplystatus(tCardApply.getApplystatus());;
				carapplyList.get(i).setReason(tCardApply.getReason());
			}
			//批量更新 t_card_apply的不通过原因和审核状态
			vipCardDao.batchUpdateNoPass(carapplyList);
			
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常",e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	
	
	/**
	 * @Title:updateIssuePass
	 * @Description:批量审核充值方案通过
	 * @param tissueCardApply
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateIssuePass(TIssueCardApply tissueCardApply) {
		Resultable resultable = null;
		try {
			String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			/*
			 * 批量查询要审核的信息
			 */
			List<TIssueCardApply> issuecarapplyList = vipCardDao.selectTIssueCardApplyList(tissueCardApply.getIds().split(","));
			for (int i = 0; i < issuecarapplyList.size(); i++) {
				if(vipCardDao.getisDefault(issuecarapplyList.get(i).getSellerid())==0){//判断是否有充值方案,0没有
					issuecarapplyList.get(0).setIsDefault(1);
				}
				issuecarapplyList.get(i).setCardstatus(1);//审核状态
				issuecarapplyList.get(i).setUdate(nowDate);//更新时间
			}
			//批量更新 t_issue_card的通过时间和审核状态
			vipCardDao.updateIssuePass(issuecarapplyList);
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常",e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	
	/**
	 * @Title:updateIssueNoPass
	 * @Description:批量审核充值方案不通过
	 * @param tissueCardApply
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateIssueNoPass(TIssueCardApply tissueCardApply) {
		Resultable resultable = null;
		try {
			String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			/*
			 * 批量查询要审核的信息
			 */
			List<TIssueCardApply> issuecarapplyList = vipCardDao.selectTIssueCardApplyList(tissueCardApply.getIds().split(","));
			for (int i = 0; i < issuecarapplyList.size(); i++) {
				issuecarapplyList.get(i).setCardstatus(3);//审核状态
				issuecarapplyList.get(i).setReason(tissueCardApply.getReason());//不通过原因
				issuecarapplyList.get(i).setUdate(nowDate);//更新时间
			}
			//批量更新 t_card_apply的不通过原因和审核状态以及时间
			vipCardDao.updateIssueNoPass(issuecarapplyList);
			
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常",e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
}
