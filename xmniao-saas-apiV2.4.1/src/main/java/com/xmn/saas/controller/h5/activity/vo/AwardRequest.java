package com.xmn.saas.controller.h5.activity.vo;

import com.xmn.saas.base.Request;

public class AwardRequest extends Request {
	private Integer awardType;
	private Integer awardId;
	
	public Integer getAwardType() {
		return awardType;
	}
	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}
	public Integer getAwardId() {
		return awardId;
	}
	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}
	
}
