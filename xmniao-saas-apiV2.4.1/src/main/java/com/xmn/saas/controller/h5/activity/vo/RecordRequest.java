package com.xmn.saas.controller.h5.activity.vo;


import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;

public class RecordRequest extends Request{

	private static final long serialVersionUID = 1L;
	
	Integer recordId;
	
	@NotNull(message="活动ID不能为空")
	Integer activityId;
	
	@NotNull(message="活动类型不能为空")
	Integer activityType;
	
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

}
