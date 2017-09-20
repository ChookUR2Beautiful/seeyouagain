package com.xmniao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WalletExpenses {
    /**
     * 
     */
    private Long id;

    /**
     * 钱包拓展类型，与b_wallet_expansion表type字段保持一致
     */
    private Byte type;

    /**
     * 费率类型，1：比例，2：固定金额
     */
    private Byte rateType;

    /**
     * 手续费比例，或固定金额，比例填写小数，如，0.06，表示6%
     */
    private BigDecimal rate;

    /**
     * 
     */
    private Date modifyTime;
    
    private BigDecimal rateSum; //应收手续费

    public BigDecimal getRateSum() {
		return rateSum;
	}

	public void setRateSum(BigDecimal rateSum) {
		this.rateSum = rateSum;
	}

	/**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 钱包拓展类型，与b_wallet_expansion表type字段保持一致
     * @return type 钱包拓展类型，与b_wallet_expansion表type字段保持一致
     */
    public Byte getType() {
        return type;
    }

    /**
     * 钱包拓展类型，与b_wallet_expansion表type字段保持一致
     * @param type 钱包拓展类型，与b_wallet_expansion表type字段保持一致
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 费率类型，1：比例，2：固定金额
     * @return rate_type 费率类型，1：比例，2：固定金额
     */
    public Byte getRateType() {
        return rateType;
    }

    /**
     * 费率类型，1：比例，2：固定金额
     * @param rateType 费率类型，1：比例，2：固定金额
     */
    public void setRateType(Byte rateType) {
        this.rateType = rateType;
    }

    /**
     * 手续费比例，或固定金额，比例填写小数，如，0.06，表示6%
     * @return rate 手续费比例，或固定金额，比例填写小数，如，0.06，表示6%
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 手续费比例，或固定金额，比例填写小数，如，0.06，表示6%
     * @param rate 手续费比例，或固定金额，比例填写小数，如，0.06，表示6%
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 
     * @return modify_time 
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 
     * @param modifyTime 
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}