package com.xmn.saas.entity.redpacket;

import java.math.BigDecimal;
import java.util.Date;

public class Redpacket {

	private Long id;

	private Integer sellerid;

	private Integer redpacketType;

	private Integer amountType;

	private String redpacketName;

	private Date beginDate;

	private Date endDate;

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

	private Integer payStatus;

	private Date createTime;

	private Integer payType;

	private BigDecimal zbalance;

	private BigDecimal amount;

	private BigDecimal balance;

	private BigDecimal commision;

	private BigDecimal integral;

	private BigDecimal profit;

	private String payid;

	private String orderNo;

	private String thirdSerial;

	private Long versionLock; // 乐观锁版本号
	
	private BigDecimal refundAmount;

	private Integer newUserNumber;

	/*
	 * 奖品类型 优惠券类型(3.抵用券 4.赠品券 5.红包)
	 */
	private Integer couponType = 5;
	
	private BigDecimal getRedpacket;	//已领取红包金额
	
	private Integer getRedpacketNumber;  //已领取红包个数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getNewUserNumber() {
		return newUserNumber;
	}

	public void setNewUserNumber(Integer newUserNumber) {
		this.newUserNumber = newUserNumber;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getBeginTime() {
		return beginTime;
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

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getZbalance() {
		return zbalance;
	}

	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getCommision() {
		return commision;
	}

	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getThirdSerial() {
		return thirdSerial;
	}

	public void setThirdSerial(String thirdSerial) {
		this.thirdSerial = thirdSerial;
	}

	public Long getVersionLock() {
		return versionLock;
	}

	public void setVersionLock(Long versionLock) {
		this.versionLock = versionLock;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getGetRedpacket() {
		return getRedpacket;
	}

	public void setGetRedpacket(BigDecimal getRedpacket) {
		this.getRedpacket = getRedpacket;
	}

	public Integer getGetRedpacketNumber() {
		return getRedpacketNumber;
	}

	public void setGetRedpacketNumber(Integer getRedpacketNumber) {
		this.getRedpacketNumber = getRedpacketNumber;
	}
	
	

}