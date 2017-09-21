/**    
 * 文件名：SellerPackageLedger.java    
 *    
 * 版本信息：    
 * 日期：2017年3月8日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.domain.ledger;

import java.math.BigDecimal;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerPackageLedger
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月8日 下午2:56:46 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class SellerPackageLedger {

	//商家基本折扣
	private BigDecimal agio;
	
	//分账比例
	private BigDecimal ledgerRatio;
	
	//分账固定金额
	private BigDecimal ledgerAmount;
	
	//订单金额
	private BigDecimal orderMoney;

	public BigDecimal getLedgerRatio() {
		return ledgerRatio;
	}

	public void setLedgerRatio(BigDecimal ledgerRatio) {
		this.ledgerRatio = ledgerRatio;
	}

	public BigDecimal getLedgerAmount() {
		return ledgerAmount;
	}

	public void setLedgerAmount(BigDecimal ledgerAmount) {
		this.ledgerAmount = ledgerAmount;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	
	public BigDecimal getAgio() {
		return agio;
	}

	public void setAgio(BigDecimal agio) {
		this.agio = agio;
	}

	@Override
	public String toString() {
		return "SellerPackageLedger [ledgerRatio=" + ledgerRatio
				+ ", ledgerAmount=" + ledgerAmount + ", orderMoney="
				+ orderMoney + "]";
	}

	public SellerPackageLedger(BigDecimal orderMoney,BigDecimal ledgerRatio, 
			BigDecimal ledgerAmount,BigDecimal agio) {
		super();
		this.ledgerRatio = ledgerRatio;
		this.ledgerAmount = ledgerAmount;
		this.orderMoney = orderMoney;
		this.agio = agio;
	}

	public SellerPackageLedger() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
