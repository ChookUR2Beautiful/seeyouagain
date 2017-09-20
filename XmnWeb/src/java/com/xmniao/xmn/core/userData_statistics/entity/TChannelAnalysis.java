package com.xmniao.xmn.core.userData_statistics.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TChannelAnalysis extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2967889129495730048L;
	
	private String channelName;
	private String versionNumber;
	private Long increasedUserNum;
	/**
	 * 查询条件 字段无关
	 */
	private Date startCensusDate;//  统计开始日期
	private Date endCensusDate;//  统计结束日期
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public Long getIncreasedUserNum() {
		return increasedUserNum;
	}
	public void setIncreasedUserNum(Long increasedUserNum) {
		this.increasedUserNum = increasedUserNum;
	}
	public Date getStartCensusDate() {
		return startCensusDate;
	}
	public void setStartCensusDate(Date startCensusDate) {
		this.startCensusDate = startCensusDate;
	}
	public Date getEndCensusDate() {
		return endCensusDate;
	}
	public void setEndCensusDate(Date endCensusDate) {
		this.endCensusDate = endCensusDate;
	}
	@Override
	public String toString() {
		return "TChannelAnalysis [channelName=" + channelName
				+ ", versionNumber=" + versionNumber + ", increasedUserNum="
				+ increasedUserNum + "]";
	}
	
	

}
