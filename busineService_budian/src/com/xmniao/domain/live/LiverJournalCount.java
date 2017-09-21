package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

public class LiverJournalCount {
    private Integer id;

    private Integer uid;

    private BigDecimal orderAmount;

    private BigDecimal comsumAmount;

    private BigDecimal comsumLedger;

    private BigDecimal privilegeLedger;
    
    private BigDecimal expectedPriviledgeLedger;

    private BigDecimal currentConsumeLedger;

    private BigDecimal currentDividendLedger;
    
    private BigDecimal currentDividendCoinLedger;

    private BigDecimal currentRecommendLedger;

    private Date updateDate;

    private int version;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getComsumAmount() {
        return comsumAmount;
    }

    public void setComsumAmount(BigDecimal comsumAmount) {
        this.comsumAmount = comsumAmount;
    }

    public BigDecimal getComsumLedger() {
        return comsumLedger;
    }

    public void setComsumLedger(BigDecimal comsumLedger) {
        this.comsumLedger = comsumLedger;
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

    public BigDecimal getCurrentDividendLedger() {
        return currentDividendLedger;
    }

    public void setCurrentDividendLedger(BigDecimal currentDividendLedger) {
        this.currentDividendLedger = currentDividendLedger;
    }

    public BigDecimal getCurrentRecommendLedger() {
        return currentRecommendLedger;
    }

    public void setCurrentRecommendLedger(BigDecimal currentRecommendLedger) {
        this.currentRecommendLedger = currentRecommendLedger;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public BigDecimal getExpectedPriviledgeLedger() {
		return expectedPriviledgeLedger;
	}

	public void setExpectedPriviledgeLedger(BigDecimal expectedPriviledgeLedger) {
		this.expectedPriviledgeLedger = expectedPriviledgeLedger;
	}
	
	

	public BigDecimal getCurrentDividendCoinLedger() {
		return currentDividendCoinLedger;
	}

	public void setCurrentDividendCoinLedger(BigDecimal currentDividendCoinLedger) {
		this.currentDividendCoinLedger = currentDividendCoinLedger;
	}

	@Override
	public String toString() {
		return "LiverJournalCount [id=" + id + ", uid=" + uid
				+ ", orderAmount=" + orderAmount + ", comsumAmount="
				+ comsumAmount + ", comsumLedger=" + comsumLedger
				+ ", privilegeLedger=" + privilegeLedger
				+ ", expectedPriviledgeLedger=" + expectedPriviledgeLedger
				+ ", currentConsumeLedger=" + currentConsumeLedger
				+ ", currentDividendLedger=" + currentDividendLedger
				+ ", currentDividendCoinLedger=" + currentDividendCoinLedger
				+ ", currentRecommendLedger=" + currentRecommendLedger
				+ ", updateDate=" + updateDate + ", version=" + version + "]";
	}

}