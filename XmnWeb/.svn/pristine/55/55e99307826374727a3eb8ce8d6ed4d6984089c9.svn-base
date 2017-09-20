/**
 * 
 */
package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ValueCard
 * 
 * 类描述：商家储值卡
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月17日 上午10:00:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class ValueCard extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4504545574365854081L;

	private Integer sellerid;
	
	private String sellerName;
	
	private Integer sellerType;//商家类型 1.普通商家 2.连锁总店 3 区域代理
	
	private Integer childSeller;//适用商户
	
	private BigDecimal totalRecharge;//累计充值
	
	private BigDecimal totalSurplus;//累计剩余
	
	private Integer rechargeNum;//累计充值人数
	
	private String relationStore;//关联子店
	
	private Date updateTime;//开通时间
	
	private Integer status;//储值卡状态 0 开通 1 关闭
	
	private BigDecimal limitRecharge;//充值限额
	
	private String combo;
	
	private String comboId;
	
	private Integer referrerRatio;//直属推荐人充值分佣百分比
	
	private Integer parentReferrerRatio;//直属推荐人上级充值分佣百分比
	
	public ValueCard() {
		super();
		this.totalRecharge = BigDecimal.ZERO;
		this.totalSurplus = BigDecimal.ZERO;
		this.rechargeNum = 0;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getChildSeller() {
		return childSeller;
	}

	public void setChildSeller(Integer childSeller) {
		this.childSeller = childSeller;
	}

	public BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public BigDecimal getTotalSurplus() {
		return totalSurplus;
	}

	public void setTotalSurplus(BigDecimal totalSurplus) {
		this.totalSurplus = totalSurplus;
	}

	public Integer getRechargeNum() {
		return rechargeNum;
	}

	public void setRechargeNum(Integer rechargeNum) {
		this.rechargeNum = rechargeNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRelationStore() {
		return relationStore;
	}

	public void setRelationStore(String relationStore) {
		this.relationStore = relationStore;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public BigDecimal getLimitRecharge() {
		return limitRecharge;
	}

	public void setLimitRecharge(BigDecimal limitRecharge) {
		this.limitRecharge = limitRecharge;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public Integer getReferrerRatio() {
		return referrerRatio;
	}

	public void setReferrerRatio(Integer referrerRatio) {
		this.referrerRatio = referrerRatio;
	}

	public Integer getParentReferrerRatio() {
		return parentReferrerRatio;
	}

	public void setParentReferrerRatio(Integer parentReferrerRatio) {
		this.parentReferrerRatio = parentReferrerRatio;
	}

	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

	@Override
	public String toString() {
		return "ValueCard [sellerid=" + sellerid + ", sellerName=" + sellerName
				+ ", sellerType=" + sellerType + ", childSeller=" + childSeller
				+ ", totalRecharge=" + totalRecharge + ", totalSurplus="
				+ totalSurplus + ", rechargeNum=" + rechargeNum
				+ ", relationStore=" + relationStore + ", updateTime="
				+ updateTime + ", status=" + status + ", limitRecharge="
				+ limitRecharge + ", combo=" + combo + ", referrerRatio="
				+ referrerRatio + ", parentReferrerRatio="
				+ parentReferrerRatio + "]";
	}

}
