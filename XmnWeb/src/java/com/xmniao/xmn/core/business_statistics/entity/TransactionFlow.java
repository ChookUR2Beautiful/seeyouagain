package com.xmniao.xmn.core.business_statistics.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TransactionFlow
 * 
 * 类描述：商家交易流水实体
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TransactionFlow extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6328114657328744949L;
	
	
	private String censusDate; //统计日期

	private Integer tradingVolume;//交易量
	
	private BigDecimal tradingTotal;//交易总额
	
	private BigDecimal averageTradingTotal;//平均交易金额
	
	/**
	 * 查询条件 字段无关
	 */
	private Date startCensusDate;//  统计开始日期
	private Date endCensusDate;//  统计结束日期
	
	
	public String getCensusDate() {
		return censusDate;
	}
	public void setCensusDate(String censusDate) {
		this.censusDate = censusDate;
	}
	public Integer getTradingVolume() {
		return tradingVolume;
	}
	public void setTradingVolume(Integer tradingVolume) {
		this.tradingVolume = tradingVolume;
	}
	public BigDecimal getTradingTotal() {
		return tradingTotal;
	}
	public void setTradingTotal(BigDecimal tradingTotal) {
		this.tradingTotal = tradingTotal;
	}
	public BigDecimal getAverageTradingTotal() {
		return this.averageTradingTotal;
	}
	public void setAverageTradingTotal(BigDecimal averageTradingTotal) {
		this.averageTradingTotal = averageTradingTotal;
	}
	public Date getStartCensusDate() {
		return startCensusDate;
	}
	public void setStartCensusDate(Date startCensusDate) {
		this.startCensusDate = startCensusDate;
	}
	public Date getEndCensusDate() {
		return endCensusDate;
	}
	public void setEndCensusDate(Date endCensusDate) {
		this.endCensusDate = endCensusDate;
	}
	@Override
	public String toString() {
		return "TransactionFlow [censusDate=" + censusDate + ", tradingVolume="
				+ tradingVolume + ", tradingTotal=" + tradingTotal
				+ ", AverageTradingTotal=" + averageTradingTotal
				+ ", startCensusDate=" + startCensusDate + ", endCensusDate="
				+ endCensusDate + "]";
	}


}
