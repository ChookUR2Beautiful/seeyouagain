package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveLedgerRecord
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-31 上午11:17:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveLedgerRecord extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6207834262846080427L;

	private Integer id;//分账记录主键

    private Integer uid;//给uid进行分账
    
    private String nname;//会员昵称
    
    private String phone;//会员手机

    private Integer uidRole;//uid在分账时所处的分账角色 	1 自身会员 2 上线会员 3上上级会员

    private BigDecimal ledgerAmount;//分账鸟币(余额)面额

    private BigDecimal realLedgerAmount;//实际领取分账金额(可能比ledger_amount小或=0)

    private Integer status;//分账到账状态 0 初始状态，具备分账资格 1 分账中 2 已到账 3 到账失败
    
    private String statusStr;//分账到账状态

    private Date createDate;//插入记录时间

    private Date updateDate;//更新记录时间

    private Integer ledgerSource;//分账记录来源: 1 打赏 2 推荐 3 红包

    private String ledgerSourceInfo;//分账描述信息

    private String orderNo;//推荐分账时，会员充值订单

    private Integer givedGiftId;//打赏分账时，会员打赏id

    private String redpacketRocordDate;//红包分账时，红包时期"yyyy-MM-dd"
    
    private Integer ledgerUid;//推荐分账时，下线的uid
    
    private String startTime;//领取开始时间
    
    private String endTime;//领取结束时间

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
    
    

    /**
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUidRole() {
        return uidRole;
    }

    public void setUidRole(Integer uidRole) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

    /**
     * 分账到账状态 0 初始状态，具备分账资格 1 分账中 2 已到账 3 到账失败
	 * @return the statusStr
	 */
	public String getStatusStr() {
		if(status==null){
			return null;
		}
		switch (status) {
		case 0:
			statusStr="初始状态";
			break;
		case 1:
			statusStr="分账中";
			break;
		case 2:
			statusStr="已到账";
			break;
		case 3:
			statusStr="到账失败";
			break;

		default:
			break;
		}
		return statusStr;
	}

	/**
	 * @param statusStr the statusStr to set
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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

    public Integer getLedgerSource() {
        return ledgerSource;
    }

    public void setLedgerSource(Integer ledgerSource) {
        this.ledgerSource = ledgerSource;
    }

    public String getLedgerSourceInfo() {
        return ledgerSourceInfo;
    }

    public void setLedgerSourceInfo(String ledgerSourceInfo) {
        this.ledgerSourceInfo = ledgerSourceInfo == null ? null : ledgerSourceInfo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getGivedGiftId() {
        return givedGiftId;
    }

    public void setGivedGiftId(Integer givedGiftId) {
        this.givedGiftId = givedGiftId;
    }

    public String getRedpacketRocordDate() {
        return redpacketRocordDate;
    }

    public void setRedpacketRocordDate(String redpacketRocordDate) {
        this.redpacketRocordDate = redpacketRocordDate == null ? null : redpacketRocordDate.trim();
    }

    public Integer getLedgerUid() {
        return ledgerUid;
    }

    public void setLedgerUid(Integer ledgerUid) {
        this.ledgerUid = ledgerUid;
    }

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveLedgerRecord [id=" + id + ", uid=" + uid + ", uidRole="
				+ uidRole + ", ledgerAmount=" + ledgerAmount
				+ ", realLedgerAmount=" + realLedgerAmount + ", status="
				+ status + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", ledgerSource=" + ledgerSource
				+ ", ledgerSourceInfo=" + ledgerSourceInfo + ", orderNo="
				+ orderNo + ", givedGiftId=" + givedGiftId
				+ ", redpacketRocordDate=" + redpacketRocordDate
				+ ", ledgerUid=" + ledgerUid + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
    
    
    
}