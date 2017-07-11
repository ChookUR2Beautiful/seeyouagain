package com.xmn.saas.controller.h5.redpacket.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;

/**
 * 红包通用查询VO
 * 
 * @ClassName: RedpacketCriteriaRequest
 * @Description: TODO
 * @author dengqiang
 * @date 2016年9月26日 下午6:01:36
 *
 */
public class RedpacketCriteriaRequest extends Request {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "sellerid not empty")
	private Integer sellerid;

	private Integer redpacketType;

	private Integer status;

	private Date startDate;

	private Date endDate;

	private Date beginTime;

	private Date endTime;

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getRedpacketType() {
		return redpacketType;
	}

	public void setRedpacketType(Integer redpacketType) {
		this.redpacketType = redpacketType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

}
