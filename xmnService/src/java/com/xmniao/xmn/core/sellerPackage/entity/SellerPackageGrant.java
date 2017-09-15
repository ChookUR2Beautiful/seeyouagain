package com.xmniao.xmn.core.sellerPackage.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerPackageGrant    
* @Description:用户套餐信息实体   
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:42:19
 */
public class SellerPackageGrant {
    private Integer id;

    private String serial;

    private Integer pid;

    private BigDecimal denomination;

    private Date useStartTime;

    private Date useEndTime;

    private Date forbidStartTime;

    private Date forbidEndTime;

    private Date getTime;

    private Integer uid;

    private String phone;

    private Integer userStatus;

    private Date userTime;

    private String orderNo;

    private Integer sellerid;

    private Long bid;
    
    private Integer isTimeOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public Date getForbidStartTime() {
        return forbidStartTime;
    }

    public void setForbidStartTime(Date forbidStartTime) {
        this.forbidStartTime = forbidStartTime;
    }

    public Date getForbidEndTime() {
        return forbidEndTime;
    }

    public void setForbidEndTime(Date forbidEndTime) {
        this.forbidEndTime = forbidEndTime;
    }

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

    public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

	public Integer getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(Integer isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	@Override
	public String toString() {
		return "SellerPackageGrant [id=" + id + ", serial=" + serial + ", pid="
				+ pid + ", denomination=" + denomination + ", useStartTime="
				+ useStartTime + ", useEndTime=" + useEndTime
				+ ", forbidStartTime=" + forbidStartTime + ", forbidEndTime="
				+ forbidEndTime + ", getTime=" + getTime + ", uid=" + uid
				+ ", phone=" + phone + ", userStatus=" + userStatus
				+ ", userTime=" + userTime + ", orderNo=" + orderNo
				+ ", sellerid=" + sellerid + ", bid=" + bid + ", isTimeOut="
				+ isTimeOut + "]";
	}

}