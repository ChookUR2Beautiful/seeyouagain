package com.xmn.saas.controller.h5.activity.vo;


import com.xmn.saas.base.Request;

public class SellerCouponDetailRequset extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cid;	//奖品Id
	
	private Integer sendNum;	//奖品数量
	
	private Integer couponType;//优惠券类型:1 普通优惠券 2 随机优惠券 3现金抵用券 4赠品券  5红包
	
	private String couponName;	//奖品名字
	
	private String couponEndDate;  //结束日期
	
	private SellerCouponDetailRequset[] sellerCouponDetails;
	
	public SellerCouponDetailRequset[] getSellerCouponDetails() {
		return sellerCouponDetails;
	}

	public void setSellerCouponDetails(SellerCouponDetailRequset[] sellerCouponDetailRequsets) {
		this.sellerCouponDetails = sellerCouponDetailRequsets;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public String getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(String couponEndDate) {
		this.couponEndDate = couponEndDate;
	}
	
}
