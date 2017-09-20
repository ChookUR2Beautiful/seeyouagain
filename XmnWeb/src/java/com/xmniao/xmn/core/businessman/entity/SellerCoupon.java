package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class SellerCoupon extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5805073813512427795L;

	private Integer cid;

    private String cname;//优惠券名称

    private BigDecimal denomination;//面额

    private Date startDate;//开始时间

    private Date endDate;//结束时间
    
    private Date sDate;
    
    private Date eDate;

    private BigDecimal limitAmount;

    private BigDecimal conditions;//使用条件

    private Integer sellerid;//商户id
    
    private String sellerName;//商家名称

    private Integer useNum;

    private Integer couponType;

    private Integer sendType;

    private Integer sendNum;//发放数量

    private Integer maximum;

    private BigDecimal minM;

    private BigDecimal maxM;

    private BigDecimal amount;

    private String sendObject;

    private Integer sendStatus;

    private Integer awardCondition;//领取条件

    private Integer awardNumber;//领取个数

    private Integer useNumber;//使用个数

    private Integer limitNumber;

    private Integer views;

    private Integer status;//状态

    private String description;

    private Date createDate;

    private Integer activityId;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public BigDecimal getConditions() {
        return conditions;
    }

    public void setConditions(BigDecimal conditions) {
        this.conditions = conditions;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public BigDecimal getMinM() {
        return minM;
    }

    public void setMinM(BigDecimal minM) {
        this.minM = minM;
    }

    public BigDecimal getMaxM() {
        return maxM;
    }

    public void setMaxM(BigDecimal maxM) {
        this.maxM = maxM;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject == null ? null : sendObject.trim();
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getAwardCondition() {
        return awardCondition;
    }

    public void setAwardCondition(Integer awardCondition) {
        this.awardCondition = awardCondition;
    }

    public Integer getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(Integer awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Override
	public String toString() {
		return "SellerCoupon [cid=" + cid + ", cname=" + cname
				+ ", denomination=" + denomination + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", sDate=" + sDate + ", eDate="
				+ eDate + ", limitAmount=" + limitAmount + ", conditions="
				+ conditions + ", sellerid=" + sellerid + ", sellerName="
				+ sellerName + ", useNum=" + useNum + ", couponType="
				+ couponType + ", sendType=" + sendType + ", sendNum="
				+ sendNum + ", maximum=" + maximum + ", minM=" + minM
				+ ", maxM=" + maxM + ", amount=" + amount + ", sendObject="
				+ sendObject + ", sendStatus=" + sendStatus
				+ ", awardCondition=" + awardCondition + ", awardNumber="
				+ awardNumber + ", useNumber=" + useNumber + ", limitNumber="
				+ limitNumber + ", views=" + views + ", status=" + status
				+ ", description=" + description + ", createDate=" + createDate
				+ ", activityId=" + activityId + "]";
	}
    
    
}