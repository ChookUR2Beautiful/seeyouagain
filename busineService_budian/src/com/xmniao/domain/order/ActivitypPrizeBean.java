package com.xmniao.domain.order;

import java.io.Serializable;

/**
 * 活动抽奖信息实体bean类
 * @author  LiBingBing
 * @version  [版本号, 2015年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivitypPrizeBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6473635077192503376L;
    
    /**
     * 活动ID
     */
    private int aid;
    
    /**
     * 派奖类型,1派送返利
     */
    private String prizeType;
    
    /**
     * 派奖金额
     */
    private double prizeNum;
    
    /**
     * 用户ID
     */
    private String uid;
    
    /**
     * 用户昵称
     */
    private String uName;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 处理时间
     */
    private String ptime;
    
    /**
     * 对应信息ID
     */
    private int piId;
    
    /**
     * 消费商户ID
     */
    private String sellerId;
    
    /**
     * 消费商户名称
     */
    private String sellerName;
    
    /**
     * 派送状态,0未派送,1为已派送
     */
    private int status;
    
    /**
     * 订单号
     */
    private String orderNumber;
    
    public int getAid()
    {
        return aid;
    }
    public void setAid(int aid)
    {
        this.aid = aid;
    }
    public String getPrizeType()
    {
        return prizeType;
    }
    public void setPrizeType(String prizeType)
    {
        this.prizeType = prizeType;
    }
    public double getPrizeNum()
    {
        return prizeNum;
    }
    public void setPrizeNum(double prizeNum)
    {
        this.prizeNum = prizeNum;
    }
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public String getuName()
    {
        return uName;
    }
    public void setuName(String uName)
    {
        this.uName = uName;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public int getPiId()
    {
        return piId;
    }
    public void setPiId(int piId)
    {
        this.piId = piId;
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
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    public String getPtime()
    {
        return ptime;
    }
    public void setPtime(String ptime)
    {
        this.ptime = ptime;
    }
}
