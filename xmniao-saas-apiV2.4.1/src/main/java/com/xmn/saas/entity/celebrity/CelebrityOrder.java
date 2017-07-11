package com.xmn.saas.entity.celebrity;

import java.math.BigDecimal;
import java.util.Date;

public class CelebrityOrder {
    private Long id;

    private String orderNo;

    private Date creatTime;

    private Long sellerId;

    private Long celebrityId;

    private Date createTime;

    private Integer status;

    private BigDecimal price;

    private Date arrivalTime;

    private Integer type;

    private Integer emphasis;

    private Integer crowd;

    private String demand;

    private String detailDemand;

    private Integer payStatus;

    private Integer payType;

    private Long versionLock;

    private String thirdSerial;

    private Date payTime;

    private Celebrity celebrity;

    public Celebrity getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(Celebrity celebrity) {
        this.celebrity = celebrity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(Long celebrityId) {
        this.celebrityId = celebrityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(Integer emphasis) {
        this.emphasis = emphasis;
    }

    public Integer getCrowd() {
        return crowd;
    }

    public void setCrowd(Integer crowd) {
        this.crowd = crowd;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand == null ? null : demand.trim();
    }

    public String getDetailDemand() {
        return detailDemand;
    }

    public void setDetailDemand(String detailDemand) {
        this.detailDemand = detailDemand == null ? null : detailDemand.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getVersionLock() {
        return versionLock;
    }

    public void setVersionLock(Long versionLock) {
        this.versionLock = versionLock;
    }

    public String getThirdSerial() {
        return thirdSerial;
    }

    public void setThirdSerial(String thirdSerial) {
        this.thirdSerial = thirdSerial == null ? null : thirdSerial.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}