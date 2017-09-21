package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class MallOrderProductBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer codeId;
	private Integer activityId;
	private String attrids;
	private Integer wareNum;
	private BigDecimal price;
	private BigDecimal integral;
	private BigDecimal freight;
	private Long supplierId;
	private Integer activityType;
	private Integer expTid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWareNum() {
		return wareNum;
	}

	public void setWareNum(Integer wareNum) {
		this.wareNum = wareNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getTotalPrice(){
		if(this.price == null) this.price = new BigDecimal("0");
		
		return this.price.multiply(new BigDecimal(this.wareNum)).setScale(2);
	}
	
	public BigDecimal getTotalIntegral(){
		if(this.integral == null) this.integral = new BigDecimal("0");
		
		return this.integral.multiply(new BigDecimal(this.wareNum)).setScale(2);
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getAttrids() {
		return attrids;
	}

	public void setAttrids(String attrids) {
		this.attrids = attrids;
	}

	public Integer getExpTid() {
		return expTid;
	}

	public void setExpTid(Integer expTid) {
		this.expTid = expTid;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

}
