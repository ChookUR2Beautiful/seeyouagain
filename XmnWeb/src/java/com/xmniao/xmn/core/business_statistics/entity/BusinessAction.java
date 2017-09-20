package com.xmniao.xmn.core.business_statistics.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessAction
 * 
 * 类描述：商家行为统计实体
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BusinessAction extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2741541687409672747L;
	
	private String censusDate; //统计日期
	private Integer newSeller;//新增商户
	private Integer startSeller;//启动商户
	private Integer startCount;//启动数量
	private Date  useTime;//使用时长
	private Integer tradingVolume;//交易量
	private BigDecimal tradingTotal;//交易总额
	
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
	public Integer getNewSeller() {
		return newSeller;
	}
	public void setNewSeller(Integer newSeller) {
		this.newSeller = newSeller;
	}
	public Integer getStartSeller() {
		return startSeller;
	}
	public void setStartSeller(Integer startSeller) {
		this.startSeller = startSeller;
	}
	public Integer getStartCount() {
		return startCount;
	}
	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
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
		return "BusinessAction [censusDate=" + censusDate + ", newSeller="
				+ newSeller + ", startSeller=" + startSeller + ", startCount="
				+ startCount + ", useTime=" + useTime + ", tradingVolume="
				+ tradingVolume + ", tradingTotal=" + tradingTotal
				+ ", startCensusDate=" + startCensusDate + ", endCensusDate="
				+ endCensusDate + "]";
	}

}
