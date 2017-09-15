package com.xmniao.xmn.core.seller.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 直播分账设置表
 * */
public class LiveSellerLedgerInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1518717002329532518L;

	private Integer id;
	//'商家ID'
	private Integer sellerid;
	//'分账方式 1 自动 2 手动'
	private Integer ledgerStyle;
	//'分账模式 1 全部金额分账 2 粉丝券金额分账'
	private Integer ledgerMode;
	//'分账比例 如:0.6000',
	private BigDecimal ledgerRatio;
	//'直播分账开始时间(不填开始结束时间则全部通用)',
	private Date startDate;
	//'直播分账结束时间'
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
	public BigDecimal getLedgerRatio() {
		return ledgerRatio;
	}
	public void setLedgerRatio(BigDecimal ledgerRatio) {
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
	@Override
	public String toString() {
		return "LiveSellerLedgerInfo [id=" + id + ", sellerid=" + sellerid
				+ ", ledgerStyle=" + ledgerStyle + ", ledgerMode=" + ledgerMode
				+ ", ledgerRatio=" + ledgerRatio + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	
	
}
