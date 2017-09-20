package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：Plan
 * 
 * 类描述： 简易封装的一个关于会员发行管理表(充值方案)的实体类,用于商家会员卡新增保存充值方案
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月26日 下午8:08:16 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Plan extends BaseEntity implements Comparable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 充值额
	 */
	private BigDecimal price;
	
	/*
	 * 到账额
	 */
	private BigDecimal retail;
	
	/*
	 * 是否默认充值方案
	 */
	private int isDefault;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2);
	}
	public BigDecimal getRetail() {
		return retail;
	}
	public void setRetail(BigDecimal retail) {
		this.retail = retail.setScale(2);
	}
	
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Plan [price=" + price + ", retail=" + retail + ", isDefault="
				+ isDefault + "]";
	}
	@Override
	public int compareTo(Object obj) {
		return this.getPrice().compareTo(((Plan) obj).getPrice());
	}

}