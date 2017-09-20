package com.xmniao.xmn.core.businessman.entity;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class SellerRedPackage extends BaseEntity{
	
	private static final long serialVersionUID = 1083176913925867141L;

	private Long id;

    private Integer sellerid;//商家id
    
    private String sellerName;//商家名称

    private Integer redpacketType;//红包类型

    private String redpacketName;//活动名称

    private Date beginDate;//开始日期
    
    private String beginDateStr;

    private Date endDate;//结束日期
    
    private String endDateStr;

    private BigDecimal totalAmount;//总金额

    private Integer status;//状态

    private Date createTime;//创建时间
    
    private Long lockVip;//绑定会员数
    
    private Long totalVip;//消费会员数

    private BigDecimal getRedpacket;//已领取金额
    
    private BigDecimal leftRedpacket;//剩余金额

    private Integer getRedpacketNumber;//已领取个数

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

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getLockVip() {
		return lockVip;
	}

	public void setLockVip(Long lockVip) {
		this.lockVip = lockVip;
	}

	public Long getTotalVip() {
		return totalVip;
	}

	public void setTotalVip(Long totalVip) {
		this.totalVip = totalVip;
	}

	public BigDecimal getGetRedpacket() {
		return getRedpacket;
	}

	public void setGetRedpacket(BigDecimal getRedpacket) {
		this.getRedpacket = getRedpacket;
	}

	public BigDecimal getLeftRedpacket() {
		return leftRedpacket;
	}

	public void setLeftRedpacket(BigDecimal leftRedpacket) {
		this.leftRedpacket = leftRedpacket;
	}

	public Integer getGetRedpacketNumber() {
		return getRedpacketNumber;
	}

	public void setGetRedpacketNumber(Integer getRedpacketNumber) {
		this.getRedpacketNumber = getRedpacketNumber;
	}
	
	
	public String getBeginDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(beginDate);
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(endDate);
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	@Override
	public String toString() {
		return "SellerRedPackage [id=" + id + ", sellerid=" + sellerid
				+ ", sellerName=" + sellerName + ", redpacketType="
				+ redpacketType + ", redpacketName=" + redpacketName
				+ ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", totalAmount=" + totalAmount + ", status=" + status
				+ ", createTime=" + createTime + ", lockVip=" + lockVip
				+ ", totalVip=" + totalVip + ", getRedpacket=" + getRedpacket
				+ ", leftRedpacket=" + leftRedpacket + ", getRedpacketNumber="
				+ getRedpacketNumber + "]";
	}

}