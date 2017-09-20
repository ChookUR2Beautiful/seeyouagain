package com.xmniao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WalletLock {
    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 钱包ID
     */
    private Integer accountid;

    /**
     * 类型 1:佣金余额commision 2:赠送余额zbalance  
     */
    private Byte type;

    /**
     * 是否锁定添加 0:否 1:是
     */
    private Boolean lockAdd;

    /**
     * 是否锁定扣除 0:否 1:是
     */
    private Boolean lockSubtract;

    /**
     * 锁定前返利余额
     */
    private BigDecimal qbalance;

    /**
     * 锁定前佣金余额
     */
    private BigDecimal qcommision;

    /**
     * 锁定前营收余额
     */
    private BigDecimal qincome;

    /**
     * 锁定前余额
     */
    private BigDecimal qzbalance;

    /**
     * 锁定前钱包余额
     */
    private BigDecimal qamount;

    /**
     * 锁定前剩余积分
     */
    private BigDecimal qintegral;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 记录ID
     * @return id 记录ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 记录ID
     * @param id 记录ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 钱包ID
     * @return accountid 钱包ID
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * 钱包ID
     * @param accountid 钱包ID
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * 类型 1:佣金余额commision 2:赠送余额zbalance  
     * @return type 类型 1:佣金余额commision 2:赠送余额zbalance  
     */
    public Byte getType() {
        return type;
    }

    /**
     * 类型 1:佣金余额commision 2:赠送余额zbalance  
     * @param type 类型 1:佣金余额commision 2:赠送余额zbalance  
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 是否锁定添加 0:否 1:是
     * @return lock_add 是否锁定添加 0:否 1:是
     */
    public Boolean getLockAdd() {
        return lockAdd;
    }

    /**
     * 是否锁定添加 0:否 1:是
     * @param lockAdd 是否锁定添加 0:否 1:是
     */
    public void setLockAdd(Boolean lockAdd) {
        this.lockAdd = lockAdd;
    }

    /**
     * 是否锁定扣除 0:否 1:是
     * @return lock_subtract 是否锁定扣除 0:否 1:是
     */
    public Boolean getLockSubtract() {
        return lockSubtract;
    }

    /**
     * 是否锁定扣除 0:否 1:是
     * @param lockSubtract 是否锁定扣除 0:否 1:是
     */
    public void setLockSubtract(Boolean lockSubtract) {
        this.lockSubtract = lockSubtract;
    }

    /**
     * 锁定前返利余额
     * @return qbalance 锁定前返利余额
     */
    public BigDecimal getQbalance() {
        return qbalance;
    }

    /**
     * 锁定前返利余额
     * @param qbalance 锁定前返利余额
     */
    public void setQbalance(BigDecimal qbalance) {
        this.qbalance = qbalance;
    }

    /**
     * 锁定前佣金余额
     * @return qcommision 锁定前佣金余额
     */
    public BigDecimal getQcommision() {
        return qcommision;
    }

    /**
     * 锁定前佣金余额
     * @param qcommision 锁定前佣金余额
     */
    public void setQcommision(BigDecimal qcommision) {
        this.qcommision = qcommision;
    }

    /**
     * 锁定前营收余额
     * @return qincome 锁定前营收余额
     */
    public BigDecimal getQincome() {
        return qincome;
    }

    /**
     * 锁定前营收余额
     * @param qincome 锁定前营收余额
     */
    public void setQincome(BigDecimal qincome) {
        this.qincome = qincome;
    }

    /**
     * 锁定前余额
     * @return qzbalance 锁定前余额
     */
    public BigDecimal getQzbalance() {
        return qzbalance;
    }

    /**
     * 锁定前余额
     * @param qzbalance 锁定前余额
     */
    public void setQzbalance(BigDecimal qzbalance) {
        this.qzbalance = qzbalance;
    }

    /**
     * 锁定前钱包余额
     * @return qamount 锁定前钱包余额
     */
    public BigDecimal getQamount() {
        return qamount;
    }

    /**
     * 锁定前钱包余额
     * @param qamount 锁定前钱包余额
     */
    public void setQamount(BigDecimal qamount) {
        this.qamount = qamount;
    }

    /**
     * 锁定前剩余积分
     * @return qintegral 锁定前剩余积分
     */
    public BigDecimal getQintegral() {
        return qintegral;
    }

    /**
     * 锁定前剩余积分
     * @param qintegral 锁定前剩余积分
     */
    public void setQintegral(BigDecimal qintegral) {
        this.qintegral = qintegral;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}