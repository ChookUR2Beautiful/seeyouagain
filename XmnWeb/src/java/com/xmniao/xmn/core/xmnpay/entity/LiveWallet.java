package com.xmniao.xmn.core.xmnpay.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：LiveWallet
 * 
 * 类描述： 直播钱包
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-1-6 下午2:12:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveWallet extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5374871668138515625L;

	private Integer id;

    private Integer uid;

    private Integer status;

    private BigDecimal balance;//鸟蛋余额

    private BigDecimal commision;//鸟豆余额
    
    private BigDecimal zbalance;//鸟币余额
    
    private BigDecimal turnEggOut;
    
    private BigDecimal eggsTotal;//礼物，私信，弹幕总收入(5 主播礼物收入，6 主播私信收入  7 主播弹幕收入)

    private String sign;

    private Integer signType;

    private Date createTime;

    private Date updateTime;
    
    private String restrictive;//鸟币消费余额限制设置，默认 001 不限制，002 限制
    
    private BigDecimal limitBalance;//鸟币最低余额限制，当余额低于设置值，不允许消费。值为0时不限制

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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }
    
    
    

    /**
	 * @return the zbalance
	 */
	public BigDecimal getZbalance() {
		return zbalance;
	}

	/**
	 * @param zbalance the zbalance to set
	 */
	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}

	/**
	 * @return the turnEggOut
	 */
	public BigDecimal getTurnEggOut() {
		return turnEggOut;
	}

	/**
	 * @param turnEggOut the turnEggOut to set
	 */
	public void setTurnEggOut(BigDecimal turnEggOut) {
		this.turnEggOut = turnEggOut;
	}
	
	

	/**
	 * @return the eggsTotal
	 */
	public BigDecimal getEggsTotal() {
		return eggsTotal;
	}

	/**
	 * @param eggsTotal the eggsTotal to set
	 */
	public void setEggsTotal(BigDecimal eggsTotal) {
		this.eggsTotal = eggsTotal;
	}

	public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
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

	/**
	 * @return the restrictive
	 */
	public String getRestrictive() {
		return restrictive;
	}

	/**
	 * @param restrictive the restrictive to set
	 */
	public void setRestrictive(String restrictive) {
		this.restrictive = restrictive;
	}

	/**
	 * @return the limitBalance
	 */
	public BigDecimal getLimitBalance() {
		return limitBalance;
	}

	/**
	 * @param limitBalance the limitBalance to set
	 */
	public void setLimitBalance(BigDecimal limitBalance) {
		this.limitBalance = limitBalance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiveWallet [id=" + id + ", uid=" + uid + ", status=" + status
				+ ", balance=" + balance + ", commision=" + commision
				+ ", turnEggOut=" + turnEggOut + ", eggsTotal=" + eggsTotal
				+ ", sign=" + sign + ", signType=" + signType + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", restrictive="
				+ restrictive + ", limitBalance=" + limitBalance + "]";
	}
    
    
}