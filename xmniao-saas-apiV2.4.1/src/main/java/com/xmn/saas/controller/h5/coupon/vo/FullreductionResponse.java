/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

import java.math.BigDecimal;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减活动数据详情响应信息
 * 创建人：huangk   
 * 创建时间：2016年10月10日 上午10:38:37      
 */

public class FullreductionResponse {
	
	private int id;
	private int sellerId;
	private String cname;
	private String startTime;
	private String endTime;
	private int viewNum;//曝光次数
	private int newUserNum;//新会员数
	private int joinNum;//参与人数
	private String activeAmount;//刺激消费金额
	//是否开启三级递减
	private int isDerate;
	private BigDecimal derateLevel1Amount;
    private BigDecimal consumeAndPay1;
    private BigDecimal derateLevel2Amount;
    private BigDecimal consumeAndPay2;
    private BigDecimal derateLevel3Amount;
    private BigDecimal consumeAndPay3;
    private BigDecimal consumeAndPay;//消费并支付满
    //活动时间
	private int passedDays;//活动已进行天数
	private int lastDays;//活动距离结束天数
	private int limitNumber;
	private BigDecimal offsetAmount;//减免金额
	private BigDecimal reductionAmount;//减免总金额
	private int status;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	public int getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public int getNewUserNum() {
		return newUserNum;
	}
	public void setNewUserNum(int newUserNum) {
		this.newUserNum = newUserNum;
	}
	public String getActiveAmount() {
		return activeAmount;
	}
	public void setActiveAmount(String activeAmount) {
		this.activeAmount = activeAmount;
	}
	public int getIsDerate() {
		return isDerate;
	}
	public void setIsDerate(int isDerate) {
		this.isDerate = isDerate;
	}
	public BigDecimal getDerateLevel1Amount() {
		return derateLevel1Amount;
	}
	public void setDerateLevel1Amount(BigDecimal derateLevel1Amount) {
		this.derateLevel1Amount = derateLevel1Amount;
	}
	public BigDecimal getConsumeAndPay1() {
		return consumeAndPay1;
	}
	public void setConsumeAndPay1(BigDecimal consumeAndPay1) {
		this.consumeAndPay1 = consumeAndPay1;
	}
	public BigDecimal getDerateLevel2Amount() {
		return derateLevel2Amount;
	}
	public void setDerateLevel2Amount(BigDecimal derateLevel2Amount) {
		this.derateLevel2Amount = derateLevel2Amount;
	}
	public BigDecimal getConsumeAndPay2() {
		return consumeAndPay2;
	}
	public void setConsumeAndPay2(BigDecimal consumeAndPay2) {
		this.consumeAndPay2 = consumeAndPay2;
	}
	public BigDecimal getDerateLevel3Amount() {
		return derateLevel3Amount;
	}
	public void setDerateLevel3Amount(BigDecimal derateLevel3Amount) {
		this.derateLevel3Amount = derateLevel3Amount;
	}
	public BigDecimal getConsumeAndPay3() {
		return consumeAndPay3;
	}
	public void setConsumeAndPay3(BigDecimal consumeAndPay3) {
		this.consumeAndPay3 = consumeAndPay3;
	}
	public BigDecimal getConsumeAndPay() {
		return consumeAndPay;
	}
	public void setConsumeAndPay(BigDecimal consumeAndPay) {
		this.consumeAndPay = consumeAndPay;
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
	public int getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}
	public void setOffsetAmount(BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}
	public BigDecimal getReductionAmount() {
		return reductionAmount;
	}
	public void setReductionAmount(BigDecimal reductionAmount) {
		this.reductionAmount = reductionAmount;
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
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}	
	
}
