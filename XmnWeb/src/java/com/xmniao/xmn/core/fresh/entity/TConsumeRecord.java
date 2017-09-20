package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TConsumeRecord
 * 
 * 类描述： 会员卡消费记录实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年2月16日 下午4:03:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TConsumeRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Long noId;

    private Integer cardType;

    private Integer uid;

    private String nname;

    private String phoneId;

    private Integer sellerId;

    private String sellerName;

    private BigDecimal amount;

    private Long bid;

    private BigDecimal money;

    private Date rdate;

    private Date udate;

    /*
     * 最小消费金额
     */
    private BigDecimal minAmount;
    
    /*
     * 最大消费金额
     */
    private BigDecimal maxAmount;
    
    /*
     * 最小订单金额
     */
    private BigDecimal minMoney;
    
    /*
     * 最大订单金额
     */
    private BigDecimal maxMoney;
    
    /*
     * 消费日期-String类型
     */
    private String rdateStr;
    
    /*
     * 会员卡名
     */
    private String cardName;
    
    /*
     * 会员序号(t_card_seller主键)
     */
    private Integer cardNo;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNoId() {
		return noId;
	}

	public void setNoId(Long noId) {
		this.noId = noId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rdateStr = (rdate==null)?null:df.format(rdate);
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public BigDecimal getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(BigDecimal minMoney) {
		this.minMoney = minMoney;
	}

	public BigDecimal getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(BigDecimal maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getRdateStr() {
		return rdateStr;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "TConsumeRecord [id=" + id + ", noId=" + noId + ", cardType="
				+ cardType + ", uid=" + uid + ", nname=" + nname + ", phoneId="
				+ phoneId + ", sellerId=" + sellerId + ", sellerName="
				+ sellerName + ", amount=" + amount + ", bid=" + bid
				+ ", money=" + money + ", rdate=" + rdate + ", udate=" + udate
				+ ", minAmount=" + minAmount + ", maxAmount=" + maxAmount
				+ ", minMoney=" + minMoney + ", maxMoney=" + maxMoney
				+ ", rdateStr=" + rdateStr + ", cardName=" + cardName
				+ ", cardNo=" + cardNo + "]";
	}
	
}