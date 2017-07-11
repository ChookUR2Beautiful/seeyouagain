package com.xmn.saas.controller.h5.redpacket.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.redpacket.Redpacket;

/**
 * 红包请求参数Vo
 * 
 * @ClassName: RedpacketSaveCommonRequest
 * @Description: TODO
 * @author dengqiang
 * @date 2016年9月26日 下午8:11:04
 *
 */
public class RedpacketSaveCommonRequest extends Request {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "sellerid require")
	private Integer sellerid;

	@NotNull(message = "redpacketType require")
	
	private Integer redpacketType;

	@NotNull(message = "redpacketName require")
	private String redpacketName;

	@NotNull(message = "beginDate require")
	private String beginDate;

	@NotNull(message = "endDate require")
	private String endDate;

	@NotNull(message = "beginTime require")
	private String beginTime;

	@NotNull(message = "endTime require")
	private String endTime;

	@NotNull(message = "totalAmount require")
	private BigDecimal totalAmount;

	
	private BigDecimal randomAmountMini;

	private BigDecimal randomAmountMax;

	private BigDecimal singleAmount;

	private Integer redpacketNumber;
	
	private BigDecimal receiveCondition;

	//@Pattern(regexp = "^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,5}|180\\.0{1,5})$", message = "longitude error")
	private String longitude;

    //@Pattern(regexp = "^[\\-\\+]?([0-8]?\\d{1}\\.\\d{1,5}|90\\.0{1,5})$", message = "latitude error")
	private String latitude;

	private Integer amountType;
	
	private BigDecimal newUserAmount;

	private BigDecimal oldUserAmount;

	private String shareAwardsProportion;
	

	private String paramUrl;

	private String address;

	private BigDecimal deductibleAmount;

	private Long views;

	private BigDecimal realSpending;

	private Integer status;

	private Integer payStatus;

	private Date createTime;

	private Integer payType;

	private BigDecimal profit;

	private BigDecimal balance;

	private BigDecimal commision;

	private BigDecimal zbalance;

	private BigDecimal integral;

	public Redpacket convertToRedpacket() {
		Redpacket packet = new Redpacket();
		try {
			BeanUtils.copyProperties(packet, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packet;
	}

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

	public void setRedpacketType(Integer redpacketType) {
		this.redpacketType = redpacketType;
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

	public BigDecimal getSingleAmount() {
		return singleAmount;
	}

	public void setSingleAmount(BigDecimal singleAmount) {
		this.singleAmount = singleAmount;
	}

	public Integer getRedpacketNumber() {
		return redpacketNumber;
	}

	public void setRedpacketNumber(Integer redpacketNumber) {
		this.redpacketNumber = redpacketNumber;
	}

	public BigDecimal getReceiveCondition() {
		return receiveCondition;
	}

	public void setReceiveCondition(BigDecimal receiveCondition) {
		this.receiveCondition = receiveCondition;
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

	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
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

	public BigDecimal getZbalance() {
		return zbalance;
	}

	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	

}
