/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRequestBean
 * 
 * 类描述： 主播查询请求实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-1-20 下午2:19:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveRequestBean extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2985093434123576466L;
	
	private String fansRankName;//粉丝级别名称
	
	private String fansRankNameStr;//粉丝级别名称
	
	private BigDecimal payment;//支付金额
	
	private BigDecimal paymentSum;//支付总金额
	
	private long people;//充值人数
	
	private String isOnLine;
	
	private String startTime;//查询开始时间
	
	private String endTime;//查询结束时间
	
	

	/**
	 * @return the fansRankName
	 */
	public String getFansRankName() {
		return fansRankName;
	}

	/**
	 * @param fansRankName the fansRankName to set
	 */
	public void setFansRankName(String fansRankName) {
		this.fansRankName = fansRankName;
	}

	
	/**
	 * @return the fansRankNameStr
	 */
	public String getFansRankNameStr() {
		if(payment==null){
			return null;
		}
		if(payment.compareTo(new BigDecimal(500))<0){
			fansRankNameStr="铁粉";
		}else if(payment.compareTo(new BigDecimal(5000))<0){
			fansRankNameStr="银粉";
		}else if(payment.compareTo(new BigDecimal(10000))<0){
			fansRankNameStr="金粉";
		}else if(payment.compareTo(new BigDecimal(50000))<0){
			fansRankNameStr="金粉";
		}else if(payment.compareTo(new BigDecimal(100000))<0){
			fansRankNameStr="白金粉";
		}else if(payment.compareTo(new BigDecimal(300000))<0){
			fansRankNameStr="黑金粉";
		}else if(payment.compareTo(new BigDecimal(500000))<0){
			fansRankNameStr="钻粉";
		}else {
			fansRankNameStr="黑钻粉";
		}
		return fansRankNameStr;
	}

	/**
	 * @param fansRankNameStr the fansRankNameStr to set
	 */
	public void setFansRankNameStr(String fansRankNameStr) {
		this.fansRankNameStr = fansRankNameStr;
	}

	/**
	 * @return the payment
	 */
	public BigDecimal getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	/**
	 * @return the paymentSum
	 */
	public BigDecimal getPaymentSum() {
		return paymentSum;
	}

	/**
	 * @param paymentSum the paymentSum to set
	 */
	public void setPaymentSum(BigDecimal paymentSum) {
		this.paymentSum = paymentSum;
	}

	/**
	 * @return the people
	 */
	public long getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(long people) {
		this.people = people;
	}

	
	/**
	 * @return the isOnLine
	 */
	public String getIsOnLine() {
		return isOnLine;
	}

	/**
	 * @param isOnLine the isOnLine to set
	 */
	public void setIsOnLine(String isOnLine) {
		this.isOnLine = isOnLine;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiveRequestBean [fansRankName=" + fansRankName + ", payment="
				+ payment + ", paymentSum=" + paymentSum + ", people=" + people
				+ ", isOnLine=" + isOnLine + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	
	
	

}
