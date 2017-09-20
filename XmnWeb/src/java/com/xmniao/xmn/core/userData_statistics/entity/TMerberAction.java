package com.xmniao.xmn.core.userData_statistics.entity;

import java.math.BigDecimal;

/**
 * 项目名称:XmnWeb
 * 
 * 类名：TMerberAction.java
 * 
 * 类描述：用户数据统计-->用户行为统计
 * 
 * 创建人：yang'xu
 * 
 * 创建时间：2014-12-22 15：24：53
 * 
 * Copyright © 广东寻蜜鸟网络技术有限公司
 */
public class TMerberAction extends PUserDataResponse {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3687380925179857916L;
	
	
	private Long tradingVolume=0L;		//交易量（笔）
	private BigDecimal tradingTotal = new BigDecimal(0);	//交易金额
	
	
	/**
	 * 查询条件 字段无关
	 */
	private String startCensusDate;//  统计开始日期
	private String endCensusDate;//  统计结束日期
	
	
	
	public String getStartCensusDate() {
		return startCensusDate;
	}
	public void setStartCensusDate(String startCensusDate) {
		this.startCensusDate = startCensusDate;
	}
	public String getEndCensusDate() {
		return endCensusDate;
	}
	public void setEndCensusDate(String endCensusDate) {
		this.endCensusDate = endCensusDate;
	}
	public Long getTradingVolume() {
		return tradingVolume;
	}
	public void setTradingVolume(Long tradingVolume) {
		this.tradingVolume = tradingVolume;
	}
	public BigDecimal getTradingTotal() {
		return tradingTotal;
	}
	public void setTradingTotal(BigDecimal tradingTotal) {
		this.tradingTotal = tradingTotal;
	}
	@Override
	public String toString() {
		return "TMerberAction [tradingVolume=" + tradingVolume
				+ ", tradingTotal=" + tradingTotal + ", startCensusDate="
				+ startCensusDate + ", endCensusDate=" + endCensusDate
				+ ", start_no=" + start_no + ", uv=" + uv + ", pv=" + pv
				+ ", valid_user=" + valid_user + ", add_user=" + add_user
				+ ", total_user=" + total_user + ", date=" + date + "]";
	}
	
}
