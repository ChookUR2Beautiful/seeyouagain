package com.xmniao.xmn.core.fresh.service;

import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.VipCardDao;
import com.xmniao.xmn.core.businessman.entity.Plan;
import com.xmniao.xmn.core.businessman.entity.ReqVipCardBean;
import com.xmniao.xmn.core.businessman.entity.TCardSeller;
import com.xmniao.xmn.core.businessman.entity.TRechargeRecord;
import com.xmniao.xmn.core.businessman.entity.TSellerNexus;
import com.xmniao.xmn.core.businessman.entity.VipCardBean;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.fresh.entity.TConsumeRecord;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshUserManageService
 * 
 * 类描述：生鲜会员(卡)管理Service
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年2月16日 上午10:12:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class FreshUserManageService {
	
	private Logger log = LoggerFactory.getLogger(FreshUserManageService.class);
	
	@Autowired
	private VipCardDao vipCardDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	 @Autowired
	 private TSequenceService tSequenceService;
	 
	public List<VipCardBean> getVipCardList(VipCardBean vipCardBean){
		return vipCardDao.getVipCardList(vipCardBean);
	}
	
	public Long getVipCardCount(VipCardBean vipCardBean){
		return vipCardDao.getVipCardCount(vipCardBean);
	}
	
	/*
	 * 新增商家会员卡管理
	 */
	@Transactional
	public void addVipCardInfo(ReqVipCardBean reqVipCardBean){
		//分解子店String
		String[] childShop = reqVipCardBean.getChildSeller().split(",");
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		List<String> chlidList = Arrays.asList(childShop);
		List<Map<String,Object>> childShopList = sellerDao.getSellerName(chlidList);
		
		//1.新增商户配置表
		Map<String,Object> insertMap = new HashMap<String,Object>();
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
		
		//2.新增子店表
		String msellerId = "";
		for(int index=0;index<childShop.length;index++){
			msellerId= childShop[index];
			insertMap.put("msellerId", msellerId);
			for(Map<String,Object> map:childShopList){
				if(map.get("sellerid").toString().equals(msellerId)){
					insertMap.put("msellerName", map.get("sellername"));
					break;
				}
			}
			vipCardDao.addVipCardSellerNexus(insertMap);
		}
		
		//3.新增会员卡管理表
		for(int index=0;index<reqVipCardBean.getPlanList().size();index++){
			insertMap.put("batchId",tSequenceService.getAndUpdateSid(100004));//约定！
			insertMap.put("price", reqVipCardBean.getPlanList().get(index).getPrice());
			insertMap.put("retail", reqVipCardBean.getPlanList().get(index).getRetail());
			vipCardDao.addVipCardIssueCard(insertMap);
		}
	}
	
	/*
	 * 更新会员卡管理
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void updateVipCardInfo(ReqVipCardBean reqVipCardBean){
		//分解子店String
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		List<Map<String,Object>> childShopList = sellerDao.getSellerName(reqVipCardBean.getChildSellerList());
		
		//1.新增商户配置表
		Map<String,Object> insertMap = new HashMap<String,Object>();
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
		//2.新增子店表
		//2.0 获取当前会员卡门店对应关系
		//2-1 对比 1.相同则不处理;2.有修改全部更新
		String childStr = vipCardDao.getSupporSellerString(reqVipCardBean.getSellerId());
		String childStr2 = reqVipCardBean.getChildSeller().substring(0,reqVipCardBean.getChildSeller().length()-1);
		if(childStr.equals(childStr2)){
			System.out.println("适用门店关系没有修改过");
		}else{
			vipCardDao.removeSellerNexus(reqVipCardBean.getSellerId());
			
			//2-2 再添加现在的子店对应关系
			for(String sid:reqVipCardBean.getChildSellerList()){
				insertMap.put("msellerId", sid);
				for(Map<String,Object> map:childShopList){
					if(map.get("sellerid").toString().equals(sid)){
						insertMap.put("msellerName",  map.get("sellername"));
						break;
					}
				}
				vipCardDao.addVipCardSellerNexus(insertMap);
			}
		}
		
		//3.会员卡充值方案
		List<Plan> planList = vipCardDao.getVipCardIssue(reqVipCardBean.getSellerId());
		Collections.sort(planList);
		
		List<Plan> newPlanList = reqVipCardBean.getPlanList();
		
		Iterator<Plan> it = newPlanList.iterator();
		while (it.hasNext()) 
		{
			Plan plan = it.next();
			if(plan.getPrice() == null && plan.getRetail()==null){
				System.out.println("删除的无效数据，删除");
				it.remove();
			}
		}
		
		Collections.sort(newPlanList);
		
		if(planList.toString().equals(newPlanList.toString())){
		
			System.out.println("会员卡充值方案没有修改过");
		}else{
			vipCardDao.removeIssueCard(reqVipCardBean.getSellerId());
			//3.2将现在的方案添加到表中
			for(Plan plan:newPlanList){
				insertMap.put("batchId",tSequenceService.getAndUpdateSid(100004));//约定！
				insertMap.put("price", plan.getPrice());
				insertMap.put("retail", plan.getRetail());
				vipCardDao.addVipCardIssueCard(insertMap);
			}
		}
	}
	
	/*
	 * 更新会员卡管理
	 */
	@Transactional
	public void updateVipCardInfoOld(ReqVipCardBean reqVipCardBean){
		//分解子店String
		String[] childShop = reqVipCardBean.getChildSeller().split(",");
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		List<String> chlidList = Arrays.asList(childShop);
		List<Map<String,Object>> childShopList = sellerDao.getSellerName(chlidList);
		
		//1.新增商户配置表
		Map<String,Object> insertMap = new HashMap<String,Object>();
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
		
		//2.新增子店表
		//2-1 先删除所有子店对应关系
		vipCardDao.removeSellerNexus(reqVipCardBean.getSellerId());
		//2-2 再添加现在的子店对应关系
		String msellerId = "";
		for(int index=0;index<childShopList.size();index++){
			msellerId= childShop[index];
			insertMap.put("msellerId", msellerId);
			for(Map<String,Object> map:childShopList){
				if(map.get("sellerid").toString().equals(msellerId)){
					insertMap.put("msellerName", map.get("sellername"));
					break;
				}
			}
			vipCardDao.addVipCardSellerNexus(insertMap);
		}
	
		//3.新增会员卡管理表
		//3.1先删除所有对应方案
		vipCardDao.removeIssueCard(reqVipCardBean.getSellerId());
		//3.2将现在的方案添加到表中
		for(int index=0;index<reqVipCardBean.getPlanList().size();index++){
			
			insertMap.put("batchId",tSequenceService.getAndUpdateSid(100004));//约定！
			insertMap.put("price", reqVipCardBean.getPlanList().get(index).getPrice());
			insertMap.put("retail", reqVipCardBean.getPlanList().get(index).getRetail());
			vipCardDao.addVipCardIssueCard(insertMap);
		}
	}
	
	public List<TSellerNexus> getSupporSellerList(TSellerNexus tSellerNexus){
		return vipCardDao.getSupporSellerList(tSellerNexus);
	} 
	
	public long getSupporSellerCount(TSellerNexus tSellerNexus){
		return vipCardDao.getSupporSellerCount(tSellerNexus);
	} 
	
	public ReqVipCardBean getVipCardBeanDetail(Integer sellerId){
		return vipCardDao.getVipCardBeanDetail(sellerId);
	}
	public List<Plan> getVipCardIssue(Integer sellerId){
		return vipCardDao.getVipCardIssue(sellerId);
	}
	
	/*
	 * 获取持卡信息列表
	 */
	public List<TCardSeller> getCardholderList(TCardSeller tCardSeller){
		List<TCardSeller> cardSellerList = vipCardDao.getCardholderList(tCardSeller);
		if(cardSellerList.size()==0){
			
		}else{
			TCardSeller cardSeller = vipCardDao.getCountAmount(tCardSeller);
			cardSellerList.get(0).setTotalAmount(cardSeller.getTotalAmount());
			cardSellerList.get(0).setTotalConsumeAmount(cardSeller.getTotalConsumeAmount());
			cardSellerList.get(0).setTotalNoConsumeAmount(cardSeller.getTotalNoConsumeAmount());
		}
		return cardSellerList;
	}
	
	/*
	 * 获取持卡信息列表总数
	 */
	public Long getCardholderCount(TCardSeller tCardSeller){
		return vipCardDao.getCardholderCount(tCardSeller);
	}
	
	/*
	 * 获取充值记录列表
	 */
	public List<TRechargeRecord> getPrepaidList(TRechargeRecord tRechargeRecord){
		
		return vipCardDao.getPrepaidList(tRechargeRecord);
	}
	
	/*
	 * 获取充值记录列表总数
	 */
	public Long getPrepaidCount(TRechargeRecord tRechargeRecord){
		
		return vipCardDao.getPrepaidCount(tRechargeRecord);
	}
	
	/*
	 * 获取消费记录列表
	 */
	public List<TConsumeRecord> getConsumeList(TConsumeRecord tConsumeRecord){
		
		return vipCardDao.getConsumeList(tConsumeRecord);
	}
	
	/*
	 * 获取消费记录列表总数
	 */
	public Long getConsumeCount(TConsumeRecord tConsumeRecord){
		
		return vipCardDao.getConsumeCount(tConsumeRecord);
	}
	
	public void updateCardStatus(TCardSeller tCardSeller){
		vipCardDao.updateCardSellerStatus(tCardSeller);
	}
	
	/*
	 * 临时方法
	 */
	public List<Map<String,Object>> getmSellerList(Integer sellerId){
		return vipCardDao.getMulShopList(sellerId);
	}
}
