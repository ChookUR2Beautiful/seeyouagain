package com.xmniao.domain.coupon;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：ActivityFcouspoints
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月8日 下午2:22:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ActivityFcouspoints {
    private Integer id;

    private Integer sellerid;

    private String name;

    private Date beginDate;

    private Date endDate;

    private Date endTime;

    private Integer giveNumber;

    private Integer pointsNumber;

    private BigDecimal fullPrice;

    private Integer isSuposition;

    private Integer countPoints;

    private Integer joinNumber;

    private Integer conversionNumber;

    private Integer status;

    private Integer views;

    private Date createTime;

    private Date nowDate;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
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

    public Integer getCountPoints() {
        return countPoints;
    }

    public void setCountPoints(Integer countPoints) {
        this.countPoints = countPoints;
    }

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Integer getConversionNumber() {
        return conversionNumber;
    }

    public void setConversionNumber(Integer conversionNumber) {
        this.conversionNumber = conversionNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
    
}