package com.xmn.saas.controller.api.v1.wallet.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;

/**
 * 钱包设置自动提现类
 * 
 * @author zhouxiaojian
 *
 */
public class SetupAutoWithdrawalRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -7517913388494732364L;


    @Min( 50 )
    @Max( 50000 )
    private double money;//店内收益

    @Min( 50 )
    @Max( 50000 )
    private double cmAmount;//店外收益

    @NotNull( message = "提现状态不能为空" )
    private Integer mention;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getCmAmount() {
        return cmAmount;
    }

    public void setCmAmount(double cmAmount) {
        this.cmAmount = cmAmount;
    }

    public Integer getMention() {
        return mention;
    }

    public void setMention(Integer mention) {
        this.mention = mention;
    }

    @Override
    public String toString() {
        return "SetupAutoWithdrawalRequest [money=" + money + ", cmAmount=" + cmAmount
                + ", mention=" + mention + "]";
    }
    
    
    
    


}
