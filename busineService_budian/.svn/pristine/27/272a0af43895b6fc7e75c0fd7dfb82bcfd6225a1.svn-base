/**    
 * 文件名：LiveLedgerBean.java    
 *    
 * 版本信息：    
 * 日期：2016年11月18日    
 * Copyright 足下 Corporation 2016     
 * 版权所有    
 *    
 */
package com.xmniao.domain.ledger;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveLedgerBean
 * 
 * 类描述： 直播分账实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年11月18日 上午10:59:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveLedgerBean {


	private double  orderMoney;					/* 订单实际参与金额 */
	private double  userMoney;					/* 用户支付金额 */
	
	private double  ledgerRatio = 0d;			/* 分账上限比例 ,默认：5% */
	
	private boolean bSellerid=true;					/* 消费商户ID */
	private double  baseagio;					/* 商户直播分账比例 */
	
	private boolean bGenusSellerid=false; 				/* 所属商户ID */
	private double  sellerRatio = 0d;			/* 商户店外收益比例，默认：20%*ledgerRation */
	
	private boolean bMikeId=false;
	private Integer mikeType=2;					/* 寻蜜客类型: 1.个人寻蜜客  2.中脉寻蜜客  */
	private double  mikeRatio = 0d;			/* 寻蜜客分账比例,默认：20%*ledgerRation*/
	
	private boolean bParentMikeId=false;				/* 上级寻蜜客ID */
	private double  parentMikeRatio = 0d;	/* 上级寻蜜客分账比例,默认为：10%*ledgerRation*/
	
	private boolean bTopMikeId=false;					/* 上上级寻蜜客ID*/
	private double  topMikeRatio = 0d;		/* 上上级寻蜜客分账比例,默认：10%*ledgerRation*/
	
	private boolean bConsumeJointid=false;				/* 经销商ID（原消费区域合作商 ） */
	private double  consumeJointRatio = 0d;	/* 经销商分账比例,默认 10%*ledgerRation */
	
	private double sellerExtraMoney;	/* 商家额外收益（不参与本次分账，直接归属商家） */
	private Integer ledgerMode=0;		/* 订单分账模式 0 正常立减(正常分账) 1不给立减(不给分账)  2不给立减(只给消费商家分账) */
	
	public double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public double getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(double userMoney) {
		this.userMoney = userMoney;
	}
	public double getLedgerRatio() {
		return ledgerRatio;
	}
	public void setLedgerRatio(double ledgerRatio) {
		this.ledgerRatio = ledgerRatio;
	}
	public boolean isbSellerid() {
		return bSellerid;
	}
	public void setbSellerid(boolean bSellerid) {
		this.bSellerid = bSellerid;
	}
	public double getBaseagio() {
		return baseagio;
	}
	public void setBaseagio(double baseagio) {
		this.baseagio = baseagio;
	}
	public boolean isbGenusSellerid() {
		return bGenusSellerid;
	}
	public void setbGenusSellerid(boolean bGenusSellerid) {
		this.bGenusSellerid = bGenusSellerid;
	}
	public double getSellerRatio() {
		return sellerRatio;
	}
	public void setSellerRatio(double sellerRatio) {
		this.sellerRatio = sellerRatio;
	}
	public boolean isbMikeId() {
		return bMikeId;
	}
	public void setbMikeId(boolean bMikeId) {
		this.bMikeId = bMikeId;
	}
	public Integer getMikeType() {
		return mikeType;
	}
	public void setMikeType(Integer mikeType) {
		this.mikeType = mikeType;
	}
	public double getMikeRatio() {
		return mikeRatio;
	}
	public void setMikeRatio(double mikeRatio) {
		this.mikeRatio = mikeRatio;
	}
	public boolean isbParentMikeId() {
		return bParentMikeId;
	}
	public void setbParentMikeId(boolean bParentMikeId) {
		this.bParentMikeId = bParentMikeId;
	}
	public double getParentMikeRatio() {
		return parentMikeRatio;
	}
	public void setParentMikeRatio(double parentMikeRatio) {
		this.parentMikeRatio = parentMikeRatio;
	}
	public boolean isbTopMikeId() {
		return bTopMikeId;
	}
	public void setbTopMikeId(boolean bTopMikeId) {
		this.bTopMikeId = bTopMikeId;
	}
	public double getTopMikeRatio() {
		return topMikeRatio;
	}
	public void setTopMikeRatio(double topMikeRatio) {
		this.topMikeRatio = topMikeRatio;
	}
	public boolean isbConsumeJointid() {
		return bConsumeJointid;
	}
	public void setbConsumeJointid(boolean bConsumeJointid) {
		this.bConsumeJointid = bConsumeJointid;
	}
	public double getConsumeJointRatio() {
		return consumeJointRatio;
	}
	public void setConsumeJointRatio(double consumeJointRatio) {
		this.consumeJointRatio = consumeJointRatio;
	}
	public double getSellerExtraMoney() {
		return sellerExtraMoney;
	}
	public void setSellerExtraMoney(double sellerExtraMoney) {
		this.sellerExtraMoney = sellerExtraMoney;
	}
	public Integer getLedgerMode() {
		return ledgerMode;
	}
	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}

	
	

}
