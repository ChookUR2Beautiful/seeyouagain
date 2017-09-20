/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PostCondition
 * 
 * 类描述： 
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月25日 下午3:57:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class PostCondition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6285943326508116028L;

	private Long id;//主键
	
	private Long templateId;//模板主键
	
	private BigDecimal firstItem;//首件价格
	
	private Integer firstNums;//首件数量
	
	private BigDecimal continueItem;//续件价格
	
	private Integer continueNums;//续件数量
	
	private String deliveryNo;//配送的编号
	
	private String deliveryCity;//配送城市
	
	private String createTime;//创建时间
	
	private String updateTime;//更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public BigDecimal getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(BigDecimal firstItem) {
		this.firstItem = firstItem;
	}

	public Integer getFirstNums() {
		return firstNums;
	}

	public void setFirstNums(Integer firstNums) {
		this.firstNums = firstNums;
	}

	public BigDecimal getContinueItem() {
		return continueItem;
	}

	public void setContinueItem(BigDecimal continueItem) {
		this.continueItem = continueItem;
	}

	public Integer getContinueNums() {
		return continueNums;
	}

	public void setContinueNums(Integer continueNums) {
		this.continueNums = continueNums;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "PostCondition [id=" + id + ", templateId=" + templateId
				+ ", firstItem=" + firstItem + ", firstNums=" + firstNums
				+ ", continueItem=" + continueItem + ", continueNums="
				+ continueNums + ", deliveryNo=" + deliveryNo
				+ ", deliveryCity=" + deliveryCity + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
}
