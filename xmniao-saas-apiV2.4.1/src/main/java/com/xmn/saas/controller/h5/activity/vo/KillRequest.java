package com.xmn.saas.controller.h5.activity.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.activity.Kill;

public class KillRequest extends Request {
	@NotNull(message = "活动名称不能为空")
	private String name; // 活动名称
	
	@NotNull(message = "开始时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate; // 开始日期
	
	@NotNull(message = "结束时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 结束日期
	
	@NotNull(message = "开始时间不能为空")
	@DateTimeFormat(pattern = "HH:mm")
	private Date beginTime; // 开始时间
	
	@NotNull(message = "结束时间不能为空")
	@DateTimeFormat(pattern = "HH:mm")
	private Date endTime; // 结束时间
	
	@NotNull(message = "每人限领不能为空")
	private Integer limitNumber;	//每人限获（0：不限制，1:限领一次)
	
	@NotNull(message = "秒杀金额不能为空")
	private BigDecimal secKillAmount; // 秒杀金额

	private SellerCouponDetailRequset[] sellerCouponDetails;
	
	public Kill convertRequestToBean(){
		Kill kill=new Kill();
		kill.setName(name);
		kill.setBeginDate(beginDate);
		kill.setEndDate(endDate);
		kill.setBeginTime(beginTime);
		kill.setEndTime(endTime);
		kill.setSecKillAmount(secKillAmount);
		kill.setLimitNumber(limitNumber);
		return kill;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getSecKillAmount() {
		return secKillAmount;
	}

	public void setSecKillAmount(BigDecimal secKillAmount) {
		this.secKillAmount = secKillAmount;
	}

	public SellerCouponDetailRequset[] getSellerCouponDetails() {
		return sellerCouponDetails;
	}

	public void setSellerCouponDetails(SellerCouponDetailRequset[] sellerCouponDetails) {
		this.sellerCouponDetails = sellerCouponDetails;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}
	
}
