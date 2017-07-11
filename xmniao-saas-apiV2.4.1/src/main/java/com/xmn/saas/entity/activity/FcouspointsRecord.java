package com.xmn.saas.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

public class FcouspointsRecord extends ActivityRecord{
    private Integer id;

    private Integer activityId;

    private Integer sellerid;

    private Long bid;

    private Integer uid;

    private String phone;

    private Integer givePoints;

    private Integer recordType=1;	//记录类型   1:获点记录  2:兑换记录
    
    private String payType; //支付类型
    
    private BigDecimal payment;  //支付金额
    
    private String number;	//订单号
    
    private Date getTime;
    
    private Integer bfirst; //是否为首单，0不是首单，1是首单
    
    public Integer getBfirst() {
		return bfirst;
	}

	public void setBfirst(Integer bfirst) {
		this.bfirst = bfirst;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getPayType() {
		if("1000001".equals(payType)){
			return "支付宝支付";
		}else if("1000000".equals(payType)){
			return "余额支付";
		}else if("1000003".equals(payType)){
			return "微信支付";
		}
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

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

    public Integer getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(Integer givePoints) {
        this.givePoints = givePoints;
    }

}