package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 调单申请记录信息
 * @author  LiBingBing
 * @version  [版本号, 2015年7月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AdjustApplyBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6177165287501192629L;
    
    /**
     * 申请商家
     */
    private String sellerid;
    
    /**
     * 调单订单号
     */
    private String bid;
    
    /**
     * 调单的手机号
     */
    private String phoneid;
    
    /**
     * 调单的消费金额
     */
    private BigDecimal money;
    
    /**
     * 调单申请时间
     */
    private String sdate;
    
    /**
     * 调单状态
     */
    private String handlestatu;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 调单用户ID
     */
    private String uid;
    
    /**
     * 调单处理时间
     */
    private String pdate;
    
    /**
     * 调单处理人
     */
    private String handleuser;
    
    /**
     * 驳回原因
     */
    private String reason;

    public String getSellerid()
    {
        return sellerid;
    }

    public void setSellerid(String sellerid)
    {
        this.sellerid = sellerid;
    }

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }

    public String getPhoneid()
    {
        return phoneid;
    }

    public void setPhoneid(String phoneid)
    {
        this.phoneid = phoneid;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getSdate()
    {
        return sdate;
    }

    public void setSdate(String sdate)
    {
        this.sdate = sdate;
    }

    public String getHandlestatu()
    {
        return handlestatu;
    }

    public void setHandlestatu(String handlestatu)
    {
        this.handlestatu = handlestatu;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getPdate()
    {
        return pdate;
    }

    public void setPdate(String pdate)
    {
        this.pdate = pdate;
    }

    public String getHandleuser()
    {
        return handleuser;
    }

    public void setHandleuser(String handleuser)
    {
        this.handleuser = handleuser;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
