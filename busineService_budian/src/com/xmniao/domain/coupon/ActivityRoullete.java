package com.xmniao.domain.coupon;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityRoullete {
    private Integer id;

    private Integer sellerid;

    private String name;

    private Date beginDate;

    private Date endDate;

    private Date beginTime;

    private Date endTime;

    private Long awardProportion;

    private Integer prizeDrawNumber;

    private Integer limitNumber;

    private Integer setCondition;

    private Integer views;

    private Integer status;

    private Integer joinNumber;

    private Integer giveNumber;

    private Integer newuserNum;

    private BigDecimal consumeAmount;

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

    public Long getAwardProportion() {
        return awardProportion;
    }

    public void setAwardProportion(Long awardProportion) {
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

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
    }

    public Integer getNewuserNum() {
        return newuserNum;
    }

    public void setNewuserNum(Integer newuserNum) {
        this.newuserNum = newuserNum;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
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