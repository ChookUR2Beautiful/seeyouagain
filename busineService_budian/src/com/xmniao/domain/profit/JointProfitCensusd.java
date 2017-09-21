package com.xmniao.domain.profit;

import java.io.Serializable;
import java.math.BigDecimal;

public class JointProfitCensusd implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -3781582871713887419L;

    /**
     * 合作商ID
     */
    private Integer jointid;

    /**
     * corporate
     */
    private String corporate;

    /**
     * 分账收益，所有订单的收益总额，包括 未分账及已分账数据。不包括手续费
     */
    private BigDecimal ledgerProfit = BigDecimal.ZERO;

    /**
     * 未到账收益，状态为 未分账的收益总额，不包括手续费
     */
    private BigDecimal notProfit = BigDecimal.ZERO;

    /**
     * 已到账收益，状态为已分账的收益总额，不包括手续费
     */
    private BigDecimal hasProfit = BigDecimal.ZERO;

    /**
     * 渠道手续费，所获得收益，需支付的手续费总额
     */
    private BigDecimal poundage = BigDecimal.ZERO;

    /**
     * 商户ID
     */
    private Integer sellerid;

    /**
     * 商户名称
     */
    private String sellername;

    /**
     * 商户流水总额，本店订单消费总额
     */
    private BigDecimal waterTotal = BigDecimal.ZERO;

    /**
     * 商户佣金总额，所属会员(本店/店外)消费 所得佣金总额
     */
    private BigDecimal commision = BigDecimal.ZERO;

    /**
     * 统计时间
     */
    private String censusTime;
    
    /**
     * 订单日期
     */
    private String orderDate;

    public Integer getJointid()
    {
        return jointid;
    }

    public void setJointid(Integer jointid)
    {
        this.jointid = jointid;
    }

    public String getCorporate()
    {
        return corporate;
    }

    public void setCorporate(String corporate)
    {
        this.corporate = corporate;
    }

    public BigDecimal getLedgerProfit()
    {
        return ledgerProfit;
    }

    public void setLedgerProfit(BigDecimal ledgerProfit)
    {
        this.ledgerProfit = ledgerProfit;
    }

    public BigDecimal getNotProfit()
    {
        return notProfit;
    }

    public void setNotProfit(BigDecimal notProfit)
    {
        this.notProfit = notProfit;
    }

    public BigDecimal getHasProfit()
    {
        return hasProfit;
    }

    public void setHasProfit(BigDecimal hasProfit)
    {
        this.hasProfit = hasProfit;
    }

    public BigDecimal getPoundage()
    {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage)
    {
        this.poundage = poundage;
    }

    public Integer getSellerid()
    {
        return sellerid;
    }

    public void setSellerid(Integer sellerid)
    {
        this.sellerid = sellerid;
    }

    public String getSellername()
    {
        return sellername;
    }

    public void setSellername(String sellername)
    {
        this.sellername = sellername;
    }

    public BigDecimal getWaterTotal()
    {
        return waterTotal;
    }

    public void setWaterTotal(BigDecimal waterTotal)
    {
        this.waterTotal = waterTotal;
    }

    public BigDecimal getCommision()
    {
        return commision;
    }

    public void setCommision(BigDecimal commision)
    {
        this.commision = commision;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getCensusTime()
    {
        return censusTime;
    }

    public void setCensusTime(String censusTime)
    {
        this.censusTime = censusTime;
    }
}
