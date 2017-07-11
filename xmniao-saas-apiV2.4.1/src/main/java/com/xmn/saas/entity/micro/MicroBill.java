package com.xmn.saas.entity.micro;

import java.math.BigDecimal;
import java.util.Date;

public class MicroBill {
    private Integer id;

    private Integer payType;

    private String orderName;

    private String source;

    private String orderNumber;

    private BigDecimal totalAmount;

    private String authCode;

    private Integer payStatus;

    private Integer sellerid;

    private Integer uid;

    private Date createTime;
    
    
    private String clientType;
    
    private String appVersion;
    
    

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MicroBill [id=" + id + ", payType=" + payType + ", orderName=" + orderName
                + ", source=" + source + ", orderNumber=" + orderNumber + ", totalAmount="
                + totalAmount + ", authCode=" + authCode + ", payStatus=" + payStatus
                + ", sellerid=" + sellerid + ", uid=" + uid + ", createTime=" + createTime
                + ", clientType=" + clientType + ", appVersion=" + appVersion + "]";
    }
    
    
}