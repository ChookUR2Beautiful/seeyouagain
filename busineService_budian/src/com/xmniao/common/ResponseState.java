package com.xmniao.common;

/**
 * 返回状态码类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ResponseState
{
    /**
     * 成功
     */
    public static final int SUCCESS = 100;
    
    
    /**
     * 其它错误
     */
    public static final int ELSEEROR = 101;
    
    
    /**
     * 订单失败
     */
    public static final int ORDERFAIL=102;
    
    
    /**
     * 业务员失败
     */
    public static final int STAFFFAIL=103;
    
    
    /**
     * 商家分账失败
     */
    public static final int LEDGERFAIL=104;
    
    /**
     * 向蜜客订单失败
     */
    public static final int MIKEORDERFAIL=105;
    
    /**
     * 订单退款失败
     */
    public static final int REFUNDFAIL=106;
    
    /**
     * 订单为空
     */
    public static final int ORDERNULL=107;
    
    /**
     * 订单已存在
     */
    public static final int ORDERREADY=108;
    
    /**
     * 打款失败
     */
    public static final int PAYFAIL=109;
    
    /**
     * 已打款成功
     */
    public static final int PAYREADY=110;
    
    /**
     * 订单已修改
     */
    public static final int ORDER_READY_MODIFY=111;
    
    /**
     * 订单已退款
     */
    public static final int ORDER_READY_REFUND=112;
    
    /**
     * 订单未支付
     */
    public static final int ORDER_NO_PAY=113;
    
    /**
     * 订单已分账
     */
    public static final int ORDER_READY_LEDGER=114;
    
    /**
     * 优惠券已重复使用
     */
    public static final int COUPON_READY_USING=115;
    
    /**
     * 传入参数异常
     */
    public static final int PARAM_ERROR=116;
    
}
