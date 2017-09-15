package com.xmniao.xmn.core.xmer.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ViewWechat {
    private String ordersn;//微信查看记录ID
    
    private Integer buyUid; //查看者用户ID

    private Integer soldUid;//被查看者用户ID

    private BigDecimal integral;//支付积分
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date viewDate;//查看时间

    public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public Integer getBuyUid() {
        return buyUid;
    }

    public void setBuyUid(Integer buyUid) {
        this.buyUid = buyUid;
    }

    public Integer getSoldUid() {
        return soldUid;
    }

    public void setSoldUid(Integer soldUid) {
        this.soldUid = soldUid;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }
}