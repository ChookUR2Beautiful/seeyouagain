/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   促销优惠数据详情响应信息
 * 创建人：huangk   
 * 创建时间：2016年10月10日 上午10:38:37      
 */

public class CouponDetailResponse {
	
	private int id;//优惠券id
	private int sellerId;
	private String activeAmount;//刺激消费金额
	private int awardNum;//领取个数
	private int newUserNum;//新会员数
	private int viewNum;//曝光次数
	private int passedDays;//活动已进行天数
	private int lastDays;//活动距离结束天数
	private int useNum;//使用个数
	private String cname;
	private String description;
	private int sendNum;
	private String awardCondition;
	private String useCondition;
	private String limitNumber;
	private String startTime;
	private String endTime;
	private String denomination;
	private String payAndConsume;
	private int status;
	private Integer maximum;
	
	
	
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getActiveAmount() {
		return activeAmount;
	}
	public void setActiveAmount(String activeAmount) {
		this.activeAmount = activeAmount;
	}
	public int getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(int awardNum) {
		this.awardNum = awardNum;
	}
	public int getNewUserNum() {
		return newUserNum;
	}
	public void setNewUserNum(int newUserNum) {
		this.newUserNum = newUserNum;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public int getPassedDays() {
		return passedDays;
	}
	public void setPassedDays(int passedDays) {
		this.passedDays = passedDays;
	}
	public int getLastDays() {
		return lastDays;
	}
	public void setLastDays(int lastDays) {
		this.lastDays = lastDays;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSendNum() {
		return sendNum;
	}
	public void setSendNum(int sendNum) {
		this.sendNum = sendNum;
	}
	public String getAwardCondition() {
		return awardCondition;
	}
	public void setAwardCondition(String awardCondition) {
		this.awardCondition = awardCondition;
	}
	public String getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}
	public String getUseCondition() {
		return useCondition;
	}
	public void setUseCondition(String useCondition) {
		this.useCondition = useCondition;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPayAndConsume() {
		return payAndConsume;
	}
	public void setPayAndConsume(String payAndConsume) {
		this.payAndConsume = payAndConsume;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUseNum() {
		return useNum;
	}
	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}
	
}
