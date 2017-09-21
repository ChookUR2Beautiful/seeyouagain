package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveDividendsLedgerDetailRecord
 * 
 * 类描述：每日红包发放详细归属实体类 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月2日 下午2:27:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveDividendsLedgerDetailRecord {
    private Integer id;

    private Integer uid;
    
    private Integer objectOriented;
    
    private Integer pid;

    private BigDecimal ledgerAmount;

    private BigDecimal ledgerCoin;

    private BigDecimal ledgerCommonCoin;
    
    private BigDecimal ledgerSellerCoin;
    
    private BigDecimal realLedgerAmount;
    
    private BigDecimal realLedgerCoin;
    
    private BigDecimal realLedgerCommonCoin;
    
    private BigDecimal realLedgerSellerCoin;
    
    private String orderNo;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getLedgerAmount() {
        return ledgerAmount;
    }

    public void setLedgerAmount(BigDecimal ledgerAmount) {
        this.ledgerAmount = ledgerAmount;
    }

    public BigDecimal getLedgerCoin() {
        return ledgerCoin;
    }

    public void setLedgerCoin(BigDecimal ledgerCoin) {
        this.ledgerCoin = ledgerCoin;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
	public LiveDividendsLedgerDetailRecord() {
		super();
	}

	public BigDecimal getLedgerCommonCoin() {
		return ledgerCommonCoin;
	}

	public void setLedgerCommonCoin(BigDecimal ledgerCommonCoin) {
		this.ledgerCommonCoin = ledgerCommonCoin;
	}

	public BigDecimal getLedgerSellerCoin() {
		return ledgerSellerCoin;
	}

	public void setLedgerSellerCoin(BigDecimal ledgerSellerCoin) {
		this.ledgerSellerCoin = ledgerSellerCoin;
	}

	public BigDecimal getRealLedgerAmount() {
		return realLedgerAmount;
	}

	public void setRealLedgerAmount(BigDecimal realLedgerAmount) {
		this.realLedgerAmount = realLedgerAmount;
	}

	public BigDecimal getRealLedgerCoin() {
		return realLedgerCoin;
	}

	public void setRealLedgerCoin(BigDecimal realLedgerCoin) {
		this.realLedgerCoin = realLedgerCoin;
	}

	public BigDecimal getRealLedgerCommonCoin() {
		return realLedgerCommonCoin;
	}

	public void setRealLedgerCommonCoin(BigDecimal realLedgerCommonCoin) {
		this.realLedgerCommonCoin = realLedgerCommonCoin;
	}

	public BigDecimal getRealLedgerSellerCoin() {
		return realLedgerSellerCoin;
	}

	public void setRealLedgerSellerCoin(BigDecimal realLedgerSellerCoin) {
		this.realLedgerSellerCoin = realLedgerSellerCoin;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getObjectOriented() {
		return objectOriented;
	}

	public void setObjectOriented(Integer objectOriented) {
		this.objectOriented = objectOriented;
	}

	public LiveDividendsLedgerDetailRecord(Integer pid,
			BigDecimal ledgerAmount, BigDecimal ledgerCoin,BigDecimal ledgerCommonCoin, BigDecimal ledgerSellerCoin, 
			String orderNo,Integer uid,Integer objectOriented) {
		super();
		this.pid = pid;
		this.ledgerAmount = ledgerAmount;
		this.ledgerCoin = ledgerCoin;
		this.ledgerCommonCoin= ledgerCommonCoin;
		this.ledgerSellerCoin = ledgerSellerCoin;
		this.orderNo = orderNo;
		this.objectOriented=objectOriented;
		this.uid=uid;
	}
    
}