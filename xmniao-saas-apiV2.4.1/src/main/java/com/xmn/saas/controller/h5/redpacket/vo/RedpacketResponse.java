package com.xmn.saas.controller.h5.redpacket.vo;

import com.xmn.saas.entity.redpacket.RedpacketRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 红包数据出参响应
 * 
 * @ClassName: RedpacketResponse
 * @Description: TODO
 * @author dengqiang
 * @date 2016年9月30日 下午4:21:48
 *
 */
@SuppressWarnings("all")
public class RedpacketResponse {

	private Long redpacketId;

	private Integer sellerid;

	private Integer redpacketType;

	private Integer amountType;

	private String redpacketName;

	private String beginDate;

	private String endDate;

	private String beginTime;

	private String endTime;

	private BigDecimal totalAmount;

	private BigDecimal randomAmountMini;

	private BigDecimal randomAmountMax;

	private Integer redpacketNumber;

	private BigDecimal singleAmount;

	private BigDecimal newUserAmount;

	private BigDecimal oldUserAmount;

	private String shareAwardsProportion;

	private String longitude;

	private String latitude;

	private String address;

	private BigDecimal receiveCondition;

	private BigDecimal deductibleAmount;

	private Long views;

	private BigDecimal realSpending;

	private Integer status;

	private String createTime;

	private String recordTime;

	private Integer payType;

	private Integer recordNumber;

	private BigDecimal chargeBalance;

	private Integer newUserNumber;

	private Integer dayNumberIng;

	private Integer dayNumberEnd;

	private RedpacketRecord redpacketRecord;

	private BigDecimal stimulateConsume;

	private Date closeDate;

	private Integer newuserNum;



	/******** 用户信息 ********/
	private Long userId;

	private String nickname;

	private String phone;

	private String avatar;

	private String genussellerid;

	private Integer downType;

	public String getNickname() {
		return nickname;
	}

	public Integer getNewuserNum() {
		return newuserNum;
	}

	public void setNewuserNum(Integer newuserNum) {
		this.newuserNum = newuserNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGenussellerid() {
		return genussellerid;
	}

	public void setGenussellerid(String genussellerid) {
		this.genussellerid = genussellerid;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public BigDecimal getStimulateConsume() {
		return stimulateConsume;
	}

	public void setStimulateConsume(BigDecimal stimulateConsume) {
		this.stimulateConsume = stimulateConsume;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public RedpacketRecord getRedpacketRecord() {
		return redpacketRecord;
	}

	public void setRedpacketRecord(RedpacketRecord redpacketRecord) {
		this.redpacketRecord = redpacketRecord;
	}

	public Integer getDayNumberIng() {
		return dayNumberIng;
	}

	public void setDayNumberIng(Integer dayNumberIng) {
		this.dayNumberIng = dayNumberIng;
	}

	public Integer getDayNumberEnd() {
		return dayNumberEnd;
	}

	public void setDayNumberEnd(Integer dayNumberEnd) {
		this.dayNumberEnd = dayNumberEnd;
	}

	public Integer getNewUserNumber() {
		return newUserNumber;
	}

	public void setNewUserNumber(Integer newUserNumber) {
		this.newUserNumber = newUserNumber;
	}

	public BigDecimal getChargeBalance() {
		return chargeBalance;
	}

	public void setChargeBalance(BigDecimal chargeBalance) {
		this.chargeBalance = chargeBalance;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public Long getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(Long redpacketId) {
		this.redpacketId = redpacketId;
	}

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

	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	public String getRedpacketName() {
		return redpacketName;
	}

	public void setRedpacketName(String redpacketName) {
		this.redpacketName = redpacketName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public Integer getDownType() {
		return downType;
	}

	public void setDownType(Integer downType) {
		this.downType = downType;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getRandomAmountMini() {
		return randomAmountMini;
	}

	public void setRandomAmountMini(BigDecimal randomAmountMini) {
		this.randomAmountMini = randomAmountMini;
	}

	public BigDecimal getRandomAmountMax() {
		return randomAmountMax;
	}

	public void setRandomAmountMax(BigDecimal randomAmountMax) {
		this.randomAmountMax = randomAmountMax;
	}

	public Integer getRedpacketNumber() {
		return redpacketNumber;
	}

	public void setRedpacketNumber(Integer redpacketNumber) {
		this.redpacketNumber = redpacketNumber;
	}

	public BigDecimal getSingleAmount() {
		return singleAmount;
	}

	public void setSingleAmount(BigDecimal singleAmount) {
		this.singleAmount = singleAmount;
	}

	public BigDecimal getNewUserAmount() {
		return newUserAmount;
	}

	public void setNewUserAmount(BigDecimal newUserAmount) {
		this.newUserAmount = newUserAmount;
	}

	public BigDecimal getOldUserAmount() {
		return oldUserAmount;
	}

	public void setOldUserAmount(BigDecimal oldUserAmount) {
		this.oldUserAmount = oldUserAmount;
	}

	public String getShareAwardsProportion() {
		return shareAwardsProportion;
	}

	public void setShareAwardsProportion(String shareAwardsProportion) {
		this.shareAwardsProportion = shareAwardsProportion;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getReceiveCondition() {
		return receiveCondition;
	}

	public void setReceiveCondition(BigDecimal receiveCondition) {
		this.receiveCondition = receiveCondition;
	}

	public BigDecimal getDeductibleAmount() {
		return deductibleAmount;
	}

	public void setDeductibleAmount(BigDecimal deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public BigDecimal getRealSpending() {
		return realSpending;
	}

	public void setRealSpending(BigDecimal realSpending) {
		this.realSpending = realSpending;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

}
