package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TLiveSellerLedger extends BaseEntity{
	private static final long serialVersionUID = -1437874446263949568L;

	private Integer id;

    private Integer sellerid;

    private Integer ledgerStyle;

    private Integer ledgerMode;

    private Double ledgerRatio;

    private Date startDate;

    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getLedgerStyle() {
        return ledgerStyle;
    }

    public void setLedgerStyle(Integer ledgerStyle) {
        this.ledgerStyle = ledgerStyle;
    }

    public Integer getLedgerMode() {
        return ledgerMode;
    }

    public void setLedgerMode(Integer ledgerMode) {
        this.ledgerMode = ledgerMode;
    }

    public Double getLedgerRatio() {
        return ledgerRatio;
    }

    public void setLedgerRatio(Double ledgerRatio) {
        this.ledgerRatio = ledgerRatio;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}