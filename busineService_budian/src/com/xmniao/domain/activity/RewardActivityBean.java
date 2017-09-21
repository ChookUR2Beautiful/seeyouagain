package com.xmniao.domain.activity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 打赏活动业务实体类
 * @author  LiBingBing
 * @version  [版本号, 2015年3月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RewardActivityBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2002650968618952308L;
    
    /**
     * 订单ID
     */
    private String orderCode;
    
    /**
     * 消费商家ID
     */
    private String sellerId;
    
    /**
     * 消费商家名称
     */
    private String sellerName;
    
    /**
     * 商家区域编码
     */
    private String areaId;
    
    /**
     * 消费合作商ID
     */
    private String jointId;
    
    /**
     * 消费合作商名称
     */
    private String corporate;
    
    /**
     * 打赏人ID
     */
    private int uid;
    
    /**
     * 打赏人电话
     */
    private String uPhone;
    
    /**
     * 打赏人昵称
     */
    private String uName;
    
    /**
     * 服务员在用户表中的ID
     */
    private String waiterId;
    
    /**
     * 服务员电话
     */
    private String sPhone;
    
    /**
     * 服务员昵称
     */
    private String sName;
    
    /**
     * 打赏金额
     */
    private BigDecimal tip;
    
    /**
     * 打赏时间
     */
    private String sdate;
    
    /**
     * 打赏状态，0未赏，1已赏
     */
    private int tipStatus;
    
    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(String sellerId)
    {
        this.sellerId = sellerId;
    }

    public String getSellerName()
    {
        return sellerName;
    }

    public void setSellerName(String sellerName)
    {
        this.sellerName = sellerName;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

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

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getuPhone()
    {
        return uPhone;
    }

    public void setuPhone(String uPhone)
    {
        this.uPhone = uPhone;
    }

    public String getuName()
    {
        return uName;
    }

    public void setuName(String uName)
    {
        this.uName = uName;
    }

    public String getWaiterId()
    {
        return waiterId;
    }

    public void setWaiterId(String waiterId)
    {
        this.waiterId = waiterId;
    }

    public String getsPhone()
    {
        return sPhone;
    }

    public void setsPhone(String sPhone)
    {
        this.sPhone = sPhone;
    }

    public String getsName()
    {
        return sName;
    }

    public void setsName(String sName)
    {
        this.sName = sName;
    }

    public BigDecimal getTip()
    {
        return tip;
    }

    public void setTip(BigDecimal tip)
    {
        this.tip = tip;
    }

    public int getTipStatus()
    {
        return tipStatus;
    }

    public void setTipStatus(int tipStatus)
    {
        this.tipStatus = tipStatus;
    }

    public String getSdate()
    {
        return sdate;
    }

    public void setSdate(String sdate)
    {
        this.sdate = sdate;
    }
}
