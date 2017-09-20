package com.xmniao.xmn.core.businessman.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.businessman.entity.Plan;
import com.xmniao.xmn.core.businessman.entity.ReqVipCardBean;
import com.xmniao.xmn.core.businessman.entity.TCardApply;
import com.xmniao.xmn.core.businessman.entity.TCardSeller;
import com.xmniao.xmn.core.businessman.entity.TIssueCardApply;
import com.xmniao.xmn.core.businessman.entity.TRechargeRecord;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerNexus;
import com.xmniao.xmn.core.businessman.entity.VipCardBean;
import com.xmniao.xmn.core.fresh.entity.TConsumeRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VipCardDao
 * 
 * 类描述： 商户会员卡管理DAO层
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月20日 上午10:06:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface VipCardDao {

	/*
	 * 获取所有商户的会员卡信息
	 */
	@DataSource("slave")
	List<VipCardBean> getVipCardList(VipCardBean vipCardBean);
	
	/*
	 * 获取所有商户的会员卡数
	 */
	@DataSource("slave")
	Long getVipCardCount(VipCardBean vipCardBean);
	
	@DataSource("master")
	int addVipCardSellerConfig(Map<String,Object> paramMap);
	
	@DataSource("master")
	int addVipCardSellerNexus(Map<String,Object> paramMap);
	
	@DataSource("master")
	int addVipCardIssueCard(Map<String,Object> paramMap);
	
	@DataSource("slave")
	List<TSellerNexus> getSupporSellerList(TSellerNexus tSellerNexus);
	
	@DataSource("slave")
	Long getSupporSellerCount(TSellerNexus tSellerNexus);
	
	//获取商户的会员卡配置
	@DataSource("slave")
	ReqVipCardBean getVipCardBeanDetail(Integer sellerId);
	
	//获取对应商户的方案列表
	@DataSource("slave")
	List<Plan> getVipCardIssue(Integer sellerId);
	
	
	//更新会员卡配置
	@DataSource("master")
	int updateSellerConfig(Map<String,Object> paramMap);
	
	//删除商家对应的会员卡方案
	@DataSource("master")
	int removeIssueCard(Integer sellerId);
	
	//删除商家与子店的会员卡对应关系
	@DataSource("master")
	int removeSellerNexus(Integer sellerId);

	//获取持卡信息列表
	@DataSource("slave")
	List<TCardSeller> getCardholderList(TCardSeller tCardSeller);

	//获取持卡信息列表总数
	@DataSource("slave")
	Long getCardholderCount(TCardSeller tCardSeller);
	
	//统计所有会员卡的金额
	@DataSource("slave")
	TCardSeller getCountAmount(TCardSeller tCardSeller);
	
	//获取充值记录列表
	@DataSource("slave")
	List<TRechargeRecord> getPrepaidList(TRechargeRecord tRechargeRecord);
	
	//获取充值记录总数
	@DataSource("slave")
	Long getPrepaidCount(TRechargeRecord tRechargeRecord);
	
	//获取消费记录列表
	@DataSource("slave")
	List<TConsumeRecord> getConsumeList(TConsumeRecord tConsumeRecord);
	
	//获取消费记录总数
	@DataSource("slave")
	Long getConsumeCount(TConsumeRecord tConsumeRecord);
	
	//临时方法，获取适用门店的sellerId及sellerName
	@DataSource("slave")
	List<Map<String,Object>> getMulShopList(Integer sellerId);
	
	//获取商家与子店的会员卡对应关系 并拼成String类型
	@DataSource("slave")
	String getSupporSellerString(Integer sellerId);
	
	//修改用户的会员卡状态
	@DataSource("master")
	int updateCardSellerStatus(TCardSeller tCardSeller);
	
	//查询该商家是否已有会员卡配置
	@DataSource("slave")
	int getSellerCardCount(Integer sellerId);
	
	//获取申请审核的VIP卡信息
	@DataSource("slave")
	List<TCardApply> getCardApplyList(TCardApply tCardApply);
	
	//获取充值方案审核信息列表
	@DataSource("slave")
	List<TIssueCardApply> getissueCardApplyList(TIssueCardApply tissueCardApply);
	
	//获取申请审核的VIP卡信息记录总数
	@DataSource("slave")
	Long getCardApplyListCount(TCardApply tCardApply);
	
	//获取充值方案审核信息记录总数
	@DataSource("slave")
	Long getissueCardApplyListCount(TIssueCardApply tissueCardApply);
	
	//查看会员卡审核申请记录详情
	@DataSource("slave")
	TCardApply getVipCardAuditDetail(Integer id);
	
	//查看方案审核申请记录详情
	@DataSource("slave")
	TIssueCardApply getIssueCardAuditDetail(Integer id);
	
	//批量查询修改会员卡信息
    @DataSource("slave")
    List<TCardApply> selectTCardApplyList(Object[] objects);
    
  //批量查询修改充值方案信息
    @DataSource("slave")
    List<TIssueCardApply> selectTIssueCardApplyList(Object[] objects);
	
    //查询商家是否已开通会员卡
    @DataSource("slave")
    Integer checkExist(Integer sellerid);
    
    //查询是否是连锁店的子店
    @DataSource("slave")
    Integer checkIsmultiple(Integer sellerid);
    
  //查询是总店还是单店
    @DataSource("slave")
    Integer checkIstotalbusiness(Integer sellerid);
    
    //查询适用商家
    @DataSource("slave")
    List<TSeller> getIsmultipleSub(Integer sellerid);
    
    //批量插入适用商家表t_seller_nexus
    @DataSource("slave")
    Integer batchAddSellerNexus(List<TSellerNexus> sellerNexusList);
    
    //批量更新会员卡使用说明
    @DataSource("slave")
	void batchUpdateCarConfig(List<TCardApply> cardApplyList);
    
  //批量更新 t_card_apply的审核状态和时间
    @DataSource("slave")
	void batchUpdateCarApply(List<TCardApply> cardApplyList);
    //批量更新 t_card_apply的不通过原因和审核状态
    @DataSource("slave")
    void batchUpdateNoPass(List<TCardApply> tCardApplylist);
    
    //批量更新 t_issue_card的通过时间和审核状态
    @DataSource("slave")
    void updateIssuePass(List<TIssueCardApply> issuecarapplyList);
    
  //批量更新 t_issue_card的不通过原因和审核状态以及时间
    @DataSource("slave")
    void updateIssueNoPass(List<TIssueCardApply> issuecarapplyList);
   
  //查询会员卡是否有默认充值方案
  	@DataSource("slave")
  	int getisDefault(Integer sellerId);
}
