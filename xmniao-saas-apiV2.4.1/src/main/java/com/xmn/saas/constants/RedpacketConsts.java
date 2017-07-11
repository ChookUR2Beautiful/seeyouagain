package com.xmn.saas.constants;

/**
 * 红包常量
 * @ClassName: RedpacketConsts 
 * @Description: TODO 
 * @author dengqiang 
 * @date 2016年10月14日 下午12:42:26 
 *
 */
public class RedpacketConsts {

	/***红包类型***/
	public static final Integer DRAINAGE = 0; // 引流红包
	public static final Integer LIMIT = 1; // 限时到店红包
	public static final Integer CONSUME = 2; // 消费满赠红包
	public static final Integer RECOMMEND = 3; // 推荐消费红包
	public static final Integer COMMON = 4; // 普通抽奖红包

	/***红包状态***/
	public static final Integer DISABLE = 0; //禁用
	public static final Integer NORMAL = 1; //启用
	public static final Integer LOCK = 2;//锁定
	public static final Integer SHARE = 3;//已分享

	/***支付状态***/
	public static final Integer PAYNO = 1; //未支付
	public static final Integer PAYYES = 2; //支付成功
	public static final Integer PAYCALCEN = 3; //取消支付
	public static final Integer PAYFAILURE = 4; //支付失败
	public static final Integer REFUND=5 ; //已退款
	
	/***红包订单来源 ***/
	public static final String SOURCE="006"; 
	
	/***用户类型***/
	public static final String USER_TYPE="2";  //1:用户  2：商家
	
	/****订单类型***/
	public static final String ORDER_TYPE="2";
	
}
