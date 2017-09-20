package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TRechargeRecord
 * 
 * 类描述： 商家会员卡充值订单表 实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月26日 下午8:10:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TRechargeRecord extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/*
	 * 记录ID,主键
	 */
	private Integer id;

	/*
	 * 充值订单号
	 */
    private Integer orderId;

    /*
     * 会员卡发行编号,商家会员卡不使用该数据
     */
    private Integer batchId;

    /*
     * 会员卡编号 
     */
    private Long noId;

    /*
     * 会员卡类型 0 商家会员卡
     */
    private Integer cardType;

    /*
     * 用户ID
     */
    private Integer uid;

    /*
     * 充值面额
     */
    private BigDecimal amount;

    /*
     * 到账金额
     */
    private BigDecimal profit;

    /*
     * 实收金额 (商家会员卡不使用该字段)
     */
    private BigDecimal cash;

    /*
     * 充值类型
     */
    private Integer type;

    /*
     * 充值状态
     */
    private Integer payStatus;

    /*
     * 支付方式
     */
    private String payType;

    /*
     * 支付ID
     */
    private String payId;

    /*
     * 支付流水号
     */
    private String number;

    /*
     * 第三方支付账号
     */
    private String thirdUid;

    /*
     * 充值时间
     */
    private Date rdate;

    /*
     * 更新时间
     */
    private Date udate;

    /*
     * 充值描述
     */
    private String depict;

    /*
     * 用户昵称
     */
    private String nname;
    
    /*
     * 用户手机号
     */
    private String phoneId;
    
    /*
     * 会员卡名称
     */
    private String cardName;
    
    /*
     * 充值时间(字符串格式)
     */
    private String rdateStr;
    
    /*
     * 到账时间(字符串格式)
     */
    private String udateStr;
    
    /*
     * 支付方式(汉字字符串格式)
     */
    private String payTypeStr;
    
    /*
     * 支付状态(汉字字符串格式)
     */
    private String payStatusStr;
    
    private BigDecimal minAmount;
    
    private BigDecimal maxAmount;
    
    private BigDecimal maxProfit;
    
    private BigDecimal minProfit;
    
    private Integer cardNo;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Long getNoId() {
		return noId;
	}

	public void setNoId(Long noId) {
		this.noId = noId;
	}

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public void setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid == null ? null : thirdUid.trim();
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
        if(rdate != null){
    		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		rdateStr=df.format(rdate);
        }
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
        if(udate != null){
    		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		udateStr=df.format(udate);
        }
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict == null ? null : depict.trim();
    }

    
	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}


	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getRdateStr() {
		return rdateStr;
	}

	public String getPayTypeStr() {
		return payTypeStr;
	}

	public String getUdateStr() {
		return udateStr;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
		if(payStatus != null){
			switch(payStatus){
        	case 0:
        		payStatusStr="待处理";
        		break;
        	case 1:
        		payStatusStr="充值成功";
        		break;
        	case 2:
        		payStatusStr="充值异常";
        		break;	
        	default :
        		payStatusStr="未知充值状态";
        		break;
        	}
		}
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
        if(payType!=null){
        	switch(payType){
        	case "1000001":
        		payTypeStr="支付宝支付";
        		break;
        	case "1000003":
        		payTypeStr="微信支付";
        		break;
        	default :
        		payTypeStr="未知方式支付";
        		break;
        	}
        }
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	
	public String getPayStatusStr() {
		return payStatusStr;
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

	public BigDecimal getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(BigDecimal maxProfit) {
		this.maxProfit = maxProfit;
	}

	public BigDecimal getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(BigDecimal minProfit) {
		this.minProfit = minProfit;
	}

	
	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "TRechargeRecord [id=" + id + ", orderId=" + orderId
				+ ", batchId=" + batchId + ", noId=" + noId + ", cardType="
				+ cardType + ", uid=" + uid + ", amount=" + amount
				+ ", profit=" + profit + ", cash=" + cash + ", type=" + type
				+ ", payStatus=" + payStatus + ", payType=" + payType
				+ ", payId=" + payId + ", number=" + number + ", thirdUid="
				+ thirdUid + ", rdate=" + rdate + ", udate=" + udate
				+ ", depict=" + depict + ", nname=" + nname + ", phoneId="
				+ phoneId + ", cardName=" + cardName + ", rdateStr=" + rdateStr
				+ ", udateStr=" + udateStr + ", payTypeStr=" + payTypeStr
				+ ", payStatusStr=" + payStatusStr + ", minAmount=" + minAmount
				+ ", maxAmount=" + maxAmount + ", maxProfit=" + maxProfit
				+ ", minProfit=" + minProfit + ", cardNo=" + cardNo + "]";
	}

}