package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;

import javax.xml.crypto.Data;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TBargainPrice
 * 
 * 类描述：爆品价格
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年07月03日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TBargainPrice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6226094960282132061L;
	private Integer id;
	private Integer bid;//爆品区域ID
	private String startTime;//该价格开始销售时间
	private String endTime;//该价格销售截止时间
	private BigDecimal price;//销售价格
	private Integer status;//使用状态
	
	public String getStartTimeText() {
		return startTime != null ? startTime.substring(0, 5) : null;
	}

	public String getEndTimeText() {
		return endTime != null ? endTime.substring(0, 5) : null;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
