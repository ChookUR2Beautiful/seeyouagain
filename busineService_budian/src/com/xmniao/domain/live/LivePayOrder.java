package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LivePayOrder
 * 
 * 类描述： 直播充值订单
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月19日 下午7:35:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
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

    private String receipt;
    
    private BigDecimal currentConsumeLedger;
    
    private BigDecimal currentPrivilegeLedger;
    
    private BigDecimal currentDividendLedger;
    
    private BigDecimal currentDividendCoinLedger;
        
    private Integer hasDividend;	//该单是否有分红资格
    
    private Integer hasRecomend;	//该单是否有推荐资格
    
    private Integer quantity;	//套数
    
    private Integer isLedger;
    
    private BigDecimal consumeAmount;	//打赏金额
    
    private Integer objectOriented;	//充值渠道 1 VIP 2 商家 3 直销
    
    private Integer sellerid;
    
    private BigDecimal quota;
    
    private BigDecimal qQuota;
    
    private Integer debitcardId;
    
    private Integer sellertype;
    
    private String sellername;
    
    private BigDecimal sellerIncome;
    
    private BigDecimal offsetSellerIncome;
    
    private BigDecimal entrySelleragio;
    
    private Integer entrySellerid;
    
    private String uidRelationChain;
    
    private String excitationProject;
    
    private String isVirtual;	//V客报单，只升级别，不给上级奖励以及不给该单充值鸟豆
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

//    public BigDecimal getTotalLedgerAmount() {
//		return totalLedgerAmount;
//	}

	public void setTotalLedgerAmount(BigDecimal totalLedgerAmount) {
		this.totalLedgerAmount = totalLedgerAmount;
	}

//    public BigDecimal getConsumeLedger() {
//        return consumeLedger;
//    }

    public void setConsumeLedger(BigDecimal consumeLedger) {
        this.consumeLedger = consumeLedger;
    }

//    public BigDecimal getPrivilegeLedger() {
//        return privilegeLedger;
//    }

    public void setPrivilegeLedger(BigDecimal privilegeLedger) {
        this.privilegeLedger = privilegeLedger;
    }

    public Integer getLedgerLevel() {
        return ledgerLevel;
    }

    public void setLedgerLevel(Integer ledgerLevel) {
        this.ledgerLevel = ledgerLevel;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt == null ? null : receipt.trim();
    }

    
//	public BigDecimal getCurrentConsumeLedger() {
//		return currentConsumeLedger;
//	}

	public void setCurrentConsumeLedger(BigDecimal currentConsumeLedger) {
		this.currentConsumeLedger = currentConsumeLedger;
	}

//	public BigDecimal getCurrentPrivilegeLedger() {
//		return currentPrivilegeLedger;
//	}

	public void setCurrentPrivilegeLedger(BigDecimal currentPrivilegeLedger) {
		this.currentPrivilegeLedger = currentPrivilegeLedger;
	}

//	public Integer getHasDividend() {
//		return hasDividend;
//	}

	public void setHasDividend(Integer hasDividend) {
		this.hasDividend = hasDividend;
	}

//	public Integer getHasRecomend() {
//		return hasRecomend;
//	}

	public void setHasRecomend(Integer hasRecomend) {
		this.hasRecomend = hasRecomend;
	}

	public Integer getQuantity() {
		return quantity==null?1:quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	public BigDecimal getCurrentDividendLedger() {
//		return currentDividendLedger;
//	}

	public void setCurrentDividendLedger(BigDecimal currentDividendLedger) {
		this.currentDividendLedger = currentDividendLedger;
	}

	
//	public Integer getIsLedger() {
//		return isLedger;
//	}

	public void setIsLedger(Integer isLedger) {
		this.isLedger = isLedger;
	}

//	public BigDecimal getConsumeAmount() {
//		return consumeAmount;
//	}

	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	
//	public BigDecimal getCurrentDividendCoinLedger() {
//		return currentDividendCoinLedger;
//	}

	public void setCurrentDividendCoinLedger(BigDecimal currentDividendCoinLedger) {
		this.currentDividendCoinLedger = currentDividendCoinLedger;
	}

	public Integer getObjectOriented() {
		return objectOriented==null?1:objectOriented;
	}

	public void setObjectOriented(Integer objectOriented) {
		this.objectOriented = objectOriented;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
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
		this.sellername = sellername;
	}

//	public BigDecimal getqQuota() {
//		return qQuota;
//	}
//
//	public void setqQuota(BigDecimal qQuota) {
//		this.qQuota = qQuota;
//	}
//
//	public BigDecimal getSellerIncome() {
//		return sellerIncome;
//	}
//
//	public void setSellerIncome(BigDecimal sellerIncome) {
//		this.sellerIncome = sellerIncome;
//	}
//
//	public BigDecimal getOffsetSellerIncome() {
//		return offsetSellerIncome;
//	}

//	public void setOffsetSellerIncome(BigDecimal offsetSellerIncome) {
//		this.offsetSellerIncome = offsetSellerIncome;
//	}

	
	public Integer getDebitcardId() {
		return debitcardId;
	}

	public void setDebitcardId(Integer debitcardId) {
		this.debitcardId = debitcardId;
	}

	
	public BigDecimal getEntrySelleragio() {
		return entrySelleragio;
	}

	public void setEntrySelleragio(BigDecimal entrySelleragio) {
		this.entrySelleragio = entrySelleragio;
	}

	public Integer getEntrySellerid() {
		return entrySellerid;
	}

	public void setEntrySellerid(Integer entrySellerid) {
		this.entrySellerid = entrySellerid;
	}

	public String getUidRelationChain() {
		return uidRelationChain;
	}

	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}

	
	public String getExcitationProject() {
		return excitationProject;
	}

	public void setExcitationProject(String excitationProject) {
		this.excitationProject = excitationProject;
	}

	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	@Override
	public String toString() {
		return "LivePayOrder [id=" + id + ", orderNo=" + orderNo + ", payNo="
				+ payNo + ", payCode=" + payCode + ", comboId=" + comboId
				+ ", uid=" + uid + ", payment=" + payment + ", freeCoin="
				+ freeCoin + ", realCoin=" + realCoin + ", payType=" + payType
				+ ", payState=" + payState + ", payTime=" + payTime
				+ ", version=" + version + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", totalLedgerAmount="
				+ totalLedgerAmount + ", consumeLedger=" + consumeLedger
				+ ", privilegeLedger=" + privilegeLedger + ", ledgerLevel="
				+ ledgerLevel + ", receipt=" + receipt
				+ ", currentConsumeLedger=" + currentConsumeLedger
				+ ", currentPrivilegeLedger=" + currentPrivilegeLedger
				+ ", currentDividendLedger=" + currentDividendLedger
				+ ", currentDividendCoinLedger=" + currentDividendCoinLedger
				+ ", hasDividend=" + hasDividend + ", hasRecomend="
				+ hasRecomend + ", quantity=" + quantity + ", isLedger="
				+ isLedger + ", consumeAmount=" + consumeAmount
				+ ", objectOriented=" + objectOriented + ", sellerid="
				+ sellerid + ", quota=" + quota + ", qQuota=" + qQuota
				+ ", debitcardId=" + debitcardId + ", sellertype=" + sellertype
				+ ", sellername=" + sellername + ", sellerIncome="
				+ sellerIncome + ", offsetSellerIncome=" + offsetSellerIncome
				+ ", entrySelleragio=" + entrySelleragio + ", entrySellerid="
				+ entrySellerid + "]";
	}


}