package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

public class LivePrivilege {
    private Integer id;

    private String orderNo;

    private Integer objectOriented;

    private Integer uid;

    private BigDecimal payment;

    private Date createTime;

    private Date updateTime;

    private BigDecimal totalLedgerAmount;

    private BigDecimal consumeLedger;

    private BigDecimal privilegeLedger;

    private BigDecimal currentConsumeLedger;

    private BigDecimal currentPrivilegeLedger;

    private BigDecimal currentDividendLedger;

    private BigDecimal currentDividendCoinLedger;

    private Integer ledgerLevel;

    private Integer hasRecomend;

    private Integer hasDividend;

    private Integer debitcardId;
    
    private BigDecimal quota;
    
    private Integer version;

    private Integer periodExcitation;
    
    private Integer curPeriodExcitation;
    
    private BigDecimal baseAmount;
    
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

    public Integer getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(Integer objectOriented) {
        this.objectOriented = objectOriented;
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

    public Integer getLedgerLevel() {
        return ledgerLevel;
    }

    public void setLedgerLevel(Integer ledgerLevel) {
        this.ledgerLevel = ledgerLevel;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public Integer getDebitcardId() {
		return debitcardId;
	}

	public void setDebitcardId(Integer debitcardId) {
		this.debitcardId = debitcardId;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public Integer getPeriodExcitation() {
		return periodExcitation;
	}

	public void setPeriodExcitation(Integer periodExcitation) {
		this.periodExcitation = periodExcitation;
	}

	public Integer getCurPeriodExcitation() {
		return curPeriodExcitation;
	}

	public void setCurPeriodExcitation(Integer curPeriodExcitation) {
		this.curPeriodExcitation = curPeriodExcitation;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}
   
    
}