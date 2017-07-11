package com.xmn.saas.controller.api.v1.wallet.vo;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;

/**
 * 提现申请类
 * 
 * @author zhouxiaojian
 *
 */
public class WithdrawalApplyRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -1053433817412527952L;

    @NotNull( message = "提现金额不能为空" )
    private double amount; // 提现金额

    @NotNull( message = "收益类型不能为空" )
    private Integer type;// 1营业收入 2 店外收益

    private Integer accountId; // 提现账户id

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }



}
