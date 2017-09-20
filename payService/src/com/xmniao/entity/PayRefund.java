package com.xmniao.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PayRefund {
	
	//钱包ID
	private Integer accountid;
	
	//退款记录ID
    private Integer id;

    //支付ID
    private Long payId;

    //退款流水号
    private String refundId;

    //请求退款时间 yyyy-MM-dd HH:mm:ss
    private String sdate;

    //退款成功时间 yyyy-MM-dd HH:mm:ss
    private String edate;

    //退款状态
    private Integer status;

    //退款总金额
    private BigDecimal money;

    //钱包金额
    private BigDecimal amount;

    //佣金金额
    private BigDecimal commision;

    //返利金额
    private BigDecimal balance;

    //积分
    private BigDecimal integral;

    //第三方支付金额
    private BigDecimal samount;

    //第三方支付流水号
    private String thirdId;

    //退款描述
    private String description;
    
    //退款手续费
    private BigDecimal fees;
    
    //赠送金额
    private BigDecimal zamount;

    //第三方支付类型
    private String thirdName;
    
    //支付时间 yyyyMMddHHmmss
    private String payDate;
    
    //业务系统退款ID
    private BigInteger bid;
    
    //支付描述(通联支付时间)
    private String payDescription;
    
    //用户ID
    private Integer userId;
    
    
    private BigDecimal liveCoin;//鸟币支付金额
    
    private BigDecimal sellerCoin;//商家专享鸟币支付金额
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getSamount() {
        return samount;
    }

    public void setSamount(BigDecimal samount) {
        this.samount = samount;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId == null ? null : thirdId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	public BigDecimal getZamount() {
		return zamount;
	}

	public BigInteger getBid() {
		return bid;
	}

	public void setBid(BigInteger bid) {
		this.bid = bid;
	}

	public void setZamount(BigDecimal zamount) {
		this.zamount = zamount;
	}

	public String getPayDescription() {
		return payDescription;
	}

	public void setPayDescription(String payDescription) {
		this.payDescription = payDescription;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getLiveCoin() {
		return liveCoin;
	}

	public void setLiveCoin(BigDecimal liveCoin) {
		this.liveCoin = liveCoin;
	}

	public BigDecimal getSellerCoin() {
		return sellerCoin;
	}

	public void setSellerCoin(BigDecimal sellerCoin) {
		this.sellerCoin = sellerCoin;
	}

	@Override
	public String toString() {
		return "PayRefund [accountid=" + accountid + ", id=" + id + ", payId="
				+ payId + ", refundId=" + refundId + ", sdate=" + sdate
				+ ", edate=" + edate + ", status=" + status + ", money="
				+ money + ", amount=" + amount + ", commision=" + commision
				+ ", balance=" + balance + ", integral=" + integral
				+ ", samount=" + samount + ", thirdId=" + thirdId
				+ ", description=" + description + ", fees=" + fees
				+ ", zamount=" + zamount + ", thirdName=" + thirdName
				+ ", payDate=" + payDate + ", bid=" + bid + ", payDescription="
				+ payDescription + ", userId=" + userId + ", liveCoin="
				+ liveCoin + ", sellerCoin=" + sellerCoin + "]";
	}

}