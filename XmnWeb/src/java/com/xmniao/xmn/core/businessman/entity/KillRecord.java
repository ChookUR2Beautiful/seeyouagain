package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class KillRecord extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -62995843434961557L;

	private Integer id;

    private Integer activityId;

    private Integer cid;

    private String serialNo;

    private Date getTime;
    
    private Date sgetTime;
    
    private Date egetTime;
    
    private Integer uid;

    private String phone;

    private Integer useStatus;

    private Integer payStatus;

    private Date payTime;

    private Date useTime;
    
    private Date suseTime;
    
    private Date euseTime;
    
    private Integer isBinding;

    private Integer isShare;

    private String number;

    private Integer version;

    private BigDecimal secKillAmount;

    private BigDecimal payAmount;
    
    private BigDecimal denomination;

    private Integer payType;

    private Integer sellerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getSecKillAmount() {
        return secKillAmount;
    }

    public void setSecKillAmount(BigDecimal secKillAmount) {
        this.secKillAmount = secKillAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	@Override
	public String toString() {
		return "KillRecord [id=" + id + ", activityId=" + activityId + ", cid="
				+ cid + ", serialNo=" + serialNo + ", getTime=" + getTime
				+ ", uid=" + uid + ", phone=" + phone + ", useStatus="
				+ useStatus + ", payStatus=" + payStatus + ", payTime="
				+ payTime + ", useTime=" + useTime + ", isBinding=" + isBinding
				+ ", isShare=" + isShare + ", number=" + number + ", version="
				+ version + ", secKillAmount=" + secKillAmount + ", payAmount="
				+ payAmount + ", denomination=" + denomination + ", payType="
				+ payType + ", sellerid=" + sellerid + "]";
	}
    
}