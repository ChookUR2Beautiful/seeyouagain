/**   
 * 文件名：TSellerDetailed.java   
 *    
 * 日期：2014年11月11日15时46分32秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerDetailed
 * 
 * 类描述：商家详情
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时46分32秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerDetailed extends BaseEntity {

	private static final long serialVersionUID = -110645230374660367L;

	private Integer sellerid;// 商家ID
	private Double consume;// 人均消费
	private String returnrmb;// 返现描述
	private Integer iswifi;// 0=没有|1=免费提供|2=有偿提供
	private Integer isparking;// 0=没有|1=免费停车位|2=有偿停车位
	private Integer isroom;	//0=未知 1有 2没有
	private String introduce;// 商家介绍
	private String dishes;// 推荐菜品
	private String landmark;// 参考地标
	private Integer number;//点赞数 number
	private String sellername;// 商家名称
	private Integer numberSt;//点赞数(搜索条件)
	private Integer numberEd;//点赞数(搜索条件)
	private String WIFIName;// WIFI名称
	private String WIFIPassword;// WIFI密码
	private String tips;//商家提示（活动）
	private Integer sellerGrade;//商家等级
	private String sellerGradeStr;//商家等级
	private Double shopArea;//店面面积
	private Integer staffNumber;//服务员数量
	private Double monthlyTurnover;//月营业额(月流水)
	private Integer isChain;//是否连锁
	private Integer isPay;//商家打赏功，0未开启，1开启
	private String rule;//商家优惠
	private String adjustReason;//级别调整原因
	private Integer isFirst;//是否开启首次，默认 0:否 1:是
	private Integer isOpenBooking;//是否开通点菜 0：否，1：是 默认为否
	private Integer rebateOut;//是否允许总店返利资金提现:0不允许，1允许
	private Integer operatingOut;//是否允许总店营业资金提现:0不允许，1允许
	private Integer isShopinfo;//是否允许总店修改商家基本信息:0不允许，1允许
	private Integer isDiscount;//是否允许总店修改折扣:0不允许，1允许
    private String lssellername;//连锁店名称
    private String fatherid;//总部商家ID
    private Double amountLimit;//商家提现限额，默认50000
    
	public String getFatherid() {
		return fatherid;
	}

	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}

	public String getLssellername() {
		return lssellername;
	}

	public void setLssellername(String lssellername) {
		this.lssellername = lssellername;
	}

	public Integer getRebateOut() {
		return rebateOut;
	}

	public void setRebateOut(Integer rebateOut) {
		this.rebateOut = rebateOut;
	}

	public Integer getOperatingOut() {
		return operatingOut;
	}

	public void setOperatingOut(Integer operatingOut) {
		this.operatingOut = operatingOut;
	}

	public Integer getIsShopinfo() {
		return isShopinfo;
	}

	public void setIsShopinfo(Integer isShopinfo) {
		this.isShopinfo = isShopinfo;
	}

	public Integer getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}

	//Add by zhang'zhiwen on 2015/4/22
	private String sellerIds;//前台传递的商家的Id
	
	
	public String getIsOpenBookingText() {
		if (this.isOpenBooking != null) {
			switch (this.isOpenBooking) {
			case 0:
				return "否";
			case 1:
				return "是";
			}
		}
		return null;
	}
	
	public String getSellerIds() {
		return sellerIds;
	}

	public void setSellerIds(String sellerIds) {
		this.sellerIds = sellerIds;
	}

	public Integer getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getSellerGrade() {
		return sellerGrade;
	}

	public void setSellerGrade(Integer sellerGrade) {
		this.sellerGrade = sellerGrade;
	}
	public String getSellerGradeStr() {
            if(this.sellerGrade == null){
            	return "";
            }
			if(this.sellerGrade == 1) return "A";
			if(this.sellerGrade == 2) return "B+";
			if(this.sellerGrade == 3) return "B";
			if(this.sellerGrade == 4) return "C+";
			if(this.sellerGrade == 5) return "C";
			return "";
	}
	public void setSellerGradeStr(String sellerGradeStr) {
		this.sellerGradeStr = sellerGradeStr;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Double getShopArea() {
		return shopArea;
	}

	public void setShopArea(Double shopArea) {
		this.shopArea = shopArea;
	}

	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

	public Double getMonthlyTurnover() {
		return monthlyTurnover;
	}

	public void setMonthlyTurnover(Double monthlyTurnover) {
		this.monthlyTurnover = monthlyTurnover;
	}

	public Integer getIsChain() {
		return isChain;
	}

	public void setIsChain(Integer isChain) {
		this.isChain = isChain;
	}

	public String getWIFIName() {
		return WIFIName;
	}

	public void setWIFIName(String wIFIName) {
		WIFIName = wIFIName;
	}

	public String getWIFIPassword() {
		return WIFIPassword;
	}

	public void setWIFIPassword(String wIFIPassword) {
		WIFIPassword = wIFIPassword;
	}

	//获取是否有wifi文字说明
	public String getIswifiText(){
		if(iswifi == null) return null;
		if(iswifi == 0) return "没有";
		if(iswifi == 1) return "免费提供";
		if(iswifi == 2) return "有偿提供";
		return null;
	}
	
	//获取是否有停车场文字说明
	public String getIsparkingText(){
		if(isparking == null) return null;
		if(isparking == 0) return "没有";
		if(isparking == 1) return "免费停车位";
		if(isparking == 2) return "有偿停车位";
		return null;
	}
	
	public Integer getNumberSt() {
		return numberSt;
	}

	public void setNumberSt(Integer numberSt) {
		this.numberSt = numberSt;
	}

	public Integer getNumberEd() {
		return numberEd;
	}

	public void setNumberEd(Integer numberEd) {
		this.numberEd = numberEd;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	/**
	 * 创建一个新的实例 TSellerDetailed.
	 * 
	 */
	public TSellerDetailed() {
		super();
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Double getConsume() {
		return consume;
	}

	public void setConsume(Double consume) {
		this.consume = consume;
	}

	public String getReturnrmb() {
		return returnrmb;
	}

	public void setReturnrmb(String returnrmb) {
		this.returnrmb = returnrmb;
	}

	public Integer getIswifi() {
		return iswifi;
	}

	public void setIswifi(Integer iswifi) {
		this.iswifi = iswifi;
	}

	public Integer getIsparking() {
		return isparking;
	}

	public void setIsparking(Integer isparking) {
		this.isparking = isparking;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDishes() {
		return dishes;
	}

	public void setDishes(String dishes) {
		this.dishes = dishes;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public Integer getIsOpenBooking() {
		return isOpenBooking;
	}

	public void setIsOpenBooking(Integer isOpenBooking) {
		this.isOpenBooking = isOpenBooking;
	}

	public Double getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(Double amountLimit) {
		this.amountLimit = amountLimit;
	}

	public Integer getIsroom() {
		return isroom;
	}

	public void setIsroom(Integer isroom) {
		this.isroom = isroom;
	}

	@Override
	public String toString() {
		return "TSellerDetailed [sellerid=" + sellerid + ", consume=" + consume
				+ ", returnrmb=" + returnrmb + ", iswifi=" + iswifi
				+ ", isparking=" + isparking + ", introduce=" + introduce
				+ ", dishes=" + dishes + ", landmark=" + landmark + ", number="
				+ number + ", sellername=" + sellername + ", numberSt="
				+ numberSt + ", numberEd=" + numberEd + ", WIFIName="
				+ WIFIName + ", WIFIPassword=" + WIFIPassword + ", tips="
				+ tips + ", sellerGrade=" + sellerGrade + ", sellerGradeStr="
				+ sellerGradeStr + ", shopArea=" + shopArea + ", staffNumber="
				+ staffNumber + ", monthlyTurnover=" + monthlyTurnover
				+ ", isChain=" + isChain + ", isPay=" + isPay + ", rule="
				+ rule + ", adjustReason=" + adjustReason + "]";
	}
}
