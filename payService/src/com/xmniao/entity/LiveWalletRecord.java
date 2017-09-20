package com.xmniao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LiveWalletRecord {
    private Integer id;

    private Integer walletId;

    private Integer liveRecordId;

    private Integer anchorId;

    private Integer rtype;

    private BigDecimal eggMoney;

    private BigDecimal qEggMoney;

    private BigDecimal hEggMoney;

    private Long coinMoney;

    private Long qCoinMoney;

    private Long hCoinMoney;

    private String remarks;

    private String description;

    private BigDecimal beansMoney;

    private BigDecimal qBeansMoney;

    private BigDecimal hBeansMoney;

    private BigDecimal sellerBeansMoney;

    private BigDecimal qSellerBeansMoney;

    private BigDecimal hSellerBeansMoney;

    private Date createTime;

    private Date updateTime;

    public LiveWalletRecord() {
        super();
    }

    public LiveWalletRecord(Integer rtype, BigDecimal eggMoney, Long coinMoney, String remarks, String description, BigDecimal beansMoney, BigDecimal sellerBeansMoney) {
        this.rtype = rtype;
        this.eggMoney = eggMoney;
        this.coinMoney = coinMoney;
        this.remarks = remarks;
        this.description = description;
        this.beansMoney = beansMoney;
        this.sellerBeansMoney = sellerBeansMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getLiveRecordId() {
        return liveRecordId;
    }

    public void setLiveRecordId(Integer liveRecordId) {
        this.liveRecordId = liveRecordId;
    }

    public Integer getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public BigDecimal getEggMoney() {
        return eggMoney;
    }

    public void setEggMoney(BigDecimal eggMoney) {
        this.eggMoney = eggMoney;
    }

    public BigDecimal getqEggMoney() {
        return qEggMoney;
    }

    public void setqEggMoney(BigDecimal qEggMoney) {
        this.qEggMoney = qEggMoney;
    }

    public BigDecimal gethEggMoney() {
        return hEggMoney;
    }

    public void sethEggMoney(BigDecimal hEggMoney) {
        this.hEggMoney = hEggMoney;
    }

    public Long getCoinMoney() {
        return coinMoney;
    }

    public void setCoinMoney(Long coinMoney) {
        this.coinMoney = coinMoney;
    }

    public Long getqCoinMoney() {
        return qCoinMoney;
    }

    public void setqCoinMoney(Long qCoinMoney) {
        this.qCoinMoney = qCoinMoney;
    }

    public Long gethCoinMoney() {
        return hCoinMoney;
    }

    public void sethCoinMoney(Long hCoinMoney) {
        this.hCoinMoney = hCoinMoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getBeansMoney() {
        return beansMoney;
    }

    public void setBeansMoney(BigDecimal beansMoney) {
        this.beansMoney = beansMoney;
    }

    public BigDecimal getqBeansMoney() {
        return qBeansMoney;
    }

    public void setqBeansMoney(BigDecimal qBeansMoney) {
        this.qBeansMoney = qBeansMoney;
    }

    public BigDecimal gethBeansMoney() {
        return hBeansMoney;
    }

    public void sethBeansMoney(BigDecimal hBeansMoney) {
        this.hBeansMoney = hBeansMoney;
    }

    public BigDecimal getSellerBeansMoney() {
        return sellerBeansMoney;
    }

    public void setSellerBeansMoney(BigDecimal sellerBeansMoney) {
        this.sellerBeansMoney = sellerBeansMoney;
    }

    public BigDecimal getqSellerBeansMoney() {
        return qSellerBeansMoney;
    }

    public void setqSellerBeansMoney(BigDecimal qSellerBeansMoney) {
        this.qSellerBeansMoney = qSellerBeansMoney;
    }

    public BigDecimal gethSellerBeansMoney() {
        return hSellerBeansMoney;
    }

    public void sethSellerBeansMoney(BigDecimal hSellerBeansMoney) {
        this.hSellerBeansMoney = hSellerBeansMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LiveWalletRecord{" +
                "id=" + id +
                ", walletId=" + walletId +
                ", liveRecordId=" + liveRecordId +
                ", anchorId=" + anchorId +
                ", rtype=" + rtype +
                ", eggMoney=" + eggMoney +
                ", qEggMoney=" + qEggMoney +
                ", hEggMoney=" + hEggMoney +
                ", coinMoney=" + coinMoney +
                ", qCoinMoney=" + qCoinMoney +
                ", hCoinMoney=" + hCoinMoney +
                ", remarks='" + remarks + '\'' +
                ", description='" + description + '\'' +
                ", beansMoney=" + beansMoney +
                ", qBeansMoney=" + qBeansMoney +
                ", hBeansMoney=" + hBeansMoney +
                ", sellerBeansMoney=" + sellerBeansMoney +
                ", qSellerBeansMoney=" + qSellerBeansMoney +
                ", hSellerBeansMoney=" + hSellerBeansMoney +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}