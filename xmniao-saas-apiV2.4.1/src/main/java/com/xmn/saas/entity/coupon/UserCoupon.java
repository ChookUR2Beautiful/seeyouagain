package com.xmn.saas.entity.coupon;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 项目名称：xmniao-saas-api    
 * 类描述：   用户优惠券信息(用户领取记录表)
 * 创建人：huangk   
 * 创建时间：2016年9月27日 下午3:50:15
 */
public class UserCoupon {
    private Integer cuid;//主键

    private Integer cid;//优惠券id

    private String serialNo;//优惠券序列号

    private BigDecimal denomination;//面额

    private Integer getWay;//获取方式

    private Date getTime;//获取时间

    private Integer uid;//用户id
    
    private Integer sellerId;//绑定的商户id

    private String phone;//手机号

    private Integer useStatus;//使用状态

    private BigDecimal useMoney;//已使用金额

    private Date startDate;//有效开始时间

    private Date endDate;//有效结束时间

    private Date lockTime;//锁定时间

    private Date useTime;//使用时间
    
    private Integer isShare;//是否分享
    
    private Integer isVerify;//是否核销

    private Long bid;//消费订单号
    
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

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	@Override
    public String toString(){
    	return "UserCoupon:{"+
    			"cuid="+cuid+
    			",uid="+uid+
    			",sellerId="+sellerId+
    			",cid="+cid+
    			",serial_no="+serialNo+
    			",startDate="+startDate+
    			",endDate="+endDate+
    			",denomination="+denomination+
    			",phone="+phone+
    			",useStatus="+useStatus+
    			",useMoney="+useMoney+
    			",startDate="+startDate+
    			",endDate="+endDate+
    			",isShare="+isShare+
    			",isVerify="+isVerify+
    			",bid="+bid+
    			"}";
    }
}