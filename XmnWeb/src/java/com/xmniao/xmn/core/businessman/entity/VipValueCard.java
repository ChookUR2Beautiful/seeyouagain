/**
 * 
 */
package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VipValueCard
 * 
 * 类描述： 会员处置卡
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月21日 上午11:49:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class VipValueCard extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1203280458427901044L;

	private Integer id;
	
	private String userName;
	
	private Integer uid;
	
	private String account;//账号
	
	private Integer type;//卡类型 1.普通商家 2.连锁总店 3 区域代理
	
	private String sellerid;//所属商家
	
	private String sellername;
	
	private Integer applySeller;//适用商户
	
	private BigDecimal totalLimit;//充值额度
	
	private BigDecimal presentLimit;//当前额度
	
	private BigDecimal consumerLimit;//已消费额度
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getApplySeller() {
		return applySeller;
	}

	public void setApplySeller(Integer applySeller) {
		this.applySeller = applySeller;
	}

	public BigDecimal getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}

	public BigDecimal getPresentLimit() {
		return presentLimit;
	}

	public void setPresentLimit(BigDecimal presentLimit) {
		this.presentLimit = presentLimit;
	}

	public BigDecimal getConsumerLimit() {
		return consumerLimit;
	}

	public void setConsumerLimit(BigDecimal consumerLimit) {
		this.consumerLimit = consumerLimit;
	}
	
	@Override
	public String toString() {
		return "VipValueCard [id=" + id + ", userName=" + userName + ", uid="
				+ uid + ", account=" + account + ", type=" + type
				+ ", sellerid=" + sellerid + ", sellername=" + sellername
				+ ", applySeller=" + applySeller + ", totalLimit=" + totalLimit
				+ ", presentLimit=" + presentLimit + ", consumerLimit="
				+ consumerLimit + "]";
	}

}
