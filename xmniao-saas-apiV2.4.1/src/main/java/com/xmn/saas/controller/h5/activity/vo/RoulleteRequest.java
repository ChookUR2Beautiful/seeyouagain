package com.xmn.saas.controller.h5.activity.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.activity.Roullete;

public class RoulleteRequest extends Request {
	@NotNull(message="活动名称不能为空")
	private String name; // 活动名称
	
	@NotNull(message="开始时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate; // 开始日期
	
	@NotNull(message="结束时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 结束日期
	
	@NotNull(message="获奖概率不能为空")
	private Double awardProportion; // 获奖几率
	
	@NotNull(message="抽取次数不能为空")
	private Integer prizeDrawNumber; // 每人可抽奖次数
	
	@NotNull(message="是否领取一次不能为空")
	private Integer limitNumber; // 每人限获（0：不限制，按数字依次类推限制数量）

	/**
	 * 0.不重设 1.分享获得次数，通过分享获得一次抽奖机会 2.每天重置次数，通过每天0点重置抽取奖品次数 3.消费获得次数，通过消费获得一次抽奖机会
	 */
	@NotNull(message="设置条件不能为空")
	private Integer setCondition; 
	
	@NotNull(message="奖品不能为空")
	private SellerCouponDetailRequset[] sellerCouponDetails;

	public Roullete convertRequestToBean(){
		Roullete roullete=new Roullete();
		roullete.setName(name);
		roullete.setBeginDate(beginDate);
		roullete.setEndDate(endDate);
		roullete.setAwardProportion(awardProportion);
		roullete.setPrizeDrawNumber(prizeDrawNumber);
		roullete.setLimitNumber(limitNumber);
		roullete.setSetCondition(setCondition);
		roullete.setLimitNumber(limitNumber);
		return roullete;
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

	public Double getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(Double awardProportion) {
		this.awardProportion = awardProportion;
	}

	public Integer getPrizeDrawNumber() {
		return prizeDrawNumber;
	}

	public void setPrizeDrawNumber(Integer prizeDrawNumber) {
		this.prizeDrawNumber = prizeDrawNumber;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Integer getSetCondition() {
		return setCondition;
	}

	public void setSetCondition(Integer setCondition) {
		this.setCondition = setCondition;
	}

	public SellerCouponDetailRequset[] getSellerCouponDetails() {
		return sellerCouponDetails;
	}

	public void setSellerCouponDetails(SellerCouponDetailRequset[] sellerCouponDetails) {
		this.sellerCouponDetails = sellerCouponDetails;
	}
	
	
}
