package com.xmniao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WalletExpansionRecord {
    /**
     * 
     */
    private Long id;

    /**
     * 关联b_wallet_expansion id
     */
    private Long walletExpansionId;

    /**
     * 操作类型，0：未知，1：增加余额，2：扣减余额（用于非转出扣余额，如手动扣减），3：转出到寻蜜鸟钱包余额(可提现)，4：转出到寻蜜鸟钱包余额(不可提现)，5：冻结，6：解冻
     */
    private Byte type;

    /**
     * 备注，用于操作备注描述，如：V客红包、冻结
     */
    private String remark;

    /**
     * 操作触发来源，用于记录记录来源，如，V客红包对应充值订单号
     */
    private String source;

    /**
     * 余额操作金额
     */
    private BigDecimal amount;

    /**
     * 余额操作前金额
     */
    private BigDecimal qamount;

    /**
     * 余额操作前金额
     */
    private BigDecimal hamount;

    /**
     * 记录时间
     */
    private Date createTime;
    
    /**
     * 钱包类型
     */
    private Byte expansionType;
    
    /**
     * uid
     */
    private Integer uid;
    
    
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Byte getExpansionType() {
		return expansionType;
	}

	public void setExpansionType(Byte expansionType) {
		this.expansionType = expansionType;
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
     * 关联b_wallet_expansion id
     * @return wallet_expansion_id 关联b_wallet_expansion id
     */
    public Long getWalletExpansionId() {
        return walletExpansionId;
    }

    /**
     * 关联b_wallet_expansion id
     * @param walletExpansionId 关联b_wallet_expansion id
     */	
    public void setWalletExpansionId(Long walletExpansionId) {
        this.walletExpansionId = walletExpansionId;
    }

    /**
     * 操作类型，0：未知，1：增加余额，2：扣减余额（用于非转出扣余额，如手动扣减），3：转出到寻蜜鸟钱包余额(可提现)，4：转出到寻蜜鸟钱包余额(不可提现)，5：冻结，6：解冻
     * @return type 操作类型，0：未知，1：增加余额，2：扣减余额（用于非转出扣余额，如手动扣减），3：转出到寻蜜鸟钱包余额(可提现)，4：转出到寻蜜鸟钱包余额(不可提现)，5：冻结，6：解冻
     */
    public Byte getType() {
        return type;
    }

    /**
     * 操作类型，0：未知，1：增加余额，2：扣减余额（用于非转出扣余额，如手动扣减），3：转出到寻蜜鸟钱包余额(可提现)，4：转出到寻蜜鸟钱包余额(不可提现)，5：冻结，6：解冻
     * @param type 操作类型，0：未知，1：增加余额，2：扣减余额（用于非转出扣余额，如手动扣减），3：转出到寻蜜鸟钱包余额(可提现)，4：转出到寻蜜鸟钱包余额(不可提现)，5：冻结，6：解冻
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 备注，用于操作备注描述，如：V客红包、冻结
     * @return remark 备注，用于操作备注描述，如：V客红包、冻结
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，用于操作备注描述，如：V客红包、冻结
     * @param remark 备注，用于操作备注描述，如：V客红包、冻结
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 操作触发来源，用于记录记录来源，如，V客红包对应充值订单号
     * @return source 操作触发来源，用于记录记录来源，如，V客红包对应充值订单号
     */
    public String getSource() {
        return source;
    }

    /**
     * 操作触发来源，用于记录记录来源，如，V客红包对应充值订单号
     * @param source 操作触发来源，用于记录记录来源，如，V客红包对应充值订单号
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 余额操作金额
     * @return amount 余额操作金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 余额操作金额
     * @param amount 余额操作金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 余额操作前金额
     * @return qamount 余额操作前金额
     */
    public BigDecimal getQamount() {
        return qamount;
    }

    /**
     * 余额操作前金额
     * @param qamount 余额操作前金额
     */
    public void setQamount(BigDecimal qamount) {
        this.qamount = qamount;
    }

    /**
     * 余额操作前金额
     * @return hamount 余额操作前金额
     */
    public BigDecimal getHamount() {
        return hamount;
    }

    /**
     * 余额操作前金额
     * @param hamount 余额操作前金额
     */
    public void setHamount(BigDecimal hamount) {
        this.hamount = hamount;
    }

    /**
     * 记录时间
     * @return create_time 记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 记录时间
     * @param createTime 记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}