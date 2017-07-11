package com.xmn.saas.entity.bill;

import java.math.BigDecimal;
import java.util.Date;

public class LivePayOrder {
    private Integer id;

    private String orderNo;

    private String payNo;

    private String payCode;

    private Integer comboId;

    private Integer uid;

    private BigDecimal payment;

    private BigDecimal freeCoin;

    private BigDecimal realCoin;
    
    private BigDecimal entrySelleragio;

    private String payType;

    private Integer payState;

    private Date payTime;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    private BigDecimal totalLedgerAmount;

    private BigDecimal consumeLedger;

    private BigDecimal privilegeLedger;

    private Integer ledgerLevel;

    private BigDecimal currentConsumeLedger;

    private BigDecimal currentPrivilegeLedger;

    private BigDecimal currentDividendLedger;

    private BigDecimal currentDividendCoinLedger;

    private Integer quantity;

    private Integer hasRecomend;

    private Integer hasDividend;

    private Integer isLedger;

    private BigDecimal quota;

    private BigDecimal qQuota;

    private Integer debitcardId;

    private Integer sellerid;

    private Integer sellertype;

    private String sellername;

    private Integer objectOriented;

    private BigDecimal sellerIncome;

    private BigDecimal offsetSellerIncome;

    private Integer isToPay;

    private Integer payUid;
    
    

    public BigDecimal getEntrySelleragio() {
        return entrySelleragio;
    }

    public void setEntrySelleragio(BigDecimal entrySelleragio) {
        this.entrySelleragio = entrySelleragio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getFreeCoin() {
        return freeCoin;
    }

    public void setFreeCoin(BigDecimal freeCoin) {
        this.freeCoin = freeCoin;
    }

    public BigDecimal getRealCoin() {
        return realCoin;
    }

    public void setRealCoin(BigDecimal realCoin) {
        this.realCoin = realCoin;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public BigDecimal getTotalLedgerAmount() {
        return totalLedgerAmount;
    }

    public void setTotalLedgerAmount(BigDecimal totalLedgerAmount) {
        this.totalLedgerAmount = totalLedgerAmount;
    }

    public BigDecimal getConsumeLedger() {
        return consumeLedger;
    }

    public void setConsumeLedger(BigDecimal consumeLedger) {
        this.consumeLedger = consumeLedger;
    }

    public BigDecimal getPrivilegeLedger() {
        return privilegeLedger;
    }

    public void setPrivilegeLedger(BigDecimal privilegeLedger) {
        this.privilegeLedger = privilegeLedger;
    }

    public Integer getLedgerLevel() {
        return ledgerLevel;
    }

    public void setLedgerLevel(Integer ledgerLevel) {
        this.ledgerLevel = ledgerLevel;
    }

    public BigDecimal getCurrentConsumeLedger() {
        return currentConsumeLedger;
    }

    public void setCurrentConsumeLedger(BigDecimal currentConsumeLedger) {
        this.currentConsumeLedger = currentConsumeLedger;
    }

    public BigDecimal getCurrentPrivilegeLedger() {
        return currentPrivilegeLedger;
    }

    public void setCurrentPrivilegeLedger(BigDecimal currentPrivilegeLedger) {
        this.currentPrivilegeLedger = currentPrivilegeLedger;
    }

    public BigDecimal getCurrentDividendLedger() {
        return currentDividendLedger;
    }

    public void setCurrentDividendLedger(BigDecimal currentDividendLedger) {
        this.currentDividendLedger = currentDividendLedger;
    }

    public BigDecimal getCurrentDividendCoinLedger() {
        return currentDividendCoinLedger;
    }

    public void setCurrentDividendCoinLedger(BigDecimal currentDividendCoinLedger) {
        this.currentDividendCoinLedger = currentDividendCoinLedger;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getHasRecomend() {
        return hasRecomend;
    }

    public void setHasRecomend(Integer hasRecomend) {
        this.hasRecomend = hasRecomend;
    }

    public Integer getHasDividend() {
        return hasDividend;
    }

    public void setHasDividend(Integer hasDividend) {
        this.hasDividend = hasDividend;
    }

    public Integer getIsLedger() {
        return isLedger;
    }

    public void setIsLedger(Integer isLedger) {
        this.isLedger = isLedger;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getqQuota() {
        return qQuota;
    }

    public void setqQuota(BigDecimal qQuota) {
        this.qQuota = qQuota;
    }

    public Integer getDebitcardId() {
        return debitcardId;
    }

    public void setDebitcardId(Integer debitcardId) {
        this.debitcardId = debitcardId;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSellertype() {
        return sellertype;
    }

    public void setSellertype(Integer sellertype) {
        this.sellertype = sellertype;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(Integer objectOriented) {
        this.objectOriented = objectOriented;
    }

    public BigDecimal getSellerIncome() {
        return sellerIncome;
    }

    public void setSellerIncome(BigDecimal sellerIncome) {
        this.sellerIncome = sellerIncome;
    }

    public BigDecimal getOffsetSellerIncome() {
        return offsetSellerIncome;
    }

    public void setOffsetSellerIncome(BigDecimal offsetSellerIncome) {
        this.offsetSellerIncome = offsetSellerIncome;
    }

    public Integer getIsToPay() {
        return isToPay;
    }

    public void setIsToPay(Integer isToPay) {
        this.isToPay = isToPay;
    }

    public Integer getPayUid() {
        return payUid;
    }

    public void setPayUid(Integer payUid) {
        this.payUid = payUid;
    }
}