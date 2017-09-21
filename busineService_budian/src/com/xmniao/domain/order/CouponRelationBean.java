package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 优惠券订单关系实体类
 * @author  LiBingBing
 * @version  [版本号, 2015年6月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CouponRelationBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 50055204939549420L;

    /**
     * 订单号
     */
    private String bid;
    
    /**
     * 优惠卷序列号
     */
    private String cdid;
    
    /**
     * 优惠卷面额
     */
    private BigDecimal cdenom = new BigDecimal(0.00);
    
    /**
     * 优惠券支付金额
     */
    private BigDecimal cuser = new BigDecimal(0.00);
    
    /**
     * 优惠劵类型，1平台发行，2商家发行
     */
    private int ctype;

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }

    public String getCdid()
    {
        return cdid;
    }

    public void setCdid(String cdid)
    {
        this.cdid = cdid;
    }

    public BigDecimal getCdenom()
    {
        return cdenom;
    }

    public void setCdenom(BigDecimal cdenom)
    {
        this.cdenom = cdenom;
    }

    public BigDecimal getCuser()
    {
        return cuser;
    }

    public void setCuser(BigDecimal cuser)
    {
        this.cuser = cuser;
    }

    public int getCtype()
    {
        return ctype;
    }

    public void setCtype(int ctype)
    {
        this.ctype = ctype;
    }
}
