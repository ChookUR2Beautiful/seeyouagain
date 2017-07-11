package com.xmn.saas.controller.h5.activity.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Fcouspoints;


public class FcouspontsRequest {

	private String name;

    private Date endTime;
    
    private Integer pointsNumber;
    
    private BigDecimal fullPrice;
    
    private Integer isSuposition;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate; // 开始日期;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 结束日期
    
    private Integer awardId;
    
	public Integer getAwardId() {
		return awardId;
	}

	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPointsNumber() {
		return pointsNumber;
	}

	public void setPointsNumber(Integer pointsNumber) {
		this.pointsNumber = pointsNumber;
	}

	public BigDecimal getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(BigDecimal fullPrice) {
		this.fullPrice = fullPrice;
	}

	public Integer getIsSuposition() {
		return isSuposition;
	}

	public void setIsSuposition(Integer isSuposition) {
		this.isSuposition = isSuposition;
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

	public Fcouspoints convertRequestToBean() {
		Fcouspoints fcouspoints = new Fcouspoints();
		fcouspoints.setName(name);
		fcouspoints.setEndDate(endDate);
		fcouspoints.setBeginDate(beginDate);
		fcouspoints.setPointsNumber(pointsNumber);
		fcouspoints.setFullPrice(fullPrice);
		fcouspoints.setIsSuposition(isSuposition);
		return fcouspoints;
	}
    
    
	
}
