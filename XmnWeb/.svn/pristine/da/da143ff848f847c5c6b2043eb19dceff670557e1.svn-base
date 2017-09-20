package com.xmniao.xmn.core.businessman.entity;

import java.util.Arrays;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ReqVipCardBean
 * 
 * 类描述： 会员卡管理在查看/新增/修改的Bean
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月26日 下午7:42:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ReqVipCardBean extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 会员卡ID
	 */
	private String id;
	
	/*
	 * 会员卡名称
	 */
	private String cardName;
	
	/*
	 * 商家编号
	 */
	private Integer sellerId;
	
	/*
	 * 商家名称
	 */
	private String sellerName;
	
	/*
	 * 会员卡LOGO
	 */
	private String sellerLogo;
	
	/*
	 * 是否开启支付功能
	 */
	private Integer isPay;
	
	/*
	 * 会员卡状态
	 */
	private Integer cardStatus;
	
	/*
	 * 支持会员卡的子店sellerid集合，格式如"10001,10002,10003..."
	 */
	private String childSeller;
	
	/*
	 * 会员特权
	 */
	private String rights;
	
	/*
	 * 支持会员卡的子店数量
	 */
	private Integer sellerNum;
	
	/*
	 * 默认充值方案
	 */
	private int defaultPlan;
	/*
	 * 充值方案列表
	 */
	private List<Plan> planList;
	
	/*
	 * 支持会员卡的子店sellerid List集合
	 */
	private List<String> childSellerList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getChildSeller() {
		return childSeller;
	}

	public void setChildSeller(String childSeller) {
		this.childSeller = childSeller;
		
		String[] childen = childSeller.split(",");
		sellerNum = childen.length;
		childSellerList = Arrays.asList(childen);
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	public String getSellerLogo() {
		return sellerLogo;
	}

	public void setSellerLogo(String sellerLogo) {
		this.sellerLogo = sellerLogo;
	}

	public Integer getSellerNum() {
		return sellerNum;
	}
	
	public List<String> getChildSellerList() {
		return childSellerList;
	}

	
	public int getDefaultPlan() {
		return defaultPlan;
	}

	public void setDefaultPlan(int defaultPlan) {
		this.defaultPlan = defaultPlan;
	}

	@Override
	public String toString() {
		return "ReqVipCardBean [id=" + id + ", cardName=" + cardName
				+ ", sellerId=" + sellerId + ", sellerName=" + sellerName
				+ ", sellerLogo=" + sellerLogo + ", isPay=" + isPay
				+ ", cardStatus=" + cardStatus + ", childSeller=" + childSeller
				+ ", rights=" + rights + ", sellerNum=" + sellerNum
				+ ", defaultPlan=" + defaultPlan + ", planList=" + planList
				+ ", childSellerList=" + childSellerList + "]";
	}



}
