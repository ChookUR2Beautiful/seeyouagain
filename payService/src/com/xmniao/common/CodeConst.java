package com.xmniao.common;
/**
 * 全局静态变量
 */
public interface CodeConst {

	/** 银联支付通道 */
	String GENET_ENC_CHANNEL_CODE = "001";

	/** 帮付通支付通道 */
	String GENET_BFT_CHANNEL_CODE = "002";

	/** U付支付通道 */
	String UMP_CHANNEL_CODE = "100";

	/** U付支付通道 - 普通通道 */
	String UMP_UNQUICK_SUB_CHANNEL_CODE = "01";

	/** U付支付通道 - 一键支付通道 */
	String UMP_QUICK_SUB_CHANNEL_CODE = "02";
	
	/** U付支付通道  - 绑定卡或解绑卡通道 */
	String UMP_BIND_UNBIND_SUB_CHANNEL_CODE = "03";
	
	/** U付支付通道  - 退款通道 */
	String UMP_REFUND_SUB_CHANNEL_CODE = "04";

	/** U付支付通道 - 所有支付通道 */
	String UMP_ALL_SUB_CHANNNEL_CODE = "00";

	/** 充值 */
	String DEPOSIT_ACTION = "deposit";

	/** 支付 */
	String TRANS_ACTION = "trans";
	
	/** 退款*/
	String REFUND_ACTION = "refund";
	
	/** 解绑 */
	String UNBIND_ACTION = "unbind";
	
	/** 绑定 */
	String BIND_ACTION = "bind";

	/** 页面回调 */
	String PAGE_CALLBACK_ACTION = "page_callback";
	
	/** u付交易成功状态 */
	String TRADE_SUCCESS = "TRADE_SUCCESS";
	
	/** u付 交易创建，等待买家付款。 */
	String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	
	/** u付 交易关闭 在指定时间段内未支付时关闭的交易；*/
	String TRADE_CLOSED = "TRADE_CLOSED";
	
	/** u付 交易撤销*/
	String TRADE_CANCEL = "TRADE_CANCEL";
	
	/** u付 交易失败*/
	String TRADE_FAIL = "TRADE_FAIL";
	
	/** 退款成功*/
	String REFUND_SUCCESS = "REFUND_SUCCESS";
	
	/** 退款失败*/
	String REFUND_FAIL = "REFUND_FAIL";
	
	/** 退款结果不明 */
	String REFUND_UNKNOWN = "REFUND_UNKNOWN";
	
	/** 退款处理中*/
	String REFUND_PROCESS = "REFUND_PROCESS";
	
	/**支付宝通道*/
	String ZHI_FU_BAO_CHANNEL_CODE="1001";
	/**支付宝普通通道*/
	 String ZHI_FU_BAO_SUBCHANNEL_CODE="1002";
	 
	/**支付宝异步回调*/
	String ZHI_FU_BAO = "zhi_fu_bao";
	
	/**支付宝同步回调*/
	String ZHI_FU_BAO_CALLBACK = "zhi_fu_bao_callback";
	
	/**支付宝退款回调 */
	String ZHI_FU_BAO_REFUND = "zhi_fu_bao_refund";
	
	/**支付宝等待卖家收款(卖家帐号冻结)*/
   String TRADE_PENDING = "TRADE_PENDING";
  
   /**支付宝交易成功不做任何操作*/
   String TRADE_FINISHED = "TRADE_FINISHED";
   
   /** 成功 */
   String SUCCESS = "SUCCESS";
   
   /** u付代发成功 **/
   String UPAY_SUCCESS = "4";
   
   /** u付代发失败 **/
   String UPAY_FAIL = "3";
   
   /** u付代发待确认 **/
   String UPAY_NOTCONFIRMED = "10";
}
