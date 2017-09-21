package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 合作商日统计实体Bean类
 * @author  LiBingBing
 * @version  [版本号, 2014年12月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JointDayCensusBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4025846808594949206L;
    
    /**
     * 合作商ID
     */
    private String jointId;
    
    /**
     * 合作商名称
     */
    private String corporate;
    
    /**
     * 日收益总额
     */
    private BigDecimal profitTotal = new BigDecimal(0.00);
    
    /**
     * 日收益到账总额
     */
    private BigDecimal accountTotal = new BigDecimal(0.00);
    
    /**
     * 订单日期
     */
    private String orderDate;
    
    /**
     * 统计时间
     */
    private String censusTime;

    public String getJointId()
    {
        return jointId;
    }

    public void setJointId(String jointId)
    {
        this.jointId = jointId;
    }

    public String getCorporate()
    {
        return corporate;
    }

    public void setCorporate(String corporate)
    {
        this.corporate = corporate;
    }

    public BigDecimal getProfitTotal()
    {
        return profitTotal;
    }

    public void setProfitTotal(BigDecimal profitTotal)
    {
        this.profitTotal = profitTotal;
    }

    public BigDecimal getAccountTotal()
    {
        return accountTotal;
    }

    public void setAccountTotal(BigDecimal accountTotal)
    {
        this.accountTotal = accountTotal;
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
