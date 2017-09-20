package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class UserCoupon extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 471603938878787463L;

	private Integer cuid;

    private Integer cid;

    private String serialNo;//序列码

    private BigDecimal denomination;

    private Integer getWay;

    private Date getTime;//领取时间

    private Integer uid;//用户id

    private Integer sellerid;

    private String phone;//手机号

    private Integer useStatus;//使用状态

    private BigDecimal useMoney;

    private Date startDate;
    
    private Date sDate;
    
    private Date eDate;

    private Date endDate;

    private Date lockTime;

    private Date useTime;

    private Integer isShare;

    private Long bid;//订单号

    private Integer isVerify;//是否核销

    private Integer activityId;

    private Integer activityType;

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public Integer getGetWay() {
        return getWay;
    }

    public void setGetWay(Integer getWay) {
        this.getWay = getWay;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
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

	@Override
	public String toString() {
		return "UserCoupon [cuid=" + cuid + ", cid=" + cid + ", serialNo="
				+ serialNo + ", denomination=" + denomination + ", getWay="
				+ getWay + ", getTime=" + getTime + ", uid=" + uid
				+ ", sellerid=" + sellerid + ", phone=" + phone
				+ ", useStatus=" + useStatus + ", useMoney=" + useMoney
				+ ", startDate=" + startDate + ", sDate=" + sDate + ", eDate="
				+ eDate + ", endDate=" + endDate + ", lockTime=" + lockTime
				+ ", useTime=" + useTime + ", isShare=" + isShare + ", bid="
				+ bid + ", isVerify=" + isVerify + ", activityId=" + activityId
				+ ", activityType=" + activityType + "]";
	}
    
}