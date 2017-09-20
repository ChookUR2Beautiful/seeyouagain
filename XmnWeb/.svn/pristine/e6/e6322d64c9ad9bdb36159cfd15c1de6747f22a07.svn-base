package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class FcouspointRecord extends BaseEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5549163922970824317L;

	private Integer id;

    private Integer activityId;

    private Integer sellerid;

    private Long bid;

    private Integer uid;

    private String phone;

    private Integer givePoints;
    
    private Integer points;
    
    private Integer converPoints;

    private Date getTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(Integer givePoints) {
        this.givePoints = givePoints;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public Integer getConverPoints() {
		return converPoints;
	}

	public void setConverPoints(Integer converPoints) {
		this.converPoints = converPoints;
	}

	@Override
	public String toString() {
		return "FcouspointRecord [id=" + id + ", activityId=" + activityId
				+ ", sellerid=" + sellerid + ", bid=" + bid + ", uid=" + uid
				+ ", phone=" + phone + ", givePoints=" + givePoints
				+ ", points=" + points + ", getTime=" + getTime + "]";
	}
    
}