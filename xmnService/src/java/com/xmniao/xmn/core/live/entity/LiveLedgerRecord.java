package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LiveLedgerRecord implements Serializable{
	
	/**
	 */
	private static final long serialVersionUID = 4797550852127720883L;

	//'分账记录主键',
	private long id; 
	
	// 'uid',
	private long uid;
	
	//'uid在分账时所处的分账角色 1 自身会员 2 上线会员 3上上级会员',
	private int uidRole; 
	
	//'分账鸟币(余额)面额',
	private BigDecimal ledgerAmount; 
	
	//'实际领取分账金额(可能比ledger_amount小或=0)',
	private BigDecimal realLedgerAmount; 
	
	//'分账到账状态 0 初始状态，具备分账资格 1 分账中 2 已到账 3 到账失败',
	private int status; 
	
	//'插入记录时间',
	private Date createDate; 
	
	//'更新记录时间',
	private Date updateDate; 
	
	//'分账记录来源: 1 打赏 2 推荐 3 红包',
	private int ledgerSource; 
	
	// '分账描述信息',
	private String ledgerSourceInfo;
	
	// '推荐分账时，会员充值订单',
	private String orderNo;
	
	//'打赏分账时，会员打赏id',
	private long givedGiftId; 
	
	// '红包分账时，红包时期"yyyy-MM-dd"',
	private String redpacketRocordDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getUidRole() {
		return uidRole;
	}

	public void setUidRole(int uidRole) {
		this.uidRole = uidRole;
	}

	public BigDecimal getLedgerAmount() {
		return ledgerAmount;
	}

	public void setLedgerAmount(BigDecimal ledgerAmount) {
		this.ledgerAmount = ledgerAmount;
	}

	public BigDecimal getRealLedgerAmount() {
		return realLedgerAmount;
	}

	public void setRealLedgerAmount(BigDecimal realLedgerAmount) {
		this.realLedgerAmount = realLedgerAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getLedgerSource() {
		return ledgerSource;
	}

	public void setLedgerSource(int ledgerSource) {
		this.ledgerSource = ledgerSource;
	}

	public String getLedgerSourceInfo() {
		return ledgerSourceInfo;
	}

	public void setLedgerSourceInfo(String ledgerSourceInfo) {
		this.ledgerSourceInfo = ledgerSourceInfo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getGivedGiftId() {
		return givedGiftId;
	}

	public void setGivedGiftId(long givedGiftId) {
		this.givedGiftId = givedGiftId;
	}

	public String getRedpacketRocordDate() {
		return redpacketRocordDate;
	}

	public void setRedpacketRocordDate(String redpacketRocordDate) {
		this.redpacketRocordDate = redpacketRocordDate;
	}

	@Override
	public String toString() {
		return "LiveLedgerRecord [id=" + id + ", uid=" + uid + ", uidRole="
				+ uidRole + ", ledgerAmount=" + ledgerAmount
				+ ", realLedgerAmount=" + realLedgerAmount + ", status="
				+ status + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", ledgerSource=" + ledgerSource
				+ ", ledgerSourceInfo=" + ledgerSourceInfo + ", orderNo="
				+ orderNo + ", givedGiftId=" + givedGiftId
				+ ", redpacketRocordDate=" + redpacketRocordDate + "]";
	}
	

	
	
	
}
