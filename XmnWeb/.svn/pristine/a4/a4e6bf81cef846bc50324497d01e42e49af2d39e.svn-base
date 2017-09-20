/**
 * 
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerActivity
 * 
 * 类描述： 商家活动
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月9日 下午4:35:26 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class SellerActivity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7122776650798216269L;

	private Integer id;
	
	private Integer sellerid;//商户id
	
	private String sellerName;//商家名称
	
	private String activityName;//活动名称
	
	private Integer type;//活动类型
	
	private Integer joinNum;//参与人数
	
	private Integer newVip;//绑定新会员
	
	private Date startDate;//开始时间

    private Date endDate;//结束时间
    
    private Date sDate;
    
    private Date eDate;
    
    private Integer status;//状态
    
    private Date createTime;//创建时间

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

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getNewVip() {
		return newVip;
	}

	public void setNewVip(Integer newVip) {
		this.newVip = newVip;
	}
	
	@JSONField(format="yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JSONField(format="yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	
	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SellerActivity [id=" + id + ", sellerid=" + sellerid
				+ ", sellerName=" + sellerName + ", activityName="
				+ activityName + ", type=" + type + ", joinNum=" + joinNum
				+ ", newVip=" + newVip + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", sDate=" + sDate + ", eDate="
				+ eDate + ", status=" + status + ", createTime=" + createTime
				+ "]";
	}
    
}
