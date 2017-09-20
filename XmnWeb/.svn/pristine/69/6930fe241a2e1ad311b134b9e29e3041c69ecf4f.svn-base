package com.xmniao.xmn.core.coupon.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：TCouponValidity
 * 
 * @类描述： 优惠券有效期
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年5月29日 下午5:17:33
 * 
 */
public class TCouponValidity extends BaseEntity {

	private static final long serialVersionUID = -4853134001671823669L;
	private Integer cvid;//
	private Integer cid;// 优惠券id
	private Date startDate;// 开始日期
	private String startTime;// 开始时间
	private Date endDate;// 结束日期
	private String endTime;// 结束时间

	public String getStartTimeText() {
		return startTime != null ? startTime.substring(0, 5) : null;
	}

	public String getEndTimeText() {
		return endTime != null ? endTime.substring(0, 5) : null;
	}

	public Integer getCvid() {
		return cvid;
	}

	public void setCvid(Integer cvid) {
		this.cvid = cvid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TCouponValidity [cvid=" + cvid + ", cid=" + cid
				+ ", startDate=" + startDate + ", startTime=" + startTime
				+ ", endDate=" + endDate + ", endTime=" + endTime + "]";
	}

}
