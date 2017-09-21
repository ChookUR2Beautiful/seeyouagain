/**
 * 
 */
package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaleGroup
 * 
 * 类描述：在此处添加类描述
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月4日上午8:56:13
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSaleGroup implements Serializable {

	private static final long serialVersionUID = -2266917587021146912L;

	private Integer id;// 分组id
	private Long codeId;// 产品唯一标识编号
	private String pvIds;// 属性值id组合，未排序的，","作分隔符
	private String pvIdsSort;// 属性值id组合，由小到大排列，","作分隔符
	private List<String> pvValues;// 属性值集合
	private BigDecimal amount;// 加价金额
	private Integer stock;// 库存
	private Integer sort;// 排序
	private BigDecimal maxAmount;
	private Integer sales;	//销售数量
	private Integer maxStock;
	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	

	public Integer getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPvIds() {
		return pvIds;
	}

	public void setPvIds(String pvIds) {
		this.pvIds = pvIds;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<String> getPvValues() {
		return pvValues;
	}

	public void setPvValues(List<String> pvValues) {
		this.pvValues = pvValues;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getPvIdsSort() {
		return pvIdsSort;
	}

	public void setPvIdsSort(String pvIdsSort) {
		this.pvIdsSort = pvIdsSort;
	}

	@Override
	public String toString() {
		return "TSaleGroup [id=" + id + ", codeId=" + codeId + ", pvIds=" + pvIds + ", pvIdsSort=" + pvIdsSort
				+ ", pvValues=" + pvValues + ", amount=" + amount + ", stock=" + stock + ", sort=" + sort + "]";
	}

}
